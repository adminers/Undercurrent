package com.gamereference.util;

import java.io.InputStream;
import java.net.URI;
import java.net.URLConnection;
import java.nio.file.*;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class ControlledDownloader {

    private static final String TARGET_ROOT = "D:/downloads/";
    
    // 💡 关键：设置最大并发数，建议根据网站的耐受程度设置 (例如 5-10)
    private static final Semaphore SEMAPHORE = new Semaphore(10); 

    public static void main(String[] args) {
        // 模拟 1000 个 URL
        List<String> imageUrls = IntStream.range(0, 1000)
                .mapToObj(i -> "https://example.com/image.jpg") 
                .toList();

        downloadWithRateLimit(imageUrls);
    }

    public static void downloadWithRateLimit(List<String> urls) {
        try { Files.createDirectories(Paths.get(TARGET_ROOT)); } catch (Exception e) {}

        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            var futures = IntStream.range(0, urls.size())
                .mapToObj(i -> CompletableFuture.runAsync(() -> {
                    
                    // 🔒 获取许可，如果没有可用许可，虚拟线程会在此阻塞
                    try {
                        SEMAPHORE.acquire(); 
                        
                        String url = urls.get(i);
                        String extension = url.contains(".") ? url.substring(url.lastIndexOf(".")) : ".jpg";
                        String fileName = "reference" + (i + 1) + extension;
                        doDownload(url, fileName);
                        
                        // 🟢 模拟下载间隔，降低频率感（可选）
                        Thread.sleep(200); 
                        
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    } finally {
                        // 🔓 无论下载成功失败，最后必须释放许可
                        SEMAPHORE.release();
                    }
                }, executor))
                .toArray(CompletableFuture[]::new);

            CompletableFuture.allOf(futures).join();
        }
    }

    private static void doDownload(String urlStr, String fileName) {
        Path targetPath = Paths.get(TARGET_ROOT, fileName);
        try {
            URI uri = new URI(urlStr);
            URLConnection conn = uri.toURL().openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) Chrome/120.0.0.0");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(10000);

            try (InputStream in = conn.getInputStream()) {
                Files.copy(in, targetPath, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("✔ 已存至: " + targetPath.toAbsolutePath());
            }
        } catch (Exception e) {
            System.err.println("✘ 下载失败 [" + fileName + "]: " + e.getMessage());
        }
    }
}