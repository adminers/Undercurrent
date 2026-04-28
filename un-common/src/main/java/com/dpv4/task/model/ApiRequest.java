package com.dpv4.task.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ApiRequest {
    
    private String taskId;
    private LocalDateTime timestamp;
    private Map<String, Object> params;
    private String requestBody;
    
    public ApiRequest() {
        this.timestamp = LocalDateTime.now();
        this.params = new HashMap<>();
    }
    
    public ApiRequest(String taskId) {
        this();
        this.taskId = taskId;
    }
    
    public String getTaskId() {
        return taskId;
    }
    
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    
    public Map<String, Object> getParams() {
        return params;
    }
    
    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
    
    public void addParam(String key, Object value) {
        this.params.put(key, value);
    }
    
    public String getRequestBody() {
        return requestBody;
    }
    
    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }
}