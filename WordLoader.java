import java.util.List;
import java.io.FileNotFoundException;

/**
 * Instances of classes that implement this interface can be used to load a list of shows from a
 * specified xml source file.
 * The following parts are used to load these show attributes:
 *        word: the original word
 *        lexical: part of the speech
 *        definition: the definition of the word
 */
public interface WordLoader {

  /**
   * This method returns the list of words described in the xml file
   * @param filePath the file path of the xml file
   * @return the list of words described in the xml file
   * @throws FileNotFoundException if the filepath is not a xml file or cannot be read
   */
  List<IWord> loadWords(String filePath) throws FileNotFoundException;

}
