package com.dpv4.file.model;

import java.time.LocalDateTime;

/**
 * 文件上传记录模型
 *
 * @author dpv4
 * @date 2026-05-19
 */
public class FileRecord {

    /**
     * 记录ID
     */
    private String id;

    /**
     * 原始文件名
     */
    private String originalFileName;

    /**
     * 存储文件名
     */
    private String storedFileName;

    /**
     * 文件大小（字节）
     */
    private Long fileSize;

    /**
     * 文件类型（MIME类型）
     */
    private String contentType;

    /**
     * 文件存储路径
     */
    private String filePath;

    /**
     * 访问URL
     */
    private String accessUrl;

    /**
     * 上传时间
     */
    private LocalDateTime uploadTime;

    /**
     * 文件分类
     */
    private String category;

    /**
     * 文件描述
     */
    private String description;

    /**
     * 是否已删除
     */
    private Boolean deleted;

    public FileRecord() {
        this.uploadTime = LocalDateTime.now();
        this.deleted = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getStoredFileName() {
        return storedFileName;
    }

    public void setStoredFileName(String storedFileName) {
        this.storedFileName = storedFileName;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getAccessUrl() {
        return accessUrl;
    }

    public void setAccessUrl(String accessUrl) {
        this.accessUrl = accessUrl;
    }

    public LocalDateTime getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(LocalDateTime uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "FileRecord{" +
                "id='" + id + '\'' +
                ", originalFileName='" + originalFileName + '\'' +
                ", fileSize=" + fileSize +
                ", uploadTime=" + uploadTime +
                '}';
    }
}