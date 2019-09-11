package bst;

/**
 * This class represents a generic binary search tree. it is a generalization of the BST interface.
 * It works with objects of classes that implements Comparable interface.
 */
public class BSTImpl<T extends Comparable<T>> implements BST<T> {
  /**
   * The root node of the BST.
   */
  private Node<T> root;


  /**
   * Construct a BST which has an empty node as the root.
   */
  public BSTImpl() {
    root = new EmptyNode<T>();
  }

  /**
   * Add a new object to the BST. When there is node is equal to the object to be added, exit. The
   * object have to be added to the tree at a proper position. It will be completed in the method
   * addHelp.
   *
   * @param obj the added object
   */
  @Override
  public void add(T obj) {
    if (!present(obj)) {
      root = root.addHelp(obj);
    }

  }

  /**
   * Return the size of the tree by counting all the nodes.
   *
   * @return the size.
   */
  @Override
  public int getSize() {

    return root.count();
  }

  /**
   * Traverse the tree to find if there is such an object.
   *
   * @param obj the object
   * @return true, if there is. Otherwise, return false.
   */
  @Override
  public boolean present(T obj) {

    return root.presentHelp(obj);
  }

  /**
   * Return the minimum in the tree, it must the one on the left. It will be obtain by the method
   * minimumHelp.
   *
   * @return the minimum.
   */
  @Override
  public T minimum() {
    return root.minimumHelp();
  }

  /**
   * Return the rank of the object, starting from 1. When there is no such an object in the tree,
   * return 0.
   *
   * @param data the object.
   * @return its rank
   */
  @Override
  public int rank(T data) {
    if (present(data)) {
      return root.rank(data);
    }
    return 0;
  }

  /**
   * Return the corresponding object according to the rank.
   *
   * @param x the rank.
   * @return the corresponding object
   */
  @Override
  public T select(int x) {
    return root.select(x);
  }
}
