package com.gamereference.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class JsonImageDownloader {

    private static final String TARGET_ROOT = "D:/downloads/";
    private static final String JSON_PATH = "image.json"; // 位于 resources 目录下

    // 💡 控频：最大允许 10 个线程同时下载，保护带宽也防止被封
    private static final Semaphore SEMAPHORE = new Semaphore(10);

    public static void main(String[] args) {
        // 1. 读取并解析 JSON
        List<String> imageUrls = loadUrlsFromJson();

        if (imageUrls == null || imageUrls.isEmpty()) {
            System.err.println("❌ 未能读取到图片链接，请检查 image.json 内容");
            return;
        }

        // 2. 执行限流下载
        downloadProcess(imageUrls);
    }

    private static List<String> loadUrlsFromJson() {
        System.out.println("📂 正在读取配置文件...");
        // 使用 ClassLoader 读取 resources 下的文件
        try (InputStream is = JsonImageDownloader.class.getClassLoader().getResourceAsStream(JSON_PATH)) {
            if (is == null) throw new RuntimeException("找不到文件: " + JSON_PATH);

            InputStreamReader reader = new InputStreamReader(is, StandardCharsets.UTF_8);
            // 使用 Gson 解析为 List<String>
            return new Gson().fromJson(reader, new TypeToken<List<String>>() {
            }.getType());
        } catch (Exception e) {
            System.err.println("❌ JSON 解析失败: " + e.getMessage());
            return null;
        }
    }

    private static void downloadProcess(List<String> urls) {
        try {
            Files.createDirectories(Paths.get(TARGET_ROOT));
        } catch (Exception e) {
        }

        // 使用 Java 21 虚拟线程执行器
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            System.out.println("🚀 开始有序下载 " + urls.size() + " 张图片...");

            var futures = IntStream.range(0, urls.size())
                    .mapToObj(i -> CompletableFuture.runAsync(() -> {
                        try {
                            SEMAPHORE.acquire(); // 获取许可

                            String url = urls.get(i);
                            String extension = url.contains(".") ? url.substring(url.lastIndexOf('.')) : ".jpg";
                            String fileName = "reference" + (i + 1) + extension;

                            performDownload(url, fileName);

                            // 随机微调下载间隔，模拟真人行为
                            Thread.sleep(ThreadLocalRandom.current().nextInt(100, 300));

                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        } finally {
                            SEMAPHORE.release(); // 释放许可
                        }
                    }, executor))
                    .toArray(CompletableFuture[]::new);

            CompletableFuture.allOf(futures).join();
            System.out.println("\n🎉 任务全部处理完毕！");
        }
    }

    private static void performDownload(String urlStr, String fileName) {
        Path targetPath = Paths.get(TARGET_ROOT, fileName);
        try {
            URLConnection conn = new URI(urlStr).toURL().openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) Chrome/120.0.0.0");
            conn.setConnectTimeout(5000);

            try (InputStream in = conn.getInputStream()) {
                Files.copy(in, targetPath, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("✔ " + fileName + " 下载成功");
            }
        } catch (Exception e) {
            System.err.println("✘ " + fileName + " 失败: " + e.getMessage());
        }
    }
}