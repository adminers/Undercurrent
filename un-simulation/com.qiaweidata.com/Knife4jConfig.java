package com.fly.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;

import io.swagger.annotations.ApiOperation;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * knife4j
 *
 * @author jack
 */
@EnableKnife4j
@Configuration
@EnableSwagger2WebMvc
@Import(BeanValidatorPluginsConfiguration.class)
public class Knife4jConfig
{
    
    @Value("${knife4j.enable: true}")
    private boolean enable;
    
    @Bean(value = "defaultApi2")
    public Docket defaultApi2()
    {
        return new Docket(DocumentationType.SWAGGER_2).enable(enable)
            .apiInfo(new ApiInfoBuilder().title("接口API").description("接口文档").termsOfServiceUrl("http://00fly.online/").contact(new Contact("xx", "xx", "xx")).version("1.0").build())
            // 分组名称
            .groupName("0.1版")
            .select()
            .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
            .paths(PathSelectors.any())
            .build();
    }
}
