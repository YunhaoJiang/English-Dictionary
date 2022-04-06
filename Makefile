runTests:
	javac -cp .:junit5.jar DataWranglerTests.java
	java -jar junit5.jar -cp . --scan-class-path -n DataWranglerTests
clean:
	rm *.class
