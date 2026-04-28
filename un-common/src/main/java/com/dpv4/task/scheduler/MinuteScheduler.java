package com.dpv4.task.scheduler;

import com.dpv4.task.config.TaskConfig;
import com.dpv4.task.handler.ResultHandler;
import com.dpv4.task.model.TaskResult;
import com.dpv4.task.service.TaskExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MinuteScheduler {
    
    @Autowired
    private TaskExecutor taskExecutor;
    
    @Autowired
    private ResultHandler resultHandler;
    
    @Autowired
    private TaskConfig taskConfig;
    
    @Autowired
    private Executor threadPool;
    
    private ScheduledExecutorService scheduler;
    private final AtomicInteger executionCount = new AtomicInteger(0);
    
    public void start() {
        if (!taskConfig.isTaskEnabled()) {
            System.out.println("定时任务已禁用");
            return;
        }
        
        scheduler = Executors.newSingleThreadScheduledExecutor();
        
        // 每分钟执行一次（初始延迟5秒，避免启动时立即执行）
        scheduler.scheduleAtFixedRate(() -> {
            executeTask();
        }, 5, 60, TimeUnit.SECONDS);
        
        System.out.println("调度器已启动，每分钟执行一次");
    }
    
    private void executeTask() {
        int count = executionCount.incrementAndGet();
        System.out.println("第 " + count + " 次任务开始执行");
        
        // 在新的线程中执行任务，避免阻塞调度器
        threadPool.execute(() -> {
            TaskResult result = taskExecutor.execute();
            resultHandler.handle(result);
        });
    }
    
    public void stop() {
        if (scheduler != null) {
            scheduler.shutdown();
            System.out.println("调度器已停止");
        }
    }
    
    public int getExecutionCount() {
        return executionCount.get();
    }
}