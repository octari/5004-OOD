package document;

/**
 * This interface represents a text document. It contains the context itself and methods to count
 * words and wrap the text.
 */
public interface TextDocument {
  /**
   * Count the words in the text.
   *
   * @return the number of words in the text.
   */
  int getWordCount();

  /**
   * Return the text itself.
   *
   * @return the text
   */
  String getText();

  /**
   * Break text into lines of certain width. All the words remain the same and separated by a single
   * space.
   *
   * @param columnWidth the given column width
   * @return the TextDocument with wrapped text.
   * @throws IllegalArgumentException when the given column width is less than 1 or equals to 1 if
   *                                  words' lengths are greater than 1.
   */
  TextDocument wrap(int columnWidth) throws IllegalArgumentException;
}
