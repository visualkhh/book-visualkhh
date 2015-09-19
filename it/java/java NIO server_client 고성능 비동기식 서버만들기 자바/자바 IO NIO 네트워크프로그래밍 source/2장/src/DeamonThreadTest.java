public class DeamonThreadTest { 
         
        public static void main(String[] args) { 
                // 스레드 생성 
                Thread t = new Thread() { 
                        public void run() { 
                                try { 
                                        // 5초간 멈춤 
                                        Thread.sleep(5000); 
                                        // 스레드 종료 메세지 
                                        System.out.println("MyThread 종료"); 
                                } catch (Exception e) { 
                                        // 무시.. 
                                } 
                        } 
                } 
                // 데몬 스레드로 설정.. 
                t.setDaemon(true); 
                // 스레드 시작 
                t.start(); 
                 
                // main 메소드 종료 메세지 
                System.out.println("main() 종료"); 
        } 

} 
