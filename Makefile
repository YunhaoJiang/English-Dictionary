runTests:
	javac -cp .:junit5.jar DataWranglerTests.java
	java -jar junit5.jar -cp . --scan-class-path -n DataWranglerTests
	javac -cp .:junit5.jar FEDtests.java -Xlint
	java -jar junit5.jar --class-path . --scan-classpath -n FEDtests


clean:
	rm *.class
