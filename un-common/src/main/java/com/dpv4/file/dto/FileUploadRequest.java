package com.dpv4.file.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 文件上传请求DTO
 *
 * @author dpv4
 * @date 2026-05-18
 */
public class FileUploadRequest {

    /**
     * 上传的文件列表（支持多选）
     */
    private List<MultipartFile> files;

    /**
     * 文件分类（可选）
     */
    private String category;

    /**
     * 文件描述（可选）
     */
    private String description;

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
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
}