import java.util.Stack;

/**
 * This interface represents a term in the expression.
 */
public interface ArithmeticNode {
  /**
   * Turn the node into corresponding operators or operands, and evaluate the them.
   * @param helpStack Stack used to help the evaluation
   * @return the result of the evaluation.
   */
  double evaluateHelper(Stack<Double> helpStack);

  /**
   * Add a new valid term to the existed expression.
   * @param n the content of the term.
   * @return the added result
   */
  ArithmeticNode addTerm(String n);

  /**
   * Return the content of the term.
   * @return Return the content of the term
   */
  String getElement();

  /**
   * Turn the infix expression into a formatted postfix expression node by node.
   * @param helperStack Stack used to help the convert
   * @return
   */
  String convert(Stack<ArithmeticNode> helperStack);
}
