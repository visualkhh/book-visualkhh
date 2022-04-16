package visualkhh.design.patterns.responsibility_chain.airport.chains;

import visualkhh.design.patterns.responsibility_chain.airport.Person;

public class InspectionProcessor implements Chain{
    private Chain nextInChain;

    @Override
    public void setNext(Chain nextInChain) {
        this.nextInChain = nextInChain;
    }

    @Override
    public void process(Person request) {
        if (request.getItems().contains("knife")) {
            System.out.println("불가합니다.");
        } else {
            System.out.println("환영합니다.");

        }
    }
}
