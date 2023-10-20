package com.ljy.third.util;

import org.springframework.web.method.HandlerMethod;

public class HandlerMethodLogs {

	
	//interceptor의 object handler을 받아서 사용하는 로깅 메소드
	public HandlerMethodLogs(Object handler) {
		
		// @RequestMapping : HandleMethod
	    // 정적 리소소 : ResourceHttpRequestHandler
	    if (handler instanceof HandlerMethod) {
	      HandlerMethod handlerMethod = (HandlerMethod) handler;// 호출한 컨트롤러 메서드의 모든 정보가 포함되어 있음.
	      //HandlerMethod를 검색해서 매핑 원리 파악하기
	      //TemplateInfoVO anno = handlerMethod.get(TemplateInfoVO.class);\
	      
	    }
	    
	    //controller의 주소를 가져옴
	    System.out.println("REQUEST HANDLER : " + handler);
	    System.out.println("REQUEST GETCLASS : " + handler.getClass());
	    
	    /*
	    System.out.println("TemplateInfoController.class : " + TemplateInfoController.class.toString());
	    System.out.println("TemplateInfoController.class : " + newTemplateInfoController.toString());
		*/
		
	}
	
}
