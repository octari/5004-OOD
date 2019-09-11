

/**
 * This class implements the Polynomial ADT.
 */
public class PolynomialImpl implements Polynomial {
  private PolynomialNode head;

  /**
   * Create a new empty polynomial.
   */
  public PolynomialImpl() {
    this.head = new EmptyNode();
  }


  /**
   * Create a corresponding polynomial using the given polynomial String.
   *
   * @param polynomial the given polynomial String
   * @throws IllegalArgumentException when the polynomial is not in the valid format.
   */
  public PolynomialImpl(String polynomial) throws IllegalArgumentException {

    this.head = new EmptyNode();

    String[] terms = polynomial.split(" ");
    for (String term : terms) {
      int coefficient = 0;
      int pow = 0;
      String t = term.replace("x^", " ");
      String[] n = t.split(" ");
      try {
        int i = 0;
        for (String num : n) {
          if (i == 0) {
            coefficient = Integer.parseInt(num);
          } else if (i == 1) {
            pow = Integer.parseInt(num);
          }
          i++;
        }
        this.addTerm(coefficient, pow);
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException("The input polynomial is invalid.");
      }

    }
  }

  /**
   * Add a term to the polynomial.
   *
   * @param coefficient the coefficient of the term
   * @param power       the power of the term
   * @throws IllegalArgumentException when the power of the term is negative.
   */
  @Override
  public void addTerm(int coefficient, int power) throws IllegalArgumentException {
    if (power < 0) {
      throw new IllegalArgumentException("A power shouldn't be negative.");
    } else if (coefficient != 0) {
      this.head = this.head.addTerm(coefficient, power);
    }
  }

  /**
   * Remove a term from the polynomial.
   */
  @Override
  public void removeTerm(int power) {
    this.head = this.head.remove(power);
  }

  /**
   * Return the degree of the polynomial.
   *
   * @return the degree of the polynomial
   */
  @Override
  public int getDegree() {
    return this.head.countDegree();
  }

  /**
   * Return the coefficient of the term with given power.
   *
   * @param power the power of the term
   * @return the coefficient of the term
   */
  @Override
  public int getCoefficient(int power) {
    return head.findCoefficient(power);
  }

  /**
   * Calculate the value of the polynomial with the given x.
   *
   * @param number the value of x
   * @return the calculation result
   */
  @Override
  public double evaluate(double number) {
    return head.evaluate(number);
  }

  /**
   * Add up two polynomials.
   *
   * @param other another polynomial
   * @return a new result polynomial
   * @throws IllegalArgumentException when the other isn't an instance of PolynomialImpl.
   */
  @Override
  public Polynomial add(Polynomial other) throws IllegalArgumentException {
    if (!(other instanceof PolynomialImpl)) {
      throw new IllegalArgumentException("The two should be in the same class.");
    } else {
      PolynomialImpl temp = new PolynomialImpl();
      ((PolynomialImpl) other).head.addHelp(temp);
      return this.head.addHelp(temp);
    }
  }

  /**
   * Return the formatted expression of the polynomial.
   *
   * @return the formatted String.
   */
  @Override
  public String toString() {
    return head.toString();
  }
}
