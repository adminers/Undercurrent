package com.fly.log.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Spring Context 工具类
 */
@Component
public class SpringContextUtils implements ApplicationContextAware
{
    public static ApplicationContext applicationContext;
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
        throws BeansException
    {
        SpringContextUtils.applicationContext = applicationContext;
    }
    
    public static <T> T getBean(Class<T> clazz)
    {
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