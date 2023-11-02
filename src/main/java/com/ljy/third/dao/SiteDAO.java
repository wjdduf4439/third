package com.ljy.third.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ljy.third.vo.FormMenuVO;
import com.ljy.third.vo.SiteMenuVO;
import com.ljy.third.vo.SysCodeVO;

@Repository("SiteDAO")
public class SiteDAO {

	@Inject //�ڹٿ��� �����ϴ� �ش� ���������Ŀ� ���� �����͸� �����ϴ� ������̼�
	private SqlSession sqlSession;
	
	public List<SiteMenuVO> selectSiteMenuList(SiteMenuVO siteMenuVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.ljy.third.dao.selectSiteMenuList", siteMenuVO );
	}

	
	public int selectSiteMenuCnt(SiteMenuVO siteMenuVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.ljy.third.dao.lookSiteMenuCnt", siteMenuVO );
	}


	public String selectSiteMenuMax(SiteMenuVO siteMenuVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.ljy.third.dao.selectSiteMenuMax", siteMenuVO );
	}

	
	public SiteMenuVO selectSiteMenuOne(SiteMenuVO siteMenuVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.ljy.third.dao.selectSiteMenuOne", siteMenuVO );
	}


	public SiteMenuVO selectSiteMenuRecent() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.ljy.third.dao.selectSiteMenuRecent");
	}


	public List<FormMenuVO> selectSiteMenuFormList() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.ljy.third.dao.selectSiteMenuFormList");
	}


	public List<SiteMenuVO> selectSiteField(SiteMenuVO siteMenuVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.ljy.third.dao.selectSiteField", siteMenuVO);
	}

	
	public void insertSiteMenu(SiteMenuVO siteMenuVO) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert("com.ljy.third.dao.insertSiteMenu", siteMenuVO);
	}


	public void insertSiteTable(SiteMenuVO siteMenuVO) {
		// TODO Auto-generated method stub
		sqlSession.update("com.ljy.third.dao.insertSiteTable", siteMenuVO);
	}

	
	public void deleteSiteMenu(SiteMenuVO siteMenuVO) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.delete("com.ljy.third.dao.deleteSiteMenu", siteMenuVO);
	}
	
	public void deleteSiteTableDelete(SiteMenuVO siteMenuVO) {
		// TODO Auto-generated method stub
		sqlSession.update("com.ljy.third.dao.deleteSiteTableDelete", siteMenuVO);
	}
	
	public void updateSiteMenu(SiteMenuVO siteMenuVO) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("com.ljy.third.dao.updateSiteMenu", siteMenuVO);
	}

	public void updateSiteField(SiteMenuVO siteMenuVO) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("com.ljy.third.dao.updateSiteField", siteMenuVO);
	}	
	
	public List<SysCodeVO> siteTemplateTypeInput(SysCodeVO sysCodeVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.ljy.third.dao.siteTemplateTypeInput", sysCodeVO);
	}
	
	public List<SysCodeVO> siteFieldInput(SysCodeVO sysCodeVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.ljy.third.dao.siteFieldInput", sysCodeVO);
	}
	
	


	
	
}
