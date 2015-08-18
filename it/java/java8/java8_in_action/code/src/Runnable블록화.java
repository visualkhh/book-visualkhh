public class Runnable블록화 {

	public Runnable블록화() {
	}

	public static void main(String[] args) {
		Thread t = new Thread(()-> 
			{
				while(true)
				System.out.println("aaaa");
			}
		);
		t.start();
		
		
		
		
		Runnable r = ()->{
			while(true)
				System.out.println("rrr");
		};
		new Thread(r).start();
		
	}

}
