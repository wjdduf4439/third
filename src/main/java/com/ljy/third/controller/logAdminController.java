package com.ljy.third.controller;

import java.util.List;

import jakarta.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ljy.third.service.logAdminService;
import com.ljy.third.util.PageSet;
import com.ljy.third.vo.logAdminVO;

//로그 기록과 관련된 동작을 기록한 컨트롤러
@Controller
public class logAdminController {

	@Resource(name = "logAdminService")
	logAdminService logAdminService;
	
	@RequestMapping(value = "/log/logAdmin.go")
	public String logAdmin(ModelMap map, @ModelAttribute("searchVO") logAdminVO logAdminVO) throws Exception {
		
		int countList = logAdminService.selectlogAdminCnt(logAdminVO);
		
		PageSet paging = new PageSet(logAdminVO.getPageIndex(), countList, logAdminVO.getRecordCountPerPage());
		logAdminVO = (logAdminVO) paging.recordSet(logAdminVO);
		
		List<logAdminVO> resultList = logAdminService.selectlogAdminList(logAdminVO);
		
		map.addAttribute("resultList", resultList);
		map.addAttribute("countList", countList);
		map.addAttribute("paging", paging);
		
		return "/log/logAdmin";
	}
	
}
