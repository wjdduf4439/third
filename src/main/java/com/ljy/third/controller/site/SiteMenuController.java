package com.ljy.third.controller.site;

import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ljy.third.util.PageSet;
import com.ljy.third.service.site.SiteService;
import com.ljy.third.vo.SysCodeVO;
import com.ljy.third.vo.site.SiteMenuVO;

import jakarta.annotation.Resource;


//게시판 생성 기능을 담당하는 컨트롤러
@Controller
public class SiteMenuController {

	@Resource(name = "SiteService")
	SiteService siteService;
	
	@RequestMapping(value = "/siteMenu/menuAdmin.go")
	public String menuAdminList(ModelMap map, @ModelAttribute("searchVO")SiteMenuVO siteMenuVO) throws Exception {
		
		int countList = siteService.selectSiteMenuCnt(siteMenuVO);// ������ �Խ����� �Խù��� ���� ���� �� �÷� ���ϱ�
		
		PageSet paging = new PageSet(siteMenuVO.getPageIndex(), countList, siteMenuVO.getRecordCountPerPage());
		siteMenuVO = (SiteMenuVO) paging.recordSet(siteMenuVO);// ���ڵ� ��ġ �Ϸ� �޼ҵ�
		
		List<SiteMenuVO> resultList = siteService.selectSiteMenuList(siteMenuVO);
		
		map.addAttribute("resultList", resultList);
		map.addAttribute("countList", countList);
		map.addAttribute("paging", paging);
		
		return "site/siteMenu";
	}
	
}
