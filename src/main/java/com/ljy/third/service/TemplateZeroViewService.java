package com.ljy.third.service;

import java.util.List;

import com.ljy.third.vo.TemplateViewInfoVO;
import com.ljy.third.vo.TemplateZeroViewVO;

public interface TemplateZeroViewService {

	public List<TemplateViewInfoVO> selectTableFieldList(TemplateViewInfoVO templateViewInfoVO) throws Exception;

	public List<TemplateZeroViewVO> selectTableRecordList(TemplateViewInfoVO templateViewInfoVO) throws Exception;

	public int selectTableRecordListCount(TemplateViewInfoVO templateViewInfoVO) throws Exception;
	
	public int selectTableRecordListCount(TemplateZeroViewVO templateZeroViewVO) throws Exception;
	
	public TemplateZeroViewVO selectTableRecordOne(TemplateZeroViewVO templateZeroViewVO) throws Exception;
	
	public TemplateZeroViewVO selectTableRecordRecent(TemplateZeroViewVO templateZeroViewVO) throws Exception;
	
}
