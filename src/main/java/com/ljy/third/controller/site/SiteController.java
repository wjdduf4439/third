package com.ljy.third.controller.site;

import java.util.HashMap;
import java.util.List;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ljy.third.util.PageSet;
import com.ljy.third.service.site.SiteService;
import com.ljy.third.vo.site.SiteMenuVO;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;


//게시판 생성 기능을 담당하는 컨트롤러
@Controller
public class SiteController {
	
	@Value("${server.servlet.context-path}")
	private String rootContextPath;
	
	@Resource(name = "SiteService")
	SiteService siteService;
	
	
	public SiteController(SiteService msiteService) {
		
		this.siteService = msiteService;
		
	}

	@RequestMapping(value = "/site/site{processMark:Admin|Write}.go")
	public String siteAdmin(HttpServletRequest request, ModelMap map, @ModelAttribute("searchVO")SiteMenuVO siteMenuVO, @PathVariable("processMark")String processMark ) throws Exception {
		
		String viewMessage = "";
		if( "Admin".equals(processMark) ) {
			
			int countList = siteService.selectSiteMenuCnt(siteMenuVO);
			
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
	
	@ResponseBody
	@RequestMapping(value = "/site/site{processMark:Insert|Update}.go", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	public HashMap<String, Object> siteAdminProcess(HttpServletRequest request, ModelMap map, @RequestBody HashMap<String, Object> stringJson, @PathVariable("processMark")String processMark ) throws Exception {

		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		if( !"true".equals(request.getHeader("AJAX"))	) {
			resultMap.put("message",  "ajax 요청 아님!" ); return resultMap;
		}
		
		if( "Insert".equals(processMark) ) {
			
			int countRecord = siteService.selectSiteMenuCnt(stringJson);
			/*
			System.out.println("placerow :" + siteMenuVO.getPlaceRow());
			System.out.println("placewidth :" + siteMenuVO.getPlaceWidth());
			*/
			if(countRecord != 0) {
				
				SiteMenuVO resultVO = siteService.selectSiteMenuRecent();
				//System.out.println("recenttitle :" + resultVO.getTitle() );
//				if(!resultVO.getTitle().equals(siteMenuVO.getTitle())) {
				if(!resultVO.getTitle().equals(stringJson.get("title"))) {
				
					siteService.insertSiteMenu(stringJson);
					this.siteMakeTable(stringJson);
					
				}
				
			}else {
				
				siteService.insertSiteMenu(stringJson);
				this.siteMakeTable(stringJson);
				
			}
			
		}else if( "Update".equals(processMark) ) {

			siteService.updateSiteMenu(stringJson);
			this.siteMakeTable(stringJson);
			
		}

		resultMap.put("returnPage",  rootContextPath + "/site/siteAdmin.go" );
		
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/site/siteDelete.go", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	public HashMap<String, Object> siteAdminDelte(HttpServletRequest request, @RequestBody HashMap<String, Object> stringJson) throws Exception {
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		if( !"true".equals(request.getHeader("AJAX"))	) {
			resultMap.put("message",  "ajax 요청 아님!" ); return resultMap;
		}
		
		if( "N".equals( stringJson.get("del_chk") ) ) {
			siteService.disableSiteMenu(stringJson);
		} else {
			siteService.deleteSiteMenu(stringJson);
		}

		resultMap.put("returnPage",  rootContextPath + "/site/siteAdmin.go" );
		
		return resultMap;
	}
	
	
	@ResponseBody	
	@RequestMapping(value = "/site/siteRestore.go", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	public HashMap<String, Object> siteAdminRestore(HttpServletRequest request, @RequestBody HashMap<String, Object> stringJson) throws Exception {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		if( !"true".equals(request.getHeader("AJAX"))	) {
			resultMap.put("message",  "ajax 요청 아님!" ); return resultMap;
		}
		
		siteService.restoreSiteMenu(stringJson);
		
		resultMap.put("returnPage",  rootContextPath + "/site/siteAdmin.go" );
		
		return resultMap;
	}	

	
		//placeName 필드를 재구성하기 위한 메소드
		public void siteMakeTable(HashMap<String, Object> stringJson) throws Exception{

			
			try {
				

				String[] fieldNumber = ((String) stringJson.get("placeRow")).split(",");

				stringJson.put( "fieldNumber", fieldNumber);
				
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}

			List<SiteMenuVO> fieldList = siteService.selectSiteField(stringJson);
			
			String placeName = "";
			
			for(int i=0; i<fieldList.size(); i++){
				
				placeName += fieldList.get(i).getColumn_Name();
				if(i != fieldList.size()-1){ placeName += ","; }
				
			}
			

			stringJson.put( "placeName", placeName);
			
			siteService.updateSiteField(stringJson);
			
			System.out.println("siteMakeTable 메소드 종료");
		}
	
}
