package com.rondeo.pixwarsspace.utils.test;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;

/**
 * @Title: MsgWindows
 * @Description: MsgWindows
 * @Company: www.qiaweidata.com
 * @author: shenshilong
 * @date: 2025-07-04
 * @version: V1.0
 */
public class MsgWindows {
    public static void main(String[] args) {

        showWindowsNotificationWithIconA("", "");
    }

    private static void run() {

        if (SystemTray.isSupported()) {

            // 创建一个系统托盘图标

            SystemTray tray = SystemTray.getSystemTray();

            Image image = Toolkit.getDefaultToolkit().getImage("E:\\temp\\love_file\\7ef1f0493749ef069e1836166e62594c.png");

            TrayIcon trayIcon = new TrayIcon(image, "Message Notification");


            // 添加弹出菜单

            PopupMenu popup = new PopupMenu();

            MenuItem item = new MenuItem("Show Message");

            item.addActionListener(e -> JOptionPane.showMessageDialog(null, "Hello, World!"));

            popup.add(item);

            trayIcon.setPopupMenu(popup);


            // 添加到系统托盘
            try {


                tray.add(trayIcon);

            } catch (AWTException e) {

                e.printStackTrace();

            }
            JOptionPane.showMessageDialog(null, "Hello, World!");

        }
    }


    public static void showWindowsNotificationWithIconA(String title, String message) {

        // 获取图标文件的URL。这里假设图标位于项目的resources/static/A.ico。
        /*URL iconUrl = GlobalKeyListener.class.getResource("/static/A.ico");
        if (iconUrl == null) {
            System.err.println("无法加载图标资源！");
            return;
        }

        // 将URL转换为适合PowerShell脚本使用的字符串形式，并进行必要的转义
        String file = iconUrl.toString().replace("\\", "/");
        if (!file.contains("file")) {
            try {
                file = extractIconToTemp();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }*/

        // 构建PowerShell脚本
        /*String script = String.format("
                        $ErrorActionPreference = 'Stop';
                        Add-Type -AssemblyName System.Runtime.WindowsRuntime;
                        [Windows.UI.Notifications.ToastNotificationManager, Windows.UI.Notifications, ContentType = WindowsRuntime] | Out-Null;
                        $template = [Windows.UI.Notifications.ToastTemplateType]::ToastImageAndText02;  # 使用带图标的模板
                        $toastContent = [Windows.UI.Notifications.ToastNotificationManager]::GetTemplateContent($template);
                        $toastXml = [xml] $toastContent.GetXml();
                        $toastXml.SelectSingleNode('//text[1]').InnerText = '%s';  # 标题
                        $toastXml.SelectSingleNode('//text[2]').InnerText = '%s';  # 内容
                        $toastXml.SelectSingleNode('//image').Attributes['src'].Value = '%s';  # 图标路径
                        $toastNode = [Windows.Data.Xml.Dom.XmlDocument]::new();
                        $toastNode.LoadXml($toastXml.OuterXml);
                        $toast = [Windows.UI.Notifications.ToastNotification]::new($toastNode);
                        $notifier = [Windows.UI.Notifications.ToastNotificationManager]::CreateToastNotifier('柚柚通知');  # 应用名称
                        $notifier.Show($toast);
                        ",
        title.replace("'", "''"), message.replace("'", "''"), file);

        // 打印构建好的脚本（可选）
        System.out.println(script);

        // 执行构建好的脚本
        WindowsSystemUtil.executeShellScriptEx(script);

        */


        ProcessBuilder processBuilder = new ProcessBuilder("dir");
        List<String> command = processBuilder.command();
        System.out.println(command);
    }

    public static String extractIconToTemp() throws IOException {

        // 使用ClassLoader加载资源
        InputStream inputStream = null;

        if (inputStream == null) {
            throw new FileNotFoundException("图标文件未找到！");
        }

        try {
            // 创建临时文件
            File tempFile = File.createTempFile("appicon", ".ico");
            tempFile.deleteOnExit(); // 程序正常退出时删除临时文件

            // 将输入流的数据写入临时文件
            try (OutputStream outputStream = new FileOutputStream(tempFile)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }

            // 返回临时文件的绝对路径
            return tempFile.getAbsolutePath();
        } finally {
            inputStream.close();
        }
    }


}
