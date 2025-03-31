package com.fly.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 
 * 配置跨域请求
 * 
 * @author 00fly
 * @version [版本号, 2021年9月29日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Configuration
public class GlobalCorsConfig
{
    @Bean
    public CorsFilter corsFilter()
    {
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config());
        return new CorsFilter(configSource);
    }
    
    private CorsConfiguration config()
    {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin(CorsConfiguration.ALL);
        config.addAllowedMethod(CorsConfiguration.ALL);
        config.setAllowCredentials(true);
        config.addAllowedHeader("Origin,x-requested-with,Content-Type,Accept,X-Cookie");
        config.addExposedHeader("Origin,x-requested-with,Content-Type,Accept,X-Cookie");
        return config;
    }
}
