package com.ljy.third.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.ui.ModelMap;

@Repository("ViewNumDAO")
public class ViewNumDAO {

	@Inject //�ڹٿ��� �����ϴ� �ش� ���������Ŀ� ���� �����͸� �����ϴ� ������̼�
	private SqlSession sqlSession;
	
	public void viewNumPlusUpdate(ModelMap map) {
		// TODO Auto-generated method stub
		sqlSession.selectOne("com.ljy.third.dao.ViewNum.plusViewNum", map);
	}
	
}
