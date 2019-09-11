/**
 * This class represents the abstract expression.
 */
public abstract class AbstractExpression {
  protected ArithmeticNode head;

  /**
   * Construct an abstract expression with the given string.
   * @param expression the given string
   * @throws IllegalArgumentException when the string is in an invalid format.
   */
  public AbstractExpression(String expression) throws IllegalArgumentException {
    this.head = new EmptyNode();

    if (expression == null || expression.equals("") || expression.charAt(0) == ' '
            || expression.charAt(expression.length() - 1) == ' ') {
      throw new IllegalArgumentException("The expression is not in a valid format.");
    }
  }

  /**
   * Return a formatted String of the expression.
   * @return a formatted String of the expression
   */
  public String toString() {
    return head.toString();
  }
}

