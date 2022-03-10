import java.util.Collection;

/**
 * Instances of classes that implement this interface can be used to drive a
 * console-based text user interface for the Eng Dictionary App.
 */
public interface IWordSearcherFrontend {

    // constructor args (IWordSearcherBackend) reads input from System.in
    // constructor args (String, IWordSearcherBackend) reads input from String

    /**
     * This method drives the entire read, eval, print loop (repl) for the
     * Eng Dictionary App.  This loop will continue to run until the user 
     * explicitly enters the quit command. 
     */
    void runCommandLoop();

    // to help make it easier to test the functionality of this program, 
    // the following helper methods will help support runCommandLoop():

    public void displayCommandMenu(); // prints command options to System.out

    public void dictionary(Collection<IWord> Words); // displays a list of words

    public void wordSearch(); // reads word from System.in, displays results
    
    public void wordAdd(); // reads word from System.in, if the word is not in the dictionary, asks for a definition and adds it to the database
    						//with the message [ATTENTION THIS WORD WAS ADDED BY A USER] attached to it
    
    public void about(); // prints info about the app
}
