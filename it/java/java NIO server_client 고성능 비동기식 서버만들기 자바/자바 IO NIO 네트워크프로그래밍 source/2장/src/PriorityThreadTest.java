class PriorityThread implements Runnable {

	public void run() {
		try { 
			// isInterrupted() 메소드를 while 문 조건으로 사용. 만약 이 스레드에 interrupt() 메소드를 호출하면 
			//isInterrupted() 메소드는 true 를 리턴해서 while 문을 빠져나가게 된다. 
			while (!Thread.currentThread().isInterrupted()) { 
				// PriorityThread 의 우선순위를 출력.
				System.out.println("Priority : " + Thread.currentThread().getPriority()); 
				// 0.5초간 멈춤. 
				Thread.sleep(500); 
			} 
		} catch (InterruptedException e) { 
			// 예상했던 예외이므로 무시.. 
		}
	}
}

public class PriorityThreadTest {

	public static void main(String args[]) throws InterruptedException {
		System.out.println("Start Main..");
		System.out.println("Thread.MAX_PRIORITY : " + Thread.MAX_PRIORITY);
		System.out.println("Thread.MIN_PRIORITY : " + Thread.MIN_PRIORITY);
		System.out.println("Thread.NORM_PRIORITY : " + Thread.NORM_PRIORITY);

		// 스레드를 생성하고 시작 시킴.
		// 기본적으로 Thread.NORM_PRIORITY, 즉 5의 우선순위 값을 갖게됨.
		Thread t = new Thread(new PriorityThread());
		t.start();
		Thread.sleep(500);
		
		// 우선순위를 Thread.MIN_PRIORITY, 즉 1로 바꿈.
		t.setPriority(Thread.MIN_PRIORITY);
		Thread.sleep(500);
		
		// 우선순위를 8로 바꿈.
		t.setPriority(8);
		Thread.sleep(500);
		
		// 우선순위를 Thread.MAX_PRIORITY, 즉 10으로 바꿈.
		t.setPriority(Thread.MAX_PRIORITY);
		Thread.sleep(500);
		
		// 스레드를 종료시킴.
		t.interrupt();

		System.out.println("End Main..");
	}
}
