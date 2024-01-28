package com.ljy.third.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.ui.ModelMap;

import jakarta.inject.Inject;

@Repository("ViewNumDAO")
public class ViewNumDAO {

	@Inject //�ڹٿ��� �����ϴ� �ش� ���������Ŀ� ���� �����͸� �����ϴ� ������̼�
	@Autowired
	private SqlSession sqlSession;
	
	public void viewNumPlusUpdate(ModelMap map) {
		// TODO Auto-generated method stub
		sqlSession.selectOne("com.ljy.third.dao.ViewNum.plusViewNum", map);
	}
	
}
