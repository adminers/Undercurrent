package com.qiaweidata.util.windowsplit;

import javax.swing.*;
import java.awt.*;

public class CountdownPanel extends JPanel {
    
    private final JLabel countdownLabel;
    
    public CountdownPanel() {
        setLayout(new BorderLayout());
        setOpaque(false);
        
        countdownLabel = new JLabel("", SwingConstants.CENTER);
        countdownLabel.setFont(WindowConfig.COUNTDOWN_FONT);
        countdownLabel.setForeground(WindowConfig.COUNTDOWN_COLOR);
        
        add(countdownLabel, BorderLayout.CENTER);
    }
    
    public void updateCountdown(int seconds) {
        if (seconds > 0) {
            countdownLabel.setText(seconds + " 秒后自动关闭");
        } else {
            updateClosingState();
        }
    }
    
    public void updateClosingState() {
        countdownLabel.setText("正在关闭窗口...");
    }
    
    public void setCustomText(String text) {
        countdownLabel.setText(text);
    }
    
    public void setCustomStyle(Font font, Color color) {
        countdownLabel.setFont(font);
        countdownLabel.setForeground(color);
    }
}