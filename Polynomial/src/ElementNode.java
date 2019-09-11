/**
 * This class represents an element node in the polynomial ADT implementation.
 */
class ElementNode implements PolynomialNode {
  private int coefficient;
  private int power;
  private PolynomialNode rest;

  ElementNode(int coefficient, int power, PolynomialNode rest) {
    this.coefficient = coefficient;
    this.power = power;
    this.rest = rest;
  }

  /**
   * Add a new term to the node.
   *
   * @param coefficient the coefficient of the given term
   * @param power       the power of the given term
   * @return the head of modified polynomial
   */
  @Override
  public PolynomialNode addTerm(int coefficient, int power) {
    if (power == this.power) {
      if (this.coefficient + coefficient == 0) {
        return rest;
      } else {
        return new ElementNode(this.coefficient + coefficient, power, this.rest);
      }
    } else if (power > this.power) {
      return new ElementNode(coefficient, power, this);
    } else {
      return new ElementNode(this.coefficient, this.power, this.rest.addTerm(coefficient, power));
    }
  }

  /**
   * Return the coefficient of the node.
   *
   * @return the coefficient
   */
  @Override
  public int getCoefficient() {
    return this.coefficient;
  }

  /**
   * Return the coefficient of the corresponding term.
   *
   * @param power the power of the term
   * @return the coefficient of the corresponding term
   */
  @Override
  public int findCoefficient(int power) {
    if (this.power == power) {
      return this.coefficient;
    } else {
      return rest.findCoefficient(power);
    }
  }

  /**
   * Return the greatest power among the nodes.
   *
   * @return the greatest power
   */
  @Override
  public int countDegree() {
    if (this.power >= this.rest.countDegree()) {
      return power;
    } else {
      return this.rest.countDegree();
    }
  }

  /**
   * Remove the corresponding the term from the polynomial.
   *
   * @param power the power of the term
   * @return the head of the modified polynomial
   */
  @Override
  public PolynomialNode remove(int power) {
    if (this.power == power) {
      return rest;
    }
    return new ElementNode(this.coefficient, this.power, this.rest.remove(power));
  }

  /**
   * Calculate the value of polynomial by adding the value nodes.
   *
   * @param x the value of the x
   * @return sum of values of the nodes
   */
  @Override
  public double evaluate(double x) {
    double result = 1;
    for (int i = 0; i < this.power; i++) {
      result = result * x;
    }
    return (double) coefficient * result + rest.evaluate(x);
  }


  /**
   * Add the nodes of this polynomial to the other.
   *
   * @param other another polynomial
   * @return the modified polynomial
   */
  @Override
  public Polynomial addHelp(Polynomial other) {
    other.addTerm(this.coefficient, this.power);
    return rest.addHelp(other);

  }

  /**
   * Return the formatted expression of nodes.
   *
   * @return the formatted String
   */
  @Override
  public String toString() {
    if (rest instanceof EmptyNode) {
      if (power == 0) {
        return "" + this.coefficient;
      }
      return "" + this.coefficient + "x^" + this.power;
    }

    String pre;

    if (power == 0) {
      pre = "" + this.coefficient + " ";
    } else {
      pre = "" + this.coefficient + "x^" + this.power + " ";
    }

    if (rest.getCoefficient() <= 0) {
      return pre + rest.toString();
    } else {
      return pre + "+" + rest.toString();
    }
  }
}
