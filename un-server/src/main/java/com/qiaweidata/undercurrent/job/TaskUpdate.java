package com.qiaweidata.undercurrent.job;

import cn.hutool.core.io.FileUtil;
import com.qiaweidata.undercurrent.ai.ImitateCode;
import java.nio.charset.StandardCharsets;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

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

        ImitateCode.IS_RUN = FileUtil.readString(ImitateCode.properties.get("fileRun"), StandardCharsets.UTF_8);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("TaskUpdate " + df.format(new Date()));
    }
}