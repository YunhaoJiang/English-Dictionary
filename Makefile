runTests:
	javac -cp .:junit5.jar EnglishDictionaryBackendTest.java
	java -jar junit5.jar -cp . --scan-class-path -n EnglishDictionaryBackendTest
clean:
	rm *.class
