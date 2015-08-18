import java.io.File;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Predicate;


public class 함수형인터페이스_사용 {

	public 함수형인터페이스_사용() {
	}
	
	public static void main(String[] args) {
		File file = new File(".");
		File[] files = null;
		files = file.listFiles();
		ArrayList<File>hiddenFiles = getPredicateFilter(files,(File a)->a.isHidden());
		
		
		System.out.println("---Predicate---");
		for (File atfile : hiddenFiles) {
			System.out.println(atfile.getName());
		}
		
		System.out.println("---Consumer---");
		getConsumerFilter(files,(File a)->System.out.println(a.getName()));
		
	}
	
	
	public static ArrayList<File> getPredicateFilter(File[] files, Predicate<File> p){
		ArrayList<File> result = new ArrayList<File>();
		for (File file : files) {
			if(p.test(file))
				result.add(file);
		}
		return result;
	}
	
	public static void getConsumerFilter(File[] files, Consumer<File> p){
		for (File file : files) {
			p.accept(file);
		}
	}
	

	
}
