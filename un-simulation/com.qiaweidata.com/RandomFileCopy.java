package com.fly.git.util;

import com.fly.core.utils.SpringContextUtils;
import com.fly.git.service.HttpService;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class RandomFileCopy {

//    public static final HttpService httpService = SpringContextUtils.getBean("httpService", HttpService.class);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            createFile();
        }
    }

    public static void createFile() {
        Path sourceDir = Paths.get("E:\\giteeWork\\UndercurrentPro\\effict-side");
        Path targetDir = Paths.get("E:\\temp\\project1\\un-simulation\\com.qiaweidata.com");

        try {
            List<Path> files = new ArrayList<>();
            Files.walk(sourceDir)
                    .filter(path -> Files.isRegularFile(path) && !path.getParent().getFileName().toString().equals("springboot-git"))
                    .forEach(files::add);

            while (files.isEmpty()) {
                // 如果文件夹及子文件夹中没有文件，则重新遍历文件夹
                files.clear();
                Files.walk(sourceDir)
                        .filter(path -> Files.isRegularFile(path) && !path.getParent().getFileName().toString().equals("springboot-git"))
                        .forEach(files::add);
            }

            Random random = new Random();
            Path randomFile = files.get(random.nextInt(files.size()));
            Files.copy(randomFile, targetDir.resolve(randomFile.getFileName()), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Random file copied successfully.");
//            String s = httpService.fetchDataFromUrl("");
//            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
