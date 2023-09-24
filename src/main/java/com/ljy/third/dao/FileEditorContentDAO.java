package com.ljy.third.dao;


import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ljy.third.vo.FileEditorContentVO;

@Repository("FileEditorContentDAO")
public class FileEditorContentDAO {
	
	@Inject //�ڹٿ��� �����ϴ� �ش� ���������Ŀ� ���� �����͸� �����ϴ� ������̼�
	private SqlSession sqlSession;
	
	public String selectTableRecordListMax(FileEditorContentVO mFileEditorContentVO) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.ljy.third.dao.fileEditorContentMapper.fileEditorContentListMax", mFileEditorContentVO );
	}

	public void insertContentFile(FileEditorContentVO mFileEditorContentVO) {
		// TODO Auto-generated method stub
		sqlSession.selectOne("com.ljy.third.dao.fileEditorContentMapper.insertContentFileDAO", mFileEditorContentVO);
	}
	
	public void updateEditorContentFid(FileEditorContentVO mFileEditorContentVO) {
		// TODO Auto-generated method stub
		sqlSession.selectOne("com.ljy.third.dao.fileEditorContentMapper.updateEditorContentFid", mFileEditorContentVO);
	}

}
