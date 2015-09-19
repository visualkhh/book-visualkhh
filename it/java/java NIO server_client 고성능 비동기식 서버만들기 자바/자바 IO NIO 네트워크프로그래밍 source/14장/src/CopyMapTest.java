import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class CopyMapTest extends MyTimer {

	public static void main(String[] args) throws Exception {
		start();
		copyMap();
		end("copyMap");
	}
	
	public static void copyMap() throws Exception {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		FileChannel in = null;
		FileChannel out = null;
		
		try {
			fis = new FileInputStream(filePath);
			fos = new FileOutputStream("C:/copyMap.zip");
			in = fis.getChannel();
			out = fos.getChannel();
			
			MappedByteBuffer m = in.map(FileChannel.MapMode.READ_ONLY, 0, in.size());
			out.write(m);
		} finally {
			if (in != null) in.close();
			if (out != null) out.close();
		}
	}
}
