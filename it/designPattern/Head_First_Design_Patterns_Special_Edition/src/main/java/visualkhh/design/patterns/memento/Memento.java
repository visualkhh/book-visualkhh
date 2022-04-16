package visualkhh.design.patterns.memento;

import java.util.ArrayList;
import java.util.List;

// https://always-intern.tistory.com/6?category=1036393
public class Memento {
    public static void main(String[] args) {
        List<Life.Memento> savedTimes = new ArrayList<>();
        Life life = new Life();

        life.setTime("1000 B.C.");
        savedTimes.add(life.saveToMemento());
        life.setTime("1000 A.D.");
        savedTimes.add(life.saveToMemento());
        life.setTime("2000 A.D.");
        savedTimes.add(life.saveToMemento());
        life.setTime("4000 A.D.");
        savedTimes.add(life.saveToMemento());

        life.restoreFromMemento(savedTimes.get(0));
    }
}
