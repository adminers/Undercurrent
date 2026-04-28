package com.dpv4.task.model;

import java.time.LocalDateTime;

public class ApiResponse {
    
    private int statusCode;
    private String body;
    private LocalDateTime responseTime;
    private long durationMs;
    private boolean success;
    private String errorMessage;
    
    public ApiResponse() {
        this.responseTime = LocalDateTime.now();
    }
    
    public int getStatusCode() {
        return statusCode;
    }
    
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        this.success = statusCode >= 200 && statusCode < 300;
    }
    
    public String getBody() {
        return body;
    }
    
    public void setBody(String body) {
        this.body = body;
    }
    
    public LocalDateTime getResponseTime() {
        return responseTime;
    }
    
    public void setResponseTime(LocalDateTime responseTime) {
        this.responseTime = responseTime;
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
    
    @Override
    public String toString() {
        return "ApiResponse{" +
                "statusCode=" + statusCode +
                ", success=" + success +
                ", durationMs=" + durationMs +
                ", body='" + body + '\'' +
                '}';
    }
}