package com.fly.mail.service;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import com.fly.mail.model.MailBean;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class InfoService
{
    @Autowired
    private JavaMailSender javaMailSender;
    
    @Value("${spring.mail.sender}")
    private String MAIL_SENDER; // 邮件发送者
    
    @Autowired
    private TemplateEngine templateEngine;
    
    /**
     * queryInfo
     * 
     * @param ip
     * @param port
     * @return
     * @throws UnknownHostException
     * @see [类、类#方法、类#成员]
     */
    @Cacheable(value = "demo")
    public Map<String, String> queryInfo(String ip, String port)
        throws UnknownHostException
    {
        String time = null;
        File war = new File("demo.jar");
        if (war.exists())
        {
            time = DateFormatUtils.format(war.lastModified(), "yyyy-MM-dd HH:mm:ss");
            log.info("**** {} , createTime : {},  ip : {}", war.getAbsolutePath(), time, ip);
        }
        Map<String, String> map = new HashMap<String, String>(3);
        map.put("createTime", time);
        map.put("port", port);
        map.put("client_ip", ip);
        map.put("server_ip", InetAddress.getLocalHost().getHostAddress());
        return map;
    }
    
    /**
     * 邮件发送
     * 
     * @param mailBean
     * @see [类、类#方法、类#成员]
     */
    public void sendSimpleMail(MailBean mailBean)
    {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        // 邮件发送人
        simpleMailMessage.setFrom(MAIL_SENDER);
        // 邮件接收人
        simpleMailMessage.setTo(mailBean.getRecipient());
        // 邮件主题
        simpleMailMessage.setSubject(mailBean.getSubject());
        // 邮件内容
        simpleMailMessage.setText(mailBean.getContent());
        javaMailSender.send(simpleMailMessage);
    }
    
    /**
     * 带图片html模板邮件发送
     * 
     * @param email
     * @param userName
     * @throws Exception
     */
    public void sendHtml(String email, String userName)
        throws Exception
    {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
        messageHelper.setTo(email); // 设置收件人邮箱
        messageHelper.setSubject("subject"); // 设置邮件主题
        Context context = new Context();
        context.setVariable("userName", userName);
        
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setCacheable(true);
        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");
        String process = templateEngine.process("email_welcome1", context);
        messageHelper.setText(process, true);
        Resource res = new ClassPathResource("templates/img/welcome.png");
        messageHelper.addInline("p00", res);
        javaMailSender.send(mimeMessage);
    }
}