import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@FunctionalInterface
interface BuffreadReaderProcessor{
	String process(BufferedReader b) throws IOException;
}


public class 람다전달 {

	public 람다전달() {
	}
	
	public static String processFile(BuffreadReaderProcessor p) throws IOException{
		try(BufferedReader br = new BufferedReader(new FileReader("vv.txt"))){
			return p.process(br);
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		String str = processFile((BufferedReader br)->br.readLine());
		System.out.println(str);
	}

}
