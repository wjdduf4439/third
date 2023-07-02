package com.ljy.third.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ljy.third.vo.loginHomeVO;


//springboot는 mapper어노테이션으로 별도로 db와 연동할 수 있다.
@Repository("loginHomeDAO")
public class loginHomeDAO {
	
	@Inject //자바에서 지원하는 해당 데이터형식에 맞춰 데이터를 주입하는 어노테이션
	private SqlSession sqlSession;
	
	public loginHomeVO selectloginHomeOne(loginHomeVO mloginHomeVO) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("access Login DAO");
		loginHomeVO registedLoginHomeVO = new loginHomeVO();
		
		return sqlSession.selectOne("com.ljy.third.dao.loginHomeMapper", mloginHomeVO);
	}
	
}
