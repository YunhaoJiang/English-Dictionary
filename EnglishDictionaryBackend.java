import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;




public class EnglishDictionaryBackend implements IEnglishDictionaryBackend {

	TreeMap<String,List<IWord>> treeMap = new TreeMap<String,List<IWord>>();
	RedBlackTree<IWord> rbt = new RedBlackTree<IWord>();

    public EnglishDictionaryBackend()
    {

    }
    

    public boolean addWords(IWord word)
    {
    	if ( treeMap.containsKey(word.getWord()) == true )
    	{
    		List<IWord> lstWord = treeMap.get(word.getWord());
    		lstWord.add(word);
    		
    	}
    	else
    	{
    		List<IWord> lstWord2 = new ArrayList<IWord>();
    		lstWord2.add(word);
    		treeMap.put(word.getWord(), lstWord2);
    		
    	}
    	return true;
    }


    public int getNumberOfWords() 
    {
    	int count = 0;
    	for (Map.Entry<String, List<IWord>> entry : treeMap.entrySet())
    		count += entry.getValue().size();
    	
    	
        return count;
    }

    // these methods can be used to look-up Words
    public List<IWord> searchByWord(String word){
    	
    	if ( treeMap.containsKey(word) == true )
    	{
    		return treeMap.get(word);
    	}
    	
    	return null;
    }
}
