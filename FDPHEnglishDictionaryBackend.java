import java.util.ArrayList;
import java.util.List;

public class FDPHEnglishDictionaryBackend implements IEnglishDictionaryBackend{

	IWord w1 = new FDPHWord("hey", "hello", "noun", false);
	IWord w2 = new FDPHWord("whey", "protein", "noun", false);
	IWord w3 = new FDPHWord("ley", "sleep", "verb", true);
	IWord w4 = new FDPHWord("leyy", "sleepy", "noun", false);

	List<IWord> words = new ArrayList<IWord>();

	public void PH() {
		words.add(w1);
		words.add(w2);
		words.add(w3);
		words.add(w4);
	}
	
	@Override
	public boolean addWords(IWord word) {
		for(int i=0; i<this.words.size(); i++) {
			if (this.words.get(i).getWord().toLowerCase().equals(word.getWord().toLowerCase())) return false;	
		}
		words.add(word);
		return true;
	}

	@Override
	public int getNumberOfWords() {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	public List<IWord> searchByWord(String word) {
		List<IWord> result = new ArrayList<IWord>();
		for(int i=0; i<this.words.size(); i++) {
			if (this.words.get(i).getWord().toLowerCase().contains(word)) result.add(words.get(i));	
		}
		return result;
	}

	@Override
	public boolean updateWord(String word, String definition) {
		return false;
	}

	@Override
	public IWord doYouMean(String word) {
		return null;
	}

	@Override
	public List<IWord> searchByStart(String start) {
		return null;
	}

}
