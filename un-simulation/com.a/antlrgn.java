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
 // 创建一个antlr4的g4文件。要求实现简单的加减乘除计算。如：1 2=3\n
4 5=-2\n任务：创建一个antlr4的g4文件。实现简单的加减乘除计算。
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class Antlr4 {
    public static void main(String[] args) throws Exception {
        String code = "1 2=3\n4 5=-2\n";
        Antlr4Lexer lexer = new Antlr4Lexer(CharStreams.fromString(code));
        Antlr4Parser parser = new Antlr4Parser(new CommonTokenStream(lexer));
        ParseTree tree = parser.prog();
        System.out.println(tree.toStringTree(parser));
    }
}   
  </code></pre>

</body>
</html>