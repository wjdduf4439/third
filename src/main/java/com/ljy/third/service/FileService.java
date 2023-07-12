package com.ljy.third.service;

import java.util.List;

import com.ljy.third.vo.FileVO;

public interface FileService {

	public List<FileVO> selectFileMenuList(String atchFileId) throws Exception;
	
	public int selectAtchFileCount(String fid);

	public void deleteFileMenu(String code, String fid, String fsign, String sitecode);
	
	
}
