package visualkhh.design.patterns.bridge;


import visualkhh.design.patterns.bridge.draws.CircleDrawing;
import visualkhh.design.patterns.bridge.draws.RectDrawing;
import visualkhh.design.patterns.bridge.subjects.Circle;
import visualkhh.design.patterns.bridge.subjects.Rectangle;
import visualkhh.design.patterns.bridge.subjects.Shape;


// https://www.crocus.co.kr/1537
public class Bridge {
    public static void main(String[] args) {
        Shape rectangle = new Rectangle(new RectDrawing());
        rectangle.drawLine(1, 2);
        rectangle.fill();
        rectangle.draw();
        System.out.println();
        Shape circle = new Circle(new CircleDrawing());
        circle.drawLine(3, 4);
        circle.fill();
        circle.draw();

    }
}
