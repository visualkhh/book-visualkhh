import java.util.ArrayList;
import java.util.Comparator;

public class 정렬 {

	public 정렬() {
	}

	public static void main(String[] args) {
		ArrayList<Integer> ilist = new ArrayList<Integer>();
		ilist.add(4);
		ilist.add(1);
		ilist.add(3);
		ilist.add(2);

		ilist.sort(new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return o1.compareTo(o2);
			}
		});

		ilist.sort((Integer a, Integer b) -> a.compareTo(b));

		for (Integer integer : ilist) {
			System.out.println(integer);
		}
	}

}
