package com.ljy.third;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
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
