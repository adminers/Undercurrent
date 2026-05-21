package com.dpv4.file.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileDownloadResponse {

    private String fileName;
    
    private String filePath;
    
    private long fileSize;
    
    private String fileType;
    
    private LocalDateTime uploadTime;
    
    private String downloadUrl;
    
    private String status;
}