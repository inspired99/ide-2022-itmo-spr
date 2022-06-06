import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class testParser {

    @Test
    public void testSimpleAdd() {
        String input_1 = "1+(2+a)";
        String expected = " BinaryOp{ Literal 1 +  Brackets( BinaryOp{ Literal 2 +  Variable a} ) } ";
        assertEquals(Parser.parse(input_1).evaluate(),
                expected);
        String input_2 = "1+2+(a+c)";
        String expected_2 = " BinaryOp{ BinaryOp{ Literal 1 +  Literal 2}  +  " +
                "Brackets( BinaryOp{ Variable a +  Variable c} ) } ";
        assertEquals(Parser.parse(input_2).evaluate(), expected_2);

    }

    @Test
    public void testSimpleMul() {
        String input_1 = "1*2*3";
        String expected_1 = " BinaryOp{ BinaryOp{ Literal 1 *  Literal 2}  *  Literal 3} ";
        assertEquals(Parser.parse(input_1).evaluate(), expected_1);
    }

    @Test
    public void testSimpleDiv() {
        String input_1 = "b/2/4";
        String expected_1 = " BinaryOp{ BinaryOp{ Variable b /  Literal 2}  /  Literal 4} ";
        assertEquals(Parser.parse(input_1).evaluate(), expected_1);
    }


    @Test
    public void testSimpleSub() {
        String input_1 = "1-2-0-5";
        String expected_1 = " BinaryOp{ BinaryOp{ BinaryOp{ Literal 1 -  Literal 2}  -  Literal 0}  -  Literal 5} ";
        assertEquals(Parser.parse(input_1).evaluate(), expected_1);
    }

    @Test
    public void testComplexExpressions() {
        String input_1 = "1+2+(a+(2*5+3))";
        String expected_1 = " BinaryOp{ BinaryOp{ Literal 1 +  Literal 2}  +  Brackets( BinaryOp{ Variable a +  " +
                "Brackets( BinaryOp{ BinaryOp{ Literal 2 *  Literal 5}  +  Literal 3} ) } ) } ";
        assertEquals(Parser.parse(input_1).evaluate(), expected_1);
        String input_2 = "a+(b+c+(d-c-(c-d)))*6/1";
        String expected_2 = " BinaryOp{ Variable a +  BinaryOp{ BinaryOp{ Brackets( BinaryOp{ BinaryOp{ Variable b + " +
                " Variable c}  +  Brackets( BinaryOp{ BinaryOp{ Variable d -  Variable c}  -  Brackets( " +
                "BinaryOp{ Variable c -  Variable d} ) } ) } )  *  Literal 6}  /  Literal 1} } ";
        assertEquals(Parser.parse(input_2).evaluate(), expected_2);
    }
}