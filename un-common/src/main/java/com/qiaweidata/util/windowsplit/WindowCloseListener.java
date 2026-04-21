package com.qiaweidata.util.windowsplit;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WindowCloseListener extends WindowAdapter {
    
    private final WindowController windowController;
    
    public WindowCloseListener(WindowController windowController) {
        this.windowController = windowController;
    }
    
    @Override
    public void windowClosing(WindowEvent e) {
        windowController.onWindowClosed();
    }
    
    @Override
    public void windowClosed(WindowEvent e) {
        System.out.println("窗口已关闭");
    }
    
    @Override
    public void windowOpened(WindowEvent e) {
        System.out.println("窗口已打开，等待 " + 3 + " 秒后自动关闭");
    }
}