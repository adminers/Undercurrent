package com.fly.core.log.job;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomUtils;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.fly.core.utils.SpringContextUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * ScheduleLog
 * 
 * @author 00fly
 * @version [版本号, 2022年11月30日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Slf4j
@Component
public class ScheduleLog
{
    @Value("hello, 00fly in java!")
    private String welcome;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    // ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
    ExecutorService fixedThreadPool = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), new CustomizableThreadFactory("fixed-pool-"));
    
    @Scheduled(fixedRate = 30000L)
    public void run()
    {
        final long count = jdbcTemplate.queryForObject("select count(*) from boot_log", Long.class);
        log.info("------------ boot_log count: {} ----------", count);
        if (count > 100)
        {
            log.info("###### truncate table boot_log ######");
            jdbcTemplate.execute("truncate table boot_log");
        }
        
        for (int i = 0; i < RandomUtils.nextInt(5, 10); i++)
        {
            fixedThreadPool.execute(() -> runCall());
        }
    }
    
    private void runCall()
    {
        // 演示MDC和NDC
        final String id = UUID.randomUUID().toString();
        final String profile = SpringContextUtils.getActiveProfile();
        ThreadContext.put("id", id);
        ThreadContext.push(profile);
        try
        {
            if (RandomUtils.nextBoolean())
            {
                final int i = 1 / 0; // 抛出异常
                Assert.notNull(i, "The value must be null");
            }
        }
        catch (Exception e)
        {
            log.error("系统出错：" + e.getMessage(), e);
        }
        for (int i = 0; i < 10; i++)
        {
            log.info("★★★★★★★  {} ==> {}", welcome, id);
            try
            {
                TimeUnit.MILLISECONDS.sleep(RandomUtils.nextLong(1000L, 3000L));
            }
            catch (InterruptedException e)
            {
                log.error(e.getMessage(), e);
            }
        }
        ThreadContext.clearAll();
    }
}
