package com.qiaweidata.undercurrent.job;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpRequest;
import com.qiaweidata.undercurrent.ai.ImitateCode;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import com.qiaweidata.undercurrent.mail.MailUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static final Logger log = LogManager.getLogger(TaskRun.class);

    private final ImitateCode imitateCode = new ImitateCode();

    private final AtomicInteger currentLineIndex = new AtomicInteger(Integer.valueOf(FileUtil.readUtf8String(ImitateCode.properties.get("lineFile"))));

    /**
     * TRUE - stop
     */
    public final AtomicBoolean STATE = new AtomicBoolean(false);

    /**
     * TRUE - can submit
     */
    public final AtomicBoolean COMMIT_STATE = new AtomicBoolean(false);

    public static final List<Integer> WEB_RUN = new CopyOnWriteArrayList<>();

    /**
     * 每5秒执行一次
     */
    @Scheduled(cron = "0/5 * *  * * ? ")
    public void execute() {

        if ("0".equals(ImitateCode.IS_RUN) ||
            this.STATE.get()) {
            return;
        }
        this.STATE.set(true);
        try {

            if (this.currentLineIndex.get() == LINE_INDEX.get()) {
                this.imitateCode.timer();
            } else {

                // save
                this.imitateCode.save();

                // if web user , send msg
                sendMsg();

                // clear
                this.imitateCode.setText("");
                this.currentLineIndex.set(LINE_INDEX.get());
                COMMIT_STATE.set(true);
                FileUtil.writeUtf8String(String.valueOf(this.currentLineIndex), ImitateCode.properties.get("lineFile"));
            }
        } finally {
            this.STATE.set(false);
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("get code " + df.format(new Date()));
    }

    @Scheduled(cron = "0 0/30 *  * * ? ")
    public void commitRun() {

        String body = HttpRequest.post(ImitateCode.properties.get("gitUrl") + "/git/commitCode")
            .header("token", ImitateCode.properties.get("gitToken"))
            .execute()
            .body();

        log.info(" commit api complete ： {}", body);
        COMMIT_STATE.set(false);
    }

    private void sendMsg() {

        int lineIndex = this.currentLineIndex.get();
        if (WEB_RUN.isEmpty() ||
            !WEB_RUN.contains(lineIndex)) {
            return;
        }
        MailUtils.send(this.imitateCode.text + "\n" + this.imitateCode.getCode());
        WEB_RUN.remove(lineIndex);
    }

    public ImitateCode getImitateCode() {
        return this.imitateCode;
    }

    public int getCurrentLineIndex() {
        return currentLineIndex.get();
    }
}