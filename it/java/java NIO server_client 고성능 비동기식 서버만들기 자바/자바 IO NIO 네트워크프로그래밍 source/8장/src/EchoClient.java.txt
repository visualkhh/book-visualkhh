import java.net.*;				
import java.io.*;				
				
public class EchoClient{				
				
	public static void main(String[] args) {			
		try{		
			Socket sock = new Socket("127.0.0.1", 10001);	
			BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));	
			OutputStream out = sock.getOutputStream();	
			InputStream in = sock.getInputStream();	
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(out));	
			BufferedReader br = new BufferedReader(new InputStreamReader(in));	
			String line = null;	
			while((line = keyboard.readLine()) != null){	
				if(line.equals("quit")) break;
				pw.println(line);
				pw.flush();
				String echo = br.readLine();
				System.out.println("서버 로부터 전달받은 문자열 :" + echo);
			}	
			pw.close();	
			br.close();	
			sock.close();	
		}catch(Exception e){		
			System.out.println(e);	
		}		
	} // main			
}				
