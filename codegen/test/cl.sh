M2REPO="$HOME/.m2/repository"

WORKSPACE=".."
export BASE="."
export STDLOG="$BASE/std.log"


LIBPATH="$HOME/.m2/repository/org/apache/logging/log4j/log4j-api/2.11.1/log4j-api-2.11.1.jar:$HOME/.m2/repository/org/apache/logging/log4j/log4j-core/2.11.1/log4j-core-2.11.1.jar"



CLASSPATH=".:\
$WORKSPACE/bin:\
$WORKSPACE/target/classes:\
"
CLASSPATH="$CLASSPATH:$LIBPATH"


echo "using classspath $CLASSPATH"

CMD="$JAVA_HOME/bin/java -classpath $CLASSPATH com.ezto.codegen.daocode.view.ClassloaderBuilder $*"

$CMD




