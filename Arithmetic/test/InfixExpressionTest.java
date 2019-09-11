import org.junit.Test;

import static org.junit.Assert.*;

public class InfixExpressionTest {

  @Test
  public void nullConstructor() {
    try {
      InfixExpression nullCase = new InfixExpression(null);
      fail("An exception should be thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("The expression is not in a valid format.", e.getMessage());
    }
  }

  @Test
  public void emptyConstructor() {
    try {
      InfixExpression emptyCase = new InfixExpression("");
      fail("An exception should be thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("The expression is not in a valid format.", e.getMessage());
    }
  }

  @Test
  public void spacesConstructor() {
    try {
      InfixExpression spaceCase = new InfixExpression(" ");
      fail("An exception should be thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("The expression is not in a valid format.", e.getMessage());
    }
  }

  @Test
  public void constantConstructor() {
    InfixExpression constantCaseZero = new InfixExpression("a");
    assertEquals("a", constantCaseZero.toPostfix().toString());

    InfixExpression constantCase1 = new InfixExpression("5");
    assertEquals("5", constantCase1.toString());

    InfixExpression constantCaseExtraZero = new InfixExpression("05");
    assertEquals("05", constantCaseExtraZero.toString());

    InfixExpression constantCase2 = new InfixExpression("50");
    assertEquals("50", constantCase2.toString());
  }

  @Test
  public void negativeConstantConstructor() {
    try {
      InfixExpression negativeConstantCase = new InfixExpression("-6");
      fail("An exception should be thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("The input is invalid.", e.getMessage());
    }
  }

  @Test
  public void singleOperator() {
    InfixExpression addCase = new InfixExpression("1 + 2");
    assertEquals("1 + 2", addCase.toString());
    assertEquals("1 2 +", addCase.toPostfix().toString());
    assertEquals(3, addCase.evaluate(), 0.001);

    InfixExpression minusCase = new InfixExpression("4 - 2");
    assertEquals("4 - 2", minusCase.toString());
    assertEquals("4 2 -", minusCase.toPostfix().toString());
    assertEquals(2, minusCase.evaluate(), 0.001);

    InfixExpression multiplyCase = new InfixExpression("10 * 8");
    assertEquals("10 * 8", multiplyCase.toString());
    assertEquals("10 8 *", multiplyCase.toPostfix().toString());
    assertEquals(80, multiplyCase.evaluate(), 0.001);

    InfixExpression divisionCase = new InfixExpression("5 / 2");
    assertEquals("5 / 2", divisionCase.toString());
    assertEquals("5 2 /", divisionCase.toPostfix().toString());
    assertEquals(2.5, divisionCase.evaluate(), 0.001);

    InfixExpression divisionCase1 = new InfixExpression("5 / 0");
    assertEquals("5 / 0", divisionCase1.toString());
    assertEquals("5 0 /", divisionCase1.toPostfix().toString());
    assertTrue(Double.isInfinite(divisionCase1.evaluate()));

  }

  @Test
  public void multipleOperators2(){
    InfixExpression addAddCase = new InfixExpression("1 + 2 + 3");
    assertEquals("1 + 2 + 3", addAddCase.toString());
    assertEquals("1 2 + 3 +", addAddCase.toPostfix().toString());
    assertEquals(6, addAddCase.evaluate(), 0.001);

    InfixExpression minusMinusCase = new InfixExpression("3 - 2 - 1");
    assertEquals("3 - 2 - 1", minusMinusCase.toString());
    assertEquals("3 2 - 1 -", minusMinusCase.toPostfix().toString());
    assertEquals(0, minusMinusCase.evaluate(), 0.001);

    InfixExpression multiplyMultiplyCase = new InfixExpression("1 * 2 * 3");
    assertEquals("1 * 2 * 3", multiplyMultiplyCase.toString());
    assertEquals("1 2 * 3 *", multiplyMultiplyCase.toPostfix().toString());
    assertEquals(6, multiplyMultiplyCase.evaluate(), 0.001);

    InfixExpression divisionDivisionCase = new InfixExpression("12 / 3 / 2");
    assertEquals("12 / 3 / 2", divisionDivisionCase.toString());
    assertEquals("12 3 / 2 /", divisionDivisionCase.toPostfix().toString());
    assertEquals(2, divisionDivisionCase.evaluate(), 0.001);

//    InfixExpression addMinusCase = new InfixExpression("3 + ( 2 - 1 )");
//    assertEquals("3 + ( 2 - 1 )",addMinusCase.toString());
//    assertEquals("3 2 1 - +", addMinusCase.toPostfix().toString());
//    assertEquals(4, addMinusCase.evaluate(), 0.001);

    InfixExpression addMultiplyCase = new InfixExpression("3 + 2 * 5");
    assertEquals("3 + 2 * 5", addMultiplyCase.toString());
    assertEquals("3 2 5 * +", addMultiplyCase.toPostfix().toString());
    assertEquals(13,addMultiplyCase.evaluate(), 0.001);

    InfixExpression addDivisionCase = new InfixExpression("4 + 16 / 2");
    assertEquals("4 + 16 / 2", addDivisionCase.toString());
    assertEquals("4 16 2 / +", addDivisionCase.toPostfix().toString());
    assertEquals(12, addDivisionCase.evaluate(), 0.001);

    InfixExpression minusMultiplyCase = new InfixExpression("16 / 2 - 4");
    assertEquals("16 / 2 - 4", minusMultiplyCase.toString());
    assertEquals("16 2 / 4 -", minusMultiplyCase.toPostfix().toString());
    assertEquals(4, minusMultiplyCase.evaluate(), 0.001);

    InfixExpression minusDivisionCase = new InfixExpression("15 - 2 * 4");
    assertEquals("15 - 2 * 4", minusDivisionCase.toString());
    assertEquals("15 2 4 * -", minusDivisionCase.toPostfix().toString());
    assertEquals(7, minusDivisionCase.evaluate(), 0.001);

    InfixExpression multiplyDivisionCase = new InfixExpression("10 * 2 / 5");
    assertEquals("10 * 2 / 5", multiplyDivisionCase.toString());
    assertEquals("10 2 * 5 /", multiplyDivisionCase.toPostfix().toString());
    assertEquals(4, multiplyDivisionCase.evaluate(), 0.001);
  }

  @Test
  public void multipleOperators3(){
    InfixExpression case1 = new InfixExpression("12 / 4 - 6 * 2");
    assertEquals("12 / 4 - 6 * 2", case1.toString());
    assertEquals("12 4 / 6 2 * -", case1.toPostfix().toString());
    assertEquals(-9, case1.evaluate(), 0.001);

    InfixExpression case2 = new InfixExpression("10 + 15 * 2 / 3");
    assertEquals("10 + 15 * 2 / 3", case2.toString());
    assertEquals("10 15 2 * 3 / +", case2.toPostfix().toString());
    assertEquals(20, case2.evaluate(), 0.001);

    InfixExpression case3 = new InfixExpression("5 + 6 - 40 / 2");
    assertEquals("5 + 6 - 40 / 2", case3.toString());
    assertEquals("5 6 + 40 2 / -", case3.toPostfix().toString());
    assertEquals(-9, case3.evaluate(), 0.001);

    InfixExpression case4 = new InfixExpression("1 + 3 * 2 - 4");
    assertEquals("1 + 3 * 2 - 4", case4.toString());
    assertEquals("1 3 2 * + 4 -", case4.toPostfix().toString());
    assertEquals(3, case4.evaluate(), 0.001);
  }

  @Test
  public void multipleOperators4(){
    InfixExpression test = new InfixExpression("1 + 2 * 3 / 6 - 5");
    assertEquals("1 + 2 * 3 / 6 - 5", test.toString());
    assertEquals("1 2 3 * 6 / + 5 -", test.toPostfix().toString());
    assertEquals(-3, test.evaluate(), 0.001);
  }


  @Test
  public void singleOperatorWithParenthesis(){
//    InfixExpression addCase = new InfixExpression("( ) 1 + 2");
//    assertEquals("( ) 1 + 2", addCase.toString());
//    assertEquals("1 2 +", addCase.toPostfix().toString());
//    assertEquals(3, addCase.evaluate(), 0.001);

    InfixExpression minusCase = new InfixExpression("( 4 - 2 )");
    assertEquals("( 4 - 2 )", minusCase.toString());
    assertEquals("4 2 -", minusCase.toPostfix().toString());
    assertEquals(2, minusCase.evaluate(), 0.001);

//    InfixExpression multiplyCase = new InfixExpression("10 ( * 8 )");
//    assertEquals("10 ( * 8 )", multiplyCase.toString());
//    assertEquals("10 8 *", multiplyCase.toPostfix().toString());
//    assertEquals(80, multiplyCase.evaluate(), 0.001);

    InfixExpression divisionCase = new InfixExpression("5 / ( 2 )");
    assertEquals("5 / ( 2 )", divisionCase.toString());
    assertEquals("5 2 /", divisionCase.toPostfix().toString());
    assertEquals(2.5, divisionCase.evaluate(), 0.001);
  }

  @Test
  public void multipleOperators2WithParenthesis(){
    InfixExpression case1 = new InfixExpression("3 + ( 2 - 1 )");
    assertEquals("3 + ( 2 - 1 )",case1.toString());
    assertEquals("3 2 1 - +", case1.toPostfix().toString());
    assertEquals(4, case1.evaluate(), 0.001);

    InfixExpression case2 = new InfixExpression("( 3 + 2 ) * 5");
    assertEquals("( 3 + 2 ) * 5", case2.toString());
    assertEquals("3 2 + 5 *", case2.toPostfix().toString());
    assertEquals(25,case2.evaluate(), 0.001);

    InfixExpression minusDivisionCase = new InfixExpression("( 15 - 2 * 4 )");
    assertEquals("( 15 - 2 * 4 )", minusDivisionCase.toString());
    assertEquals("15 2 4 * -", minusDivisionCase.toPostfix().toString());
    assertEquals(7, minusDivisionCase.evaluate(), 0.001);

//    InfixExpression multiplyDivisionCase = new InfixExpression("( ) 10 * 2 / 5");
//    assertEquals("( ) 10 * 2 / 5", multiplyDivisionCase.toString());
//    assertEquals("10 2 * 5 /", multiplyDivisionCase.toPostfix().toString());
//    assertEquals(4, multiplyDivisionCase.evaluate(), 0.001);

  }

  @Test
  public void multipleOperators3WithParenthesis(){
    InfixExpression case1 = new InfixExpression("12 / ( ( 4 - 6 ) * 2 )");
    assertEquals("12 / ( ( 4 - 6 ) * 2 )", case1.toString());
    assertEquals("12 4 6 - 2 * /", case1.toPostfix().toString());
    assertEquals(-3, case1.evaluate(), 0.001);

    InfixExpression case2 = new InfixExpression("( 10 + 15 ) ( ) * 2 / 3");
    assertEquals("( 10 + 15 ) ( ) * 2 / 3", case2.toString());
    assertEquals("10 15 + 2 * 3 /", case2.toPostfix().toString());
    assertEquals(16.666666, case2.evaluate(), 0.001);

    InfixExpression case3 = new InfixExpression("( ) 5 + 6 - 40 / 2");
    assertEquals("( ) 5 + 6 - 40 / 2", case3.toString());
    assertEquals("5 6 + 40 2 / -", case3.toPostfix().toString());
    assertEquals(-9, case3.evaluate(), 0.001);

    InfixExpression case4 = new InfixExpression("( 1 + ( 3 * 2 - 4 ) ) ( )");
    assertEquals("( 1 + ( 3 * 2 - 4 ) ) ( )", case4.toString());
    assertEquals("1 3 2 * 4 - +", case4.toPostfix().toString());
    assertEquals(3, case4.evaluate(), 0.001);
  }

  @Test
  public void multipleOperators4WithParenthesis(){
    InfixExpression test = new InfixExpression("( ( ) 1 ( + 2 ) ) * 3 / ( 6 - 5 )");
    assertEquals("( ( ) 1 ( + 2 ) ) * 3 / ( 6 - 5 )", test.toString());
    assertEquals("1 2 + 3 * 6 5 - /", test.toPostfix().toString());
    assertEquals(9, test.evaluate(), 0.001);
  }
  @Test
  public void InvalidWithUnbalancedParentheses(){
    try{
      InfixExpression case1 = new InfixExpression("1 + ( 2 * ( 3 - 4 )");
      fail("An exception should be thrown.");
    }
    catch(IllegalArgumentException e){
      assertEquals("The input is invalid.", e.getMessage());
    }
  }

  @Test
  public void validCantEvaluated(){
    InfixExpression case1 = new InfixExpression("a + 3 / b");
    assertEquals("a + 3 / b", case1.toString());
    assertEquals("a 3 b / +", case1.toPostfix().toString());
    try{
      case1.evaluate();
      fail("An exception should be thrown.");
    }
    catch(ArithmeticException e){
      assertEquals("cant be evaluated.", e.getMessage());
    }
  }

  @Test
  public void validCantEvaluatedWithParentheses(){
    InfixExpression case1 = new InfixExpression("( 3 - b ) * x");
    assertEquals("( 3 - b ) * x", case1.toString());
    assertEquals("3 b - x *", case1.toPostfix().toString());
    try{
      case1.evaluate();
      fail("An exception should have been thrown.");
    }
    catch(ArithmeticException e){
      assertEquals("cant be evaluated.", e.getMessage());
    }
  }



  @Test
  public void testToPostfix() {
    InfixExpression case1 = new InfixExpression("a + b * 3 + 4 ( )");
    assertEquals("a b 3 * + 4 +", case1.toPostfix().toString());
    assertEquals(20, case1.evaluate(), 0.001);

    InfixExpression case2 = new InfixExpression("( ( ( 1 + 5 ) ) * ( 3 + 4 ) )");
    assertEquals("1 5 + 3 4 + *", case2.toPostfix().toString());
    assertEquals(42, case2.evaluate(), 0.001);

    InfixExpression case3 = new InfixExpression("4 + ( 3 * 5 + 1 )");
    assertEquals("4 3 5 * 1 + +", case3.toPostfix().toString());
    assertEquals(20, case3.evaluate(), 0.001);

    InfixExpression case4 = new InfixExpression("4 + ( 3 * ( 5 + 1 ) )");
    assertEquals("4 3 5 1 + * +", case4.toPostfix().toString());
    assertEquals(22, case4.evaluate(), 0.001);

//    InfixExpression case5 = new InfixExpression("( )");
//    assertEquals("", case5.toPostfix().toString());
  }
}