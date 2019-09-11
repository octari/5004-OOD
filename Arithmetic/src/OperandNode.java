import java.util.Stack;

/**
 * This class represents an operand term. It implements all the operation in the ArithmeticNode interface.
 */
public class OperandNode implements ArithmeticNode {
  private String n;
  private ArithmeticNode rest;

  /**
   * Construct an OperandNode with the given String.
   * @param n the Operand string
   * @param rest the following ArithmeticNode.
   */
  public OperandNode(String n, ArithmeticNode rest) {
    this.n = n;
    this.rest = rest;
  }

  /**
   * Push the operand node into the help Stack, and continue to evaluate the rest.
   * @param helpStack Stack used to help the evaluation
   * @return the result of evaluation the rest
   */
  @Override
  public double evaluateHelper(Stack<Double> helpStack) {
    try {
      helpStack.push(Double.parseDouble(this.getElement()));
    }
    catch(Exception e){
      throw new ArithmeticException("cant be evaluated.");
    }
    return rest.evaluateHelper(helpStack);
  }

  /**
   * Return the content of the term.
   * @return the content of the term.
   */
  @Override
  public String getElement() {
    return this.n;
  }

  /**
   * Turn the node into postfix expresion format.
   * @param helperStack Stack used to help the convert
   * @return the formatted concatenation of the this term and the rest in String.
   */
  @Override
  public String convert(Stack<ArithmeticNode> helperStack) {
    return n + " ".concat(rest.convert(helperStack));
  }

  /**
   * Add the term to the existed expression.
   * @param n the content of the term.
   * @return the added result
   */
  @Override
  public ArithmeticNode addTerm(String n) {
    return new OperandNode(this.n, rest.addTerm(n));
  }

  /**
   * Return a formatted String of this node.
   * @return the formatted String of the node.
   */
  @Override
  public String toString() {
    if (rest.getElement() == null) {
      return "" + n + rest.toString();
    } else {
      return "" + n + " " + rest.toString();
    }
  }
}
