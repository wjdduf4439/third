package com.ljy.third.service.templateZero;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ljy.third.dao.BoardDAO;
import com.ljy.third.dao.FileDAO;
import com.ljy.third.dao.TemplateZeroViewDAO;
import com.ljy.third.vo.TemplateViewInfoVO;
import com.ljy.third.vo.templateZero.TemplateZeroViewVO;

@Service("TemplateZeroViewService")
public class TemplateZeroViewServiceImpl implements TemplateZeroViewService {

	@Resource(name = "TemplateZeroViewDAO")
	TemplateZeroViewDAO templateZeroViewDAO; 
	
	@Resource(name = "FileDAO")
	FileDAO fileDAO;

	@Resource(name = "BoardDAO")
	private BoardDAO mboardDAO;
	
	@Override
	public List<TemplateViewInfoVO> selectTableFieldList(TemplateViewInfoVO templateViewInfoVO) throws Exception {
		// TODO Auto-generated method stub		
		return templateZeroViewDAO.selectTableFieldList(templateViewInfoVO);
	}

	@Override
	public List<TemplateZeroViewVO> selectTableRecordList(TemplateViewInfoVO templateViewInfoVO) throws Exception {
		// TODO Auto-generated method stub
		return templateZeroViewDAO.selectTableRecordList(templateViewInfoVO);
	}

	@Override
	public int selectTableRecordListCount(TemplateViewInfoVO templateViewInfoVO) throws Exception {
		// TODO Auto-generated method stub
		return templateZeroViewDAO.selectTableRecordListCount(templateViewInfoVO);
	}
	
	@Override
	public int selectTableRecordListCount(TemplateZeroViewVO templateZeroViewVO) throws Exception {
		// TODO Auto-generated method stub
		return templateZeroViewDAO.selectTableRecordListCount(templateZeroViewVO);
	}

	@Override
	public TemplateZeroViewVO selectTableRecordOne(TemplateZeroViewVO templateZeroViewVO) throws Exception {
		// TODO Auto-generated method stub
		return templateZeroViewDAO.selectTableRecordOne(templateZeroViewVO);
	}

	@Override
	public TemplateZeroViewVO selectTableRecordRecent(TemplateZeroViewVO templateZeroViewVO) throws Exception {
		// TODO Auto-generated method stub
		return templateZeroViewDAO.selectTableRecordRecent(templateZeroViewVO);
	}

}
