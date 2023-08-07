package com.ljy.third.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ljy.third.dao.logAdminDAO;
import com.ljy.third.vo.logAdminVO;

@Service("logAdminService")
public class logAdminServiceImpl implements logAdminService {

	@Resource(name = "logAdminDAO")
	logAdminDAO mlogAdminDAO;
	
	@Override
	public List<logAdminVO> selectlogAdminList(logAdminVO mlogAdminVO) throws Exception {
		// TODO Auto-generated method stub
		return mlogAdminDAO.selectlogAdminList(mlogAdminVO);
	}

	@Override
	public int selectlogAdminCnt(logAdminVO mlogAdminVO) throws Exception {
		// TODO Auto-generated method stub
		return mlogAdminDAO.selectlogAdminCnt(mlogAdminVO);
	}

	@Override
	public String selectlogAdminMax(logAdminVO mlogAdminVO) throws Exception {
		// TODO Auto-generated method stub
		return mlogAdminDAO.selectTableRecordListMax();
	}

	@Override
	public void insertlogAdmin(logAdminVO mlogAdminVO) throws Exception {
		// TODO Auto-generated method stub
		
		try {
			String maxCode = mlogAdminDAO.selectTableRecordListMax();
			int temp = Integer.parseInt(maxCode.substring(6, maxCode.length())); temp ++;
			int tailNumber = temp;
			
			String newCode = "LOGAD_";
			int i = 1;
			
			while(temp >= 10) {
				
				temp /= 10;
				i++;
				
			}
			
			for(int j = 0; j < 14-i ; j++) { newCode += "0"; System.out.println(newCode); }
			newCode += Integer.toString(tailNumber);
			
			System.out.println(newCode);
			
			mlogAdminVO.setLogCode(newCode);
			
			
		}catch(Exception e) {
			
			mlogAdminVO.setLogCode("LOGAD_00000000000001");
			
		}
		
		mlogAdminDAO.insertlogAdmin(mlogAdminVO);
	}

}
