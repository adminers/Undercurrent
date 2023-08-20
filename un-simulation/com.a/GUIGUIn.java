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
 // 创建一个包含 10 个黑色的正方形的窗口。可以使用 GUI 组件库中的正方形组件来创建一个正方形窗口。在窗口中添加一个按钮,该按钮将引发一个事件处理程序,用于在单击按钮时绘制正方形。可以使用 GUI 事件处理程序的内置方法来绘制正方形,例如使用正方形的拼写检查方法来检查正方形的拼写是否正确,并根据检查结果调整正方形的大小和颜色。\n

// 创建一个包含 10 个黑色正方形的窗口。可以使用 GUI 组件库中的正方形组件来创建一个正方形窗口。在窗口中添加一个按钮,该按钮将引发一个事件处理程序,用于在单击按钮时绘制正方形。可以使用 GUI 事件处理程序的内置方法来绘制正方形,例如使用正方形的拼写检查方法来检查正方形的拼写是否正确,并根据检查结果调整正方形的大小和颜色。\n
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DrawSquares extends JFrame {
    private JButton button;
    private JPanel panel;

    public DrawSquares() {
        super("Draw Squares");
        button = new JButton("Draw Squares");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                drawSquares();
            }
        });
        panel = new JPanel();
        panel.add(button);
        add(panel);
        setSize(300, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void drawSquares() {
        Graphics g = getGraphics();
        if (g != null) {
            g.setColor(Color.BLUE);
            g.fillRect(10, 10, 100, 100);
            g.setColor(Color.RED);
            g.fillRect(10, 120, 100, 100);
            g.setColor(Color.GREEN);
            g.fillRect(120, 10, 100, 100);
            g.setColor(Color.YELLOW);
            g.fillRect(120, 120, 100, 100);<h1><p>Bad Request</p></h1>   
  </code></pre>

</body>
</html>