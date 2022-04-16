package visualkhh.design.patterns.flyweight;


import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Circle {

    private String color;
    private String name;
    private int x;
    private int y;
    @Builder
    public Circle(String color, String name, int x, int y) {
        this.color = color;
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public void draw() {
        System.out.println("Circle.draw()");
    }
}
