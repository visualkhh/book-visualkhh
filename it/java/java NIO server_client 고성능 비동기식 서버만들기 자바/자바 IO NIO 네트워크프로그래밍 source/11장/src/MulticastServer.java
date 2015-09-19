import java.io.*; 	
import java.net.*; 	
	
public class MulticastServer extends Thread { 	
  DatagramSocket socket = null; 	
  DatagramPacket packet = null;	
  InetAddress channel =null;	
  int port = 20001; 	
  String address = "239.0.0.1";	
  boolean onAir = true; 	
	
  public MulticastServer() throws IOException { 	
    super("멀티케스트 방송국"); 	
    socket = new DatagramSocket(port); 	
  } 	
	
  public void run() { 	
  	String msg = "멀티 케스트 방송이 잘 들리시나요?";
    byte[] b = new byte[100]; 	
    while (onAir) { 	
      try { 	
        b = msg.getBytes(); // 바이트 배열로 만듦	
        channel = InetAddress.getByName(address); 	
        packet = new DatagramPacket(b, b.length, channel, port); 	
        socket.send(packet);	
        try { 	
          sleep(500);	
          System.out.println("방송중입니다.");	
        } catch (InterruptedException e) { } 	
      } catch (IOException e) { 	
          e.printStackTrace(); 	
      }  	
    } 	
    socket.close(); 	
  } 	
	
  public static void main(String[] args) throws java.io.IOException { 	
    new MulticastServer().start(); 	
  } 	
}	
