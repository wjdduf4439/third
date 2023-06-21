package com.ljy.third.dao;

import org.springframework.stereotype.Repository;

import com.ljy.third.vo.loginHomeVO;

@Repository
public class loginHomeDAO {

	public loginHomeVO selectFormMenuOne(loginHomeVO mloginHomeVO) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("access Login DAO");
		
		return mloginHomeVO;
	}
	
}
