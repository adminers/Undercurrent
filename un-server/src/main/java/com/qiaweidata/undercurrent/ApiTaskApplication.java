package com.qiaweidata.undercurrent;

import com.dpv4.task.scheduler.MinuteScheduler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ApiTaskApplication {
    
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ApiTaskApplication.class, args);
        
        // 启动定时任务
        MinuteScheduler scheduler = context.getBean(MinuteScheduler.class);
        scheduler.start();
        
        System.out.println("API 定时任务已启动，每分钟执行一次");
    }
}