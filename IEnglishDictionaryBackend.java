import java.util.List;

public interface IEnglishDictionaryBackend {

    public boolean addWords(IWord word); // adds word to backend database
    public int getNumberOfWords(); // retrieve number of words in database

    // these methods can be used to look-up Words
    public List<IWord> searchByWord(String word);
}
