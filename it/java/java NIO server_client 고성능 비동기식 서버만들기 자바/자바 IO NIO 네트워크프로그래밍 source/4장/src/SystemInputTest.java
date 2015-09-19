import java.io.*;

public class SystemInputTest {

	public static void main(String[] args) {
		int i = 0;
		
		try {
			while((i = System.in.read()) != -1){
				System.out.write(i);
			} // while
		} catch (IOException e) {
			System.out.println(e);
		}
	} // main
}
