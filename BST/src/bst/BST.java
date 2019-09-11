package bst;

/**
 * This generic interface represents a binary search tree ADT.
 */
public interface BST<T> {

  /**
   * Add a new object to the BST.
   *
   * @param obj the added object
   */
  void add(T obj);

  /**
   * Return the size of the BST.
   *
   * @return the size
   */
  int getSize();

  /**
   * Determine whether the object is in the BST.
   *
   * @param obj the object
   * @return true if it is, otherwise, return false.
   */
  boolean present(T obj);

  /**
   * Return the minimum in the tree.
   *
   * @return the minimum
   */
  T minimum();

  /**
   * Return the rank of this object in the tree.
   *
   * @param data the object.
   * @return its rank
   */
  int rank(T data);

  /**
   * Return the object using the given rank.
   *
   * @param x the rank.
   * @return the corresponding object
   */
  T select(int x);
}
