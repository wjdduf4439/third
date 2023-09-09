package com.ljy.third.controller;

import java.io.File;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ljy.third.service.FileEditorContentService;
import com.ljy.third.vo.FileEditorContentVO;

@RestController
public class FileEditorContentController {
	
	private String SAVE_PATH =  "";
	private String PREFIX_URL =  SAVE_PATH + "/";
	
	@Resource(name = "fileEditorContentService")
	private FileEditorContentService mFileEditorContentService;
	 
	
	@RequestMapping(value = "/ckeditorCon/upload.go", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public FileEditorContentVO upload(MultipartHttpServletRequest request) throws Exception {
		
		System.out.println("ckeditorCon 접속");
		makedir(request);
		
		// ckeditor 에서 파일을 보낼 때 upload : [파일] 형식으로 해서 넘어오기 때문에 upload라는 키의 밸류를 받아서 uploadFile에 저장함
		ModelAndView mav = new ModelAndView("jsonView");

		String siteCode = request.getParameter("siteCode");
		MultipartFile uploadFile = request.getFile("upload");
		
		
		System.out.println("ckeditorCon siteCode : " + siteCode);

		String originalFileName = uploadFile.getOriginalFilename();

		String ext = originalFileName.substring(originalFileName.indexOf("."));

		String newFileName = UUID.randomUUID() + ext;

		String savePath = PREFIX_URL + newFileName;
		String uploadPath = "./upload/" + newFileName; 

		File file = new File(savePath);
		uploadFile.transferTo(file);
		
		
		FileEditorContentVO reqFileEditorContentVO = new FileEditorContentVO();
		
		
		reqFileEditorContentVO.setSiteCode(siteCode);
		reqFileEditorContentVO.setFpath(savePath);
		
		//서비스에서 코드 처리
		//저장은 성공했는데 이제 무슨 값을 집어넣어서 관리하지?
		mFileEditorContentService.insertContentFile(reqFileEditorContentVO);

		return reqFileEditorContentVO;
	}
		
	//파일을 기입하거나 저장할때 경로는 지정
	private void makedir( HttpServletRequest req ) {
		
		String processerName = System.getProperty("os.name").toLowerCase();
		//System.out.println("processerName : " + processerName); 
		
		if(processerName.contains("windows")) { this.SAVE_PATH = "c:/ckeditor_upload"; }
		else { this.SAVE_PATH = "/home/ec2-user/third_FileDir"; }
				
		this.PREFIX_URL =  SAVE_PATH + "/";
		
	}

}
