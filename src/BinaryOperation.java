public class BinaryOperation implements ArithmeticExpression{
    private final ArithmeticExpression leftInnerExpr;
    private final ArithmeticExpression rightInnerExpr;
    private final String op;

    public BinaryOperation(ArithmeticExpression leftInnerExpr, ArithmeticExpression rightInnerExpr, String op) {
        this.leftInnerExpr = leftInnerExpr;
        this.rightInnerExpr = rightInnerExpr;
        this.op = op;
    }

    @Override
    public String evaluate() {
        String resLeft = leftInnerExpr.evaluate();
        String resRight = rightInnerExpr.evaluate();
        return " BinaryOp{" + resLeft + " " + op + " " + resRight + "} ";
    }
}