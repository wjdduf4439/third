package com.ljy.third;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;

import com.ljy.third.config.EnvConfig;


@SpringBootApplication
@PropertySource(value = { "classpath:application.yml" }, factory = EnvConfig.class)
public class ThirdApplication extends SpringBootServletInitializer {
	
	public ThirdApplication(){
		super();
		setRegisterErrorPageFilter(false);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ThirdApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ThirdApplication.class);
	}
	
	
}
