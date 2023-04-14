package com.qiaweidata.un;

import com.qiaweidata.un.enums.LoopFloderEnum;
import com.qiaweidata.un.pojo.FileInfo;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
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

        files.forEach(fileInfo -> {

            // 读取到特殊标记,替换/追加代码
            readLine(fileInfo.getPath());
        });

        // 识别 css,js 等 html 元素的标记,插入版本号

        // 把修改后的 jsp 和修改前的 jsp,分别记录到一个文件中,方便用 vscode 做对比


    }

    private static void readLine(String fullPath) {

        Path path = Paths.get(fullPath);
        try (Stream<String> filteredLines = Files.lines(path)) {
            filteredLines.forEach(line -> {
                if (line.indexOf("String path = request.getContextPath();") != -1) {
                    System.out.println("添加 String jspUpdateVersion = JspCodeUtils.getJspVersion();");
                    System.out.println(line);
                }
                if (line.indexOf("<%=basePath%>") != -1) {
                    System.out.println(line);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
