package com.qiaweidata.un;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Title: JspUtil
 * @Description: JspUtil
 * @Company: www.qiaweidata.com
 * @author: shenshilong
 * @date: 2023-04-14
 * @version: V1.0
 */
public class JspUtil {

    /**
     * 批量替换 JSP 页面的 java 代码
     *
     * @param args
     */
    public static void main(String[] args) {

        // 读取文件夹下的所有 jsp 文件
        String path = "E:\\work_svn\\webpage";        //要遍历的路径
        List<File> files = SimpleFileUtil.files(path);

        // create full file
        StringBuilder fullStr = new StringBuilder();
        StringBuilder newFullStr = new StringBuilder();
        files.forEach(fileInfo -> {

            // 读取到特殊标记,替换/追加代码
            readLine(fileInfo.getPath(), fullStr, newFullStr);
        });

        // 把修改后的 jsp 和修改前的 jsp,分别记录到一个文件中,方便用 vscode 做对比
        try {
            Files.write(Paths.get("E:\\temp\\fullStr.jsp"), fullStr.toString().getBytes());
            Files.write(Paths.get("E:\\temp\\newFullStr.jsp"), newFullStr.toString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void readLine(String fullPath, StringBuilder fullStr, StringBuilder newFullStr) {

        Path path = Paths.get(fullPath);
        try (Stream<String> filteredLines = Files.lines(path)) {
            filteredLines.forEach(line -> {
                fullStr.append(line).append("\n");
                if (line.indexOf("String path = request.getContextPath();") != -1) {
                    System.out.println("添加 String jspUpdateVersion = JspCodeUtils.getJspVersion();");
                    System.out.println(line);
                    newFullStr.append("    String jspUpdateVersion = JspCodeUtils.getJspVersion();").append("\n");;
                    newFullStr.append(line).append("\n");;
                }
                int startIndex = line.indexOf("<%=basePath%>");
                if (startIndex != -1) {
                    fileSplit(line, newFullStr);
                } else {
                    newFullStr.append(line).append("\n");
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 识别 css,js 等 html 元素的标记,插入版本号
     *
     * @param line
     * @param newFullStr
     */
    private static void fileSplit(String line, StringBuilder newFullStr) {

        if (line.indexOf(".js") == -1 &&
            line.indexOf(".css") == -1) {
            newFullStr.append(line).append("\n");
            return;
        }
        StringBuilder sb = new StringBuilder(line.length() + 24);
        if (line.indexOf('?') != -1) {
            String[] split = line.split("\\?");
            if (split.length > 2) {
                System.out.println("这是不对滴");
            }

            sb.append(split[0]).append("?");
            String twoStr = split[1];

            // 判断是否有多个&值
            if (twoStr.indexOf('&') != -1) {
                System.out.println("这是不对滴");
            } else {
                System.out.println("-------------------------------------------------------" + split[1]);
                sb.append("v=<%=jspUpdateVersion%>").append(split[1].replace("v=311", ""));
            }
        } else {
            String[] split = line.split("\"");
            for (int i = 0; i < split.length; i++) {
                sb.append(split[i]);
                if (i == split.length - 2) {
                    sb.append("?v=<%=jspUpdateVersion%>\"");
                } else if (i != split.length - 1) {
                    sb.append("\"");
                }
            }
        }
        newFullStr.append(sb).append("\n");
    }
}
