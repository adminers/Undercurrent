package com.dpv4.file.service;

import com.dpv4.file.dto.FileUploadResult;
import com.dpv4.file.model.FileRecord;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 通用文件上传服务接口
 * 不限制文件类型，支持所有文件上传
 *
 * @author dpv4
 * @date 2026-05-19
 */
public interface GenericFileUploadService {

    /**
     * 上传单个文件（无类型限制）
     *
     * @param file      文件
     * @param category  分类（可选）
     * @param description 描述（可选）
     * @return 上传结果
     */
    FileUploadResult uploadFile(MultipartFile file, String category, String description);

    /**
     * 批量上传文件（无类型限制）
     *
     * @param files     文件列表
     * @param category  分类（可选）
     * @param description 描述（可选）
     * @return 上传结果列表
     */
    List<FileUploadResult> uploadFiles(List<MultipartFile> files, String category, String description);

    /**
     * 获取所有文件记录
     *
     * @return 文件记录列表
     */
    List<FileRecord> getAllRecords();

    /**
     * 根据分类获取文件记录
     *
     * @param category 分类
     * @return 文件记录列表
     */
    List<FileRecord> getRecordsByCategory(String category);

    /**
     * 删除文件记录
     *
     * @param id 记录ID
     * @return 是否删除成功
     */
    boolean deleteRecord(String id);

    /**
     * 获取记录数量
     *
     * @return 记录总数
     */
    long getRecordCount();
}