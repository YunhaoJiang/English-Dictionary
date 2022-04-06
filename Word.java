public class Word implements IWord{
	final private String word;
	final private String definition;
	final private String lexicalCategory;
	final private boolean userGenerated;

	public Word(String word, String definition, String lexicalCategory, boolean userGenerated) {
		this.word = word;
		this.definition = definition;
		this.lexicalCategory = lexicalCategory;
		this.userGenerated = userGenerated;
	}

	@Override
	public String getWord() {
		return this.word;
	}

	@Override
	public String getDefinition() {
		return this.definition;
	}

	@Override
	public String getLexicalCategory() {
		return this.lexicalCategory;
	}

	@Override
	public boolean isUserGenerated() {
		return this.userGenerated;
	}

	@Override
	public int compareTo(IWord o) {
		if (this.getWord().compareTo(o.getWord())>0) return 1;
		else if (this.getWord().compareTo(o.getWord())<0) return -1;
		return 0;
	}
}
