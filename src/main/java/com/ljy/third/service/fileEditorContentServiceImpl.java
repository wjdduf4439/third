package com.ljy.third.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ljy.third.dao.fileEditorContentDAO;
import com.ljy.third.vo.FileEditorContentVO;

@Service("fileEditorContentService")
public class fileEditorContentServiceImpl implements FileEditorContentService {

	@Resource(name = "fileEditorContentDAO")
	private fileEditorContentDAO mfileEditorContentDAO;

	@Override
	public void insertContentFile(FileEditorContentVO mFileEditorContentVO) throws Exception {
		// TODO Auto-generated method stub
		
		try {
				String maxCode = mfileEditorContentDAO.selectTableRecordListMax(mFileEditorContentVO);
				int temp = Integer.parseInt(maxCode.substring(7, maxCode.length())); temp ++;
				int tailNumber = temp;
				
				String newCode = "ECFILE_";
				int i = 1;
				
				while(temp >= 10) {
					
					temp /= 10;
					i++;
					
				}
				
				for(int j = 0; j < 13-i ; j++) { newCode += "0"; }
				newCode += Integer.toString(tailNumber);
				
				mFileEditorContentVO.setCode(newCode);
		}catch(Exception e) {
			//nullpointer방지
			mFileEditorContentVO.setCode("ECFILE_0000000000001");
			
		}
		
		
		mfileEditorContentDAO.insertContentFile(mFileEditorContentVO);
	}

	
	
}
