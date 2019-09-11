import java.util.function.Function;

/**
 * This class represent a word node. It implements interface Sentence.
 */
public class WordNode implements Sentence {

  private String word;
  private Sentence rest;

  /**
   * Construct a WordNode object with the given word, and rest.
   *
   * @param word the word of WordNode.
   * @param rest the rest of Sentence.
   */
  public WordNode(String word, Sentence rest) {
    this.word = word;
    this.rest = rest;
  }


  /**
   * Count this word and keep counting the rest of the sentence.
   *
   * @return the number of words
   */
  @Override
  public int getNumberOfWords() {
    return 1 + this.rest.getNumberOfWords();
  }

  /**
   * If this is the longest word in the sentence, return itself; otherwise, keep find the longest in
   * the rest of the sentence.
   *
   * @return the longest word in the sentence.
   */
  @Override
  public String longestWord() {
    if (word.length() > this.rest.longestWord().length()) {
      return word;
    } else {
      return this.rest.longestWord();
    }
  }

  /**
   * Convert the WordNode into formatted word String in a sentence.
   *
   * @return a formatted word in string.
   */
  @Override
  public String toString() {
    if (this.rest instanceof WordNode) {
      return this.word + " " + rest.toString();
    } else if (rest instanceof EmptyNode) {
      return this.word + ".";
    } else {
      return this.word + rest.toString();
    }
  }

  /**
   * Return a duplicate of a given sentence.
   *
   * @return a duplicate of a given sentence.
   */
  @Override
  public Sentence clone() {

    return new WordNode(this.word, this.rest.clone());
  }

  /**
   * If it's a WordNode, it shouldn't be the spot to hold the other sentence; Preserve it in the
   * merged list.
   *
   * @param other another given sentence.
   * @return a sentence that merged two sentences.
   */
  @Override
  public Sentence merge(Sentence other) {

    return new WordNode(word, this.rest.merge(other));

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
   * Determine whether the word has "Z".
   *
   * @return true if it has
   */
  @Override
  public boolean countHelper() {
    return word.contains("z") || word.contains("Z");
  }

  /**
   * Return false when the node is a word.
   *
   * @return false
   */
  @Override
  public boolean countPuncHelper() {
    return false;
  }

  /**
   * Turn the English sentence into Piglatin.
   *
   * @return the Piglatin sentence
   */
  @Override
  public Sentence code() {
    if ("AEIOUaeiou".contains(this.word.substring(0, 1))) {
      return new WordNode(word + "WAY", rest.code());
    }
    return new WordNode(word.substring(1) + word.substring(0, 1) + "AY", rest.code());
  }


}
