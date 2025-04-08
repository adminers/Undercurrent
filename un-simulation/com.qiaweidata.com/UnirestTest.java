package com.fly.unirest;

import org.junit.Test;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * UnirestTest
 * 
 * @author 00fly
 * @version [版本号, 2018年11月10日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Slf4j
public class UnirestTest
{
    @Test
    public void testGET()
    {
        try
        {
            HttpResponse<String> jsonResponse = Unirest.get("http://httpbin.org/post")
                    .header("accept", "application/json")
                    .queryString("id", "123456")// url后拼接的参数
                    .asString();
            log.info("{}", jsonResponse.getStatus());
            log.info("{}", jsonResponse.getStatusText());
            log.info("{}", jsonResponse.getHeaders());
            log.info("{}", jsonResponse.getBody());
            log.info("{}", jsonResponse.getRawBody());
        }
        catch (UnirestException e)
        {
            log.error(e.getMessage(), e);
        }
    }
    
    @Test
    public void testPOST()
    {
        try
        {
            HttpResponse<JsonNode> jsonResponse = Unirest.post("http://httpbin.org/post")
                    .header("accept", "application/json")
                    .queryString("id", "123456")// url后拼接的参数
                    .field("last", "Polo")
                    .asJson();
            log.info("{}", jsonResponse.getStatus());
            log.info("{}", jsonResponse.getStatusText());
            log.info("{}", jsonResponse.getHeaders());
            log.info("{}", jsonResponse.getBody());
            log.info("{}", jsonResponse.getRawBody());
        }
        catch (UnirestException e)
        {
            log.error(e.getMessage(), e);
        }
    }
}
