package com.fly.log.job;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomUtils;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.stereotype.Component;

import com.fly.log.service.User;
import com.fly.log.service.UserManager;
import com.fly.log.utils.SpringContextUtils;

import lombok.extern.log4j.Log4j2;

/**
 * 
 * ScheduleJob
 * 
 * @author 00fly
 * @version [版本号, 2022年11月30日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Log4j2
@Component
public class ScheduleJob
{
    @Value("${welcome.message:hello, 00fly in java!}")
    private String welcome;
    
    @Autowired
    private UserManager userManager;
    
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Bean
    public ScheduledExecutorService scheduledExecutorService()
    {
        // return Executors.newScheduledThreadPool(8);
        return new ScheduledThreadPoolExecutor(8, new CustomizableThreadFactory("schedule-pool-"));
    }
    
    @Scheduled(fixedRate = 30 * 1000L)
    public void run1()
    {
        long t = System.currentTimeMillis();
        for (long i = 1; i < 10; i++)
        {
            User user = new User();
            user.setId(t++);
            user.setUserName("小明");
            user.setPassWord("password_" + t);
            userManager.saveUser(user);
        }
    }
    
    @Scheduled(fixedRate = 30000L)
    public void run2()
    {
        log.info("###### truncate table boot_log ######");
        jdbcTemplate.execute("truncate table boot_log");
        
        // ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
        ExecutorService fixedThreadPool = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), new CustomizableThreadFactory("fixed-pool-"));
        for (int i = 0; i < RandomUtils.nextInt(5, 10); i++)
        {
            fixedThreadPool.execute(() -> runCall());
        }
    }
    
    public void runCall()
    {
        // 演示MDC和NDC
        String id = UUID.randomUUID().toString();
        String profile = SpringContextUtils.getActiveProfile();
        ThreadContext.put("id", id);
        ThreadContext.push(profile);
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
    
    @Scheduled(fixedRate = 10000L)
    public void showCount()
    {
        log.info("------------ boot_log count: {} ----------", jdbcTemplate.queryForObject("select count(*) from boot_log", Long.class));
    }
}
