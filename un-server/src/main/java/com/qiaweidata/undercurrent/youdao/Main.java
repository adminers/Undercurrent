package com.qiaweidata.undercurrent.youdao;

public class Main {
    public static void main(String[] args) {
        String a = "int x = 0; \n" +
                    "logger.warn(\"Traveling to root loop ended because pathToRootCyclesGuard() threshold was reached.\" \n" +
                    "+ \" It means that albums contains loop. uid={}; startFromAlbumId={}\", uid, startFromAlbumId);\n" +
                    "int y = 1;";
        // 使用正则表达式去掉 logger.warn 整行，包括换行符
        a = a.replaceAll("(?s)\\s*logger\\.warn\\(.*?\\);\\s*", "");
        System.out.println(a);
    }
}