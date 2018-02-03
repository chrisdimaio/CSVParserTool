@ECHO OFF
DEL /Q "C:\Users\cdimaio\Dropbox\MyProjects\DataStuff\CSVParserTool\*.class"

javac -d C:\Users\cdimaio\Dropbox\MyProjects\DataStuff\CSVParserTool\ *.java 

DIR /B *.class

cd "C:\Users\cdimaio\Dropbox\MyProjects\DataStuff\CSVParserTool"
java -cp C:\Users\cdimaio\Dropbox\MyProjects\DataStuff\CSVParserTool\; CSVParserTool %1 %2 %3 %4 %5 %6 %7 %8 %9

ECHO ON