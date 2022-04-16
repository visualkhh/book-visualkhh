package visualkhh.design.patterns.visitor;

public interface Element {
    int accept(Visitor visitor);
}