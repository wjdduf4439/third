package com.ljy.third.controller.templateZero;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ljy.third.util.FileUploadValidateWork;
import com.ljy.third.util.PageSet;
import com.ljy.third.service.site.SiteService;
import com.ljy.third.service.templateZero.TemplateZeroService;
import com.ljy.third.vo.TemplateInfoVO;
import com.ljy.third.vo.site.SiteMenuVO;
import com.ljy.third.vo.templateZero.TemplateZeroVO;

//json 데이터 vo로 파싱하는법
//https://ynzu-dev.tistory.com/entry/JAVA-json-%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%A5%BC-VO%EB%A1%9C-%ED%8C%8C%EC%8B%B1-%EB%B3%80%ED%99%98%ED%95%98%EB%8A%94-%EB%B0%A9%EB%B2%95-json-to-java-class

//일반 게시판의 동작을 담당하는 컨트롤러
@Controller
public class TemplateZeroController {	
	
	@Value("${server.servlet.context-path}")
	private String rootContextPath;

	@Resource(name ="TemplateZeroService")
	private TemplateZeroService templateZeroService; 
	
	@Resource(name ="SiteService")
	private SiteService siteService; 
	
	@RequestMapping("/template/templateZero{processMark:List|Write}.go")
	public String TemplateZeroList(@ModelAttribute("searchVO") TemplateZeroVO templateZeroVO  ,ModelMap map, HttpServletRequest req
			,@PathVariable("processMark")String processMark ) throws Exception {
		
		String viewMessage = "";
		
		/*
		 	
		 	전체적으로 list작업은 field 정보가 담긴 templateInfoVO를 서비스에 넣어 TemplateZeroVO의 값을 가져오는 것을 목표로 한다
		 	templateInfoVO -> db에서 테이블의 field 값을 가져옴
		 	templateZeroVO -> 페이지에서 form의 input 값을 가져옴, 페이징 처리에 사용																									
		 	
		 */
		if( "List".equals(processMark) ) {

			TemplateInfoVO templateInfoVO =  (TemplateInfoVO) req.getAttribute("templateInfoVO");
			templateZeroVO.setSiteTitle(templateInfoVO.getTitle());
			int countList = templateZeroService.selectTableRecordListCount(templateInfoVO);

			/*
			recordSet 작업 후, ZeroVO의 pageindex를 
			현재 페이징을 선언하면 currpage가 1인 상태에서 전달받을 경우, 1에서 더 늘어나는 현상이 보임
			*/
			PageSet paging = new PageSet(templateZeroVO.getPageIndex(), countList, templateZeroVO.getRecordCountPerPage());
			{
				templateZeroVO = (TemplateZeroVO) paging.recordSet(templateZeroVO);
				templateInfoVO.setPageIndex( templateZeroVO.getPageIndex()  );
				templateInfoVO.setRecordCountPerPage( templateZeroVO.getRecordCountPerPage()  );
				
			}
			
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
			String primaryCode = resultSiteMenuVO.getPrimaryCode();
			
			String[] nArray =  new String[1];
			
			//현재 resultSiteMenuVO
			map.addAttribute("primaryCode", primaryCode);
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
	
	@ResponseBody
	@RequestMapping(value = "/template/templateZero{processMark:Insert|Update}.go", method = RequestMethod.POST, produces = "application/json; charset=utf8")
//	@RequestMapping("/template/templateZero{processMark:Insert|Update}.go")
	public HashMap<String, Object> TemplateZeroProcess(HttpServletRequest request, @RequestBody HashMap<String, String> stringJson, 
			@PathVariable("processMark")String processMark ) throws Exception {

		System.out.println("templateZero dto 값 결과 : " + stringJson.toString());
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		if( !"true".equals(request.getHeader("AJAX"))	) {
			resultMap.put("message",  "ajax 요청 아님!" ); return resultMap;
		}

		//공지사항 표시용 sitemenuvo
		SiteMenuVO mSiteMenuVO = new SiteMenuVO();
		//mSiteMenuVO.setSiteCode(templateZeroVO.getSiteCode());
		mSiteMenuVO.setSiteCode(stringJson.get("siteCode"));
		SiteMenuVO resultSiteMenuVO = siteService.selectSiteMenuOne(mSiteMenuVO);

		//파일 검증 과정
		FileUploadValidateWork fValidate = null;
		boolean resultValidateWork = false;
		/*
		if(!templateZeroVO.getB_filename().get(0).getOriginalFilename().equals("")){
			fValidate = new FileUploadValidateWork(multiRequest, resultSiteMenuVO);
			resultValidateWork = fValidate.isResultValidateWork();
			
			//파일 검증 실패 시 
			if(resultValidateWork == false) {
				map.addAttribute("System_errMessage", "파일 검증 실패 : " + fValidate.getMessage());
				resultMap.put("returnPage",  rootContextPath + "/alert/href" );
				return resultMap;
			}
		}
		*/
		
		if( "Insert".equals(processMark) ) {
			
			templateZeroService.insertTableRecord(stringJson, request);
			
		}else if( "Update".equals(processMark) ) {
			
			templateZeroService.updateTableRecord(stringJson, request);
			
		}
		
		resultMap.put("returnPage",  rootContextPath + "/template/templateZeroList.go" );
		return resultMap;
	}
	@ResponseBody
	@RequestMapping(value = "/template/templateZeroDelete.go", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	public HashMap<String, Object> TemplateZeroDelete(HttpServletRequest request, @RequestBody HashMap<String, Object> stringJson) throws Exception {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		if( !"true".equals(request.getHeader("AJAX"))	) {
			resultMap.put("message",  "ajax 요청 아님!" ); return resultMap;
		}
		
		if( "N".equals( stringJson.get("del_chk") ) ) {
			
			System.out.println("비활성화 진행");
			templateZeroService.disableTableRecord(stringJson);
			
		} else {
			
			System.out.println("삭제 진행");
			templateZeroService.deleteTableRecord(stringJson);
			
		}
		
		resultMap.put("returnPage",  rootContextPath + "/template/templateZeroList.go" );
		
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/template/templateZeroRestore.go", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	public HashMap<String, Object> TemplateZeroRestore(HttpServletRequest request, @RequestBody HashMap<String, Object> stringJson) throws Exception {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		if( !"true".equals(request.getHeader("AJAX"))	) {
			resultMap.put("message",  "ajax 요청 아님!" ); return resultMap;
		}
		
		System.out.println("복구 진행");
		templateZeroService.restoreTableRecord(stringJson);
		
		resultMap.put("returnPage",  rootContextPath + "/template/templateZeroList.go" );
		
		return resultMap;
	}
	
}
