runTests: runEnglishDictionaryBackendTest

runEnglishDictionaryBackendTest: EnglishDictionaryBackendTest.java
        javac EnglishDictionaryBackendTest.java
        java EnglishDictionaryBackendTest


clean:
        rm *.class
