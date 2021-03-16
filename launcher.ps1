chcp 65001 | Out-Null;
javac -encoding UTF-8 *.java;
java "-Dfile.encoding=UTF-8" Main;
Remove-Item *.class;