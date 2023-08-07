package com.ljy.third.service;

import java.util.List;

import com.ljy.third.vo.logAdminVO;

public interface logAdminService {

	public List<logAdminVO> selectlogAdminList(logAdminVO mlogAdminVO) throws Exception;
	
	public int selectlogAdminCnt(logAdminVO mlogAdminVO) throws Exception;
	
	public String selectlogAdminMax(logAdminVO mlogAdminVO) throws Exception;
	
	public void insertlogAdmin(logAdminVO mlogAdminVO) throws Exception;
	
}
