package com.fly;

import java.net.InetAddress;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.map.repository.config.EnableMapRepositories;

@EnableMapRepositories
@SpringBootApplication
public class KeyValueApplication
{
    @Value("${server.port}")
    Integer port;
    
    @Autowired
    ServletContext servletContext;
    
    public static void main(String[] args)
    {
        SpringApplication.run(KeyValueApplication.class, args);
    }
    
    @Bean
    @ConditionalOnWebApplication
    CommandLineRunner init()
    {
        return args -> {
            if (SystemUtils.IS_OS_WINDOWS && port > 0)
            {
                String ip = InetAddress.getLocalHost().getHostAddress();
                String url = "http://" + ip + ":" + port + servletContext.getContextPath();
                Runtime.getRuntime().exec("cmd /c start /min " + url + "/user/");
                Runtime.getRuntime().exec("cmd /c start /min " + url + "/doc.html");
            }
        };
    }
}
