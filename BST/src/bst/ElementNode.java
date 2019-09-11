package bst;

import java.util.LinkedList;
import java.util.List;

/**
 * This class represents an element node in the generic bst implementation.
 */
public class ElementNode<T extends Comparable<T>> implements Node<T> {
  /**
   * element: the content of this node. children: a list of children node of this node.
   */
  private T element;
  private List<Node<T>> children;

  /**
   * Construct an element node with the given content. And it has 2 empty children nodes.
   *
   * @param element the content.
   */
  public ElementNode(T element) {
    this.element = element;
    children = new LinkedList<>();
    children.add(new EmptyNode<>());
    children.add(new EmptyNode<>());
  }

  /**
   * Help to complete the method add in class BSTImpl. Add the object to the subtree of this node.
   * If it's greater than this node, add it to the right subtree. If it's less, add to the left
   * subtree.
   *
   * @param obj the object to be added.
   * @return the mutated node after adding.
   */
  @Override
  public Node<T> addHelp(T obj) {
    if (obj.compareTo(this.element) > 0) {
      this.children.set(1, this.children.get(1).addHelp(obj));
    } else if (obj.compareTo(this.element) < 0) {
      this.children.set(0, this.children.get(0).addHelp(obj));
    }
    return this;
  }

  /**
   * Help to complete the method present in class BSTImpl. Find the object in the subtree of this
   * node. If this object is greater than this node, find it in the right subtree. If it's less than
   * this object, find it in the left subtree.
   *
   * @param obj the object
   * @return true
   */
  @Override
  public boolean presentHelp(T obj) {
    if (obj.compareTo(this.element) > 0) {
      return this.children.get(1).presentHelp(obj);
    } else if (obj.compareTo(this.element) < 0) {
      return this.children.get(0).presentHelp(obj);
    }
    return true;
  }

  /**
   * Help to complete the method minimum in the class BSTImpl. Visit the left subtree of this node.
   *
   * @return the minimum in the subtree of this node.
   */
  @Override
  public T minimumHelp() {
    if (this.children.get(0) instanceof EmptyNode) {
      return this.element;
    }
    return this.children.get(0).minimumHelp();

  }

  /**
   * Help to get the size of the BST. Count the left subtree, this node itself and the right
   * subtree.
   *
   * @return size of the subtree.
   */
  @Override
  public int count() {
    return 1 + this.children.get(0).count() + this.children.get(1).count();
  }

  /**
   * Help to complete the method rank in the class BSTImpl. If data is equals to this node, the rank
   * is 1 + size of the left subtree. If the data is greater than this node, the rank is 1 + size of
   * the left subtree + its rank in right subtree.
   *
   * @param data the given object.
   * @return its rank
   */
  @Override
  public int rank(T data) {
    if (data.compareTo(this.element) == 0) {
      return this.leftSize() + 1;
    } else if (data.compareTo(this.element) < 0) {
      return this.children.get(0).rank(data);
    } else {
      return this.children.get(1).rank(data) + this.leftSize() + 1;
    }
  }

  /**
   * Help to complete the method select in the class BSTImpl. If the given rank is equal to 1 + size
   * of the left subtree, return this node. If it's greater, select in the right subtree using the
   * rank x - 1 - size of left subtree. If it's less, select in the left subtree.
   *
   * @param x the given rank
   * @return corresponding object.
   */
  @Override
  public T select(int x) {
    int r = 1 + this.leftSize();
    if (r == x) {
      return this.element;
    } else if (r < x) {
      return this.children.get(1).select(x - r);
    } else {
      return this.children.get(0).select(x);
    }
  }

  /**
   * Private help method to get the size of the left subtree.
   *
   * @return the size of left subtree.
   */
  private int leftSize() {
    return this.children.get(0).count();
  }

}
