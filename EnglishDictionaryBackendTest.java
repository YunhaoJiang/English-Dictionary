import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;


class EnglishDictionaryBackendTest {


	@Test
    void TestAddWords() {

        EnglishDictionaryBackend edb = new EnglishDictionaryBackend();

        Word newWord = new Word();
        newWord.setWord("hit");
        newWord.setDefinition("to move your hand, a bat, etc., quickly so that it touches someone or something in a forceful or violent way");
        newWord.setLexicalCategory("verb");
        edb.addWords(newWord);

        newWord = new Word();
        newWord.setWord("hit");
        newWord.setDefinition("an act of hitting someone or something");
        newWord.setLexicalCategory("noun");
        edb.addWords(newWord);

        assertEquals(2, edb.getNumberOfWords(), 0);
    }


    @Test
    void getNumberOfWords() {

        EnglishDictionaryBackend edb = new EnglishDictionaryBackend();

        Word newWord = new Word();
        newWord.setWord("hit");
        newWord.setDefinition("to move your hand, a bat, etc., quickly so that it touches someone or something in a forceful or violent way");
        newWord.setLexicalCategory("verb");
        edb.addWords(newWord);

        newWord = new Word();
        newWord.setWord("hit");
        newWord.setDefinition("an act of hitting someone or something");
        newWord.setLexicalCategory("noun");
        edb.addWords(newWord);

        newWord = new Word();
        newWord.setWord("student");
        newWord.setDefinition("a person who attends a school, college, or university");
        newWord.setLexicalCategory("noun");
        edb.addWords(newWord);

        assertEquals(3, edb.getNumberOfWords(), 0);
    }

    @Test
    void searchByWord() {

        EnglishDictionaryBackend edb = new EnglishDictionaryBackend();

        Word newWord = new Word();
        newWord.setWord("hit");
        newWord.setDefinition("to move your hand, a bat, etc., quickly so that it touches someone or something in a forceful or violent way");
        newWord.setLexicalCategory("verb");
        edb.addWords(newWord);

        newWord = new Word();
        newWord.setWord("hit");
        newWord.setDefinition("an act of hitting someone or something");
        newWord.setLexicalCategory("noun");
        edb.addWords(newWord);

        newWord = new Word();
        newWord.setWord("student");
        newWord.setDefinition("a person who attends a school, college, or university");
        newWord.setLexicalCategory("noun");
        edb.addWords(newWord);

        List<IWord> lstIWord = edb.searchByWord("hit");

        assertEquals(2, lstIWord.size(), 0);

    }

    @Test
    void searchByWord2() {

        EnglishDictionaryBackend edb = new EnglishDictionaryBackend();

        Word newWord = new Word();
        newWord.setWord("hit");
        newWord.setDefinition("to move your hand, a bat, etc., quickly so that it touches someone or something in a forceful or violent way");
        newWord.setLexicalCategory("verb");
        edb.addWords(newWord);

        newWord = new Word();
        newWord.setWord("hit");
        newWord.setDefinition("an act of hitting someone or something");
        newWord.setLexicalCategory("noun");
        edb.addWords(newWord);

        newWord = new Word();
        newWord.setWord("student");
        newWord.setDefinition("a person who attends a school, college, or university");
        newWord.setLexicalCategory("noun");
        edb.addWords(newWord);

        List<IWord> lstIWord = edb.searchByWord("student");

        assertEquals(1, lstIWord.size(), 0);

    }

    @Test
    void searchByWord3() {

        EnglishDictionaryBackend edb = new EnglishDictionaryBackend();

        Word newWord = new Word();
        newWord.setWord("hit");
        newWord.setDefinition("to move your hand, a bat, etc., quickly so that it touches someone or something in a forceful or violent way");
        newWord.setLexicalCategory("verb");
        edb.addWords(newWord);

        newWord = new Word();
        newWord.setWord("hit");
        newWord.setDefinition("an act of hitting someone or something");
        newWord.setLexicalCategory("noun");
        edb.addWords(newWord);

        newWord = new Word();
        newWord.setWord("student");
        newWord.setDefinition("a person who attends a school, college, or university");
        newWord.setLexicalCategory("noun");
        edb.addWords(newWord);

        List<IWord> lstIWord = edb.searchByWord("aaaaa");

        assertEquals(null, lstIWord);

    }

}
