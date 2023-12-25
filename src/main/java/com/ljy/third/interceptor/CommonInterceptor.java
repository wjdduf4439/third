package com.ljy.third.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ljy.third.util.EnumLogs;
import com.ljy.third.util.ValidatingValue;


@Component
public class CommonInterceptor  implements HandlerInterceptor { 

	ValidatingValue validatingValue = new ValidatingValue();
	
	@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		//c:import 태그 구문으로 접근하면 해당 요청이 null로 뜬다
		
		//System.out.println("CommonInterceptor 접근 = 요청명 : " + request.getContextPath());		
		/*
		System.out.println("CommonInterceptor 접근 = getHeader(\"referer\") : " + request.getHeader("referer"));
		System.out.println("CommonInterceptor 접근 = request.getPathInfo() : " + request.getPathInfo());
		System.out.println("CommonInterceptor 접근 = request.getRequestURI() : " + request.getRequestURI());
		System.out.println("CommonInterceptor 접근 = request.getQueryString() : " + request.getQueryString());
		*/
		
		//c:import 태그 구문으로 접근하면 해당 요청이 jsp로 뜬다, 해당 요청을 비활성화 하기
		if( validatingValue.validatingStringIndexOf( request.getRequestURI(), new String[]{".jsp"}) > -1 ) {
			System.out.println("통과된 요청 : " + request.getRequestURI());
			return true;
		}
		
		//EnumLogs e =new EnumLogs(request);
		System.out.println("ajax 요청여부 : " + request.getHeader("AJAX"));
			
			
		
		return true;
	}

	
 
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub
    	
    }
	
}
