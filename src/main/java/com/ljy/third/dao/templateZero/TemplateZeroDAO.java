package com.ljy.third.dao.templateZero;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ljy.third.vo.TemplateInfoVO;
import com.ljy.third.vo.templateZero.TemplateZeroVO;

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
	
	public List<TemplateZeroVO> selectTableNoticeList(TemplateZeroVO templateZeroVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.ljy.third.dao.TemplateZero.selectTableNoticeList", templateZeroVO );
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

	public String selectTableRecordListMax(HashMap<String, String> stringJson) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.ljy.third.dao.TemplateZero.selectTableRecordListMax", stringJson );
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

	public void insertTableRecord(HashMap<String, String> stringJson) {
		// TODO Auto-generated method stub
		sqlSession.selectOne("com.ljy.third.dao.TemplateZero.insertTableRecord", stringJson );
	}
	
	public void updateTableRecord(HashMap<String, String> stringJson) {
		// TODO Auto-generated method stub
		sqlSession.selectOne("com.ljy.third.dao.TemplateZero.updateTableRecord", stringJson );
	}
	
	public void disableTableRecord(HashMap<String, Object> stringJson) {
		// TODO Auto-generated method stub
		sqlSession.selectOne("com.ljy.third.dao.TemplateZero.disableTableRecord", stringJson );
	}
	
	public void restoreTableRecord(HashMap<String, Object> stringJson) {
		// TODO Auto-generated method stub
		sqlSession.selectOne("com.ljy.third.dao.TemplateZero.restoreTableRecord", stringJson );
	}
	
	public void deleteTableRecord(HashMap<String, Object> stringJson) {
		// TODO Auto-generated method stub
		sqlSession.selectOne("com.ljy.third.dao.TemplateZero.deleteTableRecord", stringJson );
	}
	
	public void deleteFileRecord(HashMap<String, Object> stringJson) {
		// TODO Auto-generated method stub
		sqlSession.selectOne("com.ljy.third.dao.TemplateZero.deleteFileRecord", stringJson );
	}
	
	
}
