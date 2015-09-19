import java.io.*;

public class DataOutputStreamTest {

	public static void main(String[] args) {
		FileOutputStream fis = null;
		DataOutputStream dis = null;
		try{
			fis = new FileOutputStream("data.bin");
			dis = new DataOutputStream(fis);
			dis.writeBoolean(true);
			dis.writeByte((byte)5);
			dis.writeInt(100);
			dis.writeDouble(200.5);
			dis.writeUTF("hello world");
			System.out.println("저장하였습니다.");
		}catch(Exception ex){
			System.out.println(ex);
		}finally{
			try {
				fis.close();
			} catch (IOException e) {}
			try {
				dis.close();
			} catch (IOException e) {}
		}
	}
}
