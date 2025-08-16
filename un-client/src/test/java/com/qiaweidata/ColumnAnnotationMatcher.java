package com.qiaweidata;

import java.util.regex.*;

public class ColumnAnnotationMatcher {

    public static void main(String[] args) {
        String annotationLine = "\t@Column(name = \"target_connect\", length = 32)";
        String fieldDeclaration = "\tprivate String targetConnect;";

        // 1. 从 @Column 注解中提取 name 的值（如 "parent_id"）
        String columnName = extractColumnName(annotationLine);
        System.out.println("Extracted column name: " + columnName); // 输出: parent_id

        // 2. 转换为 Java 字段名（驼峰命名，如 "parentId"）
        String javaFieldName = convertToJavaFieldName(columnName);
        System.out.println("Java field name: " + javaFieldName); // 输出: parentId

        // 3. 检查字段声明是否匹配
        boolean isMatch = isFieldDeclarationMatch(fieldDeclaration, javaFieldName);
        System.out.println("Does the field match? " + isMatch); // 输出: true
    }

    /**
     * 从 @Column 注解中提取 name 属性值
     * @param annotationLine 注解行，如 `@Column(name = "parent_id", length = 32)`
     * @return 列名（如 "parent_id"）
     */
    public static String extractColumnName(String annotationLine) {
        Pattern pattern = Pattern.compile("name\\s*=\\s*\"([^\"]+)\"");
        Matcher matcher = pattern.matcher(annotationLine);
        if (matcher.find()) {
            return matcher.group(1); // 返回 "parent_id"
        }
        return null;
    }

    /**
     * 将数据库列名（如 parent_id）转换为 Java 字段名（如 parentId）
     * @param columnName 列名（如 "parent_id"）
     * @return Java 字段名（驼峰命名，如 "parentId"）
     */
    public static String convertToJavaFieldName(String columnName) {
        StringBuilder result = new StringBuilder();
        boolean nextUpper = false;

        for (char c : columnName.toCharArray()) {
            if (c == '_') {
                nextUpper = true;
            } else {
                if (nextUpper) {
                    result.append(Character.toUpperCase(c));
                    nextUpper = false;
                } else {
                    result.append(c);
                }
            }
        }

        return result.toString();
    }

    /**
     * 检查字段声明是否匹配给定的 Java 字段名
     * @param fieldDeclaration 字段声明，如 `private String parentId;`
     * @param javaFieldName 预期的字段名（如 "parentId"）
     * @return 是否匹配
     */
    public static boolean isFieldDeclarationMatch(String fieldDeclaration, String javaFieldName) {
        // 正则匹配：private + 类型（如 String）+ 字段名 + ;
        Pattern pattern = Pattern.compile("private\\s+\\w+\\s+" + javaFieldName + "\\s*;");
        return pattern.matcher(fieldDeclaration.trim()).matches();
    }
}