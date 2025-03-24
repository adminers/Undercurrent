package com.fly.hello.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class PrintSysInfo
{
    @Value("${spring.datasource.url}")
    String url;
    
    @Autowired
    Environment environment;
    
    /**
     * 此写法methodName注意不要重复，否则报错：A bean with that name has already been defined in class path resource
     * 
     * @return
     */
    @Bean
    CommandLineRunner print()
    {
        return args -> {
            log.info("spring.datasource.url ：{}", url);
            log.info("spring.config.location：{}", environment.getProperty("spring.config.location"));
            log.info("spring.config.location：{}", System.getenv("spring.config.location"));
            
            // java -jar springboot-hello-1.0.0.jar --spring.config.location=./application-location.yml --spring.main.allow-bean-definition-overriding=true
            // 04-25 17:20:18.868 [main] INFO com.fly.hello.runner.PrintSysInfo - spring.datasource.url ：jdbc:h2:mem:local;database_to_upper=false
            // 04-25 17:20:18.868 [main] INFO com.fly.hello.runner.PrintSysInfo - spring.config.location：./application-location.yml
            // 04-25 17:20:18.869 [main] INFO com.fly.hello.runner.PrintSysInfo - spring.config.location：null
        };
    }
}
