import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WordLoader implements IWordLoader {
  protected List<IWord> dictionary;

  public WordLoader() {
    this.dictionary = new ArrayList<>();
  }

  /**
   * This method returns the list of words described in the xml file
   *
   * @param filePath the file path of the xml file
   * @return the list of words described in the xml file
   * @throws FileNotFoundException if the filepath is not a xml file or cannot be read
   */
  @Override
  public List<IWord> loadWords(String filePath) throws FileNotFoundException {
    dictionary = new ArrayList<>(35000);
    File dictFile = new File(filePath);
    BufferedReader reader = new BufferedReader(new FileReader(dictFile));
    addToDictionary(reader);
    return dictionary;
  }

  private void addToDictionary(BufferedReader reader) {
    String nextLine = "";
    try {
      while (true) {
        while (!nextLine.contains("<word>")) {
          nextLine = reader.readLine();
          if (nextLine == null) {
            return;
          }
        }
        IWord toAdd = generateWordObject(reader);
        if (!isDuplicate(toAdd)) {
          dictionary.add(toAdd);
        }
        nextLine = reader.readLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  protected boolean isDuplicate(IWord current) {
    try {
      IWord toCheck = dictionary.get(dictionary.size() - 1);
      if (toCheck.getWord().equals(current.getWord())) {
        return true;
      }
    } catch (IndexOutOfBoundsException e) {
      return false;
    }
    return false;
  }

  protected Word generateWordObject(BufferedReader reader) throws IOException {
    String wordLine = replaceEscapedChar(reader.readLine());
    String lexLine = replaceEscapedChar(reader.readLine());
    String defLine = replaceEscapedChar(reader.readLine());

    String word = wordLine.substring(wordLine.indexOf("<name>") + 6, wordLine.indexOf("</name>"));
    String lexicalCategory =
        lexLine.substring(lexLine.indexOf("<lexical>") + 9, lexLine.indexOf("</lexical>"));
    String definition =
        defLine.substring(defLine.indexOf("<definition>") + 12, defLine.indexOf("</definition>"));

    if (lexicalCategory.equals("")) {
      lexicalCategory = "N/A";
    }

    return new Word(word, definition, lexicalCategory, false);
  }


  protected String replaceEscapedChar(String target) {
    target = target.replace("&amp;", "&");
    target = target.replace("&lt;", "<");
    target = target.replace("&gt;", ">");
    target = target.replace("&quot;", "\"");
    target = target.replace("&apos;", "'");
    return target;
  }

  /*protected IWord mergeDuplicate(IWord current) {
    Word lastWord = (Word) dictionary.remove(dictionary.size() - 1);
    String newDef;
    String newLex;
    int newWordContained = lastWord.getWordContained() + 1;
    if (lastWord.getWordContained() == 1) {
      newDef = "1. " + lastWord.getDefinition() + "\n2. " + current.getDefinition();
      newLex = "1. " + lastWord.getLexicalCategory() + " 2. " + current.getLexicalCategory();
    } else {
      newDef = lastWord.getDefinition() + "\n" + newWordContained + ". " + current.getDefinition();
      newLex = lastWord.getLexicalCategory() + " " + newWordContained + ". "
          + current.getLexicalCategory();
    }

    Word result = new Word(current.getWord(), newDef, newLex, false);
    result.setWordContained(newWordContained);

    return result;
  }*/
}
