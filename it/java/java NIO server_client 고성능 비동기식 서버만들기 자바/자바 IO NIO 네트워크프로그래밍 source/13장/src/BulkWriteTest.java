import java.nio.ByteBuffer;

public class BulkWriteTest {
	
	public static void main(String[] args) throws Exception {
		ByteBuffer buf = ByteBuffer.allocate(10);
		buf.position(5);
		buf.mark();
		System.out.println("Position : " + buf.position() + ", Limit : " + buf.limit());
		
		byte[] b = new byte[15];
		for (int i = 0; i < b.length; i++) {
			b[i] = (byte) i;
		}
		
		// 버퍼에서 얼마나 쓸 수 있는지를 계산..
		int size = buf.remaining();
		if (b.length < size) {
			size = b.length;
		}
		// 버퍼 또는 배열에서 이용할 수 있는 만큼만 이용..
		buf.put(b, 0, size);
		System.out.println("Position : " + buf.position() + ", Limit : " + buf.limit());
		
		// byte[] 에 담은 데이터를 처리하기 위한 메소드.
		buf.reset();
		doSomething(buf, size);
	}
	
	// byte[] 과 함께 배열이 얼마나 사용되었는지 크기를 함께 넘겨줘서 사용함.
	public static void doSomething(ByteBuffer buf, int size) {
		for (int i = 0; i < size; i++) {
			System.out.println("byte = " + buf.get());
		}
	}
}
