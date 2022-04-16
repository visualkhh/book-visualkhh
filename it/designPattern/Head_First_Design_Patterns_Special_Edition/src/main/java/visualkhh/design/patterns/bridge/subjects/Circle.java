package visualkhh.design.patterns.bridge.subjects;


import visualkhh.design.patterns.bridge.draws.Drawing;

public class Circle extends Shape {
    public Circle(Drawing drawing) {
        super(drawing);
    }

    @Override
    public void draw() {
        System.out.println("Rect draw extend");
    }
}
