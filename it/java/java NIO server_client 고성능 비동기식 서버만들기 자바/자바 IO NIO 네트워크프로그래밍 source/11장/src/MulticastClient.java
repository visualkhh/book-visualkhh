import java.io.*; 
import java.net.*; 

public class MulticastClient { 
  MulticastSocket receiver = null; 
  DatagramPacket packet = null;
  InetAddress channel =null;
  int port = 20001; 
  String address = "239.0.0.1";
  byte[] b = new byte[100]; 

  public MulticastClient () {
     try { 
        receiver = new MulticastSocket(port); 
        channel = InetAddress.getByName(address); 
        packet = new DatagramPacket(b, b.length); 
        receiver.joinGroup(channel); 
        for (int i=0; i<3; i++) { 
           receiver.receive(packet); 
           String notice = new String(packet.getData()); 
           System.out.println(notice);
        } 
        receiver.leaveGroup(channel); 
        receiver.close(); 
     } catch (IOException e) { 
        e.printStackTrace(); 
     }  
  } 

   public static void main(String[] args) throws IOException { 
     new MulticastClient();
   }
}
