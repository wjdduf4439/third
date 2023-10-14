package com.ljy.third.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ljy.third.service.loginHomeService;
import com.ljy.third.vo.loginHomeVO;

//login초기화면과 login동작과 관련된 동작을 담당하는 컨트롤러
@Controller
public class loginHomeController{
	
	@Resource(name = "loginHomeService")
	loginHomeService mloginHomeService;
	

	
	@RequestMapping("/loginHome.go")
	public String getLoginHome(ModelMap map, HttpServletRequest req) throws Exception { 
		/*
		 String resourceSrc = req.getSession().getServletContext().getRealPath("/");
		 System.out.println("resourceSrc : " + resourceSrc);
		  
		 String processerName = System.getProperty("os.name").toLowerCase();
		 System.out.println("processerName : " + processerName);
		 */
		
		
		
		return "loginHome"; 
	}
	
	@RequestMapping("/accLoginHome.go")
	public String accLoginHome(ModelMap map, @ModelAttribute("loginHomeVO")loginHomeVO mloginHomeVO, HttpServletRequest req) throws Exception {
		
		/*
		 System.out.println("in id : " + mloginHomeVO.getId());
		 System.out.println("in pw : " + mloginHomeVO.getPw());
		 System.out.println("access Login Controller");
		 */
		loginHomeVO registedLoginHomeVO = mloginHomeService.accLoginHome(mloginHomeVO);
		
		System.out.println("regist id : " + registedLoginHomeVO.getId());
		System.out.println("regist pw : " + registedLoginHomeVO.getPw());
		
		if( mloginHomeVO.getId().equals(registedLoginHomeVO.getId()) == true && mloginHomeVO.getPw().equals(registedLoginHomeVO.getPw()) == true ) {
			
			HttpSession session = req.getSession();
			session.setAttribute("id", registedLoginHomeVO.getId());
			session.setAttribute("sessionPass", "pass");
			
			return "succLogin";
			
		}
		
		return "failLogin";
	}
	
	
	@RequestMapping("/accLogout.go")
	public String accLoginHome(ModelMap map, HttpServletRequest req) throws Exception {
		
		HttpSession session = req.getSession();
		session.invalidate();
		
		return "forward:/loginHome.go";
	}
	
	
	
}
