package com.dpv4.task.service;


import com.dpv4.task.model.ApiRequest;
import com.dpv4.task.model.ApiResponse;

public interface ApiInvoker {
    
    ApiResponse invoke(ApiRequest request);
    
    ApiResponse invokeWithRetry(ApiRequest request, int maxRetries, long retryDelay);
}