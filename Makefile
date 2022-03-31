runTests: EnglishDictionaryBackendTest.class
	java EnglsihDictionaryuBackendTest

EnglishDictionaryBackendTest.class: EnglishDictionaryBackendTest.java EnglishDictionaryBackend.class
	javac EnglishDictionaryBackendTest.java

EnglishDictionaryBackend.class: EnglishDictionaryBackend.java IEnglishDictionaryBackend.class
	javac EnglishDictionaryBackend.java

IEnglishDictionaryBackend.class: IEnglishDictionaryBackend.java
	javac IEnglishDictionaryBackend.java

clean:
	rm *.class
