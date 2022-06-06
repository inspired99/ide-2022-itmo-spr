public class Parenthesis implements ArithmeticExpression {
    private final ArithmeticExpression innerExpr;

    public Parenthesis(ArithmeticExpression innerExpr) {
        this.innerExpr = innerExpr;
    }

    public ArithmeticExpression getInnerExpr() {
        return innerExpr;
    }

    @Override
    public String evaluate() {
        return " Brackets(" + innerExpr.evaluate() + ") ";
    }
}