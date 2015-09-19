import java.io.*;

public class StreamReaderTest {

	public static void main(String[] args) {
		if(args.length != 1){
			System.out.println("사용법 : java StreamReaderTest 파일명");
			System.exit(0);
		}//if

		FileInputStream fis = null;
		InputStreamReader isr = null;
		OutputStreamWriter osw = null;
		try{
			fis = new FileInputStream(args[0]);
			isr = new InputStreamReader(fis);
			osw = new OutputStreamWriter(System.out);
			char[] buffer = new char[512];
			int readcount = 0;
			while((readcount = isr.read(buffer)) != -1){
				osw.write(buffer,0,readcount);
			}
		}catch(Exception ex){
			System.out.println(ex);
		}finally{
			try{
				fis.close();
				isr.close();
				osw.close();
			}catch(Exception e){}
		}
		
	}
}
