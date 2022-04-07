import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DataWranglerTests {

  /**
   * Test Word class's constructor and getters.
   */
  @Test
  public void test1() {
    Word word = new Word("Test", "see if it works", "v.", false);

    assertEquals("Test", word.getWord());
    assertEquals("see if it works", word.getDefinition());
    assertEquals("v.", word.getLexicalCategory());
    assertFalse(word.isUserGenerated());
  }

  /**
   * Test isDuplicate method. This method is used check duplicate with last added word (Since the
   * xml is in alphabetical order).
   */
  @Test
  public void test2() {
    WordLoader wordLoader = new WordLoader();
    {
      // Test 1: check feedback on empty dictionary
      IWord word = new Word("Test", "see if it works", "v.", false);
      assertFalse(wordLoader.isDuplicate(word));
      wordLoader.dictionary.add(word);
    }

    {
      // Test 2: check non-duplicate word on non-empty dictionary
      IWord word = new Word("Exam", "see if it works", "v.", false);
      assertFalse(wordLoader.isDuplicate(word));
      wordLoader.dictionary.add(word);
    }

    {
      // Test 3: check duplicate word on non-empty dictionary
      IWord word = new Word("Exam", "others", "n.", false);
      assertTrue(wordLoader.isDuplicate(word));
    }
  }

  /**
   * Test generateWordObject method.
   */
  @Test
  public void test3() {
    WordLoader wordLoader = new WordLoader();
    String input = "    <name>abate</name>\n" + "    <lexical>verb</lexical>\n"
        + "    <definition>to reduce in amount, degree, intensity, etc.; lessen; diminish</definition>\n"
        + "  </word>";
    BufferedReader reader = new BufferedReader(new StringReader(input));
    try {
      IWord word = wordLoader.generateWordObject(reader);
      assertEquals("abate", word.getWord());
      assertEquals("verb", word.getLexicalCategory());
      assertEquals("to reduce in amount, degree, intensity, etc.; lessen; diminish",
          word.getDefinition());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Test on replacing escaped characters in xml.
   */
  @Test
  public void test4() {
    WordLoader wordLoader = new WordLoader();
    String before = "&amp;1&lt;2&gt;3&quot;4&apos;";
    String after = wordLoader.replaceEscapedChar(before);
    assertEquals("&1<2>3\"4'", after);

  }

  /**
   * Test loading the entire dictionary
   */
  @Test
  public void test5() {
    WordLoader wordLoader = new WordLoader();
    try {
      wordLoader.loadWords("dictionary.xml");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    assertEquals(3466, wordLoader.dictionary.size());

  }


  /**
   * Additional Test: Test loading non-existent file
   */
  @Test
  public void test6() {
    WordLoader wordLoader = new WordLoader();
    try {
      wordLoader.loadWords("notfound.txt");
    } catch (Exception e) {
      assertEquals(FileNotFoundException.class, e.getClass());
    }
  }

  /**
   * Additional Test: Word's setDefinition and setUserGenerated methods
   */
  @Test
  public void test7() {
    IWord word = new Word("Test", "see if it works", "v.", false);
    word.setDefinition("new definition");
    word.setUserGenerated(true);
    assertEquals("new definition", word.getDefinition());
    assertTrue(word.isUserGenerated());
  }

  /**
   * Additional Test: Backend's addWords method
   */
  @Test
  public void test8() {
    EnglishDictionaryBackend backend = new EnglishDictionaryBackend();
    boolean result = backend.addWords(new Word("Test", "see if it works", "v.", false));
    assertTrue(result);
    assertEquals(1,backend.getNumberOfWords());
  }

  /**
   * Additional Test: Backend's searchByWord method
   */
  @Test
  public void test9() {
    EnglishDictionaryBackend backend = new EnglishDictionaryBackend();
    backend.addWords(new Word("Test", "see if it works", "v.", false));
    backend.addWords(new Word("Test2", "see if it works", "v.", false));
    backend.addWords(new Word("Test3", "see if it works", "v.", false));
    backend.addWords(new Word("Test4", "see if it works", "v.", false));
    backend.addWords(new Word("Test5", "see if it works", "v.", false));
    List<IWord> result = backend.searchByWord("Test3");
    assertEquals("Test3", result.get(0).getWord());
  }
}
