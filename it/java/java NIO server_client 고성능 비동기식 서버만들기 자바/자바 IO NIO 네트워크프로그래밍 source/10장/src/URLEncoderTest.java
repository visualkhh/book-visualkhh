import java.net.*;			
			
public class URLEncoderTest {			
			
	public static void main(String[] args) {		
		if(args.length != 1){	
			System.out.println("사용법 : jvaa URLEncoderTest 인코딩할 문자열");
			System.exit(1);
		}	
		String encodeStr = URLEncoder.encode(args[0]);	
		System.out.println(args[0] + "===>" + encodeStr);	
	}		
}			
