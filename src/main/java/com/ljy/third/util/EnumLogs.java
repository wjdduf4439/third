package com.ljy.third.util;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

public class EnumLogs {

	public EnumLogs(HttpServletRequest request) {
		
		//Enumeration 클래스가 Enumeration형의 데이터를 request로 받아들여 자동으로 변환을 해준다.
		
		System.out.println("/////// ---- enum class log ------------------------------------------------");
		
		 Enumeration params = request.getParameterNames(); while
		 (params.hasMoreElements()){ String name = (String)params.nextElement();
		 System.out.println(name + " : " +request.getParameter(name)); }
		 
		 
		 System.out.println("[ END ] ---- enum class log ------------------------------------------------");
		 
	}
	
}
