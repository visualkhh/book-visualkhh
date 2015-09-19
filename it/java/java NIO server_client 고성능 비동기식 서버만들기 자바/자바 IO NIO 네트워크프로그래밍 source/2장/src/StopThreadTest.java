public class StopThread implements Runnable { 
        // 조건문 빠져나가기 위해 사용할 플래그 변수. 
        private boolean stopped = false; 

        public void run() { 
                // stopped 플래그를 while 문 조건으로 사용. 
                while (!stopped) { 
                        System.out.println("Thread is alive.."); 
                        try { 
                                // 0.5초간 멈춤. 
                                // 이곳에서 sleep 메소드를 호출하는 이유는 while 같은 반복문을 잠시의 여유없이 수행하면 CPU에 많은 부담을 주기 때문이다. 
                                // 궁금한 독자들은 sleep 메소드 부분을 빼고 다시 컴파일 한 후에 CPU 사용률을 측정해보기 바란다. 확연히 차이가 날 것이다. 
                                Thread.sleep(500); 
                        } catch (InterruptedException e) { 
                                e.printStackTrace(); 
                        } 
                } 
                System.out.println("Thread is dead.."); 
        } 

        // 이 메소드 호출로 StopThread 를 멈춤. 
        public void stop() { 
                stopped = true; 
        } 
} 

public class StopThreadTest { 
        public static void main(String[] args) { 
                System.out.println("# Start StopThreadTest.java"); 
                StopThreadTest stt = new StopThreadTest(); 
                stt.process(); 
        } 

        public void process() { 
                // StopThread 인스턴스를 생성한 후 이 인자를 파라미터로 받는 
                // 스레드 인스턴스를 생성한 후에 출발시킴. 
                StopThread st = new StopThread(); 
                Thread thread = new Thread(st); 
                thread.start(); 

                try { 
                        // 1초간 멈춤. 
                        Thread.sleep(1000); 
                } catch (InterruptedException e) { 
                        e.printStackTrace(); 
                } 
                 
                // StopThread 를 정지시킴. 
                st.stop(); 
        } 
} 
