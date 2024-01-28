package com.ljy.third.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ljy.third.vo.TemplateInfoVO;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class servletVOConfig {

    @Bean
    TemplateInfoVO templateInfoVO(HttpServletRequest req){
        System.out.println("=======templateInfoVO bean 등록이 실행됩니다======");
        return new TemplateInfoVO();
    }
	
}
