package com.fly.hello.runner;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;

import com.fly.core.utils.SpringContextUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * CommandLineRunners
 * 
 * @author 00fly
 * @version [版本号, 2023年3月31日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Slf4j
@Component
@Profile("dev")
public class CommandLineRunners
{
    @Autowired
    ResourceLoader resourceLoader;
    
    @Value("lili")
    String name;
    
    @Value("foo,bar,fun")
    Set<String> set;
    
    @Value("foo,bar,fun")
    List<String> list;
    
    @Bean
    private CommandLineRunner value()
    {
        return args -> {
            log.info("--- name: {}", name);
            log.info("--- set: {}", set);
            log.info("--- list: {}", list);
            log.info("================== profile: {}", SpringContextUtils.getActiveProfile());
        };
    }
    
    @Bean
    private CommandLineRunner resource()
    {
        return args -> {
            URL url = ClassUtils.getDefaultClassLoader().getResource("data/pic/18.jpg");
            log.info("--- ClassUtils: {}", url);
            log.info("--- ClassUtils: {}", url.getPath());
            
            url = ResourceUtils.getURL("classpath:data/pic/18.jpg");
            log.info("--- ResourceUtils: {}", url);
            log.info("--- ResourceUtils: {}", url.getPath());
            
            Resource resource = new ClassPathResource("data/pic/18.jpg");
            log.info("--- ClassPathResource: {}", resource.getURL().getPath());
            
            // file:/C:/Users/DELL/Desktop/JAR/springboot-hello-1.0.0.jar!/BOOT-INF/classes!/data/pic/18.jpg
        };
    }
    
    @Bean
    private CommandLineRunner resourceLoader()
    {
        return args -> {
            Resource resource = resourceLoader.getResource("classpath:data/pic/18.jpg");
            log.info("--- ResourceLoader: {}", resource.getURL().getPath());
        };
    }
    
    @Bean
    private CommandLineRunner readResource()
    {
        return args -> {
            Resource resource = new ClassPathResource("data/bak/data.json");
            URL url = resource.getURL();
            log.info("--- Resource URL: {}", url);
            // jar:file:/C:/Users/DELL/Desktop/JAR/springboot-hello-1.0.0.jar!/BOOT-INF/classes!/data/bak/data.json
            
            if (ResourceUtils.isFileURL(url))
            {
                String text = FileUtils.readFileToString(resource.getFile(), StandardCharsets.UTF_8.toString());
                log.info("--- FileUtils.readFileToString: {}", text);
                
                List<String> lines = FileUtils.readLines(resource.getFile(), StandardCharsets.UTF_8.toString());
                log.info("--- FileUtils.readLines");
                lines.stream().forEach(log::info);
            }
            else if (ResourceUtils.isJarURL(url))
            {
                String text = IOUtils.toString(url, StandardCharsets.UTF_8.toString());
                log.info("--- IOUtils.toString: {}", text);
                
                List<String> lines = IOUtils.readLines(resource.getInputStream(), StandardCharsets.UTF_8.toString());
                log.info("--- IOUtils.readLines");
                lines.stream().forEach(log::info);
            }
        };
    }
    
    @Bean
    private CommandLineRunner readResource2()
    {
        return args -> {
            Resource resource = new ClassPathResource("data/bak/data.json");
            String text = IOUtils.toString(resource.getURL(), StandardCharsets.UTF_8.toString());
            log.info("--- IOUtils.toString: {}", text);
            
            List<String> lines = IOUtils.readLines(resource.getInputStream(), StandardCharsets.UTF_8.toString());
            log.info("--- IOUtils.readLines");
            lines.stream().forEach(log::info);
        };
    }
}
