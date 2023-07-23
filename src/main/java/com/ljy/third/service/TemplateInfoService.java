package com.ljy.third.service;
import com.ljy.third.vo.TemplateInfoVO;
import com.ljy.third.vo.TemplateViewInfoVO;

public interface TemplateInfoService {
	
	public TemplateInfoVO selectTableName(TemplateInfoVO templateInfoVO) throws Exception;
	
	public TemplateViewInfoVO selectTableName(TemplateViewInfoVO templateViewInfoVO) throws Exception;
	
}
