package com.dpv4.file.service.impl;

import com.dpv4.file.service.FileUploadService;
import com.dpv4.file.dto.FileUploadResult;
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
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 文件上传服务实现类
 * 支持多文件上传，兼容苹果Safari浏览器
 *
 * @author dpv4
 * @date 2026-05-18
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadServiceImpl.class);

    @Value("${file.upload.dir:./uploads}")
    private String uploadDir;

    @Value("${file.allowed-types:image/jpeg,image/png,image/gif,image/webp,image/jpg}")
    private String allowedTypes;

    @Value("${file.max-size:52428800}")
    private long maxFileSize;

    @Override
    public FileUploadResult uploadFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return FileUploadResult.failure("未知文件", "文件不能为空");
        }

        String originalFileName = file.getOriginalFilename();
        if (!StringUtils.hasText(originalFileName)) {
            return FileUploadResult.failure("未知文件", "文件名不能为空");
        }

        if (!isFileAllowed(file)) {
            return FileUploadResult.failure(originalFileName, "不支持的文件类型");
        }

        if (file.getSize() > maxFileSize) {
            return FileUploadResult.failure(originalFileName, "文件大小超过限制");
        }

        try {
            Path uploadPath = getUploadPath();
            Files.createDirectories(uploadPath);

            String extension = getFileExtension(originalFileName);
            String storedFileName = generateStoredFileName(extension);
            Path targetPath = uploadPath.resolve(storedFileName);

            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            String filePath = "/api/files/" + getDatePath() + "/" + storedFileName;
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
    public List<FileUploadResult> uploadFiles(List<MultipartFile> files) {
        if (files == null || files.isEmpty()) {
            return List.of();
        }

        return files.stream()
                .map(this::uploadFile)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isFileAllowed(MultipartFile file) {
        String contentType = file.getContentType();
        if (!StringUtils.hasText(contentType)) {
            return false;
        }

        List<String> allowedTypeList = getAllowedFileTypes();
        return allowedTypeList.stream()
                .anyMatch(type -> contentType.toLowerCase().startsWith(type.toLowerCase()));
    }

    @Override
    public List<String> getAllowedFileTypes() {
        return Arrays.asList(allowedTypes.split(","));
    }

    @Override
    public long getMaxFileSize() {
        return maxFileSize;
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