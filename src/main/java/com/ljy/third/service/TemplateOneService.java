package com.ljy.third.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ljy.third.vo.TemplateInfoVO;
import com.ljy.third.vo.TemplateOneVO;

public interface TemplateOneService {

	public List<TemplateInfoVO> selectTableFieldList(TemplateInfoVO templateInfoVO) throws Exception;

	public List<TemplateOneVO> selectTableRecordList(TemplateInfoVO templateInfoVO) throws Exception;

	public int selectTableRecordListCount(TemplateInfoVO templateInfoVO) throws Exception;
	
	public int selectTableRecordListCount(TemplateOneVO templateOneVO) throws Exception;
	
	public TemplateOneVO selectTableRecordOne(TemplateOneVO templateOneVO) throws Exception;
	
	public TemplateOneVO selectTableRecordContext(TemplateOneVO templateOneVO) throws Exception;
	
	public TemplateOneVO selectTableRecordRecent(TemplateOneVO templateOneVO) throws Exception;
	
	public void insertTableRecord(TemplateOneVO templateOneVO, final MultipartHttpServletRequest multiRequest ) throws Exception;
	
	public void updateTableRecord(TemplateOneVO templateOneVO, final MultipartHttpServletRequest multiRequest ) throws Exception;
	
	public void deleteTableRecord(TemplateOneVO templateOneVO) throws Exception;
	
}
