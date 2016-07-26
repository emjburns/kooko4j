To run, use the run script

```
./run.sh
```

To run without logging, change the run script to the no logging option.

To switch to file logging, edit /src/main/resources/log4j.properties

```
# comment/uncomment to toggle console logging
log4j.rootLogger=DEBUG, CONSOLE

# comment/uncomment to toggle file logging
# file is at top level in log4j.log
log4j.rootLogger=DEBUG, FILE
```
