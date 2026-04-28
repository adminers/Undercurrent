package com.dpv4.task.service;

import com.dpv4.task.model.ApiRequest;
import com.dpv4.task.model.ApiResponse;
import com.dpv4.task.util.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiInvokerImpl implements ApiInvoker {
    
    @Autowired
    private HttpClientUtil httpClientUtil;
    
    @Override
    public ApiResponse invoke(ApiRequest request) {
        long startTime = System.currentTimeMillis();
        ApiResponse response = new ApiResponse();
        
        try {
            // 发送 POST 请求
            String result = httpClientUtil.doPost(request.getRequestBody());
            response.setBody(result);
            response.setStatusCode(200);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setErrorMessage(e.getMessage());
            response.setStatusCode(500);
        }
        
        response.setDurationMs(System.currentTimeMillis() - startTime);
        return response;
    }
    
    @Override
    public ApiResponse invokeWithRetry(ApiRequest request, int maxRetries, long retryDelay) {
        ApiResponse response = null;
        
        for (int i = 0; i <= maxRetries; i++) {
            response = invoke(request);
            
            if (response.isSuccess()) {
                return response;
            }
            
            if (i < maxRetries) {
                try {
                    Thread.sleep(retryDelay);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
        
        return response;
    }
}