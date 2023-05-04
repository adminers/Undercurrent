package com.qiaweidata.undercurrent.mail;

import cn.hutool.core.io.FileUtil;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {

    private static Logger log = LoggerFactory.getLogger(MailController.class);

    @Autowired
    private JavaMailSender mailSender;

    // 发件箱（这是一个假的发件箱）
    private static String MAIL_FROM = "1019358770@qq.com";

    // 收件箱（这是一个假的收件箱）
    private static String MAIL_TO = "1019358770@qq.com";

    /**
     * 发送简单邮件
     */
    @RequestMapping("sendSimpleMail")
    public String sendSimpleMail() {

        SimpleMailMessage message = new SimpleMailMessage();

        // 发件箱
        message.setFrom(MAIL_FROM);

        // 收件箱可以是多个，用 String[] 表示多个收件箱
        message.setTo(MAIL_TO);

        List<String> fileNames = FileUtil.readUtf8Lines("E:\\giteeWork\\UndercurrentPro\\effict-side\\project\\un-simulation\\FileName.txt");

        // 邮件主题
        message.setSubject("THEME CODE :" + fileNames.get(0));

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