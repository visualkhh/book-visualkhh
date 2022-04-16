package visualkhh.design.patterns.responsibility_chain.airport;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor @Getter @Setter
public class Person {
    private String name;
    private int age;
    private String job;
    private String ticket;
    private String passport;
    private List<String> items;

    @Builder
    public Person(String name, int age, String job, String ticket, String passport, List<String> items) {
        this.name = name;
        this.age = age;
        this.job = job;
        this.ticket = ticket;
        this.passport = passport;
        this.items = items;
    }
}
