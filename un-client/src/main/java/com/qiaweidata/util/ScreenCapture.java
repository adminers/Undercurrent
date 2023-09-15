package com.qiaweidata.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScreenCapture {

    private static final int CAPTURE_INTERVAL = 5000; // 截图时间间隔（毫秒），这里设置为每5秒截图一次

    public static void main(String[] args) {

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(new Runnable() {
            public void run() {

                if (0 == getRunFlag()) {
                    return;
                }
                screen();
            }
        }, 0, 5, TimeUnit.SECONDS);
    }

    public static int getRunFlag() {

        int runFlag = 0;
        try {

            // 创建 URL 对象
            URL url = new URL("http://192.168.1.14:8073/runFlag");

            // 打开连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // 设置请求方式为 GET
            conn.setRequestMethod("GET");

            // 获取响应代码
            int responseCode = conn.getResponseCode();

            // 读取响应的返回值
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            runFlag = Integer.parseInt(response.toString());

            // 关闭连接
            conn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return runFlag;
    }

    private static void screen() {

        try {

            // 截取屏幕截图
            Robot robot = new Robot();
            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage screenshot = robot.createScreenCapture(screenRect);

            // 将截图保存到临时文件
            File tempFile = File.createTempFile("screenshot", ".png");
            ImageIO.write(screenshot, "png", tempFile);

            // 通过URL上传截图文件到服务器
            FileUploader.execute(tempFile);

            // 删除临时文件
            tempFile.delete();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
