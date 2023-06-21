package com.ljy.third.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ljy.third.vo.loginHomeVO;

@RestController
public class homeController {

	@GetMapping("/")
	public String test( ) {
		return "Do you want to build a Snowman?";
	}
	
}
