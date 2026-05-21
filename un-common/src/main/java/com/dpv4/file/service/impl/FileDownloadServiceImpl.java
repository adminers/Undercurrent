package com.dpv4.file.service.impl;

import com.dpv4.file.dto.FileDownloadResponse;
import com.dpv4.file.service.FileDownloadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Service
public class FileDownloadServiceImpl implements FileDownloadService {

    @Value("${file.upload-dir:./uploads}")
    private String uploadDir;

    private Path uploadPath;

    @PostConstruct
    public void init() {
        uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
        try {
            Files.createDirectories(uploadPath);
            log.info("文件上传目录已初始化: {}", uploadPath);
        } catch (IOException e) {
            log.error("创建文件上传目录失败", e);
        }
    }

    @Override
    public Resource downloadFile(String datePath, String fileName) {
        try {
            Path filePath = getFilePath(datePath, fileName);
            Resource resource = new UrlResource(filePath.toUri());
            
            if (resource.exists() && resource.isReadable()) {
                log.info("文件下载成功: {}", filePath);
                return resource;
            } else {
                log.warn("文件不存在或不可读: {}", filePath);
                return null;
            }
        } catch (MalformedURLException e) {
            log.error("文件路径格式错误: {}", e.getMessage());
            return null;
        }
    }

    @Override
    public FileDownloadResponse getFileInfo(String datePath, String fileName) {
        Path filePath = getFilePath(datePath, fileName);
        
        if (!Files.exists(filePath)) {
            return FileDownloadResponse.builder()
                    .fileName(fileName)
                    .status("NOT_FOUND")
                    .build();
        }

        try {
            long fileSize = Files.size(filePath);
            String fileType = Files.probeContentType(filePath);
            
            return FileDownloadResponse.builder()
                    .fileName(fileName)
                    .filePath(filePath.toString())
                    .fileSize(fileSize)
                    .fileType(fileType != null ? fileType : "application/octet-stream")
                    .downloadUrl("/api/files/download/" + datePath + "/" + fileName)
                    .status("SUCCESS")
                    .build();
        } catch (IOException e) {
            log.error("获取文件信息失败: {}", e.getMessage());
            return FileDownloadResponse.builder()
                    .fileName(fileName)
                    .status("ERROR")
                    .build();
        }
    }

    @Override
    public List<FileDownloadResponse> listAllFiles() {
        List<FileDownloadResponse> files = new ArrayList<>();
        
        try (Stream<Path> dateDirs = Files.list(uploadPath)) {
            dateDirs.filter(Files::isDirectory)
                    .forEach(dateDir -> {
                        String datePath = dateDir.getFileName().toString();
                        List<FileDownloadResponse> dateFiles = listFilesByDate(datePath);
                        files.addAll(dateFiles);
                    });
        } catch (IOException e) {
            log.error("遍历文件目录失败: {}", e.getMessage());
        }
        
        files.sort((a, b) -> {
            if (a.getUploadTime() != null && b.getUploadTime() != null) {
                return b.getUploadTime().compareTo(a.getUploadTime());
            }
            return 0;
        });
        
        return files;
    }

    @Override
    public List<FileDownloadResponse> listFilesByDate(String datePath) {
        List<FileDownloadResponse> files = new ArrayList<>();
        Path dateDir = uploadPath.resolve(datePath);
        
        if (!Files.exists(dateDir) || !Files.isDirectory(dateDir)) {
            return files;
        }

        LocalDate date = parseDate(datePath);
        
        try (Stream<Path> filePaths = Files.list(dateDir)) {
            filePaths.filter(Files::isRegularFile)
                    .forEach(filePath -> {
                        String fileName = filePath.getFileName().toString();
                        try {
                            long fileSize = Files.size(filePath);
                            String fileType = Files.probeContentType(filePath);
                            
                            FileDownloadResponse response = FileDownloadResponse.builder()
                                    .fileName(fileName)
                                    .filePath(filePath.toString())
                                    .fileSize(fileSize)
                                    .fileType(fileType != null ? fileType : "application/octet-stream")
                                    .uploadTime(date != null ? date.atStartOfDay() : LocalDateTime.now())
                                    .downloadUrl("/api/files/download/" + datePath + "/" + fileName)
                                    .status("SUCCESS")
                                    .build();
                            
                            files.add(response);
                        } catch (IOException e) {
                            log.warn("获取文件信息失败: {}", filePath);
                        }
                    });
        } catch (IOException e) {
            log.error("遍历日期目录失败: {}", e.getMessage());
        }
        
        return files;
    }

    @Override
    public Path getFilePath(String datePath, String fileName) {
        return uploadPath.resolve(datePath).resolve(fileName).normalize();
    }

    @Override
    public long getFileSize(String datePath, String fileName) {
        Path filePath = getFilePath(datePath, fileName);
        try {
            return Files.size(filePath);
        } catch (IOException e) {
            log.error("获取文件大小失败: {}", e.getMessage());
            return -1;
        }
    }

    @Override
    public boolean fileExists(String datePath, String fileName) {
        Path filePath = getFilePath(datePath, fileName);
        return Files.exists(filePath) && Files.isRegularFile(filePath);
    }

    private LocalDate parseDate(String datePath) {
        try {
            return LocalDate.parse(datePath, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (Exception e) {
            return null;
        }
    }
}