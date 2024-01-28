package com.ljy.third.controller;

import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


//실제로 파일 다운로드를 담당하는 컨트롤러. fileController에서 이어짐
@Controller
public class FileDownloadView {

	private String SAVE_PATH =  "";
	private String PREFIX_URL =  SAVE_PATH + "/";
	
	@RequestMapping(value = "/fileDownloadView")
	public ResponseEntity<Object> download(String code, ModelMap map, HttpServletRequest req) {
		
		makedir(req);
		this.PREFIX_URL += req.getAttribute("realFileName");
		
		System.out.println("다운로드 시도 PREFIX_URL : " + this.PREFIX_URL); 
		System.out.println("다운로드 시도 파일명 : " + req.getAttribute("writeFileName")); 
								
		
		try {
			Path filePath = Paths.get(PREFIX_URL);
			Resource resource = new InputStreamResource(Files.newInputStream(filePath)); // 파일 resource 얻기
			String downloadRealFileName = URLEncoder.encode(req.getAttribute("writeFileName").toString(), "UTF-8"); //파일 이름을 한글로도 다운로드 받을 수 있게 하기
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "application/json;charset=UTF-8");
			headers.setContentDisposition(ContentDisposition.builder("attachment").filename(downloadRealFileName).build());  // 다운로드 되거나 로컬에 저장되는 용도로 쓰이는지를 알려주는 헤더
			
			return new ResponseEntity<Object>(resource, headers, HttpStatus.OK);
			
		} catch(Exception e) {
			return new ResponseEntity<Object>(null, HttpStatus.CONFLICT);
		}
	}
	
	//파일을 기입하거나 저장할때 경로는 지정
	private void makedir( HttpServletRequest req ) {
		
		String processerName = System.getProperty("os.name").toLowerCase();
		//System.out.println("processerName : " + processerName); 
		
		if(processerName.contains("windows")) { this.SAVE_PATH = "c:/upload"; }
		else { this.SAVE_PATH = "/home/ec2-user/third_FileDir"; }
				
		this.PREFIX_URL =  SAVE_PATH + "/";
		
	}
	

}
