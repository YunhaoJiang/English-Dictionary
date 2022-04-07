run:
	javac EnglishDictionaryApp.java
	java EnglishDictionaryApp

runDataWranglerTests:
	javac -cp .:junit5.jar DataWranglerTests.java
	java -jar junit5.jar --class-path . --scan-class-path -n DataWranglerTests

runAlgorithmEngineerTests:
	javac -cp .:junit5.jar AlgorithmEngineerTests.java
	java -jar junit5.jar --class-path . --scan-class-path -n AlgorithmEngineerTests

runBackendDeveloperTests:
	javac -cp .:junit5.jar BackendDeveloperTests.java
	java -jar junit5.jar --class-path . --scan-class-path -n BackendDeveloperTests

runFrontendDeveloperTests:
	javac -cp .:junit5.jar FrontendDeveloperTests.java
	java -jar junit5.jar --class-path . --scan-class-path -n FrontendDeveloperTests

runTests: runDataWranglerTests runAlgorithmEngineerTests runBackendDeveloperTests runFrontendDeveloperTests

clean:
	rm *.class
