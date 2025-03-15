package com.fly;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PreDestroy;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.fly.core.log.LogPoolManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableAsync
@EnableScheduling
@SpringBootApplication
public class HelloApplication
{
    public static void main(String[] args)
    {
        // 加载外部配置文件
        // args = new String[] {"--spring.config.location=C:\\application-location.yml"};
        if (args.length > 0)
        {
            Stream.of(args).forEach(log::info);
            String location = Stream.of(args)
                .filter(arg -> arg.contains("--spring.config.location"))
                .map(arg -> StringUtils.substringAfter(arg, "="))
                .collect(Collectors.joining());
            LogPoolManager.setConfigLocation(location);
        }
        
        String params = StringUtils.join(args, ",");
        if (StringUtils.containsIgnoreCase(params, "--noWeb"))
        {
            log.info("############### without Web Configuration #############");
            new SpringApplicationBuilder(HelloApplication.class).web(WebApplicationType.NONE).run(args);
            return;
        }
        SpringApplication.run(HelloApplication.class, args);
    }
    
    @PreDestroy
    public void destroy()
    {
        log.info("###### destroy ######");
    }
}