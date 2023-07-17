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

// Create a GUI application with a window containing 10 black squares. You can create a black square using the GUI component library's square component. Add a button that raises an event handler when clicked. Use the built-in event handling methods of the square spelled check method to check if the square is spelled correctly and adjust its size and color accordingly.
import java.awt.*;
import javax.swing.*;

public class BlackSquares {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(BlackSquares::createAndShowGui);
    }

    private static void createAndShowGui() {
        JFrame frame = new JFrame("BlackSquares");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (int i = 0; i < 10; i++) {
                    g.setColor(Color.BLACK);
                    g.fillRect(i * 50, i * 50, 50, 50);
                }
            }
        };
        panel.setPreferredSize(new Dimension(500, 500));
        frame.add(panel);

        JButton button = new JButton("Click me!");
        button.addActionListener(e -> {
            Graphics g = panel.getGraphics();
            g.setColor(Color.BLUE);
            g.fillRect(250, 250, 50, 50);
        });
        frame.add(button, BorderLayout.SOUTH);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
   
  </code></pre>

</body>
</html>