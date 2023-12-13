package com.ljy.third.controller.site;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ljy.third.util.PageSet;
import com.ljy.third.service.site.SiteService;
import com.ljy.third.vo.TemplateZeroVO;
import com.ljy.third.vo.site.SiteMenuVO;


//게시판 생성 기능을 담당하는 컨트롤러
@Controller
public class SiteController {

	@Resource(name = "SiteService")
	SiteService siteService;

	@RequestMapping(value = "/site/site{processMark:Admin|Write}.go")
	public String siteAdmin(ModelMap map, @ModelAttribute("searchVO")SiteMenuVO siteMenuVO, @PathVariable("processMark")String processMark ) throws Exception {
		
		String viewMessage = "";
		if( "Admin".equals(processMark) ) {
			
			int countList = siteService.selectSiteMenuCnt(siteMenuVO);// ������ �Խ����� �Խù��� ���� ���� �� �÷� ���ϱ�
			
			PageSet paging = new PageSet(siteMenuVO.getPageIndex(), countList, siteMenuVO.getRecordCountPerPage());
			siteMenuVO = (SiteMenuVO) paging.recordSet(siteMenuVO);// ���ڵ� ��ġ �Ϸ� �޼ҵ�
			
			List<SiteMenuVO> resultList = siteService.selectSiteMenuList(siteMenuVO);
			
			map.addAttribute("resultList", resultList);
			map.addAttribute("countList", countList);
			map.addAttribute("paging", paging);
			
			viewMessage = "site/siteMenu";
			
		}else if( "Write".equals(processMark) ) {
			
			if(!siteMenuVO.getSiteCode().equals("")) {

				SiteMenuVO resultMenuVO = siteService.selectSiteMenuOne(siteMenuVO);

				if(!resultMenuVO.getSiteCode().equals("")) { resultMenuVO.setPlaceRowArray(resultMenuVO.getPlaceRow().split(",")); }
				
				System.out.println("siteAdminWrite templatetype : " + resultMenuVO.getTemplateType());
				
			}

			//System.out.println("placerow : " + resultMenuVO.getPlaceRow());
			//System.out.println("placeWidth : " + resultMenuVO.getPlaceWidth());
			//System.out.println("siteAdminWrite templatetype : " + resultMenuVO.getTemplateType());
			
			map.addAttribute("formList", siteService.selectSiteMenuFormList());
			map.addAttribute("resultList", siteService.selectSiteMenuOne(siteMenuVO));
			
			viewMessage = "site/siteWrite";
			
		}
		
		return viewMessage;
	}
	
	//@RequestMapping(value = "/site/siteInsert.go")
	@RequestMapping(value = "/site/site{processMark:Insert|Update}.go")
	public String siteAdminProcess(ModelMap map, @ModelAttribute("searchVO")SiteMenuVO siteMenuVO, @PathVariable("processMark")String processMark ) throws Exception {
		
		String viewMessage = "redirect:/site/siteAdmin.go";
		if( "Insert".equals(processMark) ) {
			
			int countRecord = siteService.selectSiteMenuCnt(siteMenuVO);
			
			System.out.println("placerow :" + siteMenuVO.getPlaceRow());
			System.out.println("placewidth :" + siteMenuVO.getPlaceWidth());
			
			if(countRecord != 0) {
				
				SiteMenuVO resultVO = siteService.selectSiteMenuRecent();
				//System.out.println("recenttitle :" + resultVO.getTitle() );
				if(!resultVO.getTitle().equals(siteMenuVO.getTitle())) {
				
					siteService.insertSiteMenu(siteMenuVO);
					this.siteMakeTable(siteMenuVO);
					
				}
				
			}else {
				
				siteService.insertSiteMenu(siteMenuVO);
				this.siteMakeTable(siteMenuVO);
				
			}
			
		}else if( "Update".equals(processMark) ) {

			siteService.updateSiteMenu(siteMenuVO);
			this.siteMakeTable(siteMenuVO);
			
		}
		
		return viewMessage;
	}
	
	@RequestMapping(value = "/site/siteDelete.go")
	public String siteAdminDelte(ModelMap map, @ModelAttribute("searchVO")SiteMenuVO siteMenuVO) throws Exception {
		
		//siteService.deleteSiteMenu(siteMenuVO);
		
		if( "N".equals( siteMenuVO.getDel_chk() ) ) {
			
			System.out.println("비활성화 진행");
			siteService.disableSiteMenu(siteMenuVO);
			
		} else {
			
			System.out.println("삭제 진행");
			siteService.deleteSiteMenu(siteMenuVO);
			
		}
		
		return "redirect:/site/siteAdmin.go";
	}
	
	@RequestMapping("/site/siteRestore.go")
	public String siteAdminRestore(ModelMap map, @ModelAttribute("searchVO")SiteMenuVO siteMenuVO) throws Exception {
		
		System.out.println("복구 진행");
		siteService.restoreSiteMenu(siteMenuVO);
		
		return "redirect:/site/siteAdmin.go";
	}	

	
	//��ȣ�� ǥ�õ� �迭 �׸���� �Ӽ������� ġȯ�� �� �ְ� �ϴ� �޼���
		public void siteMakeTable(SiteMenuVO siteMenuVO) throws Exception{
			
			try {
				
				String[] fieldNumber = siteMenuVO.getPlaceRow().split(",");//������ �ʵ���� �����ϴ� ����
				siteMenuVO.setFieldNumber(fieldNumber);
				
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
			List<SiteMenuVO> fieldList = siteService.selectSiteField(siteMenuVO);
			
			String placename = "";
			
			for(int i=0; i<fieldList.size(); i++){
				
				placename += fieldList.get(i).getColumn_Name();
				if(i != fieldList.size()-1){ placename += ","; }
				
			}
			
			siteMenuVO.setPlaceName(placename);
			
			siteService.updateSiteField(siteMenuVO);
			
		}
	
}
