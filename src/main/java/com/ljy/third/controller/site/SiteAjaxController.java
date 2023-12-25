package com.ljy.third.controller.site;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ljy.third.service.site.SiteService;
import com.ljy.third.vo.SysCodeVO;


//게시판 생성 부분의 sitefield 항목과 그 부가적인 기능을 동작하는 컨트롤러
@RestController
public class SiteAjaxController {
	
	@Resource(name = "SiteService")
	SiteService siteService;
	
	@RequestMapping(value = "/site/siteFieldInput.go")
	public String siteFieldInput(String tCode) throws Exception {
		
		System.out.println("siteFieldInput tCode : " + tCode);
		
		String inputHtml = "";
		
		SysCodeVO mSysCodeVO = new SysCodeVO();
		mSysCodeVO.setTemCodeHead(tCode);
		
		List<SysCodeVO> resultList = siteService.siteFieldInput(mSysCodeVO);
		
		for(int i = 0; i < resultList.size(); i++ ) { 
			
			
			inputHtml += "<input type='checkbox' id='placeRowVal' name='placeRowVal' value='";
			inputHtml += resultList.get(i).getSysOutNum();
			inputHtml += "' placeholder='제목' onclick='javascript:fn_checked(";
			inputHtml += resultList.get(i).getSysOutNum();
			inputHtml += ");' />";
			inputHtml += resultList.get(i).getTemFieldCharge() + "  ";
			
			//System.out.print( "SysCodeName : " + resultList.get(i).getSysCodeName() + " ; "); 
			//System.out.println( "sysCodeValue : " + resultList.get(i).getSysCodeValue() + " ; "); 
		}
		
		return inputHtml;
	}
	
	/*
	
		수정 화면 진입시 템플릿 유형을 표시한 다음 템플릿의 필드를 표시하기 위한 메소드
		템플릿의 필드는 체크박스만 표시하고 필드 너비는 siteUpdateWidthInput 메소드에서 실행한다.
	
	*/
	@RequestMapping(value = "/site/siteUpdateInput.go")
	public String siteUpdateInput(String tCode, String placeRowStr) throws Exception {
		
		
		
		//placeRowStrSpliter로 placeRowStr을 분해해서 체크할 체크박스를 구분함
		String placeRowStrSpliter = ",";
		String[] placeRowArray = placeRowStr.split(placeRowStrSpliter);
		//for(int j = 0; j < placeRowArray.length; j++ ) {  System.out.println("placeRowArray[" + j + "] : " + placeRowArray[j]); }
		 
		
		String inputHtml = "";
		
		SysCodeVO mSysCodeVO = new SysCodeVO();
		mSysCodeVO.setTemCodeHead(tCode);
		
		List<SysCodeVO> resultList = siteService.siteFieldInput(mSysCodeVO);
		System.out.println("siteUpdateInput tCode : " + tCode);
		//System.out.println("placeRowStr : " + placeRowStr);
		System.out.println("siteUpdateInput resultList size : " + resultList.size());
		
		
		for(int i = 0; i < resultList.size(); i++ ) { 
			
			
			inputHtml += "<input type='checkbox' id='placeRowVal" + resultList.get(i).getSysOutNum() + "'"; 
			inputHtml += " name='placeRowVal' value='";
			inputHtml += resultList.get(i).getSysOutNum();
			inputHtml += "' placeholder='제목' onclick='javascript:fn_checked(";
			inputHtml += resultList.get(i).getSysOutNum();
			inputHtml += ");'";
			for(int j = 0; j < placeRowArray.length; j++ ) { 
				//System.out.println("placeRowArray[j] : " + placeRowArray[j] + " :: " + resultList.get(i).getSysCodeNum() + " resultList.get(i).getSysCodeName()");
				
				if( Integer.parseInt(placeRowArray[j]) == resultList.get(i).getSysOutNum()) {
					inputHtml += " checked "; 
					}
				}
			inputHtml += "/>";
//			inputHtml += resultList.get(i).getTemFieldCharge() + "  ";
			inputHtml += "<label for='placeRowVal" + resultList.get(i).getSysOutNum() + "'>" + resultList.get(i).getTemFieldCharge() +"</label>";
			
			
			
		}
		
		return inputHtml;
	}
	
	/*
	
		수정 화면 진입시 템플릿 유형을 표시한 다음 템플릿의 필드 체크박스를 표시하고 필드 너비 input 태그를 표시하기 위한 메소드


	 */
	@RequestMapping(value = "/site/siteUpdateWidthInput.go")
	public String siteUpdateWidthInput(String placeRowStr, String placeWidthStr) throws Exception {
		
		System.out.println("siteUpdateWidthInput 메소드 접근");
		System.out.println("siteUpdateWidthInput placeRowStr : " + placeRowStr);
		System.out.println("siteUpdateWidthInput placeWidthStr : " + placeWidthStr);
		String inputHtml = "";
		
		//sitefield의 순번을 나타내는 구간
		String placeRowStrSpliter = ",";
		String[] placeRowArray = {};
		if(placeRowStr == "") placeRowArray[0] = "0";
		else placeRowArray = placeRowStr.split(placeRowStrSpliter);
		
		//sitefield의 표시길이를 나타내는 구간, placeWidthStrArray_temp 배열에 먼저 선언한 다음, placeWidthStrArray 배열 선언 작업을 한다
		String placeWidthStrSpliter = ",";
		String[] placeWidthStrArray = new String[placeRowArray.length];
		String[] placeWidthStrArray_temp = {};
		if(placeWidthStr == "") placeWidthStrArray[0] = "";
		else placeWidthStrArray_temp = placeWidthStr.split(placeWidthStrSpliter);
		
		/*
		placeWidthStrArray 선언 작업은, 최대 index을 placeRowArray.length로 하는 중에, 
			i의 값이 placeWidthStrArray_temp 배열 값보다 작으면 placeWidthStrArray_temp배열 안의 값을 선언
			i의 값이 placeWidthStrArray_temp 배열 값보다 같거나 크면 ""을 선언
		*/
		for(int i = 0; i < placeRowArray.length; i++ ) {
			if(i < placeWidthStrArray_temp.length) placeWidthStrArray[i] = placeWidthStrArray_temp[i];
			else placeWidthStrArray[i] = "";
		}	
			
		/*
		System.out.println("siteUpdateWidthInput placeRowArray length : " + placeRowArray.length);
		System.out.println("siteUpdateWidthInput placeWidthStrArray length : " + placeWidthStrArray.length);
		*/
		for(int i = 0; i < placeRowArray.length; i++ ) { 
			
			inputHtml += "<input  type='number' id='placeWidthOption";
			inputHtml += placeRowArray[i];
			inputHtml += "' name='placeWidthOption";
			inputHtml += placeRowArray[i];
			inputHtml += "' class ='width150' value='";
			inputHtml += placeWidthStrArray[i];
			inputHtml +=  "' placeholder='속성";
			inputHtml += (i+1);
			inputHtml += "' /> : "; 
					//이 부분을 getSysCodeValue로 대체해야 하지만 굳이 서버를 가용할 이유가 없어보임
			inputHtml += "</br>";
			
			
		}
		
		
		return inputHtml;
	}
	
	/* 
	 
	 게시판 설정 처음 접속시 템플릿 유형 부분을 수정 시 해당 메소드 실행
	 	작성 화면 : tcode : NONE
	 	수정 화면 : tcode : T~

	 */
	@RequestMapping(value = "/site/templateTypeOptionInput.go")
	public String templateTypeOptionInput(String tCode) throws Exception {
		
		System.out.println("templateTypeOptionInput tCode : " + tCode);
		
		String inputHtml = "";
		
		SysCodeVO mSysCodeVO = new SysCodeVO();
				
		if( tCode != "NONE" && !"NONE".equals(tCode) ) { mSysCodeVO.setCode(tCode); }
		else{ mSysCodeVO.setCode("NONE"); 	}
		
		List<SysCodeVO> resultList = siteService.siteTemplateTypeInput(mSysCodeVO);
		
		//inputHtml += " <select  id=\"templateTypeSelect\" name=\"templateTypeSelect\" class=\"selectText width150\" onchange=\"javascript:fn_template();\" >\r\n";
		
		if( tCode != "NONE" && !"NONE".equals(tCode) ) { 
			inputHtml += " <select  id=\"templateTypeSelect\" name=\"templateTypeSelect\" class=\"selectText width150\" disabled >\r\n";
		} else {
			inputHtml += " <select  id=\"templateTypeSelect\" name=\"templateTypeSelect\" class=\"selectText width150\" >\r\n";
		}
		
		
		inputHtml += " <option value=\"9\">::: 선택 :::</option> ";
		
			for(int i = 0; i < resultList.size(); i++ ) { 
				
				
				inputHtml += "<option id='templateTypeOption' value='";
				inputHtml += resultList.get(i).getCode();
				
				if( tCode != "NONE" && !"NONE".equals(tCode) ) { inputHtml += "' selected>"; }
				else { inputHtml += "' >"; }
								
				
				inputHtml += resultList.get(i).getFormName() + "  ";
				inputHtml += "</option>";
				
				/*
					
					수정 화면에서 나와야 할 태그
						<option id="templateTypeOption" value="0" selected disabled >일반</option>
						<option id="templateTypeOption" value="1" selected disabled >템플릿</option>

				 	최초 작성 홤녀에서 나와야 할 태그
					
						<option id="templateTypeOption" value="0" >일반</option>
						<option id="templateTypeOption" value="1" >템플릿</option>

				 */
				
			}
		
		
		inputHtml += " </select>";
		
		return inputHtml;
	}
	

}
