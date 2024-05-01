package com.fly;

import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TemplateTest
{
    @Test
    public void test()
        throws Exception
    {
        try (Scanner sc = new Scanner(System.in))
        {
            do
            {
                
                log.info("------------输入x退出,回车换行继续------------");
            } while (!"x".equalsIgnoreCase(sc.nextLine()));
            log.info("------------成功退出------------");
        }
    }
    
    @Test
    public void test1()
        throws IOException
    {
        Resource resource = new ClassPathResource("");
        String protocol = resource.getURL().getProtocol();
        log.info("###### protocol: {}", protocol);
    }
    
}