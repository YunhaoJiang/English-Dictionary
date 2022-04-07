// --== CS400 Project two W4 ==--
// Name: Sangho Jeon
// Role: BackEnd Developer
// CSL Username: sangho
// Email: sjeon36@wisc.edu
// Lecture #: 002 @1:00pm

import java.util.List;

public interface IEnglishDictionaryBackend {

  public boolean addWords(IWord word); // adds word to backend database

  public int getNumberOfWords(); // retrieve number of words in database

  // these methods can be used to look-up Words
  public List<IWord> searchByWord(String word);

  public boolean updateWord(String word, String definition);

  public IWord doYouMean(String word);

  List<IWord> searchByStart(String start);
}
