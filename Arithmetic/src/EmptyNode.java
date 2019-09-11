import java.util.Stack;

/**
 * This class represents the empty node, the end of the expression.
 * It implements all the operations in the ArithmeticNode interface.
 */
public class EmptyNode implements ArithmeticNode {
  public EmptyNode (){

  }

  /**
   * Return the final result if it's valid expression; otherwise throw Arithmetic Exception.
   * @param helpStack Stack used to help the evaluation
   * @return the result of the evaluation
   * @throws ArithmeticException when the expression can't be evaluated
   */
  @Override
  public double evaluateHelper(Stack<Double> helpStack) throws ArithmeticException {
    if (helpStack.size() == 1 ) {
      try {
        return helpStack.pop();
      }
      catch (Exception e){
        throw new ArithmeticException("can't be evaluated");
      }
    }
    throw new ArithmeticException("can't be evaluated");
  }

  /**
   * Return null
   * @return null
   */
  @Override
  public String getElement() {
    return null;
  }

  /**
   * Return the operators in the help stack.
   * @param helperStack Stack used to help the convert
   * @return the operators in the help stack
   * @throws IllegalStateException when ones in the stack are invalid operators
   */
  @Override
  public String convert(Stack<ArithmeticNode> helperStack) throws IllegalStateException {
    String r = "";
    if (!helperStack.empty()) {
      while (!helperStack.empty()) {

        if (!helperStack.peek().getElement().matches("^[+\\-*/()]$")){
          throw new IllegalStateException("cant convert.");
        }
        r = r.concat(helperStack.pop().getElement()) + " ";
      }
    }
    return r;
  }


  @Override
  public ArithmeticNode addTerm(String n) {
    if (n.matches("^\\d+$") || n.matches("^[a-zA-Z]+$")) {
      return new OperandNode(n, new EmptyNode());
    } else {
      return new OperatorNode(n, new EmptyNode());
    }
  }

  /**
   * Return empty string.
   * @return ""
   */
  @Override
  public String toString() {
    return "";
  }
}
