package com.dpv4.file.controller;

import com.dpv4.file.dto.FileUploadResponse;
import com.dpv4.file.dto.FileUploadResult;
import com.dpv4.file.service.FileUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 文件上传控制器
 * 支持多选文件上传，兼容苹果Safari浏览器
 *
 * @author dpv4
 * @date 2026-05-18
 */
@RestController
@RequestMapping("/api/files")
@CrossOrigin(origins = "*")
public class FileUploadController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);

    @Autowired
    private FileUploadService fileUploadService;

    /**
     * 上传单个文件
     *
     * @param file 上传的文件
     * @return 上传结果
     */
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FileUploadResponse> uploadFile(
            @RequestParam("file") MultipartFile file) {

        LOGGER.info("收到文件上传请求: name={}, size={}",
                file.getOriginalFilename(), file.getSize());

        FileUploadResult result = fileUploadService.uploadFile(file);

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
     * 批量上传文件（支持多选）
     *
     * @param files 文件列表
     * @return 上传结果
     */
    @PostMapping(value = "/upload/batch", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FileUploadResponse> uploadFiles(
            @RequestParam("files") List<MultipartFile> files) {

        LOGGER.info("收到批量文件上传请求: 数量={}", files.size());

        if (files == null || files.isEmpty()) {
            return ResponseEntity.badRequest().body(FileUploadResponse.error("请选择要上传的文件"));
        }

        List<FileUploadResult> results = fileUploadService.uploadFiles(files);

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
     * 获取允许的文件类型
     *
     * @return 允许的文件类型列表
     */
    @GetMapping("/allowed-types")
    public ResponseEntity<List<String>> getAllowedFileTypes() {
        return ResponseEntity.ok(fileUploadService.getAllowedFileTypes());
    }

    /**
     * 获取最大文件大小（字节）
     *
     * @return 最大文件大小
     */
    @GetMapping("/max-size")
    public ResponseEntity<Long> getMaxFileSize() {
        return ResponseEntity.ok(fileUploadService.getMaxFileSize());
    }
}