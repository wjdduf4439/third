package com.ljy.third.interceptor;


import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ljy.third.util.ValidatingValue;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class CommonInterceptor implements  HandlerInterceptor { 

	ValidatingValue validatingValue = new ValidatingValue();
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		
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
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
		
		System.out.println("???");
	}

	@Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub
		System.out.println("???");
    	
    }
	
}
