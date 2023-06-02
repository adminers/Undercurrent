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
 // Use Swing to draw a red circle in the middle of the GUI window.

import javax.swing.*;
import java.awt.*;

public class DrawCircle extends JPanel {
    private int radius;
    private Color color;
    private int xCenter;
    private int yCenter;
    private static final long serialVersionUID = 1L;
    public DrawCircle(int radius, Color color, int xCenter, int yCenter) {
        this.radius = radius;
        this.color = color;
        this.xCenter = xCenter;
        this.yCenter = yCenter;
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        g.fillOval(xCenter, yCenter, radius, radius);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame();
            DrawCircle d = new DrawCircle(20, Color.RED, 200, 200);
            f.add(d);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setResizable(false);
            f.setTitle("Drawing");
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        });
    }
}   
  </code></pre>

</body>
</html>