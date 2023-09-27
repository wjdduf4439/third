package com.ljy.third.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ljy.third.vo.FileEditorContentVO;
import com.ljy.third.vo.TemplateInfoVO;
import com.ljy.third.vo.TemplateOneVO;
import com.ljy.third.vo.TemplateZeroVO;

public interface TemplateZeroService {

	public List<TemplateInfoVO> selectTableFieldList(TemplateInfoVO templateInfoVO) throws Exception;

	public List<TemplateZeroVO> selectTableRecordList(TemplateInfoVO templateInfoVO) throws Exception;

	public int selectTableRecordListCount(TemplateInfoVO templateInfoVO) throws Exception;
	
	public int selectTableRecordListCount(TemplateZeroVO templateZeroVO) throws Exception;
	
	public TemplateZeroVO selectTableRecordOne(TemplateZeroVO templateZeroVO) throws Exception;
	
	public TemplateZeroVO selectTableRecordRecent(TemplateZeroVO templateZeroVO) throws Exception;
	
	public TemplateZeroVO selectTableECFRecordList(TemplateZeroVO templateZeroVO) throws Exception;
	
	public void insertTableRecord(TemplateZeroVO templateZeroVO, final MultipartHttpServletRequest multiRequest, HttpServletRequest req ) throws Exception;
	
	public void updateTableRecord(TemplateZeroVO templateZeroVO, final MultipartHttpServletRequest multiRequest, HttpServletRequest req ) throws Exception;
	
	public void deleteTableRecord(TemplateZeroVO templateZeroVO) throws Exception;
	
}
