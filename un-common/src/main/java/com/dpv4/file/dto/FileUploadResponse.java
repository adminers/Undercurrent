package com.dpv4.file.dto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 文件上传响应DTO
 *
 * @author dpv4
 * @date 2026-05-18
 */
public class FileUploadResponse {

    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * 消息
     */
    private String message;

    /**
     * 上传时间
     */
    private LocalDateTime uploadTime;

    /**
     * 上传结果列表
     */
    private List<FileUploadResult> results;

    public FileUploadResponse() {
        this.uploadTime = LocalDateTime.now();
    }

    public static FileUploadResponse success(String message, List<FileUploadResult> results) {
        FileUploadResponse response = new FileUploadResponse();
        response.setSuccess(true);
        response.setMessage(message);
        response.setResults(results);
        return response;
    }

    public static FileUploadResponse error(String message) {
        FileUploadResponse response = new FileUploadResponse();
        response.setSuccess(false);
        response.setMessage(message);
        return response;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(LocalDateTime uploadTime) {
        this.uploadTime = uploadTime;
    }

    public List<FileUploadResult> getResults() {
        return results;
    }

    public void setResults(List<FileUploadResult> results) {
        this.results = results;
    }
}