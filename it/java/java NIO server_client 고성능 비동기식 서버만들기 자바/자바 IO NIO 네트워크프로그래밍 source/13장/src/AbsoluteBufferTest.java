import java.nio.ByteBuffer;

public class AbsoluteBufferTest {
	
	public static void main(String[] args) throws Exception {
		// 크기가 10인 ByteBuffer 를 생성. 
		ByteBuffer buf = ByteBuffer.allocate(10);
		System.out.print("Init Position : " + buf.position());
		System.out.print(", Init Limit : " + buf.limit());
		System.out.println(", Init Capacity : " + buf.capacity());
		
		buf.put(3, (byte) 3);
		buf.put(4, (byte) 4);
		buf.put(5, (byte) 5);
		// 위치를 지정해서 쓴 경우에는 position 이 변하지 않는다.
		System.out.println("Position : " + buf.position());
		
		// 버퍼에 있는 데이터를 출력함. 역시 position 이 변하지 않는다.
		System.out.println("Value : " + buf.get(3) + ", Position : " + buf.position());
		System.out.println("Value : " + buf.get(4) + ", Position : " + buf.position());
		System.out.println("Value : " + buf.get(5) + ", Position : " + buf.position());
		// position 9 에는 아무값도 넣지 않았지만 기본적으로 0이 입력됨을 볼 수 있다.
		System.out.println("Value : " + buf.get(9) + ", Position : " + buf.position());
	} 
	
}
