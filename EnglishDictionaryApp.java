import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;

public class EnglishDictionaryApp {
	
	public static void main(String[] args)
    {
		WordLoader var1 = new WordLoader();
        List<IWord> var2 = null;

        try{
            var2 = var1.loadWords("/home/sjeon36/Project2_BU_red");
        }catch (FileNotFoundException var6) {
            System.out.println("Word Not Found");
            var6.printStackTrace();
        }
       EnglishDictionaryBackend var3 = new EnglishDictionaryBackend();
        Iterator var4 = var2.iterator();

        while(var4.hasNext()){
            IWord var5 = (IWord)var4.next();
            var3.addWords(var5);
        }

        EnglishDictionaryFrontend var7 = new EnglishDictionaryFrontend(var3);
        var7.runCommandLoop();//
        
    }

}
