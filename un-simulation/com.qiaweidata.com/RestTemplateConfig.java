package com.fly.core.config;

import java.nio.charset.StandardCharsets;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * 注册 RestTemplate
 * 
 * @author 00fly
 * @version [版本号, 2018年11月20日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Configuration
public class RestTemplateConfig
{
    @Bean
    public RestTemplate restTemplate()
    {
        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient());
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        // RestTemplate设置编码
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return restTemplate;
    }
    
    /**
     * Apache HttpClient
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    private HttpClient httpClient()
    {
        // 支持HTTP、HTTPS
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory> create().register("http", PlainConnectionSocketFactory.getSocketFactory()).register("https", SSLConnectionSocketFactory.getSocketFactory()).build();
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);
        connectionManager.setMaxTotal(200);
        connectionManager.setDefaultMaxPerRoute(100);
        connectionManager.setValidateAfterInactivity(2000);
        RequestConfig requestConfig = RequestConfig.custom()
            .setSocketTimeout(65000) // 服务器返回数据(response)的时间，超时抛出read timeout
            .setConnectTimeout(5000) // 连接上服务器(握手成功)的时间，超时抛出connect timeout
            .setConnectionRequestTimeout(1000)// 从连接池中获取连接的超时时间，超时抛出ConnectionPoolTimeoutException
            .build();
        return HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).setConnectionManager(connectionManager).build();
    }
}