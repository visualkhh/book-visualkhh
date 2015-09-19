import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class ViewBufferTest {
	public static void main(String[] args) {
		// 크기가 10인 ByteBuffer 생성
		ByteBuffer buf = ByteBuffer.allocate(10);
		
		// 뷰 버퍼인 IntBuffer 생성
		IntBuffer ib = buf.asIntBuffer();
		// 뷰 버퍼의 초기값 출력
		System.out.println("position=" + ib.position() + ", limit=" + ib.limit() + ", capacity=" + ib.capacity());
		// 뷰 버퍼에 데이터 쓰기
		ib.put(1024).put(2048);
		// 뷰 버퍼의 데이터 출력
		System.out.println("index_0=" + ib.get(0) + ", index_1=" + ib.get(1));
		
		// 원본 버퍼도 변경되었는지 확인 위해 출력
		while (buf.hasRemaining()) {
			System.out.print(buf.get() + " ");
		}
	}
}
