import java.io.*;

public class DataInputStreamTest {

	public static void main(String[] args) {
		FileInputStream fis = null;
		DataInputStream dis = null;
		try{
			fis = new FileInputStream("data.bin");
			dis = new DataInputStream(fis);
			boolean b = dis.readBoolean();
			byte b2 = dis.readByte();
			int i = dis.readInt();
			double d = dis.readDouble();
			String s = dis.readUTF();
			System.out.println("boolean :" + b);
			System.out.println("byte :" + b2);
			System.out.println("int :" + i);
			System.out.println("double :" + d);
			System.out.println("String :" + s);
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
