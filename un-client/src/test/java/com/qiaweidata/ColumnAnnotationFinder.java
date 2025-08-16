package com.qiaweidata;

import java.util.List;
import java.util.regex.*;

public class ColumnAnnotationFinder {
    public static void main(String[] args) {
        List<String> lines = List.of(
            "    @Column(name = \"target_table\", length = 500)",
            "    private String targetTable;",
            "    @Column(name = \"source_table\")",
            "    public void someMethod() {}"
        );

        // 遍历列表，找到所有包含 @Column 的行
        /*for (String line : lines) {
            if (isColumnAnnotation(line)) {
                System.out.println("Found @Column: " + line.trim());
                extractColumnInfo(line);
            }
        }*/

        for (String line : lines) {
            if (containsColumnAnnotation(line)) {
                System.out.println("Found @Column: " + line.trim());
            }
        }
    }

    /**
     * 判断一行是否包含 @Column 注解
     */
    public static boolean isColumnAnnotation(String line) {
        return line.trim().startsWith("@Column");
    }

    /**
     * 判断一行是否包含 @Column 注解（不解析参数）
     */
    public static boolean containsColumnAnnotation(String line) {
        // 正则：匹配 @Column 后跟可选括号（可能包含任意内容）
        return Pattern.compile("@Column(\\s*\\([^)]*\\))?")
                     .matcher(line.trim())
                     .find();
    }


    /**
     * 从 @Column 注解中提取 name 和 length
     */
    public static void extractColumnInfo(String columnLine) {
        // 正则匹配：@Column(name = "xxx", length = yyy)
        Pattern pattern = Pattern.compile(
            "@Column\\(\\s*name\\s*=\\s*\"([^\"]+)\"\\s*(,\\s*length\\s*=\\s*(\\d+)\\s*)?\\)"
        );
        Matcher matcher = pattern.matcher(columnLine.trim());

        if (matcher.find()) {
            String name = matcher.group(1);  // 提取 name 值
            String length = matcher.group(3); // 提取 length 值（可能为 null）
            
            System.out.println("Column name: " + name);
            if (length != null) {
                System.out.println("Column length: " + length);
            }
        }
    }
}