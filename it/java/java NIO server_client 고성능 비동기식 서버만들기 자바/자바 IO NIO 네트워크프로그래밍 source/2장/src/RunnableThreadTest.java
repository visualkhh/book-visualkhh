// Runnable 인터페이스를 구현 
public class RunnableThread implements Runnable { 
      // run() 를 오버라이딩하여 재정의.  
public void run() { 
                System.out.println("Runnable 인터페이스를 구현"); 
        } 
} 

public class RunnableThreadTest { 
        public static void main(String[] args) { 
                // Thread 생성자에 RunnableThread 인스턴스를 파라미터로 전달. 
                Thread t = new Thread(new RunnableThread()); 
                t.start(); 
        } 
} 
