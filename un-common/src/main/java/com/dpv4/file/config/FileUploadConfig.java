package com.dpv4.file.config;

import jakarta.servlet.MultipartConfigElement;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

/**
 * 文件上传配置类
 * 配置文件上传大小限制等参数
 *
 * @author dpv4
 * @date 2026-05-18
 */
@Configuration
public class FileUploadConfig {

    /**
     * 配置文件上传大小限制
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 单个文件最大 50MB
        factory.setMaxFileSize(DataSize.ofMegabytes(50));
        // 总上传数据最大 100MB
        factory.setMaxRequestSize(DataSize.ofMegabytes(100));
        return factory.createMultipartConfig();
    }
}