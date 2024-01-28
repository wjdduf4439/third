package com.ljy.third.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ljy.third.dao.FileDAO;
import com.ljy.third.vo.FileVO;

import jakarta.annotation.Resource;

@Service("FileService")
public class FileServiceImpl implements FileService {

	@Resource(name = "FileDAO")
	private FileDAO fileDAO;
	
	@Override
	public List<FileVO> selectFileMenuList(String atchFileId) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("FileServiceImpl atchFileId : " + atchFileId);
		
		FileVO mfileVO = new FileVO();
		
		String fid = atchFileId.substring(0, atchFileId.indexOf("_AND_")); mfileVO.setFid(fid);
		String fcode = atchFileId.substring(atchFileId.indexOf("_AND_") + 5, atchFileId.length() ); mfileVO.setCode(fcode);

		//System.out.println("FileServiceImpl fcode : " + mfileVO.getCode());
		//System.out.println("FileServiceImpl fid : " + mfileVO.getFid());
		
		
		return fileDAO.selectFileMenuList(mfileVO);
	}

	@Override
	public int selectAtchFileCount(String fid) {
		// TODO Auto-generated method stub
		return fileDAO.selectAtchFileCount(fid);
	}
	
	@Override
	public String selectFileCodeMax() {
		// TODO Auto-generated method stub
		return fileDAO.selectFileCodeMax();
	}	
	
	@Override
	public void deleteFileMenu(String code, String fid, String fsign, String sitecode) {
		// TODO Auto-generated method stub
		fileDAO.deleteFileMenu(code, fid, fsign);
		
	}

	
	
}
