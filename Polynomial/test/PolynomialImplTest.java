

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class PolynomialImplTest {
  private PolynomialImpl case1;
  private PolynomialImpl case2;

  @Before
  public void setUp() {
    case1 = new PolynomialImpl();
    case2 = new PolynomialImpl();
  }


  @Test
  public void testEmptyList() {
    assertEquals("0", case1.toString());

    assertEquals(0, case1.getDegree());

    assertEquals(0, case1.getCoefficient(0));
    assertEquals(0, case1.getCoefficient(1));
    assertEquals(0, case1.getCoefficient(2));
    assertEquals(0, case1.getCoefficient(-1));

    assertEquals(0, case1.evaluate(4), 0.001);
  }

  @Test
  public void testEmptyRemove() {
    case1.removeTerm(2);
    assertEquals(0, case1.getCoefficient(2));
    assertEquals(0, case1.getDegree());
    assertEquals("0", case1.toString());
  }

  @Test
  public void testInvalidAdd() {
    try {
      case1.addTerm(0, -1);
      fail("An exception should have been thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("A power shouldn't be negative.", e.getMessage());
    }

    try {
      case1.addTerm(2, -1);
      fail("An exception should have been thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("A power shouldn't be negative.", e.getMessage());
    }
  }

  /**
   * Test method addTerm when the term's coefficient is 0. The term can't be added to the
   * polynomial.
   */
  @Test
  public void testZeroAdd() {
    case1.addTerm(0, 2);

    assertEquals(0, case1.getCoefficient(2));
    assertEquals(0, case1.getDegree());

    assertEquals("0", case1.toString());
  }

  /**
   * Test for methods addTerm, toString, getDegree, getCoefficient, evaluate, add and remove.
   */
  @Test
  public void testAdd() {

    case1.addTerm(0, 0);
    assertEquals("0", case1.toString());
    assertEquals(0, case1.getDegree());


    case1.addTerm(1, 1);
    assertEquals("1x^1", case1.toString());
    assertEquals(1, case1.getDegree());

    //Add a term with different power.
    case1.addTerm(2, 3);
    assertEquals("2x^3 +1x^1", case1.toString());
    assertEquals(3, case1.getDegree());

    // Add a term with the same power.
    case1.addTerm(1, 3);
    assertEquals("3x^3 +1x^1", case1.toString());
    assertEquals(3, case1.getDegree());

    // Add a constant number.
    case1.addTerm(9, 0);
    assertEquals("3x^3 +1x^1 +9", case1.toString());
    assertEquals(3, case1.getDegree());

    // Add a term with negative coefficient in the middle of the polynomial.
    case1.addTerm(-2, 2);
    assertEquals("3x^3 -2x^2 +1x^1 +9", case1.toString());
    assertEquals(3, case1.getDegree());

    // Add a term with coefficient 0.
    case1.addTerm(0, 8);
    assertEquals("3x^3 -2x^2 +1x^1 +9", case1.toString());
    assertEquals(3, case1.getDegree());

    // Add a term with negative coefficient at the front.
    case1.addTerm(-4, 4);
    assertEquals("-4x^4 +3x^3 -2x^2 +1x^1 +9", case1.toString());
    assertEquals(4, case1.getDegree());

    case1.addTerm(-6, 3);
    assertEquals("-4x^4 -3x^3 -2x^2 +1x^1 +9", case1.toString());
    assertEquals(4, case1.getDegree());

    case1.addTerm(-5, 5);
    assertEquals("-5x^5 -4x^4 -3x^3 -2x^2 +1x^1 +9", case1.toString());
    assertEquals(5, case1.getDegree());

    case1.addTerm(5, 5);
    assertEquals("-4x^4 -3x^3 -2x^2 +1x^1 +9", case1.toString());
    assertEquals(4, case1.getDegree());

    //Test getCoefficient
    assertEquals(0, case1.getCoefficient(5));
    assertEquals(-4, case1.getCoefficient(4));
    assertEquals(-3, case1.getCoefficient(3));
    assertEquals(-2, case1.getCoefficient(2));
    assertEquals(1, case1.getCoefficient(1));
    assertEquals(9, case1.getCoefficient(0));
    // Test getCoefficient when there is no such a term.
    assertEquals(0, case1.getCoefficient(-2));
    // Test evaluate.
    assertEquals(1, case1.evaluate(1), 0.01);
    assertEquals(5, case1.evaluate(-1), 0.01);
    assertEquals(9, case1.evaluate(0), 0.01);


    case2.addTerm(1, 1);
    assertEquals("1x^1", case2.toString());
    case2.addTerm(2, 3);
    assertEquals("2x^3 +1x^1", case2.toString());
    case2.addTerm(1, 3);
    assertEquals("3x^3 +1x^1", case2.toString());
    case2.addTerm(9, 0);
    assertEquals("3x^3 +1x^1 +9", case2.toString());
    case2.addTerm(-2, 2);
    assertEquals("3x^3 -2x^2 +1x^1 +9", case2.toString());
    case2.addTerm(0, 8);
    assertEquals("3x^3 -2x^2 +1x^1 +9", case2.toString());
    case2.addTerm(-4, 4);
    assertEquals("-4x^4 +3x^3 -2x^2 +1x^1 +9", case2.toString());
    case2.addTerm(5, 5);
    assertEquals("5x^5 -4x^4 +3x^3 -2x^2 +1x^1 +9", case2.toString());

    assertEquals(5, case2.getDegree());

    // Test add two polynomials with different degrees.
    assertEquals("5x^5 -8x^4 -4x^2 +2x^1 +18", case1.add(case2).toString());
    assertEquals("-4x^4 -3x^3 -2x^2 +1x^1 +9", case1.toString());
    assertEquals("5x^5 -4x^4 +3x^3 -2x^2 +1x^1 +9", case2.toString());

    assertEquals("5x^5 -8x^4 -4x^2 +2x^1 +18", case2.add(case1).toString());
    assertEquals("-4x^4 -3x^3 -2x^2 +1x^1 +9", case1.toString());
    assertEquals("5x^5 -4x^4 +3x^3 -2x^2 +1x^1 +9", case2.toString());

    case2.removeTerm(0);
    assertEquals("5x^5 -4x^4 +3x^3 -2x^2 +1x^1", case2.toString());
    assertEquals(5, case2.getDegree());
    assertEquals(0, case2.getCoefficient(0));

    case2.removeTerm(5);
    assertEquals("-4x^4 +3x^3 -2x^2 +1x^1", case2.toString());
    assertEquals(4, case2.getDegree());
    assertEquals(0, case2.getCoefficient(5));

    // Test add two polynomials with the same degrees.
    assertEquals("-8x^4 -4x^2 +2x^1 +9", case1.add(case2).toString());
    assertEquals("-4x^4 -3x^3 -2x^2 +1x^1 +9", case1.toString());
    assertEquals("-4x^4 +3x^3 -2x^2 +1x^1", case2.toString());

    assertEquals("-8x^4 -4x^2 +2x^1 +9", case2.add(case1).toString());
    assertEquals("-4x^4 -3x^3 -2x^2 +1x^1 +9", case1.toString());
    assertEquals("-4x^4 +3x^3 -2x^2 +1x^1", case2.toString());

    // Test remove when there is no such a term.
    case2.removeTerm(6);
    assertEquals("-4x^4 +3x^3 -2x^2 +1x^1", case2.toString());
    assertEquals(4, case2.getDegree());
    assertEquals(0, case2.getCoefficient(5));

    case2.removeTerm(2);
    assertEquals("-4x^4 +3x^3 +1x^1", case2.toString());
    assertEquals(4, case2.getDegree());
    assertEquals(0, case2.getCoefficient(2));

    case2.removeTerm(4);
    assertEquals("3x^3 +1x^1", case2.toString());
    assertEquals(3, case2.getDegree());
    assertEquals(0, case2.getCoefficient(4));
  }


  @Test
  public void testConstructor() {
    Polynomial case3 = new PolynomialImpl("4x^3 +3x^1 -5");
    assertEquals("4x^3 +3x^1 -5", case3.toString());
    assertEquals(3, case3.getDegree());

    Polynomial case4 = new PolynomialImpl("-3x^4 -2x^5 -5 +11x^1");
    assertEquals("-2x^5 -3x^4 +11x^1 -5", case4.toString());
    assertEquals(5, case4.getDegree());


    Polynomial case5 = new PolynomialImpl("-3x^4 -2x^5 -5 +0x^1");
    assertEquals("-2x^5 -3x^4 -5", case5.toString());
    assertEquals(5, case5.getDegree());


    Polynomial case6 = new PolynomialImpl("0");
    assertEquals("0", case6.toString());
    assertEquals(0, case6.getDegree());

    Polynomial case7 = new PolynomialImpl("7");
    assertEquals("7", case7.toString());
    assertEquals(0, case6.getDegree());

  }

  @Test
  public void testInvalidConstructor() {
    try {
      Polynomial case3 = new PolynomialImpl("3abc");
      fail("An exception should have been thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("The input polynomial is invalid.", e.getMessage());
    }
  }
}