package com.dpv4.task.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskConfig {
    
    @Value("${api.url:https://httpbin.org/post}")
    private String apiUrl;
    
    @Value("${api.timeout:30000}")
    private int timeout;
    
    @Value("${task.enabled:true}")
    private boolean taskEnabled;
    
    @Value("${task.retry-count:3}")
    private int retryCount;
    
    @Value("${task.retry-delay:1000}")
    private long retryDelay;
    
    public String getApiUrl() {
        return apiUrl;
    }
    
    public int getTimeout() {
        return timeout;
    }
    
    public boolean isTaskEnabled() {
        return taskEnabled;
    }
    
    public int getRetryCount() {
        return retryCount;
    }
    
    public long getRetryDelay() {
        return retryDelay;
    }
}