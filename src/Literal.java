public class Literal implements ArithmeticExpression {
    private final String value;

    public Literal(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String evaluate() {
        return " Literal " + getValue();
    }

}