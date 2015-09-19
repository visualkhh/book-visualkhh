import java.io.*;

public class CharArrayInputOutputTest {

	public static void main(String[] args) {
		if(args.length != 1){
			System.out.println("사용법 : java CharArrayInputOutputTest 파일이름");
			System.exit(0);
		} // if end
		
		FileReader fis = null;
		CharArrayReader bais = null;
		CharArrayWriter baos = null;		
		try{
			fis = new FileReader(args[0]);
			baos = new CharArrayWriter();
			char[] buffer = new char[512];
			int readcount = 0;
			
			while((readcount = fis.read(buffer)) != -1){
				baos.write(buffer, 0, readcount);	
			}
			
			
			char[] fileArray = baos.toCharArray();
			System.out.println("파일의 내용을 모두 읽어들여 Char[]로 만들었습니다.");
			System.out.println("읽어들인 Char의 수 :" + fileArray.length);
			
			bais = new CharArrayReader(fileArray);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

			while((readcount = bais.read(buffer)) != -1){
				bw.write(buffer, 0, readcount);	
				bw.flush();
			}
			System.out.println("\n\n");
			System.out.println("읽어들인 내용을 모두 출력하였습니다.");			
		}catch(Exception ex){
			System.out.println(ex);
		}finally{
			try{
				fis.close();
				bais.close();
				baos.close();
			}catch(IOException ioe){
				System.out.println(ioe);
			}
		}
	}
}
