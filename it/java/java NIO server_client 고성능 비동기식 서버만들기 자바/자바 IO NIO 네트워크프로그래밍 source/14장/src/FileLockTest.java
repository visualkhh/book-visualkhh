import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class SharedFileChannelInstanceTest {
	
	public static void main(String[] args) throws Exception {
		RandomAccessFile raf = new RandomAccessFile("C:/test.doc", "rw");
		
		raf.seek(1000);		
		FileChannel fc = raf.getChannel();
		System.out.println("File postion: " + fc.position());
		
		raf.seek(500);
		System.out.println("File postion: " + fc.position());
		
		fc.position(100);
		System.out.println("File postion: " + raf.getFilePointer());
	}

}
