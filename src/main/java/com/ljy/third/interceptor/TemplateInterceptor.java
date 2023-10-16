package com.ljy.third.interceptor;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;


/*
	servletConfig를 사용하여 해당 인터셉터를 등록해주어야 한다.

	- preHandle : 클라이언트의 요청을 컨트롤러에 전달하기 전에 호출된다. 여기서 false를 리턴하면 다음 내용(Controller)을 실행하지 않는다.
	- postHandle : 클라이언트의 요청을 처리한 뒤에 호출된다. 컨트롤러에서 예외가 발생되면 실행되지 않는다.
	- afterCompletion : 클라이언트 요청을 마치고 클라이언트에서 뷰를 통해 응답을 전송한뒤 실행이 된다. 뷰를 생성할 때에 예외가 발생할 경우에도 실행이 된다.
 
*/

@Configuration
public class TemplateInterceptor implements HandlerInterceptor { 

		@Override
   		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
			System.out.println("------------------------------------------ Interceptor preHandle 접속 --------------------------------------------");
			System.out.println("request.getRequestURI() : " + request.getRequestURI());
			
			System.out.println("request siteCode_json : " + request.getAttribute("siteCode_json"));
			
			//Enumeration 클래스가 Enumeration형의 데이터를 request로 받아들여 자동으로 변환을 해준다.
			Enumeration params = request.getParameterNames();
			while (params.hasMoreElements()){
			    String name = (String)params.nextElement();
			    System.out.println(name + " : " +request.getParameter(name));
			}
			
			System.out.println("------------------------------------------ Interceptor preHandle 종료 --------------------------------------------");
			return true;
		}

		
	 
	    @Override
	    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
	            throws Exception {
	        // TODO Auto-generated method stub
	    	
	    }
	        
	
}
