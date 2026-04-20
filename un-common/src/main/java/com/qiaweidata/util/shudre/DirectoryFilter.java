package com.qiaweidata.util.shudre;

import java.io.File;
import java.io.FileFilter;

/**
 * 目录过滤器：只接受目录，过滤掉系统隐藏目录
 */
public class DirectoryFilter implements FileFilter {
    
    @Override
    public boolean accept(File pathname) {
        // 只接受目录，并且不是隐藏目录（Windows 下可根据需要调整）
        if (!pathname.isDirectory()) {
            return false;
        }
        // 过滤掉系统特殊目录（可选）
        String name = pathname.getName();
        if (name.equals("System Volume Information") || 
            name.equals("$Recycle.Bin") ||
            name.startsWith(".")) {
            return false;
        }
        return true;
    }
}