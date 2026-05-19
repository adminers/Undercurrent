package com.dpv4.file.service.impl;

import com.dpv4.file.model.FileRecord;
import com.dpv4.file.service.FileRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 文件记录服务实现类
 * 使用内存存储 + 文件持久化
 *
 * @author dpv4
 * @date 2026-05-19
 */
@Service
public class FileRecordServiceImpl implements FileRecordService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileRecordServiceImpl.class);

    @Value("${file.record.path:./data/file-records.json}")
    private String recordFilePath;

    private final Map<String, FileRecord> recordMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        loadRecordsFromFile();
    }

    @Override
    public FileRecord addRecord(FileRecord record) {
        String id = UUID.randomUUID().toString().replace("-", "");
        record.setId(id);
        record.setUploadTime(LocalDateTime.now());
        record.setDeleted(false);
        recordMap.put(id, record);
        saveRecordsToFile();
        LOGGER.info("文件记录添加成功: id={}, fileName={}", id, record.getOriginalFileName());
        return record;
    }

    @Override
    public FileRecord getRecordById(String id) {
        FileRecord record = recordMap.get(id);
        if (record != null && !record.getDeleted()) {
            return record;
        }
        return null;
    }

    @Override
    public List<FileRecord> getAllRecords() {
        return recordMap.values().stream()
                .filter(record -> !record.getDeleted())
                .sorted((a, b) -> b.getUploadTime().compareTo(a.getUploadTime()))
                .collect(Collectors.toList());
    }

    @Override
    public List<FileRecord> getRecordsByCategory(String category) {
        if (category == null || category.isEmpty()) {
            return getAllRecords();
        }
        return recordMap.values().stream()
                .filter(record -> !record.getDeleted() && category.equals(record.getCategory()))
                .sorted((a, b) -> b.getUploadTime().compareTo(a.getUploadTime()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteRecord(String id) {
        FileRecord record = recordMap.get(id);
        if (record != null && !record.getDeleted()) {
            record.setDeleted(true);
            saveRecordsToFile();
            LOGGER.info("文件记录已删除: id={}", id);
            return true;
        }
        return false;
    }

    @Override
    public long getRecordCount() {
        return recordMap.values().stream()
                .filter(record -> !record.getDeleted())
                .count();
    }

    private synchronized void saveRecordsToFile() {
        try {
            Path path = Paths.get(recordFilePath);
            Files.createDirectories(path.getParent());

            List<FileRecord> records = new ArrayList<>(recordMap.values());
            StringBuilder sb = new StringBuilder("[");

            for (int i = 0; i < records.size(); i++) {
                FileRecord record = records.get(i);
                sb.append("{");
                sb.append("\"id\":\"").append(record.getId()).append("\",");
                sb.append("\"originalFileName\":\"").append(escapeJson(record.getOriginalFileName())).append("\",");
                sb.append("\"storedFileName\":\"").append(escapeJson(record.getStoredFileName())).append("\",");
                sb.append("\"fileSize\":").append(record.getFileSize()).append(",");
                sb.append("\"contentType\":\"").append(escapeJson(record.getContentType())).append("\",");
                sb.append("\"filePath\":\"").append(escapeJson(record.getFilePath())).append("\",");
                sb.append("\"accessUrl\":\"").append(escapeJson(record.getAccessUrl())).append("\",");
                sb.append("\"uploadTime\":\"").append(record.getUploadTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)).append("\",");
                sb.append("\"category\":\"").append(escapeJson(record.getCategory())).append("\",");
                sb.append("\"description\":\"").append(escapeJson(record.getDescription())).append("\",");
                sb.append("\"deleted\":").append(record.getDeleted());
                sb.append("}");
                if (i < records.size() - 1) {
                    sb.append(",");
                }
            }
            sb.append("]");

            Files.writeString(path, sb.toString());
        } catch (IOException e) {
            LOGGER.error("保存文件记录失败: {}", e.getMessage(), e);
        }
    }

    private synchronized void loadRecordsFromFile() {
        try {
            Path path = Paths.get(recordFilePath);
            if (!Files.exists(path)) {
                return;
            }

            String content = Files.readString(path);
            if (content == null || content.isEmpty() || "[]".equals(content.trim())) {
                return;
            }

            content = content.trim();
            if (content.startsWith("[") && content.endsWith("]")) {
                content = content.substring(1, content.length() - 1);
            }

            String[] jsonObjects = content.split("(?<=}),\\s*(?={)");
            for (String json : jsonObjects) {
                try {
                    FileRecord record = parseJsonToRecord(json.trim());
                    if (record != null) {
                        recordMap.put(record.getId(), record);
                    }
                } catch (Exception e) {
                    LOGGER.warn("解析文件记录失败: {}", e.getMessage());
                }
            }

            LOGGER.info("已加载 {} 条文件记录", recordMap.size());
        } catch (IOException e) {
            LOGGER.error("加载文件记录失败: {}", e.getMessage(), e);
        }
    }

    private FileRecord parseJsonToRecord(String json) {
        FileRecord record = new FileRecord();

        record.setId(extractString(json, "\"id\""));
        record.setOriginalFileName(extractString(json, "\"originalFileName\""));
        record.setStoredFileName(extractString(json, "\"storedFileName\""));
        record.setFileSize(Long.parseLong(extractValue(json, "\"fileSize\"")));
        record.setContentType(extractString(json, "\"contentType\""));
        record.setFilePath(extractString(json, "\"filePath\""));
        record.setAccessUrl(extractString(json, "\"accessUrl\""));

        String uploadTimeStr = extractString(json, "\"uploadTime\"");
        if (uploadTimeStr != null && !uploadTimeStr.isEmpty()) {
            record.setUploadTime(LocalDateTime.parse(uploadTimeStr));
        }

        record.setCategory(extractString(json, "\"category\""));
        record.setDescription(extractString(json, "\"description\""));

        String deletedStr = extractValue(json, "\"deleted\"");
        record.setDeleted(Boolean.parseBoolean(deletedStr));

        return record;
    }

    private String extractValue(String json, String key) {
        int index = json.indexOf(key);
        if (index == -1) {
            return null;
        }
        int colonIndex = json.indexOf(":", index);
        if (colonIndex == -1) {
            return null;
        }
        int start = colonIndex + 1;
        while (start < json.length() && (json.charAt(start) == ' ' || json.charAt(start) == '\t')) {
            start++;
        }
        int end = start;
        if (json.charAt(start) == '"') {
            end = json.indexOf("\"", start + 1);
            if (end == -1) {
                return null;
            }
            return json.substring(start + 1, end);
        } else if (json.charAt(start) == '{' || json.charAt(start) == '[') {
            int depth = 1;
            end = start + 1;
            while (end < json.length() && depth > 0) {
                if (json.charAt(end) == '{' || json.charAt(end) == '[') {
                    depth++;
                } else if (json.charAt(end) == '}' || json.charAt(end) == ']') {
                    depth--;
                }
                end++;
            }
            return json.substring(start, end);
        } else {
            while (end < json.length() && json.charAt(end) != ',' && json.charAt(end) != '}') {
                end++;
            }
            return json.substring(start, end).trim();
        }
    }

    private String extractString(String json, String key) {
        String value = extractValue(json, key);
        if (value == null || value.isEmpty()) {
            return null;
        }
        if (value.startsWith("\"") && value.endsWith("\"")) {
            return value.substring(1, value.length() - 1);
        }
        return value;
    }

    private String escapeJson(String str) {
        if (str == null) {
            return "";
        }
        return str.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }
}