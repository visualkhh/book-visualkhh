package visualkhh.design.patterns.interpreter.expression;

public class TerminalExpression implements Expression {
    String data;

    public TerminalExpression(String data) {
        this.data = data;
    }

    @Override
    public boolean interpreter(String con) {

        if(con.contains(data)) return true;
        else return false;
    }
}
