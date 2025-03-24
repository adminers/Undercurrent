package com.fly.dns;

import java.io.IOException;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import io.leopard.javahost.JavaHost;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * JavaHostTest
 * 
 * @author 00fly
 * @version [版本号, 2023年3月15日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Slf4j
@RunWith(SpringRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class JavaHostTest
{
    @Autowired
    RestTemplate restTemplate;
    
    @Before
    public void before()
    {
        try
        {
            Resource resource = new ClassPathResource("vdns.properties");
            Properties props = PropertiesLoaderUtils.loadProperties(resource);
            JavaHost.updateVirtualDns(props);
            JavaHost.printAllVirtualDns();
        }
        catch (IOException e)
        {
            log.error(e.getMessage(), e);
        }
    }
    
    @Test
    public void test1()
    {
        String url = "http://top.00fly.online:8080/get";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        log.info("ResponseEntity StatusCode={}", responseEntity.getStatusCode());
        log.info("ResponseEntity={}", responseEntity);
    }
}
