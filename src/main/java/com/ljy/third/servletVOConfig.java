package com.ljy.third;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ljy.third.vo.TemplateInfoVO;

@Configuration
public class servletVOConfig {
    
    @Bean
    public TemplateInfoVO templateInfoVO(HttpServletRequest req){
        System.out.println("=======templateInfoVO bean 등록이 실행됩니다======");
        return new TemplateInfoVO();
    }
	
}
