import java.util.Map;
import java.util.Stack;

public class Parser {
    private static final String ADD = "+";
    private static final String SUB = "-";
    private static final String DIV = "/";
    private static final String MUL = "*";
    private static final String L_PAR = "(";
    private static final String R_PAR = ")";

    private static final Map<String, Integer> prior = Map.of(
            ADD, 0, SUB, 0, MUL, 1, DIV, 1, L_PAR, -1);

    public static ArithmeticExpression parse(String inputString) {
        Stack<String> Operations = new Stack<>();
        Stack<ArithmeticExpression> Expressions = new Stack<>();
        ArithmeticExpression result;

        for (var elem : inputString.toCharArray()) {
            if (elem == ' ') {
                continue;
            }
            if (elem == L_PAR.charAt(0)) {
                Operations.push(L_PAR);
            } else if (elem == R_PAR.charAt(0)) {
                ArithmeticExpression leftExpr = Expressions.pop();
                ArithmeticExpression rightExpr = Expressions.pop();
                var op = Operations.pop();
                BinaryOperation newBin = new BinaryOperation(rightExpr, leftExpr, op);
                Parenthesis newExpr = new Parenthesis(newBin);
                Operations.pop();
                Expressions.push(newExpr);
            } else if (prior.containsKey(String.valueOf(elem)) && !String.valueOf(elem).equals(L_PAR)) {
                while (!Operations.empty()) {
                    if (prior.get(Operations.peek()) >= prior.get(String.valueOf(elem))) {
                        ArithmeticExpression leftExpr = Expressions.pop();
                        ArithmeticExpression rightExpr = Expressions.pop();
                        String op = Operations.pop();
                        Expressions.push(new BinaryOperation(rightExpr, leftExpr, op));
                    } else
                        break;
                }
                Operations.push(String.valueOf(elem));
            } else if (Character.isDigit(elem)) {
                Expressions.push(new Literal(String.valueOf(elem)));
            } else if (Character.isLetter(elem)) {
                Expressions.push(new Variable(String.valueOf(elem)));
            }


        }
        while (!Operations.empty()) {
            ArithmeticExpression leftExpr = Expressions.pop();
            ArithmeticExpression rightExpr = Expressions.pop();
            String op = Operations.pop();
            Expressions.push(new BinaryOperation(rightExpr, leftExpr, op));
        }
        result = Expressions.pop();
        return result;
    }

}