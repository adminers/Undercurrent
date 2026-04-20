package com.qiaweidata.util.shudre;

/**
 * 文件大小格式化工具
 */
public class FileSizeFormatter {
    
    /**
     * 将字节数格式化为人类可读的字符串
     */
    public static String format(long bytes) {
        if (bytes < 1024) {
            return bytes + " B";
        }
        int exp = (int) (Math.log(bytes) / Math.log(1024));
        char pre = "KMGTPE".charAt(exp - 1);
        return String.format("%.1f %sB", bytes / Math.pow(1024, exp), pre);
    }
    
    /**
     * 递归计算目录大小（谨慎使用，大目录会很慢）
     */
    public static long getDirectorySize(java.io.File dir) {
        long size = 0;
        java.io.File[] files = dir.listFiles();
        if (files == null) {
            return 0;
        }
        for (java.io.File file : files) {
            if (file.isFile()) {
                size += file.length();
            } else if (file.isDirectory()) {
                size += getDirectorySize(file);
            }
        }
        return size;
    }
}