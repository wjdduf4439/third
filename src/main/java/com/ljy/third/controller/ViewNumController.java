package com.ljy.third.controller;

import jakarta.annotation.Resource;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ljy.third.service.ViewNumService;

//조회수 동작을 담당하는 컨트롤러
@RestController
public class ViewNumController {

	@Resource(name = "ViewNumService")
	ViewNumService viewNumService;
	
	//ajax처리로 로그 테이블을 기록하는 용도로 쓰임
	//restController로 쓸것!
	@RequestMapping(value = "/ViewNumPlus.go")
	public String viewNumPlusUpdate(String siteCode, String code) throws Exception {
		
		ModelMap map = new ModelMap();
		
		map.addAttribute("siteCode", siteCode );
		map.addAttribute("code", code );
		
		viewNumService.viewNumPlusUpdate(map);
		
		return "success viewNumPlusUpdate";
	}
	
	
}
