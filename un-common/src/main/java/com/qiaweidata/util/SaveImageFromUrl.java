package com.qiaweidata.util;

import java.io.*;
import java.net.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class SaveImageFromUrl {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://cdn.pixabay.com/photo/2016/07/30/19/33/smartphone-1557796_1280.png");
            BufferedImage image = ImageIO.read(url);
            File outputFile = new File("/Users/abc/Downloads/文件名.png");
            ImageIO.write(image, "png", outputFile);
            System.out.println("图片保存成功！");
        } catch (IOException e) {
            System.out.println("图片保存失败：" + e.getMessage());
        }
    }
}