import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;

public class EnglishDictionaryApp {

	public static void main(String[] args)
    {
		WordLoader loader = new WordLoader();
        List<IWord> wordList = null;

        try{
            wordList = loader.loadWords("dictionary.xml");
        }catch (FileNotFoundException e) {
            System.out.println("Word Not Found");
            e.printStackTrace();
        }
       EnglishDictionaryBackend backend = new EnglishDictionaryBackend();
        Iterator iterator = wordList.iterator();

        while(iterator.hasNext()){
            IWord next = (IWord)iterator.next();
            backend.addWords(next);
        }

        EnglishDictionaryFrontend frontend = new EnglishDictionaryFrontend(backend);
        frontend.runCommandLoop();

    }

}
