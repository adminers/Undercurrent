package com.qiaweidata.util.shudre;

/**
 * 将目录树导出为 Markdown 格式
 */
public class TreeMarkdownExporter {
    
    /**
     * 将磁盘树转换为 Markdown 字符串
     */
    public static String toMarkdown(DiskNode root) {
        StringBuilder sb = new StringBuilder();
        sb.append("# 磁盘目录结构\n\n");
        toMarkdown(root, sb, 0);
        return sb.toString();
    }
    
    private static void toMarkdown(DiskNode node, StringBuilder sb, int level) {
        String indent = getIndent(level);
        String icon = getIcon(node);
        
        sb.append(indent).append("- ").append(icon).append(" ").append(node.getName()).append("\n");
        
        for (DiskNode child : node.getChildren()) {
            toMarkdown(child, sb, level + 1);
        }
    }
    
    private static String getIndent(int level) {
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < level; i++) {
            indent.append("  ");
        }
        return indent.toString();
    }
    
    private static String getIcon(DiskNode node) {
        if (node.isDirectory()) {
            return "📁";
        } else {
            return "📄";
        }
    }
    
    /**
     * 打印到控制台
     */
    public static void printMarkdown(DiskNode root) {
        System.out.println(toMarkdown(root));
    }
}