package com.fly.hello.runner;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.fly.core.utils.SpringContextUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@ConditionalOnWebApplication
public class WebStartedRunner implements ApplicationListener<WebServerInitializedEvent>
{
    @Value("${server.port}")
    Integer port;
    
    @Autowired
    ServletContext servletContext;
    
    @Override
    public void onApplicationEvent(WebServerInitializedEvent event)
    {
        int port = event.getWebServer().getPort();
        log.info("#### server.port: {} ####", port);
    }
    
    @Bean
    public CommandLineRunner run()
    {
        return args -> {
            copyFile();
            openBrowser();
        };
    }
    
    private void copyFile()
        throws IOException
    {
        String path = "data/pic/18.jpg";
        Resource resource = new ClassPathResource(path);
        if (ResourceUtils.isJarURL(resource.getURL()))
        {
            try (InputStream is = resource.getInputStream())
            {
                File file = new File(path);
                FileUtils.copyInputStreamToFile(is, file);
                log.info("###### file path: {}", file.getCanonicalPath());
            }
        }
    }
    
    private void openBrowser()
        throws IOException
    {
        String url;
        switch (SpringContextUtils.getActiveProfile())
        {
            case "prod":
                log.info("请修改hosts: 127.0.0.1 test.00fly.online");
                url = "https://test.00fly.online:" + port + servletContext.getContextPath();
                break;
            
            default:
                url = SpringContextUtils.getServerBaseURL();
                break;
        }
        if (SystemUtils.IS_OS_WINDOWS && port > 0)
        {
            log.info("★★★★★★★★  now open Browser ★★★★★★★★ ");
            Runtime.getRuntime().exec("cmd /c start /min " + url);
            Runtime.getRuntime().exec("cmd /c start /min " + url + "/index");
            Runtime.getRuntime().exec("cmd /c start /min " + url + "/h2-console");
        }
    }
}
