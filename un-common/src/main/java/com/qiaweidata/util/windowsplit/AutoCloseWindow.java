package com.qiaweidata.util.windowsplit;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

public class AutoCloseWindow extends JFrame {

    @Serial
    private static final long serialVersionUID = -5601337478724457966L;
    private final WindowModel model;
    private MessagePanel messagePanel;
    private CountdownPanel countdownPanel;
    private JPanel mainPanel;
    
    public AutoCloseWindow(WindowModel model) {
        this.model = model;
        initFrame();
        createComponents();
        layoutComponents();
    }
    
    private void initFrame() {
        setTitle(WindowConfig.WINDOW_TITLE);
        setSize(WindowConfig.WINDOW_SIZE);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setAlwaysOnTop(true);
    }
    
    private void createComponents() {
        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(
            WindowConfig.BORDER_TOP,
            WindowConfig.BORDER_LEFT,
            WindowConfig.BORDER_BOTTOM,
            WindowConfig.BORDER_RIGHT
        ));
        
        messagePanel = new MessagePanel(model.getMessage());
        countdownPanel = new CountdownPanel();
    }
    
    private void layoutComponents() {
        mainPanel.add(messagePanel, BorderLayout.CENTER);
        mainPanel.add(countdownPanel, BorderLayout.SOUTH);
        add(mainPanel);
    }
    
    public void updateCountdownDisplay(int seconds) {
        countdownPanel.updateCountdown(seconds);
    }
    
    public void updateClosingDisplay() {
        countdownPanel.updateClosingState();
    }
    
    public MessagePanel getMessagePanel() {
        return messagePanel;
    }
    
    public CountdownPanel getCountdownPanel() {
        return countdownPanel;
    }
    
    public void closeWindow() {
        dispose();
    }
}