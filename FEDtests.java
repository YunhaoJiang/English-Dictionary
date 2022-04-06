import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FEDtests {

	 FDPHEnglishDictionaryBackend backend;
	 
	//BeforeEach annotation makes a method invocked automatically
	 //before each test
	 @BeforeEach
	 public void createInstane() {
		 backend = new FDPHEnglishDictionaryBackend();
		 backend.PH();
	 }
	 
	 //this test checks the functionality of the first command
	 // searchWord
	@Test
	void test1() {
		TextUITester tester = new TextUITester("1\nhey\n4\n");
		IEnglishDictionaryFrontend test = new EnglishDictionaryFrontend(backend);
		test.runCommandLoop();
		String output = tester.checkOutput();	
		assertEquals(true, output.contains("Found 2 matches."));
		assertEquals(true, output.contains("2. whey   [noun]"));
		//   return;
	}
	
	 //this test checks the functionality of the first command
	 // searchWord with a user generated word
	@Test
	void test2() {
		TextUITester tester = new TextUITester("1\nley\nq\n");
		IEnglishDictionaryFrontend test = new EnglishDictionaryFrontend(backend);
		test.runCommandLoop();
		String output = tester.checkOutput();	
		assertEquals(true, output.contains("[ATTENTION THIS WORD WAS ADDED BY A USER]"));
	}
	
	//this test checks the functionality of the second command
	// addWord with a new word
	@Test
	void test3() {
		TextUITester tester = new TextUITester("2\nney\nnoun\nno\n4\n");
		IEnglishDictionaryFrontend test = new EnglishDictionaryFrontend(backend);
		test.runCommandLoop();
		String output = tester.checkOutput();	
		assertEquals(true, output.contains("The Word “Ney” was added successfully."));
		
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
	@Test
	void test5() {
		TextUITester tester = new TextUITester("3\n4\n");
		IEnglishDictionaryFrontend test = new EnglishDictionaryFrontend(backend);
		test.runCommandLoop();
		String output = tester.checkOutput();	
		assertEquals(true, output.contains("Mojtaba Javid(frontend developer)"));
		
	}
	
	//this test checks the functionality of the interface
	//by checking the command inputs
	@Test
	void test6() {
		TextUITester tester = new TextUITester("f\nq\n");
		IEnglishDictionaryFrontend test = new EnglishDictionaryFrontend(backend);
		test.runCommandLoop();
		String output = tester.checkOutput();	
		assertEquals(true, output.contains("3) About the [D]ictionary (the app and creators' information)"));
	}

}
