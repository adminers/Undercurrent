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

// 创建一个包含 10 个黑色正方形的窗口。使用 GUI 组件库中的正方形组件来创建一个正方形窗口。在窗口中添加一个按钮,该按钮将引发一个事件处理程序,用于在单击按钮时绘制正方形。可以使用 GUI 事件处理程序的内置方法来绘制正方形,例如使用正方形的拼写检查方法来检查正方形的拼写是否正确,并根据检查结果调整正方形的大小和颜色。
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Square extends JFrame implements ActionListener {
    private JButton button;
    private JPanel panel;
    private int count = 0;

    public Square() {
        super("Square");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        button = new JButton("Draw");
        button.addActionListener(this);
        add(button, BorderLayout.SOUTH);
        panel = new JPanel();
        add(panel, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        count++;
        panel.repaint();
    }

    public void paint(Graphics g) {
        super.paint(g);
        if (count % 2 == 0) {
            g.setColor(Color.RED);
            g.fillRect(100, 100, 100, 100);
        } else {
            g.setColor(Color.BLUE);
            g.fillRect(200, 200, 100, 100);
        }
    }

    public static void main(String[] args) {
        Square s = new Square();
        s.setVisible(true);<h1><p>Bad Request</p></h1>   
  </code></pre>

</body>
</html>