package com.ljy.third.service;

import org.springframework.stereotype.Service;

import com.ljy.third.vo.loginHomeVO;

@Service("loginHomeService")
public class loginHomeServiceImpl implements loginHomeService {

	public loginHomeVO accLoginHome( loginHomeVO mloginHomeVO ) throws Exception {
		
		System.out.println("access Login Service");
		
		return mloginHomeVO;
	}
	
}
