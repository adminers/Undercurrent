package com.fly.core.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SpringContextUtils implements ApplicationContextAware
{
    private static ApplicationContext applicationContext;
    
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
     * getActiveProfile
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String getActiveProfile()
    {
        Assert.notNull(applicationContext, "applicationContext is null");
        String[] profiles = applicationContext.getEnvironment().getActiveProfiles();
        return (profiles != null && profiles.length > 0) ? StringUtils.join(profiles, ",") : "default";
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
