/**
 * WordSeacherTree for Algorithm Engineer, has an updateDefinition, suggestWord function and a getWord function 
 * @author Danny
 *
 * @param <ObjectType>
 */
public class WordSearcherTree<ObjectType> extends RedBlackTree<IWord>
    implements IWordSearcherTree<IWord> {

  /**
   * Method that updates or adds a new definition to a currently existing word. If the word does not
   * exist, then activate the word suggestion function and ask for the user to enter a new word.
   * 
   * @param Word       word whose definition will be updated
   * @param Definition definition to be updated to
   */
  @Override
  public void updateDefinition(IWord word, String Definition) {
    if (this.contains(word)) {
      // null references will not be stored within this tree
      if (word == null)
        throw new NullPointerException("null is not an element in the dictionary");
      this.updateHelper(word, root, Definition);
    } else {
      suggestWord(word);
    }
  }

  /**
   * Private helper method that searches through the tree and finds the specified word in the
   * dictionary, keep in mind only the word itself has to be the same for it to be found and have
   * its definition updated
   * 
   * @param word     Word object that is being looked for
   * @param currNode current node that is being checked (should initially be the root)
   * @param def      new definition
   */
  protected void updateHelper(IWord word, Node<IWord> currNode, String def) {
    int compare = word.compareTo(currNode.data);
    if (compare < 0) {
      // go left in the tree
      updateHelper(word, currNode.leftChild, def);
    } else if (compare > 0) {
      // go right in the tree
      updateHelper(word, currNode.rightChild, def);
    } else {
      currNode.data.setDefinition(def);
    }
  }


  /**
   * If the word that is being searched does not exist, then this method will run and ask the user
   * if they meant a similar word to what he/she was searching. In our case, we would print the last
   * visited node before reaching null.
   */
  @Override
  public void suggestWord(IWord word) {
    // null references will not be stored within this tree
    if (word == null)
      throw new NullPointerException("null is not an element in the dictionary");
    System.out.println("Did you mean: " + suggestWordHelper(word, this.root).getWord() + "?");

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

  /**
   * Returns the IWord object with the matching name as input. Throws NullPointerException if the word doesn't exist in the dictionary
   * @param word word to look for
   * @return IWord object with the same name
   */
  public IWord getWord(String word) {
    Word target = new Word(word);
    if(this.contains(target)) {
      return getHelper(target, this.root);
    }
    else {
      throw new NullPointerException(word + "is not in the dictionary");
    }
  }

  /**
   * Helper method that searches through the tree for the IWord, assumes the word is in the dictionary
   * 
   * @param word input word to search for
   * @param currNode current node that is being checked
   * @return the IWord object with the same name
   */
  protected IWord getHelper(IWord word, Node<IWord> currNode) {
    int compare = word.compareTo(currNode.data);
    if (compare < 0) {
      return getHelper(word, currNode.leftChild);
    } else if (compare > 0) {
      return getHelper(word, currNode.rightChild);
    } else {
      return currNode.data;
    }
  }
}
