# Assignment: Package Challenge [![Build Status](https://travis-ci.com/mozammal/mercateo-coding-challenge.svg?branch=main)](https://travis-ci.com/mozammal/mercateo-coding-challenge)## Instructions and Requirements for Running the applicationFor building and running the application you need:- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)- [Maven 3](https://maven.apache.org)## Running Locally1.One way to run the application is to use the java command from the command line:Use this sample command if the input file is read from the src/main/resources folder.```shellunzip mercateo-coding-challenge.zipcd mercateo-coding-challengemvn clean packagejava -cp target/mercateo-coding-challenge-1.0-SNAPSHOT.jar com.mercateo.controller.MainController sampleInput.txt```Use this sample command if the input file is read from absolute file paths```shellunzip mercateo-coding-challenge.zipcd mercateo-coding-challengemvn clean packagejava -cp target/mercateo-coding-challenge-1.0-SNAPSHOT.jar com.mercateo.controller.MainController /home/mozammaltomal_1001/app/test/test/coding-challenge/sampleInput.txt```2.Once all dependencies are installed, you can run the application from command line using the maven exec plugin:Use this sample command if the input file is read from the src/main/resources folder```shellunzip mercateo-coding-challenge.zipcd mercateo-coding-challengemvn clean packagemvn exec:java -Dexec.args="sampleInput.txt"```Use this sample command if the input file is read from absolute file path```shellunzip mercateo-coding-challenge.zipcd mercateo-coding-challengemvn clean packagemvn exec:java -Dexec.args="/home/mozammaltomal_1001/app/test/test/coding-challenge/sampleInput.txt"```3.Yet Another way to run the application is to use the run.sh script from the command line.This bash script was written to run the app on linux and tested on gcloud.Use this sample command if the input file is read from the src/main/resources folder```shellunzip mercateo-coding-challenge.zipcd mercateo-coding-challengechmod u+x run.shbash run.sh sampleInput.txt```Use this sample command if the input file is read from absolute file path```shellunzip mercateo-coding-challenge.zipcd mercateo-coding-challengechmod +x run.shbash run.sh /home/mozammaltomal_1001/app/test/test/coding-challenge/sampleInput.txt```## Assignment: Package Challenge## Problem DescriptionYou want to send your friend a package with different items. You can choose from a number of `N` items. The items are numbered from 1 to `N`. Each one of these items has a given weight and a given cost (in €), where the weights and costs of the items might be different. The package itself has a weight limit. The combined weight of the items you put in the package must not exceed the weight limit of the package, otherwise the package would be too heavy.Your goal is to determine which items to put in the package so that the total cost of the items you put inside is as large as possible. In case the total cost the of the packaged items is the same for two sets of items, you should prefer the combination of items which has a lower total weight.## Constraints1. The maximum weight that a package can hold must be <= 100.2. There may be up to 15 items you can to choose from.3. The maximum weight of an item should be <= 100.4. The maximum cost of an item should be <= €100.## Program SpecificationWrite a program, preferably in Java, which can be run on the command line in order to solve this problem. The program should take one command line argument, which contains the path to a text file. This text file should contain several lines, each line describing one test case for the problem.Each line starts with the maximum weight of the package for this test case. It is followed by ` : ` and then the list of descriptions of the items available for packaging. Each item description contains, in parentheses, the item's number, starting at 1, its weight and its cost (preceded by a € sign).In case of a constraint violation, your program should indicate this fact to the user, for example by throwing an exception with a descriptive message, allowing the user to address this problem.### Sample InputA sample input file looks like this:```81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)8 : (1,15.3,€34)75 : (1,85.31,€29) (2,14.55,€74) (3,3.98,€16) (4,26.24,€55) (5,63.69,€52) (6,76.25,€75) (7,60.02,€74) (8,93.18,€35) (9,89.95,€78)56 : (1,90.72,€13) (2,33.80,€40) (3,43.15,€10) (4,37.97,€16) (5,46.81,€36) (6,48.77,€79) (7,81.80,€45) (8,19.36,€79) (9,6.76,€64)```### Sample OutputThe solution for each test case should be printed out on a single line. On this line you should list the item numbers of the individual items to be put in the package to solve the problem. The numbers should be separated by commas. If no combination of items matches the requirements, the output should be a single `-`.The sample output for the sample input file above should look like this:```4-2,78,9```## Concluding RemarksApply best software design and development practices and document your approach, in particlar regarding the algorithms and data structures you chose. Don't forget to put comments into your source files.Good luck!## Thought process for the provided solution  ## Algorithms and Data StructureThe first step to building this application is to create a deserialize that can read input from fileby implementing a parser which takes a sequence of characters and tries to match the sequence against the specified grammar. Grammar describes the syntactic rules that validate if a token is valid. Here'sthe grammar that is used to deserialize items and packages:    init : '(' value1 ',' value ',' '$', value ')'    value ':' : init+     value: NUMBER   value1: INT   INT: [0-9]+   NUMBER: WHOLE((DOT FRACTION)|EXPONENT|(FRACTION EXPONENT))    DOT: '.'   FRACTION: (.[0-9]+)   EXPONENT: (e|E)?(+-)?WHOLE   WHOLE: [0-9]+EntityScanner.java file contains the lexer class that extracts various tokens using the aforementioned grammar rules. It  tokenizes the input, breaking it up into vocabulary symbols using various Tokenizer classessuch as NumberToken.java, SpecialSymbolToken, etc. Token class assigns a token type, value, position , and line number in the file to each token we define in the grammar. This information is used to showerror  message when tokenizer encounters unknown token. EntityScanner.java uses template pattern.EntityDeserializer.java is the tree walker that extracts the package and items by triggering the parse method using the specified grammar. EntityDeserializer.java uses template pattern.The source code for the deserializer parser goes into the  parser folder.The final step to building this application is to implement an exhaustive search that finds the optimalsolution. A brute-force way to do that is to compute all subsets U that do not include a particular element. Then compute all subsets V which do include that element. Each subset set must appear in U or in V, so the final result is {u, V}. The algorithm is recursive, and the base case is when the input set is empty. The time complexity is O(n*2^n), where n is the total number of elements. The space complexity is O(n) due to recursion. The source code for this algorithm lives in the BruteForceSearch.java file.## Project Description### ArchitectureThis project is based on a popular design pattern-  MVC. This pattern helps us develop loosely coupled applications  bysegregating various concerns into the different layers: Mode, View, and Controller.1. Model:   This represents the application data and the business rules around that data. Business rules are   implemented using service layer. The source code related to model and business logic span across the model,    service, parser, and algorithm folder. 2. View: This represents the user interface that takes model as the input and renders it to the user. The source   code related to the view layer for this project can be found in the folder called view.3. Controller: The controller is the single-point of entry into the application and responsible for   handling the requests, populating the model adhering the business rules, and selecting the appropriate   view for the request. The source code for the controller for this app goes into the controller folder.The main method in the MainController controller triggers at application start up time,  retrieves packages anditems from the EntityDeserializerService service. The heart of our application is the BruteForcePackagingSolverServiceservice that employs a brute-force algorithm on these packages and items. The runApp method in the MainController.java file retrieves the optimal solution from the BruteForcePackagingSolverService service , and writes the output to the standard console using the ConsoleDisplayView class. This application also usesa global exception handler so that program can exit gracefully at the time of error in lieu of crashing.You can find the code in the exception folder.### Object-Oriented Programming Practices used in this ApplicationA few best OOP practices used in this project.1. Program to an interface, not and implementation.2. Favor composition most of the time over inheritance.3. Design pattern such as Template pattern, Factory pattern.  ### Things that could be better1. The code violates SOLID principles in a few places.2. Poor exception handling.3. Need to improve the parser. ### Project layoutThis project followed the standard maven directory structure.The src directory is the rootdirectory for both application and test source code. The application source code lives under the src/main/java folder. Any resource files needed at runtime goes into the src/main/resources. Test code, test specific configuration, resource, etc are held in test/java and test/resources.