package visualkhh.design.patterns.visitor;

public class BagElement implements Element {
    private final int price;
    private final String name;

    public BagElement(int price, String name) {
        this.name = name;
        this.price = price;
    }

    public int getPrice() {
        return this.price;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public int accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
