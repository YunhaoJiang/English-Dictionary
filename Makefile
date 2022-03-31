runTests: 
	java -jar junit5.jar --class-path . --scan-classpath	
	 

EnglishDictionaryBackendTest.class: EnglishDictionaryBackendTest.java EnglishDictionaryBackend.class
	javac -cp.:junit5.jar EnglishDictionaryBackendTest.java -Xlint

EnglishDictionaryBackend.class: EnglishDictionaryBackend.java IEnglishDictionaryBackend.class
	javac EnglishDictionaryBackend.java 

IEnglishDictionaryBackend.class: IEnglishDictionaryBackend.java Word.class
	javac IEnglishDictionaryBackend.java

Word.class: Word.java IWord.class
	javac Word.java

IWord.class: IWord.java
	javac IWord.java

clean:
	rm *.class

