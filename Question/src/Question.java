/**
 * This interface contains all the operations that all type of questions should support.
 */
public interface Question {

  /**
   * Return the text of the question itself.
   *
   * @return the text of the question
   */
  String getQuestionText();

  /**
   * Return the status of the question, required or optional.
   *
   * @return the status of the question, TypeOfStatus
   */
  TypeOfStatus getQuestionStatus();

  /**
   * Return the type of the question, Yes/No, Essay-form and Likert.
   *
   * @return the type of the question, String
   */
  String getQuestionType();

  /**
   * Return the answer of the question.
   *
   * @return the answer of the question
   */
  String getAnswer();

  /**
   * Answer the question.
   *
   * @param a the answer users enter, String
   */
  void answer(String a);
}
