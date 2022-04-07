import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FrontendDeveloperTests {

	 FDPHEnglishDictionaryBackend backend;
	 EnglishDictionaryBackend backend_AdditionalT;
	 
	//BeforeEach annotation makes a method invoked automatically
	 //before each test
	 @BeforeEach
	 public void createInstane() {
		 backend = new FDPHEnglishDictionaryBackend();
		 backend.PH();
		 
		 backend_AdditionalT = new EnglishDictionaryBackend();
		 WordLoader wordLoader = new WordLoader();
		 ArrayList<IWord> wordList = new ArrayList<>();
		 try {
		      wordList = (ArrayList<IWord>) wordLoader.loadWords("dictionary.xml");
		    } catch (FileNotFoundException e) {
		      System.out.println("Word Not Found");
		      e.printStackTrace();
		    }
		    for (IWord word : wordList) {
		    	backend_AdditionalT.addWords(word);
		    }
	 }
	 
	 
	 //this test checks the functionality of the first command
	 // searchWord
	 
	//THIS TEST HAS CHANGED DUE TO NEW FUTURES BEING ADDED
	@Test
	void test1() {
		TextUITester tester = new TextUITester("1\n1\nhey\n5\n");
		IEnglishDictionaryFrontend test = new EnglishDictionaryFrontend(backend);
		test.runCommandLoop();
		String output = tester.checkOutput();	
		assertEquals(true, output.contains("Found 2 matches."));
	}
	
	
	 //this test checks the functionality of the first command
	 // searchWord with a user generated word
	
	//THIS TEST HAS CHANGED DUE TO NEW FUTURES BEING ADDED
	@Test
	void test2() {
		TextUITester tester = new TextUITester("1\n1\nley\nq\n");
		IEnglishDictionaryFrontend test = new EnglishDictionaryFrontend(backend);
		test.runCommandLoop();
		String output = tester.checkOutput();	
		assertEquals(true, output.contains("[ATTENTION THIS WORD WAS ADDED BY A USER]"));
	}
	
	
	//this test checks the functionality of the second command
	// addWord with a new word
	
	//THIS TEST HAS CHANGED DUE TO NEW FUTURES BEING ADDED
	@Test
	void test3() {
		TextUITester tester = new TextUITester("2\nney\nnoun\nno\nq\n");
		IEnglishDictionaryFrontend test = new EnglishDictionaryFrontend(backend);
		test.runCommandLoop();
		String output = tester.checkOutput();	
		assertEquals(true, output.contains("added successfully"));
		
	}
	
	//this test checks the functionality of the second command
	//addWord with an existing word
	@Test
	void test4() {
		TextUITester tester = new TextUITester("2\nhey\nnoun\nhello\nq\n");
		IEnglishDictionaryFrontend test = new EnglishDictionaryFrontend(backend);
		test.runCommandLoop();
		String output = tester.checkOutput();	
		assertEquals(true, output.contains("THE WORD ALREADY EXIST IN THE DICTIONARY:"));
	}
	
	
	//this test checks the functionality of the third command
	//about
	
	//THIS TEST HAS CHANGED DUE TO NEW FUTURES BEING ADDED
	@Test
	void test5() {
		TextUITester tester = new TextUITester("4\n5\n");
		IEnglishDictionaryFrontend test = new EnglishDictionaryFrontend(backend);
		test.runCommandLoop();
		String output = tester.checkOutput();	
		assertEquals(true, output.contains("Mojtaba Javid(frontend developer)"));
		
	}
	
	
	//this test checks the functionality of the interface
	//by checking the command inputs
	
	//THIS TEST HAS CHANGED DUE TO NEW FUTURES BEING ADDED
	@Test
	void test6() {
		TextUITester tester = new TextUITester("f\nq\n");
		IEnglishDictionaryFrontend test = new EnglishDictionaryFrontend(backend);
		test.runCommandLoop();
		String output = tester.checkOutput();	
		assertEquals(true, output.contains("About the [D]ictionary"));
	}
	
	
	
	
	//this test checks the functionality of the code
	//in relation to all the other team members code
	//partial search function
		
	//Additional Tests
	@Test
	void test7() {
		TextUITester tester = new TextUITester("1\n2\nhe\n5\n");
		IEnglishDictionaryFrontend test = new EnglishDictionaryFrontend(backend_AdditionalT);
		test.runCommandLoop();
		String output = tester.checkOutput();	
		assertEquals(true, output.contains("Found 25 matches."));
	}
	
	
	//this test checks the functionality of the code
	//in relation to all the other team members code
	//update definition function
			
	//Additional Tests
	@Test
	void test8() {
		TextUITester tester = new TextUITester("3\ncar\nmoving vehicle\n1\n1\ncar\n5\n");
		IEnglishDictionaryFrontend test = new EnglishDictionaryFrontend(backend_AdditionalT);
		test.runCommandLoop();
		String output = tester.checkOutput();	
		assertEquals(true, output.contains("moving vehicle"));
	}
	
	
	//this test checks the functionality of the code
	//Data wrangler's code
	//general word characteristics
				
	//Additional Tests
	@Test
	void test9() {
		Word h = new Word("hodor","GOT charcter","noun", false);
		assertEquals("hodor", h.getWord());
		assertEquals("GOT charcter", h.getDefinition());
		assertEquals(false, h.isUserGenerated());
	}
	
	
	//this test checks the functionality of the code
	//Data wrangler's code
	//general mutator functions
					
	//Additional Tests
	@Test
	void test10() {
		Word h = new Word("hodor","GOT charcter","noun", false);
		h.setUserGenerated(true);
		h.setDefinition("Hold the door");
		assertEquals(true, h.isUserGenerated());
		assertEquals("Hold the door", h.getDefinition());
	}

}
