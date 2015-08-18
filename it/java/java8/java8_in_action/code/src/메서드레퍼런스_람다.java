import java.io.File;
import java.util.ArrayList;
import java.util.function.Predicate;


public class 메서드레퍼런스_람다 {

	public 메서드레퍼런스_람다() {
	}

	public static void main(String[] args) {
		File file = new File(".");
		File[] files = null;
		files = file.listFiles();
		
//		ArrayList<File>hiddenFiles = getHidden(files);
//		ArrayList<File>hiddenFiles = getFilter(files,File::isHidden);
		ArrayList<File>hiddenFiles = getFilter(files,(File a)->a.isHidden());
		
		for (File atfile : hiddenFiles) {
			System.out.println(atfile.getName());
		}
		
	}
	
	public static ArrayList<File> getFilter(File[] files, Predicate<File> p){
		ArrayList<File> result = new ArrayList<File>();
		for (File file : files) {
			if(p.test(file))
				result.add(file);
		}
		return result;
	}
	
	public static ArrayList<File> getHidden(File[] files){
		ArrayList<File> result = new ArrayList<File>();
		for (File file : files) {
			if(file.isHidden())
				result.add(file);
		}
		return result;
	}

}
