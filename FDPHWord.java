
public class FDPHWord implements IWord{
	protected final String Title;
	protected final String defs;
	protected final String LexicalCat;
	protected boolean usergenerated;

	
	public FDPHWord(String word, String definition, String LexicalCategory, boolean userGenerated) {
		this.Title=word;
		this.defs=definition;
		this.LexicalCat=LexicalCategory;
		this.usergenerated = userGenerated;
	}
	
	@Override
	public int compareTo(IWord o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getWord() {
		// TODO Auto-generated method stub
		return this.Title;
	}

	@Override
	public String getDefinition() {
		// TODO Auto-generated method stub
		return this.defs;
	}

	@Override
	public String getLexicalCategory() {
		// TODO Auto-generated method stub
		return this.LexicalCat;
	}

	@Override
	public boolean isUserGenerated() {
		// TODO Auto-generated method stub
		return this.usergenerated;
	}

}
