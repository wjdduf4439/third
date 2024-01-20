package com.ljy.third.service.site;

import java.util.List;

import com.ljy.third.vo.SysCodeVO;
import com.ljy.third.vo.form.FormMenuVO;
import com.ljy.third.vo.site.SiteMenuVO;

public interface SiteMenuService {

	public List<SiteMenuVO> selectSiteMenuList(SiteMenuVO siteMenuVO) throws Exception;
	
	public int selectSiteMenuCnt(SiteMenuVO siteMenuVO) throws Exception;

	public SiteMenuVO selectSiteMenuOne(SiteMenuVO siteMenuVO) throws Exception;
	
	public SiteMenuVO selectSiteMenuRecent() throws Exception;
	
	public String selectSiteMenuMax(SiteMenuVO siteMenuVO) throws Exception;

	public List<SiteMenuVO> selectSiteField(SiteMenuVO siteMenuVO) throws Exception;
	
	public List<FormMenuVO> selectSiteMenuFormList() throws Exception;
	
	public void insertSiteMenu(SiteMenuVO siteMenuVO) throws Exception;

	public void deleteSiteMenu(SiteMenuVO siteMenuVO) throws Exception;

	public void updateSiteMenu(SiteMenuVO siteMenuVO) throws Exception;

	public void updateSiteField(SiteMenuVO siteMenuVO) throws Exception;
	
	public List<SysCodeVO> siteTemplateTypeInput(SysCodeVO sysCodeVO) throws Exception;
	
	public List<SysCodeVO> siteFieldInput(SysCodeVO sysCodeVO) throws Exception;
	
}
