package kr.or.javacafe;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import kr.or.javacafe.command.AbstractCommand;
import kr.or.javacafe.pool.ByteBufferPool;
import kr.or.javacafe.pool.ThreadPool;
import kr.or.javacafe.pool.WorkerThread;

/**
 * @(#)AdvancedNioServer.java
 * 
 * @author <a href="mailto:johnleen@hanmail.net">Song Ji-Hoon.</a>
 * @version 1.34, 02/10/22
 * 
 * AdvancedNioServer 클래스는 필자가 기사의 예제로 만든 서버 프레임워크의 중심이 되는 클래스이자 메인 클래스다.
 * log 나 ThreadPool 등의 필요한 리소스들을 초기화하고 클라이언트의 모든 요청을 관리하는 핵심부이다.
 * 
 * 기사를 쓰며 예제 소스를 만들 시간이 부족하여 아직 부족한 점들이 많다.
 * 일단 자체적으로 눈에 보이는 문제점 한가지만 언급하면 "&" 와 "<" 를 
 * 클라이언트가 메세지에 포함시켜서 보내면 xml 파싱시에 올바르지 않은 구문 에러를 발생시키게 된다.
 * 이것의 해결은 독자분들의 몫으로 남겨두겠다. 해결 방법의 힌트를 주자면 위의 두 문자를 다른 형태로 
 * 변경시켜서 요청을 보내고 서버에서 응답시에도 응답 메세지(클라이언트에게 뿌려줄 실제 메세지 데이터) 부분을 
 * 다시 변경해주면 될 것이다.
 */
public class AdvancedNioServer {
	
	private static int PORT = 4567;
	private static HashMap commandSet = new HashMap();
	private static Vector room = new Vector();
	private static FileHandler fileHandler;
	private static Logger logger = Logger.getLogger("kr.or.javacafe");

	private Selector selector;
	private ServerSocket serverSocket;
	private ServerSocketChannel serverSocketChannel;

	private ByteBufferPool bufferPool;
	private ThreadPool threadPool;

	public AdvancedNioServer() { initLog(); }
	
	public void initServer() {
		info("Server is initiate");
		
		try {
			File file = new File("C:\\temp.txt"); 
			bufferPool = new ByteBufferPool(1024*100, 1024*500, file); 
            threadPool = new ThreadPool(this);

            selector = Selector.open();
            
			serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.configureBlocking(false); 
			serverSocket = serverSocketChannel.socket(); 
			
			//InetAddress ia = InetAddress.getByName("218.235.126.118");
			InetAddress ia = InetAddress.getLocalHost(); 			 
			InetSocketAddress isa = new InetSocketAddress(ia, PORT);
			serverSocket.bind(isa);

			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		} catch (IOException e) {
			log(Level.WARNING, "NioServer/initServer()", e);
		} 
	}
	
	public void startServer() {
		info("Server is started..");
		try {
			while (true) {
				selector.select();
				
				Iterator it = selector.selectedKeys().iterator();
				while (it.hasNext()) {
					SelectionKey key = (SelectionKey) it.next();

					if (key.isAcceptable()) {
						ServerSocketChannel server = (ServerSocketChannel) key.channel();
						SocketChannel sc = server.accept();	
						registerChannel(selector, sc, SelectionKey.OP_READ);
						info("클라이언트가 접속했습니다.");
					} else if (key.isReadable()) {
						service(key);
					}
					it.remove();
				}								
			}      
		} catch (Exception e) {
			log(Level.WARNING, "NioServer/startServer()", e);
		} 
	}
	
	private void registerChannel(Selector selector, SelectableChannel sc, int ops) 
	  throws ClosedChannelException, IOException {
		if (sc == null) {
			info("Invalid Connection");
			return;
		}
		sc.configureBlocking(false);
		sc.register(selector, ops);
		putUser(sc);
	}

	private void service(SelectionKey key) throws IOException {
		WorkerThread worker = threadPool.getThread();
		if (worker == null) {
			info("WorkerThread is null");
			do {
				threadPool.putThread(worker);
				worker = null;
				worker = threadPool.getThread();
			} while (worker == null);
		}
		worker.serviceChannel(key);
	}
	
	
	public boolean isContainCommand(String key) { return commandSet.containsValue(key); }
	public AbstractCommand getCommand(String key) { return (AbstractCommand) commandSet.get(key); }
	public void putCommand(String key, AbstractCommand command) { commandSet.put(key, command); }
	
	public Vector getUsers() { return room; }
	public void putUser(SelectableChannel sc) { room.add(sc); }
	public void removeUser(Object channel) { room.removeElement(channel); }
	
	public ByteBufferPool getByteBufferPool() { return bufferPool; }

	public int getPort() { return PORT; }
	public void setPort(int port) { this.PORT = port; }
	
	
	/////////////////////////////  log part   ///////////////////////////////////////	
    public void initLog() {
    	try {
    		fileHandler = new FileHandler("MyLog.txt");
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
	
	
	//////////////////////////////   Main   /////////////////////////////////////
	public static void main(String[] args) {
		AdvancedNioServer server = new AdvancedNioServer();
		// PORT 변경하고 싶을시 여기서 변경해줌.
		// setPort(4000); 
		server.initServer();
		server.startServer();
	}
}
