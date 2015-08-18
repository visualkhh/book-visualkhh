import java.util.ArrayList;
import java.util.List;

public class 스트림 {

	public 스트림() {
	}

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(3);
		list.add(4);
		list.add(1);
		
		Object[] a = list.stream().filter((Integer i)-> i>1).toArray();
		for (Object object : a) {
			System.out.println((Integer)object);
		}
	}

}
