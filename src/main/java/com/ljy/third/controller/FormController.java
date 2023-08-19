package com.ljy.third.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ljy.third.service.FormService;
import com.ljy.third.util.PageSet;
import com.ljy.third.vo.FormMenuVO;
import com.ljy.third.vo.SiteMenuVO;

//항목 생성 기능을 담당하는 컨트롤러
@Controller
public class FormController {

	@Resource(name = "FormService")
	FormService formService;
	
	@RequestMapping("/form/formAdmin.go")
	public String formAdminList(@ModelAttribute("searchVO") FormMenuVO formMenuVO 
					, ModelMap model
					) throws Exception {
		
		int countList = formService.selectFormMenuCnt(formMenuVO);// ������ �Խ����� �Խù��� ���� ���� �� �÷� ���ϱ�
		
		PageSet paging = new PageSet(formMenuVO.getPageIndex(), countList, formMenuVO.getRecordCountPerPage());
		formMenuVO = (FormMenuVO) paging.recordSet(formMenuVO);// ���ڵ� ��ġ �Ϸ� �޼ҵ�
		
		List<FormMenuVO> resultList = formService.selectFormMenuList(formMenuVO);
		
		model.addAttribute("resultList", resultList);
		model.addAttribute("countList", countList);
		model.addAttribute("paging", paging);
		
		return "form/formMenu";
		
	}
	
	@RequestMapping(value = "/form/formWrite.go")
	public String formAdminWrite(ModelMap map, @ModelAttribute("searchVO")FormMenuVO formMenuVO) throws Exception {
		
		List<SiteMenuVO> siteList = formService.selectFormMenuSiteList(formMenuVO);
		
		map.addAttribute("siteList", siteList);
		map.addAttribute("resultList", formService.selectFormMenuOne(formMenuVO));
		
		return "form/formWrite";
	}
	
	@RequestMapping(value = "/form/formInsert.go")
	public String formAdminInsert(ModelMap map, @ModelAttribute("searchVO")FormMenuVO formMenuVO) throws Exception {
		
		formService.insertFormMenu(formMenuVO);
		
		return "redirect:/form/formAdmin.go";
	}
	
	@RequestMapping(value = "/form/formUpdate.go")
	public String formAdminUpdate(ModelMap map, @ModelAttribute("searchVO")FormMenuVO formMenuVO) throws Exception {
		
		formService.updateFormMenu(formMenuVO);
		
		return "redirect:/form/formAdmin.go";
	}
	
	@RequestMapping(value = "/form/formDelete.go")
	public String formAdminDelte(ModelMap map, @ModelAttribute("searchVO")FormMenuVO formMenuVO) throws Exception {
		
		formService.deleteFormMenu(formMenuVO);
		
		return "redirect:/form/formAdmin.go";
	}
	
	
	
}
