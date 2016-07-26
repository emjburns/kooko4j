package com.veritas.kooko4j;

import java.util.concurrent.TimeUnit;

public class CommonTest {

    public static final Integer WAIT_TIME_SEC = 5;

    public void log(Object msg){
        String op = "   >> ";
        System.out.println(op + msg);
    }

    public void waitforit(){
        waitforit(WAIT_TIME_SEC);
    }

    public void waitforit(Integer waitTimeSec){
        System.out.println("  ..waiting " + waitTimeSec + " seconds");
        try{
            TimeUnit.SECONDS.sleep(waitTimeSec);
        } catch (Exception E){
            //dont care
        }
    }
}
