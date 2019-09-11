/**
 * This class represents a Essay-form question. It inherits from AbstractQuestion and overrides
 * method answer.
 */
public class Essay extends AbstractQuestion {
  private String type;


  /**
   * Construct an Essay object wit the given text and status, and the type of it is "Essay-form".
   *
   * @param text   the text of the question
   * @param status the status of the question
   */
  public Essay(String text, TypeOfStatus status) {
    super(text, status);
    type = "Essay-form";
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
   * Answer the question. The answer to Essay-form question is at most 140 characters, otherwise, an
   * exception should be thrown.
   *
   * @param a the answer users enter, String
   * @throws IllegalArgumentException when the answer is longer than 140 characters
   */
  @Override
  public void answer(String a) throws IllegalArgumentException {

    if (a.length() > 140) {
      throw new IllegalArgumentException("Your answer is too long.");
    } else {
      answer = a;
    }
  }
}
