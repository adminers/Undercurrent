package com.fly.quick.resful;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fly.QuickSrcApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = QuickSrcApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestRestTemplateTest
{
    @LocalServerPort
    int port;
    
    @Autowired
    TestRestTemplate restTemplate;
    
    @Test
    public void test()
    {
        ResponseEntity<String> response = restTemplate.getForEntity("/show/info", String.class);
        log.info("###### ServerPort = {}", port);
        log.info("###### StatusCode = {}, Body = {}", response.getStatusCodeValue(), response.getBody());
    }
    
    @Test
    public void testWeb()
    {
        ResponseEntity<String> entity = restTemplate.getForEntity("/demo/first", String.class);
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(entity.getBody()).contains("success");
    }
}
