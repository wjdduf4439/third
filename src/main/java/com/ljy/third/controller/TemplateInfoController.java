package com.ljy.third.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ljy.third.service.TemplateInfoService;
import com.ljy.third.vo.TemplateInfoVO;
import com.ljy.third.vo.TemplateViewInfoVO;

@Controller
public class TemplateInfoController {

	@Resource(name ="TemplateInfoService")
	private TemplateInfoService templateInfoService; //�ش� ���� ���� ����
	
	
	@RequestMapping("/template/templateInfo.go")
	public String templeteInfo(@ModelAttribute("searchVO") TemplateInfoVO templateInfoVO ,ModelMap map) throws Exception {
		
		String[] fieldNumber;	//������ �ʵ��ȣ �����ϴ� ����
		String[] fieldName;		//������ �ʵ���� �����ϴ� ����
		String[] fieldWidth = {"50"};	//������ �ʵ�ʺ� �����ϴ� ����
		
		TemplateInfoVO tableName = templateInfoService.selectTableName(templateInfoVO);
		templateInfoVO.setSiteCode(tableName.getSiteCode());
		templateInfoVO.setTitle(tableName.getTitle());
		
		fieldNumber = tableName.getPlaceRow().toString().split(",");//������ �ʵ���� �����ϴ� ����
		templateInfoVO.setFieldNumber(fieldNumber);//�迭�� ��ȯ �� vo�� ����
		
		fieldName = tableName.getPlaceName().toString().split(",");//������ �ʵ���� �����ϴ� ����
		templateInfoVO.setFieldName(fieldName);//�迭�� ��ȯ �� vo�� ����
		
		try { //�� �Խ��� ������ �Խ����� width�ڷᰡ �ʿ� ������ ����ó�� ����
		
			fieldWidth = tableName.getPlaceWidth().toString().split(",");//������ �ʵ�ʺ� �����ϴ� ����
			
		}catch(NullPointerException e) {
			
		}
		
		templateInfoVO.setFieldWidth(fieldWidth);//�迭�� ��ȯ �� vo�� ����
		
		
		map.addAttribute("templateInfoVO", templateInfoVO);
		
		//System.out.println("templatetype : " + tableName.getTemplateType());
		System.out.println("info페이지 인덱스 : " + templateInfoVO.getPageIndex());
		
		if(tableName.getTemplateType().equals("0")){
			
			return "forward:/template/templateZeroList.go";
			
		}else if(tableName.getTemplateType().equals("1")){
			
			return "forward:/template/templateOneList.go";
			
		}  
		
		return "templateZero/templateZeroMenu";
	}
	
	
	@RequestMapping("/template/templeteViewInfo.go")
	public String templeteViewInfo(@ModelAttribute("searchVO") TemplateViewInfoVO templateViewInfoVO ,ModelMap map) throws Exception {
	
		String[] fieldNumber;	//������ �ʵ��ȣ �����ϴ� ����
		String[] fieldName;		//������ �ʵ���� �����ϴ� ����
		String[] fieldWidth = {"50"};	//������ �ʵ�ʺ� �����ϴ� ����
		
		TemplateViewInfoVO tableName = templateInfoService.selectTableName(templateViewInfoVO);
		templateViewInfoVO.setSiteCode(tableName.getSiteCode());
		templateViewInfoVO.setTitle(tableName.getTitle());
		
		fieldNumber = tableName.getPlaceRow().toString().split(",");//������ �ʵ���� �����ϴ� ����
		templateViewInfoVO.setFieldNumber(fieldNumber);//�迭�� ��ȯ �� vo�� ����
		
		fieldName = tableName.getPlaceName().toString().split(",");//������ �ʵ���� �����ϴ� ����
		templateViewInfoVO.setFieldName(fieldName);//�迭�� ��ȯ �� vo�� ����
		
		try { //�� �Խ��� ������ �Խ����� width�ڷᰡ �ʿ� ������ ����ó�� ����
			
			fieldWidth = tableName.getPlaceWidth().toString().split(",");//������ �ʵ�ʺ� �����ϴ� ����
			
		}catch(NullPointerException e) {
			
		}
		templateViewInfoVO.setFieldWidth(fieldWidth);//�迭�� ��ȯ �� vo�� ����
		map.addAttribute("templateViewInfoVO", templateViewInfoVO);
		
		if(tableName.getTemplateType().equals("0")){
			
			return "forward:/template/templateZeroViewList.go";
			
		}
	
		return "userView/templateViewMenu";
	
	}
	
}
