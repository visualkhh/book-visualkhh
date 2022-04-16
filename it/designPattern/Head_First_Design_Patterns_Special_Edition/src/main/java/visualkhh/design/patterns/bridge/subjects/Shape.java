package visualkhh.design.patterns.bridge.subjects;


import visualkhh.design.patterns.bridge.draws.Drawing;

public abstract class Shape {
    private Drawing drawing;

    protected Shape(Drawing drawing) {
        this.drawing = drawing;
    }

    public abstract void draw();

    public void drawLine(int x, int y) {
        drawing.drawLine(x, y);
    }

    public void fill() {
        drawing.fill();
    }
}
