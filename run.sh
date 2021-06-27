#!/bin/bash
export JAVA_PROGRAM_ARGS=`echo "$@"`
echo "$JAVA_PROGRAM_ARGS"
mvn clean package &>1
java -cp target/mercateo-coding-challenge-1.0-SNAPSHOT.jar com.mercateo.controller.MainController $JAVA_PROGRAM_ARGS