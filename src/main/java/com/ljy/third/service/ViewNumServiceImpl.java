package com.ljy.third.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.ljy.third.dao.ViewNumDAO;

@Service("ViewNumService")
public class ViewNumServiceImpl implements ViewNumService {

	@Resource(name = "ViewNumDAO")
	ViewNumDAO viewNumDAO;
	
	@Override
	public void viewNumPlusUpdate(ModelMap map) throws Exception {
		// TODO Auto-generated method stub
		viewNumDAO.viewNumPlusUpdate(map);
	}

}
