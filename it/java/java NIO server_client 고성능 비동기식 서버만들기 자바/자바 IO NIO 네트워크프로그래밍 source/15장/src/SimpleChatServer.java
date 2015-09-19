package net.daum.javacafe;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SimpleChatServer {
	
	private static final String HOST = "localhost";
	private static final int PORT = 9090;
	
	private static FileHandler fileHandler;
	private static Logger logger = Logger.getLogger("net.daum.javacafe");
	
	private Selector selector = null;
	private ServerSocketChannel serverSocketChannel = null;
	private ServerSocket serverSocket = null;
	
	private Vector room = new Vector();
	
	public void initServer() {
		try {
			// 실렉터를 연다.
			selector = Selector.open();
            
			// 서버소켓채널 생성.
			serverSocketChannel = ServerSocketChannel.open();
			// 비블록킹 모드로 설정.
			serverSocketChannel.configureBlocking(false); 
			// 서버소켓채널과 연결된 서버소켓 가져옴.
			serverSocket = serverSocketChannel.socket(); 
			
			// 주어진 파라미터에 해당하는 주소, 포트로 서버소켓을 바인드.
			InetSocketAddress isa = new InetSocketAddress(HOST, PORT);
			serverSocket.bind(isa);

			// 서버소켓채널을 실렉터에 등록.
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		} catch (IOException e) {
			log(Level.WARNING, "SimpleChatServer.initServer()", e);
		} 
	}
	
	public void startServer() {
		info("Server is started..");
		try {
			while (true) {
				info("요청을 기다리는 중..");
				// 실렉터의 select() 메소드로 준비된 이벤트가 있는지 체크.
				selector.select();
				
				// 실렉터의 SelectedSet 에 저장된 준비된 이벤트들(SelectionKey들)을 하나씩 처리.
				Iterator it = selector.selectedKeys().iterator();
				while (it.hasNext()) {
					SelectionKey key = (SelectionKey) it.next();
					if (key.isAcceptable()) {
						// 서버소켓채널에 클라이언트가 접속을 시도한 경우.
						accept(key);
					} else if (key.isReadable()) {
						// 이미 연결된 클라이언트가 메세지를 보낸 경우.
						read(key);
					}
					// 이미 처리한 이벤트이므로 반드시 삭제해줌.
					it.remove();
				}								
			}      
		} catch (Exception e) {
			log(Level.WARNING, "SimpleChatServer.startServer()", e);
		} 
	}
	
	private void accept(SelectionKey key) {
		ServerSocketChannel server = (ServerSocketChannel) key.channel();
		SocketChannel sc;
		try {
			// 서버소켓채널의 accept() 메소드로 서버소켓 생성.
			sc = server.accept();
			// 생성된 소켓채널을 비블록킹 및 읽기 모드로 실렉터에 등록.
			registerChannel(selector, sc, SelectionKey.OP_READ);
			info(sc.toString() + " 클라이언트가 접속했습니다.");
		} catch (ClosedChannelException e) {
			log(Level.WARNING, "SimpleChatServer.accept()", e);
		} catch (IOException e) {
			log(Level.WARNING, "SimpleChatServer.accept()", e);
		}
	}
	
	private void registerChannel(Selector selector, SocketChannel sc, int ops) throws ClosedChannelException, IOException {
		if (sc == null) {
			info("Invalid Connection");
			return;
		}
		sc.configureBlocking(false);
		sc.register(selector, ops);
		// 채팅방에 추가
		addUser(sc);
	}

	private void read(SelectionKey key) {
		// SelectionKey 로부터 소켓채널을 얻어옴.
		SocketChannel sc = (SocketChannel) key.channel();
		// 바이트버퍼 생성.
		ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
		try {
			// 요청한 클라이언트의 소켓채널로부터 데이터를 읽어들임.
			int read = sc.read(buffer);
			info(read + " byte 를 읽었습니다.");
		} catch (IOException e) {
			try {
				sc.close();
			} catch (IOException e1) {
			}
			
			removeUser(sc);
			
			info(sc.toString() + " 클라언트가 접속을 해제했습니다.");
		}
		
		try {
			// 클라이언트가 보낸 메세지를 채팅방 안에 모든 사용자에게 브로드캐스트해줌.
			broadcast(buffer);
		} catch (IOException e) {
			log(Level.WARNING, "SimpleChatServer.broadcast()", e);
		}
		
		// 버퍼 메모리를 해제해줌.
		clearBuffer(buffer);
	}
	
	private void broadcast(ByteBuffer buffer) throws IOException {
		buffer.flip();
		
		Iterator iter = room.iterator();
		while (iter.hasNext()) {
			SocketChannel sc = (SocketChannel) iter.next();
			if (sc != null) {
				sc.write(buffer);
				buffer.rewind();
			}
		}
	}
	
	private void clearBuffer(ByteBuffer buffer) {
		if (buffer != null) {
			buffer.clear();
			buffer = null;
		}
	}
	
	private void addUser(SocketChannel sc) {
		room.add(sc);
	}
	
	private void removeUser(SocketChannel sc) {
		room.remove(sc);
	}
	
	
	
	/////////////////////////////  Log part   ///////////////////////////////	
    public void initLog() {
    	try {
    		fileHandler = new FileHandler("SimpleChatServer.log");
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
	
	///////////////////////////// Main ////////////////////////////
	public static void main(String[] args) {
		SimpleChatServer scs = new SimpleChatServer();
		scs.initLog();
		scs.initServer();
		scs.startServer();
	}

}
