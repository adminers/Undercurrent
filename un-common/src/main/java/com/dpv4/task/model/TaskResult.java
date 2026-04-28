package com.dpv4.task.model;


import java.time.LocalDateTime;

public class TaskResult {
    
    private String taskId;
    private String taskName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private ApiRequest request;
    private ApiResponse response;
    private long durationMs;
    private boolean success;
    private String errorMessage;
    private int retryCount;
    
    public TaskResult() {
        this.startTime = LocalDateTime.now();
    }
    
    public String getTaskId() {
        return taskId;
    }
    
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
    
    public String getTaskName() {
        return taskName;
    }
    
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    
    public LocalDateTime getStartTime() {
        return startTime;
    }
    
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
    
    public LocalDateTime getEndTime() {
        return endTime;
    }
    
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
    
    public ApiRequest getRequest() {
        return request;
    }
    
    public void setRequest(ApiRequest request) {
        this.request = request;
    }
    
    public ApiResponse getResponse() {
        return response;
    }
    
    public void setResponse(ApiResponse response) {
        this.response = response;
    }
    
    public long getDurationMs() {
        return durationMs;
    }
    
    public void setDurationMs(long durationMs) {
        this.durationMs = durationMs;
    }
    
    public boolean isSuccess() {
        return success;
    }
    
    public void setSuccess(boolean success) {
        this.success = success;
    }
    
    public String getErrorMessage() {
        return errorMessage;
    }
    
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    public int getRetryCount() {
        return retryCount;
    }
    
    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }
}