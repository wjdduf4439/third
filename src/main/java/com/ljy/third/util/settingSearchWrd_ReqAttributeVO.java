package com.ljy.third.util;

import com.ljy.third.vo.TemplateOneVO;
import com.ljy.third.vo.TemplateZeroVO;

public class settingSearchWrd_ReqAttributeVO {

	private TemplateZeroVO gtemplateZeroVO = new TemplateZeroVO();
	private TemplateOneVO gtemplateOneVO = new TemplateOneVO();
	
	public TemplateZeroVO setting_TemplateZeroVO(TemplateZeroVO mtemplateZeroVO, String msearchWrd) {
		
		TemplateZeroVO restemplateZeroVO = mtemplateZeroVO;
		restemplateZeroVO.setSearchWrd(null);
		
		return restemplateZeroVO;
		
	}
	
	
	public TemplateOneVO setting_TemplateOneVO(TemplateOneVO mtemplateOneVO, String msearchWrd) {
		
		TemplateOneVO restemplateOneVO = mtemplateOneVO;
		restemplateOneVO.setSearchWrd(null);
		
		return restemplateOneVO;
		
	}
	
}
