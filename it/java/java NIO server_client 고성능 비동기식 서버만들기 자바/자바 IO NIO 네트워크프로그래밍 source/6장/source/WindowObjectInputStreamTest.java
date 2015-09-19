import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class WindowObjectInputStreamTest {

	public static void main(String[] args) {
		FileInputStream fin = null;
		ObjectInputStream ois = null;

		try{
			fin = new FileInputStream("hwin.dat");
			ois = new ObjectInputStream(fin);
			
			HelloWindow hwin = (HelloWindow)ois.readObject();
			hwin.setVisible(true);
		}catch(Exception ex){
		}finally{
			try{
				ois.close();
				fin.close();
			}catch(IOException ioe){}
		} // finally
	} // main end
}
