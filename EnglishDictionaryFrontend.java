x// --== CS400 Project two W3 ==--
// Name: Mojtaba Javid
// Role: FrontEnd Developer
// CSL Username: mojtaba
// Email: javid2@wisc.edu
// Lecture #: 002 @1:00pm
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
	public void displayWord(IWord Word) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void wordSearch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void wordAdd() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void about() {
		// TODO Auto-generated method stub
		
	}

}
