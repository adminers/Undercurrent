package com.dpv4.file.service;

import com.dpv4.file.dto.FileDownloadResponse;
import org.springframework.core.io.Resource;

import java.nio.file.Path;
import java.util.List;

public interface FileDownloadService {

    Resource downloadFile(String datePath, String fileName);

    FileDownloadResponse getFileInfo(String datePath, String fileName);

    List<FileDownloadResponse> listAllFiles();

    List<FileDownloadResponse> listFilesByDate(String datePath);

    Path getFilePath(String datePath, String fileName);

    long getFileSize(String datePath, String fileName);

    boolean fileExists(String datePath, String fileName);
}