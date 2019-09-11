import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class for the FibonacciCounter class.
 */
public class FibonacciCounterTest {
  private FibonacciCounter f1;


  @Before
  public void setUp() {
    f1 = new FibonacciCounter(5);
  }

  /**
   * Test that the constructor creates objects correctly when the correct parameters are passed to
   * it.
   */
  @Test
  public void testConstructor() {
    FibonacciCounter f2 = new FibonacciCounter(2);
    assertEquals(2, f2.getCount());
    assertEquals(1, f2.getValue());
  }

  /**
   * Test the constructor when the count value is negative, an exception should be thrown.
   */
  @Test
  public void testIllegalCount() {
    try {
      FibonacciCounter f3 = new FibonacciCounter(-1);
      fail("Exception should have been thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("The count is negative.", e.getMessage());
    }
  }

  /**
   * Test for constructor when the fibonacci number is too big to be a single integer. Since
   * Fibonacci(47) = 2971215073, which is greater than Integer.MAX_SIZE = 2147483647, an exception
   * should be thrown.
   */
  @Test
  public void testOverflow() {
    try {
      FibonacciCounter f3 = new FibonacciCounter(47);
      fail("Exception should have been thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("The fibonacci number is too big for a single integer.",
              e.getMessage());
    }
  }


  @Test
  public void testCount() {
    assertEquals(5, f1.getCount());
  }

  @Test
  public void testValue() {
    assertEquals(5, f1.getValue());
  }

  /**
   * Test method countIncrease when the increased value can be held in a single integer.
   */
  @Test
  public void testIncrease() {
    FibonacciCounter result1 = f1.countIncrease();
    assertEquals(6, result1.getCount());
    assertEquals(8, result1.getValue());
  }

  /**
   * Test method countIncrease when the increased value can't be held in a single integer.
   */
  @Test
  public void testIncreaseOverflow() {
    FibonacciCounter f2 = new FibonacciCounter(46);
    try {
      FibonacciCounter result = f2.countIncrease();
      fail("Exception should have been thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("The fibonacci number is too big for a single integer."
              , e.getMessage());
    }
  }

  /**
   * Test method countDecrease when the count is greater than 0.
   */
  @Test
  public void testDecrease() {
    FibonacciCounter result1 = f1.countDecrease();
    assertEquals(4, result1.getCount());
    assertEquals(3, result1.getValue());
  }

  /**
   * Test method countDecrease when the count is 0.
   */
  @Test
  public void testDecreaseZero() {
    FibonacciCounter f3 = new FibonacciCounter(0);
    FibonacciCounter result3 = f3.countDecrease();
    assertEquals(0, result3.getCount());
    assertEquals(0, result3.getValue());
  }

}