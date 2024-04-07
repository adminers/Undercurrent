package com.fly.log;

import java.io.IOException;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableScheduling
@SpringBootApplication
@PropertySource("classpath:jdbc.properties")
public class BootLogApplication implements CommandLineRunner
{
    @Value("${server.port}")
    Integer port;
    
    @Autowired
    ServletContext servletContext;
    
    public static void main(String[] args)
    {
        SpringApplication.run(BootLogApplication.class, args);
    }
    
    @Override
    public void run(String... args)
        throws IOException
    {
        if (SystemUtils.IS_OS_WINDOWS && port > 0)
        {
            log.info("★★★★★★★★  now open Browser ★★★★★★★★ ");
            String url = "http://127.0.0.1:" + port + servletContext.getContextPath();
            Runtime.getRuntime().exec("cmd /c start /min " + url + "/h2-console");
        }
    }
}