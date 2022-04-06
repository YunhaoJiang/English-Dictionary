
public class BDPHWord implements IWord {

    private String word = "";
    private String definition = "";
    private String lexicalCategory = "";
    private boolean isUserGenerated;

    @Override
    public int compareTo(IWord o) {
        // TODO Auto-generated method stub
        return word.compareTo(o.getWord());
    }

    @Override
    public String getWord() {
        // TODO Auto-generated method stub
        return word;
    }

    public void setWord(String _word)
    {
        this.word = _word;
    }

    @Override
    public String getDefinition() {
        // TODO Auto-generated method stub
        return definition;
    }

    public void setDefinition(String _def)
    {
        this.definition = _def;
    }

    @Override
    public void setUserGenerated(boolean b) {
        this.isUserGenerated = b;
    }

    @Override
    public String getLexicalCategory() {
        // TODO Auto-generated method stub
        return lexicalCategory;
    }

    public void setLexicalCategory(String lexCate) {
        // TODO Auto-generated method stub
        lexicalCategory = lexCate;
    }

    @Override
    public boolean isUserGenerated() {
        // TODO Auto-generated method stub
        return false;
    }

}
