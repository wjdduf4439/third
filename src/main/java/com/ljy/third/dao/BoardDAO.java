package com.ljy.third.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.ljy.third.vo.BoardVO;
import com.ljy.third.vo.FileVO;

@Repository("BoardDAO")
public class BoardDAO {
	
	@Inject //�ڹٿ��� �����ϴ� �ش� ���������Ŀ� ���� �����͸� �����ϴ� ������̼�
	private SqlSession sqlSession;

	public List<BoardVO> listBoardDAO(BoardVO mboardVO){
		return  sqlSession.selectList("com.ljy.third.dao.memberMapper.selectBoardDAO", mboardVO);
	}

	public BoardVO lookBoardDAO(BoardVO mboardVO) {
		return sqlSession.selectOne("com.ljy.third.dao.memberMapper.lookBoardDAO", mboardVO);
	}
	
	public List<FileVO> lookFileBoardDAO(BoardVO mboardVO) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.ljy.third.dao.memberMapper.lookFileBoardDAO", mboardVO);
	}
	
	public FileVO lookOneFileBoardDAO(BoardVO mboardVO) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.ljy.third.dao.memberMapper.lookOneFileBoardDAO", mboardVO);
	}
	
	public FileVO lookOneFileBoardDAO(String code) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.ljy.third.dao.memberMapper.lookOneFileBoardDAO2", code);
	}
	

	public BoardVO oneBoardDAO(BoardVO mboardVO){
		return sqlSession.selectOne("com.ljy.third.dao.memberMapper.oneBoardDAO", mboardVO);
	}
	
	public int countBoardDAO() {
		// TODO Auto-generated method stub
		return  sqlSession.selectOne("com.ljy.third.dao.memberMapper.countBoardDAO"); // int���� �̾ƿ����� �Ҷ��� sql dao �Լ�
	}

	public void insertBoardDAO(BoardVO mboardVO) {
		sqlSession.insert("com.ljy.third.dao.memberMapper.insertBoardDAO", mboardVO);
	}

	public void insertFileBoardDAO(List<FileVO> fileVO) {
		
		sqlSession.insert("com.ljy.third.dao.memberMapper.insertfileBoardDAO", fileVO);
		
	}
	
	public void deleteBoardDAO(BoardVO mboardVO) {
		sqlSession.delete("com.ljy.third.dao.memberMapper.deleteBoardDAO", mboardVO);
	}

	public void updatehitBoardDAO(BoardVO mboardVO) {
		sqlSession.update("com.ljy.third.dao.memberMapper.updatehitBoardDAO", mboardVO);
		
	}



	
}
 