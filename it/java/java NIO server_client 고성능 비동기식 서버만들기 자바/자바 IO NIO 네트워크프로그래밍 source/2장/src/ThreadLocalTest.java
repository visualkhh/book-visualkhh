import java.util.Random;

public class ThreadLocalTest {
	
	// 카운터 변수 생성.
	static volatile int counter = 0;
	// 랜덤 클래스 생성.
	static Random random = new Random();

	// ThreadLocal 을 상속한 ThreadLocalObject 클래스 생성.
	private static class ThreadLocalObject extends ThreadLocal {
		Random random = new Random();
		// 초기값으로 0~999 사이의 랜덤값을 설정.
		// initialValue() 메소드의 접근자가 protected 임에 주의.
		// return 값이 Object 임에 주의.
		protected Object initialValue() {
			return new Integer(random.nextInt(1000));
		}
	}
	// ThreadLocal 의 변수 생성.
	static ThreadLocal threadLocal = new ThreadLocalObject();

	// 각 스레드의 value 출력 메소드.
	private static void displayValues() {
		System.out.println("Thread Name:"
				+ Thread.currentThread().getName()
				+ ", initialValue:"
				+ threadLocal.get()
				+ ", counter:"
				+ counter);
	}

	public static void main(String args[]) {
		// main 스레드 value 출력.
		displayValues();
		
		Runnable runner = new Runnable() {
			public void run() {
				synchronized (ThreadLocalTest.class) {
					// 카운터를 1 증가시킴.
					counter++;
				}
				// value 출력.
				displayValues();
				try {
					// 스레드로컬의 초기값만큼 멈춤.
					Thread.sleep(((Integer) threadLocal.get()).intValue());
					// value 출력.
					displayValues();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		// 스레드 3개를 생성해서 각 스레드 value 를 출력.
		for (int i = 0; i < 3; i++) {
			Thread t = new Thread(runner);
			t.start();
		}
	}
}
