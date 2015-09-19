import java.io.*;

public class SmallBuffer {
	public static void main(String[] args) {
		SmallBuffer io = new SmallBuffer();
		try {
			long startTime = System.currentTimeMillis();
			io.copy("c:/TestFile.doc", "c:/TestFile2.doc");            
			long endTime = System.currentTimeMillis();
			System.out.println("SmallBuffer 처리시간 : " + (endTime - startTime) + " milli seconds" );
		} catch (IOException e) { 
			e.printStackTrace();
		}
	}

	public static void copy(String fileFrom, String fileTo) throws IOException {
		InputStream inBuffer = null;
		OutputStream outBuffer = null;
		try {
			// 파일을 읽고 쓸 때 2048byte 의 크기를 갖는 버퍼스트림으로 버퍼링을 하면서 파일 복사를 함.
			InputStream in = new FileInputStream(fileFrom);
			inBuffer = new BufferedInputStream(in, 2048);
			OutputStream out = new FileOutputStream(fileTo);
			outBuffer = new BufferedOutputStream(out , 2048);
			while (true) {
				int bytedata = inBuffer.read();
				if (bytedata == -1)
					break;
				out.write(bytedata);
			}
		} finally {
			if (inBuffer != null)
				inBuffer.close();
			if (outBuffer !=null)
				outBuffer.close();
		}
	} 
}
