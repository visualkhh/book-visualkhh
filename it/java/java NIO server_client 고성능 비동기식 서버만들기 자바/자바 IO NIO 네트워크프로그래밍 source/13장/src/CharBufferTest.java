import java.nio.CharBuffer;

public class CharBufferTest {
	
	public static void main(String[] args) throws Exception {
		CharBuffer c = CharBuffer.allocate(10);		
		System.out.println("Position : " + c.position());
		
		c.put("hello ÁöÈÆ!");
		System.out.println("Position : " + c.position());
	}
}
