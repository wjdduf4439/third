package com.ljy.third.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ljy.third.service.FileService;
import com.ljy.third.util.PageSet;
import com.ljy.third.service.TemplateOneService;
import com.ljy.third.vo.FileVO;
import com.ljy.third.vo.TemplateInfoVO;
import com.ljy.third.vo.TemplateOneVO;


//템플릿 게시판의 동작을 담당하는 컨트롤러, 현재 모양도 못잡음
@Controller
public class TemplateOneController {	

	@Autowired
	ServletContext c;
	
	@Resource(name ="TemplateOneService")
	private TemplateOneService templateOneService; //�ش� ���� ���� ����
	
	@Resource(name = "FileService")
	private FileService fileService;
	
	@RequestMapping("/template/templateOneList.go")
	public String TemplateOneList(@ModelAttribute("searchVO") TemplateOneVO templateOneVO, ModelMap map, HttpServletRequest req) throws Exception {
		
		TemplateInfoVO templateInfoVO =  (TemplateInfoVO) req.getAttribute("templateInfoVO");
		
		templateOneVO.setSiteTitle(templateInfoVO.getTitle());

		int countList = templateOneService.selectTableRecordListCount(templateInfoVO);// ������ �Խ����� �Խù��� ���� ���� �� �÷� ���ϱ�
		
		PageSet paging = new PageSet(templateOneVO.getPageIndex(), countList, templateOneVO.getRecordCountPerPage());
		templateOneVO = (TemplateOneVO) paging.recordSet(templateOneVO);// ���ڵ� ��ġ �Ϸ� �޼ҵ�
		
		List<TemplateInfoVO> fieldList = templateOneService.selectTableFieldList(templateInfoVO);
		List<TemplateOneVO> resultList = templateOneService.selectTableRecordList(templateInfoVO);
		
		//System.out.println("ù��° �ڵ�" + resultList.get(0).getCode());
		//System.out.println("ù��° ����" + resultList.get(0).getTitle());
		//System.out.println("ù��° ����" + resultList.get(0).getContext());
		//System.out.println("ù��° ���Ϲ�ȣ" + resultList.get(0).getAtchFileId());
		
		
		map.addAttribute("fieldWidth", templateInfoVO.getFieldWidth());
		map.addAttribute("fieldList", fieldList);
		map.addAttribute("messengerList", resultList);
		map.addAttribute("countList", countList);
		map.addAttribute("paging", paging);

		return "templateZero/templateOneMenu";
	}
	
	
	@RequestMapping("/template/templateOneWrite.go")
	public String TemplateOneWrite(@ModelAttribute("searchVO") TemplateOneVO templateOneVO ,ModelMap map, String mcode, String msiteCode, HttpServletRequest req) throws Exception {
		

		//templateOneVO.setCode(mcode);
		//templateOneVO.setSiteCode(msiteCode);
		//System.out.println("��code�� " + templateOneVO.getCode());
		//System.out.println("��sitecode�� " + templateOneVO.getSiteCode());
		
		if(templateOneVO.getCode() == null) { templateOneVO.setCode("00000000000000000000");}
		if(templateOneVO.getSiteCode() == null) {templateOneVO.setCode(mcode); templateOneVO.setSiteCode(msiteCode);}
		
		/*System.out.println("�����code�� " + templateOneVO.getCode());
		System.out.println("sitecode�� " + templateOneVO.getSiteCode());
		System.out.println("msitecode�� "+ msiteCode);*/
		
		TemplateOneVO writeVO = templateOneService.selectTableRecordOne(templateOneVO);
		
		//System.out.println("���code��" + writeVO.getCode());
		//if(templateOneVO.getSiteCode() != null) {System.out.println("������ ������" + writeVO.getContext());}
		
		map.addAttribute("writeVO", writeVO); // �޽����� ������� writeList�� ����
		
		//map.addAttribute("context", templateOneVO.getContext());
		
		return "templateZero/templateOneWrite";
	}
	
	@RequestMapping("/template/templateOneInsert.go")
	public String TemplateOneInsert(final MultipartHttpServletRequest multiRequest, @ModelAttribute("searchVO") TemplateOneVO templateOneVO , ModelMap map, HttpServletRequest req) throws Exception {
		
		//System.out.println("insert ���ӵ�");
		//System.out.println("��ȣ���� : "+ refreshVO.getContext() );
		//System.out.println("������ġ : " + c.getRealPath("/image"));
		
		int countRecord = templateOneService.selectTableRecordListCount(templateOneVO);
		
		TemplateOneVO refreshVO = new TemplateOneVO();
		
		if(countRecord != 0) {
			
			refreshVO = templateOneService.selectTableRecordRecent(templateOneVO);
			if( !templateOneVO.getContext().equals( refreshVO.getContext() ) ) { templateOneService.insertTableRecord(templateOneVO, multiRequest, req); } 
			//ù �۸� �ƴ϶�� rvo�� �ش� �Խñ��� ������ ��, ���ΰ�ħ�� ���� ���ؼ���
			
		} else { templateOneService.insertTableRecord(templateOneVO, multiRequest, req); }
			//ù���̶�� �׳� ����
		
		
		
		
		
		return "forward:/template/templateInfo.go";
	}
	
	@RequestMapping("/template/templateOneUpdate.go")
	public String TemplateOneUpdate(final MultipartHttpServletRequest multiRequest ,@ModelAttribute("searchVO") TemplateOneVO templateOneVO  ,ModelMap map, HttpServletRequest req) throws Exception {
		
		//System.out.println("update ���ӵ�");
		
		System.out.println("delValue : " + templateOneVO.getDelValue() );
		
		String[] mDelArray = templateOneVO.getDelValue().split(","); templateOneVO.setDelArray(mDelArray); // delarray�� �迭�� ����, mybatis�� foreach ���� Ȱ��
		
		templateOneService.updateTableRecord(templateOneVO, multiRequest, req);
		
		return "forward:/template/templateInfo.go";
	}
	
	@RequestMapping("/template/templateOneDelete.go")
	public String TemplateOneDelete(@ModelAttribute("searchVO") TemplateOneVO templateOneVO  ,ModelMap map) throws Exception {
		
		//System.out.println("�����ڵ� : " + templateOneVO.getCode());
		
		
		TemplateOneVO deleteVO = templateOneService.selectTableRecordOne(templateOneVO);
		
		try {
			
			templateOneVO.setAtchFileId(deleteVO.getAtchFileId());
			System.out.println("��Ʈ�ѷ� ������ fid : " + templateOneVO.getAtchFileId());
		
		}catch(Exception e) {
			
			System.out.println("���� ����");
		}
		
		
		templateOneService.deleteTableRecord(templateOneVO);
		
		return "forward:/template/templateInfo.go";
	}
	
	
	@RequestMapping(value = "/template/templateOneAjaxReturn.go", produces = "application/json; charset=utf8")//ajax utf-8 ó��
	public @ResponseBody Map<String, Object> TemplateOneAjaxReturn(@ModelAttribute("searchVO") TemplateOneVO templateOneVO, HttpServletRequest request, String code, String siteCode) throws Exception {
		
		templateOneVO.setSiteCode(siteCode);
		
		TemplateOneVO resultVO = templateOneService.selectTableRecordOne(templateOneVO);
		int fileCount = fileService.selectAtchFileCount(resultVO.getAtchFileId());
		
		List<FileVO> resultFileVO = fileService.selectFileMenuList(resultVO.getAtchFileId());
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
        
		resultMap.put("context", resultVO.getContext());
		resultMap.put("atchFileId", resultVO.getAtchFileId());
		resultMap.put("fileCount", Integer.toString(fileCount));
		
		List<String> fcodeList = new ArrayList<String>();
		List<String> fnameList = new ArrayList<String>();
		
		for(int i = 0; i < resultFileVO.size(); i++) { fcodeList.add(resultFileVO.get(i).getCode()); fnameList.add(resultFileVO.get(i).getFname());  }
		
		resultMap.put("fcode",fcodeList );
		resultMap.put("fname",fnameList );
        
	    return resultMap;
		
	}
	
	
}
