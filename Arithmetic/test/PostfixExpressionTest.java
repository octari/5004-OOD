import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class PostfixExpressionTest {

  @Test
  public void nullConstructor() {
    try {
      PostfixExpression nullCase = new PostfixExpression(null);
      fail("An exception should be thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("The expression is not in a valid format.", e.getMessage());
    }
  }

  @Test
  public void emptyConstructor() {
    try {
      PostfixExpression emptyCase = new PostfixExpression("");
      fail("An exception should be thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("The expression is not in a valid format.", e.getMessage());
    }
  }

  @Test
  public void spacesConstructor() {
    try {
      PostfixExpression spaceCase = new PostfixExpression(" ");
      fail("An exception should be thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("The expression is not in a valid format.", e.getMessage());
    }
  }

  @Test
  public void leadingSpace() {
    try {
      PostfixExpression testCase1 = new PostfixExpression(" 1  5  3 *  +    4 +");
      fail("An exception should be thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("The expression is not in a valid format.", e.getMessage());
    }
  }

  @Test
  public void tailSpace() {
    try {
      PostfixExpression testCase1 = new PostfixExpression("1  5  3 *  +    4 + ");
      fail("An exception should be thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("The expression is not in a valid format.", e.getMessage());
    }
  }

  @Test
  public void noSpace() {
    try {
      PostfixExpression testcase = new PostfixExpression("1 3+ 5 -");
      fail("An exception should be thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("The expression is not in a valid format.", e.getMessage());
    }
  }

  @Test
  public void constantConstructor() {
    PostfixExpression constantCaseZero = new PostfixExpression("0");
    assertEquals("0", constantCaseZero.toString());

    PostfixExpression constantCase1 = new PostfixExpression("5");
    assertEquals("5", constantCase1.toString());

    PostfixExpression constantCaseExtraZero = new PostfixExpression("05");
    assertEquals("05", constantCaseExtraZero.toString());

    PostfixExpression constantCase2 = new PostfixExpression("50");
    assertEquals("50", constantCase2.toString());
  }

  @Test
  public void negativeConstantConstructor() {
    try {
      PostfixExpression negativeConstantCase = new PostfixExpression("-6");
      fail("An exception should be thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("The expression is not in a valid format.", e.getMessage());
    }
  }

  @Test
  public void singleOperatorCase() {

    PostfixExpression addCase = new PostfixExpression("2 3 +");
    assertEquals("2 3 +", addCase.toString());
    assertEquals(5, addCase.evaluate(), 0.001);

    PostfixExpression minusCase = new PostfixExpression("5 4 -");
    assertEquals("5 4 -", minusCase.toString());
    assertEquals(1, minusCase.evaluate(), 0.001);

    PostfixExpression multiplyCase = new PostfixExpression("10 9 *");
    assertEquals("10 9 *", multiplyCase.toString());
    assertEquals(90, multiplyCase.evaluate(), 0.001);

    PostfixExpression divisionCase = new PostfixExpression("9 0 /");
    assertEquals("9 0 /", divisionCase.toString());
    assertTrue(Double.isInfinite(divisionCase.evaluate()));
  }

  @Test
  public void multipleOperators2Case() {

    PostfixExpression addMultiplyCase = new PostfixExpression("2 3 + 5 *");
    assertEquals("2 3 + 5 *", addMultiplyCase.toString());
    assertEquals(25, addMultiplyCase.evaluate(), 0.001);

    PostfixExpression addMinusCase = new PostfixExpression("1 2 3 - +");
    assertEquals("1 2 3 - +", addMinusCase.toString());
    assertEquals(0, addMinusCase.evaluate(), 0.001);

    PostfixExpression addAddCase = new PostfixExpression("1 2 + 3 +");
    assertEquals("1 2 + 3 +", addAddCase.toString());
    assertEquals(6, addAddCase.evaluate(), 0.001);

    PostfixExpression addDivisionCase = new PostfixExpression("4 1 3 + /");
    assertEquals("4 1 3 + /", addDivisionCase.toString());
    assertEquals(1, addDivisionCase.evaluate(), 0.001);

    PostfixExpression minusMultiplyCase = new PostfixExpression("1 3 5 * -");
    assertEquals("1 3 5 * -", minusMultiplyCase.toString());
    assertEquals(-14, minusMultiplyCase.evaluate(), 0.001);

    PostfixExpression minusDivisionCase = new PostfixExpression("7 1 - 3 /");
    assertEquals("7 1 - 3 /", minusDivisionCase.toString());
    assertEquals(2, minusDivisionCase.evaluate(), 0.001);

    PostfixExpression minusMinusCase = new PostfixExpression("3 2 1 - -");
    assertEquals("3 2 1 - -", minusMinusCase.toString());
    assertEquals(2, minusMinusCase.evaluate(), 0.001);

    PostfixExpression multiplyDivisionCase = new PostfixExpression("3 4 * 6 /");
    assertEquals("3 4 * 6 /", multiplyDivisionCase.toString());
    assertEquals(2, minusDivisionCase.evaluate(), 0.001);

    PostfixExpression multiplyMultiplyCase = new PostfixExpression("3 4 5 * *");
    assertEquals("3 4 5 * *", multiplyMultiplyCase.toString());
    assertEquals(60, multiplyMultiplyCase.evaluate(), 0.001);

    PostfixExpression divisionDivisionCase = new PostfixExpression("42 7 / 3 /");
    assertEquals("42 7 / 3 /", divisionDivisionCase.toString());
    assertEquals(2, divisionDivisionCase.evaluate(), 0.001);
  }


  @Test
  public void multipleOperators3Case() {
    PostfixExpression case1 = new PostfixExpression("1 5 - 3 4 + *");
    assertEquals("1 5 - 3 4 + *", case1.toString());
    assertEquals(-28, case1.evaluate(), 0.001);

    PostfixExpression case2 = new PostfixExpression("12 2 / 8 + 2 -");
    assertEquals("12 2 / 8 + 2 -", case2.toString());
    assertEquals(12, case2.evaluate(), 0.001);

    PostfixExpression case3 = new PostfixExpression("10 5 8 * - 6 /");
    assertEquals("10 5 8 * - 6 /", case3.toString());
    assertEquals(-5, case3.evaluate(), 0.001);

    PostfixExpression case4 = new PostfixExpression("3 5 * 14 2 / -");
    assertEquals("3 5 * 14 2 / -", case4.toString());
    assertEquals(8, case4.evaluate(), 0.001);
  }

  @Test
  public void multipleOperators4Case() {
    PostfixExpression testCase = new PostfixExpression("1 2 + 6 * 5 3 - /");
    assertEquals("1 2 + 6 * 5 3 - /", testCase.toString());
    assertEquals(9, testCase.evaluate(), 0.001);
  }

  @Test
  public void expressionWithSpaces() {
    PostfixExpression testCase = new PostfixExpression("1 5 3 * +      4 +");
    assertEquals("1 5 3 * + 4 +", testCase.toString());
    assertEquals(20, testCase.evaluate(), 0.001);

    PostfixExpression testCase1 = new PostfixExpression("1  5  3 *  +    4 +");
    assertEquals("1 5 3 * + 4 +", testCase1.toString());
    assertEquals(20, testCase1.evaluate(), 0.001);
  }

  @Test
  public void invalidOperatorExpression() {
    try {
      PostfixExpression extraOperator1 = new PostfixExpression("+ 1 5");
      fail("An exception should be thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("The expression is not in a valid format.", e.getMessage());
    }

    try {
      PostfixExpression extraOperator2 = new PostfixExpression("1 + 5");
      fail("An exception should be thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("The expression is not in a valid format.", e.getMessage());
    }

    try {
      PostfixExpression extraOperator3 = new PostfixExpression("1 5 * 3 - +");
      fail("An exception should be thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("The expression is not in a valid format.", e.getMessage());
    }
  }

  @Test
  public void invalidOperandExpression() {
    try {
      PostfixExpression extraOperand = new PostfixExpression("1 5 + 2");
      fail("An exception should be thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("The expression is not in a valid format.", e.getMessage());
    }

    try {
      PostfixExpression extraOperand = new PostfixExpression("1 5 + 2 2");
      fail("An exception should be thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("The expression is not in a valid format.", e.getMessage());
    }

    try {
      PostfixExpression extraOperand = new PostfixExpression("1 5 + 0");
      fail("An exception should be thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("The expression is not in a valid format.", e.getMessage());
    }

  }
  @Test
  public void validCantEvaluate(){
    PostfixExpression case1 = new PostfixExpression("a b + 3 *");
    try{
      case1.evaluate();
      fail("An exception should have been thrown.");
    }
    catch(ArithmeticException e){
      assertEquals("cant be evaluated.", e.getMessage());
    }
  }

  @Test
  public void invalidOperator(){
    try{
      PostfixExpression case1 = new PostfixExpression("1 5 =");
      fail("An exception should be thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("The expression is not in a valid format.", e.getMessage());
    }
  }

  @Test
  public void invalidOperand(){
    try{
      PostfixExpression case1 = new PostfixExpression("^ b +");
      case1.evaluate();
      fail("An exception should be thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("The expression is not in a valid format.", e.getMessage());
    }
  }
  @Test
  public void invalidMissingSpace(){
    try{
      PostfixExpression case1 = new PostfixExpression("1 2+");
      fail("An exception should have been thrown.");
    }
    catch (IllegalArgumentException e){
      assertEquals("The expression is not in a valid format.", e.getMessage());
    }
  }
}