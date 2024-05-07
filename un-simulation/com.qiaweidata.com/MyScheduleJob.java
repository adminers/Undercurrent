package com.fly.demo.auto;

import java.io.IOException;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 定时任务类
 * 
 * @author 00fly
 * @version [版本号, 2017年4月25日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Slf4j
@Component
public class MyScheduleJob
{
    @Component
    class MyTask1 implements SchedulingConfigurer
    {
        PropertiesConfiguration config;
        
        public MyTask1()
        {
            super();
            try
            {
                config = new PropertiesConfiguration("demo.properties");
                FileChangedReloadingStrategy strategy = new FileChangedReloadingStrategy();
                strategy.setRefreshDelay(60000L);// 刷新周期1分钟
                config.setReloadingStrategy(strategy);
            }
            catch (ConfigurationException e)
            {
                log.error(e.getMessage(), e.getCause());
            }
        }
        
        @Override
        public void configureTasks(ScheduledTaskRegistrar taskRegistrar)
        {
            taskRegistrar.addTriggerTask(new Runnable()
            {
                @Override
                public void run()
                {
                    // 任务逻辑
                    log.info("★★★★★★★ MyTask1 run ★★★★★★★");
                }
            }, new Trigger()
            {
                @Override
                public Date nextExecutionTime(TriggerContext triggerContext)
                {
                    // 任务触发，可修改任务的执行周期
                    String cron = config.getString("schedule.myjob.cron");
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                }
            });
        }
    }
    
    @Component
    class MyTask2 implements SchedulingConfigurer
    {
        ResourceBundle config = ResourceBundle.getBundle("demo");
        
        @Override
        public void configureTasks(ScheduledTaskRegistrar taskRegistrar)
        {
            taskRegistrar.addTriggerTask(new Runnable()
            {
                @Override
                public void run()
                {
                    // 任务逻辑
                    log.info("------ MyTask2 run -------");
                }
            }, new Trigger()
            {
                @Override
                public Date nextExecutionTime(TriggerContext triggerContext)
                {
                    // 任务触发，ResourceBundle方式读取修改任务的执行周期
                    ResourceBundle.clearCache();
                    String cron = config.getString("schedule.myjob.cron");
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                }
            });
        }
    }
    
    @Component
    class MyTask3 implements SchedulingConfigurer
    {
        Resource resource1 = new ClassPathResource("cron");
        
        @Override
        public void configureTasks(ScheduledTaskRegistrar taskRegistrar)
        {
            taskRegistrar.addTriggerTask(new Runnable()
            {
                @Override
                public void run()
                {
                    // 任务逻辑
                    log.info("######### MyTask3 run #########");
                }
            }, new Trigger()
            {
                @Override
                public Date nextExecutionTime(TriggerContext triggerContext)
                {
                    String cronText = "*/4 * * * * ?";
                    try
                    {
                        cronText = FileUtils.readFileToString(resource1.getFile(), "utf-8");
                        log.info("cronText = {}", cronText);
                    }
                    catch (IOException e)
                    {
                        log.error(e.getMessage(), e.getCause());
                    }
                    // 任务触发，文件方式读取可修改任务的执行周期
                    return new CronTrigger(cronText).nextExecutionTime(triggerContext);
                }
            });
        }
    }
}