package visualkhh.design.patterns.mediator;

import java.util.ArrayList;
import java.util.List;

// https://www.crocus.co.kr/1542
public class Mediator {
    List<IDestination> list = new ArrayList<IDestination>();

    public void addDestination(IDestination destination) {
        list.add(destination);
    }

    public void onEvent(String from, String event) {
        for (IDestination each : list) { // 이벤트의 전송
            each.receiveEvent(from, event);
        }


    }

    public static void main(String[] args) {
        Mediator mediator = new Mediator();
        ISource tcp = new TcpComm();
        tcp.setMediator(mediator);
        ISource system = new SystemSignal();
        system.setMediator(mediator);


        mediator.addDestination(new Display());
        mediator.addDestination(new Log());
        tcp.eventOccured("connected");
        system.eventOccured("key input");
//        tcp.eventOccured("disconnected");
//        system.eventOccured("mouse input");

    }
}

