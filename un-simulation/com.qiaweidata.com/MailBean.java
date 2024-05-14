package com.fly.mail.model;

import java.io.Serializable;

/**
 * 
 * MailBean
 * 
 * @author 00fly
 * @version [版本号, 2020年1月11日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class MailBean implements Serializable
{
    private static final long serialVersionUID = 3940915172241354982L;
    
    private String recipient; // 邮件接收人
    
    private String subject; // 邮件主题
    
    private String content; // 邮件内容
    
    public String getRecipient()
    {
        return recipient;
    }
    
    public void setRecipient(String recipient)
    {
        this.recipient = recipient;
    }
    
    public String getSubject()
    {
        return subject;
    }
    
    public void setSubject(String subject)
    {
        this.subject = subject;
    }
    
    public String getContent()
    {
        return content;
    }
    
    public void setContent(String content)
    {
        this.content = content;
    }
}