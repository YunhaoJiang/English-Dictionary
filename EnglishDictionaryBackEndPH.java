import java.util.ArrayList;
import java.util.List;

public class EnglishDictionaryBackEndPH implements IEnglishDictionaryBackend{

	Word w1 = new Word("hey", "hello", "noun", false);
	Word w2 = new Word("whey", "protein", "noun", false);
	Word w3 = new Word("ley", "sleep", "verb", true);
	Word w4 = new Word("leyy", "sleepy", "noun", false);



	List<IWord> words = new ArrayList<IWord>();
	
	@Override
	public boolean addWords(IWord word) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getNumberOfWords() {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	public List<IWord> searchByWord(String word) {
		words.add(w1);
		words.add(w2);
		words.add(w3);
		return words;
	}

}
