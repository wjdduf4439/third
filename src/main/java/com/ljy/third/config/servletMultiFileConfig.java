package com.ljy.third.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import jakarta.servlet.MultipartConfigElement;

@Configuration
public class servletMultiFileConfig {

    @Bean
    MultipartResolver multipartResolver() {
        StandardServletMultipartResolver multipartResolver = new StandardServletMultipartResolver();
        return multipartResolver; 
    }

    @Bean
    MultipartConfigElement multipartConfigElement() {
    	MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxRequestSize(DataSize.ofBytes(25 * 1024 * 1024));
        factory.setMaxFileSize(DataSize.ofBytes(20 * 1024 * 1024));

        return factory.createMultipartConfig();
    }
	
}
