package com.fly.core.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import lombok.extern.slf4j.Slf4j;

/**
 * Spring Context 工具类
 */
@Slf4j
@Component
public class SpringContextUtils implements ApplicationContextAware
{
    private static ApplicationContext applicationContext;
    
    private static String SERVER_BASE_URL = null;
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
        throws BeansException
    {
        log.info("###### execute setApplicationContext ######");
        SpringContextUtils.applicationContext = applicationContext;
    }
    
    public static ApplicationContext getApplicationContext()
    {
        return applicationContext;
    }
    
    public static <T> T getBean(Class<T> clazz)
    {
        Assert.notNull(applicationContext, "applicationContext is null");
        return applicationContext.getBean(clazz);
    }
    
    /**
     * execute @PostConstruct May be SpringContextUtils not inited, throw NullPointerException
     * 
     * @return
     */
    public static String getActiveProfile()
    {
        Assert.notNull(applicationContext, "applicationContext is null");
        String[] profiles = applicationContext.getEnvironment().getActiveProfiles();
        return (profiles != null && profiles.length > 0) ? StringUtils.join(profiles, ",") : "default";
    }
    
    /**
     * can use in @PostConstruct
     * 
     * @param context
     * @return
     */
    public static String getActiveProfile(ApplicationContext context)
    {
        Assert.notNull(context, "context is null");
        String[] profiles = context.getEnvironment().getActiveProfiles();
        return (profiles != null && profiles.length > 0) ? StringUtils.join(profiles, ",") : "default";
    }
    
    /**
     * get web服务基准地址，一般为 http://${ip}:${port}/${contentPath}
     * 
     * @return
     * @throws UnknownHostException
     * @see [类、类#方法、类#成员]
     */
    public static String getServerBaseURL()
        throws UnknownHostException
    {
        ServletContext servletContext = getBean(ServletContext.class);
        Assert.notNull(servletContext, "servletContext is null");
        if (SERVER_BASE_URL == null)
        {
            String ip = InetAddress.getLocalHost().getHostAddress();
            SERVER_BASE_URL = "http://" + ip + ":" + getProperty("server.port") + servletContext.getContextPath();
        }
        return SERVER_BASE_URL;
    }
    
    /**
     * getProperty
     * 
     * @param key eg：server.port
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String getProperty(String key)
    {
        return applicationContext.getEnvironment().getProperty(key, "");
    }
}
