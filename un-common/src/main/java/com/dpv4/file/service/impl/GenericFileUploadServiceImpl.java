package com.dpv4.file.service.impl;

import com.dpv4.file.dto.FileUploadResult;
import com.dpv4.file.model.FileRecord;
import com.dpv4.file.service.FileRecordService;
import com.dpv4.file.service.GenericFileUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 通用文件上传服务实现类
 * 支持所有类型文件上传，自动记录上传历史
 *
 * @author dpv4
 * @date 2026-05-19
 */
@Service
public class GenericFileUploadServiceImpl implements GenericFileUploadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenericFileUploadServiceImpl.class);

    @Value("${file.upload.dir:./uploads}")
    private String uploadDir;

    @Value("${file.max-size:52428800}")
    private long maxFileSize;

    private final FileRecordService fileRecordService;

    public GenericFileUploadServiceImpl(FileRecordService fileRecordService) {
        this.fileRecordService = fileRecordService;
    }

    @Override
    public FileUploadResult uploadFile(MultipartFile file, String category, String description) {
        if (file == null || file.isEmpty()) {
            return FileUploadResult.failure("未知文件", "文件不能为空");
        }

        String originalFileName = file.getOriginalFilename();
        if (!StringUtils.hasText(originalFileName)) {
            return FileUploadResult.failure("未知文件", "文件名不能为空");
        }

        if (file.getSize() > maxFileSize) {
            return FileUploadResult.failure(originalFileName, "文件大小超过限制（最大50MB）");
        }

        try {
            Path uploadPath = getUploadPath();
            Files.createDirectories(uploadPath);

            String extension = getFileExtension(originalFileName);
            String storedFileName = generateStoredFileName(extension);
            Path targetPath = uploadPath.resolve(storedFileName);

            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            String datePath = getDatePath();
            String filePath = "/api/files/download/" + datePath + "/" + storedFileName;

            FileRecord record = new FileRecord();
            record.setOriginalFileName(originalFileName);
            record.setStoredFileName(storedFileName);
            record.setFileSize(file.getSize());
            record.setContentType(file.getContentType());
            record.setFilePath(targetPath.toString());
            record.setAccessUrl(filePath);
            record.setCategory(category);
            record.setDescription(description);

            fileRecordService.addRecord(record);

            LOGGER.info("文件上传成功: {} -> {}", originalFileName, filePath);

            return FileUploadResult.success(
                    originalFileName,
                    storedFileName,
                    file.getSize(),
                    file.getContentType(),
                    filePath
            );
        } catch (IOException e) {
            LOGGER.error("文件上传失败: {}", e.getMessage(), e);
            return FileUploadResult.failure(originalFileName, "文件存储失败: " + e.getMessage());
        }
    }

    @Override
    public List<FileUploadResult> uploadFiles(List<MultipartFile> files, String category, String description) {
        if (files == null || files.isEmpty()) {
            return List.of();
        }

        return files.stream()
                .map(file -> uploadFile(file, category, description))
                .collect(Collectors.toList());
    }

    @Override
    public List<FileRecord> getAllRecords() {
        return fileRecordService.getAllRecords();
    }

    @Override
    public List<FileRecord> getRecordsByCategory(String category) {
        return fileRecordService.getRecordsByCategory(category);
    }

    @Override
    public boolean deleteRecord(String id) {
        return fileRecordService.deleteRecord(id);
    }

    @Override
    public long getRecordCount() {
        return fileRecordService.getRecordCount();
    }

    private Path getUploadPath() {
        return Paths.get(uploadDir, getDatePath());
    }

    private String getDatePath() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

    private String getFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex == -1) {
            return "";
        }
        return fileName.substring(lastDotIndex);
    }

    private String generateStoredFileName(String extension) {
        return UUID.randomUUID().toString().replace("-", "") + extension;
    }
}