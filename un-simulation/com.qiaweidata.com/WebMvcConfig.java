package com.fly.core.config;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fly.core.AuthorizationInterceptor;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * WebMvcConfig
 * 
 * @author 00fly
 * @version [版本号, 2019年7月21日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Slf4j
@Configuration
public class WebMvcConfig implements WebMvcConfigurer
{
    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;
    
    /**
     * 配置Interceptor
     * 
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(authorizationInterceptor).addPathPatterns("/user/**");
    }
    
    /**
     * 配置静态访问资源
     * 
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");
        registry.addResourceHandler("/pic/**").addResourceLocations("classpath:/pic/");
        try
        {
            // 外部文件目录映射
            registry.addResourceHandler("/upload/**").addResourceLocations("file:" + new File("./upload/").getCanonicalPath() + "/");
        }
        catch (IOException e)
        {
            log.error(e.getMessage(), e);
        }
    }
    
    /**
     * 配置跨域请求
     * 
     * @param registry
     */
//    @Override
//    public void addCorsMappings(CorsRegistry registry)
//    {
//        registry.addMapping("/**").allowCredentials(true).allowedHeaders("*").allowedOrigins("*").allowedMethods("*");
//    }
}