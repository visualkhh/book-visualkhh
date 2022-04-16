package visualkhh.design.patterns.visitor;

public class CartVisitor implements Visitor {
    @Override
    public int visit(BagElement bagElement) {
        System.out.println("가방 이름: "+ bagElement.getName() + "가격: "+ bagElement.getPrice());
        return bagElement.getPrice();
    }

    @Override
    public int visit(ShoesElement shoesElement) {
        int price = shoesElement.getPrice();

        if (shoesElement.getSize() > 270) {
            price += 2000;
        } else if (shoesElement.getSize() < 230) {
            price -= 5000;
        }

        System.out.println("신발 이름: "+ shoesElement.getName() + "사이즈: "+ shoesElement.getSize() + "가격: "+ price);
        return price;
    }
}