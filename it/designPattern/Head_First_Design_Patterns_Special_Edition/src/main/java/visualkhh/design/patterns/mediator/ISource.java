package visualkhh.design.patterns.mediator;

interface ISource {
    public void setMediator(Mediator mediator);

    public void eventOccured(String event);
}

