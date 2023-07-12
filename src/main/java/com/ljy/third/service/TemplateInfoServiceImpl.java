package com.ljy.third.service;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ljy.third.dao.TemplateInfoDAO;
import com.ljy.third.vo.TemplateInfoVO;

@Service("TemplateInfoService")
public class TemplateInfoServiceImpl implements TemplateInfoService {

	@Resource(name = "TemplateInfoDAO")
	TemplateInfoDAO templateInfoDAO; 

	@Override
	public TemplateInfoVO selectTableName(TemplateInfoVO templateInfoVO) throws Exception {
		// TODO Auto-generated method stub
		return templateInfoDAO.selectTableName(templateInfoVO);
	}

}
