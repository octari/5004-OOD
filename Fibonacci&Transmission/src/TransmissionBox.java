/**
 * This class represents a single car transmission. A car transmission has a current speed and gear,
 * and 4 speed thresholds for the 5 gears in order.
 */

public class TransmissionBox {
  /**
   * Current speed for this car.
   */
  private int speed;
  /**
   * Speed to go from 1 to 2 (exclusive).
   */
  private int gearSpeed1;
  /**
   * Speed to go from 2 to 3 (exclusive).
   */
  private int gearSpeed2;
  /**
   * Speed to go from 3 to 4 (exclusive).
   */
  private int gearSpeed3;
  /**
   * Speed to go from 4 to 5 (exclusive).
   */
  private int gearSpeed4;
  /**
   * Current gear of this car.
   */
  private int gear;


  /**
   * Construct a Transmission object that has the provided speed and 4 speed thresholds for the 5
   * gears in order. The speed should be a non-negative number. The speed should not be allowed to
   * go beyond 120 mph. Speed thresholds should be monotonically increasing and they should
   * non-negative(i.e. the threshold between gears 2 and 3 should not be greater than that between
   * gears 3 and 4, and so on).
   *
   * @param speed      the current speed of this car
   * @param gearSpeed1 speed to go from 1 to 2 (exclusive)
   * @param gearSpeed2 speed to go from 2 to 3 (exclusive)
   * @param gearSpeed3 speed to go from 3 to 4 (exclusive)
   * @param gearSpeed4 speed to go from 4 to 5 (exclusive)
   * @throws IllegalArgumentException The speed is a negative number or it's greater than 120 mph;
   *                                  Or speed thresholds are not monotonically increasing or
   *                                  negative
   */
  public TransmissionBox(int speed, int gearSpeed1, int gearSpeed2,
                         int gearSpeed3, int gearSpeed4) throws IllegalArgumentException {
    if (speed < 0 || speed > 120) {
      throw new IllegalArgumentException("Speed is illegal.");
    }
    if (gearSpeed1 >= gearSpeed2 || gearSpeed2 >= gearSpeed3 || gearSpeed3 >= gearSpeed4
            || gearSpeed1 < 0) {
      throw new IllegalArgumentException("Speed thresholds are illegal.");
    }


    this.speed = speed;
    this.gearSpeed1 = gearSpeed1;
    this.gearSpeed2 = gearSpeed2;
    this.gearSpeed3 = gearSpeed3;
    this.gearSpeed4 = gearSpeed4;

    // get the current gear according to the speed
    gear = 1;
    if (speed > gearSpeed1) {
      if (speed < gearSpeed2) {
        gear = 2;
      } else {
        if (speed < gearSpeed3) {
          gear = 3;
        } else {
          if (speed < gearSpeed4) {
            gear = 4;
          } else {
            gear = 5;
          }
        }
      }
    }
  }

  /**
   * Return a TransmissionBox object with speed increased by 2 and the appropriate gear.
   *
   * @return a TransmissionBox object with speed increased by 2 and the appropriate gear
   * @throws IllegalArgumentException when the increased speed is greater than 120
   */
  public TransmissionBox speedIncrease() throws IllegalArgumentException {
    speed = speed + 2;
    TransmissionBox ret = new TransmissionBox(speed, gearSpeed1, gearSpeed2,
            gearSpeed3, gearSpeed4);
    return ret;
  }

  /**
   * Return a TransmissionBox object with speed decreased by 2 and the appropriate gear.
   *
   * @return a TransmissionBox object with speed decreased by 2 and the appropriate gear
   * @throws IllegalArgumentException when the decreased speed is negative
   */
  public TransmissionBox speedDecrease() throws IllegalArgumentException {
    speed = speed - 2;
    TransmissionBox ret = new TransmissionBox(speed, gearSpeed1, gearSpeed2,
            gearSpeed3, gearSpeed4);
    return ret;
  }

  /**
   * Return the current speed of the car.
   *
   * @return the current speed of the car
   */
  public int getSpeed() {
    return this.speed;
  }

  /**
   * Return the current gear of the car.
   *
   * @return the current gear of the car
   */
  public int getGear() {
    return this.gear;
  }

  /**
   * Return a suitably formatted string about current speed and gear of the car.
   *
   * @return a suitably formatted string about current speed and gear of the car
   */
  public String toString() {
    String printable;
    int currentSpeed = getSpeed();
    int currentGear = getGear();
    printable = "Current speed: " + currentSpeed + "\nCurrent gear: "
            + currentGear;
    return printable;
  }


}
