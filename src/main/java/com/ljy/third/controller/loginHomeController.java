package com.ljy.third.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.ljy.third.service.loginHomeService;
import com.ljy.third.vo.loginHomeVO;

@Controller
public class loginHomeController{
	
	@Resource(name = "loginHomeService")
	loginHomeService mloginHomeService;
	
	@GetMapping("/loginHome.go")
	public String getLoginHome() throws Exception {
		
		System.out.println("access getLoginHome");
		
		return "loginHome";
	}
	
	
	@GetMapping("/accLoginHome.go")
	public String accLoginHome( loginHomeVO mloginHomeVO ) throws Exception {
		
		System.out.println("access Login Controller");
		mloginHomeVO = mloginHomeService.accLoginHome(mloginHomeVO);
		
		
		return "succLogin";
	}
}
