package com.dpv4.task.service;

import com.dpv4.task.config.TaskConfig;
import com.dpv4.task.model.ApiRequest;
import com.dpv4.task.model.ApiResponse;
import com.dpv4.task.model.TaskResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class TaskExecutor {
    
    @Autowired
    private ApiInvoker apiInvoker;
    
    @Autowired
    private TaskConfig taskConfig;
    
    public TaskResult execute() {
        TaskResult result = new TaskResult();
        result.setTaskId(UUID.randomUUID().toString());
        result.setTaskName("MinuteApiTask");
        result.setStartTime(LocalDateTime.now());
        
        try {
            // 构建请求
            ApiRequest request = buildRequest();
            result.setRequest(request);
            
            // 调用 API
            long startMs = System.currentTimeMillis();
            ApiResponse response = apiInvoker.invokeWithRetry(
                    request,
                    taskConfig.getRetryCount(),
                    taskConfig.getRetryDelay()
            );
            result.setResponse(response);
            result.setDurationMs(System.currentTimeMillis() - startMs);
            result.setSuccess(response.isSuccess());
            result.setEndTime(LocalDateTime.now());
            
            if (!response.isSuccess()) {
                result.setErrorMessage(response.getErrorMessage());
            }
            
        } catch (Exception e) {
            result.setSuccess(false);
            result.setErrorMessage(e.getMessage());
            result.setEndTime(LocalDateTime.now());
        }
        
        return result;
    }
    
    private ApiRequest buildRequest() {
        ApiRequest request = new ApiRequest();
        request.setRequestBody("{\"timestamp\":\"" + LocalDateTime.now() + "\",\"action\":\"heartbeat\"}");
        request.addParam("timestamp", LocalDateTime.now().toString());
        return request;
    }
}