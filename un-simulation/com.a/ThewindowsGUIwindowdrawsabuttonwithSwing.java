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
 // The windows GUI window draws a button with Swing.
 The task is to display a message in that window.
import javax.swing.JFrame;
import javax.swing.JLabel;

public class WindowExample {
    public static void main(String[] args) {
        JFrame f = new JFrame("Hello!");
        JLabel label = new JLabel("Hello World");
        f.add(label);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(300, 100);
        f.setVisible(true);
    }
}   
  </code></pre>

</body>
</html>