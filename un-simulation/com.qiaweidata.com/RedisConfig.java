package com.fly.demo.auto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 
 * 配置文件值注入
 * 
 * @author 00fly
 * @version [版本号, 2018年7月20日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Configuration
@PropertySource(value = "classpath:redis.properties")
public class RedisConfig
{
    @Value("${port}")
    private int port;
    
    public int getPort()
    {
        return port;
    }
    
    public void setPort(int port)
    {
        this.port = port;
    }
}
