/**
 * Instances of classes that implement this interface represents a single word-definition pair
 * that can be stored, sorted, and searched for based on these accessors below.
 */
public interface IWord extends Comparable<IWord> {

  // constructor args (String word, String definition, boolean userGenerated)

  String getWord(); // return the title of the word
  String getDefinition(); // return the definition of the word
  String getLexicalCategory(); // return the parts of speech like adj, noun, verb
  boolean isUserGenerated(); // return true if it is generated by user; false otherwise
  void setDefinition(String def);

  void setUserGenerated(boolean b);
  // compareTo() method supports sorting words in ascending order by rating
}
