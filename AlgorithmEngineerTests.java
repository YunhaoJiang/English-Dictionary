import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AlgorithmEngineerTests {

  /**
   * TEST METHOD 1: ADDS THREE WORDS AND CHECKS THE POSITION AND DEFINITION OF ONE OF THE WORDS
   * (CHAGRIN), UPDATES THE DEFINITION AND CONFIRMS THAT THE DEFINITION HAS INDEED BEEN UPDATED
   */
 /* @Test
  void test1() {
    // Adds words "chagrin", "random", and "tomb" to the dictionary
    AEPHWord chagrin = new AEPHWord("chagrin",
        "A distress of mind caused by humiliation, disappointment, or failure.", "noun", false);
    AEPHWord random =
        new AEPHWord("random", "Lacking a definite plan, purpose, or pattern.", "adjective", false);
    AEPHWord tomb = new AEPHWord("tomb", "An excavation in which a corpse is buried.", "noun", false);
    WordSearcherTree<AEPHWord> tree = new WordSearcherTree<AEPHWord>();
    tree.insert(chagrin);
    tree.insert(random);
    tree.insert(tomb);
    // Checks the definition and position of the "chagrin"
    Assertions.assertEquals("A distress of mind caused by humiliation, disappointment, or failure.",
        tree.root.leftChild.data.getDefinition());
    // Updates the definition of the "chagrin"
    tree.updateDefinition(new AEPHWord("chagrin"), "NEW DEFINITION");
    // Checks the updated definition of the "chagrin"
    Assertions.assertEquals("NEW DEFINITION", tree.root.leftChild.data.getDefinition());
  }*/

  /**
   * TEST CASE 2: ADDS 4 WORDS TO THE DICTIONARY, BUT ALSO TRIES TO UPDATE THE DEFINITION FOR A WORD
   * THAT IS NOT IN THE DICTIONARY USING THE suggestWordHelper() METHOD, SHOULD RETURN THE CLOSEST
   * WORD THAT IS IN THE DICTIONARY WHICH IS 'tomb'. NEXT ADDS THE CHECKS THE DEFINTION OF 'tome',
   * UPDATES THE DEFINITION OF 'tome', AND CHECKS THE UPDATED DEFINITION ONE MORE TIME
   */
 /* @Test
  void test2() {
    // Adds words "chagrin", "random", and "tomb" to the dictionary
    AEPHWord chagrin = new AEPHWord("chagrin",
        "A distress of mind caused by humiliation, disappointment, or failure.", "noun", false);
    AEPHWord random =
        new AEPHWord("random", "Lacking a definite plan, purpose, or pattern.", "adjective", false);
    AEPHWord tomb = new AEPHWord("tomb", "An excavation in which a corpse is buried.", "noun", false);
    WordSearcherTree<AEPHWord> tree = new WordSearcherTree<AEPHWord>();
    tree.insert(chagrin);
    tree.insert(random);
    tree.insert(tomb);
    // Since "tome" is not in the dictionary, should suggest "tomb"
    Assertions.assertEquals(tomb, tree.suggestWordHelper(new AEPHWord("tome"), tree.root));
    // Checks if the definition of tomb has been affected
    Assertions.assertEquals("An excavation in which a corpse is buried.",
        tree.root.rightChild.data.getDefinition());
    // Adds "tome" for real this time
    AEPHWord tome = new AEPHWord("tome", "A large or scholarly book.", "noun", false);
    tree.insert(tome);
    // Checks the definition and position of the "tome"
    Assertions.assertEquals("A large or scholarly book.",
        tree.root.rightChild.rightChild.data.getDefinition());
    // Updates "tome"'s definition
    tree.updateDefinition(new AEPHWord("tome"), "TEST");
    // Test to see if the new definition has been updated
    Assertions.assertEquals("TEST", tree.root.rightChild.rightChild.data.getDefinition());
  }*/

  /**
   * TEST METHOD 3: CREATES 9 WORDS WITH NO DEFINITION FROM 'a' TO 'i' AND CHECKS USING
   * toLevelOrderString() TO SEE IF THE TREE IS CORRECT
   */
 /* @Test
  void test3() {
    AEPHWord a = new AEPHWord("a");
    AEPHWord b = new AEPHWord("b");
    AEPHWord c = new AEPHWord("c");
    AEPHWord d = new AEPHWord("d");
    AEPHWord e = new AEPHWord("e");
    AEPHWord f = new AEPHWord("f");
    AEPHWord g = new AEPHWord("g");
    AEPHWord h = new AEPHWord("h");
    AEPHWord i = new AEPHWord("i");
    WordSearcherTree<AEPHWord> tree = new WordSearcherTree<AEPHWord>();
    tree.insert(a);
    tree.insert(b);
    tree.insert(c);
    tree.insert(d);
    tree.insert(e);
    tree.insert(f);
    tree.insert(g);
    tree.insert(h);
    tree.insert(i);
    Assertions.assertEquals("[ d, b, f, a, c, e, h, g, i ]", tree.toLevelOrderString());
  }*/

  /**
   * TEST METHOD 4: ADDS 4 LETTERS TO THE DICTIONARY AND THEN USES THE suggestWordHelper() FUNCTION
   * TO FIND A SIMILAR WORD CLOSE TO THE INPUT, THE INPUT IN THIS CASE IS THE LETTER 'm', SHOULD
   * RETURN 'n' SINCE IT'S THE CLOSEST LETTER TO IT
   */
 /* @Test
  void test4() {
    AEPHWord a = new AEPHWord("a");
    AEPHWord f = new AEPHWord("f");
    AEPHWord n = new AEPHWord("n");
    AEPHWord w = new AEPHWord("w");
    WordSearcherTree<AEPHWord> tree = new WordSearcherTree<AEPHWord>();
    tree.insert(a);
    tree.insert(f);
    tree.insert(n);
    tree.insert(w);
    // Expects the suggestion function to return the word that is closest to the inputted one
    Assertions.assertEquals(n, tree.suggestWordHelper(new AEPHWord("m"), tree.root));
  }*/
  
  /**
   * TEST METHOD 5 TRIES TO UPDATE THE DEFINITION OF A NULL WORD, SHOULD THROW A NullPointerException
   */
/* @Test
 void test5(){
 // Adds words "chagrin", "random", and "tomb" to the dictionary
    AEPHWord chagrin = new AEPHWord("chagrin",
        "A distress of mind caused by humiliation, disappointment, or failure.", "noun", false);
    AEPHWord random =
        new AEPHWord("random", "Lacking a definite plan, purpose, or pattern.", "adjective", false);
    AEPHWord tomb = new AEPHWord("tomb", "An excavation in which a corpse is buried.", "noun", false);
    WordSearcherTree<AEPHWord> tree = new WordSearcherTree<AEPHWord>();
    tree.insert(chagrin);
    tree.insert(random);
    tree.insert(tomb);
    // Tests to see if the correct exception is thrown when trying to update the definition of a null word
    Assertions.assertThrows(NullPointerException.class, () -> tree.updateDefinition(null, "null definition"));
    
  }*/
}

