import java.net.*;			
			
public class URLDecoderTest {			
			
	public static void main(String[] args) {		
		if(args.length != 1){	
			System.out.println("사용법 : jvaa URLDecoderTest 디코딩할 문자열");
			System.exit(1);
		}	
		String decodeStr = URLDecoder.decode(args[0]);	
		System.out.println(args[0] + "===>" + decodeStr);	
	}		
}			
