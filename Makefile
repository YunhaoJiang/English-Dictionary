runTests:
	javac -cp .:junit5.jar EnglishDictionaryBackendTest.java
	java -jar junit5.jar -cp . --scan-class-path -n EnglishDictionaryBackendTest
	javac -cp .:junit5.jar DataWranglerTests.java
	java -jar junit5.jar -cp . --scan-class-path -n DataWranglerTests
	javac -cp .:junit5.jar FEDtests.java -Xlint
	java -jar junit5.jar --class-path . --scan-classpath -n FEDtests
	javac -cp .:junit5.jar AlgorithmEngineerTests.java
	java -jar junit5.jar --class-path . --scan-classpath -n AlgorithmEngineerTests

clean:
	rm *.class
