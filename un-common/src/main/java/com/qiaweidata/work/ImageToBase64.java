package com.qiaweidata.work;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

public class ImageToBase64 {
    public static void main(String[] args) {
        String imagePath = "C:\\Users\\Administrator\\Downloads\\h2048.jpg"; // 指定图片文件的路径
        String base64String = imageToBase64(imagePath);
        System.out.println("data:image/jpeg;base64," + base64String);
    }

    public static String imageToBase64(String imagePath) {
        File file = new File(imagePath);
        try (FileInputStream imageInFile = new FileInputStream(file)) {
            // 读取图片文件为字节数组
            byte[] imageData = new byte[(int) file.length()];
            imageInFile.read(imageData);

            // 使用Base64编码
            return Base64.getEncoder().encodeToString(imageData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
