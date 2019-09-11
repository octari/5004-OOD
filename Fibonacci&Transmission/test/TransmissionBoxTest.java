import org.junit.Before;
import org.junit.Test;

/**
 * A Junit test class for the Transmission class.
 */
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

public class TransmissionBoxTest {
  private TransmissionBox car1;

  @Before
  public void setUp() {
    car1 = new TransmissionBox(27, 20, 30, 40,
            50);
  }

  /**
   * Test for illegal arguments in the constructor. Exceptions should be thrown for
   * non-monotonically increasing speed thresholds.
   */
  @Test
  public void testThresholds() {
    try {
      TransmissionBox car = new TransmissionBox(35, 30, 36,
              40, 30);
      fail("Exception should have been thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("Speed thresholds are illegal.", e.getMessage());
    }
  }

  /**
   * Test for illegal arguments in the constructor. Exceptions should be thrown for negative speed.
   */
  @Test
  public void testNegativeSpeed() {
    try {
      TransmissionBox car = new TransmissionBox(-10, 20, 30,
              40, 50);
      fail("Exception should have been thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("Speed is illegal.", e.getMessage());
    }
  }

  /**
   * Test for illegal arguments in constructor. Exception should be thrown for the speed greater
   * than 120.
   */
  @Test
  public void testOverSpeed() {
    try {
      TransmissionBox car = new TransmissionBox(130, 20, 30,
              40, 50);
      fail("Exception should have been thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("Speed is illegal.", e.getMessage());
    }
  }

  /**
   * Test for illegal arguments in the constructor. Exceptions should be thrown for negative
   * thresholds.
   */
  @Test
  public void testNegativeThresholds() {
    try {
      TransmissionBox car = new TransmissionBox(35, -10, 36,
              40, 30);
      fail("Exception should have been thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("Speed thresholds are illegal.", e.getMessage());
    }

  }

  /**
   * Test that the constructor creates objects correctly when the correct parameters are passed to
   * it.
   */
  @Test
  public void testConstructor() {
    TransmissionBox car = new TransmissionBox(60, 30, 40,
            50, 60);
    assertEquals(60, car.getSpeed());
    assertEquals(5, car.getGear());

  }

  @Test
  public void testSpeed() {
    assertEquals(27, car1.getSpeed());
  }

  @Test
  public void testGear() {
    assertEquals(2, car1.getGear());
  }

  /**
   * Test method speedIncrease, after increasing speed, the gear wouldn't change.
   */
  @Test
  public void testIncreaseWithinGear() {
    car1 = car1.speedIncrease();
    assertEquals(29, car1.getSpeed());
    assertEquals(2, car1.getGear());

  }

  /**
   * Test method speedIncrease, after increasing speed, the gear will change.
   */
  @Test
  public void testIncreaseShiftGear() {
    TransmissionBox car2 = new TransmissionBox(29, 20, 30,
            40, 50);
    car2 = car2.speedIncrease();
    assertEquals(31, car2.getSpeed());
    assertEquals(3, car2.getGear());

  }

  /**
   * Test method speedIncrease when the increased speed is greater than 120.
   */
  @Test
  public void testIncreaseOverLimit() {
    TransmissionBox car = new TransmissionBox(119, 10, 20,
            30, 40);
    try {
      TransmissionBox result = car.speedIncrease();
      fail("An exception should have been thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("Speed is illegal.", e.getMessage());
    }


  }

  /**
   * Test method speedDecrease, after decreasing speed, the gear wouldn't change.
   */
  @Test
  public void testDecreaseWithinGear() {
    car1 = car1.speedDecrease();
    assertEquals(25, car1.getSpeed());
    assertEquals(2, car1.getGear());
  }

  /**
   * Test method speedDecreasing, after decreasing speed, the gear will change.
   */
  @Test
  public void testDecreaseShiftGear() {
    TransmissionBox car3 = new TransmissionBox(31, 20, 30,
            40, 50);
    car3 = car3.speedDecrease();
    assertEquals(29, car3.getSpeed());
    assertEquals(2, car3.getGear());

  }

  /**
   * Test method speedDecrease when the decreased speed is negative.
   */
  @Test
  public void testDecreaseNegative() {
    TransmissionBox car = new TransmissionBox(1, 10, 20,
            30, 40);
    try {
      TransmissionBox result = car.speedDecrease();
      fail("An exception should have been thrown.");
    } catch (IllegalArgumentException e) {
      assertEquals("Speed is illegal.", e.getMessage());
    }
  }

  @Test
  public void testPrint() {
    assertEquals("Current speed: 27\nCurrent gear: 2",
            car1.toString());
  }


}