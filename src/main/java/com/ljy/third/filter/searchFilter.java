package com.ljy.third.filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.util.HtmlUtils;

@Component
public class searchFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    	System.out.print("Load filter - "); System.out.println("Start search keyword Filter");
        
    }
    

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        System.out.println("search vo 받아옴 ");  
       
        //writer input 필드에서 검색어를 가져오고 필터 작업을 실시함
        
        /*
        String msearchWrd = req.getParameter("searchWrd");
        if(null != msearchWrd) { 
        	System.out.println("search 검색어 " + msearchWrd); 
        	msearchWrd = XssFilter(msearchWrd);
        	System.out.println("변환된 search 검색어 " + msearchWrd);
        }
        */ 
        
        //변환시킨 searchWrd를 다른 attribute로 넣어서 둔다
        //문제는 요청에 따라 맞춰야 하는 vo는 여러개인데
        //1. 그냥 voobj로 가져와서 setsearchwrd로 하고 보내기 -> 나중에 보낼때 문제가 생김
        //2. voobj에 변환하는 기능을 넣기 -> 이걸로 할까?, voobj가 무거워지지만 어쩔수없음
        
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("End URI checking");
    }
    
    public String XssFilter(String msearchWrd){ String resSearchWrd = HtmlUtils.htmlEscape(msearchWrd);  return resSearchWrd; }
    
    
}