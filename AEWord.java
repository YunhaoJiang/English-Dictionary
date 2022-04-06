
public class AEWord implements IWord{

  final private String word;
  private String def;
  private String lexCat;
  boolean userGen;
  
  public AEWord(String word, String def, String lexCat, boolean userGen) {
    this.word = word;
    this.def = def;
    this.lexCat = lexCat;
    this.userGen = userGen;
  }
  
  protected AEWord(String word) {
    this.word = word;
    this.def = "";
    this.lexCat = "";
    this.userGen = true;
  }
  
  @Override
  public int compareTo(IWord o) {
    return this.word.compareTo(o.getWord());
  }

  @Override
  public String getWord() {
    return this.word;
  }

  @Override
  public String getDefinition() {
    return this.def;
  }

  @Override
  public String getLexicalCategory() {
    return this.lexCat;
  }

  @Override
  public boolean isUserGenerated() {
    return this.userGen;
  }
  
  @Override
  public void setDefinition(String def) {
    this.def = def;
  }
  
  @Override
  public String toString() {
    return this.word;
  }

}
