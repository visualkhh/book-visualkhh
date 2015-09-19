package kr.or.javacafe.pool;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.ArrayList;
import java.util.logging.Level;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

import kr.or.javacafe.AdvancedNioServer;
import kr.or.javacafe.command.AbstractCommand;

/**
 * @(#)WorkerThread.java
 * 
 * @author <a href="mailto:johnleen@hanmail.net">Song Ji-Hoon.</a>
 * @version 1.46, 02/10/28
 * 
 * WorkerThread 클래스는 클라이언트의 요청이 들어왔을때 실제적인 처리를 하는 클래스다.
 * ThreadPool 에 의해 재사용을 목적으로 관리된다.
 * 이 클래스에서 가장 눈여겨 볼 부분은 Command 패턴과 자바의 런타임 동적 로딩의 특징을 결합해서
 * 동적으로 클라이언트의 요청을 처리할 클래스를 찾아 처리 할 수 있다는 점이다.
 * 따라서 추후 서버의 기능 확장시 그 기능에 대한 모듈만 만들어서 추가하고 클라이언트만 버전업해서
 * 재배포하면 된다. 서버는 기능 확장을 위해 중지시키지 않아도 동적으로 추가된 기능에 대한 모듈을 찾아서
 * 서비스한다.
 */
public class WorkerThread extends Thread {				

    private static final String CommandPath = "kr.or.javacafe.command.";
	 
	private AdvancedNioServer server;
    private SelectionKey key;
	private ThreadPool pool;
	private ByteBufferPool bufferPool;
	
	private Charset charset;
	private CharsetDecoder decoder;
	private CharBuffer charBuffer;
	
	private ByteArrayInputStream in;
	private SAXParserFactory factory;
	private SAXParser parser;
	private SaxHandler handler;

    public WorkerThread(ThreadPool pool, AdvancedNioServer server) {  
		this.pool = pool; 
		this.server = server; 
		bufferPool = server.getByteBufferPool();
		
		charset = Charset.forName("UTF-8");
		decoder = charset.newDecoder();
		
		try {
			factory = SAXParserFactory.newInstance(); 
			parser = factory.newSAXParser(); 
		} catch (ParserConfigurationException e) {
			server.log(Level.WARNING, "WorkerThread/WorkerThread()", e);
		} catch (SAXException ex) {
			server.log(Level.WARNING, "WorkerThread/WorkerThread()", ex);
		}
	}

    public synchronized void run() {
    	server.info(getName() + " is ready");
    	
	    while (true) 
loop:   {
			try {
				this.wait();
			} catch (InterruptedException e) {             
				server.log(Level.WARNING, "WorkerThread/run()", e);
				this.interrupted();
			}

			if (key == null) {
				pool.putThread(this);
				break loop;
			}
			
			try {
				requestProcess(key);
			} catch (IOException e) {
				server.info("클라이언트가 접속을 종료했습니다.");
				//server.log(Level.WARNING, "WorkerThread/run", e);
				
				try {
					server.removeUser(key.channel());
					key.channel().close();								
				} catch (IOException ex) {
					server.log(Level.WARNING, "WorkerThread/run()", e);
				} finally {
					key.selector().wakeup();
				}
			} finally {
				key = null;			
				pool.putThread(this);
			}	
	    }
	}		
	
	public synchronized void serviceChannel(SelectionKey key) {
		this.key = key;	
		key.interestOps(key.interestOps() & (~SelectionKey.OP_READ));
		this.notify();
	}
	
	private void requestProcess(SelectionKey key) throws IOException {
        SocketChannel sc = (SocketChannel) key.channel();
		ByteBuffer buffer = bufferPool.getMemoryBuffer();
		int count;
		
		AbstractCommand command = null;
			
		count = sc.read(buffer); 
		buffer.flip();
			
		if (count < 0) {
			server.removeUser(sc);
			sc.close();
			bufferPool.putBuffer(buffer);
			key.selector().wakeup();
			server.info("클라이언트가 접속을 종료했습니다.");
			return;
		}
		
		charBuffer = decoder.decode(buffer);						
		String temp = charBuffer.toString();
		byte[] bb = temp.substring(temp.indexOf("\r\n\r\n")).trim().getBytes("UTF-8");
		String[] param = parsingXML(bb);
System.out.println(param[0] + " " + param[1]);
		if (param == null) {
			finish(buffer);
			return;
		}
			
		if (server.isContainCommand(param[0])) {
			command = server.getCommand(param[0]);
		} else {
			try {
				command = (AbstractCommand) Class.forName(CommandPath + param[0]).newInstance();
			} catch (Exception e) {
				//server.log(Level.WARNING, "WorkerThread/requestProcess()", e);
				server.info("명령을 수행하는 커맨드 클래스가 존재하는지 확인해보세요.");
				finish(buffer);
				return;
			} 
				
			if (command == null) {
				finish(buffer);
				return;
			} else {
				server.putCommand(param[0], command);
			}
		}
        buffer.clear();
		command.execute(server, buffer, param[1]);
 
		finish(buffer);	     
	}
	
	private void finish(ByteBuffer buffer) {
		bufferPool.putBuffer(buffer);
		key.interestOps(key.interestOps() | SelectionKey.OP_READ);      
		key.selector().wakeup();		
	}

	private String[] parsingXML(byte[] xml) {
		String[] param = null;
		ArrayList list = null;
		
		if (xml == null) 
			return param;
		
		in = new ByteArrayInputStream(xml);
		try {          
			handler = SaxHandler.getInstance(); 
			parser.parse(in, handler);          
			list = handler.getContents();  
		} catch (SAXException e) {
			server.log(Level.WARNING, "WorkerThread/parsingXML()", e);
		} catch (IOException ex) {
			server.log(Level.WARNING, "WorkerThread/parsingXML()", ex);
		}
		
		param = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {  
			param[i] = (String) list.get(i);
		}		
		handler.clearSaxHandler();
		
		return param;
	}	
}
