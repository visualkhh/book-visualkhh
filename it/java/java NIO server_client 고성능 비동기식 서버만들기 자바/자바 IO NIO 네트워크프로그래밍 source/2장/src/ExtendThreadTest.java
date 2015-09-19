// Thread 클래스를 상속. 
public class ExtendThread extends Thread { 
        // run() 를 오버라이딩하여 재정의. 
        public void run() { 
                System.out.println("Thread 클래스를 상속"); 
        } 
} 

public class ExtendThreadTest { 
        public static void main(String[] args) { 
                ExtendThread et = new ExtendThread(); 
                // start() 를 이용하여 스레드를 시작 시킨다. 
                // 이후 ExtendThread 의 run() 가 실행되고 run() 가 종료되면 바로 ExtendThread 가 소멸된다. 
                et.start(); 
        } 
} 
