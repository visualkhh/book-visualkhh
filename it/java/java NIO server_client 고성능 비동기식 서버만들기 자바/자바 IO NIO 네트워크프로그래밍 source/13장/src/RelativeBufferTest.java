import java.nio.ByteBuffer;

public class RelativeBufferTest {
	
	public static void main(String[] args) throws Exception {
		// 크기가 10인 ByteBuffer 를 생성. 
		ByteBuffer buf = ByteBuffer.allocate(10);
		System.out.print("Init Position : " + buf.position());
		System.out.print(", Init Limit : " + buf.limit());
		System.out.println(", Init Capacity : " + buf.capacity());
		
		// 현재 position 이 0 인데 이곳에 mark 해둠.
		buf.mark();
		// a, b c 를 순서대로 버퍼에 넣는다.
		buf.put((byte) 10).put((byte) 11).put((byte) 12);
		// mark 해둔 0 인덱스로 position 을 되돌림.
		buf.reset();
		
		// 현재 position 의 버퍼에 있는 데이터를 출력함.
		System.out.println("Value : " + buf.get() + ", Position : " + buf.position());
		System.out.println("Value : " + buf.get() + ", Position : " + buf.position());
		System.out.println("Value : " + buf.get() + ", Position : " + buf.position());
		// position 4 에는 아무값도 넣지 않았지만 기본적으로 0이 입력됨을 볼 수 있다.
		System.out.println("Value : " + buf.get() + ", Position : " + buf.position());
	} 
	
}
