package com.ljy.third.service;


import org.springframework.stereotype.Service;

import com.ljy.third.dao.loginHomeDAO;
import com.ljy.third.vo.loginHomeVO;

import jakarta.annotation.Resource;

@Service("loginHomeService")
public class loginHomeServiceImpl implements loginHomeService {

	@Resource(name = "loginHomeDAO")
	loginHomeDAO mloginHomeDAO;
	
	public loginHomeVO accLoginHome( loginHomeVO mloginHomeVO ) throws Exception {
		
		System.out.println("access Login Service");
		loginHomeVO registedLoginHomeVO = mloginHomeDAO.selectloginHomeOne(mloginHomeVO);
		
		return registedLoginHomeVO;
	}
	
}
