/**
 * This class represents an empty node in the polynomial ADT implementation.
 */
class EmptyNode implements PolynomialNode {

  /**
   * Add a term to an empty node.
   *
   * @param coefficient the coefficient of the given term
   * @param power       the power of the given term
   * @return the term itself.
   */
  @Override
  public PolynomialNode addTerm(int coefficient, int power) {
    return new ElementNode(coefficient, power, new EmptyNode());
  }

  /**
   * Return the coefficient of an empty node.
   *
   * @return 0
   */
  @Override
  public int getCoefficient() {
    return 0;
  }

  /**
   * Return 0 Since the coefficient of empty node is 0.
   *
   * @param power the power of the term
   * @return 0
   */
  @Override
  public int findCoefficient(int power) {
    return 0;
  }


  /**
   * Return the power of an empty node.
   *
   * @return 0
   */
  @Override
  public int countDegree() {
    return 0;
  }

  /**
   * Remove the empty node itself.
   *
   * @param power the power of the term
   * @return itself
   */
  @Override
  public PolynomialNode remove(int power) {
    return new EmptyNode();
  }

  /**
   * Return 0.
   *
   * @param x the value of the x
   * @return 0
   */
  @Override
  public double evaluate(double x) {
    return 0;
  }

  /**
   * Return the other polynomial itself.
   *
   * @param other another polynomial
   * @return the other polynomial
   */
  @Override
  public Polynomial addHelp(Polynomial other) {
    return other;
  }


  @Override
  public String toString() {
    return "0";
  }
}
