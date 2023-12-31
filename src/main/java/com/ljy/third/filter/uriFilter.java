package com.ljy.third.filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class uriFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    	System.out.print("Load filter - "); System.out.println("Start URI checking");
        
    }
    

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        System.out.println("doFilter Request URI: {} " + req.getRequestURL());        
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("End URI checking");
    }
}
