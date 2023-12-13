package com.ljy.third.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ljy.third.util.FileUploadValidateWork;
import com.ljy.third.util.PageSet;
import com.ljy.third.service.site.SiteService;
import com.ljy.third.service.TemplateZeroService;
import com.ljy.third.vo.TemplateInfoVO;
import com.ljy.third.vo.TemplateZeroVO;
import com.ljy.third.vo.site.SiteMenuVO;

//json 데이터 vo로 파싱하는법
//https://ynzu-dev.tistory.com/entry/JAVA-json-%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%A5%BC-VO%EB%A1%9C-%ED%8C%8C%EC%8B%B1-%EB%B3%80%ED%99%98%ED%95%98%EB%8A%94-%EB%B0%A9%EB%B2%95-json-to-java-class

//일반 게시판의 동작을 담당하는 컨트롤러
@Controller
public class TemplateZeroController {	

	@Resource(name ="TemplateZeroService")
	private TemplateZeroService templateZeroService; 
	
	@Resource(name ="SiteService")
	private SiteService siteService; 
	
	@RequestMapping("/template/templateZero{processMark:List|Write}.go")
	public String TemplateZeroList(@ModelAttribute("searchVO") TemplateZeroVO templateZeroVO  ,ModelMap map, HttpServletRequest req
			,@PathVariable("processMark")String processMark ) throws Exception {
		
		String viewMessage = "";
		
		if( "List".equals(processMark) ) {

			TemplateInfoVO templateInfoVO =  (TemplateInfoVO) req.getAttribute("templateInfoVO");
			templateZeroVO.setSiteTitle(templateInfoVO.getTitle());
			int countList = templateZeroService.selectTableRecordListCount(templateInfoVO);// ������ �Խ����� �Խù��� ���� ���� �� �÷� ���ϱ�
			
			PageSet paging = new PageSet(templateZeroVO.getPageIndex(), countList, templateZeroVO.getRecordCountPerPage());
			//현재 페이징을 선언하면 currpage가 1인 상태에서 전달받을 경우, 1에서 더 늘어나는 현상이 보임
			templateZeroVO = (TemplateZeroVO) paging.recordSet(templateZeroVO);// ���ڵ� ��ġ �Ϸ� �޼ҵ�
			
			List<TemplateInfoVO> fieldList = templateZeroService.selectTableFieldList(templateInfoVO);
			List<TemplateZeroVO> resultList = templateZeroService.selectTableRecordList(templateInfoVO);
			List<TemplateZeroVO> noticeList = new ArrayList<TemplateZeroVO>(); //공지사항 리스트 가져오기
			{
				noticeList = templateZeroService.selectTableNoticeList(templateZeroVO);
				if( noticeList.size() > 0 ) { map.addAttribute("noticeList", noticeList); } //noticelist
				else { map.addAttribute("noticeList", null); }
			}
			
			map.addAttribute("fieldWidth", templateInfoVO.getFieldWidth());
			map.addAttribute("fieldList", fieldList);
			map.addAttribute("resultList", resultList);
			map.addAttribute("countList", countList);
			map.addAttribute("paging", paging);
			viewMessage = "templateZero/templateZeroMenu";
			
		}else if( "Write".equals(processMark) ) {
			
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
			viewMessage = "templateZero/templateZeroWrite";
		}

		
		return viewMessage;
	}
	
	@RequestMapping("/template/templateZero{processMark:Insert|Update}.go")
	public String TemplateZeroProcess(final MultipartHttpServletRequest multiRequest , @ModelAttribute("searchVO") TemplateZeroVO templateZeroVO ,ModelMap map, HttpServletRequest req,
			@PathVariable("processMark")String processMark ) throws Exception {
		

		//공지사항 표시용 sitemenuvo
		SiteMenuVO mSiteMenuVO = new SiteMenuVO();
		mSiteMenuVO.setSiteCode(templateZeroVO.getSiteCode());
		SiteMenuVO resultSiteMenuVO = siteService.selectSiteMenuOne(mSiteMenuVO);

		//파일 검증 과정
		FileUploadValidateWork fValidate = null;
		boolean resultValidateWork = false;
		if(!templateZeroVO.getB_filename().get(0).getOriginalFilename().equals("")){
			fValidate = new FileUploadValidateWork(multiRequest, resultSiteMenuVO);
			resultValidateWork = fValidate.isResultValidateWork();
			
			//파일 검증 실패 시 
			if(resultValidateWork == false) { 
				System.out.println("파일 검증 실패 : " + fValidate.getMessage());
				map.addAttribute("System_errMessage", fValidate.getMessage());
				return "forward:/template/templateZeroList.go";
			}
		}
		
		if( "Insert".equals(processMark) ) {
			
			templateZeroService.insertTableRecord(templateZeroVO, multiRequest, req);
			
		}else if( "Update".equals(processMark) ) {
			
			templateZeroService.updateTableRecord(templateZeroVO, multiRequest, req);
			
		}
		return "forward:/template/templateZeroList.go";
	}
	
	@RequestMapping("/template/templateZeroDelete.go")
	public String TemplateZeroDelete(@ModelAttribute("searchVO") TemplateZeroVO templateZeroVO  ,ModelMap map) throws Exception {
		
		if( "N".equals( templateZeroVO.getDel_chk() ) ) {
			
			System.out.println("비활성화 진행");
			templateZeroService.disableTableRecord(templateZeroVO);
			
		} else {
			
			System.out.println("삭제 진행");
			templateZeroService.deleteTableRecord(templateZeroVO);
			
		}
		
		//return "forward:/template/templateInfo.go";
		return "forward:/template/templateZeroList.go";
	}
	
	@RequestMapping("/template/templateZeroRestore.go")
	public String TemplateZeroRestore(@ModelAttribute("searchVO") TemplateZeroVO templateZeroVO  ,ModelMap map) throws Exception {
		
		System.out.println("복구 진행");
		templateZeroService.restoreTableRecord(templateZeroVO);
		
		//return "forward:/template/templateInfo.go";
		return "forward:/template/templateZeroList.go";
	}
	
}
