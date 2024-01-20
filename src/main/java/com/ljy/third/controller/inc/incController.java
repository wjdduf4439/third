package com.ljy.third.controller.inc;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ljy.third.service.form.FormService;
import com.ljy.third.service.site.SiteService;
import com.ljy.third.vo.form.FormMenuVO;
import com.ljy.third.vo.site.SiteMenuVO;


//header와 관련된 동작을 기록한 controller
@Controller
public class incController {
	
	@Resource(name = "FormService")
	private FormService formService;
	
	@Resource(name = "SiteService")
	SiteService siteService;
	
	@RequestMapping(value = "/AdminLoginFormBox.go")
	public String loginFormBox(ModelMap map) throws Exception {
		
		return "inc/loginFormBox";
	}
	
	@RequestMapping(value = "/AdminHeader.go")
	public String header(ModelMap map, FormMenuVO formMenuVO) throws Exception {
		
		//header_call에 true를 넣어서 header 부분의 form의 노출을 del_chk로 제어할수 있게 한다
		formMenuVO.setHeader_call("true");
		List<FormMenuVO> formList = formService.selectFormMenuList(formMenuVO);
		System.out.println("form의 사이즈 갯수 : " + formList.size() );
		
		SiteMenuVO msiteMenuVO = new SiteMenuVO();
		msiteMenuVO.setHeader_call("true");
		
		//FORM_TABLE의 정보 주입
		map.addAttribute("formList", formList);
		
		List<SiteMenuVO> tempList = new ArrayList<SiteMenuVO>();
		List<SiteMenuVO> resultList  = new ArrayList<SiteMenuVO>();
		
		int i = 0;
		int i2 = 0;
		
		//SITECONTROLLER 호출
		for(i = 0; i<  formList.size(); i++) {
			/*
			각 form 안에 있는 site의 번호를 매겨서 시작점을 재지정함
			각 form에 게시판이 없을 경우 i2++를 통해서 각 form 의 index를 1씩 넘겨서 다음 form에서 게시판을 조회할 수 있게 만든다
			*/
			formList.get(i).setStartIndex(i*i2);
			
			msiteMenuVO.setFormCode(formList.get(i).getFormCode());
			System.out.println("form 헤더작업 formcode : " + formList.get(i).getFormCode() );
			resultList =  siteService.selectSiteMenuList(msiteMenuVO);//해당 form 안의 게시판 이름, 코드를 가져옴
			System.out.println("form 헤더작업 count : " + resultList.size() );
			
			if(resultList.size() == 0) {
				
				tempList.add(new SiteMenuVO()); //결과 쿼리가 하나도 없으면 null에 해당하는 object를 주입
				
			}else {
				
				for(i2 = 0; i2 < resultList.size(); i2++) {
					
					tempList.add(resultList.get(i2));
					
				}
				
			}
		
			if(i2 == 0) { i2++; } //각 form에 게시판이 없으면 index에 1을 더해 주어서 다음 form의 시작점에서 index가 자연스럽게 동작하도록 하기
			
			if(i == 0 ) {
				/*
				첫번째 form에 게시판이 없으면 index가 0으로 시작하게 되니까 1로 자연스럽게 수정되도록 유도하기
				첫번째 form에 게시판이 있으면 0에서 시작되는 index의 보정을 위해 i2 - 1을 해주기
				*/
				if(i2 == 0) { formList.get(i).setEndIndex(i2); } 
				
				else { formList.get(i).setEndIndex(i2 - 1); }
				
			} else {
				formList.get(i).setEndIndex(i*i2 + formList.get(i).getStartIndex()); //처음 계산하는 리스트가 아닐 바에야 마지막 리스트에 예외를 줄 필요는 없음
			}
			
		}
		
		//SITE_TABLE의 정보를 MAP에 주입
		map.addAttribute("sites", tempList);
		
		return "inc/header";
		//return "inc/json_header";
	}
	
}
