package com.ljy.third.dao.site;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ljy.third.vo.FormMenuVO;
import com.ljy.third.vo.SysCodeVO;
import com.ljy.third.vo.site.SiteMenuVO;

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
	
	public int selectSiteMenuCnt(HashMap<String, Object> stringJson) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.ljy.third.dao.lookSiteMenuCnt", stringJson );
	}	


	public String selectSiteMenuMax(SiteMenuVO siteMenuVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.ljy.third.dao.selectSiteMenuMax", siteMenuVO );
	}
	
	public String selectSiteMenuMax(HashMap<String, Object> stringJson) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.ljy.third.dao.selectSiteMenuMax", stringJson );
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
	
	public List<SiteMenuVO> selectSiteField(HashMap<String, Object> stringJson) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.ljy.third.dao.selectSiteField", stringJson);
	}

	
	public void insertSiteMenu(HashMap<String, Object> stringJson) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert("com.ljy.third.dao.insertSiteMenu", stringJson);
	}


	public void insertSiteTable(HashMap<String, Object> stringJson) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("com.ljy.third.dao.insertSiteTable", stringJson);
	}
	
	public void updateSiteMenu(HashMap<String, Object> stringJson) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("com.ljy.third.dao.updateSiteMenu", stringJson);
	}

	public void disableSiteMenu(HashMap<String, Object> stringJson) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("com.ljy.third.dao.disableSiteMenu", stringJson);
	}
	
	public void deleteSiteMenu(HashMap<String, Object> stringJson) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.delete("com.ljy.third.dao.deleteSiteMenu", stringJson);
	}
	
	public void deleteSiteTableDelete(HashMap<String, Object> stringJson) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("com.ljy.third.dao.deleteSiteTableDelete", stringJson);
	}
	
	public void restoreSiteMenu(HashMap<String, Object> stringJson) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.delete("com.ljy.third.dao.restoreSiteMenu", stringJson);
	}

	public void updateSiteField(SiteMenuVO siteMenuVO) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("com.ljy.third.dao.updateSiteField", siteMenuVO);
	}	
	
	public void updateSiteField(HashMap<String, Object> stringJson) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("com.ljy.third.dao.updateSiteField", stringJson);
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
