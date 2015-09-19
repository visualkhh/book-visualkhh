package net.daum.javacafe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Iterator;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SimpleChatClient {
	
	private static final String HOST = "localhost";
	private static final int PORT = 9090;
	
	private static FileHandler fileHandler;
	private static Logger logger = Logger.getLogger("net.daum.javacafe");
	
	private Selector selector = null;
	private SocketChannel sc = null;
	
	private Charset charset = null;
	private CharsetDecoder decoder = null;
	
	public SimpleChatClient() {
		charset = Charset.forName("EUC-KR");
		decoder = charset.newDecoder();
	}
	
	public void initServer() {
		try {
			// 실렉터를 연다.
			selector = Selector.open();
            
			// 소켓채널 생성.
			sc = SocketChannel.open(new InetSocketAddress(HOST, PORT));
			// 비블록킹 모드로 설정.
			sc.configureBlocking(false); 

			// 서버소켓채널을 실렉터에 등록.
			sc.register(selector, SelectionKey.OP_READ);
		} catch (IOException e) {
			log(Level.WARNING, "SimpleChatClient.initServer()", e);
		}
	}
	
	public void startServer() {
		startWriter();
		startReader();
	}
	
	private void startWriter() {
		info("Writer is started..");
		Thread t = new MyThread(sc);
		t.start();
	}
	
	private void startReader() {
		info("Reader is started..");
		try {
			while (true) {
				info("요청을 기다리는 중..");
				// 실렉터의 select() 메소드로 준비된 이벤트가 있는지 체크.
				selector.select();
				
				// 실렉터의 SelectedSet 에 저장된 준비된 이벤트들(SelectionKey들)을 하나씩 처리.
				Iterator it = selector.selectedKeys().iterator();
				while (it.hasNext()) {
					SelectionKey key = (SelectionKey) it.next();
					if (key.isReadable()) {
						// 이미 연결된 클라이언트가 메세지를 보낸 경우.
						read(key);
					}
					// 이미 처리한 이벤트이므로 반드시 삭제해줌.
					it.remove();
				}								
			}      
		} catch (Exception e) {
			log(Level.WARNING, "SimpleChatClient.startServer()", e);
		} 
	}
	
	private void read(SelectionKey key) {
		// SelectionKey 로부터 소켓채널을 얻어옴.
		SocketChannel sc = (SocketChannel) key.channel();
		// 바이트버퍼 생성.
		ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
		int read = 0;
		try {
			// 요청한 클라이언트의 소켓채널로부터 데이터를 읽어들임.
			read = sc.read(buffer);
			info(read + " byte 를 읽었습니다.");
		} catch (IOException e) {
			try {
				sc.close();
			} catch (IOException e1) {
			}
		}
		
		buffer.flip();
		
		String data = "";
		try {
			data = decoder.decode(buffer).toString();
		} catch (CharacterCodingException e) {
			log(Level.WARNING, "SimpleChatClient.read()", e);
		}
		
		System.out.println("Message - " + data);
		
		// 버퍼 메모리를 해제해줌.
		clearBuffer(buffer);
	}
	
	private void clearBuffer(ByteBuffer buffer) {
		if (buffer != null) {
			buffer.clear();
			buffer = null;
		}
	}
	
	
	
	/////////////////////////////  Log part   ///////////////////////////////	
    public void initLog() {
    	try {
    		fileHandler = new FileHandler("SimpleChatClient.log");
    	} catch (IOException e) {}

        logger.addHandler(fileHandler);
        logger.setLevel(Level.ALL);    
    }

	public void log(Level level, String msg, Throwable error) {
		logger.log(level, msg, error);
	}

	public void info(String msg) {
		logger.info(msg);
	}
	
	
	class MyThread extends Thread {
		private SocketChannel sc = null;
		public MyThread(SocketChannel sc) {
			this.sc = sc;
		}
		public void run() {
			ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
			try {
				while (!Thread.currentThread().isInterrupted()) {
					buffer.clear();
					BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
					String message = in.readLine();
						
					if (message.equals("quit") || message.equals("shutdown")) {					
						System.exit(0);
					}
						
					buffer.put(message.getBytes());
					buffer.flip();
	
					sc.write(buffer);
				}
			} catch (Exception e) {
				log(Level.WARNING, "MyThread.run()", e);
			} finally {
				clearBuffer(buffer);
			}
		}
	}

	///////////////////////////// Main ////////////////////////////
	public static void main(String[] args) {
		SimpleChatClient scc = new SimpleChatClient();
		scc.initLog();
		scc.initServer();
		scc.startServer();
	}
}
