/**
 * This class represent a Yes/No question. It inherits from AbstractQuestion, and overrides method
 * answer.
 */
public class YesNo extends AbstractQuestion {
  /**
   * The type of the question.
   */
  private String type;

  /**
   * Construct a YesNo object with the given text and status, the type of it is "Yes/No".
   *
   * @param text   the text fo the question
   * @param status the status of the question
   */
  public YesNo(String text, TypeOfStatus status) {
    super(text, status);
    type = "Yes/No";
  }

  /**
   * Return the type of the question.
   *
   * @return the type of the question
   */
  @Override
  public String getQuestionType() {
    return this.type;
  }

  /**
   * Answer the  question. The answer to the Yes/No question must be "Yes" or "No", otherwise, an
   * exception should be thrown.
   *
   * @param a the answer users enter, String
   * @throws IllegalArgumentException when the answer is not "Yes" or "No"
   */
  @Override
  public void answer(String a) throws IllegalArgumentException {
    if (a.equals("Yes") || a.equals("No")) {
      this.answer = a;
    } else {
      throw new IllegalArgumentException("Invalid answer.");
    }

  }
}
