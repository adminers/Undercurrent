//package com.qiaweidata.work;
//
//import org.apache.poi.xwpf.usermodel.XWPFDocument;
//import org.apache.poi.xwpf.usermodel.XWPFParagraph;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
//public class HtmlToWordConverter {
//    public static void main(String[] args) {
//        try {
//            // 读取 HTML 文件
//            File htmlFile = new File("C:\\Users\\Administrator\\Music\\test.html");
//            Document doc = Jsoup.parse(htmlFile, "UTF-8");
//
//            // 创建 Word 文档
//            XWPFDocument document = new XWPFDocument();
//            FileOutputStream out = new FileOutputStream(new File("output.docx"));
//
//            // 解析 HTML 内容并添加到 Word 文档
//            Elements elements = doc.body().children();
//            for (Element element : elements) {
//                XWPFParagraph paragraph = document.createParagraph();
//                paragraph.createRun().setText(element.text());
//            }
//
//            // 保存 Word 文档
//            document.write(out);
//            out.close();
//
//            System.out.println("Word 文档生成成功！");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}