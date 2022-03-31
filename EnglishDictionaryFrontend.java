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

	public EnglishDictionaryFrontend(String input, IEnglishDictionaryBackend backend) {
	    this.backend = backend;
	    this.scnr = new Scanner(input);
	  }

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

	@Override
	public void displayCommandMenu() {
		System.out.println("Command Menu:");
	    System.out.println("\t1) [S]earch a Word");
	    System.out.println("\t2) [A]dd a Word to the dictionary");
	    System.out.println("\t3) About the [D]ictionary (size, creators info, source)");
	    System.out.println("\t4) [Q]uit");
	    System.out.print("Choose a command from the menu above: ");
		
	}

	@Override
	public void displayWords(List<IWord> Words) {
		System.out.println("Found " + Words.size() + "/" + backend.getNumberOfWords() + " matches.");
	    for (int i = 0; i < Words.size(); i++) {
	      IWord current = Words.get(i);
	      System.out.println(i + 1 + ". " + current.getWord() +  "   " + current.getLexicalCategory());
	      String warning = "";
	      if(current.isUserGenerated()) warning = "[ATTENTION THIS WORD WAS ADDED BY A USER]";
	      System.out.println("\tDefenitions: " + warning + "\n\t" + current.getDefinition());
	    }
		
	}

	@Override
	public void wordSearch() {
	    System.out.print("Write a word that you would like to search for: ");
	    String userKeyword = this.scnr.next();
	    this.scnr.nextLine(); 
	    List<IWord> searchResult = this.backend.searchByWord(userKeyword);
	    this.displayWords(searchResult);		
	}

	@Override
	public void wordAdd() {
		System.out.print("Write the word that you would like to add: ");
	    String userWord = this.scnr.next();
	    this.scnr.nextLine();
	    System.out.print("Write the Lexical Category (noun, verb, ...): ");
	    String userLC = this.scnr.next();
	    this.scnr.nextLine();
	    System.out.print("Write the definition: ");
	    String userDef = this.scnr.next();
	    this.scnr.nextLine();
	    IWord newWord = new Word(userWord, userDef, userLC, true); 
	    boolean added = this.backend.addWords(newWord);
	    if(added == true) System.out.println("\tThe Word “" + userWord + "” was added successfully.");
	    else {
	    	List<IWord> words = this.backend.searchByWord(userWord);
	    	IWord word = null;
	    	for (int i = 0; i < words.size(); i++) {
	    		if(words.get(i).getWord().toLowerCase().equals(userWord.toLowerCase())) word = words.get(i);}
	  	      String warning = "";
	  	      if(word.isUserGenerated()) warning = "[ATTENTION THIS WORD WAS ADDED BY A USER]";
	    	System.out.println("\tTHE WORD ALREADY EXIST IN THE DICTIONARY:" + warning + "\n\t" + word.getDefinition());
	    	
	    };
		
	}

	@Override
	public void about() {
		System.out.println("\tThe English Dictionary Project was implemented and created in group efforts by group BU-red." + 
	"\tOur Team consisting of personA(data wrangler), personB(algorithm engineer), personC(backend developer)," +
				"\tand personC(frontend developer), has created an ever-growing app that provides Webster 1913 definition" + 
	"\tfor more than 130,000 words.");		
	}

}
