package com.ljy.third.controller;

import javax.annotation.Resource;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ljy.third.service.logAdminService;
import com.ljy.third.vo.logAdminVO;

//로그 기입 동작을 담당하는 컨트롤러
@RestController
public class logInsertController {

	@Resource(name = "logAdminService")
	logAdminService logAdminService;
	
	//ajax처리로 로그 테이블을 기록하는 용도로 쓰임
	//restController로 쓸것!
	@RequestMapping(value = "/log/logInsert.go")
	public String logInsert(ModelMap map, logAdminVO logAdminVO) throws Exception {
		
		/*
		 * System.out.println("logInsert 메소드 접속"); System.out.println("ip : " +
		 * logAdminVO.getIp()); System.out.println("logReq : " +
		 * logAdminVO.getLogReq());
		 */
		//logCode는 service에서 결정		
		//logtime는 db에서 설정
		
		logAdminService.insertlogAdmin(logAdminVO);
		
		return "success";
	}
	
}
