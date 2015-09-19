package kr.or.javacafe;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Song Ji-Hoon.
 *
 * e-mail: johnleen@hanmail.net
 * JavaCafe: www.JavaCafe.or.kr
 * 
 * 원래 주석을 이런식으로 사용하는게 아니지만 독자분들의 이해를 돕기 위해
 * 주석을 자세하게 달았습니다.
 */
public class NioServer {
	
	private static int PORT = 4567;
	
	private static Vector room = new Vector();
	
	// non-blocking 에 사용될 Selector, ServerSocketChannel
	private Selector selector;
	private ServerSocket serverSocket;
	private ServerSocketChannel serverSocketChannel;
	
	// log 에 필요한 객체들
	private static FileHandler fileHandler;
	private static Logger logger = Logger.getLogger("kr.or.javacafe");
	
	
	public NioServer() { initLog(); }
	
	public void initServer() {
		info("Server is initiate");
		
		try {	
			selector = Selector.open();
			
			serverSocketChannel = ServerSocketChannel.open(); 
			serverSocketChannel.configureBlocking(false);
			serverSocket = serverSocketChannel.socket(); 

			InetAddress ia = InetAddress.getLocalHost();
			InetSocketAddress isa = new InetSocketAddress(ia, PORT);
			serverSocket.bind(isa);

			// ServerSocketChannel 을 Selector 에 OP_ACCEPT 로 등록함.
			// OP_ACCEPT 로 등록했으므로 ServerSocketChannel 로 요청하면 SelectionKey 가 OP_ACCEPT 값을 갖게 되고
			// 따라서 아래에 나올 key.isAcceptable() 조건문으로 구별해서 처리하게 됨.
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		} catch (IOException e) {
			log(Level.WARNING, "NioServer/initServer()", e);
		} 
	}
	
	public void startServer() {
		info("Server is start");
		try {
			while (true) {
				// 이벤트가 발생한 SelectableChannel 들을 선택해서 Selector 안의 Selected key set 에 저장. 
				int n = selector.select();			
				
				// 위에서 저장된 Selected key set 안의 SelectionKey 들을 Iterator 에 차례로 집어넣음.
				// 여기서 주의할점은 이렇게 가져온 key set 은 Selector 내부의 private key 들을 직접 참조하고 
				// 있으므로 다른 스레드에 의해 이 key set 이 변경되지 않도록 해야한다는 것이다.
				// 그렇지 않다면 원치 않는 결과를 보게 될 것이다.
				Iterator it = selector.selectedKeys().iterator();
				
				// 남는 key 가 있을때까지 루프를 돌며 요청을 처리하는 부분.
				while (it.hasNext()) {
					SelectionKey key = (SelectionKey) it.next();
										
					// key 가 OP_ACCEPT 라면 true 가 됨.
					// initServer() 에서 ServerSocketChannel 생성시 Selector 에 OP_ACCEPT 로 등록했으므로.
                    // ServerSocketChannel 에 접속한 클라이언트라면 이곳에서 처리됨.
					if (key.isAcceptable()) {
						// 주어진 key 로 ServerSocketChannel 을 만듬.
						ServerSocketChannel server = (ServerSocketChannel) key.channel();
						SocketChannel sc = server.accept();	

						// 접속한 클라이언트 SocketChannel 을 read 모드로 등록함.
						// 앞으로 이 SocketChannel 은 OP_READ 의 key 값을 갖게 됨.
						boolean isRegist = registerChannel(selector, sc, SelectionKey.OP_READ);
						
						// chatting 을 위해 static 으로 만든 방에 접속한 사용자를 추가함.
						if (isRegist) 
							room.addElement(sc);

						// 콘솔창에 인자로 주어진 스트링을 시간과 함께 보여줌.
						info("Key is Acceptable");

					// OP_READ 의 key 라면 이곳에서 처리함.
					} else if (key.isReadable()) {
						service(key);

						// 콘솔창에 인자로 주어진 스트링을 시간과 함께 보여줌.
						info("Key is Readable");
					}
					// 요청을 마무리 했으므로 Iterator 에서 키를 제거함. 
					it.remove();
				}
			}      
		} catch (Exception e) {
			log(Level.WARNING, "NioServer/startServer()", e);
		} 
	}
	
	// ServerSocketChannel 에 등록한 클라이언트의 SocketChannel 을 read 모드(OP_READ)로 등록시키는 메소드.
	// read 모드로만 등록 시킨 이유는 채팅등의 서비스에서 서버는 단지 클라이언트의 요청을 읽고 그에 대한 명령을
	// 분석한후 적절한 응답을 해주면 되므로 다른 모드로 클라이언트를 등록 시킬 필요가 없어서 이렇게 했음.
	// 거의 대부분의 C/S 서버가 클라이언트를 read 모드로만 등록하면 됨.
	private boolean registerChannel(Selector selector, SelectableChannel sc, int ops) 
	  throws ClosedChannelException, IOException {
		boolean isSuccess = false;	
		// 주어진 SocketChannel 이 null 이면 콘솔로 알리고 그냥 리턴함. 
		// 거의 없는 일이겠지만 혹시 있을지도 모르므로 처리했음.
		if (sc == null) {
			info("Invalid Connection");
			return isSuccess;
		}
		// 주어진 SocketChannel 을 non-blocking 으로 설정.
		sc.configureBlocking(false);

		// 인자로 주어진 selector 와 pos 로 SocketChannel 을 등록함.
		// 만약 OP_READ 와 OP_WRITE 두가지 오퍼레이션을 등록하고 싶다면
		// OP_READ | OP_WIRTE 를 ops 부분에 넣으면 됨.
		sc.register(selector, ops);
		
		isSuccess = true;
		return isSuccess;
	}
	
	// 주어진 key 로 SocketChannel 을 생성해서 메세지를 읽고 적절한 응답을 해주는 서비스 메소드.
	// 효율을 위해선 역시 이 메소드도 내부 처리 로직을 스레드풀로 구현해야함.
	// 이것은 다음호에서 구현할 것임.
	private void service(SelectionKey key) throws IOException {
		// key 로부터 SocketChannel 만듬.
		SocketChannel sc = (SocketChannel) key.channel();

        int readCount = 0;

		try {
			// ByteBuffer 를 만듬.
			ByteBuffer buf = ByteBuffer.allocateDirect(4096);

			// 위에서 만든 버퍼로 SocketChannel 로 부터 읽은 데이터를 저장함.
			readCount = sc.read(buf);
			
			// 만약 읽은 값이 0 보다 작다면 EOF 가 전달된 것이므로 소켓을 종료함.
			if (readCount < 0) {
				room.removeElement(sc);
				sc.close();
			}

			buf.flip();
			
			broadcast(buf);
			
			buf.clear();
						
        } catch (IOException e) {
        	info("클라이언트가 대화방을 나갔습니다.");
        	// Error 메세지를 안보여주기 위해 주석처리 했음.
            //log(Level.WARNING, "NioServer/service()", e);
            try {
            	room.removeElement(sc);
                sc.close();
            } catch (IOException ignored) {
            }
        }		
	}
	
	// room 에 있는 모든 클라이언트에게 브로드캐스트.
	private void broadcast(ByteBuffer buffer) throws IOException {		
		int size = room.size();
		for (int i = 0; i < size; i++) {
			// 버퍼를 다시 읽기 위해.
			buffer.rewind();
			SocketChannel sc = (SocketChannel) room.get(i);			
			sc.write(buffer);			
		}
	}	

	
	// PORT를 설정하고 현재 PORT 알아내는 setter/getter
	public int getPort() { return PORT; }
	public void setPort(int port) { this.PORT = port; }
		
    
	// 로그 관련된 객체들을 초기화하는 메소드.
    public void initLog() {
    	try {
			// 로그를 파일에 남기기 위해 FileHandler 를 만듬.
    		fileHandler = new FileHandler("MyLog.txt");
    	} catch (IOException e) {}
    	
		// FileHandler 를 등록함.
        logger.addHandler(fileHandler);
		// Log Level 을 모두 사용할 수 있게 함.
        logger.setLevel(Level.ALL);    
    }
    
	// 로그를 남기는 메소드.	
	public void log(Level level, String msg, Throwable error) {
		logger.log(level, msg, error);
	}
	
	// 프로그램 수행 중 정보를 나타낼 수 있는 메소드. 
	public void info(String msg) {
		logger.info(msg);
	}
	
	public static void main(String[] args) {
		NioServer server = new NioServer();
		// PORT 변경하고 싶을시 여기서 변경해줌.
		// setPort(4000); 
		server.initServer();
		server.startServer();
	}
}
