package com.ljy.third.dao.site;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ljy.third.vo.SysCodeVO;
import com.ljy.third.vo.form.FormMenuVO;
import com.ljy.third.vo.site.SiteMenuVO;

@Repository("SiteDAO")
public class SiteDAO {

	@Inject //�ڹٿ��� �����ϴ� �ش� ���������Ŀ� ���� �����͸� �����ϴ� ������̼�
	private SqlSession sqlSession;
	
	public List<SiteMenuVO> selectSiteMenuList(SiteMenuVO siteMenuVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.ljy.third.dao.site.selectSiteMenuList", siteMenuVO );
	}

	
	public int selectSiteMenuCnt(SiteMenuVO siteMenuVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.ljy.third.dao.site.lookSiteMenuCnt", siteMenuVO );
	}
	
	public int selectSiteMenuCnt(HashMap<String, Object> stringJson) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.ljy.third.dao.site.lookSiteMenuCnt", stringJson );
	}	


	public String selectSiteMenuMax(SiteMenuVO siteMenuVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.ljy.third.dao.site.selectSiteMenuMax", siteMenuVO );
	}
	
	public String selectSiteMenuMax(HashMap<String, Object> stringJson) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.ljy.third.dao.site.selectSiteMenuMax", stringJson );
	}

	
	public SiteMenuVO selectSiteMenuOne(SiteMenuVO siteMenuVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.ljy.third.dao.site.selectSiteMenuOne", siteMenuVO );
	}


	public SiteMenuVO selectSiteMenuRecent() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.ljy.third.dao.site.selectSiteMenuRecent");
	}


	public List<FormMenuVO> selectSiteMenuFormList() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.ljy.third.dao.site.selectSiteMenuFormList");
	}


	public List<SiteMenuVO> selectSiteField(SiteMenuVO siteMenuVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.ljy.third.dao.site.selectSiteField", siteMenuVO);
	}
	
	public List<SiteMenuVO> selectSiteField(HashMap<String, Object> stringJson) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.ljy.third.dao.site.selectSiteField", stringJson);
	}

	
	public void insertSiteMenu(HashMap<String, Object> stringJson) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert("com.ljy.third.dao.site.insertSiteMenu", stringJson);
	}


	public void insertSiteTable(HashMap<String, Object> stringJson) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("com.ljy.third.dao.site.insertSiteTable", stringJson);
	}
	
	public void updateSiteMenu(HashMap<String, Object> stringJson) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("com.ljy.third.dao.site.updateSiteMenu", stringJson);
	}

	public void disableSiteMenu(HashMap<String, Object> stringJson) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("com.ljy.third.dao.site.disableSiteMenu", stringJson);
	}
	
	public void deleteSiteMenu(HashMap<String, Object> stringJson) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.delete("com.ljy.third.dao.site.deleteSiteMenu", stringJson);
	}
	
	public void deleteSiteTableDelete(HashMap<String, Object> stringJson) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("com.ljy.third.dao.site.deleteSiteTableDelete", stringJson);
	}
	
	public void restoreSiteMenu(HashMap<String, Object> stringJson) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.delete("com.ljy.third.dao.site.restoreSiteMenu", stringJson);
	}

	public void updateSiteField(SiteMenuVO siteMenuVO) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("com.ljy.third.dao.site.updateSiteField", siteMenuVO);
	}	
	
	public void updateSiteField(HashMap<String, Object> stringJson) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("com.ljy.third.dao.site.updateSiteField", stringJson);
	}	
	
	public List<SysCodeVO> siteTemplateTypeInput(SysCodeVO sysCodeVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.ljy.third.dao.site.siteTemplateTypeInput", sysCodeVO);
	}
	
	public List<SysCodeVO> siteFieldInput(SysCodeVO sysCodeVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.ljy.third.dao.site.siteFieldInput", sysCodeVO);
	}
	
	


	
	
}
