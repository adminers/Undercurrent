package com.dpv4.task.util;

import com.dpv4.task.config.TaskConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Component
public class HttpClientUtil {
    
    @Autowired
    private TaskConfig taskConfig;
    
    private final HttpClient httpClient;
    
    public HttpClientUtil() {
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
    }
    
    public String doPost(String body) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(taskConfig.getApiUrl()))
                .timeout(Duration.ofMillis(taskConfig.getTimeout()))
                .header("Content-Type", "application/json")
                .header("User-Agent", "Java-API-Task/1.0")
                .POST(HttpRequest.BodyPublishers.ofString(body != null ? body : "{}"))
                .build();
        
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        
        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            return response.body();
        } else {
            throw new IOException("HTTP " + response.statusCode() + ": " + response.body());
        }
    }
    
    public String doGet() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(taskConfig.getApiUrl()))
                .timeout(Duration.ofMillis(taskConfig.getTimeout()))
                .header("User-Agent", "Java-API-Task/1.0")
                .GET()
                .build();
        
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}