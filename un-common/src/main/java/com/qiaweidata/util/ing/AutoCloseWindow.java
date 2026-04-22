package com.qiaweidata.util.ing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AutoCloseWindow {

    public static void main(String[] args) {
        // 确保 Swing 组件在事件调度线程中创建
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        // 创建主窗口
        JFrame frame = new JFrame("提示");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null); // 居中显示
        
        // 创建显示文字的标签
        JLabel label = new JLabel("这是一段提示文字，3秒后自动关闭", SwingConstants.CENTER);
        label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        label.setForeground(new Color(51, 51, 51));
        
        // 添加标签到窗口
        frame.add(label, BorderLayout.CENTER);
        
        // 添加倒计时标签（可选）
        JLabel countdownLabel = new JLabel("3秒后自动关闭...", SwingConstants.CENTER);
        countdownLabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        countdownLabel.setForeground(Color.GRAY);
        frame.add(countdownLabel, BorderLayout.SOUTH);
        
        // 显示窗口
        frame.setVisible(true);
        
        // 创建定时器，3秒后关闭窗口
        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // 关闭窗口并释放资源
                // 如果是主窗口需要退出程序，用 System.exit(0);
            }
        });
        timer.setRepeats(false); // 只执行一次
        timer.start();
        
        // 倒计时更新（可选）
        Timer countdownTimer = new Timer(1000, new ActionListener() {
            int seconds = 3;
            @Override
            public void actionPerformed(ActionEvent e) {
                seconds--;
                if (seconds > 0) {
                    countdownLabel.setText(seconds + "秒后自动关闭...");
                } else {
                    countdownLabel.setText("正在关闭...");
                    ((Timer)e.getSource()).stop();
                }
            }
        });
        countdownTimer.start();
    }
}