package com.qiaweidata.undercurrent.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jeecg.common.api.vo.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @Title: FileController
 * @Description: FileController
 * @date: 2023-09-12
 * @version: V1.0
 */
@RestController
@CrossOrigin
public class FileController {

    private static final Logger log = LogManager.getLogger(FileController.class);

    @RequestMapping(value = "/caton2", method = RequestMethod.GET)
    @ResponseBody
    public String caton(HttpServletRequest request, HttpServletResponse response) throws Exception {

        System.out.println(" caton");
        //TimeUnit.SECONDS.sleep(1);
        return "{\"caton\":\"caton\"}";
    }

    @PostMapping(value = "/upload")
    public Result<?> upload(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {

        String msg = "上传成功!";
        if (!file.isEmpty()) {
            try {
                LocalDate currentDate = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String formattedDate = currentDate.format(formatter);

                DateTimeFormatter month = DateTimeFormatter.ofPattern("yyyy-MM");
                String monthDate = currentDate.format(month);
                String root = "E:\\temp\\love_file\\" + monthDate;
                Path path = Paths.get(root);
                if (!Files.exists(path)) {
                    Files.createDirectory(path);
                }
                file.transferTo(new File(root + "\\" + formattedDate + "_" + System.currentTimeMillis() + ".png"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            msg = "请选择一个文件上传";
        }
        log.info("传来一个文件");
        return Result.OK(msg);
    }

    @RequestMapping(value = "/runFlag", method = RequestMethod.GET)
    @CrossOrigin
    public String runFlag(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String runFlag = "0";
        String filePath = "E:\\temp\\love_file\\run_flag.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String firstLine = reader.readLine();

            if (firstLine != null) {
                runFlag = firstLine;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return runFlag;
    }
}
