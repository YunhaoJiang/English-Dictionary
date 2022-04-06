
import java.util.List;





public class EnglishDictionaryBackend implements IEnglishDictionaryBackend {

	WordSearcherTree<IWord> searcherTree = new WordSearcherTree<IWord>();

    public EnglishDictionaryBackend()
    {

    }


    public boolean addWords(IWord word)
    {
    	if (searcherTree.contains(word) == true )
    		searcherTree.updateDefinition(word, word.getDefinition());
    	else
    		searcherTree.insert(word);
    	
		return true;
    }

    public int getNumberOfWords()
    {
        return searcherTree.size();
    }

    // these methods can be used to look-up Words
    public List<IWord> searchByWord(String word){

    	return searcherTree.getWord(word);
    }
}
