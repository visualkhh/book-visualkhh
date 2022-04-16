package visualkhh.design.patterns.visitor;
// https://velog.io/@newtownboy/%EB%94%94%EC%9E%90%EC%9D%B8%ED%8C%A8%ED%84%B4-%EB%B0%A9%EB%AC%B8%EC%9E%90%ED%8C%A8%ED%84%B4Visitor-Pattern
// https://brownbears.tistory.com/575
public class VisitorMain {
    public static void main(String[] args) {

        Element[] elements = new Element[]{
                new BagElement(40000, "토트백"),
                new BagElement(10000, "백팩"),
                new ShoesElement(50000, "나이키", 210),
                new ShoesElement(100000, "아디다스", 290),
                new ShoesElement(156000, "리복", 255)
        };

        Visitor visitor = new CartVisitor();
        int totalPrice = 0;
        for (Element element: elements) {
            totalPrice += element.accept(visitor);
        }
    }
}
