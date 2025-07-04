package com.rondeo.pixwarsspace.utils.test;

import java.awt.*;
import java.io.IOException;

public class WindowsToastNotification {
    public static void showToast(String title, String message) throws IOException, AWTException {
        if (System.getProperty("os.name").contains("Windows 10")) {
            String script = String.format("$notification = New-Object -ComObject Wscript.Shell $notification.Popup(\"%s\", 0, \"%s\", 0)", message, title);
            
            String powershellCommand = "powershell.exe -Command \"" + script + "\"";
            System.out.println(powershellCommand);
            Runtime.getRuntime().exec(powershellCommand);
        } else {
            // 回退到传统方法
            SystemTray tray = SystemTray.getSystemTray();
            Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
            
            TrayIcon trayIcon = new TrayIcon(image, "Java Notification");
            trayIcon.setImageAutoSize(true);
            trayIcon.setToolTip("Java Notification");
            tray.add(trayIcon);
            
            trayIcon.displayMessage(title, message, TrayIcon.MessageType.INFO);
        }
    }
    
    public static void main(String[] args) throws IOException, AWTException {
        showToast("Java Toast通知", "这是一条Windows 10 Toast通知!");
    }
}