public class ThreadJoinTest { 
         
        public static void main(String[] args) { 
                // 스레드 생성 
                Thread t = new Thread() { 
                        public void run() { 
                                try { 
                                        // 2초간 멈춤 
                                        Thread.sleep(2000); 
                                        // 스레드 종료 메세지 
                                        System.out.println("MyThread 종료"); 
                                        // 3초간 멈춤 
                                        Thread.sleep(3000); 
                                } catch (Exception e) { 
                                        // 무시.. 
                                } 
                        } 
                } 
                // 스레드 시작 
                t.start(); 
                try { 
                        // join() 메소드 실행.. 
                        // t 스레드가 종료될때까지 main 스레드가 기다림. 
                        // join() 메소드가 InterruptedException 을 발생시키는것에 주의. 
                        t.join(); 
                } catch (InterruptedException e) { 
                        e.printStackTrace(); 
                } 
                 
                // main 메소드 종료 메세지 
                System.out.println("main() 종료"); 
        } 
} 
