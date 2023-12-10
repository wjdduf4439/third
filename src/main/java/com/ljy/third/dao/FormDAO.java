package com.ljy.third.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ljy.third.vo.FormMenuVO;
import com.ljy.third.vo.site.SiteMenuVO;

@Repository("FormDAO")
public class FormDAO {

	@Inject //�ڹٿ��� �����ϴ� �ش� ���������Ŀ� ���� �����͸� �����ϴ� ������̼�
	private SqlSession sqlSession;
	
	public List<FormMenuVO> selectFormMenuList(FormMenuVO formMenuVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.ljy.third.dao.selectFormMenuList", formMenuVO );
	}

	
	public int selectFormMenuCnt(FormMenuVO formMenuVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.ljy.third.dao.lookFormMenuCnt", formMenuVO );
	}


	public String selectFormMenuMax(FormMenuVO formMenuVO) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.ljy.third.dao.selectFormMenuMax", formMenuVO );
	}

	
	public FormMenuVO selectFormMenuOne(FormMenuVO formMenuVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.ljy.third.dao.lookFormMenuOne", formMenuVO );
	}


	public List<SiteMenuVO> selectFormMenuSiteList(FormMenuVO formMenuVO) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.ljy.third.dao.selectFormMenuSiteList", formMenuVO);
	}

	
	public void insertFormMenu(FormMenuVO formMenuVO) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert("com.ljy.third.dao.insertFormMenu", formMenuVO);
	}

	
	public void deleteFormMenu(FormMenuVO formMenuVO) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.delete("com.ljy.third.dao.deleteFormMenu", formMenuVO);
	}
	
	public void deleteFormSiteMenu(FormMenuVO formMenuVO) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.delete("com.ljy.third.dao.deleteFormSiteMenu", formMenuVO);
	}

	
	public void updateFormMenu(FormMenuVO formMenuVO) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("com.ljy.third.dao.updateFormMenu", formMenuVO);
	}
	
}
