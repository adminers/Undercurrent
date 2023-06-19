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
 // Java draws 30 10x10 pixel black squares in the center of the Swing GUI\n
" +
//  https://codegeex.cn
import javax.swing.*;
import java.awt.*;

public class Test extends JFrame{
    public Test(){
        super("Test");
        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> new Test());
    }
}   
  </code></pre>

</body>
</html>