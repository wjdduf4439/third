package com.ljy.third.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ljy.third.service.loginHomeService;
import com.ljy.third.vo.loginHomeVO;

@Controller
public class loginHomeController{
	
	@Resource(name = "loginHomeService")
	loginHomeService mloginHomeService;
	

	
	@RequestMapping("/loginHome.go")
	public String getLoginHome(ModelMap map) throws Exception {
		
		System.out.println("access getLoginHome");
		
		return "loginHome";
	}
	
	
	
	
	@RequestMapping("/accLoginHome.go")
	public String accLoginHome(ModelMap map, @ModelAttribute("loginHomeVO")loginHomeVO mloginHomeVO ) throws Exception {
		
		System.out.println("in id : " + mloginHomeVO.getId());
		System.out.println("in pw : " + mloginHomeVO.getPw());
		
		System.out.println("access Login Controller");
		loginHomeVO registedLoginHomeVO = mloginHomeService.accLoginHome(mloginHomeVO);
		
		System.out.println("regist id : " + registedLoginHomeVO.getId());
		System.out.println("regist pw : " + registedLoginHomeVO.getPw());
		
		map.addAttribute("accessTok","0");
		if( mloginHomeVO.getId().equals(registedLoginHomeVO.getId()) == true && mloginHomeVO.getPw().equals(registedLoginHomeVO.getPw()) == true ) {
			
			map.addAttribute("accessTok","1");
			
		}
		
		
		
		return "succLogin";
	}
	
	
	
	
}
