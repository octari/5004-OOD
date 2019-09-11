import java.util.EmptyStackException;
import java.util.Stack;

/**
 * This class represent a postfix expression, inherited from the AbstractExpression class.
 */
public class PostfixExpression extends AbstractExpression {

  /**
   * Construct a postfix expression with the given expression String.
   * @param expression the postfix expression in String
   * @throws IllegalArgumentException when the expression is invalid
   */
  public PostfixExpression(String expression) throws IllegalArgumentException {
    super(expression);
    if (!isValid(expression)) {
      throw new IllegalArgumentException("The expression is not in a valid format.");
    }
    for (String s : expression.split(" ")) {
      if (s.equals("")) {
        continue;
      }
      if (s.matches("^\\d+$") || s.matches("^[+\\-*/]$")
              || s.matches("^[a-zA-Z]+$")) {
        head = this.head.addTerm(s);
      } else {
        throw new IllegalArgumentException("The expression is not in a valid format.");
      }

    }
  }

  /**
   * Evaluate the postfix expression.
   * @return the result of the postfix expression
   */
  public double evaluate() {
    Stack<Double> helpStack = new Stack<>();
    return head.evaluateHelper(helpStack);

  }

  /**
   * Determine whether the postfix expression is valid in the constructor.
   * @param expression the given postfix expression in String
   * @return true when the expression is valid; return false, otherwise.
   */
  private boolean isValid(String expression) {
    Stack<String> helperStack = new Stack<>();
    String[] expressions = expression.split(" ");

    // Deal with the null input expression.
    if (expressions.length == 0) {
      return false;
    }


    for (String s : expressions) {
      if (s.equals("")) {
        continue;
      }

      if (!s.matches("^\\d+$") && !s.matches("^[+\\-*/]$")
              && !s.matches("^[a-zA-Z]+$")) {
        return false;
      }

      if (s.matches("^\\d+$") || s.matches("^[a-zA-Z]+$")) {
        helperStack.push(s);
        continue;
      }
      if (helperStack.empty()) {
        return false;
      }
      try {
        helperStack.pop();

        helperStack.pop();

        helperStack.push("1");
      } catch (EmptyStackException e) {
        return false;
      }
    }
    return helperStack.size() == 1;
  }


}
