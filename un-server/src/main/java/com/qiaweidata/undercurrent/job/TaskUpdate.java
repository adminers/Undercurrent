package com.qiaweidata.undercurrent.job;

import cn.hutool.core.io.FileUtil;
import com.qiaweidata.undercurrent.ai.ImitateCode;
import java.nio.charset.StandardCharsets;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.qiaweidata.undercurrent.ai.ImitateCode.PRO_FILE_CONFIG;

/**
 * update task mis
 *
 * @Title:
 * @Description:
 * @Company: www.fineplug.top
 * @author: shenshilong[shilong_shen@163.com]
 * @date:
 * @version: v1.0
 */
@Component
public class TaskUpdate {

    /**
     * 每5秒执行一次
     */
    @Scheduled(cron = "0/5 * *  * * ? ")
    public void execute() {

        PRO_FILE_CONFIG.clear();
        PRO_FILE_CONFIG.addAll(FileUtil.readUtf8Lines(ImitateCode.properties.get("fileRun")));

        ImitateCode.IS_RUN = PRO_FILE_CONFIG.get(0);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("TaskUpdate " + df.format(new Date()));
    }
}