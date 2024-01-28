package com.ljy.third.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ljy.third.vo.TemplateInfoVO;
import com.ljy.third.vo.TemplateViewInfoVO;

import jakarta.inject.Inject;


@Repository("TemplateInfoDAO")
public class TemplateInfoDAO {

	@Inject //�ڹٿ��� �����ϴ� �ش� ���������Ŀ� ���� �����͸� �����ϴ� ������̼�
	@Autowired
	private SqlSession sqlSession;
	
	public List<?> selectMoreMenuList() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.ljy.third.dao.TemplateInfo.selectMoreMenuList");
	}

	public TemplateInfoVO selectTableName(TemplateInfoVO templateInfoVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.ljy.third.dao.TemplateInfo.selectTableName", templateInfoVO );
	}
	
	public TemplateViewInfoVO selectTableName(TemplateViewInfoVO templateViewInfoVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.ljy.third.dao.TemplateInfo.selectTableName_View", templateViewInfoVO );
	}

	public List<?> selectTableFieldList(TemplateInfoVO templateInfoVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.ljy.third.dao.TemplateInfo.selectTableFieldList", templateInfoVO );
	}

	public List<TemplateInfoVO> selectTableRecordList(TemplateInfoVO templateInfoVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.ljy.third.dao.TemplateInfo.selectTableRecordList", templateInfoVO );
	}
	
	public int selectTableRecordListCount(TemplateInfoVO templateInfoVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.ljy.third.dao.TemplateInfo.selectTableRecordListCount", templateInfoVO );
	}
	
}
