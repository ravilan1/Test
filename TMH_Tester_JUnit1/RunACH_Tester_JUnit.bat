ECHO OFF 
REM Requires 32 bit IBM Java 
"C:\Program Files (x86)\IBM\Java70\jre\bin\java" -classpath .;bin;lib\* org.junit.runner.JUnitCore utc.tmh.tester.JUnit_ACH_Tester

