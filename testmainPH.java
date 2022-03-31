
public class testmainPH {

	public static void main(String[] args) {
		IEnglishDictionaryBackend backend = new EnglishDictionaryBackend();
		IEnglishDictionaryFrontend frontend = new EnglishDictionaryFrontend(backend); 
	    frontend.runCommandLoop();
	}

}
