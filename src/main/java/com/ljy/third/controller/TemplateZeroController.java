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


//일반 게시판의 동작을 담당하는 컨트롤러
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
		//현재 페이징을 선언하면 currpage가 1인 상태에서 전달받을 경우, 1에서 더 늘어나는 현상이 보임
		templateZeroVO = (TemplateZeroVO) paging.recordSet(templateZeroVO);// ���ڵ� ��ġ �Ϸ� �޼ҵ�
		
		List<TemplateInfoVO> fieldList = templateZeroService.selectTableFieldList(templateInfoVO);
		List<TemplateZeroVO> resultList = templateZeroService.selectTableRecordList(templateInfoVO);
		
		//System.out.println("ù��° �ʵ�� : " + fieldList.get(0).getColumn_Name());
		//System.out.println("ù��° �Խù� : " + resultList.get(0).getTitle());
		
		//���� ������ �����ͼ� list�� ��� �۾�
		List<TemplateZeroVO> noticeList = new ArrayList<TemplateZeroVO>(); //공지사항 리스트 가져오기
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

		//내용 첨부 이미지 불러오기
		//code 값이 없으면 내용첨부이미지 확인 절차를 생략하고, code 값이 있으면 내용첨부이미지 확인 절차 진행
		//System.out.println("들어올 때의 code값 : " + templateZeroVO.getCode() );
		if( !"".equals(templateZeroVO.getCode()) ) {

			TemplateZeroVO ECFresultVO = templateZeroService.selectTableECFRecordList(templateZeroVO);
			//이미첨부 기능이 없는 게시물이나 처음 화면에서 불러올때를 대비해 null대비함
			if(!"".equals(ECFresultVO.getEditorImage())) { resultVO.setEditorImage(ECFresultVO.getEditorImage()); }
			
		}
		
		map.addAttribute("resultList", resultVO);
		
		return "templateZero/templateZeroWrite";
	}
	
	@RequestMapping("/template/templateZeroInsert.go")
	public String TemplateZeroInsert(final MultipartHttpServletRequest multiRequest, @ModelAttribute("searchVO") TemplateZeroVO templateZeroVO  ,ModelMap map, HttpServletRequest req) throws Exception {

		
		int countRecord = templateZeroService.selectTableRecordListCount(templateZeroVO);
		
		TemplateZeroVO refreshVO = new TemplateZeroVO();
		refreshVO = templateZeroService.selectTableRecordRecent(templateZeroVO);
		
		//공지사항 표시용 sitemenuvo
		SiteMenuVO mSiteMenuVO = new SiteMenuVO();
		
		if(countRecord != 0) {
			
			refreshVO = templateZeroService.selectTableRecordRecent(templateZeroVO);
			
			if( !refreshVO.getContext().equals( templateZeroVO.getContext() ) ) { templateZeroService.insertTableRecord(templateZeroVO, multiRequest, req); }
			
			if(templateZeroVO.getNoticeSwitch().equals("1")) {
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
		
			//
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
