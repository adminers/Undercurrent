package com.dpv4.file.controller;

import com.dpv4.file.dto.FileUploadResponse;
import com.dpv4.file.dto.FileUploadResult;
import com.dpv4.file.model.FileRecord;
import com.dpv4.file.service.GenericFileUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

/**
 * 通用文件上传控制器
 * 支持所有类型文件上传，自动记录上传历史
 *
 * @author dpv4
 * @date 2026-05-19
 */
@RestController
@RequestMapping("/api/files")
@CrossOrigin(origins = "*")
public class GenericFileUploadController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenericFileUploadController.class);

    @Value("${file.upload.dir:./uploads}")
    private String uploadDir;

    private final GenericFileUploadService genericFileUploadService;

    public GenericFileUploadController(GenericFileUploadService genericFileUploadService) {
        this.genericFileUploadService = genericFileUploadService;
    }

    /**
     * 通用文件上传（单个）
     */
    @PostMapping(value = "/generic/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FileUploadResponse> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "description", required = false) String description) {

        LOGGER.info("收到通用文件上传请求: name={}, size={}, category={}",
                file.getOriginalFilename(), file.getSize(), category);

        FileUploadResult result = genericFileUploadService.uploadFile(file, category, description);

        if (result.getSuccess()) {
            return ResponseEntity.ok(FileUploadResponse.success(
                    "文件上传成功",
                    List.of(result)
            ));
        } else {
            return ResponseEntity.badRequest().body(FileUploadResponse.error(
                    "文件上传失败: " + result.getErrorMessage()
            ));
        }
    }

    /**
     * 通用文件批量上传
     */
    @PostMapping(value = "/generic/upload/batch", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FileUploadResponse> uploadFiles(
            @RequestParam("files") List<MultipartFile> files,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "description", required = false) String description) {

        LOGGER.info("收到通用文件批量上传请求: 数量={}, category={}", files.size(), category);

        if (files == null || files.isEmpty()) {
            return ResponseEntity.badRequest().body(FileUploadResponse.error("请选择要上传的文件"));
        }

        List<FileUploadResult> results = genericFileUploadService.uploadFiles(files, category, description);

        long successCount = results.stream().filter(FileUploadResult::getSuccess).count();
        long failureCount = results.size() - successCount;

        if (failureCount == 0) {
            return ResponseEntity.ok(FileUploadResponse.success(
                    String.format("全部 %d 个文件上传成功", successCount),
                    results
            ));
        } else if (successCount == 0) {
            return ResponseEntity.badRequest().body(FileUploadResponse.error(
                    String.format("全部 %d 个文件上传失败", failureCount)
            ));
        } else {
            return ResponseEntity.ok(FileUploadResponse.success(
                    String.format("部分文件上传完成: 成功 %d 个, 失败 %d 个", successCount, failureCount),
                    results
            ));
        }
    }

    /**
     * 获取所有文件记录
     */
    @GetMapping("/generic/records")
    public ResponseEntity<List<FileRecord>> getAllRecords(
            @RequestParam(value = "category", required = false) String category) {

        List<FileRecord> records;
        if (category != null && !category.isEmpty()) {
            records = genericFileUploadService.getRecordsByCategory(category);
        } else {
            records = genericFileUploadService.getAllRecords();
        }
        return ResponseEntity.ok(records);
    }

    /**
     * 获取文件记录数量
     */
    @GetMapping("/generic/count")
    public ResponseEntity<Map<String, Long>> getRecordCount() {
        long count = genericFileUploadService.getRecordCount();
        return ResponseEntity.ok(Map.of("count", count));
    }

    /**
     * 删除文件记录
     */
    @DeleteMapping("/generic/records/{id}")
    public ResponseEntity<Map<String, Object>> deleteRecord(@PathVariable String id) {
        boolean deleted = genericFileUploadService.deleteRecord(id);
        if (deleted) {
            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "记录删除成功"
            ));
        } else {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", "记录不存在或已删除"
            ));
        }
    }

    /**
     * 文件下载
     */
    @GetMapping("/download/{datePath}/{fileName}")
    public ResponseEntity<Resource> downloadFile(
            @PathVariable String datePath,
            @PathVariable String fileName) {

        try {
            Path filePath = Paths.get(uploadDir, datePath, fileName);
            Resource resource = new FileSystemResource(filePath);

            if (!resource.exists()) {
                return ResponseEntity.notFound().build();
            }

            String contentType = "application/octet-stream";
            String headerValue = "attachment; filename=\"" + fileName + "\"";

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                    .body(resource);
        } catch (Exception e) {
            LOGGER.error("文件下载失败: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}