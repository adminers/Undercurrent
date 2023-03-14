package com.qiaweidata.un;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * @Title: FileUploadTest
 * @Description: FileUploadTest
 * @Company: www.wrenchdata.com
 * @author: shenshilong
 * @date: 2023-03-13
 * @version: V1.0
 */
public class FileUploadTest {

    public static void main(String[] args) {

        /*try (FileInputStream input = new FileInputStream("E:\\git\\qiwen-file-master\\README.md");) {
            doPost(input.readAllBytes(), "");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        for (int i = 0; i < 10_000; i++) {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                BufferedImage bufferedImage = ScreenCaptureTest.extracted();
                byte[] bytes = ScreenCaptureTest.bufferedImageToByteArray(bufferedImage);
                doPost(bytes, "");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static String doPost( byte[] fileBytes, String fileName) {

        //拿到fileName拼接URL
        StringBuffer sb=new StringBuffer();
        final String url =
            sb.append("http://localhost:8082/uploadFile?fileId=" + UUID.randomUUID() + "&suffix=.jpg").append(fileName).toString();
        //创建HttpClient实例
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建post方法连接实例，在post方法中传入待连接地址
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;

        try {
            //设置请求参数（类似html页面中name属性）
            MultipartEntityBuilder entity = MultipartEntityBuilder.create();
            entity.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            entity.setCharset(Charset.forName("UTF-8"));
            if(fileBytes!=null) {
                //内容类型，用于定义网络文件的类型和网页的编码，决定文件接收方将以什么形式、什么编码读取这个文件
                ContentType OCTEC_STREAM = ContentType.create("application/octet-stream", Charset.forName("UTF-8"));
                //添加文件
                entity.addBinaryBody("importSource", fileBytes, OCTEC_STREAM, fileName);
            }
            entity.addTextBody("sourceId", "4028818982e871ab0182e8f53fac0099");

            httpPost.setEntity(entity.build());
            httpPost.setHeader("Cookie","JSESSIONID=B7C9690454870704B431C735B46D5230; wrenchdata_uid=de9b7ac4-ee39-4f99-ac51-9bf59ed27f64");



            //发起请求，并返回请求响应
            response = httpClient.execute(httpPost);
            String uploadResult = EntityUtils.toString(response.getEntity(), "utf-8");


            System.out.println(uploadResult);
            return uploadResult;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "文件上传错误";
    }

}
