package com.ljy.third.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.ljy.third.vo.loginHomeVO;

@Service("loginHomeService")
public interface loginHomeService {
	
	public loginHomeVO accLoginHome(loginHomeVO mloginHomeVO) throws Exception;

}
