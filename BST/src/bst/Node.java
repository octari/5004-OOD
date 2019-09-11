package bst;

/**
 * This generic interface represents all the operations to be supported by a BST of objects of type
 * T.
 */
public interface Node<T extends Comparable<T>> {

  /**
   * Help to complete the method add in class BSTImpl. Add the object to the subtree of this node.
   * If it's greater than this node, add it to the right subtree. If it's less, add to the left
   * subtree.
   *
   * @param obj the object to be added.
   * @return the mutated node after adding.
   */
  Node<T> addHelp(T obj);

  /**
   * Help to complete the method present in class BSTImpl. Find the object in the subtree of this
   * node. If this object is greater than this node, find it in the right subtree. If it's less than
   * this object, find it in the left subtree.
   *
   * @param obj the object
   * @return true
   */
  boolean presentHelp(T obj);

  /**
   * Help to complete the method minimum in the class BSTImpl. Visit the left subtree of this node.
   *
   * @return the minimum in the subtree of this node.
   */
  T minimumHelp();

  /**
   * Help to get the size of the BST. Count the left subtree, this node itself and the right
   * subtree.
   *
   * @return size of the subtree.
   */
  int count();

  /**
   * Help to complete the method rank in the class BSTImpl. If data is equals to this node, the rank
   * is 1 + size of the left subtree. If the data is greater than this node, the rank is 1 + size of
   * the left subtree + its rank in right subtree.
   *
   * @param data the given object.
   * @return its rank
   */
  int rank(T data);

  /**
   * Help to complete the method select in the class BSTImpl. If the given rank is equal to 1 + size
   * of the left subtree, return this node. If it's greater, select in the right subtree using the
   * rank x - 1 - size of left subtree. If it's less, select in the left subtree.
   *
   * @param x the given rank
   * @return corresponding object.
   */
  T select(int x);
}
