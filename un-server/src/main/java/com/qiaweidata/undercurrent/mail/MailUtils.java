package com.qiaweidata.undercurrent.mail;

import cn.hutool.core.io.FileUtil;
import com.qiaweidata.undercurrent.SpringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.List;

/**
 * @Title: MailUtils
 * @Description: MailUtils
 * @date: 2023-05-08
 * @version: V1.0
 */
public class MailUtils {

    private static final Logger log = LogManager.getLogger(MailUtils.class);


    // 发件箱（这是一个假的发件箱）
    private static String MAIL_FROM = "1019358770@qq.com";

    // 收件箱（这是一个假的收件箱）
    private static String MAIL_TO = "1019358770@qq.com";

    public static JavaMailSender mailSender = SpringUtils.getBean(JavaMailSender.class);

    public static String send(String text) {

        SimpleMailMessage message = new SimpleMailMessage();

        // 发件箱
        message.setFrom(MAIL_FROM);

        // 收件箱可以是多个，用 String[] 表示多个收件箱
        message.setTo(MAIL_TO);

        // 邮件主题
        message.setSubject("THEME CODE :" + text);

        // 邮件正文
        message.setText("YES");

        try {
            mailSender.send(message);
            return "success";
        } catch (Exception e) {
            log.error("发送简单邮件异常", e);
            return "failure";
        }

    }
}
