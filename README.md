## GPS Feed Integration

### Getting started
- Download and install Java 11. We recommend the OpenJDK version over the Oracle version.
    - [Windows](https://medium.com/w-logs/installing-java-11-on-macos-with-homebrew-7f73c1e9fadf)
    - [Mac with homebrew](https://medium.com/w-logs/installing-java-11-on-macos-with-homebrew-7f73c1e9fadf)
- [Install Maven](https://maven.apache.org/install.html)

### Running the code

Run tests:
```
mvn test
```
You should see 1 passing test.

Build the code:
```
mvn package
```

Run the code:
```
java -jar target/Main.jar
```