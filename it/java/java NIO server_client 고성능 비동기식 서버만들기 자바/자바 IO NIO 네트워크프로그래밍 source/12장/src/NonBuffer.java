import java.io.*;

public class NonBuffer {
	public static void main(String[] args) {
		NonBuffer io = new NonBuffer();
		try {
			// 파일복사 시작시간 기록..
			long startTime = System.currentTimeMillis();
			// 파일 복사 시작..
			io.copy("c:/TestFile.doc", "c:/TestFile2.doc");   
			// 파일복사 종료시간 기록..  
			long endTime = System.currentTimeMillis();
			// 복사한 시간이 얼마나 되는지 출력.
			System.out.println("NonBuffer 처리시간 : "	+ (endTime - startTime) + " milli seconds" );
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
			while (true) {
				// 1바이트를 읽는다.
				int bytedata = in.read();
				// 만약 EOF 라면 루프를 빠져나간다.
				if (bytedata == -1)
					break;
				// 앞서 읽은 1바이트를 파일에 쓴다.
				out.write(bytedata);
			}
		} finally {
			// finally 구문안에서 다 사용한 스트림을 안전하게 닫아준다.
			if (in != null)
				in.close();
			if (out !=null)
				out.close();
		}
	}
}
