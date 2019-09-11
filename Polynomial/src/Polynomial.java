
public interface Polynomial {
  /**
   * Add a new term of the given power and coefficient to the this polynomial.
   *
   * @param coefficient the coefficient of the term
   * @param power       the power of the term
   * @throws IllegalArgumentException when the power is negative
   */
  void addTerm(int coefficient, int power) throws IllegalArgumentException;

  /**
   * Remove a term of the given power from this polynomial.
   *
   * @param power the power of the term to be removed
   */
  void removeTerm(int power);

  /**
   * Return the degree of this polynomial.
   *
   * @return the degree of this polynomial
   */
  int getDegree();

  /**
   * Return the coefficient of the term of given power. When there is no such a term, return 0.
   *
   * @param power the power of the term
   * @return coefficient of the term of the given term
   */
  int getCoefficient(int power);

  /**
   * Calculate the value of the polynomial with the given x.
   *
   * @param number the value of x
   * @return the calculation result.
   */
  double evaluate(double number);

  /**
   * Add two polynomials.
   *
   * @param other another polynomial
   * @return a new result polynomial
   * @throws IllegalArgumentException when the other is not an instance of PolynomialImpl
   */
  Polynomial add(Polynomial other) throws IllegalArgumentException;

}
