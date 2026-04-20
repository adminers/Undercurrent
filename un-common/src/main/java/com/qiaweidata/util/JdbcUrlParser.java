package com.qiaweidata.util;

public class JdbcUrlParser {

    /**
     * 提取 IP 地址
     */
    public static String extractIp(String url) {
        // 去掉 jdbc:mysql://
        String withoutProtocol = url.substring(url.indexOf("//") + 2);
        // 取到第一个冒号或斜杠之前的部分
        int colonIndex = withoutProtocol.indexOf(":");
        if (colonIndex > 0) {
            return withoutProtocol.substring(0, colonIndex);
        }
        // 如果没有端口，可能是直接跟斜杠
        int slashIndex = withoutProtocol.indexOf("/");
        if (slashIndex > 0) {
            return withoutProtocol.substring(0, slashIndex);
        }
        return withoutProtocol;
    }
    
    /**
     * 提取端口号
     */
    public static String extractPort(String url) {
        String withoutProtocol = url.substring(url.indexOf("//") + 2);
        int colonIndex = withoutProtocol.indexOf(":");
        if (colonIndex < 0) {
            return ""; // 没有端口
        }
        int slashIndex = withoutProtocol.indexOf("/", colonIndex);
        if (slashIndex < 0) {
            return withoutProtocol.substring(colonIndex + 1);
        }
        return withoutProtocol.substring(colonIndex + 1, slashIndex);
    }
    
    /**
     * 提取数据库名
     */
    public static String extractDatabase(String url) {
        String withoutProtocol = url.substring(url.indexOf("//") + 2);
        // 找到第一个斜杠后的部分
        int firstSlash = withoutProtocol.indexOf("/");
        if (firstSlash < 0) {
            return "";
        }
        String afterSlash = withoutProtocol.substring(firstSlash + 1);
        // 如果后面有 ?，截取到 ? 为止
        int questionMark = afterSlash.indexOf("?");
        if (questionMark > 0) {
            return afterSlash.substring(0, questionMark);
        }
        return afterSlash;
    }
    
    /**
     * 提取 ? 后面的参数串
     */
    public static String extractParams(String url) {
        int questionMark = url.indexOf("?");
        if (questionMark < 0) {
            return "";
        }
        return url.substring(questionMark);
    }
}