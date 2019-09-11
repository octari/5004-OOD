import java.util.EmptyStackException;
import java.util.Stack;

/**
 * This class represents an operator term. It implements all the operation in the ArithmeticNode interface.
 */
public class OperatorNode implements ArithmeticNode {
  private String operator;
  private ArithmeticNode rest;
  private int precedence;


  /**
   * Constructor an operator node with the given operator String.
   * @param operator the string operator
   * @param rest the following ArithmeticNode.
   * @throws IllegalArgumentException when the given string is not a valid operator.
   */
  public OperatorNode(String operator, ArithmeticNode rest)throws IllegalArgumentException {
    this.operator = operator;
    this.rest = rest;
    switch (operator) {
      case "+":
        precedence = 0;
        break;
      case "-":
        precedence = 0;
        break;
      case "*":
        precedence = 1;
        break;
      case "/":
        precedence = 1;
        break;
      case "(":
        precedence = -1;
        break;
      case ")":
        precedence = 0;
        break;
      default:
        throw new IllegalArgumentException("Invalid input.");
    }
  }

  /**
   * Determine whether this operator has greater precedence than the other.
   * @param other another operator
   * @return true if this has greater precedence than the other; return false, otherwise.
   */
  private boolean isGreater(OperatorNode other) {
    return this.precedence > other.precedence;
  }

  /**
   * Turn the node into corresponding operator and evaluate.
   * @param helpStack Stack used to help the evaluation
   * @return the result of the evaluation
   */
  @Override
  public double evaluateHelper(Stack<Double> helpStack) {
    double a = 0;
    double b = 0;
    try {
      a = helpStack.pop();
      b = helpStack.pop();
    }
    catch (EmptyStackException e){
      throw new ArithmeticException("cant be evaluated.");
    }
    double result = 0;
    switch (operator) {
      case "+":
        result = a + b;
        break;
      case "-":
        result = b - a;
        break;
      case "*":
        result = a * b;
        break;
      case "/":
        result = b / a;
        break;
        default:
          throw new ArithmeticException("cant be evaluated.");
    }
    helpStack.push(result);
    return rest.evaluateHelper(helpStack);
  }

  /**
   * Return the operator.
   * @return the operator
   */
  @Override
  public String getElement() {
    return operator;
  }

  /**
   * Turn the operator node into string in the formatted postfix expression.
   * @param helperStack Stack used to help the convert
   * @return the result of the convert
   */
  @Override
  public String convert(Stack<ArithmeticNode> helperStack) {
    StringBuilder pre = new StringBuilder();

    // when the operator is )
    if (this.getElement().equals(")")) {

      if (helperStack.empty()) {
        throw new IllegalStateException("cant convert.");
      }

      while (!helperStack.empty()) {
        if (helperStack.peek().getElement().equals("(")) {
          helperStack.pop();
          break;

        } else {
          pre.append(helperStack.pop().getElement()).append(" ");
        }
      }
      return pre + rest.convert(helperStack);
    }
//      while(!helperStack.peek().getElement().equals("(")){
//        if (!helperStack.empty()){
//          pre = pre + helperStack.pop().getElement() + " ";
//        }
//        else{
//          throw new IllegalStateException("cant convert.");
//        }
//      }
//      helperStack.pop();
//      return pre + rest.convert(helperStack);
//    }

    else if (helperStack.empty() || this.operator.equals("(")) {
      helperStack.push(this);
      return rest.convert(helperStack);
    }

    else {
      while (!helperStack.empty() /*&& !helperStack.peek().getElement().equals("(")*/) {
        if (!this.isGreater((OperatorNode) helperStack.peek())) {
          if (!helperStack.peek().getElement().matches("^[+\\-*/]$")) {
            throw new IllegalStateException("cant convert.");
          }

          pre = new StringBuilder(pre.toString().concat(helperStack.pop().getElement()) + " ");
        } else {
          break;
        }
      }

      helperStack.push(this);
      return pre + rest.convert(helperStack);
  }


  }


  /**
   * Add the operator to the existed expression.
   * @param n the content of the term
   * @return the added result
   */
  @Override
  public ArithmeticNode addTerm(String n) {
    return new OperatorNode(this.operator, rest.addTerm(n));
  }

  /**
   * Return the formatted string of the operator
   * @return the formatted string of the operator
   */
  @Override
  public String toString() {
    if (rest.getElement() == null) {
      return this.operator.concat(rest.toString());
    } else {
      return this.operator.concat(" ".concat(rest.toString()));
    }
  }
}
