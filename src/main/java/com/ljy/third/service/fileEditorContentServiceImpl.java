package com.ljy.third.service;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ljy.third.dao.FileEditorContentDAO;
import com.ljy.third.vo.FileEditorContentVO;

@Service("fileEditorContentService")
public class fileEditorContentServiceImpl implements FileEditorContentService {

	@Resource(name = "FileEditorContentDAO")
	private FileEditorContentDAO mfileEditorContentDAO;

	private String SAVE_PATH =  "";
	private String PREFIX_URL =  SAVE_PATH + "/";
	
	@Override
	public FileEditorContentVO insertContentFile(FileEditorContentVO mFileEditorContentVO, MultipartHttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub

		makedir(request);

		String siteCode = request.getParameter("siteCode");
		MultipartFile uploadFile = request.getFile("upload");
		
		
		System.out.println("ckeditorCon siteCode : " + siteCode);
		
		String newCode = "";
		try {
				String maxCode = mfileEditorContentDAO.selectTableRecordListMax(mFileEditorContentVO);
				int temp = Integer.parseInt(maxCode.substring(7, maxCode.length())); temp ++;
				int tailNumber = temp;
				
				newCode = "ECFILE_";
				int i = 1;
				
				while(temp >= 10) {
					
					temp /= 10;
					i++;
					
				}
				
				for(int j = 0; j < 13-i ; j++) { newCode += "0"; }
				newCode += Integer.toString(tailNumber);
				
		}catch(Exception e) {
			//nullpointer방지
			newCode = "ECFILE_0000000000001";
			
		}
		mFileEditorContentVO.setCode(newCode);
		

		
		String originFilename = "";
		String savingFilename = "";
		int savingFileTypeIndex = 0;
		String savingFileType = "";
		
		//savingfname으로 파일 저장, savingFileTypeIndex으로 파일 형식의 온점 마크 위치를 찾음
		originFilename = uploadFile.getOriginalFilename();
		savingFilename = makeSavingFileCode( newCode);
		
		//savingFileTypeIndex으로 파일 형식의 온점 마크 위치를 찾고 파일 형식을 잘라내어 저장함
		savingFileTypeIndex = makeSavingFileTypeIndex(originFilename);
		savingFileType = originFilename.substring(savingFileTypeIndex, originFilename.length());
		

		String savePath = PREFIX_URL + savingFilename + savingFileType;
		String uploadPath = "./upload/" + savingFilename + savingFileType;

		File file = new File(savePath);
		uploadFile.transferTo(file);

		mFileEditorContentVO.setSiteCode(siteCode);
		mFileEditorContentVO.setFpath(savePath);
		mFileEditorContentVO.setSavingFname(savingFilename + savingFileType);
		mFileEditorContentVO.setFname(originFilename);
		
		
		mfileEditorContentDAO.insertContentFile(mFileEditorContentVO);
		
		return mFileEditorContentVO;
	}


	
	//파일을 기입하거나 저장할때 경로는 지정
	private void makedir( HttpServletRequest req ) {
		
		String processerName = System.getProperty("os.name").toLowerCase();
		//System.out.println("processerName : " + processerName); 
		
		if(processerName.contains("windows")) { this.SAVE_PATH = "c:/ckeditor_upload"; }
		else { this.SAVE_PATH = "/ckeditor_FileDir"; }
				
		this.PREFIX_URL =  SAVE_PATH + "/";
		
	}
	
	//파일을 기입하거나 저장할때 현재 시간과 fid, sign에 기반해서 코드를 생성
	private String makeSavingFileCode( String code ) {

		LocalDate  savingDate = LocalDate.now();
		DateTimeFormatter savingDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		LocalTime  savingTime = LocalTime.now();
		DateTimeFormatter savingTimeFormatter = DateTimeFormatter.ofPattern("HH-mm-ss");
		
		return code + "_" + savingDate.format(savingDateFormatter)+ "_" + savingTime.format(savingTimeFormatter);
	}
	
	private int makeSavingFileTypeIndex( String originFileName ) {

		List<Integer> indexList = new ArrayList<Integer> ();
		String fileTypeMark = ".";
		int savingFileTypeIndex = originFileName.indexOf(fileTypeMark);
		
		while(savingFileTypeIndex != -1) {
			
			if(savingFileTypeIndex == -1) break;
			
			indexList.add(savingFileTypeIndex);
			savingFileTypeIndex = originFileName.indexOf(fileTypeMark, savingFileTypeIndex + fileTypeMark.length());
		}
		
		return indexList.get(indexList.size() - 1);
	}
	
}
