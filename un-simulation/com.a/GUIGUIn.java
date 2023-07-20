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

// 创建一个包含 10 个黑色正方形的窗口。可以使用 GUI 组件库中的正方形组件来创建一个正方形窗口。在窗口中添加一个按钮,该按钮将引发一个事件处理程序,用于在单击按钮时绘制正方形。可以使用 GUI 事件处理程序的内置方法来绘制正方形,例如使用正方形的拼写检查方法来检查正方形的拼写是否正确,并根据检查结果调整正方形的大小和颜色。
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DrawSquares extends JFrame {
    private JButton button;
    private JPanel panel;

    public DrawSquares() {
        super("Draw Squares");
        button = new JButton("Draw Squares");
        button.addActionListener(new ButtonListener());
        panel = new JPanel();
        panel.setLayout(new GridLayout(10, 10));
        for (int i = 0; i < 100; i++) {
            panel.add(new JPanel());
        }
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(button, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setVisible(true);
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < 100; i++) {
                JPanel p = (JPanel) panel.getComponent(i);
                p.setBackground(Color.BLACK);
            }
        }
    }

    public static void main(String[] args) {<h1><p>Bad Request</p></h1>   
  </code></pre>

</body>
</html>