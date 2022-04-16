package visualkhh.design.patterns.mediator;

public class SystemSignal implements ISource{
    Mediator mediator;

    @Override
    public void setMediator(Mediator mediator) { // 중재자 설정
        this.mediator = mediator;
    }

    @Override
    public void eventOccured(String event) { // 이벤트의 전달
        mediator.onEvent("System", event);
    }

}
