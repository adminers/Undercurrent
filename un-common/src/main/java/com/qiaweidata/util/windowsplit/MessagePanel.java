package com.qiaweidata.util.windowsplit;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

public class MessagePanel extends JPanel {

    @Serial
    private static final long serialVersionUID = -268246554521869064L;
    private final JLabel messageLabel;
    
    public MessagePanel(String message) {
        setLayout(new BorderLayout());
        setOpaque(false);
        
        messageLabel = new JLabel(message, SwingConstants.CENTER);
        messageLabel.setFont(WindowConfig.MESSAGE_FONT);
        messageLabel.setForeground(WindowConfig.MESSAGE_COLOR);
        
        add(messageLabel, BorderLayout.CENTER);
    }
    
    public void setMessage(String message) {
        messageLabel.setText(message);
    }
    
    public String getMessage() {
        return messageLabel.getText();
    }
    
    public void updateMessageStyle(Font font, Color color) {
        messageLabel.setFont(font);
        messageLabel.setForeground(color);
    }
}