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

    /**
     * 发送简单邮件
     */
    @RequestMapping("sendSimpleMail")
    public String sendSimpleMail() {

        List<String> fileNames = FileUtil.readUtf8Lines("E:\\giteeWork\\UndercurrentPro\\effict-side\\project\\un-simulation\\FileName.txt");
        return MailUtils.send(fileNames.get(0));
    }
}