package com.ljy.third.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ljy.third.service.FormService;
import com.ljy.third.vo.FormMenuVO;
import com.ljy.third.vo.SiteMenuVO;

@Controller
public class incController {
	
	@Resource(name = "FormService")
	private FormService formService;
	
	@RequestMapping(value = "/AdminHeader.go")
	public String header(ModelMap map, FormMenuVO formMenuVO) throws Exception {
		
		
		List<FormMenuVO> formList = formService.selectFormMenuList(formMenuVO);
		//System.out.println("form의 사이즈 갯수 : " + formList.size() );
		
		
		//FORM_TABLE의 정보 주입
		map.addAttribute("formList", formList);
		
		List<SiteMenuVO> tempList = new ArrayList<SiteMenuVO>();
		List<SiteMenuVO> resultList  = new ArrayList<SiteMenuVO>();
		
		int i = 0;
		int i2 = 0;
		
		//SITECONTROLLER 호출
		for(i = 0; i<  formList.size(); i++) {
			
			formList.get(i).setStartIndex(i*i2);
			
			resultList =  formService.selectFormMenuSiteList(formList.get(i));//해당 항목 안의 게시판 이름, 코드를 가져옴
			
			if(resultList.size() == 0) {
				
				tempList.add(new SiteMenuVO()); //결과 쿼리가 하나도 없으면 null에 해당하는 object를 주입
				
			}else {
				
				for(i2 = 0; i2 < resultList.size(); i2++) {
					
					tempList.add(resultList.get(i2));
					
				}
				
			}
		
			if(i2 == 0) { i2++; } //게시판이 없는 항목은 1로 고쳐주어서 index가 자연스럽게 동작하도록 하기
			
			if(i == 0 ) {
				
				if(i2 == 0) { formList.get(i).setEndIndex(i2); }
				
				else { formList.get(i).setEndIndex(i2 - 1); }
				
			} else {
				formList.get(i).setEndIndex(i*i2 + formList.get(i).getStartIndex()); //처음 계산하는 리스트가 아닐 바에야 마지막 리스트에 예외를 줄 필요는 없음
			}
			
		}
		
		//SITE_TABLE의 정보를 MAP에 주입
		map.addAttribute("sites", tempList);
		
		return "inc/header";
	}
	
}
