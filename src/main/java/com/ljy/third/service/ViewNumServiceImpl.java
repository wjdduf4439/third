package com.ljy.third.service;


import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.ljy.third.dao.ViewNumDAO;

import jakarta.annotation.Resource;

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
