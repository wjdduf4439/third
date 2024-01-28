package com.ljy.third.service.form;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ljy.third.dao.form.FormDAO;
import com.ljy.third.vo.form.FormMenuVO;
import com.ljy.third.vo.site.SiteMenuVO;

import jakarta.annotation.Resource;

@Service("FormService")
public class FormServiceImpl implements FormService {

	@Resource(name = "FormDAO")
	FormDAO formDAO;
	
	@Override
	public List<FormMenuVO> selectFormMenuList(FormMenuVO fiteMenuVO) throws Exception {
		// TODO Auto-generated method stub
		return formDAO.selectFormMenuList(fiteMenuVO);
	}

	@Override
	public int selectFormMenuCnt(FormMenuVO fiteMenuVO) throws Exception {
		// TODO Auto-generated method stub
		return formDAO.selectFormMenuCnt(fiteMenuVO);
	}

	@Override
	public String selectFormMenuMax(HashMap<String, Object> stringJson) throws Exception {
		// TODO Auto-generated method stub
		return formDAO.selectFormMenuMax(stringJson);
	}

	@Override
	public FormMenuVO selectFormMenuOne(FormMenuVO fiteMenuVO) throws Exception {
		// TODO Auto-generated method stub
		return formDAO.selectFormMenuOne(fiteMenuVO);
	}

	@Override
	public List<SiteMenuVO> selectFormMenuSiteList(FormMenuVO formMenuVO) throws Exception {
		// TODO Auto-generated method stub
		return formDAO.selectFormMenuSiteList(formMenuVO);
	}

	@Override
	public void insertFormMenu(HashMap<String, Object> stringJson) throws Exception {
		// TODO Auto-generated method stub
		
		try {
			
			String maxCode = formDAO.selectFormMenuMax(stringJson);
			
			int temp = Integer.parseInt(maxCode.substring(5, maxCode.length())); temp ++;
			
			String newCode = "FORM_";
			int i = 1;
			
			while(temp > 10) {
				
				temp /= 10;
				i++;
				
			}
			
			for(int j = 0; j < 15-i ; j++) { newCode += "0"; }
			newCode += Integer.toString(temp);
			
			stringJson.put("formCode", newCode);
			
			
		}catch(Exception e) {
			
			stringJson.put("formCode", "FORM_000000000000001");
			
		}
		
		formDAO.insertFormMenu(stringJson);
	}

	@Override
	public void updateFormMenu(HashMap<String, Object> stringJson) throws Exception {
		// TODO Auto-generated method stub
		formDAO.updateFormMenu(stringJson);
	}

	@Override
	public void disableFormMenu(HashMap<String, Object> stringJson) throws Exception{
		// TODO Auto-generated method stub
		formDAO.disableFormMenu(stringJson);

	}
	
	@Override
	public void deleteFormMenu(HashMap<String, Object> stringJson) throws Exception{
		// TODO Auto-generated method stub
		formDAO.deleteFormSiteMenu(stringJson);
		formDAO.deleteFormMenu(stringJson);
	}
	
	@Override
	public void restoreFormMenu(HashMap<String, Object> stringJson) throws Exception {
		// TODO Auto-generated method stub
		formDAO.restoreFormMenu(stringJson);
	}

}
