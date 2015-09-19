import java.io.*;

public class ByteArrayInputOutputTest {

	public static void main(String[] args) {
		if(args.length != 1){
			System.out.println("사용법 : java ByteArrayInputOutputTest 파일이름");
			System.exit(0);
		} // if end
		
		FileInputStream fis = null;
		ByteArrayInputStream bais = null;
		ByteArrayOutputStream baos = null;		
		try{
			fis = new FileInputStream(args[0]);
			baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[512];
			int readcount = 0;
			// 파일로 부터 읽어들인 byte배열을 ByteArrayOutputStream에게 쓴다.
			while((readcount = fis.read(buffer)) != -1){
				baos.write(buffer, 0, readcount);	
			}
			
			
			// ByteArrayOutputStream의 내부 저장공간에 저장된 바이트 배열을 반환한다.
			byte[] fileArray = baos.toByteArray();
			System.out.println("파일의 내용을 모두 읽어들여 byte[]로 만들었습니다.");
			System.out.println("읽어들인 byte의 수 :" + fileArray.length);
			
			// byte[]로 부터 읽어들이는 ByteArrayInputStrem을 생성한다.
			bais = new ByteArrayInputStream(fileArray);
			// ByteArrayInputStream을 통하여 읽어들인 byte배열을 표준 출력 장치(모니터)
			// 에 출력한다.
			while((readcount = bais.read(buffer)) != -1){
				System.out.write(buffer, 0, readcount);	
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
