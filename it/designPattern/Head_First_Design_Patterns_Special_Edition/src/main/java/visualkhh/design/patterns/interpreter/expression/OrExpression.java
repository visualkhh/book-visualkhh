package visualkhh.design.patterns.interpreter.expression;

public class OrExpression implements Expression {
    Expression expr1;
    Expression expr2;

    public OrExpression(Expression expr1, Expression expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    public boolean interpreter(String con) {
        return expr1.interpreter(con) || expr2.interpreter(con);
    }
}