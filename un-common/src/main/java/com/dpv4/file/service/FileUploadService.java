package com.dpv4.file.service;

import com.dpv4.file.dto.FileUploadResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 文件上传服务接口
 *
 * @author dpv4
 * @date 2026-05-18
 */
public interface FileUploadService {

    /**
     * 上传单个文件
     *
     * @param file 上传的文件
     * @return 上传结果
     */
    FileUploadResult uploadFile(MultipartFile file);

    /**
     * 批量上传文件
     *
     * @param files 文件列表
     * @return 上传结果列表
     */
    List<FileUploadResult> uploadFiles(List<MultipartFile> files);

    /**
     * 验证文件是否允许上传
     *
     * @param file 文件
     * @return 是否允许
     */
    boolean isFileAllowed(MultipartFile file);

    /**
     * 获取允许的文件类型列表
     *
     * @return 允许的文件类型
     */
    List<String> getAllowedFileTypes();

    /**
     * 获取允许的最大文件大小（字节）
     *
     * @return 最大文件大小
     */
    long getMaxFileSize();
}