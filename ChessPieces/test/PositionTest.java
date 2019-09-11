import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * A JUnit test class for Position.
 */
public class PositionTest {
  private Position case1;

  @Before
  public void setUp() {
    case1 = new Position(5, 3);
  }

  /**
   * Test when the position is out of the board.
   */
  @Test
  public void testInvalid() {
    try {
      Position case2 = new Position(-1, 5);
      fail("An exception should have been thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("This position is out of range.", e.getMessage());
    }

    try {
      Position case2 = new Position(1, -5);
      fail("An exception should have been thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("This position is out of range.", e.getMessage());
    }

    try {
      Position case2 = new Position(10, 5);
      fail("An exception should have been thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("This position is out of range.", e.getMessage());
    }

    try {
      Position case2 = new Position(1, 9);
      fail("An exception should have been thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("This position is out of range.", e.getMessage());
    }
  }

  @Test
  public void testRow() {
    assertEquals(5, case1.getRow());
  }

  @Test
  public void testColumn() {
    assertEquals(3, case1.getColumn());
  }

  /**
   * Test for method isHorizontal.
   */
  @Test
  public void testHorizontal() {
    Position case2 = new Position(5, 7);
    assertEquals(true, case1.isHorizontal(case2));
    assertEquals(true, case2.isHorizontal(case1));

    Position case3 = new Position(5, 3);
    assertEquals(false, case1.isHorizontal(case3));

    Position case4 = new Position(3, 5);
    assertEquals(false, case1.isHorizontal(case4));
  }

  /**
   * Test for method isVertical.
   */
  @Test
  public void testVertical() {
    Position case2 = new Position(3, 3);
    assertEquals(true, case1.isVertical(case2));
    assertEquals(true, case2.isVertical(case1));

    Position case3 = new Position(5, 3);
    assertEquals(false, case1.isVertical(case3));

    Position case4 = new Position(3, 5);
    assertEquals(false, case1.isVertical(case4));
  }

  /**
   * Test for method isDiagonal.
   */
  @Test
  public void testDiagonal() {
    Position case2 = new Position(4, 2);
    assertEquals(true, case1.isDiagonal(case2));
    assertEquals(true, case2.isDiagonal(case1));

    Position case3 = new Position(7, 5);
    assertEquals(true, case1.isDiagonal(case3));
    assertEquals(true, case3.isDiagonal(case1));

    Position case4 = new Position(3, 5);
    assertEquals(true, case1.isDiagonal(case4));
    assertEquals(true, case4.isDiagonal(case1));

    Position case5 = new Position(6, 2);
    assertEquals(true, case1.isDiagonal(case5));
    assertEquals(true, case5.isDiagonal(case1));


    Position case6 = new Position(5, 3);
    assertEquals(false, case1.isDiagonal(case6));

    Position case7 = new Position(5, 6);
    assertEquals(false, case1.isDiagonal(case7));
    Position case8 = new Position(7, 3);
    assertEquals(false, case1.isDiagonal(case8));
  }

  /**
   * Test for method isAhead.
   */
  @Test
  public void testAhead() {
    Position case2 = new Position(6, 3);
    assertEquals(true, case1.isAhead(case2));
    assertEquals(false, case2.isAhead(case1));

    Position case3 = new Position(7, 3);
    assertEquals(false, case1.isAhead(case3));
    Position case4 = new Position(5, 3);
    assertEquals(false, case1.isAhead(case4));
    Position case5 = new Position(5, 6);
    assertEquals(false, case1.isAhead(case5));
    Position case6 = new Position(6, 4);
    assertEquals(false, case1.isAhead(case6));
  }

  /**
   * Test for method isBackward.
   */
  @Test
  public void testBackward() {
    Position case2 = new Position(4, 3);
    assertEquals(true, case1.isBackward(case2));
    assertEquals(false, case2.isBackward(case1));

    Position case3 = new Position(3, 3);
    assertEquals(false, case1.isBackward(case3));
    Position case4 = new Position(5, 3);
    assertEquals(false, case1.isBackward(case4));
    Position case5 = new Position(5, 4);
    assertEquals(false, case1.isBackward(case5));
    Position case6 = new Position(3, 1);
    assertEquals(false, case1.isBackward(case6));
  }

  /**
   * Test for method isLPattern.
   */
  @Test
  public void testLPattern() {
    Position case2 = new Position(6, 1);
    assertEquals(true, case1.isLPattern(case2));
    assertEquals(true, case2.isLPattern(case1));
    Position case3 = new Position(6, 5);
    assertEquals(true, case1.isLPattern(case3));
    assertEquals(true, case3.isLPattern(case1));
    Position case4 = new Position(4, 1);
    assertEquals(true, case1.isLPattern(case4));
    assertEquals(true, case4.isLPattern(case1));
    Position case5 = new Position(4, 5);
    assertEquals(true, case1.isLPattern(case5));
    assertEquals(true, case5.isLPattern(case1));

    Position case6 = new Position(3, 2);
    assertEquals(true, case1.isLPattern(case6));
    assertEquals(true, case6.isLPattern(case1));
    Position case7 = new Position(3, 4);
    assertEquals(true, case1.isLPattern(case7));
    assertEquals(true, case7.isLPattern(case1));
    Position case8 = new Position(7, 2);
    assertEquals(true, case1.isLPattern(case8));
    assertEquals(true, case8.isLPattern(case1));
    Position case9 = new Position(7, 4);
    assertEquals(true, case1.isLPattern(case9));
    assertEquals(true, case9.isLPattern(case1));


    Position case10 = new Position(1, 6);
    assertEquals(false, case1.isLPattern(case10));
    Position case11 = new Position(5, 3);
    assertEquals(false, case1.isLPattern(case11));
    Position case12 = new Position(5, 2);
    assertEquals(false, case1.isLPattern(case12));

  }
}