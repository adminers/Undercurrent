package com.qiaweidata;

import java.util.regex.*;

public class FieldMatcher {

    public static void main(String[] args) {
        String returnStatement = "    return targetConnect;";
        String fieldDeclaration = "\t        public      String       targetConnect  ;//注释";

        // 1. 从 return 语句提取变量名（如 "targetConnect"）
        String variableName = extractVariableName(returnStatement);
        System.out.println("Extracted variable: " + variableName); // 输出: targetConnect

        // 2. 检查字段声明是否匹配
        boolean isMatch = isFieldDeclarationMatch(fieldDeclaration, variableName);
        System.out.println("Does the field match? " + isMatch); // 输出: true
    }

    /**
     * 从 return 语句中提取变量名（支持 this. 前缀）
     * @param returnStatement 如 "return targetConnect;" 或 "return this.wdModelId;"
     * @return 变量名（如 "targetConnect" 或 "wdModelId"），无 this. 前缀
     */
    public static String extractVariableName(String returnStatement) {
        // 正则匹配：return + [this.] + 变量名 + ;
        Pattern pattern = Pattern.compile("return\\s+(?:this\\.)?([^;]+)\\s*;");
        Matcher matcher = pattern.matcher(returnStatement.trim());
        if (matcher.find()) {
            return matcher.group(1).trim(); // 返回去除了 this. 的变量名
        }
        return null;
    }

    /**
     * 检查字段声明是否匹配变量名
     * @param fieldDeclaration 如 "private String targetConnect;"
     * @param variableName 如 "targetConnect"
     * @return 是否匹配
     */
    public static boolean isFieldDeclarationMatch(String fieldDeclaration, String variableName) {
        // 正则解析：
        // 1. (public|private|protected)? → 可选修饰符
        // 2. \s+ → 任意数量空格
        // 3. (final\s+)? → 可选 final
        // 4. [\w<>]+ → 类型（支持泛型，如 List<String>）
        // 5. \s+ → 任意数量空格
        // 6. 变量名
        // 7. \s* → 可选空格
        // 8. ; → 分号
        // 9. (//.*|/\*.*\*/)? → 可选行尾注释
        String regex = "(public|private|protected)?\\s+(final\\s+)?[\\w<>]+\\s+" + variableName + "\\s*;(\\s*(//.*|/\\*.*\\*/)?)?";
        return Pattern.compile(regex).matcher(fieldDeclaration.trim()).matches();
    }
}