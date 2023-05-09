// Parse the if else syntax with ANTLR4. Support Boolean type, write a g4 file
 that parses the if else syntax and generates an AST. Demonstrate how to parse and generate an AST. Also, complete tasks such as: 
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class IfElse {
    public static void main(String[] args) throws Exception {
        String code = "if (x) foo(); else bar();";
        IfElseLexer lexer = new IfElseLexer(CharStreams.fromString(code));
        lexer.removeErrorListeners();
        lexer.addErrorListener(ConsoleErrorListener.INSTANCE);
        IfElseParser parser = new IfElseParser(new CommonTokenStream(lexer));
        parser.removeErrorListeners();
        parser.addErrorListener(ConsoleErrorListener.INSTANCE);
        ParseTree tree = parser.if_else();
        System.out.println(tree.toStringTree(parser));
        // prints something like:
        //
        // PARSER_GRAMMAR
        // Root
        //   : 'if' '(' expression ')' statement
        //   | 'if' '(' expression ')' statement 'else' statement
        //   ;
        //
        // expression
        //   : '(' expression ')'
        //   ;
        //
        // statement
        //   : 'foo'
        //   ;
        //
        // 'foo'
        //   : ';'
        //   ;
        //
        // 'if' '(' expression ')' statement 'else' statement
        //   ;
    }
}

class ConsoleErrorListener extends BaseErrorListener {
    static final ConsoleErrorListener INSTANCE = new ConsoleErrorListener();
    @Override public void syntaxError(Recognizer<?,?> recognizer, Object o, int i, int i1, String s,
            RecognitionException e) {
        System.err.println("line " + ((Symbol)o).getLine() + ":" + ((Symbol)o).getCharPositionInLine() + ": error: " + s);
    }
}

class IfElseParser {
    static class Symbol {
        int type;
        int line, pos;
        String text;
        Symbol(int type, int line, int pos, String text) {
            this.type = type;
            this.line = line;
            this.pos = pos;
            this.text = text;
        }
    }
    static class Expr {
        int type;
        int line, pos;
        String text;
        Expr(int type, int line, int pos, String text) {
            this.type = type;
            this.line = line;
            this.pos = pos;
            this.text = text;
        }
    }
    static class Stmt {
        int type;
        int line, pos;
        String text;
        Stmt(int type, int line, int pos, String text) {
            this.type = type;
            this.line = line;
            this.pos = pos;
            this.text = text;
        }
    }
    static class ExprStmt {
        Expr expr;
        Stmt stmt;
        ExprStmt(Expr expr, Stmt stmt) {
            this.expr = expr;
            this.stmt = stmt;
        }
    }
    static class StmtExpr {
        Stmt stmt;
        Expr expr;
        StmtExpr(Stmt stmt, Expr expr) {
            this.stmt = stmt;
            this.expr = expr;
        }
    }
    static class StmtList {
        Stmt stmt;
        StmtList(Stmt stmt) {
            this.stmt = stmt;
        }
<h1><p>Bad Request</p></h1>