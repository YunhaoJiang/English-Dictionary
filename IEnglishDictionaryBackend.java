import java.util.List;

public interface IEnglishDictionaryBackend {

    public void addWords(IWord word); // adds word to backend database
    public int getNumberOfWords(); // retrieve number of words in database

    // these methods can be used to look-up Words
    public IWord searchByWord(String word);
}
