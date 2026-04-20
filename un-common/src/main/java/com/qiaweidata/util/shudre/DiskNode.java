package com.qiaweidata.util.shudre;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 磁盘/目录树节点
 */
public class DiskNode {
    private String name;              // 节点名称
    private boolean isDirectory;      // 是否是目录
    private File file;                // 对应的 File 对象
    private List<DiskNode> children;  // 子节点列表
    
    public DiskNode(String name, boolean isDirectory) {
        this.name = name;
        this.isDirectory = isDirectory;
        this.children = new ArrayList<>();
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public boolean isDirectory() {
        return isDirectory;
    }
    
    public void setDirectory(boolean directory) {
        isDirectory = directory;
    }
    
    public File getFile() {
        return file;
    }
    
    public void setFile(File file) {
        this.file = file;
    }
    
    public List<DiskNode> getChildren() {
        return children;
    }
    
    public void addChild(DiskNode child) {
        this.children.add(child);
    }
}