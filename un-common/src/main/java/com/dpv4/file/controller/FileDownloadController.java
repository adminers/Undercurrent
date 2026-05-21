package com.dpv4.file.controller;

import com.dpv4.file.dto.FileDownloadResponse;
import com.dpv4.file.service.FileDownloadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileDownloadController {

    private final FileDownloadService fileDownloadService;

    @GetMapping("/download/{datePath}/{fileName}")
    public ResponseEntity<Resource> downloadFile(
            @PathVariable String datePath,
            @PathVariable String fileName) {
        
        log.info("请求下载文件: datePath={}, fileName={}", datePath, fileName);
        
        Resource resource = fileDownloadService.downloadFile(datePath, fileName);
        
        if (resource == null) {
            log.warn("文件不存在: {}/{}", datePath, fileName);
            return ResponseEntity.notFound().build();
        }

        String encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8)
                .replace("+", "%20");

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, 
                        "attachment; filename=\"" + encodedFileName + "\"; filename*=UTF-8''" + encodedFileName)
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION)
                .body(resource);
    }

    @GetMapping("/info/{datePath}/{fileName}")
    public ResponseEntity<FileDownloadResponse> getFileInfo(
            @PathVariable String datePath,
            @PathVariable String fileName) {
        
        log.info("请求文件信息: datePath={}, fileName={}", datePath, fileName);
        
        FileDownloadResponse response = fileDownloadService.getFileInfo(datePath, fileName);
        
        if ("NOT_FOUND".equals(response.getStatus())) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    public ResponseEntity<List<FileDownloadResponse>> listAllFiles() {
        log.info("请求列出所有文件");
        
        List<FileDownloadResponse> files = fileDownloadService.listAllFiles();
        return ResponseEntity.ok(files);
    }

    @GetMapping("/list/{datePath}")
    public ResponseEntity<List<FileDownloadResponse>> listFilesByDate(
            @PathVariable String datePath) {
        
        log.info("请求按日期列出文件: datePath={}", datePath);
        
        List<FileDownloadResponse> files = fileDownloadService.listFilesByDate(datePath);
        return ResponseEntity.ok(files);
    }

    @GetMapping("/exists/{datePath}/{fileName}")
    public ResponseEntity<Boolean> checkFileExists(
            @PathVariable String datePath,
            @PathVariable String fileName) {
        
        boolean exists = fileDownloadService.fileExists(datePath, fileName);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/size/{datePath}/{fileName}")
    public ResponseEntity<Long> getFileSize(
            @PathVariable String datePath,
            @PathVariable String fileName) {
        
        long size = fileDownloadService.getFileSize(datePath, fileName);
        
        if (size < 0) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(size);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        log.error("文件下载异常: {}", e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("文件下载失败: " + e.getMessage());
    }
}