// java8, with ANTLR4, generates a g4 file that can parse formulas like if(){}else{}
 and can evaluate them.
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Main {
    public static void main(String[] args) throws Exception {
        CharStream input = CharStreams.fromString("if (x) foo() else bar()");
        IfExprLexer lexer = new IfExprLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        IfExprParser parser = new IfExprParser(tokens);
        ParseTree tree = parser.ifExpr();
        System.out.println(tree.toStringTree(parser));
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new IfExprListener(), tree);
    }
}