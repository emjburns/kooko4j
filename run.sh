#!/bin/bash

PRJ="$(cd `dirname $0`; pwd)"

java -Dlog4j.configuration=file:${PRJ}/src/main/resources/log4j.properties -jar target/kooko4j-1.0-SNAPSHOT.jar

# use this for no logging
#java -jar target/kooko4j-1.0-SNAPSHOT.jar
