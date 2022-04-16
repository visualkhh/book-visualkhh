package visualkhh.design.patterns.responsibility_chain;

import visualkhh.design.patterns.responsibility_chain.airport.Person;
import visualkhh.design.patterns.responsibility_chain.airport.chains.AirPort;
import visualkhh.design.patterns.responsibility_chain.airport.chains.InspectionProcessor;
import visualkhh.design.patterns.responsibility_chain.airport.chains.PassportProcessor;
import visualkhh.design.patterns.responsibility_chain.airport.chains.TicketProcessor;

import java.util.List;

// https://always-intern.tistory.com/1
public class ResponsibilityChain {
    public static void main(String[] args) {
        AirPort  airPort = new AirPort();
        TicketProcessor ticketProcessor = new TicketProcessor();
        PassportProcessor passportProcessor = new PassportProcessor();
        InspectionProcessor inspectionProcessor = new InspectionProcessor();

        airPort.setNext(ticketProcessor);
        ticketProcessor.setNext(passportProcessor);
        passportProcessor.setNext(inspectionProcessor);


        airPort.process(new Person("이름", 2, "개발자", "티켓", "여권", List.of("notebook", "knife", "water")));


    }
}
