
// --=400 RedBlackTree Activity File Header ==--
// Name: Danny Jiang
// CSL Username: danny
// Email: cjiang88@wisc.edu
// Lecture #: 002

import java.util.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Red-Black Tree implementation with a Node inner class for representing the nodes of the tree.
 * This tree has its insert method implemented.
 */
public class RedBlackTree<T extends Comparable<T>> implements SortedCollectionInterface<T> {

  /**
   * This class represents a node holding a single value within a binary tree the parent, left, and
   * right child references are always maintained.
   */
  protected static class Node<T> {
    public T data;
    public int blackHeight;
    public Node<T> parent; // null for root node
    public Node<T> leftChild;
    public Node<T> rightChild;

    public Node(T data) {
      this.data = data;
      this.blackHeight = 0;
    }

    /**
     * @return true when this node has a parent and is the left child of that parent, otherwise
     *         return false
     */
    public boolean isLeftChild() {
      return parent != null && parent.leftChild == this;
    }

    public boolean isRightChild() {
      return parent != null && parent.rightChild == this;
    }

  }

  protected Node<T> root; // reference to root node of tree, null when empty
  protected int size = 0; // the number of values in the tree

  /**
   * Helper method to return the uncle (parent's sibling) node
   * 
   * @param node node whose uncle will be returned
   * @return the specified node's uncle node
   */
  private Node<T> uncle(Node<T> node) {
    // Left child
    if (node.parent.isLeftChild()) {
      return node.parent.parent.rightChild;
    }

    // Right child
    if (node.parent.isRightChild()) {
      return node.parent.parent.leftChild;
    }
    return null;
  }

  /**
   * Method that enforces the RedBlackTree properties after an insert of a new red node
   * 
   * @param newNode inserted node
   */
  protected void enforceRBTreePropertiesAfterInsert(Node<T> newNode) {
    if (newNode != null) {
      if (newNode.parent != null) {
        // Red parent and red child violation
        if (newNode.parent.blackHeight == 0 && newNode.blackHeight == 0) {
          if (uncle(newNode) != null) {
            switch (uncle(newNode).blackHeight) {
              // red uncle
              case 0:
                uncle(newNode).blackHeight = 1;
                newNode.parent.blackHeight = 1;
                newNode.parent.parent.blackHeight = 0;
                // Checks if a new violation has appeared
                if (newNode.parent.parent.parent != null) {
                  if (newNode.parent.parent.parent.blackHeight == 0) {
                    this.enforceRBTreePropertiesAfterInsert(newNode.parent.parent);
                  }
                }
                break;
              // black uncle
              case 1:
                if (newNode.isLeftChild() == uncle(newNode).isLeftChild()) {
                  // case 1: same side as uncle
                  rotate(newNode, newNode.parent);
                  newNode.blackHeight = 1;
                  newNode.parent.blackHeight = 0;
                  rotate(newNode, newNode.parent);
                } else {
                  // case 2: opposite side as uncle
                  newNode.parent.blackHeight = 1;
                  newNode.parent.parent.blackHeight = 0;
                  rotate(newNode.parent, newNode.parent.parent);
                }
                break;
              default:
            }
          }
          // null uncle (black)
          else {
            if (newNode.isLeftChild() != newNode.parent.isLeftChild()) {
              // case 1: same side as null uncle
              rotate(newNode, newNode.parent);
              newNode.blackHeight = 1;
              newNode.parent.blackHeight = 0;
              rotate(newNode, newNode.parent);
            } else {
              // case 2: opposite side as null uncle
              newNode.parent.blackHeight = 1;
              newNode.parent.parent.blackHeight = 0;
              rotate(newNode.parent, newNode.parent.parent);
            }

          }
        }
      }
    }
  }

  /**
   * Performs a naive insertion into a binary search tree: adding the input data value to a new node
   * in a leaf position within the tree. After this insertion, no attempt is made to restructure or
   * balance the tree. This tree will not hold null references, nor duplicate data values.
   * 
   * @param data to be added into this binary search tree
   * @return true if the value was inserted, false if not
   * @throws NullPointerException     when the provided data argument is null
   * @throws IllegalArgumentException when the newNode and subtree contain equal data references
   */
  @Override
  public boolean insert(T data) throws NullPointerException, IllegalArgumentException {
    // null references cannot be stored within this tree
    if (data == null) {
      root.blackHeight = 1;
      throw new NullPointerException("This RedBlackTree cannot store null references.");
    }
    Node<T> newNode = new Node<>(data);
    if (root == null) {
      root = newNode;
      size++;
      root.blackHeight = 1;
      return true;
    } // add first node to an empty tree
    else {
      root.blackHeight = 1;
      boolean returnValue = insertHelper(newNode, root); // recursively insert into subtree
      if (returnValue)
        size++;
      else {
        root.blackHeight = 1;
        throw new IllegalArgumentException("This RedBlackTree already contains that value.");
      }
      root.blackHeight = 1;
      return returnValue;
    }
  }

  /**
   * Recursive helper method to find the subtree with a null reference in the position that the
   * newNode should be inserted, and then extend this tree by the newNode in that position.
   * 
   * @param newNode is the new node that is being added to this tree
   * @param subtree is the reference to a node within this tree which the newNode should be inserted
   *                as a descenedent beneath
   * @return true is the value was inserted in subtree, false if not
   */
  private boolean insertHelper(Node<T> newNode, Node<T> subtree) {
    int compare = newNode.data.compareTo(subtree.data);
    // do not allow duplicate values to be stored within this tree
    if (compare == 0)
      return false;

    // store newNode within left subtree of subtree
    else if (compare < 0) {
      if (subtree.leftChild == null) { // left subtree empty, add here
        subtree.leftChild = newNode;
        newNode.parent = subtree;
        this.enforceRBTreePropertiesAfterInsert(newNode);
        return true;
        // otherwise continue recursive search for location to insert
      } else
        return insertHelper(newNode, subtree.leftChild);
    }

    // store newNode within the right subtree of subtree
    else {
      if (subtree.rightChild == null) { // right subtree empty, add here
        subtree.rightChild = newNode;
        newNode.parent = subtree;
        this.enforceRBTreePropertiesAfterInsert(newNode);
        return true;
        // otherwise continue recursive search for location to insert
      } else
        return insertHelper(newNode, subtree.rightChild);
    }
  }

  /**
   * Performs the rotation operation on the provided nodes within this tree. When the provided child
   * is a leftChild of the provided parent, this method will perform a right rotation. When the
   * provided child is a rightChild of the provided parent, this method will perform a left
   * rotation. When the provided nodes are not related in one of these ways, this method will throw
   * an IllegalArgumentException.
   * 
   * @param child  is the node being rotated from child to parent position (between these two node
   *               arguments)
   * @param parent is the node being rotated from parent to child position (between these two node
   *               arguments)
   * @throws IllegalArgumentException when the provided child and parent node references are not
   *                                  initially (pre-rotation) related that way
   */
  private void rotate(Node<T> child, Node<T> parent) throws IllegalArgumentException {
    if (parent.leftChild != null) {
      if (parent.leftChild.equals(child)) {
        // If the child is the left child of the parent
        // Creates a temporary node that is the same as the node that is less than the
        // parent
        // but greater than the child
        RedBlackTree.Node<T> tempNode = child.rightChild;

        // Checks if there is a tree above the parent
        if (root != parent) {
          // If there is, change the child's parent to that tree
          if (parent.isLeftChild()) {
            parent.parent.leftChild = child;
          } else {
            parent.parent.rightChild = child;
          }
          child.parent = parent.parent;
        } else {
          // If parent is the root of the tree, then change child to the new root
          root = child;
        }

        // Rotate right
        parent.leftChild = tempNode;
        child.rightChild = parent;
        parent.parent = child;
      } else if (parent.rightChild.equals(child)) {
        // If the child is the right child of the parent
        // Creates a temporary node that is the same as the node that is greater than
        // the parent
        // but less than the child
        RedBlackTree.Node<T> tempNode = child.leftChild;

        // Checks if there is a tree above the parent
        if (!root.equals(parent)) {
          // If there is, change the child's parent to that tree
          if (parent.isLeftChild()) {
            parent.parent.leftChild = child;
          } else {
            parent.parent.rightChild = child;
          }
          child.parent = parent.parent;
        } else {
          // If parent is the root of the tree, then change child to the new root
          root = child;
        }
        // Rotate left
        parent.rightChild = tempNode;
        child.leftChild = parent;
        parent.parent = child;
      }
    }

    else if (parent.rightChild != null) {
      if (parent.rightChild.equals(child)) {
        // If the child is the right child of the parent
        // Creates a temporary node that is the same as the node that is greater than
        // the parent
        // but less than the child
        RedBlackTree.Node<T> tempNode = child.leftChild;

        // Checks if there is a tree above the parent
        if (!root.equals(parent)) {
          // If there is, change the child's parent to that tree
          if (parent.isLeftChild()) {
            parent.parent.leftChild = child;
          } else {
            parent.parent.rightChild = child;
          }
          child.parent = parent.parent;
        } else {
          // If parent is the root of the tree, then change child to the new root
          root = child;
        }

        // Rotate left
        parent.rightChild = tempNode;
        child.leftChild = parent;
        parent.parent = child;
      } else if (parent.leftChild.equals(child)) {
        // If the child is the left child of the parent

        // Creates a temporary node that is the same as the node that is less than the
        // parent
        // but greater than the child
        RedBlackTree.Node<T> tempNode = child.rightChild;

        // Checks if there is a tree above the parent
        if (root != parent) {
          // If there is, change the child's parent to that tree
          if (parent.isLeftChild()) {
            parent.parent.leftChild = child;
          } else {
            parent.parent.rightChild = child;
          }
          child.parent = parent.parent;
        } else {
          // If parent is the root of the tree, then change child to the new root
          root = child;
        }

        // Rotate right
        parent.leftChild = tempNode;
        child.rightChild = parent;
        parent.parent = child;
      }
    } else {
      // If the parent's both children are null
      throw new IllegalArgumentException(
          "The two node references are not parent-child relationship");
    }

    // TODO: Implement this method.
  }

  /**
   * Get the size of the tree (its number of nodes).
   * 
   * @return the number of nodes in the tree
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * Method to check if the tree is empty (does not contain any node).
   * 
   * @return true of this.size() return 0, false if this.size() > 0
   */
  @Override
  public boolean isEmpty() {
    return this.size() == 0;
  }

  /**
   * Checks whether the tree contains the value *data*.
   * 
   * @param data the data value to test for
   * @return true if *data* is in the tree, false if it is not in the tree
   */
  @Override
  public boolean contains(T data) {
    // null references will not be stored within this tree
    if (data == null)
      throw new NullPointerException("This RedBlackTree cannot store null references.");
    return this.containsHelper(data, root);
  }

  /**
   * Recursive helper method that recurses through the tree and looks for the value *data*.
   * 
   * @param data    the data value to look for
   * @param subtree the subtree to search through
   * @return true of the value is in the subtree, false if not
   */
  private boolean containsHelper(T data, Node<T> subtree) {
    if (subtree == null) {
      // we are at a null child, value is not in tree
      return false;
    } else {
      int compare = data.compareTo(subtree.data);
      if (compare < 0) {
        // go left in the tree
        return containsHelper(data, subtree.leftChild);
      } else if (compare > 0) {
        // go right in the tree
        return containsHelper(data, subtree.rightChild);
      } else {
        // we found it :)
        return true;
      }
    }
  }

  /**
   * Returns an iterator over the values in in-order (sorted) order.
   * 
   * @return iterator object that traverses the tree in in-order sequence
   */
  @Override
  public Iterator<T> iterator() {
    // use an anonymous class here that implements the Iterator interface
    // we create a new on-off object of this class everytime the iterator
    // method is called
    return new Iterator<T>() {
      // a stack and current reference store the progress of the traversal
      // so that we can return one value at a time with the Iterator
      Stack<Node<T>> stack = null;
      Node<T> current = root;

      /**
       * The next method is called for each value in the traversal sequence. It returns one value at
       * a time.
       * 
       * @return next value in the sequence of the traversal
       * @throws NoSuchElementException if there is no more elements in the sequence
       */
      public T next() {
        // if stack == null, we need to initialize the stack and current element
        if (stack == null) {
          stack = new Stack<Node<T>>();
          current = root;
        }
        // go left as far as possible in the sub tree we are in un8til we hit a null
        // leaf (current is null), pushing all the nodes we fund on our way onto the
        // stack to process later
        while (current != null) {
          stack.push(current);
          current = current.leftChild;
        }
        // as long as the stack is not empty, we haven't finished the traversal yet;
        // take the next element from the stack and return it, then start to step down
        // its right subtree (set its right sub tree to current)
        if (!stack.isEmpty()) {
          Node<T> processedNode = stack.pop();
          current = processedNode.rightChild;
          return processedNode.data;
        } else {
          // if the stack is empty, we are done with our traversal
          throw new NoSuchElementException("There are no more elements in the tree");
        }

      }

      /**
       * Returns a boolean that indicates if the iterator has more elements (true), or if the
       * traversal has finished (false)
       * 
       * @return boolean indicating whether there are more elements / steps for the traversal
       */
      public boolean hasNext() {
        // return true if we either still have a current reference, or the stack
        // is not empty yet
        return !(current == null && (stack == null || stack.isEmpty()));
      }

    };
  }

  /**
   * This method performs an inorder traversal of the tree. The string representations of each data
   * value within this tree are assembled into a comma separated string within brackets (similar to
   * many implementations of java.util.Collection, like java.util.ArrayList, LinkedList, etc). Note
   * that this RedBlackTree class implementation of toString generates an inorder traversal. The
   * toString of the Node class class above produces a level order traversal of the nodes / values
   * of the tree.
   * 
   * @return string containing the ordered values of this tree (in-order traversal)
   */
  public String toInOrderString() {
    // use the inorder Iterator that we get by calling the iterator method above
    // to generate a string of all values of the tree in (ordered) in-order
    // traversal sequence
    Iterator<T> treeNodeIterator = this.iterator();
    StringBuffer sb = new StringBuffer();
    sb.append("[ ");
    if (treeNodeIterator.hasNext())
      sb.append(treeNodeIterator.next());
    while (treeNodeIterator.hasNext()) {
      T data = treeNodeIterator.next();
      sb.append(", ");
      sb.append(data.toString());
    }
    sb.append(" ]");
    return sb.toString();
  }

  /**
   * This method performs a level order traversal of the tree rooted at the current node. The string
   * representations of each data value within this tree are assembled into a comma separated string
   * within brackets (similar to many implementations of java.util.Collection). Note that the Node's
   * implementation of toString generates a level order traversal. The toString of the RedBlackTree
   * class below produces an inorder traversal of the nodes / values of the tree. This method will
   * be helpful as a helper for the debugging and testing of your rotation implementation.
   * 
   * @return string containing the values of this tree in level order
   */
  public String toLevelOrderString() {
    String output = "[ ";
    LinkedList<Node<T>> q = new LinkedList<>();
    q.add(this.root);
    while (!q.isEmpty()) {
      Node<T> next = q.removeFirst();
      if (next.leftChild != null)
        q.add(next.leftChild);
      if (next.rightChild != null)
        q.add(next.rightChild);
      output += next.data.toString();
      if (!q.isEmpty())
        output += ", ";
    }
    return output + " ]";
  }

  @Override
  public String toString() {
    return "level order: " + this.toLevelOrderString() + "\nin order: " + this.toInOrderString();
  }

  public List<T> search(T data) {
    List<T> result = new ArrayList<>();
    try {
      result.add(searchHelper(this.root, data));
    } catch (NoSuchElementException e) {
      // do nothing
    }
    return result;
  }

  private T searchHelper (Node<T> node, T data) throws NoSuchElementException {
    if (node == null) {
      throw new NoSuchElementException("Data not found");
    }
    if (node.data.compareTo(data) == 0) {
      return node.data;
    }
    if (node.data.compareTo(data) > 0) {
      return searchHelper(node.leftChild, data);
    } else {
      return searchHelper(node.rightChild, data);
    }
  }

  /**
   * Test method that tests the scenario where a case three causes further violations for the tree
   * above. In this case, after inserting 8, case 3 is invoked but then causes an additional case 1
   * to be invoked further up in the tree.
   */
  @Test
  public void testEnforceRBTreePropertiesAfterInsert1() {
    RedBlackTree<Integer> test = new RedBlackTree<Integer>();
    // Inserts 1 to 8 into the RB Tree
    for (int i = 1; i <= 8; i++) {
      test.insert(i);
    }
    // Checks to see if the correct tree is outputed
    Assertions.assertEquals("[ 4, 2, 6, 1, 3, 5, 7, 8 ]", test.toLevelOrderString());
  }

  /**
   * Test method that tests the case of the new node being on the same side of a black parent
   * sibling.
   */
  @Test
  public void testEnforceRBTreePropertiesAfterInsert2() {
    RedBlackTree<Double> test = new RedBlackTree<Double>();
    test.insert(2.0);
    test.insert(3.0);
    test.insert(2.5);
    // Checks to see if the correct tree is outputed
    Assertions.assertEquals("[ 2.5, 2.0, 3.0 ]", test.toLevelOrderString());
  }

  /**
   * Test method that tests two cases, insert a new violation node that is has both a red parent and
   * a red parent's sibling; also insert a new violation node on the opposite side of a null
   * parent's sibling
   */
  @Test
  public void testEnforceRBTreePropertiesAfterInsert3() {
    RedBlackTree<Double> test = new RedBlackTree<Double>();
    test.insert(2.0);
    test.insert(3.0);
    test.insert(2.5);
    // inserts a red node under a red parent as well as a red parent's sibling.
    test.insert(4.8);
    System.out.println(test.toLevelOrderString());
    
    // tests the data value and color of each node
    // root node (2.5 black)
    Assertions.assertEquals(1, test.root.blackHeight);
    Assertions.assertEquals(2.5, test.root.data);

    // left child node (2.0 black)
    Assertions.assertEquals(1, test.root.leftChild.blackHeight);
    Assertions.assertEquals(2.0, test.root.leftChild.data);

    // right child node (3.0 black)
    Assertions.assertEquals(1, test.root.rightChild.blackHeight);
    Assertions.assertEquals(3.0, test.root.rightChild.data);

    // right child of right child node (4.8 red)
    Assertions.assertEquals(0, test.root.rightChild.rightChild.blackHeight);
    Assertions.assertEquals(4.8, test.root.rightChild.rightChild.data);

    // inserts a red node under a red node but on the opposite side null parent's sibling.
    test.insert(100.1);

    // root node (2.5 black)
    Assertions.assertEquals(1, test.root.blackHeight);
    Assertions.assertEquals(2.5, test.root.data);

    // left child node (2.0 black)
    Assertions.assertEquals(1, test.root.leftChild.blackHeight);
    Assertions.assertEquals(2.0, test.root.leftChild.data);

    // right child node (4.8 black)
    Assertions.assertEquals(1, test.root.rightChild.blackHeight);
    Assertions.assertEquals(4.8, test.root.rightChild.data);

    // right child of right child node (100.1 red)
    Assertions.assertEquals(0, test.root.rightChild.rightChild.blackHeight);
    Assertions.assertEquals(100.1, test.root.rightChild.rightChild.data);

    // left child of right child node (3.0 red)
    Assertions.assertEquals(0, test.root.rightChild.leftChild.blackHeight);
    Assertions.assertEquals(3.0, test.root.rightChild.leftChild.data);
  }
}
