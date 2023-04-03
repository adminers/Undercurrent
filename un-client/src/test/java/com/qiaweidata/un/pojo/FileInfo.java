package com.qiaweidata.un.pojo;

import com.qiaweidata.un.enums.FileTypeEnum;
import java.io.Serial;
import java.io.Serializable;

/**
 * @Title: FileTypeInfo
 * @Description: FileTypeInfo
 * @Company: www.qiaweidata.com
 * @author: shenshilong
 * @date: 2023-03-07
 * @version: V1.0
 */
public class FileInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = 4108639571513439570L;

    private String id;

    /**
     * 父目录ID
     */
    private String parentId;

    /**
     * 全路径
     */
    private String fullPath;

    /**
     * 文件类型
     */
    private FileTypeEnum fileType;

    /**
     * 名称
     */
    private String name;

    /**
     * 最后修改时间
     */
    private long lastModified;

    /**
     * 格式化后的时间格式
     */
    private String formatLastModified;

    /**
     * 文件后缀
     */
    private String suffix;

    /**
     * 绝对路径
     */
    private String absolutePath;

    /**
     * 删除标记
     */
    private Boolean deleteFlag = Boolean.FALSE;

    /**
     * 获取 文件类型
     *
     * @return fileType 文件类型
     */
    public FileTypeEnum getFileType() {
        return this.fileType;
    }

    /**
     * 设置 文件类型
     *
     * @param fileType 文件类型
     */
    public void setFileType(FileTypeEnum fileType) {
        this.fileType = fileType;
    }

    /**
     * 获取 名称
     *
     * @return name 名称
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置 名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取 最后修改时间
     *
     * @return lastModified 最后修改时间
     */
    public long getLastModified() {
        return this.lastModified;
    }

    /**
     * 设置 最后修改时间
     *
     * @param lastModified 最后修改时间
     */
    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }

    /**
     * 获取 格式化后的时间格式
     *
     * @return formatLastModified 格式化后的时间格式
     */
    public String getFormatLastModified() {
        return this.formatLastModified;
    }

    /**
     * 设置 格式化后的时间格式
     *
     * @param formatLastModified 格式化后的时间格式
     */
    public void setFormatLastModified(String formatLastModified) {
        this.formatLastModified = formatLastModified;
    }

    /**
     * 获取 文件后缀
     *
     * @return suffix 文件后缀
     */
    public String getSuffix() {
        return this.suffix;
    }

    /**
     * 设置 文件后缀
     *
     * @param suffix 文件后缀
     */
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    /**
     * 获取 绝对路径
     *
     * @return absolutePath 绝对路径
     */
    public String getAbsolutePath() {
        return this.absolutePath;
    }

    /**
     * 设置 绝对路径
     *
     * @param absolutePath 绝对路径
     */
    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    /**
     * 获取 全路径
     *
     * @return fullPath 全路径
     */
    public String getFullPath() {
        return this.fullPath;
    }

    /**
     * 设置 全路径
     *
     * @param fullPath 全路径
     */
    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    /**
     * 获取
     *
     * @return id
     */
    public String getId() {
        return this.id;
    }

    /**
     * 设置
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取 父目录ID
     *
     * @return parentId 父目录ID
     */
    public String getParentId() {
        return this.parentId;
    }

    /**
     * 设置 父目录ID
     *
     * @param parentId 父目录ID
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取 删除标记
     *
     * @return deleteFlag 删除标记
     */
    public Boolean getDeleteFlag() {
        return this.deleteFlag;
    }

    /**
     * 设置 删除标记
     *
     * @param deleteFlag 删除标记
     */
    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
