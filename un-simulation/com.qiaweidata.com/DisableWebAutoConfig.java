package com.fly.core.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import org.springframework.context.annotation.Configuration;

import com.github.xiaoymin.knife4j.spring.configuration.Knife4jAutoConfiguration;

/**
 * 
 * 非web环境下禁用AutoConfiguration
 * 
 * @author 00fly
 * @version [版本号, 2023年4月4日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Configuration
@ConditionalOnNotWebApplication
@EnableAutoConfiguration(exclude = Knife4jAutoConfiguration.class)
public class DisableWebAutoConfig
{
    
}
