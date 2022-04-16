package visualkhh.design.patterns.bridge.subjects;


import visualkhh.design.patterns.bridge.draws.Drawing;

public class Rectangle extends Shape {
    public Rectangle(Drawing drawing) {
        super(drawing);
    }

    @Override
    public void draw() {
        System.out.println("Rect draw extend");
    }
}
