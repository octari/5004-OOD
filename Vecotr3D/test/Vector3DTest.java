
import org.junit.Before;
import org.junit.Test;

import static java.lang.StrictMath.sqrt;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

/**
 * A Junit test class for the Vector3D class.
 */
public class Vector3DTest {
  private Vector3D vector1;
  private Vector3D vector2;
  private Vector3D vector3;

  @Before
  public void setUp() {
    vector1 = new Vector3D(1.111, 2.222, 3.335);
    vector2 = new Vector3D(0, 0, 0);
    vector3 = new Vector3D(-1, -2, -3);
  }

  /**
   * Test for method getX.
   */
  @Test
  public void testX() {
    assertEquals(1.111, vector1.getX(), 0.001);
  }

  /**
   * Test for method getY.
   */
  @Test
  public void testY() {
    assertEquals(2.222, vector1.getY(), 0.001);
  }

  /**
   * Test for method getZ.
   */
  @Test
  public void testZ() {
    assertEquals(3.335, vector1.getZ(), 0.001);
  }

  /**
   * Test for method toString when x, y, z has more than 2 decimal places.
   */
  @Test
  public void testToStringFloat() {
    assertEquals("(1.11,2.22,3.34)", vector1.toString());
  }

  /**
   * Test for method toString when x, y, z has less than 2 decimal places.
   */
  @Test
  public void testToStringInteger() {
    assertEquals("(-1.00,-2.00,-3.00)", vector3.toString());
  }

  /**
   * Test for method getMagnitude.
   */
  @Test
  public void testMagnitude() {
    assertEquals(sqrt(14), vector3.getMagnitude(), 0.001);
  }


  /**
   * Test for method normalize. When a vector's magnitude is 0, it can't be normalized, and then an
   * IllegalStatementException will be thrown.
   */
  @Test
  public void testNormalizeZero() {

    try {
      Vector3D result = vector2.normalize();
      fail("Exception should have been thrown");
    } catch (IllegalStateException e) {
      assertEquals("The operation can't be completed.", e.getMessage());
    }
  }

  /**
   * Test for method normalize, when a vector's magnitude is greater than 0.
   */
  @Test
  public void testNormalize() {
    assertEquals(-1 / sqrt(14), vector3.normalize().getX(), 0.001);
    assertEquals(-2 / sqrt(14), vector3.normalize().getY(), 0.001);
    assertEquals(-3 / sqrt(14), vector3.normalize().getZ(), 0.001);

  }

  /**
   * Test for method add. Test if the results of add are expected and the vectors are changed.
   */
  @Test
  public void testAdd() {
    Vector3D other = new Vector3D(1, 1, 1);

    assertEquals(0, vector3.add(other).getX(), 0.001);
    assertEquals(-1, vector3.add(other).getY(), 0.001);
    assertEquals(-2, vector3.add(other).getZ(), 0.001);
    assertEquals(-1, vector3.getX(), 0.001);
    assertEquals(-2, vector3.getY(), 0.001);
    assertEquals(-3, vector3.getZ(), 0.001);

    assertEquals(1, other.getX(), 0.001);
    assertEquals(1, other.getY(), 0.001);
    assertEquals(1, other.getZ(), 0.001);

  }


  /**
   * Test for method multiply. Test if the results of multiply are expected and the vectors are
   * changed.
   */
  @Test
  public void testMultiply() {

    Vector3D result3 = vector3.multiply(2);
    assertEquals(-2, result3.getX(), 0.001);
    assertEquals(-4, result3.getY(), 0.001);
    assertEquals(-6, result3.getZ(), 0.001);
    assertEquals(-1, vector3.getX(), 0.001);
    assertEquals(-2, vector3.getY(), 0.001);
    assertEquals(-3, vector3.getZ(), 0.001);

  }


  /**
   * Test for method dotProduct. Test if the results of dotProduct are expected and the vectors are
   * changed.
   */
  @Test
  public void testDotProduct() {
    Vector3D other = new Vector3D(3, 2, 1);

    assertEquals(-10, vector3.dotProduct(other), 0.001);
    assertEquals(-1, vector3.getX(), 0.001);
    assertEquals(-2, vector3.getY(), 0.001);
    assertEquals(-3, vector3.getZ(), 0.001);

    assertEquals(3, other.getX(), 0.001);
    assertEquals(2, other.getY(), 0.001);
    assertEquals(1, other.getZ(), 0.001);
  }

  /**
   * Test for method angleBetween，when the vectors' magnitude are not 0Test if the results of add
   * are expected and the vectors are changed.
   */
  @Test
  public void testAngle() {
    Vector3D other1 = new Vector3D(1, 2, 3);
    assertEquals(180, vector3.angleBetween(other1), 0.001);
    assertEquals(-1, vector3.getX(), 0.001);
    assertEquals(-2, vector3.getY(), 0.001);
    assertEquals(-3, vector3.getZ(), 0.001);
    assertEquals(1, other1.getX(), 0.001);
    assertEquals(2, other1.getY(), 0.001);
    assertEquals(3, other1.getZ(), 0.001);
  }

  /**
   * Test for method angleBetween. Test if the results of add are expected and the vectors are
   * changed. When at least one of the vectors' magnitude is 0, it can't be operated, and then an
   * IllegalStatementException will be thrown.
   */
  @Test
  public void testAngleZero() {
    Vector3D other2 = new Vector3D(1, 1, 1);
    try {
      double result = vector2.angleBetween(other2);
      fail("Exception should have been thrown");
    } catch (IllegalStateException e) {
      assertEquals("The operation can't be completed.", e.getMessage());
    }
    assertEquals(0, vector2.getX(), 0.001);
    assertEquals(0, vector2.getY(), 0.001);
    assertEquals(0, vector2.getZ(), 0.001);
    assertEquals(1, other2.getX(), 0.001);
    assertEquals(1, other2.getY(), 0.001);
    assertEquals(1, other2.getZ(), 0.001);


  }
}

