package com.ljy.third.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ljy.third.vo.TemplateViewInfoVO;
import com.ljy.third.vo.TemplateZeroViewVO;

@Repository("TemplateZeroViewDAO")
public class TemplateZeroViewDAO {
	
	@Inject //�ڹٿ��� �����ϴ� �ش� ���������Ŀ� ���� �����͸� �����ϴ� ������̼�
	private SqlSession sqlSession;

	public List<TemplateViewInfoVO> selectTableFieldList(TemplateViewInfoVO templateViewInfoVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.ljy.third.dao.TemplateZeroView.selectTableFieldList", templateViewInfoVO );
	}

	public List<TemplateZeroViewVO> selectTableRecordList(TemplateViewInfoVO templateViewInfoVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.ljy.third.dao.TemplateZeroView.selectTableRecordList", templateViewInfoVO );
	}
	
	public int selectTableRecordListCount(TemplateViewInfoVO templateViewInfoVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.ljy.third.dao.TemplateZeroView.selectTableRecordListCount", templateViewInfoVO );
	}
	
	public int selectTableRecordListCount(TemplateZeroViewVO templateZeroViewVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.ljy.third.dao.TemplateZeroView.selectTableRecordListCount", templateZeroViewVO );
	}

	public String selectTableAtchFileId(TemplateZeroViewVO templateZeroViewVO) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.ljy.third.dao.TemplateZeroView.selectTableAtchFileId", templateZeroViewVO );
	}
	
	public String maxTableAtchFileId(TemplateZeroViewVO templateZeroViewVO) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.ljy.third.dao.TemplateZeroView.maxTableAtchFileId", templateZeroViewVO ) == null? "0":sqlSession.selectOne("com.ljy.third.dao.TemplateZeroView.maxTableAtchFileId", templateZeroViewVO )  ;
	}
	
	public String countTableAtchFileId(TemplateZeroViewVO templateZeroViewVO) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.ljy.third.dao.TemplateZeroView.countTableAtchFileId", templateZeroViewVO );
	}

	public String selectTableRecordListMax(TemplateZeroViewVO templateZeroViewVO) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.ljy.third.dao.TemplateZeroView.selectTableRecordListMax", templateZeroViewVO );
	}
	
	public String selectTableFileListMax(TemplateZeroViewVO templateZeroViewVO) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.ljy.third.dao.TemplateZeroView.selectTableFileListMax", templateZeroViewVO );
	}

	public TemplateZeroViewVO selectTableRecordOne(TemplateZeroViewVO templateZeroViewVO) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.ljy.third.dao.TemplateZeroView.selectTableRecordOne", templateZeroViewVO );
	}

	public TemplateZeroViewVO selectTableRecordRecent(TemplateZeroViewVO templateZeroViewVO) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.ljy.third.dao.TemplateZeroView.selectTableRecordRecent", templateZeroViewVO );
	}
	
	
}
