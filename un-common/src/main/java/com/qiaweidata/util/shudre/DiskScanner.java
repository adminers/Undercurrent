package com.qiaweidata.util.shudre;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 磁盘扫描器主类
 */
public class DiskScanner {
    
    public static void main(String[] args) {
        List<DiskNode> disks = getDiskRoots();
        
        for (DiskNode disk : disks) {
            System.out.println("\n📀 扫描磁盘: " + disk.getName());
            System.out.println("========================================");
            scanDirectory(disk, disk, 0);
            printTree(disk, 0);
        }
    }
    
    /**
     * 获取所有磁盘根目录（Windows 返回 C:\, D:\ 等，Linux/macOS 返回 /）
     */
    private static List<DiskNode> getDiskRoots() {
        List<DiskNode> disks = new ArrayList<>();
        File[] roots = File.listRoots();
        
        for (File root : roots) {
            DiskNode diskNode = new DiskNode(root.getAbsolutePath(), true);
            diskNode.setFile(root);
            disks.add(diskNode);
        }
        return disks;
    }
    
    /**
     * 递归扫描目录
     */
    private static void scanDirectory(DiskNode parentNode, DiskNode rootDisk, int depth) {
        // 限制扫描深度，避免无限递归或性能问题（最多 5 层）
        if (depth > 5) {
            return;
        }
        
        File dir = parentNode.getFile();
        if (dir == null || !dir.exists() || !dir.isDirectory()) {
            return;
        }
        
        File[] children = dir.listFiles();
        if (children == null) {
            return;
        }
        
        for (File child : children) {
            DiskNode childNode = new DiskNode(child.getName(), child.isDirectory());
            childNode.setFile(child);
            parentNode.addChild(childNode);
            
            // 如果是目录，递归扫描
            if (child.isDirectory()) {
                scanDirectory(childNode, rootDisk, depth + 1);
            }
        }
    }
    
    /**
     * 以 Markdown 树形结构输出
     */
    private static void printTree(DiskNode node, int level) {
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < level; i++) {
            indent.append("  ");
        }
        
        String prefix = level == 0 ? "📀 " : (node.isDirectory() ? "📁 " : "📄 ");
        System.out.println(indent.toString() + prefix + node.getName());
        
        for (DiskNode child : node.getChildren()) {
            printTree(child, level + 1);
        }
    }
}