import java.io.*;

public class FullBuffer {
	public static void main(String[] args){
		FullBuffer io = new FullBuffer();
		try {
			long startTime = System.currentTimeMillis();
			io.copy("c:/TestFile.doc", "c:/TestFile2.doc");              
			long endTime = System.currentTimeMillis();
			System.out.println("FullBuffer 처리시간 : " + (endTime - startTime) + " milli seconds" );
		} catch (IOException e) { 
			e.printStackTrace();
		}
	}

	public static void copy(String fileFrom, String fileTo) throws IOException {
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(fileFrom);
			out = new FileOutputStream(fileTo);
			// 파일 크기 계산.
			int availableLength = in.available();
			// 파일 크기 만큼의 byte[] 형태의 버퍼 생성.
			byte[] buffer = new byte[availableLength];
			// 버퍼로 파일을 읽어들임.
			int bytedata = in.read(buffer);
			// 버퍼에 읽어들인 내용을 파일로 저장.
			out.write(buffer);
		} finally {
			if (in != null)
				in.close();
			if (out !=null)
				out.close();
		}
	}           
}
