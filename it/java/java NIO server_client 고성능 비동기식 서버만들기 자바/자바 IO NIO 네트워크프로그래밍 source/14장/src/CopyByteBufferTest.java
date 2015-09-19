import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class CopyByteBufferTest extends MyTimer {

	public static void main(String[] args) throws Exception {
		start();
		copyByteBuffer();
		end("copyByteBuffer");
	}
	
	public static void copyByteBuffer() throws Exception {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		FileChannel in = null;
		FileChannel out = null;
		
		try {
			fis = new FileInputStream(filePath);
			fos = new FileOutputStream("C:/copyByteBuffer.zip");
			in = fis.getChannel();
			out = fos.getChannel();
			
			ByteBuffer buf = ByteBuffer.allocate((int) in.size());
			in.read(buf);
			buf.flip(); 
			out.write(buf);
		} finally {
			if (in != null) in.close();
			if (out != null) out.close();
		}
	}
}
