package com.qiaweidata.un;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @Title: SoftwareCode
 * @Description: SoftwareCode
 * @date: 2025-04-16
 * @version: V1.0
 */
public class SoftwareCode {

    public static void main(String[] args) {
        String directoryPath = "C:\\Users\\Administrator\\Downloads\\scx-master"; // 替换为你的文件夹路径
        String outputFilePath = "合并后的文件.java"; // 输出文件的路径
        try {
            // 创建输出文件
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath));
            // 获取所有 Java 文件
            Files.walk(Paths.get(directoryPath))
                .filter(path -> path.toString().endsWith(".java"))
                .forEach(path -> {
                    try {
                        // 读取 Java 文件内容并处理
                        String content = new String(Files.readAllBytes(path));
                        String cleanedContent = cleanJavaCode(content);
                        writer.write(cleanedContent);
                        writer.write("\n"); // 添加换行符
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            writer.close();
            System.out.println("合并完成，输出文件：" + outputFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static String cleanJavaCode(String code) {

         // 去掉 import 语句
        String withoutImports = code.replaceAll("(?m)^import .*;\\s*", "")
                                    .replaceAll("(?m)^package .*;\\s*", "");
        // 去掉多行注释和单行注释
        String withoutComments = withoutImports
                .replaceAll("/\\*.*?\\*/", "") // 去掉多行注释
                .replaceAll("(?s)/\\**.*?\\*/", "") // 去掉多行注释
                .replaceAll("//.*", ""); // 去掉单行注释

        // 去掉中文字符
        withoutComments = withoutComments.replaceAll("[\u4e00-\u9fa5]", ""); // 匹配中文字符并替换为空字符串

         // 去掉 System.out.println 语句
        withoutComments = withoutComments.replaceAll("System\\.out\\.println\\(.*?\\);?\\s*", "");

        // 去掉空行
        withoutComments = withoutComments.replaceAll("(?m)^[ \t]*\r?\n", "");

        withoutComments = withoutComments.replaceAll("(?s)\\s*logger\\.warn\\(.*?\\);\\s*", "");
        withoutComments = withoutComments.replaceAll("(?s)\\s*logger\\.info\\(.*?\\);\\s*", "");
        withoutComments = withoutComments.replaceAll("(?s)\\s*logger\\.error\\(.*?\\);\\s*", "");
        withoutComments = withoutComments.replaceAll("(?s)\\s*LOGGER\\.info\\(.*?\\);\\s*", "");
        withoutComments = withoutComments.replaceAll("(?s)\\s*LOGGER\\.warn\\(.*?\\);\\s*", "");

        return withoutComments.trim(); // 返回清理后的代码
    }

}
