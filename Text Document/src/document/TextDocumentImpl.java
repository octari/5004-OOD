package document;

import java.util.Scanner;

/**
 * This class represents a TextDocument. It implements all the methods in the interface
 * TextDocument, getText, countWords and wrap.
 */
public class TextDocumentImpl implements TextDocument {
  /**
   * text: the text itself. Punctuation is attached to the word it follow in the text. Multiple
   * spaces between words is allowed.
   */
  private String text;

  /**
   * Construct a TextDocumentImpl with the given string text.
   *
   * @param text the string text
   */
  public TextDocumentImpl(String text) {
    this.text = text;
  }

  /**
   * Count the words in the text. If there is only one punctuation in the text, count it as word.
   * Characters separated by a spaces can be counted as a word.
   *
   * @return the number of the word
   */
  @Override
  public int getWordCount() {
    int count = 0;
    Scanner input = new Scanner(text);
    while (input.hasNext()) {
      input.next();
      count++;
    }
    return count;
  }

  /**
   * Return the text itself.
   *
   * @return the text
   */
  @Override
  public String getText() {
    return text;
  }

  /**
   * A help function used in method wrap. Add a word into the line. When there are enough places to
   * hold to word, add it directly. Otherwise, it has to be separately added, connected by a
   * hyphen.
   *
   * @param result      the wrapped text so far.
   * @param line        the current line to add the word
   * @param word        the added word
   * @param columnWidth the certain column width
   */
  private void addWord(StringBuilder result, StringBuilder line, String word, int columnWidth) {
    // There are enough places to hold to word, add it directly
    if (line.length() + word.length() <= columnWidth) {
      line.append(word);

    }

    // Otherwise, it has to be separately added.
    else {
      int n = columnWidth - line.length();
      line.append(word, 0, n - 1).append("-\n");
      result.append(line);
      line.delete(0, line.capacity());
      addWord(result, line, word.substring(n - 1), columnWidth);

    }
  }

  /**
   * Break text into lines of certain width. All the words remain the same and separated by a single
   * space.
   *
   * @param columnWidth the given column width
   * @return the TextDocument with wrapped text.
   * @throws IllegalArgumentException when the given column width is less than 1 or equals to 1 if
   *                                  words' lengths are greater than 1.
   */
  @Override
  public TextDocument wrap(int columnWidth) throws IllegalArgumentException {
    if (columnWidth < 1) {
      throw new IllegalArgumentException("Column width should be greater than 0.");
    }


    StringBuilder result = new StringBuilder();
    StringBuilder line = new StringBuilder();
    Scanner input = new Scanner(text);
    while (input.hasNext()) {
      String word = input.next();

      // When word length is greater than 1, and the column width is 1, it fails to wrap it.
      if (columnWidth == 1 && word.length() > 1) {
        throw new IllegalArgumentException("Column width should be greater than 1.");
      }

      if (line.length() < columnWidth) {

        int n = line.length() + word.length() - columnWidth;

        // After adding the word, the length of line will be greater than the column width.
        if (n > 0) {

          // Only one/two place left in the line. Can't hold part of the word and "-".
          // Add the fit space instead of adding the word.
          if (word.length() - n <= 2) {
            result.append(line).append(" \n");

            line = new StringBuilder();
            addWord(result, line, word, columnWidth);
            continue;
          }

          // Add the word separately.
          else {
            if (line.length() != 0) {
              line.append(" ");
            }
            addWord(result, line, word, columnWidth);

            continue;
          }

        }

        // After adding the word, the length of line will no greater than the column width.
        // Add it directly.
        else {
          // Avoid heading space.
          if (line.length() != 0) {
            line.append(" ");
          }
          addWord(result, line, word, columnWidth);
          if (line.length() == columnWidth) {
            line.append("\n");
            result.append(line);
            line = new StringBuilder();
          }
          continue;
        }

      }

      if (line.length() == columnWidth) {
        line.append("\n");
        result.append(line);
        line = new StringBuilder();
        addWord(result, line, word, columnWidth);
      }

    }
    result.append(line);
    return new TextDocumentImpl(result.toString().trim());
  }

}
