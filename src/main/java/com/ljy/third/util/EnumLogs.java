package com.ljy.third.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class EnumLogs {

	private Map<String, String> dto = new HashMap<String, String>();
	
	public EnumLogs(HttpServletRequest request) {
		
		//Enumeration 클래스가 Enumeration형의 데이터를 request로 받아들여 자동으로 변환을 해준다.
		
		System.out.println("/////// ---- enum class log ------------------------------------------------");
		
		 Enumeration params = request.getParameterNames(); 
		 while (params.hasMoreElements()){ 
			 String name = (String)params.nextElement();
			 System.out.println(name + " : " +request.getParameter(name));
		}
		 
		 
		 System.out.println("[ END ] ---- enum class log ------------------------------------------------");
		 
	}
	
	public EnumLogs(HttpServletRequest request, Map<String, String> mdto) {
		
		//Enumeration 클래스가 Enumeration형의 데이터를 request로 받아들여 자동으로 변환을 해준다.
		
		System.out.println("/////// ---- enum class log with dto ------------------------------------------------");
		
		 Enumeration params = request.getParameterNames(); 
		 while (params.hasMoreElements()){ 
			 String name = (String)params.nextElement();
			 System.out.println(name + " : " + request.getParameter(name));
			 mdto.put(name, request.getParameter(name));
		}
		 
		this.dto = mdto;
		 
		System.out.println("[ END ] ---- enum class log with dto ------------------------------------------------");
		 
	}
	
	
	
	
}
