import java.util.List;

public class EnglishDictionaryBackend implements IEnglishDictionaryBackend {

  WordSearcherTree<IWord> searcherTree;

  public EnglishDictionaryBackend() {
    searcherTree = new WordSearcherTree<IWord>();
  }


  public boolean addWords(IWord word) {
    return true;
  }

  public int getNumberOfWords() {
    return searcherTree.size();
  }

  // these methods can be used to look-up Words
  public List<IWord> searchByWord(String word) {

    return searcherTree.getWord(word);
  }
}
