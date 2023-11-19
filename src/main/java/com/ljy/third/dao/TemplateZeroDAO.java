package com.ljy.third.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ljy.third.vo.TemplateInfoVO;
import com.ljy.third.vo.TemplateZeroVO;

@Repository("TemplateZeroDAO")
public class TemplateZeroDAO {
	
	@Inject //�ڹٿ��� �����ϴ� �ش� ���������Ŀ� ���� �����͸� �����ϴ� ������̼�
	private SqlSession sqlSession;

	public List<TemplateInfoVO> selectTableFieldList(TemplateInfoVO templateInfoVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.ljy.third.dao.TemplateZero.selectTableFieldList", templateInfoVO );
	}

	public List<TemplateZeroVO> selectTableRecordList(TemplateInfoVO templateInfoVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.ljy.third.dao.TemplateZero.selectTableRecordList", templateInfoVO );
	}
	
	public int selectTableRecordListCount(TemplateInfoVO templateInfoVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.ljy.third.dao.TemplateZero.selectTableRecordListCount", templateInfoVO );
	}
	
	public int selectTableRecordListCount(TemplateZeroVO templateZeroVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.ljy.third.dao.TemplateZero.selectTableRecordListCount", templateZeroVO );
	}

	public String selectTableAtchFileId(TemplateZeroVO templateZeroVO) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.ljy.third.dao.TemplateZero.selectTableAtchFileId", templateZeroVO );
	}
	
	public String maxTableAtchFileId(TemplateZeroVO templateZeroVO) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.ljy.third.dao.TemplateZero.maxTableAtchFileId", templateZeroVO ) == null? "0":sqlSession.selectOne("com.ljy.third.dao.TemplateZero.maxTableAtchFileId", templateZeroVO )  ;
	}
	
	public String countTableAtchFileId(TemplateZeroVO templateZeroVO) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.ljy.third.dao.TemplateZero.countTableAtchFileId", templateZeroVO );
	}

	public String selectTableRecordListMax(TemplateZeroVO templateZeroVO) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.ljy.third.dao.TemplateZero.selectTableRecordListMax", templateZeroVO );
	}
	
	public String selectTableFileListMax(TemplateZeroVO templateZeroVO) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.ljy.third.dao.TemplateZero.selectTableFileListMax", templateZeroVO );
	}

	public TemplateZeroVO selectTableRecordOne(TemplateZeroVO templateZeroVO) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.ljy.third.dao.TemplateZero.selectTableRecordOne", templateZeroVO );
	}

	public TemplateZeroVO selectTableRecordRecent(TemplateZeroVO templateZeroVO) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.ljy.third.dao.TemplateZero.selectTableRecordRecent", templateZeroVO );
	}

	public void insertTableRecord(TemplateZeroVO templateZeroVO) {
		// TODO Auto-generated method stub
		sqlSession.selectOne("com.ljy.third.dao.TemplateZero.insertTableRecord", templateZeroVO );
	}
	
	public void updateTableRecord(TemplateZeroVO templateZeroVO) {
		// TODO Auto-generated method stub
		sqlSession.selectOne("com.ljy.third.dao.TemplateZero.updateTableRecord", templateZeroVO );
	}
	
	public void disableTableRecord(TemplateZeroVO templateZeroVO) {
		// TODO Auto-generated method stub
		sqlSession.selectOne("com.ljy.third.dao.TemplateZero.disableTableRecord", templateZeroVO );
	}
	
	public void restoreTableRecord(TemplateZeroVO templateZeroVO) {
		// TODO Auto-generated method stub
		sqlSession.selectOne("com.ljy.third.dao.TemplateZero.restoreTableRecord", templateZeroVO );
	}
	
	public void deleteTableRecord(TemplateZeroVO templateZeroVO) {
		// TODO Auto-generated method stub
		sqlSession.selectOne("com.ljy.third.dao.TemplateZero.deleteTableRecord", templateZeroVO );
	}
	
	public void deleteFileRecord(TemplateZeroVO templateZeroVO) {
		// TODO Auto-generated method stub
		sqlSession.selectOne("com.ljy.third.dao.TemplateZero.deleteFileRecord", templateZeroVO );
	}
	
	
}
