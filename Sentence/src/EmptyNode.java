import java.util.function.Function;

/**
 * This class represent a empty node. It implements interface Sentence.
 */
public class EmptyNode implements Sentence {

  /**
   * Return 0 when it's an EmptyNode.
   *
   * @return 0.
   */
  @Override
  public int getNumberOfWords() {
    return 0;
  }

  /**
   * Find the longest word in the sentence. Return "" which represents an EmptyNode.
   *
   * @return "".
   */
  @Override
  public String longestWord() {
    return "";
  }

  /**
   * Return "" which represents an EmptyNode in String.
   *
   * @return "".
   */
  @Override
  public String toString() {
    return "";
  }

  /**
   * Return a new EmptyNode.
   *
   * @return a new EmptyNode.
   */
  @Override
  public Sentence clone() {
    return new EmptyNode();
  }

  /**
   * If it's an EmptyNode, it should be replaced by the other Sentence when they're merged.
   *
   * @param other another given sentence.
   * @return Sentence other.
   */
  @Override
  public Sentence merge(Sentence other) {
    return other;
  }

  /**
   * This method can count the number of corresponding items.
   *
   * @param countHelper countHelper function to determine whether the item is needed
   * @return the number of corresponding items.
   */
  @Override
  public int fold(Function<Sentence, Boolean> countHelper) {
    return 0;
  }

  /**
   * Empty Node has no word with "z", return false.
   *
   * @return false
   */
  @Override
  public boolean countHelper() {
    return false;
  }

  /**
   * Return false when it is an empty node.
   *
   * @return false
   */
  @Override
  public boolean countPuncHelper() {
    return false;
  }


  @Override
  public Sentence code() {
    return this;
  }

}
