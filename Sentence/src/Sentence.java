import java.util.function.Function;

/**
 * This interface contains all the operations that all type of node should support.
 */
public interface Sentence {

  /**
   * Return the number of words of sentence.
   *
   * @return the number of words
   */
  int getNumberOfWords();

  /**
   * Return the longest word in the sentence.
   *
   * @return the longest word.
   */
  String longestWord();

  /**
   * This method will convert the sentence into one string. There is a space between every two
   * words, and no space between the last word and the end of this sentence. A period will be added
   * at the end with the lack of punctuation.
   *
   * @return a string.
   */
  String toString();

  /**
   * Return a copy of the sentence.
   *
   * @return a duplicate of a given sentence.
   */
  Sentence clone();

  /**
   * This method will merge two sentences into a single sentence, and the original lists should be
   * unchanged.
   *
   * @param other another given sentence.
   * @return a sentence that merge two sentences.
   */
  Sentence merge(Sentence other);


  /**
   * This method can help to count the word with "z".
   *
   * @return true if the word contains "z"; otherwise, return false
   */
  boolean countHelper();

  /**
   * This method can help to count the punctuations.
   *
   * @return true if the node is a punctuation
   */
  boolean countPuncHelper();

  /**
   * This method can count the number of corresponding items.
   *
   * @param countHelper countHelper function to determine whether the item is needed
   * @return the number of corresponding items.
   */
  int fold(Function<Sentence, Boolean> countHelper);

  /**
   * Encode a sentence.
   *
   * @return an English sentence in Piglatin
   */
  Sentence code();
}
