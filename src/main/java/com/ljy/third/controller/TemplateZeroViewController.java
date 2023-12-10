package com.ljy.third.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ljy.third.util.EnumLogs;
import com.ljy.third.util.PageSet;
import com.ljy.third.service.site.SiteService;
import com.ljy.third.service.TemplateZeroViewService;
import com.ljy.third.vo.TemplateViewInfoVO;
import com.ljy.third.vo.TemplateZeroViewVO;
import com.ljy.third.vo.site.SiteMenuVO;

//일반 게시판의 사용자 전용 뷰를 담당하는 컨트롤러.
@Controller
public class TemplateZeroViewController {	

	@Resource(name ="TemplateZeroViewService")
	private TemplateZeroViewService templateZeroViewService; 
	
	@Resource(name ="SiteService")
	private SiteService siteService; //�ش� ���� ���� ����
	
	@RequestMapping("/templateView/templateZeroViewList.go")
	public String TemplateZeroViewList(@ModelAttribute("searchVO") TemplateZeroViewVO templateZeroViewVO  ,ModelMap map, HttpServletRequest req) throws Exception {
		
		EnumLogs e = new EnumLogs(req);
		
		/*
		
		templateViewInfoVO = sitetable에서 가져온 게시판의 정보 
		TemplateZeroViewVO = 실제 뷰에서 표현할 정보를 담음 
		 
		 */
		
		TemplateViewInfoVO templateViewInfoVO =  (TemplateViewInfoVO) req.getAttribute("templateViewInfoVO");
		templateViewInfoVO.mergeSearchVO(templateZeroViewVO);
		System.out.println("TemplateZeroViewList 전달받은 게시판 제목 : " + templateViewInfoVO.getTitle());
		System.out.println("TemplateZeroViewList 전달받은 게시판 코드 : " + templateViewInfoVO.getSiteCode());
		System.out.println("TemplateZeroViewList 전달받은 검색항목 : " + templateViewInfoVO.getSearchCnd());
		System.out.println("TemplateZeroViewList 전달받은 검색어 : " + templateViewInfoVO.getSearchWrd());

		templateZeroViewVO.setSiteTitle(templateViewInfoVO.getTitle());

		int countList = templateZeroViewService.selectTableRecordListCount(templateViewInfoVO);// ������ �Խ����� �Խù��� ���� ���� �� �÷� ���ϱ�
		PageSet paging = new PageSet(templateZeroViewVO.getPageIndex(), countList, templateZeroViewVO.getRecordCountPerPage());
		//현재 페이징을 선언하면 currpage가 1인 상태에서 전달받을 경우, 1에서 더 늘어나는 현상이 보임
		templateZeroViewVO = (TemplateZeroViewVO) paging.recordSet(templateZeroViewVO);// ���ڵ� ��ġ �Ϸ� �޼ҵ�
		
		List<TemplateViewInfoVO> fieldList = templateZeroViewService.selectTableFieldList(templateViewInfoVO);
		List<TemplateZeroViewVO> resultList = templateZeroViewService.selectTableRecordList(templateViewInfoVO);
		//���� ������ �����ͼ� list�� ��� �۾�
		List<TemplateZeroViewVO> noticeList = new ArrayList<TemplateZeroViewVO>(); // ���� ������ ���� list
		{
			
			SiteMenuVO mSiteMenuVO = new SiteMenuVO();									//�ش� site�� ������ �����ͼ� �����Ű�� ���� vo
			mSiteMenuVO.setSiteCode(templateZeroViewVO.getSiteCode());						//���� ����Ǵ� �Խ����� �ڵ带 �����ͼ� �Է�
			SiteMenuVO resultSiteMenuVO = siteService.selectSiteMenuOne(mSiteMenuVO);	//�ش� site�� ������ ������ vo
			String[] nArray =  new String[1];											//site�� ������ �����ͼ� �ڵ常 ���� �迭 ����
			
			
			if( null != resultSiteMenuVO.getNoticeSwitch() && !resultSiteMenuVO.getNoticeSwitch().equals("") ) { nArray = resultSiteMenuVO.getNoticeSwitch().split(","); }
			
			//System.out.println(resultSiteMenuVO.getNoticeSwitch());
			//System.out.println("narray :" + nArray[0]);
			
			TemplateZeroViewVO noticeZeroVO = new TemplateZeroViewVO(); 						//���� �Խù��� ������ ������ vo
			noticeZeroVO.setSiteCode(templateZeroViewVO.getSiteCode());						//���� �Խù��� ����Ʈ �ڵ常 �����ͼ� ������
			
			for(int i = 0; i < nArray.length; i++) { 
				
				noticeZeroVO.setCode(nArray[i]);										//���� �Խù��� �Խù� �ڵ带 �����ͼ� ����
				noticeList.add(templateZeroViewService.selectTableRecordOne(noticeZeroVO));	//���� �Խù��� �Խù� ������ �����ͼ� ���� ����Ʈ�� ����
				
			}
			
			//System.out.println("noticecode : "+ noticeList.get(0).getCode());
			//System.out.println("nList :" + noticeList.get(0).getCode());
			
			if( null != noticeList.get(0) ) { map.addAttribute("noticeList", noticeList); } //noticelist�� �ƹ� vo�� ������ null�� ǥ�ð� ��
			else { map.addAttribute("noticeList", null); }
		}
		
		map.addAttribute("fieldWidth", templateViewInfoVO.getFieldWidth());
		map.addAttribute("fieldList", fieldList);
		map.addAttribute("resultList", resultList);
		map.addAttribute("countList", countList);
		map.addAttribute("paging", paging);

		return "userView/templateViewMenu";
	}
	
	@RequestMapping("/templateView/templateZeroViewWrite.go")
	public String TemplateZeroViewWrite(@ModelAttribute("searchVO") TemplateZeroViewVO templateZeroViewVO  ,ModelMap map) throws Exception {

		System.out.println("TemplateZeroViewList 전달받은 게시판 코드 : " + templateZeroViewVO.getSiteCode());

		TemplateZeroViewVO resultVO = templateZeroViewService.selectTableRecordOne(templateZeroViewVO);
		
		SiteMenuVO mSiteMenuVO = new SiteMenuVO();
		mSiteMenuVO.setSiteCode(templateZeroViewVO.getSiteCode());
		
		SiteMenuVO resultSiteMenuVO = siteService.selectSiteMenuOne(mSiteMenuVO); 
		String[] nArray =  new String[1];
		
		if( null != resultSiteMenuVO.getNoticeSwitch() && !resultSiteMenuVO.getNoticeSwitch().equals("") ) {
			
			nArray = resultSiteMenuVO.getNoticeSwitch().split(",");
			for(int i = 0; i < nArray.length; i++) { if( nArray[i].equals ( templateZeroViewVO.getCode() ) ){ resultVO.setNoticeSwitch("1"); } }
		
		}
		
		
		map.addAttribute("resultList", resultVO);
		
		return "userView/templateViewWrite";
	}
	
}