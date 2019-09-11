import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * This class represents a single Fibonacci counter. The Fibonacci counter has counter number and
 * corresponding value.
 */
public class FibonacciCounter {
  /**
   * The count number of the Fibonacci counter.
   */
  private int num;
  /**
   * The corresponding value of the counter.
   */
  private int value;

  /**
   * Construct a FibonacciCounter object that has provided counter and corresponding value. The
   * count is defined only for values that are non-negative. Any attempt to go outside this range is
   * invalid. The fibonacci number returned by the object should always be big enough to hold in a
   * single integer
   *
   * @param num the counter
   * @throws IllegalArgumentException The count is defined only for values that are non-negative. Or
   *                                  the fibonacci number returned by the object is too big to hold
   *                                  in a single integer
   */
  public FibonacciCounter(int num) throws IllegalArgumentException {
    if (num >= 0) {
      this.num = num;
      long result = (long) ((pow(((1 + sqrt(5)) / 2),
              num) - pow(((1 - sqrt(5)) / 2), num)) / sqrt(5));
      if (result > Integer.MAX_VALUE) {
        throw new IllegalArgumentException("The fibonacci number is too big for a single integer.");
      } else {
        this.value = (int) result;
      }
    } else {
      throw new IllegalArgumentException("The count is negative.");
    }
  }

  /**
   * Return the count number.
   *
   * @return the count number
   */
  public int getCount() {
    return num;
  }

  /**
   * Return the corresponding value.
   *
   * @return the corresponding value
   */
  public int getValue() {
    return value;
  }

  /**
   * Return a FibonacciCounter object with its count incremented by 1.
   *
   * @return a FibonacciCounter object with its count incremented by 1
   * @throws  IllegalArgumentException when increased fibonacci number is beyond an integer
   */
  public FibonacciCounter countIncrease() throws IllegalArgumentException  {
    FibonacciCounter ret = new FibonacciCounter(num + 1);
    return ret;
  }

  /**
   * Return a FibonacciCounter object with its count decremented by 1.
   *
   * @return a FibonacciCounter object with its count decremented by 1
   */
  public FibonacciCounter countDecrease() {
    FibonacciCounter ret;
    if (num == 0) {
      ret = new FibonacciCounter(num);
    } else {
      ret = new FibonacciCounter(num - 1);
    }
    return ret;
  }


}
