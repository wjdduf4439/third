package com.ljy.third.aop;


import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.ljy.third.vo.loginHomeVO;

//@Component
//@Aspect
public class aopExe {

	// 시작 전에 실행될 AOP 메소드
	@Before("within(com.ljy.third.controller..*)")
	public void beforeMethod() {
		System.out.println("메소드 시작전~!");
	}

	// 종료 후에 실행될 AOP 메소드
	@After("within(com.ljy.third.controller..*)")
	public void afterMethod() {
		System.out.println("메소드 종료~!");
	}
	
	@After("within(com.ljy.third.controller..*)")
	public void SpringAop( JoinPoint jpt)  {

		Signature sig = jpt.getSignature();
		System.out.println("실행 타입 : " + sig.getDeclaringType());
		System.out.println("실행 메소드 : " + sig.getName());
		
		
	}
	

	
	
}
