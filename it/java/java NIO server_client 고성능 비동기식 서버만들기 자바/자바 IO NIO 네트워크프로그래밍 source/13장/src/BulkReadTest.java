import java.nio.ByteBuffer;

public class BulkReadTest {
	
	public static void main(String[] args) throws Exception {
		ByteBuffer buf = ByteBuffer.allocate(10);
		buf.put((byte) 0).put((byte) 1).put((byte) 2).put((byte) 3).put((byte) 4);
		buf.mark();
		buf.put((byte) 5).put((byte) 6).put((byte) 7).put((byte) 8).put((byte) 9);
		buf.reset();
		
		byte[] b = new byte[15];
		
		// 버퍼에서 얼마나 쓸 수 있는지를 계산..
		int size = buf.remaining();
		if (b.length < size) {
			size = b.length;
		}
		// 버퍼 또는 배열에서 이용할 수 있는 만큼만 이용..
		buf.get(b, 0, size);
		System.out.println("Position : " + buf.position() + ", Limit : " + buf.limit());

		// byte[] 에 담은 데이터를 처리하기 위한 메소드.
		doSomething(b, size);
	}
	
	// byte[] 과 함께 배열이 얼마나 사용되었는지 크기를 함께 넘겨줘서 사용함.
	public static void doSomething(byte[] b, int size) {
		for (int i = 0; i < size; i++) {
			System.out.println("byte = " + b[i]);
		}
	}
}
