import java.io.FileInputStream;
import java.io.FileOutputStream;

public class CopyIOTest extends MyTimer {
	
	public static void main(String[] args) throws Exception {
		start();
		copyIO();
		end("copyIo");
	}
	
	public static void copyIO() throws Exception {
		FileInputStream fis = new FileInputStream(filePath);
		FileOutputStream fos = new FileOutputStream("C:/copyIO.zip");
		
		byte[] buf = new byte[fis.available()];
		try {
			fis.read(buf);
			fos.write(buf);
		} finally {
			if (fis != null) fis.close();
			if (fos != null) fos.close();
		}
	}
}
