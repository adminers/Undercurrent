package com.qiaweidata.un;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import org.junit.Test;

/**
 * @Title: DingUploadTest
 * @Description: DingUploadTest
 * @Company: www.wrenchdata.com
 * @author: shenshilong
 * @date: 2023-04-07
 * @version: V1.0
 */
public class DingUploadTest {

    @Test
    public void singleFileUpload() throws IOException {

        // 从接口返回信息中拿到resourceUrls
        String resourceUrl = "第一步接口返回的resourceUrls";
        // 从接口返回信息中拿到headers
        Map<String, String> headers = null;//"第一步接口返回的headers";
        URL url = new URL(resourceUrl);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        connection.setDoOutput(true);
        connection.setRequestMethod("PUT");
        connection.setUseCaches(false);
        connection.setReadTimeout(10000);
        connection.setConnectTimeout(10000);
        connection.connect();
        OutputStream out = connection.getOutputStream();
        InputStream is = new FileInputStream(new File("/Users/xxxxx/Desktop/测试文件.xls"));
        byte[] b =new byte[1024];
        int temp;
        while ((temp=is.read(b))!=-1){
            out.write(b,0,temp);
        }
        out.flush();
        out.close();
        int responseCode = connection.getResponseCode();
        connection.disconnect();
        if (responseCode == 200) {
            System.out.println("上传成功");
        } else {
            System.out.println("上传失败");
        }
    }

    @Test
    public void corpId() {

        //读取resources/config目录下的application.properties
        ResourceBundle rb2 = ResourceBundle.getBundle("ding");
        for(String key : rb2.keySet()){
            String value = rb2.getString(key);
            System.out.println(key + ":" + value);
        }
    }
}
