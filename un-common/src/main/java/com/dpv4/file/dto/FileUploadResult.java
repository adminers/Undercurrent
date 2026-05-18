package com.dpv4.file.dto;

/**
 * 单个文件上传结果
 *
 * @author dpv4
 * @date 2026-05-18
 */
public class FileUploadResult {

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
     * 文件类型
     */
    private String contentType;

    /**
     * 文件访问路径
     */
    private String filePath;

    /**
     * 是否上传成功
     */
    private Boolean success;

    /**
     * 错误信息（如果失败）
     */
    private String errorMessage;

    public static FileUploadResult success(String originalFileName, String storedFileName,
                                           Long fileSize, String contentType, String filePath) {
        FileUploadResult result = new FileUploadResult();
        result.setOriginalFileName(originalFileName);
        result.setStoredFileName(storedFileName);
        result.setFileSize(fileSize);
        result.setContentType(contentType);
        result.setFilePath(filePath);
        result.setSuccess(true);
        return result;
    }

    public static FileUploadResult failure(String originalFileName, String errorMessage) {
        FileUploadResult result = new FileUploadResult();
        result.setOriginalFileName(originalFileName);
        result.setSuccess(false);
        result.setErrorMessage(errorMessage);
        return result;
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

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}