
public class AEPHWord implements IWord{

  final private String word;
  private String def;
  private String lexCat;
  boolean userGen;
  
  public AEPHWord(String word, String def, String lexCat, boolean userGen) {
    this.word = word;
    this.def = def;
    this.lexCat = lexCat;
    this.userGen = userGen;
  }
  
  protected AEPHWord(String word) {
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

  public void setDefinition(String def) {
    this.def = def;
  }

  @Override
  public void setUserGenerated(boolean b) {
    this.userGen = b;
  }

  @Override
  public String toString() {
    return this.word;
  }

}
