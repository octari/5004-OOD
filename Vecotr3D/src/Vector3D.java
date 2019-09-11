import static java.lang.Math.acos;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * This class represent a 3D vector. A 3D vector has x, y, z components.
 */
public class Vector3D {
  /**
   * x component of this 3D vector.
   */
  private double x;
  /**
   * y component of this 3D vector.
   */
  private double y;
  /**
   * z component of this 3D vector.
   */
  private double z;

  /**
   * Construct a Vector3D object that has x, y, z components.
   *
   * @param x x component of this 3D vector
   * @param y y component of this 3D vector
   * @param z z component of this 3D vector
   */
  public Vector3D(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  /**
   * Return the value of x of the 3D vector.
   *
   * @return the value of x of the 3D vector
   */
  public double getX() {
    return this.x;
  }

  /**
   * Return the value of y of the 3D vector.
   *
   * @return the value of y of the 3D vector
   */
  public double getY() {
    return this.y;
  }

  /**
   * Return the value of z of the 3D vector.
   *
   * @return the value of z of the 3D vector
   */
  public double getZ() {
    return this.z;
  }

  /**
   * Return a string that describes this vector. This string should be of the form “(x,y,z)”
   * replacing the letters with their values. Each component should be formatted to round to exactly
   * two decimal places.
   *
   * @return a suitably formatted string about x, y, z components of the 3D vector
   */
  public String toString() {
    return String.format("(%.2f,%.2f,%.2f)", this.x, this.y, this.z);
  }

  /**
   * Return the magnitude of the 3D vector.
   *
   * @return the magnitude of the 3D vector
   */
  public double getMagnitude() {
    double mag = sqrt(pow(x, 2) + pow(y, 2) + pow(z, 2));
    return mag;
  }

  /**
   * Return a normalized version of the 3D vector. this is a vector that is obtained by dividing
   * each component of a vector by its magnitude. It should throw an IllegalStateException object if
   * this operation cannot be completed (when the magnitude is 0).
   *
   * @return a normalized Vector3D object
   * @throws IllegalStateException the operation can't be completed.
   */
  public Vector3D normalize() throws IllegalStateException {
    if (this.getMagnitude() != 0) {
      Vector3D ret = new Vector3D(x / getMagnitude(), y / getMagnitude(),
              z / getMagnitude());
      return ret;
    } else {
      throw new IllegalStateException("The operation can't be completed.");
    }
  }

  /**
   * Return a 3D vector that is obtained by adding respective components of the two vectors. It
   * should not change the vectors that are being added.
   *
   * @param other another Vector3D object to be added
   * @return a new Vector3D object obtained by adding these two vectors
   */
  public Vector3D add(Vector3D other) {
    return new Vector3D(x + other.getX(), y + other.getY(), z + other.getZ());
  }

  /**
   * Multiplying a vector by a constant produces a vector obtained by multiplying each component of
   * the current vector by the provided constant. It should not change the two vectors. Return a 3D
   * vector obtained by multiplying the vector by the provided constant.
   *
   * @param con the constant multiplying the vector
   * @return a new Vector3D object obtained by multiplying the vector by the provided constant
   */
  public Vector3D multiply(double con) {
    return new Vector3D(x * con, y * con, z * con);
  }

  /**
   * The dot product of two vectors is the sum of the products of 3 components. It should not change
   * the two vectors. Return a double which is the dot product of 2 vectors.
   *
   * @param other another Vector3D to be used
   * @return a double which is the dot product of 2 vectors
   */
  public double dotProduct(Vector3D other) {
    double result = x * other.getX() + y * other.getY() + z * other.getZ();
    return result;
  }

  /**
   * Return the shorter of the two angles between two vectors in degrees. It should throw an
   * IllegalStateException if this operation cannot be completed (when  at least one of their
   * magnitudes is 0). It should not change the two vectors.
   *
   * @param other another vector
   * @return the shorter of the two angles between two vectors in degrees
   * @throws IllegalStateException this operation cannot be completed
   */
  public double angleBetween(Vector3D other) throws IllegalStateException {
    if (this.getMagnitude() != 0) {
      double angle = acos((this.dotProduct(other) / (this.getMagnitude()
              * other.getMagnitude()))) * 360 / (2 * Math.PI);
      return angle;
    } else {
      throw new IllegalStateException("The operation can't be completed.");
    }
  }
}
