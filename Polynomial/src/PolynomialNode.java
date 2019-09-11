/**
 * This interface represents all operations for a node in a Polynomial implemented as an ADT.
 */
interface PolynomialNode {
  /**
   * Add the given term to the polynomial node, modify the polynomial.
   *
   * @param coefficient the coefficient of the given term
   * @param power       the power of the given term
   * @return the head of modified polynomial
   */
  PolynomialNode addTerm(int coefficient, int power);

  /**
   * Return the coefficient of this node.
   *
   * @return the coefficient
   */
  int getCoefficient();

  /**
   * Return the coefficient of the term with the given power.
   *
   * @param power the power of the term
   * @return coefficient of the corresponding term
   */
  int findCoefficient(int power);

  /**
   * Return the greatest power among the nodes in the polynomial.
   *
   * @return the greatest power
   */
  int countDegree();

  /**
   * Remove the given term from the polynomial, modify the polynomial.
   *
   * @param power the power of the term
   * @return the head of the modified polynomial
   */
  PolynomialNode remove(int power);

  /**
   * Calculate the result of the polynomial by accumulating the value of nodes with the given x.
   *
   * @param x the value of the x
   * @return the calculation result
   */
  double evaluate(double x);

  /**
   * Add up two polynomials by adding nodes of this to the other.
   *
   * @param other another polynomial
   * @return the added polynomial
   */
  Polynomial addHelp(Polynomial other);

}
