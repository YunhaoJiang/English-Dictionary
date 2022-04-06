runBackDeveloperTests:
	javac -cp .:junit5.jar EnglishDictionaryBackendTest.java
	java -jar junit5.jar -cp . --scan-class-path -n EnglishDictionaryBackendTest
runAlgorithmEngineerTests:	
	javac -cp .:junit5.jar AlgorithmEngineerTests.java -Xlint
	java -jar junit5.jar --class-path . --scan-classpath
clean:
	rm -r *class
