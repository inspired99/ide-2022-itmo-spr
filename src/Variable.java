public class Variable implements ArithmeticExpression {
    private final String var;

    public Variable(String var) {
        this.var = var;
    }

    public String getVar() {
        return var;
    }

    @Override
    public String evaluate() {
        return " Variable " + getVar();
    }
}