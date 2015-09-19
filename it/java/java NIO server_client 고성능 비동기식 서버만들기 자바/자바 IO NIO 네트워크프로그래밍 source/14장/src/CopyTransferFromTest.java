import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class CopyTransferFromTest extends MyTimer {

	public static void main(String[] args) throws Exception {
		start();
		copyTransferFrom();
		end("copyTransferFrom");
	}
	
	public static void copyTransferFrom() throws Exception {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		FileChannel in = null;
		FileChannel out = null;
		
		try {
			fis = new FileInputStream(filePath);
			fos = new FileOutputStream("C:/copyTransferFrom.zip");
			in = fis.getChannel();
			out = fos.getChannel();
			
			out.transferFrom(in, 0, in.size());
		} finally {
			if (in != null) in.close();
			if (out != null) out.close();
		}
	}
}
