package visualkhh.design.patterns.bridge.draws;

public class CircleDrawing implements Drawing {
    @Override
    public void drawLine(int x, int y) {
        System.out.println("Circle Draw line from " + x + " to " + y);
    }

    @Override
    public void fill() {
        System.out.println("Circle Fill!");
    }

    @Override
    public void delete() {
        System.out.println("Circle delete!");
    }
}
