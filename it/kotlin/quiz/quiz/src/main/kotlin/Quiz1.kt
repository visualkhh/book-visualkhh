/*
다음 요구사항을 만족하는 페어 프로그래밍 진행
기능 요구사항
각 자동차에 이름을 부여할 수 있다. 자동차 이름은 5자를 초과할 수 없다.
전진하는 자동차를 출력할 때 자동차 이름을 같이 출력한다.
자동차 이름은 쉼표(,)를 기준으로 구분한다.
자동차 경주 게임을 완료한 후 누가 우승했는지를 알려준다. 우승자는 한명 이상일 수 있다.

실행 결과
위 요구사항에 따라 3대의 자동차가 5번 움직였을 경우 프로그램을 실행한 결과는 다음과 같다.

경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).
pobi,crong,honux
시도할 회수는 몇회인가요?
입력: 5

실행 결과
pobi : -
crong : -
honux : -

pobi : --
crong : -
honux : --

pobi : ---
crong : --
honux : ---

pobi : ----
crong : ---
honux : ----

pobi : -----
crong : ----
honux : -----

pobi : -----
crong : ----
honux : -----

pobi, honux가 최종 우승했습니다.

 */

class Car (
    var name: String,
    val step: Int,
    val races: MutableList<Char> = mutableListOf<Char>()
){
    fun go() {
        // random number 0~9
        val r = (0 until 10).random()
        if (r > 4) {
            races.add('-');
        }
    }
    override fun toString() = "#$name, $step, $races";
}
fun main(args: Array<String>) {
    var carNames = "pobi,crong,honux";
    var step = "5";
    var carNamesList = carNames.split(",");
    var carList = mutableListOf<Car>();
    for (carName in carNamesList) {
        carList.add(Car(carName, step.toInt()));
    }
    // for 5 times
    for (i in 1..step.toInt()) {
        for (car in carList) {
            car.go();
            println("""${car.name}: ${car.races.joinToString("")}""")
        }
        println()
    }

    val map = mutableMapOf<Int, MutableList<String>>();
    var max = Int.MIN_VALUE;
    for (car in carList) {
        var carName = car.name;
        val reaceSize = car.races.size
        var cars = map.get(reaceSize);
        cars = cars ?: mutableListOf<String>();
        cars.add(carName)
        map.set(reaceSize, cars);
        max = Math.max(max, car.races.size);
    }

    val winners = map.get(max);
    println(winners);
}