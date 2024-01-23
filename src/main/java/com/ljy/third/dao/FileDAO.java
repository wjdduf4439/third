package com.ljy.third.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ljy.third.vo.FileVO;

@Repository("FileDAO")
public class FileDAO {
	
	@Inject //�ڹٿ��� �����ϴ� �ش� ���������Ŀ� ���� �����͸� �����ϴ� ������̼�
	private SqlSession sqlSession;
	
	public List<FileVO> selectFileMenuList(FileVO mFileVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.ljy.third.dao.FileMapper.selectFileList", mFileVO);
	}

	public int selectAtchFileCount(String fid) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.ljy.third.dao.FileMapper.selectAtchFileCount", fid);
	}

	public int selectFileSign(String atchFileId) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.ljy.third.dao.FileMapper.selectFileSign", atchFileId);
	}
	
	public String selectFileCodeMax() {
		
		return sqlSession.selectOne("com.ljy.third.dao.FileMapper.selectFileCodeMax");
		
	}
	
	public String selectFileIdMax() {
		
		return sqlSession.selectOne("com.ljy.third.dao.FileMapper.selectFileIdMax");
		
	}
	
	public void updateFileSign( FileVO mFileVO ) {
		
		sqlSession.selectOne("com.ljy.third.dao.FileMapper.updateFileSign", mFileVO);
		
	}

	public void deleteFileMenu(String code, String fid, String fsign) {
		// TODO Auto-generated method stub
		
		FileVO valueVO = new FileVO();
		
		valueVO.setCode(code);
		valueVO.setFid(fid);
		valueVO.setFsign( Integer.parseInt(fsign) );
		
		sqlSession.selectOne("com.ljy.third.dao.FileMapper.deleteFileMenu", valueVO);
	}
	
	public void deleteFileArray(String[] mDelArray) {
		
		sqlSession.selectOne("com.ljy.third.dao.FileMapper.deleteFileArray", mDelArray);
	}

}
