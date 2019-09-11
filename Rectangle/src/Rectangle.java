import java.util.NoSuchElementException;

/**
 * This class represents a rectangle. The rectangle has x, y coordinates of its lower left corner,
 * its width and its height.
 */
public class Rectangle {
  /**
   * x of the rectangle's lower left corner.
   */
  private int x;
  /**
   * y of the rectangle's lower left corner.
   */
  private int y;
  /**
   * Width of the rectangle.
   */
  private int width;
  /**
   * Height of the rectangle.
   */
  private int height;

  /**
   * Construct a Rectangle object and initializes it to the given x, y coordinates of its lower left
   * corner, its width and its height.Creating a rectangle with non-positive width or height should
   * not be allowed, although x and y are allowed to be negative.
   *
   * @param x      x of the rectangle's lower left corner
   * @param y      y of the rectangle's lower left corner
   * @param width  width of the rectangle
   * @param height height of the rectangle
   * @throws IllegalArgumentException when width or height is  non-positive
   */
  public Rectangle(int x, int y, int width, int height) throws IllegalArgumentException {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Width or height is non-positive.");
    } else {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
    }
  }

  /**
   * Determine whether the two rectangles overlaps with each other. This method should return true
   * if this rectangle overlaps with other, false otherwise. Rectangles that touch each other are
   * not considered to be overlapping.
   *
   * @param other another Rectangle object
   * @return return true if this rectangle overlaps with other, false otherwise
   */
  public boolean overlap(Rectangle other) {
    return other.x > this.x - other.width && other.x < this.x + this.width
            && other.y > this.y - other.height && other.y < this.y + this.height;
  }

  /**
   * When the rectangles overlaps with each other, there is a common part of them. This method
   * returns a Rectangle object representing teh common part; when no intersection, a
   * NoSuchElementException would be thrown.
   *
   * @param other another Rectangle object
   * @return a Rectangle object that represents the overlap of the two rectangles
   * @throws NoSuchElementException when no intersection exists
   */
  public Rectangle intersect(Rectangle other) throws NoSuchElementException {

    if (!overlap(other)) {
      throw new NoSuchElementException("No intersection exists.");
    } else {
      int resultx;
      int resulty;
      int resultw;
      int resulth;
      if (this.x < other.x) {
        resultx = other.x;
        if (other.x + other.width > this.x + this.width) {
          resultw = this.width - (other.x - this.x);
        } else {
          resultw = other.width;
        }
      } else {
        resultx = this.x;
        if (this.x + this.width > other.x + other.width) {
          resultw = other.width - (this.x - other.x);
        } else {
          resultw = this.width;
        }
      }

      if (this.y < other.y) {
        resulty = other.y;
        if (other.y + other.height > this.y + this.height) {
          resulth = this.height - (other.y - this.y);
        } else {
          resulth = other.height;
        }
      } else {
        resulty = this.y;
        if (this.height + this.y > other.y + other.height) {
          resulth = other.height - (this.y - other.y);
        } else {
          resulth = this.height;
        }
      }
      return new Rectangle(resultx, resulty, resultw, resulth);
    }
  }

  /**
   * No matter the rectangles overlaps with each other or not, there is always a rectangle to hold
   * them totally. This method returns a Rectangle object which is the smallest.
   *
   * @param other another Rectangle object
   * @return a Rectangle object that represents the union of this rectangle and the other rectangle
   */
  public Rectangle union(Rectangle other) {
    int resultx;
    int resulty;
    int resultw;
    int resulth;
    if (this.overlap(other)) {
      if (this.x < other.x) {
        resultx = this.x;
        if (other.x + other.width > this.x + this.width) {
          resultw = other.width + other.x - this.x;
        } else {
          resultw = this.width;
        }
      } else {
        resultx = other.x;
        if (this.x + this.width > other.x + other.width) {
          resultw = this.width + this.x - other.x;
        } else {
          resultw = other.width;
        }
      }

      if (this.y < other.y) {
        resulty = this.y;
        if (other.y + other.height > this.y + this.height) {
          resulth = other.height + other.y - this.y;
        } else {
          resulth = this.height;
        }
      } else {
        resulty = other.y;
        if (this.y + this.height > other.y + other.height) {
          resulth = this.height + this.y - other.y;
        } else {
          resulth = other.height;
        }
      }
    } else {
      if (this.x > other.x) {
        resultx = other.x;
        if (other.x + other.width < this.x + this.width) {
          resultw = this.x - other.x + this.width;
        } else {
          resultw = other.width;
        }
      } else {
        resultx = this.x;
        if (this.x + this.width < other.x + other.width) {
          resultw = other.x - this.x + other.width;
        } else {
          resultw = this.width;
        }
      }

      if (this.y > other.y) {
        resulty = other.y;
        if (other.y + other.height < this.y + this.height) {
          resulth = this.y - other.y + this.height;
        } else {
          resulth = other.height;
        }
      } else {
        resulty = this.y;
        if (this.y + this.height < other.y + other.height) {
          resulth = other.y - this.y + other.height;
        } else {
          resulth = this.height;
        }
      }
    }
    return new Rectangle(resultx, resulty, resultw, resulth);
  }

  /**
   * Return a string that describes this vector.The string should be formatted exactly as: “x:2,
   * y:3, w:4, h:5” without the quotation mark and replacing the numbers with the actual attributes
   * of the object.
   *
   * @return a suitably formatted string about the Rectangle object
   */
  public String toString() {
    return "x:" + this.x + ", y:" + this.y + ", w:" + this.width + ", h:" + this.height;
  }
}
