/**
 * This class represents a Likert question, It inherits from AbstractQuestion and overrides method
 * answer.
 */
public class Likert extends AbstractQuestion {
  private String type;

  /**
   * Construct an Essay object wit the given text and status, and the type of it is "Likert".
   *
   * @param text   the text of the question
   * @param status the status of the question
   */
  public Likert(String text, TypeOfStatus status) {
    super(text, status);
    type = "Likert";

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
   * Answer the question. The answer must be one of the 5-point Likert scale (Strongly Agree, Agree,
   * Neither Agree nor Disagree, Disagree, Strongly Disagree), otherwise, an exception should be
   * thrown.
   *
   * @param a the answer users enter, String
   * @throws IllegalArgumentException when the answer is not in the 5-point scale.
   */
  @Override
  public void answer(String a) throws IllegalArgumentException {
    switch (a) {
      case ("Strongly Agree"):
        answer = a;
        break;

      case ("Agree"):
        answer = a;
        break;

      case ("Neither Agree nor Disagree"):
        answer = a;
        break;

      case ("Disagree"):
        answer = a;
        break;

      case ("Strongly Disagree"):
        answer = a;
        break;

      default:
        throw new IllegalArgumentException("Invalid answer.");
    }


  }
}
