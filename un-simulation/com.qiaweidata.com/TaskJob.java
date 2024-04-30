package com.fly.core.pool;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.stereotype.Component;

import com.fly.core.log.annotation.Log;
import com.fly.core.log.enums.BusinessType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TaskJob
{
    /**
     * 无默认值会抛出IllegalArgumentException
     */
    @Value("${welcome.msg:hello, 00fly!}")
    private String welcome;
    
    /**
     * 测试日志打印
     */
    @Scheduled(fixedRate = 5000L)
    @Log(title = "定时任务", businessType = BusinessType.OTHER)
    public void printLog()
    {
        log.trace("{}", welcome);
        log.debug("{}", welcome);
        log.info("{}", welcome);
        log.warn("{}", welcome);
        log.error("{}", welcome);
    }
    
    /**
     * 默认线程池的大小为1，配置线程池支持多个线程并发执行
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Bean
    public ScheduledExecutorService scheduledExecutorService()
    {
        // Executors.newScheduledThreadPool(10);
        return new ScheduledThreadPoolExecutor(10, new CustomizableThreadFactory("schedule-pool-"));
    }
}
