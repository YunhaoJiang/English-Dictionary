// --== CS400 Project two W4 ==--
// Name: Sangho Jeon
// Role: BackEnd Developer
// CSL Username: sangho
// Email: sjeon36@wisc.edu
// Lecture #: 002 @1:00pm

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;


class BackendDeveloperTests {


    @Test
    // This test is to see if the Add words method actually adding words correctly. The tests added two new words
    //into edb and if it works well, there should be two words in edb.
    void TestAddWords() {

        EnglishDictionaryBackend edb = new EnglishDictionaryBackend();

        BDPHWord newWord = new BDPHWord();
        newWord.setWord("Jeon");
        newWord.setDefinition("one of the last names in Korea");
        newWord.setLexicalCategory("noun");
        edb.addWords(newWord);

        newWord = new BDPHWord();
        newWord.setWord("Sangho");
        newWord.setDefinition("name that are used frequently used in Korea");
        newWord.setLexicalCategory("noun");
        edb.addWords(newWord);

        assertEquals(2, edb.getNumberOfWords(), 0);
    }

    @Test
    // This test is to see when it adds the word that is already exists in the directory. When the word "hit"
    // is added first in the edb already, if the test try to add "hit" again, it should catch the
    // IllegalArgumentException e and return false.
    void TestAddWords2() {

        EnglishDictionaryBackend edb = new EnglishDictionaryBackend();

        BDPHWord newWord = new BDPHWord();
        newWord.setWord("hit");
        newWord.setDefinition("to move your hand, a bat, etc., quickly so that it touches someone or something in a forceful or violent way");
        newWord.setLexicalCategory("verb");
        edb.addWords(newWord);

        newWord = new BDPHWord();
        newWord.setWord("hit");
        newWord.setDefinition("an act of hitting someone or something");
        newWord.setLexicalCategory("noun");

        assertEquals(false, edb.addWords(newWord));
    }

    @Test
    // This test is to see if GetNumberOfWords methods get the size properly. The test added three words into edb, then
    // the number of words in the edb should be 3.
    void TestGetNumberOfWords() {

        EnglishDictionaryBackend edb = new EnglishDictionaryBackend();

        BDPHWord newWord = new BDPHWord();
        newWord.setWord("hit");
        newWord.setDefinition("to move your hand, a bat, etc., quickly so that it touches someone or something in a forceful or violent way");
        newWord.setLexicalCategory("verb");
        edb.addWords(newWord);

        newWord = new BDPHWord();
        newWord.setWord("airpods");
        newWord.setDefinition("a bluetooth earphone type of audio device produced by Apple ");
        newWord.setLexicalCategory("noun");
        edb.addWords(newWord);

        newWord = new BDPHWord();
        newWord.setWord("student");
        newWord.setDefinition("a person who attends a school, college, or university");
        newWord.setLexicalCategory("noun");
        edb.addWords(newWord);

        assertEquals(3, edb.getNumberOfWords(), 0);
    }

    @Test
    // This test is to see if the word search method search the word correctly. the tests added words "hit" and "student"
    //into edb. Then when seachByWord method search for the "hit" and put it into lstIWord, the size of the lstIWord should
    // be 1 which is "hit".
    void TestSearchByWord() {

        EnglishDictionaryBackend edb = new EnglishDictionaryBackend();

        BDPHWord newWord = new BDPHWord();
        newWord.setWord("hit");
        newWord.setDefinition("to move your hand, a bat, etc., quickly so that it touches someone or something in a forceful or violent way");
        newWord.setLexicalCategory("verb");
        edb.addWords(newWord);

        newWord = new BDPHWord();
        newWord.setWord("student");
        newWord.setDefinition("a person who attends a school, college, or university");
        newWord.setLexicalCategory("noun");
        edb.addWords(newWord);

        List<IWord> lstIWord = edb.searchByWord("hit");

        assertEquals(1, lstIWord.size(), 0);

    }


    @Test
    // This test is to see if it returns new array list correctly when word that is not exist in the directory.
    // "aaaaa" is not in the edb, so it should return new Arraylist that user can add on it.
    void TestSearchByWord2() {

        EnglishDictionaryBackend edb = new EnglishDictionaryBackend();

        BDPHWord newWord = new BDPHWord();
        newWord.setWord("Jeon");
        newWord.setDefinition("one of the last names in Korea");
        newWord.setLexicalCategory("noun");
        edb.addWords(newWord);

        newWord = new BDPHWord();
        newWord.setWord("Sangho");
        newWord.setDefinition("name that are used frequently used in Korea");
        newWord.setLexicalCategory("noun");
        edb.addWords(newWord);

        newWord = new BDPHWord();
        newWord.setWord("student");
        newWord.setDefinition("a person who attends a school, college, or university");
        newWord.setLexicalCategory("noun");
        edb.addWords(newWord);

        List<IWord> lstIWord = edb.searchByWord("aaaaa");

        assertEquals(new ArrayList<>(), lstIWord);


    }

    @Test
    //additional test for AE
    void TestAlgorithmEngineer1() {
        WordSearcherTree tree = new WordSearcherTree();
        BDPHWord newWord = new BDPHWord();
        newWord.setWord("Jeon");
        newWord.setDefinition("one of the last names in Korea");
        newWord.setLexicalCategory("noun");
        tree.insert(newWord);

        assertEquals(1, tree.size(), 0);


    }

    @Test
        //additional test for AE
    void TestAlgorithmEngineer2() {
        WordSearcherTree tree = new WordSearcherTree();
        BDPHWord newWord = new BDPHWord();
        newWord.setWord("NULL");
        newWord.setDefinition("1");
        newWord.setLexicalCategory("noun");
        tree.insert(newWord);

        tree.updateDefinition(newWord, "0");

        assertEquals( "0", newWord.getDefinition());



    }


    @Test
        // additional test for DW
    void TestDataWangler1() {
        Word word = new Word("Jeon", "lastname", "noun", true);
        assertEquals("Jeon", word.getWord());
        assertEquals("lastname", word.getDefinition());
        assertEquals("noun", word.getLexicalCategory());

    }
    // additional test for DW
    @Test
    void TestDataWangler2() {
        Word word = new Word("Jeon", "lastname", "noun", true);

        assertEquals(true, word.isUserGenerated());

    }

}
