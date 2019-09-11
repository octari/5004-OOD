import java.util.Scanner;
import java.util.Stack;

/**
 * This class represents an infixExpression, inherited from the AbstractExpression class.
 */
public class InfixExpression extends AbstractExpression {
  /**
   * Construct a infix expression with the given expression String.
   *
   * @param expression the infix expression in String
   * @throws IllegalArgumentException when the expression is invalid
   */
  public InfixExpression(String expression) throws IllegalArgumentException {
    super(expression);
    if (!isValid(expression)) {
      throw new IllegalArgumentException("The input is invalid.");
    }
    for (String s : expression.split(" ")) {
      if (s.equals("")) {
        continue;
      }
        this.head = this.head.addTerm(s);
    }
  }

  /**
   * Determine whether the expression is valid. The parentheses can be anywhere in the expression
   * and they can even be empty, but they have to be in pairs
   */
  private boolean isValid(String expression) {
    Stack<String> parenthesesHelper = new Stack<>();

    // strip the multiple spaces among the expression.
    expression = expression.replaceAll("\\s\\s+", " ");


    String[] expressions = expression.split(" ");

    for (String s : expressions){
      if (s.equals("(")){
        parenthesesHelper.push(s);
      }
      else if (s.equals(")")){
        if (parenthesesHelper.empty()){
          return false;
        }
        parenthesesHelper.pop();
      }
    }
    if (parenthesesHelper.empty()) {
      expression = expression.replaceAll("[()]", "");
      expression = expression.replaceAll("\\s\\s+", " ");

      return (expression.trim().matches("^[\\d[a-zA-Z]]+(\\s[+\\-*/]\\s[\\d[a-zA-Z]]+)+$")
              || expression.trim().matches("^[\\d[a-zA-Z]]+$"));
    }
    return false;


//    // the begin of the valid infix expression must be ( or operand.
//    if (expressions[0].equals("(")) {
//      parenthesesHelper.push(expressions[0]);
//    } else if (!expressions[0].matches("^\\d+$")
//            && !expressions[0].matches("^[a-zA-Z]+$")) {
//      return false;
//    }
//
//
//    // check the expression except the start and end.
//    for (int i = 1; i < expressions.length - 1; i++) {
//      // when it's an operand, the one before should be ( or operator,
//      // the one behind should be ) or operator.
//      if (expressions[i].matches("^\\d+$") || expressions[i].matches("^[a-zA-Z]+$")) {
//        if (!expressions[i - 1].matches("^([+*\\-/(])$")) {
//          return false;
//        }
//        if (!expressions[i + 1].matches("^([+*\\-/)])$")) {
//          return false;
//        }
//      }
//      // when it's an operator, the one before should ) or operand,
//      // the one behind should be ( or operand.
//      else if (expressions[i].matches("^([+*\\-/])$")) {
//        if (!expressions[i - 1].matches("^\\d+$") &&
//                !expressions[i - 1].matches("^[a-zA-Z]+$") && !expressions[i - 1].matches("\\)")) {
//          return false;
//        }
//        if (!expressions[i + 1].matches("^\\d+$") &&
//                !expressions[i + 1].matches("^[a-zA-Z]+$") && !expressions[i + 1].matches("\\(")) {
//          return false;
//        }
//      }
//
//      // when it's (, push to stack.
//      else if (expressions[i].equals("(")) {
//        parenthesesHelper.push(expressions[i]);
//      } else if (expressions[i].equals(")")) {
//        if (parenthesesHelper.empty()) {
//          return false;
//        }
//        parenthesesHelper.pop();
//      }
//    }
//
//    // the end of valid infix expression must be ) or operand.
//    if (expressions[expressions.length - 1].matches("\\)")) {
//      if (parenthesesHelper.empty()) {
//        return false;
//      } else {
//        parenthesesHelper.pop();
//      }
//    } else if (!expressions[expressions.length - 1].matches("^\\d+$") &&
//            !expressions[expressions.length - 1].matches("^[a-zA-Z]+$")) {
//      return false;
//    }
//
//    if (!parenthesesHelper.empty()){
//      return false;
//    }
//   return false;

  }

  /**
   * Evaluated the infix expression.
   *
   * @return the evaluation result
   */
  public double evaluate() {
    return this.toPostfix().evaluate();
  }

  /**
   * Turn the infix expression ino corresponding postfix expression
   *
   * @return the corresponding postfix expression
   */
  public PostfixExpression toPostfix() {
    Stack<ArithmeticNode> helperStack = new Stack<>();
    String result = head.convert(helperStack).trim();
    return new PostfixExpression(result);
  }
}
