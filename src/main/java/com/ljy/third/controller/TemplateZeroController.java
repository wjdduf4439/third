package com.ljy.third.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ljy.third.util.PageSet;
import com.ljy.third.service.SiteService;
import com.ljy.third.service.TemplateZeroService;
import com.ljy.third.vo.SiteMenuVO;
import com.ljy.third.vo.TemplateInfoVO;
import com.ljy.third.vo.TemplateZeroVO;

@Controller
public class TemplateZeroController {	

	@Resource(name ="TemplateZeroService")
	private TemplateZeroService templateZeroService; //�ش� ���� ���� ����
	
	@Resource(name ="SiteService")
	private SiteService siteService; //�ش� ���� ���� ����
	
	@RequestMapping("/template/templateZeroList.go")
	public String TemplateZeroList(@ModelAttribute("searchVO") TemplateZeroVO templateZeroVO  ,ModelMap map, HttpServletRequest req) throws Exception {
		
		TemplateInfoVO templateInfoVO =  (TemplateInfoVO) req.getAttribute("templateInfoVO");
		templateZeroVO.setSiteTitle(templateInfoVO.getTitle());

		int countList = templateZeroService.selectTableRecordListCount(templateInfoVO);// ������ �Խ����� �Խù��� ���� ���� �� �÷� ���ϱ�
		
		PageSet paging = new PageSet(templateZeroVO.getPageIndex(), countList, templateZeroVO.getRecordCountPerPage());
		templateZeroVO = (TemplateZeroVO) paging.recordSet(templateZeroVO);// ���ڵ� ��ġ �Ϸ� �޼ҵ�
		
		List<TemplateInfoVO> fieldList = templateZeroService.selectTableFieldList(templateInfoVO);
		List<TemplateZeroVO> resultList = templateZeroService.selectTableRecordList(templateInfoVO);
		
		//System.out.println("ù��° �ʵ�� : " + fieldList.get(0).getColumn_Name());
		//System.out.println("ù��° �Խù� : " + resultList.get(0).getTitle());
		
		//���� ������ �����ͼ� list�� ��� �۾�
		List<TemplateZeroVO> noticeList = new ArrayList<TemplateZeroVO>(); // ���� ������ ���� list
		{
			
			SiteMenuVO mSiteMenuVO = new SiteMenuVO();									//�ش� site�� ������ �����ͼ� �����Ű�� ���� vo
			mSiteMenuVO.setSiteCode(templateZeroVO.getSiteCode());						//���� ����Ǵ� �Խ����� �ڵ带 �����ͼ� �Է�
			SiteMenuVO resultSiteMenuVO = siteService.selectSiteMenuOne(mSiteMenuVO);	//�ش� site�� ������ ������ vo
			String[] nArray =  new String[1];											//site�� ������ �����ͼ� �ڵ常 ���� �迭 ����
			
			
			if( null != resultSiteMenuVO.getNoticeSwitch() && !resultSiteMenuVO.getNoticeSwitch().equals("") ) { nArray = resultSiteMenuVO.getNoticeSwitch().split(","); }
			
			//System.out.println(resultSiteMenuVO.getNoticeSwitch());
			//System.out.println("narray :" + nArray[0]);
			
			TemplateZeroVO noticeZeroVO = new TemplateZeroVO(); 						//���� �Խù��� ������ ������ vo
			noticeZeroVO.setSiteCode(templateZeroVO.getSiteCode());						//���� �Խù��� ����Ʈ �ڵ常 �����ͼ� ������
			
			for(int i = 0; i < nArray.length; i++) { 
				
				noticeZeroVO.setCode(nArray[i]);										//���� �Խù��� �Խù� �ڵ带 �����ͼ� ����
				noticeList.add(templateZeroService.selectTableRecordOne(noticeZeroVO));	//���� �Խù��� �Խù� ������ �����ͼ� ���� ����Ʈ�� ����
				
			}
			
			//System.out.println("noticecode : "+ noticeList.get(0).getCode());
			//System.out.println("nList :" + noticeList.get(0).getCode());
			
			if( null != noticeList.get(0) ) { map.addAttribute("noticeList", noticeList); } //noticelist�� �ƹ� vo�� ������ null�� ǥ�ð� ��
			else { map.addAttribute("noticeList", null); }
		}
		
		map.addAttribute("fieldWidth", templateInfoVO.getFieldWidth());
		map.addAttribute("fieldList", fieldList);
		map.addAttribute("resultList", resultList);
		map.addAttribute("countList", countList);
		map.addAttribute("paging", paging);

		return "templateZero/templateZeroMenu";
	}
	
	@RequestMapping("/template/templateZeroWrite.go")
	public String TemplateZeroWrite(@ModelAttribute("searchVO") TemplateZeroVO templateZeroVO  ,ModelMap map) throws Exception {
		

		TemplateZeroVO resultVO = templateZeroService.selectTableRecordOne(templateZeroVO);
		
		SiteMenuVO mSiteMenuVO = new SiteMenuVO();
		mSiteMenuVO.setSiteCode(templateZeroVO.getSiteCode());
		
		SiteMenuVO resultSiteMenuVO = siteService.selectSiteMenuOne(mSiteMenuVO); 
		String[] nArray =  new String[1];
		
		if( null != resultSiteMenuVO.getNoticeSwitch() && !resultSiteMenuVO.getNoticeSwitch().equals("") ) {
			
			nArray = resultSiteMenuVO.getNoticeSwitch().split(",");
			for(int i = 0; i < nArray.length; i++) { if( nArray[i].equals ( templateZeroVO.getCode() ) ){ resultVO.setNoticeSwitch("1"); } }
		
		}
		
		
		map.addAttribute("resultList", resultVO);
		
		return "templateZero/templateZeroWrite";
	}
	
	@RequestMapping("/template/templateZeroInsert.go")
	public String TemplateZeroInsert(final MultipartHttpServletRequest multiRequest, @ModelAttribute("searchVO") TemplateZeroVO templateZeroVO  ,ModelMap map, HttpServletRequest req) throws Exception {

		
		int countRecord = templateZeroService.selectTableRecordListCount(templateZeroVO);
		
		TemplateZeroVO refreshVO = new TemplateZeroVO();
		refreshVO = templateZeroService.selectTableRecordRecent(templateZeroVO);
		
		if(countRecord != 0) {
			System.out.println("���� ����" + templateZeroVO.getNoticeSwitch());
			
			refreshVO = templateZeroService.selectTableRecordRecent(templateZeroVO);
			
			if( !refreshVO.getContext().equals( templateZeroVO.getContext() ) ) { templateZeroService.insertTableRecord(templateZeroVO, multiRequest, req); } 
			//ù �۸� �ƴ϶�� rvo�� �ش� �Խñ��� ������ ��, ���ΰ�ħ�� ���� ���ؼ���
			
			if(templateZeroVO.getNoticeSwitch().equals("1")) {
				
				SiteMenuVO mSiteMenuVO = new SiteMenuVO();
				mSiteMenuVO.setSiteCode(templateZeroVO.getSiteCode());
				
				SiteMenuVO resultSiteMenuVO = siteService.selectSiteMenuOne(mSiteMenuVO);
				String reNoticeSwitch = "";
				
				if(null != resultSiteMenuVO.getNoticeSwitch() && !resultSiteMenuVO.getNoticeSwitch().equals("")) { templateZeroVO.setNoticeSwitch(resultSiteMenuVO.getNoticeSwitch() + "," + templateZeroVO.getCode()); }
				else { reNoticeSwitch = templateZeroVO.getNoticeSwitch(); }
				
				System.out.println("공지 전환 여부" + reNoticeSwitch);
				
				resultSiteMenuVO.setNoticeSwitch(templateZeroVO.getCode());
				siteService.updateSiteMenu(resultSiteMenuVO);
				
			}
			
		} else {
			
			templateZeroService.insertTableRecord(templateZeroVO, multiRequest, req);
		
		}
		
		
		return "forward:/template/templateInfo.go";
	}
	
	@RequestMapping("/template/templateZeroUpdate.go")
	public String TemplateZeroUpdate(final MultipartHttpServletRequest multiRequest , @ModelAttribute("searchVO") TemplateZeroVO templateZeroVO ,ModelMap map, HttpServletRequest req) throws Exception {
		
		
		templateZeroService.updateTableRecord(templateZeroVO, multiRequest, req);
		
		//System.out.println("notice : " + templateZeroVO.getNoticeSwitch());
		//System.out.println("siteCode : " + templateZeroVO.getSiteCode());
		

		SiteMenuVO mSiteMenuVO = new SiteMenuVO();
		mSiteMenuVO.setSiteCode(templateZeroVO.getSiteCode());
		
		SiteMenuVO resultSiteMenuVO = siteService.selectSiteMenuOne(mSiteMenuVO);
		
		if(templateZeroVO.getNoticeSwitch().equals("1")) {		//�޾Ƴ� notice�� ���������� �����ͼ� sitetable�� ������
		
			resultSiteMenuVO.setNoticeSwitch(templateZeroVO.getCode());
			siteService.updateSiteMenu(resultSiteMenuVO);
			
		}else {			//�޾Ƴ� notice�� ������ �ʾ������� �����ͼ� sitetable���� ������
			
			String[] nArray =  new String[1];
			String reNoticeSwitch = "";
			
			if(null != resultSiteMenuVO.getNoticeSwitch() && !resultSiteMenuVO.getNoticeSwitch().equals("")) { nArray = resultSiteMenuVO.getNoticeSwitch().split(","); }
			
			if(null != nArray[0]) {
				
				for(int i = 0; i < nArray.length; i++) { 
					
					if( !nArray[i].equals( templateZeroVO.getCode() ) ){ reNoticeSwitch += nArray[i]; if(i != nArray.length -1) { reNoticeSwitch += ","; } 	}
				
				}
				
			}
			
			
			resultSiteMenuVO.setNoticeSwitch(reNoticeSwitch);
			siteService.updateSiteMenu(resultSiteMenuVO);
			
			
		}
		
		return "forward:/template/templateInfo.go";
	}
	
	@RequestMapping("/template/templateZeroDelete.go")
	public String TemplateZeroDelete(@ModelAttribute("searchVO") TemplateZeroVO templateZeroVO  ,ModelMap map) throws Exception {
		
		templateZeroService.deleteTableRecord(templateZeroVO);
		
		return "forward:/template/templateInfo.go";
	}
	
}
