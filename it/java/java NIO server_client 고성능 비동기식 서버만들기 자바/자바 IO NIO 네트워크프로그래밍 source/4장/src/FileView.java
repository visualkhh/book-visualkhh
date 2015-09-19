import java.io.*;

public class FileView {

	public static void main(String[] args) {
		if(args.length != 1){
			System.out.println("사용법 : java FileView 파일이름");
			System.exit(0);
		} // if end
				
		FileInputStream fis = null;
		try{
			fis = new FileInputStream(args[0]);
			int i = 0;
			while((i = fis.read()) != -1){
				System.out.write(i);
			}
		}catch(Exception ex){
			System.out.println(ex);
		}finally{
			try {
				fis.close();
			} catch (IOException e) {}
		}
	} // main
}
