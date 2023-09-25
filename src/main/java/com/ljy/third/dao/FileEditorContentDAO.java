package com.ljy.third.dao;


import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ljy.third.vo.FileEditorContentVO;

@Repository("FileEditorContentDAO")
public class FileEditorContentDAO {
	
	@Inject //�ڹٿ��� �����ϴ� �ش� ���������Ŀ� ���� �����͸� �����ϴ� ������̼�
	private SqlSession sqlSession;
	
	public List<FileEditorContentVO> selectTableRecordList(FileEditorContentVO mFileEditorContentVO) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.ljy.third.dao.fileEditorContentMapper.selectTableRecordList", mFileEditorContentVO );
	}
	
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
	
	public void deleteEditorContent_Fid(FileEditorContentVO mFileEditorContentVO) {
		// TODO Auto-generated method stub
		sqlSession.selectOne("com.ljy.third.dao.fileEditorContentMapper.deleteEditorContent_Fid", mFileEditorContentVO);
	}

}
