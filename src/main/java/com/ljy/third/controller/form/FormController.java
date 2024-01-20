package com.ljy.third.controller.form;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ljy.third.service.form.FormService;
import com.ljy.third.util.PageSet;
import com.ljy.third.vo.form.FormMenuVO;
import com.ljy.third.vo.site.SiteMenuVO;

//항목 생성 기능을 담당하는 컨트롤러
@Controller
public class FormController {
	
	@Value("${server.servlet.context-path}")
	private String rootContextPath;

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
	
	@ResponseBody	
	@RequestMapping(value = "/form/form{processMark:Insert|Update}.go", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	public HashMap<String, Object> formAdminInsert(HttpServletRequest request, @RequestBody HashMap<String, Object> stringJson, @PathVariable("processMark")String processMark ) throws Exception {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		if( !"true".equals(request.getHeader("AJAX"))	) {
			resultMap.put("message",  "ajax 요청 아님!" ); return resultMap;
		}
		
		if( "Insert".equals(processMark) ) {
			formService.insertFormMenu(stringJson);
		}else if( "Update".equals(processMark) ) {
			formService.updateFormMenu(stringJson);
		}
		
		resultMap.put("returnPage",  rootContextPath + "/form/formAdmin.go" );
		return resultMap;
	}

	@ResponseBody	
	@RequestMapping(value = "/form/formDelete.go", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	public HashMap<String, Object> formAdminDelte(HttpServletRequest request, @RequestBody HashMap<String, Object> stringJson) throws Exception {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		if( !"true".equals(request.getHeader("AJAX"))	) {
			resultMap.put("message",  "ajax 요청 아님!" ); return resultMap;
		}
		
		if( "N".equals( stringJson.get("del_chk") ) ) {
			formService.disableFormMenu(stringJson);
		} else {
			formService.deleteFormMenu(stringJson);
		}
		
		resultMap.put("returnPage",  rootContextPath + "/form/formAdmin.go" );
		return resultMap;
	}
	
	
	@ResponseBody	
	@RequestMapping(value = "/form/formRestore.go", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	public HashMap<String, Object> siteAdminRestore(HttpServletRequest request, @RequestBody HashMap<String, Object> stringJson) throws Exception {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		if( !"true".equals(request.getHeader("AJAX"))	) {
			resultMap.put("message",  "ajax 요청 아님!" ); return resultMap;
		}
		
		formService.restoreFormMenu(stringJson);
		
		resultMap.put("returnPage",  rootContextPath + "/form/formAdmin.go" );
		return resultMap;
	}	
	
	
	
}
