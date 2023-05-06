// Use ANTLR4 to parse string formulas, a=1, b=2, "select * from table t where t. id=${a} and t. name=${b}" to replace ${a} with 1 and ${b} with 2, and then print out the result set No need to care about any features
 of ANTLR4 that might affect the output.
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class Main {
    public static void main(String[] args) throws Exception {
        String formula = "a=1, b=2, select * from table t where t. id=1 and t. name=2";
        Antlr4FormulaLexer lexer = new Antlr4FormulaLexer(CharStreams.fromString(formula));
        Antlr4FormulaParser parser = new Antlr4FormulaParser(new CommonTokenStream(lexer));
        parser.removeErrorListeners();
        parser.addErrorListener(ConsoleErrorListener.INSTANCE);
        ParseTree tree = parser.formula();
        System.out.println(tree.toStringTree(parser));
    }
}