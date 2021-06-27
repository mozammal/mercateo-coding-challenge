## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Running Locally

1.

One way to run the application is to use the java command from the command line:

Use this sample command if csv files are read from the src/main/resources folder.
```shell
unzip mercateo-coding-challenge.zip
cd mercateo-coding-challenge
mvn clean package
java -cp target/mercateo-coding-challenge-1.0-SNAPSHOT.jar com.mercateo.controller.MainController sampleInput.txt
```

Use this sample command if csv files are read from absolute file paths
```shell
unzip mercateo-coding-challenge.zip
cd mercateo-coding-challenge
mvn clean package
java -cp target/mercateo-coding-challenge-1.0-SNAPSHOT.jar com.mercateo.controller.MainController /home/mozammaltomal_1001/app/test/test/coding-challenge/sampleInput.txt
```

2.

Once all dependencies are installed, you can run the application from command line using the maven exec plugin:

Use this sample command if csv files are read from the src/main/resources folder
```shell
unzip mercateo-coding-challenge.zip
cd mercateo-coding-challenge
mvn clean package
mvn exec:java -Dexec.args="sampleInput.txt"
```

Use this sample command if csv files are read from absolute file paths
```shell
unzip mercateo-coding-challenge.zip
cd mercateo-coding-challenge
mvn clean package
mvn exec:java -Dexec.args="/home/mozammaltomal_1001/app/test/test/coding-challenge/sampleInput.txt"
```

3.

Yet Another way to run the application is to use the run.sh script from the command line. 
This bash script was written to run the app on linux and tested on gcloud.  

Use this sample command if csv files are read from the src/main/resources folder
```shell
unzip mercateo-coding-challenge.zip
cd mercateo-coding-challenge
chmod u+x run.sh
bash run.sh sampleInput.txt
```

Use this sample command if csv files are read from absolute file paths
```shell
unzip mercateo-coding-challenge.zip
cd mercateo-coding-challenge
chmod +x run.sh
bash run.sh /home/mozammaltomal_1001/app/test/test/coding-challenge/sampleInput.txt
```