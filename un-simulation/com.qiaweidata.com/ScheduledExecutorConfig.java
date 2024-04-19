package com.fly.core.config;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

@Configuration
public class ScheduledExecutorConfig
{
    /**
     * 默认线程池的大小为1，配置线程池支持多个线程并发执行
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Bean
    public ScheduledExecutorService scheduledExecutorService()
    {
        // return Executors.newScheduledThreadPool(5);
        return new ScheduledThreadPoolExecutor(5, new CustomizableThreadFactory("schedule-pool-"));
    }
}
