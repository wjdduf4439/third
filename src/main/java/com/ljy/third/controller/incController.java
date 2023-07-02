package com.ljy.third.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ljy.third.vo.loginHomeVO;

@Controller
public class incController {

	@RequestMapping(value = "/AdminHeader.go")
	public String header(ModelMap map, loginHomeVO mloginHomeVO) throws Exception {
		

		return "inc/header";
		
	}
	
}
