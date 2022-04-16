package visualkhh.design.patterns.responsibility_chain.airport.chains;

import visualkhh.design.patterns.responsibility_chain.airport.Person;

public class AirPort implements Chain{
    private Chain nextInChain;

    @Override
    public void setNext(Chain nextInChain) {
        this.nextInChain = nextInChain;
    }

    @Override
    public void process(Person request) {
        this.nextInChain.process(request);
    }
}
