package com.ljy.third.controller;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ljy.third.service.FileEditorContentService;
import com.ljy.third.vo.FileEditorContentVO;

@RestController
public class FileEditorContentController {
	
	@Resource(name = "fileEditorContentService")
	private FileEditorContentService mFileEditorContentService;
	 
	
	@RequestMapping(value = "/ckeditorCon/upload.go", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView upload(MultipartHttpServletRequest request) throws Exception {
		
		// ckeditor 에서 파일을 보낼 때 upload : [파일] 형식으로 해서 넘어오기 때문에 upload라는 키의 밸류를 받아서 uploadFile에 저장함

		//String ext = originalFileName.substring(originalFileName.indexOf("."));
		//String newFileName = UUID.randomUUID() + ext;
		
		FileEditorContentVO reqFileEditorContentVO = new FileEditorContentVO();
		//reqFileEditorContentVO.setFpath(savePath);
		
		//서비스에서 코드 처리
		//저장은 성공했는데 이제 무슨 값을 집어넣어서 관리하지?
		FileEditorContentVO resultfileEditorContentVO = mFileEditorContentService.insertContentFile(reqFileEditorContentVO, request);
		
		ModelAndView mav = new ModelAndView("jsonView");
		mav.addObject("url", resultfileEditorContentVO.getSavingFname());

		return mav;
	}

}
