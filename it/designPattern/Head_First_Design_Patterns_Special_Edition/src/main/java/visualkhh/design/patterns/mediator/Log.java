package visualkhh.design.patterns.mediator;

class Log implements IDestination {
    @Override
    public void receiveEvent(String from, String event) {
        System.out.println("Log : from " + from + " event : " + event);
    }
}
