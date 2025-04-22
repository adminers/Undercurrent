package com.qiaweidata.un;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * @Title: ScreenCaptureTest
 * @Description: ScreenCaptureTest
 * @date: 2023-03-14
 * @version: V1.0
 */
public class ScreenCaptureTest {

    public static void main(String[] args) {

        extracted();
    }


    public static BufferedImage extracted() {

        BufferedImage bufferedImage = null;
        try {

            // 创建一个Robot对象
            Robot robot = new Robot();
            // 获取屏幕的大小
            Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
            // 创建一个Rectangle对象，用于指定截屏的区域
            Rectangle rectangle = new Rectangle(dimension);
            // 捕获屏幕上的内容
            bufferedImage = robot.createScreenCapture(rectangle);
            // 将捕获的内容保存到文件
            ImageIO.write(bufferedImage, "jpg", new File("/Users/myComputer/E/temp/screenshot.jpg"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bufferedImage;
    }

    /**
     * 将BufferedImage转换为byte[]
     * @param image
     * @return
     */
    public static byte[] bufferedImageToByteArray(BufferedImage image) throws IOException{

        try (ByteArrayOutputStream os = new ByteArrayOutputStream();) {
            ImageIO.write(image, "png", os);
            return os.toByteArray();
        }
    }

}
