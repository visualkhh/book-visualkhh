package visualkhh.design.patterns.responsibility_chain.airport.chains;

import visualkhh.design.patterns.responsibility_chain.airport.Person;

public interface Chain {
    public abstract void setNext(Chain nextInChain);
    public abstract void process(Person request);
}
