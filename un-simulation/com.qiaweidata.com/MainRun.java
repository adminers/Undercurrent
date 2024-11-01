package com.fly;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainRun
{
    private static RestTemplate restTemplate;
    
    static
    {
        /*** Java HttpURLConnection (默认RestTemplate采用，不支持HTTP2) ***/
        // 使用默认客户端构造RestTemplate,写法1
        restTemplate = new RestTemplate();
        
        // 使用默认客户端构造RestTemplate,写法2
        restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory());
        
        // 使用默认客户端构造RestTemplate,写法3
        restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new SimpleClientHttpRequestFactory());
        
        // 使用默认客户端构造RestTemplate, 配置proxy，connectTimeout，readTimeout等参数
        SimpleClientHttpRequestFactory clientHttpRquestFactory = new SimpleClientHttpRequestFactory();
        clientHttpRquestFactory.setConnectTimeout(1000);
        clientHttpRquestFactory.setReadTimeout(1000);
        restTemplate = new RestTemplate(clientHttpRquestFactory);
    }
    
    public static void main(String[] args)
    {
        String url = "http://www.so.com/";
        String responseBody = restTemplate.getForObject(url, String.class);
        log.info("responseBody={}", responseBody);
        new MainRun().test();
    }
    
    public void test()
    {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.setValidating(false);
        context.load("classpath:applicationContext.xml");
        context.refresh();
        String url = "http://www.so.com/";
        String responseBody = context.getBean(RestTemplate.class).getForObject(url, String.class);
        log.info("responseBody={}", responseBody);
        context.close();
    }
    
}
