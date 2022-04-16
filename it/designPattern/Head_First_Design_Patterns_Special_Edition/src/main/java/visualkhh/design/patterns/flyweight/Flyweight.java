package visualkhh.design.patterns.flyweight;

// https://lee1535.tistory.com/106
public class Flyweight {
    public static void main(String[] args) {
        ShapeFactoryManger shapeFactoryManger = new ShapeFactoryManger();
        String[] items = {"red", "blue", "green", "yellow", "red", "blue", "green", "yellow", "red", "blue", "green", "yellow"};
        for (int i = 0; i < 100; i++) {
            int random = (int) (Math.random() * items.length);
            shapeFactoryManger.getCircle(items[random]);
        }

    }
}
