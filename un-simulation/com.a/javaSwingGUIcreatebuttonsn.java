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
 // java8,Swing GUI, create 10 buttons;\n
" +
"import javax.swing.*;\n" +
"import java.awt.event.ActionEvent;\n" +
"\n" +
"public class ButtonDemo extends JFrame {\n" +
"    JButton jButton1, jButton2, jButton3, jButton4, jButton5, jButton6, jButton7, jButton8, jButton9;\n" +
"\n" +
"    public ButtonDemo() {\n" +
"        jButton1 = new JButton(""Button 1");\n" +
"        jButton2 = new JButton(""Button 2");\n" +
"        jButton3 = new JButton(""Button 3");\n" +
"        jButton4 = new JButton(""Button 4");\n" +
"        jButton5 = new JButton(""Button 5");\n" +
"        jButton6 = new JButton(""Button 6");\n" +
"        jButton7 = new JButton(""Button 7");\n" +
"        jButton8 = new JButton(""Button 8");\n" +
"        jButton9 = new JButton(""Button 9");\n" +
"        jButton1.addActionListener(new ActionListener() {\n" +
"            @Override\n" +
"            public void actionPerformed(ActionEvent e) {\n" +
"                JOptionPane.showMessageDialog(null, ""This is a JButton"");\n" +
"            }\n" +
"        });\n" +
"        jButton2.addActionListener(e -> JOptionPane.showMessageDialog(null, ""This is a JButton""));\n" +
"        jButton3.addActionListener(e -> JOptionPane.showMessageDialog(null, ""This is a JButton""));\n" +
"        jButton4.addActionListener(e -> JOptionPane.showMessageDialog(null, ""This is a JButton""));\n" +
"        jButton5.addActionListener(e -> JOptionPane.showMessageDialog(null, ""This is a JButton""));\n" +
"        jButton6.addActionListener(e -> JOptionPane.showMessageDialog(null, ""This is a JButton""));\n" +
"        jButton7.addActionListener(e -> JOptionPane.showMessageDialog(null, ""This is a JButton""));\n" +
"        jButton8.addActionListener(e -> JOptionPane.showMessageDialog(null, ""This is a JButton""));\n" +
"        jButton9.addActionListener(e -> JOptionPane.showMessageDialog(null, ""This is a JButton""));\n" +
"        jButton10.addActionListener(e -> JOptionPane.showMessageDialog(null, ""This is a JButton""));\n" +
"        jButton11.addActionListener(e -> JOptionPane.showMessageDialog(null, ""This is a JButton""));\n" +
"        jButton12.addActionListener(e -> JOptionPane.showMessageDialog(null, ""This is a JButton""));\n" +
"        jButton13.addActionListener(e -> JOptionPane.showMessageDialog(null, ""This is a JButton""));\n" +
"        jButton14.addActionListener(e -> JOptionPane.showMessageDialog(null, ""This is a JButton""));\n" +
"        jButton15.addActionListener(e -> JOptionPane.showMessageDialog(null, ""This is a JButton""));\n" +
"        jButton16.addActionListener(e -> JOptionPane.showMessageDialog(null, ""This is a JButton""));\n" +
"        jButton17.addActionListener(e -> JOptionPane.showMessageDialog(null, ""This is a JButton""));\n" +
"        jButton18.addActionListener(e -> JOptionPane.showMessageDialog(null, ""This is a JButton""));\n" +
"        jButton19.addActionListener(e -> JOptionPane.showMessageDialog(null, ""This is a JButton""));\n" +
"        jButton20.addActionListener(e -> JOptionPane.showMessageDialog(null, ""This is a JButton""));<h1><p>Bad Request</p></h1>   
  </code></pre>

</body>
</html>