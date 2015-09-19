import java.nio.ByteBuffer;

public class SliceTest {
	public static void main(String[] args) {
		// 크기가 10인 ByteBuffer 생성
		ByteBuffer buf = ByteBuffer.allocate(10);
		// 버퍼의 position 인덱스와 같은 값을 넣음
		buf.put((byte) 0).put((byte) 1).put((byte) 2).put((byte) 3).put((byte) 4)
		.put((byte) 5).put((byte) 6).put((byte) 7).put((byte) 8).put((byte) 9);
		// position 을 3으로 변경
		buf.position(3);
		// limit 를 9로 변경
		buf.limit(9);
		
		// 원래 버퍼의 일부분을 Slice한 버퍼 생성
		ByteBuffer buf2 = buf.slice();
		// Slice한 버퍼의 position, limit, capacity를 출력
		System.out.println(
				"1) Position: " + buf2.position() 
				+ ", Limit: " + buf2.limit() 
				+ ", Capacity: " + buf2.capacity()
		);
		
		// Slice한 버퍼의 내용을 출력
		while (buf2.hasRemaining()) {
			System.out.print(buf2.get() + " ");
		}
		
		// 원래 버퍼에 3번째 값을 바꿈
		buf.put(3, (byte) 10);
		System.out.println("\n" + "=> buf 의 3 값을 10 으로 바꿈");

		// 원래 버퍼와 Slice한 버퍼에서 값이 변경되었는지 확인 위해 출
		System.out.println("Original Buffer Value : " + buf.get(3));
		System.out.println("SliceBuffer Value : " + buf2.get(0));
		
		// 복사된 버퍼에 4번째 값을 바꿈
		buf.put(4, (byte) 11);
		System.out.println("=> buf2 의 4 값을 12 로 바꿈");

		// 원래 버퍼와 Slice한 버퍼에서 값이 변경되었는지 확인 위해 출
		System.out.println("Original Buffer Value : " + buf.get(4));
		System.out.println("SliceBuffer Value : " + buf2.get(1));
	}
}
