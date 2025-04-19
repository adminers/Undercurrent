package com.qiaweidata.util;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
public class Cuttle {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("360监听");
            JLabel label = new JLabel("打开软件", JLabel.CENTER);
            frame.add(label);
            frame.setSize(300, 200);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}