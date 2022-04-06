/**
 * This interface is implemented by a Red-Black Tree that is essentially a self-balancing tree which stores
 * specific types of objects. In the case of our English Dictionary Program, the tree will store
 * word / definition objects.
 * @param <ObjectType>> Type of object that is stored
 */
public interface IWordSearcherTree<ObjectType extends Comparable<ObjectType>> extends SortedCollectionInterface<ObjectType> {
    /**
     * Method that updates or adds a new definition to a currently existing word.
     * If the word does not exist, then activate the word suggestion function and ask
     * for the user to enter a new word.
     * @param Word word whose definition will be updated
     * @param Definition definition to be updated to
     */
  //  public void updateDefinition(ObjectType Word, String Definition);
    /**
     * If the word that is being searched does not exist, then this method will run
     * and ask the user if they meant a similar word to what he/she was searching.
     * In our case, we would print the last visited node before reaching null.
     */
    public void suggestWord(ObjectType Word);
}
