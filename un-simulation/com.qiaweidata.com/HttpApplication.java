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
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class HttpApplication
{
    @Value("${server.port}")
    Integer port;
    
    @Autowired
    ServletContext servletContext;
    
    public static void main(String[] args)
    {
        SpringApplication.run(HttpApplication.class, args);
    }
    
    @Bean
    @ConditionalOnWebApplication
    CommandLineRunner init()
    {
        return args -> {
            if (SystemUtils.IS_OS_WINDOWS)// 防止非windows系统报错，启动失败
            {
                String ip = InetAddress.getLocalHost().getHostAddress();
                String url = "http://" + ip + ":" + port + servletContext.getContextPath();
                Runtime.getRuntime().exec("cmd /c start /min " + url);
                Runtime.getRuntime().exec("cmd /c start /min " + url + "/api/post");
            }
        };
    }
}
