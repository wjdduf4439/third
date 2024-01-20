package com.ljy.third.service.site;

import java.util.HashMap;
import java.util.List;

import com.ljy.third.vo.SysCodeVO;
import com.ljy.third.vo.form.FormMenuVO;
import com.ljy.third.vo.site.SiteMenuVO;

public interface SiteService {

	public List<SiteMenuVO> selectSiteMenuList(SiteMenuVO siteMenuVO) throws Exception;
	
	public int selectSiteMenuCnt(SiteMenuVO siteMenuVO) throws Exception;
	
	public int selectSiteMenuCnt(HashMap<String, Object> stringJson) throws Exception;

	public SiteMenuVO selectSiteMenuOne(SiteMenuVO siteMenuVO) throws Exception;
	
	public SiteMenuVO selectSiteMenuRecent() throws Exception;	
	
	public String selectSiteMenuMax(HashMap<String, Object> stringJson) throws Exception;
	
	public List<SiteMenuVO> selectSiteField(HashMap<String, Object> stringJson) throws Exception;
	
	public List<FormMenuVO> selectSiteMenuFormList() throws Exception;
	
	public void insertSiteMenu(HashMap<String, Object> stringJson) throws Exception;
	
	public void updateSiteMenu(HashMap<String, Object> stringJson) throws Exception;
	
	public void updateSiteField(HashMap<String, Object> stringJson) throws Exception;

	public void disableSiteMenu(HashMap<String, Object> stringJson) throws Exception;
	
	public void deleteSiteMenu(HashMap<String, Object> stringJson) throws Exception;
	
	public void restoreSiteMenu(HashMap<String, Object> stringJson) throws Exception;
	
	public List<SysCodeVO> siteTemplateTypeInput(SysCodeVO sysCodeVO) throws Exception;
	
	public List<SysCodeVO> siteFieldInput(SysCodeVO sysCodeVO) throws Exception;
	
}
