package com.ljy.third.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ljy.third.vo.logAdminVO;

import jakarta.inject.Inject;


@Repository("logAdminDAO")
public class logAdminDAO {

	@Inject //�ڹٿ��� �����ϴ� �ش� ���������Ŀ� ���� �����͸� �����ϴ� ������̼�
	@Autowired
	private SqlSession sqlSession;
	
	public List<logAdminVO> selectlogAdminList(logAdminVO mlogAdminVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.ljy.third.dao.logAdminDao.selectlogAdminList", mlogAdminVO );
	}
	
	public int selectlogAdminCnt(logAdminVO mlogAdminVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.ljy.third.dao.logAdminDao.looklogAdminCnt", mlogAdminVO );
	}

	public String selectTableRecordListMax() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.ljy.third.dao.logAdminDao.selectTableRecordListMax");
	}

	public void insertlogAdmin(logAdminVO mlogAdminVO) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("log dao 접근");
		sqlSession.update("com.ljy.third.dao.logAdminDao.insertlogAdmin", mlogAdminVO );
	}
	
}
