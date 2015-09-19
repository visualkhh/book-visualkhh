import java.net.*;			
			
public class UDPTimeClient {			
			
	public static void main(String[] args) {		
		if(args.length != 2){	
			System.out.println("사용법 : java UDPEchoClient ip port");
			System.exit(1);
		}	
		String ip = args[0];	
		int port = 0;	
		try{	
			port = Integer.parseInt(args[1]);
		}catch(Exception ex){	
			System.out.println("port 번호는 양의 정수로 입력하여 주세요.");
			System.exit(1);
		}		
		InetAddress inetaddr = null;		
		try {		
			inetaddr = InetAddress.getByName(ip);	
		} catch (UnknownHostException e) {		
			System.out.println("잘못된 도메인이나 ip입니다.");	
			System.exit(1);	
		}		
		DatagramSocket dsock = null;		
		try{		
			dsock = new DatagramSocket();	
			String line = null;	
			// 전송	
			DatagramPacket sendPacket = new DatagramPacket("".getBytes(), "".getBytes().length, inetaddr, port);	
			dsock.send(sendPacket);	
				
			byte[] buffer = new byte[200];	
			DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);	
			dsock.receive(receivePacket);	
			// 받은 결과 출력.	
			String msg = new String(receivePacket.getData(), 0, receivePacket.getData().length);	
			System.out.println("서버로 부터 전달받은 시간 :" + msg.trim());	
		}catch(Exception ex){		
			System.out.println(ex);	
		}finally{		
			if(dsock != null)	
				dsock.close();
		}		
	} // main			
} // class				
