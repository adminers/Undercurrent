<!DOCTYPE html>
<html>
<head>
  <title>Highlight.js Example</title>
  <!-- 引入 highlight.js 样式文件 -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.2.0/styles/monokai.min.css">
  <style>
    /* 增加代码行之间的距离 */
    pre {
      line-height: 1.5em;
    }
  </style>
  <!-- 引入 highlight.js 脚本文件 -->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.2.0/highlight.min.js"></script>
  <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>

  <h1>HTML 代码示例</h1>

  <pre><code class="java">
 // Java现在创建一个antlr4的g4文件。要求实现简单的加减乘除计算。如：1 2=3\n
4 5=6\n7 8=9\n10 11=12\n任务：实现一个antlr4的g4文件。
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class Main {
    public static void main(String[] args) throws Exception {
        String code = "1 2=3\n4 5=6\n7 8=9\n10 11=12";
        Antlr4SampleBaseListener listener = new Antlr4SampleBaseListener();
        Antlr4SampleLexer lexer = new Antlr4SampleLexer(CharStreams.fromString(code));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        Antlr4SampleParser parser = new Antlr4SampleParser(tokens);
        ParseTree tree = parser.prog();
        System.out.println(tree.toStringTree(parser));
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(listener, tree);
        System.out.println(listener.prog());
    }
}   
  </code></pre>

</body>
</html>