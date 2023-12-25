package com.ljy.third.service.site;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ljy.third.dao.site.SiteDAO;
import com.ljy.third.vo.FormMenuVO;
import com.ljy.third.vo.SysCodeVO;
import com.ljy.third.vo.site.SiteMenuVO;

@Service("SiteService")
public class SiteServiceImpl implements SiteService {

	@Resource(name = "SiteDAO")
	SiteDAO siteDAO;
	
	@Override
	public List<SiteMenuVO> selectSiteMenuList(SiteMenuVO siteMenuVO) throws Exception {
		// TODO Auto-generated method stub
		return siteDAO.selectSiteMenuList(siteMenuVO);
	}

	@Override
	public int selectSiteMenuCnt(SiteMenuVO siteMenuVO) throws Exception {
		// TODO Auto-generated method stub
		return siteDAO.selectSiteMenuCnt(siteMenuVO);
	}
	
	@Override
	public int selectSiteMenuCnt(HashMap<String, Object> stringJson) throws Exception{
		// TODO Auto-generated method stub
		return siteDAO.selectSiteMenuCnt(stringJson);
	}
	
	@Override
	public String selectSiteMenuMax(HashMap<String, Object> stringJson) throws Exception {
		// TODO Auto-generated method stub
		return siteDAO.selectSiteMenuMax(stringJson);
	}

	@Override
	public SiteMenuVO selectSiteMenuOne(SiteMenuVO siteMenuVO) throws Exception {
		// TODO Auto-generated method stub
		return siteDAO.selectSiteMenuOne(siteMenuVO);
	}

	@Override
	public SiteMenuVO selectSiteMenuRecent() throws Exception {
		// TODO Auto-generated method stub
		return siteDAO.selectSiteMenuRecent();
	}

	@Override
	public List<FormMenuVO> selectSiteMenuFormList() throws Exception {
		// TODO Auto-generated method stub
		return siteDAO.selectSiteMenuFormList();
	}
	
	@Override
	public List<SiteMenuVO> selectSiteField(HashMap<String, Object> stringJson) throws Exception{
		// TODO Auto-generated method stub
		return siteDAO.selectSiteField(stringJson);
	}

	@Override
	public void insertSiteMenu(HashMap<String, Object> stringJson) throws Exception{
		// TODO Auto-generated method stub
		
		try {
			
			String maxCode = siteDAO.selectSiteMenuMax(stringJson);
			
			int temp = Integer.parseInt(maxCode.substring(5, maxCode.length())); temp ++;
			
			String newCode = "SITE_";
			int i = 1;
			
			while(temp > 10) {
				
				temp /= 10;
				i++;
				
			}
			
			for(int j = 0; j < 15-i ; j++) { newCode += "0"; }
			newCode += Integer.toString(temp);
			
			stringJson.put("siteCode", newCode);
			
			
		}catch(Exception e) {
			
			stringJson.put("siteCode", "SITE_000000000000001");
			
		}
		
		siteDAO.insertSiteMenu(stringJson);
		siteDAO.insertSiteTable(stringJson);
	}

	@Override
	public void updateSiteMenu(HashMap<String, Object> stringJson) throws Exception{
		// TODO Auto-generated method stub
		siteDAO.updateSiteMenu(stringJson);
	}

	@Override
	public void disableSiteMenu(HashMap<String, Object> stringJson) throws Exception{
		// TODO Auto-generated method stub
		siteDAO.disableSiteMenu(stringJson);

	}
	
	@Override
	public void deleteSiteMenu(HashMap<String, Object> stringJson) throws Exception {
		// TODO Auto-generated method stub
		siteDAO.deleteSiteMenu(stringJson);
		siteDAO.deleteSiteTableDelete(stringJson);
	}
	
	@Override
	public void restoreSiteMenu(HashMap<String, Object> stringJson) throws Exception {
		// TODO Auto-generated method stub
		siteDAO.restoreSiteMenu(stringJson);
	}
	
	@Override
	public void updateSiteField(HashMap<String, Object> stringJson) throws Exception {
		// TODO Auto-generated method stub
		siteDAO.updateSiteField(stringJson);
	}
	
	@Override
	public List<SysCodeVO> siteTemplateTypeInput(SysCodeVO sysCodeVO) throws Exception {
		// TODO Auto-generated method stub
		return siteDAO.siteTemplateTypeInput(sysCodeVO);
	}

	@Override
	public List<SysCodeVO> siteFieldInput(SysCodeVO sysCodeVO) throws Exception {
		// TODO Auto-generated method stub
		return siteDAO.siteFieldInput(sysCodeVO);
	}

}
