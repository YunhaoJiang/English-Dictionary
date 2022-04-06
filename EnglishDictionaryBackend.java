
import java.util.List;




public class EnglishDictionaryBackend implements IEnglishDictionaryBackend {
	protected RedBlackTree<IWord> dictionaryTree;

	public EnglishDictionaryBackend() {
		this.dictionaryTree = new RedBlackTree<IWord>();
	}

	@Override
	public boolean addWords(IWord word) {
		try {
			dictionaryTree.insert(word);
		} catch (IllegalArgumentException e) {
			System.out.println("Word already exists in the dictionary");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public int getNumberOfWords() {
		return dictionaryTree.size();
	}

	@Override
	public List<IWord> searchByWord(String word) {
		IWord searchWord = new Word(word, null, null, false);
		return dictionaryTree.search(searchWord);
	}
}
