package com.ljy.third.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ljy.third.service.FormService;
import com.ljy.third.util.PageSet;
import com.ljy.third.vo.FormMenuVO;
import com.ljy.third.vo.site.SiteMenuVO;

//항목 생성 기능을 담당하는 컨트롤러
@Controller
public class FormController {

	@Resource(name = "FormService")
	FormService formService;
	

	@RequestMapping("/form/form{processMark:Admin|Write}.go")
	public String formAdminList(@ModelAttribute("searchVO") FormMenuVO formMenuVO , ModelMap map, @PathVariable("processMark")String processMark ) throws Exception {
		String viewMessage = "";
		if( "Admin".equals(processMark) ) {
			
			int countList = formService.selectFormMenuCnt(formMenuVO);// ������ �Խ����� �Խù��� ���� ���� �� �÷� ���ϱ�
			
			PageSet paging = new PageSet(formMenuVO.getPageIndex(), countList, formMenuVO.getRecordCountPerPage());
			formMenuVO = (FormMenuVO) paging.recordSet(formMenuVO);// ���ڵ� ��ġ �Ϸ� �޼ҵ�
			
			List<FormMenuVO> resultList = formService.selectFormMenuList(formMenuVO);
			
			map.addAttribute("resultList", resultList);
			map.addAttribute("countList", countList);
			map.addAttribute("paging", paging);
			viewMessage = "form/formMenu";
		}else if( "Write".equals(processMark) ) {
			
			List<SiteMenuVO> siteList = formService.selectFormMenuSiteList(formMenuVO);
			
			map.addAttribute("siteList", siteList);
			map.addAttribute("resultList", formService.selectFormMenuOne(formMenuVO));
			viewMessage = "form/formWrite";
		}
		
		return viewMessage;
		
	}
	
	@RequestMapping("/form/form{processMark:Insert|Update}.go")
	public String formAdminInsert(ModelMap map, @ModelAttribute("searchVO")FormMenuVO formMenuVO, @PathVariable("processMark")String processMark ) throws Exception {
		
		if( "Insert".equals(processMark) ) {
			formService.insertFormMenu(formMenuVO);
		}else if( "Update".equals(processMark) ) {
			formService.updateFormMenu(formMenuVO);
		}
		
		return "redirect:/form/formAdmin.go";
	}
	
	@RequestMapping(value = "/form/formDelete.go")
	public String formAdminDelte(ModelMap map, @ModelAttribute("searchVO")FormMenuVO formMenuVO) throws Exception {
		
		formService.deleteFormMenu(formMenuVO);
		
		return "redirect:/form/formAdmin.go";
	}
	
	
	
}
