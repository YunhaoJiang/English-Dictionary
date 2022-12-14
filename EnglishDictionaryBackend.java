// --== CS400 Project two W4 ==--
// Name: Sangho Jeon
// Role: BackEnd Developer
// CSL Username: sangho
// Email: sjeon36@wisc.edu
// Lecture #: 002 @1:00pm

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class EnglishDictionaryBackend implements IEnglishDictionaryBackend {
	protected WordSearcherTree<IWord> dictionaryTree;

	public EnglishDictionaryBackend() {
		this.dictionaryTree = new WordSearcherTree<IWord>();
	}

	@Override
	public boolean addWords(IWord word) {
		try {
			dictionaryTree.insert(word);
		} catch (IllegalArgumentException e) {
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
		try{
			return dictionaryTree.get(searchWord);
		} catch (NoSuchElementException e){
			return new ArrayList<>();
		}
	}

	public List<IWord> searchByStart (String start){
		IWord searchWord = new Word(start, null, null, false);
		return dictionaryTree.searchByStart(searchWord);
	}

	public boolean updateWord(String word, String definition){
		IWord searchWord = new Word(word, null, null, false);
		return dictionaryTree.updateDefinition(searchWord, definition);
	}

	public IWord doYouMean(String word){
		IWord searchWord = new Word(word, null, null, false);
		return dictionaryTree.suggestWord(searchWord);
	}
}
