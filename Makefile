runTests: FEDtests.class
	java -jar junit5.jar --class-path . --scan-classpath -n FEDtests

FEDtests.class: FEDtests.java
	javac -cp .:junit5.jar FEDtests.java -Xlint


clean:
	rm *.class
