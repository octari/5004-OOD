/**
 * This class represents a question. It offers all the operations mandated by the interface Question
 * .
 */
public abstract class AbstractQuestion implements Question {
  /**
   * The text of the question.
   */
  private String text;
  /**
   * The status of the question, required or optional.
   */
  private TypeOfStatus status;
  /**
   * The answer to the question.
   */
  protected String answer;


  /**
   * Construct an AbstractQuestion object using the given text and status.
   *
   * @param text   the text of the question
   * @param status the status of the question
   */
  public AbstractQuestion(String text, TypeOfStatus status) {
    this.text = text;
    this.status = status;
  }

  /**
   * Return the text of the question itself.
   *
   * @return the text of the question
   */
  @Override
  public String getQuestionText() {
    return text;
  }

  /**
   * Return the status of the question, required or optional.
   *
   * @return the status of the question, TypeOfStatus
   */
  @Override
  public TypeOfStatus getQuestionStatus() {
    return status;
  }


  /**
   * Return the answer of the question.
   *
   * @return the answer of the question
   */
  @Override
  public String getAnswer() {
    return answer;
  }
}
