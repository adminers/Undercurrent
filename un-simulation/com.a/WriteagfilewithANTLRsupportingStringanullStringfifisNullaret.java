// Write a g4 file with ANTLR4, supporting String a = null; String f = "if (isNull(a)){return 1; }else{return 2; }"
 and calling f() with the null literal in the input.
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class NullTest {
    public static void main(String[] args) throws Exception {
        NullTest nt = new NullTest();
        CharStream input = CharStreams.fromString("a = null; if (isNull(a)){return 1; }else{return 2; }");
        G4BaseListener listener = new G4BaseListener() {
            @Override public void exitAssign(G4Parser.Assign_exprContext ctx) {
                ParseTree tree = ctx;
                for (int i = 0; i < ctx.getChildCount(); i++) {
                    tree = ctx.getChild(i);
                    if (tree.getChildCount() == 2) {
                        break;
                    }
                }
                System.out.println(tree.toStringTree(parser));
            }
        };
        G4Lexer lexer = new G4Lexer(input);
        lexer.removeErrorListeners();
        lexer.addErrorListener(ConsoleErrorListener.INSTANCE);
        G4Parser parser = new G4Parser(new CommonTokenStream(lexer));
        parser.removeErrorListeners();
        parser.addErrorListener(ConsoleErrorListener.INSTANCE);
        ParseTree tree = parser.expr();
        System.out.println("\n\n" + tree.toStringTree(parser));
        walker = new ParseTreeWalker();
        walker.walk(listener, tree);
        if (nt.isNull()) {
            System.out.println("\n\nTest 1 passed");
        } else {
            System.out.println("\n\nTest 1 failed");
        }
    }
}

public class Test {
    public static void main(String[] args) {
        new Test().test1();
    }
}

class ConsoleErrorListener extends BaseErrorListener {
    public static ConsoleErrorListener INSTANCE = new ConsoleErrorListener();
    @Override
    public void syntaxError(Recognizer<?,?> recognizer, Object o, int i, int i1, String s,
            RecognitionException e) {
        System.err.println("line " + ((Symbol)o).getLine() + ":" + ((Symbol)o).getCharPositionInLine() + ": error: " + s);
    }
}

class Symbol {
    public int line;
    public int charPos;
    public Object value;
    public Symbol(int line, int charPos, Object value) {
        this.line = line;
        this.charPos = charPos;
        this.value = value;
    }
    public Symbol(int line, int charPos) {
        this(line, charPos, null);
    }
    @Override
    public String toString() {
        if ( value instanceof Integer ) {
            return String.format("%3d:%3d '%s'", line, charPos, (Integer)value);
        }
        if ( value instanceof String ) {
            return String.format("%3d:%3d '%s'", line, charPos, (String)value);
        }
        return String.format("%3d:%3d (unknown)", line, charPos);
    }
}

class Test {
    public static void main(String[] args) {
        new CodeGeeX().run();
    }
}
