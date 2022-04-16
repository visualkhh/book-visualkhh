package visualkhh.design.patterns.interpreter;

import visualkhh.design.patterns.interpreter.expression.AndExpression;
import visualkhh.design.patterns.interpreter.expression.Expression;
import visualkhh.design.patterns.interpreter.expression.OrExpression;
import visualkhh.design.patterns.interpreter.expression.TerminalExpression;

// https://always-intern.tistory.com/11
public class Interpreter {
    public static void main(String[] args) {
        Expression person1 = new TerminalExpression("Kushagra");
        Expression person2 = new TerminalExpression("Lokesh");
        Expression isSingle = new OrExpression(person1, person2);

        Expression vikram = new TerminalExpression("Vikram");
        Expression committed = new TerminalExpression("Committed");
        Expression isCommitted = new AndExpression(vikram, committed);

        System.out.println(isSingle.interpreter("Kushagra"));
        System.out.println(isSingle.interpreter("Lokesh"));
        System.out.println(isSingle.interpreter("Achint"));

        System.out.println(isCommitted.interpreter("Committed, Vikram"));
        System.out.println(isCommitted.interpreter("Single, Vikram"));
    }
}
