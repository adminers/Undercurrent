package com.qiaweidata.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Title: FileUtils
 * @Description: FileUtils
 * @date: 2023-10-25
 * @version: V1.0
 */
public class FileUtils {

     public static void convertImageToPdf(String imagePath, String pdfPath) throws IOException, DocumentException {

         try {
            // 读取图像文件
            BufferedImage image = ImageIO.read(new File(imagePath));

            // 计算图像的缩放比例
            float widthScale = PageSize.A4.getWidth() / image.getWidth();
            float heightScale = PageSize.A4.getHeight() / image.getHeight();
            float scale = Math.min(widthScale, heightScale);

            // 创建PDF文档对象，设置页面大小为A4
            Document document = new Document(PageSize.A4);
            // 创建PDF写入器
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfPath));
            // 打开文档
            document.open();
            // 将图像缩放后添加到PDF文档
            Image pdfImage = Image.getInstance(image, null);
            pdfImage.setRotationDegrees(-90); // 设置旋转角度为0度（纵向）
            pdfImage.scaleToFit(PageSize.A4.getWidth(), PageSize.A4.getHeight());
            document.add(pdfImage);
            // 关闭文档
            document.close();
            // 关闭PDF写入器
            writer.close();
            System.out.println("PDF生成成功！");
        } catch (IOException | DocumentException e) {
            System.err.println("生成PDF时出现错误：" + e.getMessage());
        }
    }

    public static void main(String[] args) {
        /*String imagePath = "C:\\Users\\Administrator\\Documents\\团\\签名.jpg";
        String pdfPath = "C:\\Users\\Administrator\\Documents\\团\\签名.pdf";

        try {
            convertImageToPdf(imagePath, pdfPath);
            System.out.println("Image converted to PDF successfully.");
        } catch (IOException | DocumentException e) {
            System.out.println("Error while converting image to PDF: " + e.getMessage());
        }*/
        getPathNames();
    }

      public static void getPathNames() {

        // 指定路径
        String path = "E:\\giteeWork\\GitHub\\pixwars-space-impact\\html\\war\\WEB-INF\\lib";

        File folder = new File(path);
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    System.out.println("<classpathentry kind=\"lib\" exported=\"true\" path=\"libs/" + file.getName() + "\"/>");
                }
            }
        }
    }
}
