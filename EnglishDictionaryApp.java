// --== CS400 Project two W4 ==--
// Name: Sangho Jeon
// Role: BackEnd Developer
// CSL Username: sangho
// Email: sjeon36@wisc.edu
// Lecture #: 002 @1:00pm

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EnglishDictionaryApp {

  public static void main(String[] args) {
    WordLoader wordLoader = new WordLoader();
    ArrayList<IWord> wordList = new ArrayList<>();
    try {
      wordList = (ArrayList<IWord>) wordLoader.loadWords("dictionary.xml");
    } catch (FileNotFoundException e) {
      System.out.println("Word Not Found");
      e.printStackTrace();
    }
    EnglishDictionaryBackend dictionaryBackend = new EnglishDictionaryBackend();
    for (IWord word : wordList) {
      dictionaryBackend.addWords(word);
    }
    EnglishDictionaryFrontend dictionaryFrontend = new EnglishDictionaryFrontend(dictionaryBackend);
    dictionaryFrontend.runCommandLoop();
  }

}
