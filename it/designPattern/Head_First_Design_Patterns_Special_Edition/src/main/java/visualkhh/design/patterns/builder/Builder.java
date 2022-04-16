package visualkhh.design.patterns.builder;

public class Builder {
    // https://www.crocus.co.kr/1644?category=337544
    public static void main(String[] args) {
        // new Computer("i9-9700k","WD HDD", 1024,null,null,false);
        // new Computer("i9-9700k",null, null,"T5",2048,null);
        // 위와같이 많은 파라미터가 있을경우 헛갈린다.  그렇다고 getter setter를 해서 사용하면  객체가 생성될때  한번에 설정지 않으니 아래 빌더패턴을 사용하면 된다.
        Computer computer = new Computer.builder("i7", "HDD", 1024)
                .ssd("T7", 1024)
                .monitor("27 inch")
                .bluetoothDongle(true)
                .build();
    }
}
