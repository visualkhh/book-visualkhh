import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class CopyTransferToTest extends MyTimer {

	public static void main(String[] args) throws Exception {
		start();
		copyTransferTo();
		end("copyTransferTo");
	}
	
	public static void copyTransferTo() throws Exception {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		FileChannel in = null;
		FileChannel out = null;
		
		try {
			fis = new FileInputStream(filePath);
			fos = new FileOutputStream("C:/copyTransferTo.zip");
			in = fis.getChannel();
			out = fos.getChannel();
			
			in.transferTo(0, in.size(), out);
		} finally {
			if (in != null) in.close();
			if (out != null) out.close();
		}
	}
}
