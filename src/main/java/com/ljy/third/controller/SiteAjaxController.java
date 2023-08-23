package com.ljy.third.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ljy.third.service.SiteService;
import com.ljy.third.vo.SysCodeVO;


//게시판 생성 부분의 sitefield 항목과 그 부가적인 기능을 동작하는 컨트롤러
@RestController
public class SiteAjaxController {
	
	@Resource(name = "SiteService")
	SiteService siteService;
	
	@RequestMapping(value = "/site/siteFieldInput.go")
	public String siteFieldInput(String tCode) throws Exception {
		
		//System.out.println("tCode : " + tCode);
		
		String inputHtml = "";
		
		SysCodeVO mSysCodeVO = new SysCodeVO();
		mSysCodeVO.setTemCodeHead("T" + tCode + "SF");
		
		List<SysCodeVO> resultList = siteService.siteFieldInput(mSysCodeVO);
		
		for(int i = 0; i < resultList.size(); i++ ) { 
			
			
			inputHtml += "<input type='checkbox' id='placeRow' name='placeRow' value='";
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
	
	@RequestMapping(value = "/site/siteUpdateInput.go")
	public String siteUpdateInput(String tCode, String placeRowStr) throws Exception {
		
		System.out.println("tCode : " + tCode);
		//System.out.println("placeRowStr : " + placeRowStr);
		
		//placeRowStrSpliter로 placeRowStr을 분해해서 체크할 체크박스를 구분함
		String placeRowStrSpliter = ",";
		String[] placeRowArray = placeRowStr.split(placeRowStrSpliter);
		//for(int j = 0; j < placeRowArray.length; j++ ) {  System.out.println("placeRowArray[" + j + "] : " + placeRowArray[j]); }
		 
		
		String inputHtml = "";
		
		SysCodeVO mSysCodeVO = new SysCodeVO();
		mSysCodeVO.setTemCodeHead("T" + tCode + "SF");
		
		List<SysCodeVO> resultList = siteService.siteFieldInput(mSysCodeVO);
		
		for(int i = 0; i < resultList.size(); i++ ) { 
			
			
			inputHtml += "<input type='checkbox' id='placeRow' name='placeRow' value='";
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
			inputHtml += resultList.get(i).getTemFieldCharge() + "  ";
			
		}
		
		return inputHtml;
	}
	
	
	@RequestMapping(value = "/site/siteUpdateWidthInput.go")
	public String siteUpdateWidthInput(String placeRowStr, String placeWidthStr) throws Exception {
		
		String inputHtml = "";
		
		//sitefield의 순번을 나타내는 구간
		String placeRowStrSpliter = ",";
		String[] placeRowArray = placeRowStr.split(placeRowStrSpliter);
		
		//sitefield의 표시길이를 나타내는 구간
		String placeWidthStrSpliter = ",";
		String[] placeWidthStrArray = placeWidthStr.split(placeWidthStrSpliter);
		
		
		for(int i = 0; i < placeRowArray.length; i++ ) { 
			
			inputHtml += "<input  type='number' id='placeWidthOption' ";
			inputHtml += placeRowArray[i];
			inputHtml += " name='placeWidthOption";
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

}
