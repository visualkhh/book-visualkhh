package visualkhh.design.patterns.visitor;

public class ShoesElement implements Element {
    private final int price;
    private final String name;
    private final int size;

    public ShoesElement(int price, String name, int size) {
        this.price = price;
        this.name = name;
        this.size = size;
    }

    public int getPrice() {
        return this.price;
    }

    public String getName() {
        return this.name;
    }

    public int getSize() {
        return this.size;
    }

    @Override
    public int accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
