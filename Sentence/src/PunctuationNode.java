import java.util.function.Function;

/**
 * This class represent a punctuation node. It implements interface Sentence.
 */
public class PunctuationNode implements Sentence {
  /**
   * The punctuation of the PunctuationNode.
   */
  private String punc;
  /**
   * The rest Sentence of the PunctuationNode.
   */
  private Sentence rest;

  /**
   * Construct a PunctuationNode object with the given punctuation, and rest.
   *
   * @param punc the word of sentence
   * @param rest the rest of sentence
   */
  public PunctuationNode(String punc, Sentence rest) {
    this.punc = punc;
    this.rest = rest;
  }

  /**
   * Punctuation shouldn't be counted as a word. Return the number of words of the rest.
   *
   * @return the number of words of rest
   */
  @Override
  public int getNumberOfWords() {
    return this.rest.getNumberOfWords();
  }

  /**
   * Convert the PunctuationNode into formatted punctuation String.
   *
   * @return the formatted string of punctuation
   */
  @Override
  public String toString() {
    if (rest instanceof EmptyNode) {
      return punc + rest.toString();
    } else {
      return punc + " " + rest.toString();
    }
  }

  /**
   * Punctuation shouldn't be considered as a word. Keep finding the longest word in the rest of the
   * sentence.
   *
   * @return the longest word in the rest sentence
   */
  @Override
  public String longestWord() {
    return this.rest.longestWord();
  }

  /**
   * Return a new PunctuationNode object same as this PunctuationNode object.
   *
   * @return a new PunctuationNode object same as this PunctuationNode object
   */
  @Override
  public Sentence clone() {
    return new PunctuationNode(this.punc, this.rest.clone());
  }

  /**
   * A method  that will merge two sentences into a single sentence. The merged list will preserve
   * all the punctuation.
   *
   * @param other Another given sentence.
   * @return A new PunctuationNode object that merge two sentences.
   */
  @Override
  public Sentence merge(Sentence other) {

    return new PunctuationNode(punc, this.rest.merge(other));
  }

  /**
   * This method can count the number of corresponding items.
   *
   * @param countHelper countHelper function to determine whether the item is needed
   * @return the number of corresponding items.
   */
  @Override
  public int fold(Function<Sentence, Boolean> countHelper) {
    if (countHelper.apply(this)) {
      return 1 + this.rest.fold(countHelper);
    }
    return this.rest.fold(countHelper);
  }

  /**
   * Punctuation Node has no word with "z", return false.
   *
   * @return false
   */
  @Override
  public boolean countHelper() {
    return false;
  }

  /**
   * Return true when the node is a punctuation.
   *
   * @return true
   */
  @Override
  public boolean countPuncHelper() {
    return true;
  }

  /**
   * The punctuation in the sentence will remain.
   *
   * @return the rest of the sentence in Piglatin
   */
  @Override
  public Sentence code() {
    return rest.code();
  }

}
