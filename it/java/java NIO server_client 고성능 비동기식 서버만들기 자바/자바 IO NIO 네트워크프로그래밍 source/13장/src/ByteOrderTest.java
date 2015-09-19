import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ByteOrderTest {
	public static void main(String[] args) {
		ByteBuffer buf = ByteBuffer.allocate(10);
		System.out.println(buf.order());
		buf.order(ByteOrder.LITTLE_ENDIAN);
		System.out.println(buf.order());
	}
}
