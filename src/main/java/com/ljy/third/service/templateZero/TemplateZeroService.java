package com.ljy.third.service.templateZero;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ljy.third.vo.FileEditorContentVO;
import com.ljy.third.vo.TemplateInfoVO;
import com.ljy.third.vo.TemplateOneVO;
import com.ljy.third.vo.templateZero.TemplateZeroVO;

public interface TemplateZeroService {

	public List<TemplateInfoVO> selectTableFieldList(TemplateInfoVO templateInfoVO) throws Exception;

	public List<TemplateZeroVO> selectTableRecordList(TemplateInfoVO templateInfoVO) throws Exception;

	public int selectTableRecordListCount(TemplateInfoVO templateInfoVO) throws Exception;
	
	public int selectTableRecordListCount(TemplateZeroVO templateZeroVO) throws Exception;

	public String selectTableRecordListMax(HashMap<String, String> stringJson) throws Exception;
	
	public String selectTableAtchFileIdMax(String siteCode) throws Exception;
	
	public List<TemplateZeroVO> selectTableNoticeList(TemplateZeroVO templateZeroVO) throws Exception;	
	
	public TemplateZeroVO selectTableRecordOne(TemplateZeroVO templateZeroVO) throws Exception;
	
	public TemplateZeroVO selectTableRecordRecent(TemplateZeroVO templateZeroVO) throws Exception;
	
	public TemplateZeroVO selectTableECFRecordList(TemplateZeroVO templateZeroVO) throws Exception;
	
	public void insertTableRecord(HashMap<String, String> stringJson, HttpServletRequest req ) throws Exception;
	
	public void insertFileRecord(HashMap<String, String> stringJson ) throws Exception;
	
	public void updateTableRecord(HashMap<String, String> stringJson, HttpServletRequest req ) throws Exception;
	
	public void updateAtchFileId( HashMap<String, String> stringJson ) throws Exception;
	
	public void disableTableRecord(HashMap<String, Object> stringJson) throws Exception;

	public void restoreTableRecord(HashMap<String, Object> stringJson) throws Exception;

	public void deleteTableRecord(HashMap<String, Object> stringJson) throws Exception;
	
}
