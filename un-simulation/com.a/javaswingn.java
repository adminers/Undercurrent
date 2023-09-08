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
 // java swing 画一个面板，在面板中心有一个文本叫名称，和一个按钮叫提交\n

// 提交按钮的点击事件是：将文本框中的内容显示到文本框中，并清空文本框

package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame("测试窗口");
        jFrame.setSize(400, 300);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel jPanel = new JPanel();
        jFrame.add(jPanel);

        JLabel jLabel = new JLabel("请输入您的姓名：");
        jPanel.add(jLabel);

        JTextField jTextField = new JTextField(10);
        jPanel.add(jTextField);

        JButton jButton = new JButton("提交");
        jPanel.add(jButton);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = jTextField.getText();
                System.out.println("姓名：" + name);
            }
        });

        // 显示窗口
        jFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new TestFrame();
    }
}

class TestFrame extends JFrame {
    public TestFrame() {
        // 设置窗口大小
        setSize(400, 300);
        // 设置窗口居中
        setLocationRelativeTo(null);
        // 设置窗口关闭
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置窗口标题
        setTitle("测试窗口");
        // 设置窗口布局
        setLayout(new FlowLayout());
        // 添加组件
        add(new JLabel("姓名："));
        add(new JTextField(20));
        // 添加按钮
        add(new JButton("确定"));
        // 添加监听器
        add(new JButton("确定"), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取文本框内容
                String name = ((JTextField) getComponent(1)).getText();
                // 弹出提示框
                JOptionPane.showMessageDialog(null, "姓名：" + name);
            }
        });
        // 显示窗口
        setVisible(true);
    }

    public static void main(String[] args) {
        new TestFrame();
    }
}

class TestFrame extends JFrame {
    public TestFrame() {
        // 设置窗口大小<h1><p>Bad Request</p></h1>   
  </code></pre>

</body>
</html>