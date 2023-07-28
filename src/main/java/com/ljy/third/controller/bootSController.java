package com.ljy.third.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ljy.third.vo.TemplateInfoVO;

@Controller
public class bootSController {

	@RequestMapping("/bootSView.go")
	public String bootSView( ModelMap map) throws Exception {
		
		
		
		return "bootstrap_exam/layout-basic";
		
	}
	
}
