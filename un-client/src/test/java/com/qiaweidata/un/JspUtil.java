package com.qiaweidata.un;

import com.qiaweidata.un.pojo.JspInfo;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

/**
 * commit
 *
 * @Title: JspUtil
 * @Description: JspUtil
 * @Company: www.qiaweidata.com
 * @author: shenshilong
 * @date: 2023-04-14
 * @version: V1.0
 */
public class JspUtil {

    private static final String createPath = "E:\\temp\\webpage";

    /**
     * 批量替换 JSP 页面的 java 代码
     *
     * @param args
     */
    public static void main(String[] args) {

        // 读取文件夹下的所有 jsp 文件
        String path = "E:\\work_svn\\webpage";
        List<File> files = SimpleFileUtil.files(path);

        // create full file
        StringBuilder fullStr = new StringBuilder();


        files.forEach(fileInfo -> {
            StringBuilder newFullStr = new StringBuilder();
            JspInfo jspInfo = new JspInfo();

            // 读取到特殊标记,替换/追加代码
            String fullPath = fileInfo.getPath();
            readLine(fullPath, fullStr, newFullStr, jspInfo);
            try {
                String parentPath = fileInfo.getParent();
                parentPath = parentPath.replace(path, createPath);
                fullPath = fullPath.replace(path, createPath);
                File file = new File(parentPath);
                if (!file.exists()) {
                    file.mkdirs();
                }
                if (!jspInfo.isUpdate()) {
                    Files.delete(Paths.get(fullPath));
                    Files.copy(Paths.get(fileInfo.getPath()), Paths.get(fullPath));
                    System.out.println("没有修改的jsp" + fullPath);
                } else {
                    Files.write(Paths.get(fullPath), newFullStr.toString().getBytes());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        // 把修改后的 jsp 和修改前的 jsp,分别记录到一个文件中,方便用 vscode 做对比
        try {
            Files.write(Paths.get("E:\\temp\\fullStr.jsp"), fullStr.toString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void readLine(String fullPath, StringBuilder fullStr, StringBuilder newFullStr, JspInfo jspInfo) {

        if (!fullPath.endsWith(".jsp")) {
            return;
        }
        Path path = Paths.get(fullPath);
        try (Stream<String> filteredLines = Files.lines(path)) {
            filteredLines.forEach(line -> {
                fullStr.append(line).append("\n");
                if (line.indexOf("String path = request.getContextPath();") != -1) {
                    newFullStr.append("    String jspUpdateVersion = JspCodeUtils.getJspVersion();").append("\n");
                    jspInfo.setUpdate(true);
                }
                if (line.indexOf("com.wrenchdata.core.util.StringUtil") != -1) {
                    newFullStr.append("<%@ page import=\"com.wrenchdata.core.util.JspCodeUtils\" %>").append("\n");
                    jspInfo.setUpdate(true);
                }
                int startIndex = line.indexOf("<%=basePath%>");
                if (startIndex != -1) {
                    fileSplit(line, newFullStr, jspInfo);
                    if (line.indexOf("<c:set") >= 0 ) {
                        newFullStr.append("<c:set var=\"jspUpdateVersion\" value=\"<%=jspUpdateVersion%>\" />").append("\n");
                    }
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
     * @param jspInfo
     */
    private static void fileSplit(String line, StringBuilder newFullStr, JspInfo jspInfo) {

        if (line.indexOf(".js") == -1 &&
            line.indexOf(".css") == -1) {
            newFullStr.append(line).append("\n");
            return;
        }
        jspInfo.setUpdate(true);
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
