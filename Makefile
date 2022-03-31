runTests:
	javac -cp .:junit5.jar WordSearcherTreeTest.java -Xlint
	java -jar junit5.jar --class-path . --scan-classpath
clean:
	rm -r *class
