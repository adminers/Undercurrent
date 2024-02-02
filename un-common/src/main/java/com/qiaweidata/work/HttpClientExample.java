package com.qiaweidata.work;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class HttpClientExample {
    public static void main(String[] args) {
        String url = "http://example.com";  // 替换为你要请求的URL

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        CompletableFuture<HttpResponse<String>> futureResponse = httpClient.sendAsync(httpRequest,
                HttpResponse.BodyHandlers.ofString());

        futureResponse.thenAccept(response -> {
            int statusCode = response.statusCode();
            String body = response.body();

            System.out.println("Status Code: " + statusCode);
            System.out.println("Response Body: " + body);
        });

        // 等待异步请求完成
        futureResponse.join();
    }
}
