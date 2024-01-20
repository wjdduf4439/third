package com.ljy.third.service.form;

import java.util.HashMap;
import java.util.List;

import com.ljy.third.vo.form.FormMenuVO;
import com.ljy.third.vo.site.SiteMenuVO;

public interface FormService {

	public List<FormMenuVO> selectFormMenuList(FormMenuVO formMenuVO) throws Exception;
	
	public int selectFormMenuCnt(FormMenuVO formMenuVO) throws Exception;

	public FormMenuVO selectFormMenuOne(FormMenuVO formMenuVO) throws Exception;
	
	public String selectFormMenuMax(HashMap<String, Object> stringJson) throws Exception;
	
	public List<SiteMenuVO> selectFormMenuSiteList(FormMenuVO formMenuVO) throws Exception;
	
	public void insertFormMenu(HashMap<String, Object> stringJson) throws Exception;

	public void updateFormMenu(HashMap<String, Object> stringJson) throws Exception;

	public void deleteFormMenu(HashMap<String, Object> stringJson) throws Exception;
	
	public void disableFormMenu(HashMap<String, Object> stringJson) throws Exception;

	public void restoreFormMenu(HashMap<String, Object> stringJson) throws Exception;
	
}
