public class AdvancedStopThread implements Runnable { 

        public void run() { 
                try { 
                        // isInterrupted() 메소드를 while 문 조건으로 사용. 만약 이 스레드에 interrupt() 메소드를 호출하면 
//isInterrupted() 메소드는 true 를 리턴해서 while 문을 빠져나가게 된다. 
                        while (!Thread.currentThread().isInterrupted()) { 
                                System.out.println("Thread is alive.."); 
                                // 0.5초간 멈춤. 
                                Thread.sleep(500); 
                        } 
                } catch (InterruptedException e) { 
                        // 예상했던 예외이므로 무시.. 
                } finally { 
                        // 마무리 해야할 작업이 있다면 이곳에서 정리. 
                        System.out.println("Thread is dead.."); 
                } 
        } 
} 

public class AdvancedStopThreadTest { 
        public static void main(String[] args) { 
                System.out.println("# Start AdvancedStopThreadTest.java"); 
                AdvancedStopThreadTest astt = new AdvancedStopThreadTest(); 
                astt.process(); 
        } 

        public void process() { 
                // AdvancedStopThread 인스턴스를 생성한 후 이 인자를 파라미터로 받는 
                // 스레드 인스턴스를 생성한 후에 출발시킴. 
                AdvancedStopThread ast = new AdvancedStopThread(); 
                Thread thread = new Thread(ast); 
                thread.start(); 

                try { 
                        // 1초간 멈춤. 
                        Thread.sleep(1000); 
                } catch (InterruptedException e) { 
                        e.printStackTrace(); 
                } 
                 
                // AdvancedStopThread 를 정지시킴. 
                thread.interrupt(); 
        } 
} 
