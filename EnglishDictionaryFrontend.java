// --== CS400 Project two W3 ==--
// Name: Mojtaba Javid
// Role: FrontEnd Developer
// CSL Username: mojtaba
// Email: javid2@wisc.edu
// Lecture #: 002 @1:00pm
import java.util.List;
import java.util.Scanner;

public class EnglishDictionaryFrontend implements IEnglishDictionaryFrontend{
	
	Scanner scnr;
	IEnglishDictionaryBackend backend;
	
	public EnglishDictionaryFrontend(IEnglishDictionaryBackend backend) {
		this.backend = backend;
	    this.scnr = new Scanner(System.in);
	  }

	
	/**
	   * This method drives the entire read, eval, print loop (repl) for the
	   * Dictionary App. This loop will continue to run until the user
	   * explicitly enters the quit command.
	   */
	@Override
	public void runCommandLoop() {
		System.out.println("Welcome to the English Dictionary App!");
	    System.out.println("=================================");
	    char input;
	    do {
	      this.displayCommandMenu();
	      input = this.scnr.nextLine().toUpperCase().charAt(0);

	      if (input == '1' || input == 'S') {
	        this.wordSearch();;
	      }

	      if (input == '2' || input == 'A') {
	        this.wordAdd();;
	      }

	      if (input == '3' || input == 'D') {
	        this.about();
	      }
	    } while (input != 'Q' && input != '4');
		
	}

	
	/**
	   * This method display the options for the loop
	   */
	@Override
	public void displayCommandMenu() {
		System.out.println("Command Menu:");
	    System.out.println("\t1) [S]earch a Word");
	    System.out.println("\t2) [A]dd a Word to the dictionary");
	    System.out.println("\t3) About the [D]ictionary (the app and creators' information)");
	    System.out.println("\t4) [Q]uit");
	    System.out.print("Choose a command from the menu above: ");
		
	}

	/**
	   * This method takes a list of words containing the searched key word and displays their
	   * definition, lexical category and whether they are user generated
	   * @param list of words
	   */
	@Override
	public void displayWords(List<IWord> Words) {
		System.out.println("Found " + Words.size() + " matches.");
		if(Words.size() == 0) System.out.print("The word that you searched was NOT FOUND in the dictionary;" + 
			    "\nif you would like to add it, choose option 2\n");
	    for (int i = 0; i < Words.size(); i++) {
	      IWord current = Words.get(i);
	      System.out.println(i + 1 + ". " + current.getWord() +  "   " + "[" + current.getLexicalCategory() + "]");
	      String warning = "";
	      if(current.isUserGenerated()) warning = "[ATTENTION THIS WORD WAS ADDED BY A USER]";
	      System.out.println("\tDefenitions:   " + warning + "\n\t" + current.getDefinition() + "\n");
	    }
		
	}

	/**
	   * This method takes a user input and
	   * looks for the words containing that
	   * input then passes the list of words
	   * to the display method
	   */
	@Override
	public void wordSearch() {
	    System.out.print("Write a word that you would like to search for: ");
	    String userKeyword = this.scnr.next();
	    this.scnr.nextLine(); 
	    List<IWord> searchResult = this.backend.searchByWord(userKeyword.toLowerCase());
	    this.displayWords(searchResult);		
	}

	/**
	   * This method takes a word, its definition and
	   * lexical category from the user and adds it to the dictionary
	   */
	@Override
	public void wordAdd() {
		System.out.print("Write the word that you would like to add: ");
	    String userWord = this.scnr.next();
	    this.scnr.nextLine();
	    System.out.print("Write the Lexical Category (noun, verb, ...): ");
	    String userLC = this.scnr.nextLine();
	    System.out.print("Write the definition: ");
	    String userDef = this.scnr.nextLine();
	    
	    userWord = userWord.toLowerCase();
	    String z = userWord.charAt(0) + "";
	    userWord = z.toUpperCase() + userWord.substring(1);
	    IWord newWord = new FDPHWord(userWord, userDef, userLC, true); 
	    boolean added = this.backend.addWords(newWord);
	    if(added == true) System.out.println("\tThe Word “" + userWord + "” was added successfully.\n");
	    else {
	    	List<IWord> words = this.backend.searchByWord(userWord.toLowerCase());
	    	IWord word = null;
	    	for (int i = 0; i < words.size(); i++) {
	    		if(words.get(i).getWord().toLowerCase().equals(newWord.getWord().toLowerCase())) word = words.get(i);}
	  	      String warning = "";
	  	      if(word.isUserGenerated()) warning = "[ATTENTION THIS WORD WAS ADDED BY A USER]";
	    	System.out.println("\tTHE WORD ALREADY EXIST IN THE DICTIONARY:" + "\n\tDefinition:   " + warning + "\n\t" + word.getDefinition() + "\n");
	    	
	    };
		
	}

	//a simple summary of what the app does and it creators
	@Override
	public void about() {
		System.out.println("\tThe English Dictionary Project was implemented and created in group efforts by group BU-red." + 
	"\n\tOur Team consisting of Yunhao Jiang(data wrangler), Danny Jiang(algorithm engineer)," +
				"\n\tSangho Jeon(backend developer),and Mojtaba Javid(frontend developer), has created an" + 
	"\n\tever-growing app that provides definitions for aroung " + backend.getNumberOfWords() +" words.\n");		
	}

}
