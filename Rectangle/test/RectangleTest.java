import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * A JUnit test class for Rectangle class.
 */
public class RectangleTest {
  private Rectangle rec1;

  @Before
  public void setUp() {
    rec1 = new Rectangle(0, 0, 4, 3);
  }

  /**
   * Test constructor when width is negative.
   */
  @Test
  public void testIllegalWidth() {
    try {
      Rectangle rec2 = new Rectangle(0, 0, -2, 1);
      fail("Exception should have been thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("Width or height is non-positive.", e.getMessage());
    }
  }

  /**
   * Test constructor when width is 0.
   */
  @Test
  public void testIllegalWidthZero() {
    try {
      Rectangle rec2 = new Rectangle(0, 0, 0, 1);
      fail("Exception should have been thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("Width or height is non-positive.", e.getMessage());
    }
  }

  /**
   * Test constructor when height is negative.
   */
  @Test
  public void testIllegalHeight() {
    try {
      Rectangle rec2 = new Rectangle(0, 0, 2, -1);
      fail("Exception should have been thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("Width or height is non-positive.", e.getMessage());
    }
  }

  /**
   * Test constructor when height is 0.
   */
  @Test
  public void testIllegalHeightZero() {
    try {
      Rectangle rec2 = new Rectangle(0, 0, 2, 0);
      fail("Exception should have been thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("Width or height is non-positive.", e.getMessage());
    }
  }

  /**
   * Test constructor when both width and height are non-positive.
   */
  @Test
  public void testIllegal() {
    try {
      Rectangle rec2 = new Rectangle(0, 0, -1, 0);
      fail("Exception should have been thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("Width or height is non-positive.", e.getMessage());
    }
  }

  /**
   * Test method overlap when the tow rectangles are the same.
   */
  @Test
  public void testOverlapSame() {
    Rectangle rec3 = new Rectangle(0, 0, 4, 3);
    assertEquals(true, rec1.overlap(rec3));
    assertEquals(true, rec3.overlap(rec1));
  }

  /**
   * Test method overlap when the rectangle totally covers the another.
   */
  @Test
  public void testOverlapOuter() {
    Rectangle rec3 = new Rectangle(1, 1, 2, 1);
    assertEquals(true, rec1.overlap(rec3));
    assertEquals(true, rec3.overlap(rec1));
  }

  /**
   * Test method overlap when the rectangle is totally covered by another.
   */
  @Test
  public void testOverlapInner() {
    Rectangle rec3 = new Rectangle(-1, -1, 6, 5);
    assertEquals(true, rec1.overlap(rec3));
    assertEquals(true, rec3.overlap(rec1));
  }

  /**
   * Test method overlap when the rectangle partly overlaps with another in the lower left corner.
   */
  @Test
  public void testOverlapLowerLeft() {
    Rectangle rec3 = new Rectangle(-2, -1, 4, 3);
    assertEquals(true, rec1.overlap(rec3));
    assertEquals(true, rec3.overlap(rec1));
  }

  /**
   * Test method overlap when the rectangle partly overlaps with another in the lower right corner.
   */
  @Test
  public void testOverlapLowerRight() {
    Rectangle rec3 = new Rectangle(3, -1, 5, 2);
    assertEquals(true, rec1.overlap(rec3));
    assertEquals(true, rec3.overlap(rec1));
  }

  /**
   * Test method overlap when the rectangle partly overlaps with another in the upper left corner.
   */
  @Test
  public void testOverlapUpperLeft() {
    Rectangle rec3 = new Rectangle(-2, 2, 4, 3);
    assertEquals(true, rec1.overlap(rec3));
    assertEquals(true, rec3.overlap(rec1));
  }

  /**
   * Test method overlap when the rectangle partly overlaps with another in the upper right corner.
   */
  @Test
  public void testOverlapUpperRight() {
    Rectangle rec3 = new Rectangle(3, 2, 5, 4);
    assertEquals(true, rec1.overlap(rec3));
    assertEquals(true, rec3.overlap(rec1));
  }

  /**
   * Test method overlap when the rectangle partly overlaps with another on the left.
   */
  @Test
  public void testOverlapLeft() {
    Rectangle rec3 = new Rectangle(-2, -1, 5, 5);
    assertEquals(true, rec1.overlap(rec3));
    assertEquals(true, rec3.overlap(rec1));
  }

  /**
   * Test method overlap when the rectangle partly overlaps with another on the right.
   */
  @Test
  public void testOverlapRight() {
    Rectangle rec3 = new Rectangle(3, 2, 5, 5);
    assertEquals(true, rec1.overlap(rec3));
    assertEquals(true, rec3.overlap(rec1));
  }

  /**
   * Test method overlap when the rectangle partly overlaps with another on the upper side.
   */
  @Test
  public void testOverlapUp() {
    Rectangle rec3 = new Rectangle(1, 2, 2, 2);
    assertEquals(true, rec1.overlap(rec3));
    assertEquals(true, rec3.overlap(rec1));
  }

  /**
   * Test method overlap when the rectangle partly overlaps with another on the lower side.
   */
  @Test
  public void testOverlapDown() {
    Rectangle rec3 = new Rectangle(1, -1, 2, 2);
    assertEquals(true, rec1.overlap(rec3));
    assertEquals(true, rec3.overlap(rec1));

  }

  /**
   * Test method when the attribute x of a rectangle is out of upper bound so that it can't overlap
   * with the existed one.
   */
  @Test
  public void testOverlapUpperBoundX() {
    Rectangle rec3 = new Rectangle(5, -1, 5, 2);
    assertEquals(false, rec1.overlap(rec3));
    assertEquals(false, rec3.overlap(rec1));
  }

  /**
   * Test method when the attribute x of the rectangle is out of lower bound so that it can't
   * overlap with existed one.
   */
  @Test
  public void testOverlapLowerBoundX() {
    Rectangle rec3 = new Rectangle(-5, -1, 5, 2);
    assertEquals(false, rec1.overlap(rec3));
    assertEquals(false, rec3.overlap(rec1));
  }

  /**
   * Test method when the attribute y of a rectangle is ut of upper bound so that it can't overlap
   * with the existed one.
   */
  @Test
  public void testOverlapUpperBoundY() {
    Rectangle rec3 = new Rectangle(3, 4, 5, 2);
    assertEquals(false, rec1.overlap(rec3));
    assertEquals(false, rec3.overlap(rec1));
  }

  /**
   * Test method when the attribute y of a rectangle is out the lower bound so that it can't overlap
   * with the existed one.
   */
  @Test
  public void testOverlapLowerBoundY() {
    Rectangle rec3 = new Rectangle(3, -4, 5, 2);
    assertEquals(false, rec1.overlap(rec3));
    assertEquals(false, rec3.overlap(rec1));
  }

  /**
   * Test method when the attributes x and y of a rectangle are both out of bound so that it can't
   * overlap with the existed one.
   */
  @Test
  public void testOverlapOutOfBound() {
    Rectangle rec3 = new Rectangle(5, 5, 5, 2);
    assertEquals(false, rec1.overlap(rec3));
    assertEquals(false, rec3.overlap(rec1));
  }

  /**
   * Test method intersect when the two rectangles don't overlap with each other.
   */
  @Test
  public void testIntersectNone() {
    Rectangle rec4 = new Rectangle(5, 5, 5, 2);
    try {
      Rectangle result = rec1.intersect(rec4);
      fail("No intersection exists.");
    } catch (NoSuchElementException e) {
      assertEquals("No intersection exists.", e.getMessage());
    }
  }

  /**
   * Test method intersect when the rectangle partly overlaps with another in the lower left
   * corner.
   */
  @Test
  public void testIntersectLowerLeft() {
    Rectangle rec4 = new Rectangle(-2, -1, 4, 3);
    Rectangle result1 = rec1.intersect(rec4);
    Rectangle result2 = rec4.intersect(rec1);
    assertEquals("x:0, y:0, w:2, h:2", result1.toString());
    assertEquals("x:0, y:0, w:2, h:2", result2.toString());
  }

  /**
   * Test method intersect when the rectangle partly overlaps with another in the lower right
   * corner.
   */
  @Test
  public void testIntersectLowerRight() {
    Rectangle rec4 = new Rectangle(3, -1, 5, 2);
    Rectangle result1 = rec1.intersect(rec4);
    Rectangle result2 = rec4.intersect(rec1);
    assertEquals("x:3, y:0, w:1, h:1", result1.toString());
    assertEquals("x:3, y:0, w:1, h:1", result2.toString());
  }

  /**
   * Test method intersect when the rectangle partly overlaps with another in the upper left
   * corner.
   */
  @Test
  public void testIntersectUpperLeft() {
    Rectangle rec4 = new Rectangle(-2, 2, 4, 3);
    Rectangle result1 = rec1.intersect(rec4);
    Rectangle result2 = rec4.intersect(rec1);
    assertEquals("x:0, y:2, w:2, h:1", result1.toString());
    assertEquals("x:0, y:2, w:2, h:1", result1.toString());
  }

  /**
   * Test method intersect when the rectangle partly overlaps with another in the upper right
   * corner.
   */
  @Test
  public void testIntersectUpperRight() {
    Rectangle rec4 = new Rectangle(3, 2, 5, 4);
    Rectangle result1 = rec1.intersect(rec4);
    Rectangle result2 = rec4.intersect(rec1);
    assertEquals("x:3, y:2, w:1, h:1", result1.toString());
    assertEquals("x:3, y:2, w:1, h:1", result2.toString());
  }

  /**
   * Test method intersect when the rectangle partly overlaps with another in an upper/lower edge.
   */
  @Test
  public void testIntersectUpperEdge() {
    Rectangle rec4 = new Rectangle(1, 2, 2, 2);
    Rectangle result1 = rec1.intersect(rec4);
    Rectangle result2 = rec4.intersect(rec1);
    assertEquals("x:1, y:2, w:2, h:1", result1.toString());
    assertEquals("x:1, y:2, w:2, h:1", result2.toString());
  }

  /**
   * Test method intersect when the rectangle partly overlaps with another in an left/right edge.
   */
  @Test
  public void testIntersectLeftEdge() {
    Rectangle rec4 = new Rectangle(-2, -1, 5, 5);
    Rectangle result1 = rec1.intersect(rec4);
    Rectangle result2 = rec4.intersect(rec1);
    assertEquals("x:0, y:0, w:3, h:3", result1.toString());
    assertEquals("x:0, y:0, w:3, h:3", result2.toString());
  }

  /**
   * Test method intersect when the rectangle totally covers another.
   */
  @Test
  public void testIntersectCover() {
    Rectangle rec4 = new Rectangle(1, 1, 2, 1);
    Rectangle result1 = rec1.intersect(rec4);
    Rectangle result2 = rec4.intersect(rec1);
    assertEquals(rec4.toString(), result1.toString());
    assertEquals(rec4.toString(), result2.toString());
  }

  /**
   * Test method intersect when the rectangle is totally covered by another.
   */
  @Test
  public void testIntersectCovered() {
    Rectangle rec4 = new Rectangle(-1, -1, 6, 5);
    Rectangle result1 = rec1.intersect(rec4);
    Rectangle result2 = rec4.intersect(rec1);
    assertEquals(rec1.toString(), result1.toString());
    assertEquals(rec1.toString(), result2.toString());
  }

  /**
   * Test method union when the two rectangles don't overlap with each other.
   */
  @Test
  public void testUnionNone() {
    Rectangle rec5 = new Rectangle(5, 5, 5, 2);
    Rectangle result1 = rec1.union(rec5);
    Rectangle result2 = rec5.union(rec1);
    assertEquals("x:0, y:0, w:10, h:7", result1.toString());
    assertEquals("x:0, y:0, w:10, h:7", result2.toString());
  }

  /**
   * Test method union when the rectangle partly overlaps with another in lower left corner.
   */
  @Test
  public void testUnionLowerLeft() {
    Rectangle rec5 = new Rectangle(-2, -1, 4, 3);
    Rectangle result1 = rec1.union(rec5);
    Rectangle result2 = rec5.union(rec1);
    assertEquals("x:-2, y:-1, w:6, h:4", result1.toString());
    assertEquals("x:-2, y:-1, w:6, h:4", result2.toString());
  }

  /**
   * Test method union when the rectangle partly overlaps with another in lower right corner.
   */
  @Test
  public void testUnionLowerRight() {
    Rectangle rec5 = new Rectangle(3, -1, 5, 2);
    Rectangle result1 = rec1.union(rec5);
    Rectangle result2 = rec5.union(rec1);
    assertEquals("x:0, y:-1, w:8, h:4", result1.toString());
    assertEquals("x:0, y:-1, w:8, h:4", result2.toString());
  }

  /**
   * Test method union when the rectangle partly overlaps with another in upper left corner.
   */
  @Test
  public void testUnionUpperLeft() {
    Rectangle rec5 = new Rectangle(-2, 2, 4, 3);
    Rectangle result1 = rec1.union(rec5);
    Rectangle result2 = rec5.union(rec1);
    assertEquals("x:-2, y:0, w:6, h:5", result1.toString());
    assertEquals("x:-2, y:0, w:6, h:5", result2.toString());
  }

  /**
   * Test method union when the rectangle partly overlaps with another in upper right corner.
   */
  @Test
  public void testUnionUpperRight() {
    Rectangle rec5 = new Rectangle(3, 2, 5, 4);
    Rectangle result1 = rec1.union(rec5);
    Rectangle result2 = rec5.union(rec1);
    assertEquals("x:0, y:0, w:8, h:6", result1.toString());
    assertEquals("x:0, y:0, w:8, h:6", result2.toString());
  }

  /**
   * Test method union when the rectangle partly overlaps with another in upper/lower edge.
   */
  @Test
  public void testUnionUpperEdge() {
    Rectangle rec5 = new Rectangle(1, 2, 2, 2);
    Rectangle result1 = rec1.union(rec5);
    Rectangle result2 = rec5.union(rec1);
    assertEquals("x:0, y:0, w:4, h:4", result1.toString());
    assertEquals("x:0, y:0, w:4, h:4", result2.toString());
  }

  /**
   * Test method union when the rectangle partly overlaps with another in lef/right edge.
   */
  @Test
  public void testUnionLeftEdge() {
    Rectangle rec5 = new Rectangle(-2, -1, 5, 5);
    Rectangle result1 = rec1.union(rec5);
    Rectangle result2 = rec5.union(rec1);
    assertEquals("x:-2, y:-1, w:6, h:5", result1.toString());
    assertEquals("x:-2, y:-1, w:6, h:5", result2.toString());
  }

  /**
   * Test method union when the rectangle totally covers another.
   */
  @Test
  public void testUnionCovers() {
    Rectangle rec5 = new Rectangle(1, 1, 2, 1);
    Rectangle result1 = rec1.union(rec5);
    Rectangle result2 = rec5.union(rec1);
    assertEquals(rec1.toString(), result1.toString());
    assertEquals(rec1.toString(), result2.toString());
  }

  /**
   * Test method union when the rectangle is totally covered by another.
   */
  @Test
  public void testUnionCovered() {
    Rectangle rec5 = new Rectangle(-1, -1, 6, 5);
    Rectangle result1 = rec1.union(rec5);
    Rectangle result2 = rec5.union(rec1);
    assertEquals(rec5.toString(), result1.toString());
    assertEquals(rec5.toString(), result2.toString());
  }

  /**
   * Test method toString.
   */
  @Test
  public void testToString() {
    assertEquals("x:0, y:0, w:4, h:3", rec1.toString());
  }

}