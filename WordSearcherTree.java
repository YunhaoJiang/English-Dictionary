import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * WordSearcherTree for Algorithm Engineer, has an updateDefinition, suggestWord function and a
 * getWord function
 *
 * @param <ObjectType>
 * @author Danny
 */
public class WordSearcherTree<ObjectType> extends RedBlackTree<IWord>
    implements IWordSearcherTree<IWord> {

  /**
   * Method that updates or adds a new definition to a currently existing word. If the word does not
   * exist, then activate the word suggestion function and ask for the user to enter a new word.
   *  @param word       word whose definition will be updated
   * @param Definition definition to be updated to
   * @return
   */
  @Override
  public boolean updateDefinition(IWord word, String Definition) {
    /*if (this.contains(word)) {
      // null references will not be stored within this tree
      if (word == null)
        throw new NullPointerException("null is not an element in the dictionary");
      this.updateHelper(word, root, Definition);
    } else {
      suggestWord(word);
    }*/
    try {
      List<IWord> list = this.get(word);
      if (list.size() == 1) {
        for (IWord resultWord : list) {
          resultWord.setDefinition(Definition);
          resultWord.setUserGenerated(true);
        }
      }
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  /**
   * If the word that is being searched does not exist, then this method will run and ask the user
   * if they meant a similar word to what he/she was searching. In our case, we would print the last
   * visited node before reaching null.
   * @return
   */
  @Override
  public IWord suggestWord(IWord word) {
    // null references will not be stored within this tree
    if (word == null)
      throw new NullPointerException("null is not an element in the dictionary");
    return this.suggestWordHelper(word, root);

  }



  /**
   * Private helper method that searches through the tree and finds the node right before specified
   * word, keep in mind only the word itself has to be the same for it to be found and have its
   * definition updated, and the actual search itself leads to null (since the word is not in the
   * dictionary)
   *
   * @param word     word that is going to have a similar word suggested
   * @param currNode current node that is being checked (initially root)
   * @return the word right before the search hits null
   */
  protected IWord suggestWordHelper(IWord word, Node<IWord> currNode) {
    int compare = word.compareTo(currNode.data);
    if (compare < 0) {
      if (currNode.leftChild == null) {
        return currNode.data;
      } else {
        return suggestWordHelper(word, currNode.leftChild);
      }
    } else {
      if (currNode.rightChild == null) {
        return currNode.data;
      } else {
        return suggestWordHelper(word, currNode.rightChild);
      }
    }
  }

  @Override
  public List<IWord> searchByStart(IWord searchWord) {
    Node<IWord> firstNode = this.findFirstAppearance(root, searchWord);
    if (firstNode == null) {
      return new ArrayList<>();
    }
    return this.findAllContains(firstNode, searchWord);
  }

  public Node<IWord> findFirstAppearance(Node<IWord> node, IWord data) {
    if (node == null) {
      return null;
    }
    if (node.data.getWord().startsWith(data.getWord())) {
      return node;
    }
    if (node.data.compareTo(data) > 0) {
      return findFirstAppearance(node.leftChild, data);
    } else {
      return findFirstAppearance(node.rightChild, data);
    }
  }

  private List<IWord> findAllContains(Node<IWord> currNode, IWord searchWord) {
    List<IWord> result = new ArrayList<>();
    if (currNode == null) {
      return result;
    }
    if (currNode.data.getWord().startsWith(searchWord.getWord())) {
      result.add(currNode.data);
    }
    result.addAll(findAllContains(currNode.leftChild, searchWord));
    result.addAll(findAllContains(currNode.rightChild, searchWord));
    return result;
  }
}
