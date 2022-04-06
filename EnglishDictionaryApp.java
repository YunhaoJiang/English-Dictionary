import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EnglishDictionaryApp {

  public static void main(String[] args) {
    WordLoader loader = new WordLoader();
    ArrayList<IWord> wordList = new ArrayList<>();

    try {
      wordList = (ArrayList<IWord>) loader.loadWords("dictionary.xml");
    } catch (FileNotFoundException e) {
      System.out.println("Word Not Found");
      e.printStackTrace();
    }
    EnglishDictionaryBackend backend = new EnglishDictionaryBackend();

    for (IWord word : wordList) {
      backend.addWords(word);
    }

    IEnglishDictionaryFrontend frontend = new EnglishDictionaryFrontend(backend);
    frontend.runCommandLoop();

  }

}
