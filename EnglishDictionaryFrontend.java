// --== CS400 Project two W3 ==--
// Name: Mojtaba Javid
// Role: FrontEnd Developer
// CSL Username: mojtaba
// Email: javid2@wisc.edu
// Lecture #: 002 @1:00pm
import java.util.List;
import java.util.Scanner;

public class EnglishDictionaryFrontend implements IEnglishDictionaryFrontend {

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
        this.wordSearch();
        ;
      }

      if (input == '2' || input == 'A') {
        this.wordAdd();
      }

      if (input == '3' || input == 'U') {
        this.wordUpdate();
      }

      if (input == '4' || input == 'D') {
        this.about();
      }
    } while (input != '5' && input != 'Q');

  }


  /**
   * This method display the options for the loop
   */
  @Override
  public void displayCommandMenu() {
    System.out.println("Command Menu:");
    System.out.println("\t1) [S]earch a Word");
    System.out.println("\t2) [A]dd a Word to the dictionary");
    System.out.println("\t3) [U]pdate the definition of a Word");
    System.out.println("\t4) About the [D]ictionary (the app and creators' information)");
    System.out.println("\t5) [Q]uit");
    System.out.print("Choose a command from the menu above: ");

  }

  /**
   * This method takes a list of words containing the searched key word and displays their
   * definition, lexical category and whether they are user generated
   *
   * @param Words of words
   */
  @Override
  public void displayWords(List<IWord> Words) {
    System.out.println("Found " + Words.size() + " matches.");

    for (int i = 0; i < Words.size(); i++) {
      IWord current = Words.get(i);
      System.out.print(
          i + 1 + ". " + current.getWord() + "\t[" + current.getLexicalCategory() + "]");
      String warning = "";
      if (current.isUserGenerated())
        warning = "[ATTENTION THIS WORD WAS ADDED BY A USER]";
      System.out.println(
          "\t" + warning + "\n\tDefinitions:   " + "\n\t" + current.getDefinition() + "\n");
    }

  }

  public void wordUpdate() {
    System.out.print("Enter the word you would like to update: ");
    String word = scnr.nextLine();
    System.out.print("Enter the new definition of the word: ");
    String definition = scnr.nextLine();
    if (backend.updateWord(word, definition)) {
      System.out.println("The word was updated successfully!");
    } else {
      System.out.println("The word was not found in the dictionary!");
      IWord doYouMean = backend.doYouMean(word);
      System.out.println("Do you mean: " + doYouMean.getWord() + "?");
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
    List<IWord> searchResult;
    String userKeyword;

    System.out.println("How would you like to search?");
    System.out.println("\t1) Search a single word");
    System.out.println("\t2) List words starting with...");
    System.out.print("Choose a command from the menu above: ");
    int input = scnr.nextInt();
    if (input == 1) {
      System.out.print("Write a word that you would like to search for: ");
      userKeyword = this.scnr.next();
      this.scnr.nextLine();
      searchResult = this.backend.searchByWord(userKeyword.toLowerCase());

    } else if (input == 2) {
      System.out.print("Write start letters that you would like to search for: ");
      userKeyword = scnr.next();
      this.scnr.nextLine();
      searchResult = this.backend.searchByStart(userKeyword.toLowerCase());
    } else {
      System.out.println("Invalid input!");
      return;
    }
    if (searchResult.size() == 0) {
      System.out.println("The word that you searched was NOT FOUND in the dictionary!");
      IWord doYouMean = backend.doYouMean(userKeyword);
      System.out.println("Do you mean: " + doYouMean.getWord() + "?");
      return;
    }
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
    IWord newWord = new Word(userWord, userDef, userLC, true);
    boolean added = this.backend.addWords(newWord);
    if (added)
      System.out.println("The Word “" + userWord + "” was added successfully.\n");
    else {
      List<IWord> words = this.backend.searchByWord(userWord.toLowerCase());
      IWord word = null;
      for (int i = 0; i < words.size(); i++) {
        if (words.get(i).getWord().toLowerCase().equals(newWord.getWord().toLowerCase()))
          word = words.get(i);
      }
      String warning = "";
      if (word.isUserGenerated())
        warning = "[ATTENTION THIS WORD WAS ADDED BY A USER]";
      System.out.println(
          "\tTHE WORD ALREADY EXIST IN THE DICTIONARY:" + "\n\tDefinition:   " + warning + "\n\t"
              + word.getDefinition() + "\n");

    }
    ;

  }

  //a simple summary of what the app does and it creators
  @Override
  public void about() {
    System.out.println(
        "\tThe English Dictionary Project was implemented and created in group efforts by group BU-red."
            + "\n\tOur Team consisting of Yunhao Jiang(data wrangler), Danny Jiang(algorithm engineer),"
            + "\n\tSangho Jeon(backend developer),and Mojtaba Javid(frontend developer), has created an"
            + "\n\tever-growing app that provides definitions for aroung "
            + backend.getNumberOfWords() + " words.\n");
  }

}
