package visualkhh.design.patterns.flyweight;

import java.util.HashMap;
import java.util.Map;

public class ShapeFactoryManger {
    private Map<String, Circle> instances;

    public ShapeFactoryManger() {
        this.instances = new HashMap<>();
    }

    public Circle getCircle(String color) {
        Circle circle = instances.get(color);
        if (circle == null) {
            System.out.println(String.format("%s 새롭게 생성됨", color));
            circle = Circle.builder().color(color).build();
            instances.put(color, circle);
        } else {
            System.out.println(String.format("%s 새롭게 생성됨", color));
        }
        return circle;
    }
}
