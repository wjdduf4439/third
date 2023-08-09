package com.ljy.third.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ljy.third.service.logAdminService;
import com.ljy.third.util.PageSet;
import com.ljy.third.vo.logAdminVO;

@Controller
public class logAdminController {

	@Resource(name = "logAdminService")
	logAdminService logAdminService;
	
	@RequestMapping(value = "/log/logAdmin.go")
	public String logAdmin(ModelMap map, @ModelAttribute("searchVO") logAdminVO logAdminVO) throws Exception {
		
		int countList = logAdminService.selectlogAdminCnt(logAdminVO);
		
		PageSet paging = new PageSet(logAdminVO.getPageIndex(), countList, logAdminVO.getRecordCountPerPage());
		logAdminVO = (logAdminVO) paging.recordSet(logAdminVO);
		
		System.out.println("searchWrd : " + logAdminVO.getSearchWrd());
		
		List<logAdminVO> resultList = logAdminService.selectlogAdminList(logAdminVO);
		
		map.addAttribute("resultList", resultList);
		map.addAttribute("countList", countList);
		map.addAttribute("paging", paging);
		
		return "/log/logAdmin";
	}
	
	
	//ajax처리로 로그 테이블을 기록하는 용도로 쓰임
	@RequestMapping(value = "/log/logInsert.go")
	public void logInsert(ModelMap map, logAdminVO logAdminVO) throws Exception {
		
		/*
		 * System.out.println("logInsert 메소드 접속"); System.out.println("ip : " +
		 * logAdminVO.getIp()); System.out.println("logReq : " +
		 * logAdminVO.getLogReq());
		 */
		//logCode는 service에서 결정		
		//logtime는 db에서 설정
		
		logAdminService.insertlogAdmin(logAdminVO);
		
		//return "success";
	}
	
}
