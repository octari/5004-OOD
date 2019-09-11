package bst;

/**
 * This class represents an empty node of the generic bst implementation.
 */
public class EmptyNode<T extends Comparable<T>> implements Node<T> {

  /**
   * Replace the empty node with the object.
   *
   * @param obj the object to be added.
   * @return the mutated node after adding.
   */
  @Override
  public Node<T> addHelp(T obj) {
    return new ElementNode<>(obj);
  }

  /**
   * Didn't find the object in the tree.
   *
   * @param obj the object
   * @return false.
   */
  @Override
  public boolean presentHelp(T obj) {
    return false;
  }

  /**
   * Can't reach the empty node.
   *
   * @return null
   */
  @Override
  public T minimumHelp() {
    return null;
  }

  /**
   * There is no node.
   *
   * @return 0
   */
  @Override
  public int count() {
    return 0;
  }

  /**
   * There is no such an object in the bst.
   *
   * @param data the given object.
   * @return 0
   */
  @Override
  public int rank(T data) {
    return 0;
  }

  /**
   * The rank is invalid.
   *
   * @param x the given rank
   * @return null
   */
  @Override
  public T select(int x) {
    return null;
  }
}
