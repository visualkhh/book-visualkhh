public class MyTimer {
	protected  static final String filePath = "C:/src.zip";
	
	private static long startTime;
	private static long endTime;
	// 시작 시간을 설정
	protected static void start() {
		startTime = System.currentTimeMillis();
	}
        // 종료 시간을 설정한 후 소비된 시간을 출력
	protected static void end(String name) {
		endTime = System.currentTimeMillis();
		System.out.println("[ " + name + " Time : " + (endTime - startTime) + " ] ");
	}	
}
