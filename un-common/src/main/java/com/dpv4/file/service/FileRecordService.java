package com.dpv4.file.service;

import com.dpv4.file.model.FileRecord;

import java.util.List;

/**
 * 文件记录服务接口
 *
 * @author dpv4
 * @date 2026-05-19
 */
public interface FileRecordService {

    /**
     * 添加文件记录
     *
     * @param record 文件记录
     * @return 添加后的记录（包含ID）
     */
    FileRecord addRecord(FileRecord record);

    /**
     * 根据ID获取记录
     *
     * @param id 记录ID
     * @return 文件记录
     */
    FileRecord getRecordById(String id);

    /**
     * 获取所有记录（按上传时间倒序）
     *
     * @return 文件记录列表
     */
    List<FileRecord> getAllRecords();

    /**
     * 根据分类获取记录
     *
     * @param category 分类
     * @return 文件记录列表
     */
    List<FileRecord> getRecordsByCategory(String category);

    /**
     * 根据ID删除记录（软删除）
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