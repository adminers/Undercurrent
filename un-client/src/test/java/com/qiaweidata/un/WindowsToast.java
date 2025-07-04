package com.rondeo.pixwarsspace.utils.test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WindowsToast {
    public static void showToast(String title, String message) throws IOException {
        // 单行 PowerShell 命令（XML 内联，避免换行问题）
        String psCommand = String.format(
            "powershell -Command \"[Windows.UI.Notifications.ToastNotificationManager, Windows.UI.Notifications, ContentType = WindowsRuntime] | Out-Null; [Windows.Data.Xml.Dom.XmlDocument, Windows.Data.Xml.Dom.XmlDocument, ContentType = WindowsRuntime] | Out-Null;" +
            "$xml = [Windows.Data.Xml.Dom.XmlDocument]::new(); " +
            "$xml.LoadXml('<toast><visual><binding template=\\\"ToastText02\\\"><text id=\\\"1\\\">%s</text><text id=\\\"2\\\">%s</text></binding></visual></toast>'); " +
            "$toast = [Windows.UI.Notifications.ToastNotification]::new($xml); " +
            "[Windows.UI.Notifications.ToastNotificationManager]::CreateToastNotifier(\\\"Microsoft.WindowsCalculator\\\").Show($toast);\"",
            title, message
        );

        // 执行命令并捕获错误输出
        Process process = Runtime.getRuntime().exec(psCommand);
        try (BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
            String line;
            while ((line = errorReader.readLine()) != null) {
                System.err.println("PowerShell Error: " + line); // 打印错误信息
            }
        }
    }

    public static void main(String[] args) throws IOException {
        showToast("Java 通知", "这是一条来自 Java 的 Toast 通知！");
    }

}