import java.net.*;			
import java.io.*;			
			
public class UDPEchoClient {			
			
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
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			dsock = new DatagramSocket();
			String line = null;
			while((line = br.readLine()) != null){
			
				// 전송	
				DatagramPacket sendPacket = new DatagramPacket(line.getBytes(), line.getBytes().length, inetaddr, port);	
				dsock.send(sendPacket);	
				if(line.equals("quit"))	
					break;
				// 받기	
				byte[] buffer = new byte[line.getBytes().length];	
				DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);	
				dsock.receive(receivePacket);	
				// 받은 결과 출력.	
				String msg = new String(receivePacket.getData(), 0, receivePacket.getData().length);	
				System.out.println("전송받은 문자열 :" + msg);	
			} // while		
			System.out.println("UDPEchoClient를 종료합니다.");		
		}catch(Exception ex){			
			System.out.println(ex);		
		}finally{		
			if(dsock != null)	
				dsock.close();
		}		
	} // main			
} // class				
