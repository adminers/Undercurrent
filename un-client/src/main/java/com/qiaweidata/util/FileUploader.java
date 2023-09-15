package com.qiaweidata.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileUploader {

    private static final String UPLOAD_URL = "http://192.168.1.14:8073/upload";

    public static void execute( File file) {

        try {
            URL url = new URL(UPLOAD_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            String boundary = "---------------------------" + System.currentTimeMillis();
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

            OutputStream outputStream = connection.getOutputStream();

            // 添加表单字段
            writeFormField("name", "John Doe", outputStream, boundary);
            writeFormField("age", "25", outputStream, boundary);

            // 添加文件
            writeFileField("file", file, outputStream, boundary);

            // 添加结束边界
            outputStream.write(("--" + boundary + "--").getBytes());
            outputStream.close();

            // 获取响应
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
//                System.out.println("File uploaded successfully");
            } else {
//                System.out.println("Failed to upload file");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeFormField(String fieldName, String fieldValue, OutputStream outputStream, String boundary) throws IOException {
        outputStream.write(("--" + boundary + "\r\n").getBytes());
        outputStream.write(("Content-Disposition: form-data; name=\"" + fieldName + "\"\r\n\r\n").getBytes());
        outputStream.write((fieldValue + "\r\n").getBytes());
    }

    private static void writeFileField(String fieldName, File file, OutputStream outputStream, String boundary) throws IOException {
        outputStream.write(("--" + boundary + "\r\n").getBytes());
        outputStream.write(("Content-Disposition: form-data; name=\"" + fieldName + "\"; filename=\"" + file.getName() + "\"\r\n").getBytes());
        outputStream.write(("Content-Type: application/octet-stream\r\n\r\n").getBytes());

        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = fileInputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        outputStream.write("\r\n".getBytes());
        fileInputStream.close();
    }
}
