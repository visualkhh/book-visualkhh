import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class SSCAcceptExample {

	public static void main(String[] args) throws Exception {
		ServerSocketChannel ssc = ServerSocketChannel.open();
		ssc.socket().bind(new InetSocketAddress(8080));
		ssc.configureBlocking(false);
		while (true) {
			System.out.println("커넥션 연결을 위해 대기중..");
			
			SocketChannel sc = ssc.accept();	
			if (sc == null) {
				Thread.sleep(1000);
			} else {
				System.out.println(sc.socket().getRemoteSocketAddress() + " 가 연결을 시도했습니다.");
			}
		}
	}
}
