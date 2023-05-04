package com.qiaweidata.undercurrent.job;

import com.qiaweidata.undercurrent.ai.ImitateCode;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.qiaweidata.undercurrent.ai.ImitateCode.LINE_INDEX;

/**
 * @Title:
 * @Description:
 * @Company: www.fineplug.top
 * @author: shenshilong[shilong_shen@163.com]
 * @date:
 * @version: v1.0
 */
@Component
public class TaskRun {

    @Autowired
    private ImitateCode imitateCode;

    private final AtomicInteger currentLineIndex = new AtomicInteger(0);

    /**
     * 每3秒执行一次
     */
    @Scheduled(cron = "0/3 * *  * * ? ")
    public void execute() {

        if ("0".equals(ImitateCode.IS_RUN)) {
            return;
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("weloc " + df.format(new Date()));
        if (this.currentLineIndex.get() == LINE_INDEX.get()) {
            this.imitateCode.timer();
        } else {

            // 清除

        }
    }
}