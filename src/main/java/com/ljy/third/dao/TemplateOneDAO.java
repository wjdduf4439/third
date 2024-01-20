package com.ljy.third.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ljy.third.vo.TemplateInfoVO;
import com.ljy.third.vo.TemplateOneVO;
import com.ljy.third.vo.templateZero.TemplateZeroVO;

@Repository("TemplateOneDAO")
public class TemplateOneDAO {
	
	@Inject //�ڹٿ��� �����ϴ� �ش� ���������Ŀ� ���� �����͸� �����ϴ� ������̼�
	private SqlSession sqlSession;

	public List<TemplateInfoVO> selectTableFieldList(TemplateInfoVO templateInfoVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.ljy.third.dao.TemplateOne.selectTableFieldList", templateInfoVO );
	}

	public List<TemplateOneVO> selectTableRecordList(TemplateInfoVO templateInfoVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.ljy.third.dao.TemplateOne.selectTableRecordList", templateInfoVO );
	}
	
	public int selectTableRecordListCount(TemplateInfoVO templateInfoVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.ljy.third.dao.TemplateOne.selectTableRecordListCount", templateInfoVO );
	}
	
	public int selectTableRecordListCount(TemplateOneVO templateOneVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.ljy.third.dao.TemplateOne.selectTableRecordListCount", templateOneVO );
	}

	public String selectTableAtchFileId(TemplateOneVO templateOneVO) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.ljy.third.dao.TemplateOne.selectTableAtchFileId", templateOneVO );
	}
	
	public String maxTableAtchFileId(TemplateZeroVO templateZeroVO) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.ljy.third.dao.TemplateOne.maxTableAtchFileId", templateZeroVO ) == null? "0":sqlSession.selectOne("com.ljy.third.dao.TemplateOne.maxTableAtchFileId", templateZeroVO );
	}
	
	public String countTableAtchFileId(TemplateOneVO templateOneVO) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.ljy.third.dao.TemplateOne.countTableAtchFileId", templateOneVO );
	}

	public String selectTableRecordListMax(TemplateOneVO templateOneVO) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.ljy.third.dao.TemplateOne.selectTableRecordListMax", templateOneVO );
	}
	
	public String selectTableFileListMax(TemplateOneVO templateOneVO) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.ljy.third.dao.TemplateOne.selectTableFileListMax", templateOneVO );
	}

	public TemplateOneVO selectTableRecordOne(TemplateOneVO templateOneVO) throws Exception {
		return sqlSession.selectOne("com.ljy.third.dao.TemplateOne.selectTableRecordOne", templateOneVO );
	}

	public TemplateOneVO selectTableRecordContext(TemplateOneVO templateOneVO) {
		return sqlSession.selectOne("com.ljy.third.dao.TemplateOne.selectTableRecordContext", templateOneVO );
	}

	public TemplateOneVO selectTableRecordRecent(TemplateOneVO templateOneVO) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.ljy.third.dao.TemplateOne.selectTableRecordRecent", templateOneVO);
	}

	public void insertTableRecord(TemplateOneVO templateOneVO) {
		// TODO Auto-generated method stub
		sqlSession.selectOne("com.ljy.third.dao.TemplateOne.insertTableRecord", templateOneVO );
	}
	
	public void updateTableRecord(TemplateOneVO templateOneVO) {
		// TODO Auto-generated method stub
		sqlSession.selectOne("com.ljy.third.dao.TemplateOne.updateTableRecord", templateOneVO );
	}
	
	public void deleteTableRecord(TemplateOneVO templateOneVO) {
		// TODO Auto-generated method stub
		sqlSession.selectOne("com.ljy.third.dao.TemplateOne.deleteTableRecord", templateOneVO );
	}
	
	public void deleteFileRecord(TemplateOneVO templateOneVO) {
		// TODO Auto-generated method stub
		
		System.out.println("delete할 fid = " + templateOneVO.getAtchFileId());
		
		sqlSession.selectOne("com.ljy.third.dao.TemplateOne.deleteFileRecord", templateOneVO );
	}
	
	
}
