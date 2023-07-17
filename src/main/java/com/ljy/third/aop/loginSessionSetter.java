package com.ljy.third.aop;


import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ljy.third.vo.loginHomeVO;

@Component
@Aspect
public class loginSessionSetter {

	//@After("execution(* com.ljy.third.controller.loginHomeController.accLoginHome(..))")
	
	public void SpringAop(JoinPoint jpt)  {

		//잠시 파라미터의 이름을 담을 변수
		 String parameterName;

		 
		//joinpoint에서 담긴 request에서 vo를 받아 담을 object 변수
		 Object[] parameterValues = jpt.getArgs();
		 loginHomeVO mloginHomeVO = new loginHomeVO(); 
		
		//세션작업할 세션 생성 
		HttpServletRequest request = null;
		 
		Signature sig = jpt.getSignature();
		System.out.println("로그인 후처리 세션 접속");
		
		MethodSignature signature = (MethodSignature) jpt.getSignature();
		Method method = signature.getMethod();
		
		//어노테이션에서 지정한 메소드에서 httprequest를 받아 담을 object 변수 		
		HttpSession session = ((ServletRequestAttributes)(RequestContextHolder.currentRequestAttributes())).getRequest().getSession();
		//System.out.println("세션에 등록된 id의 값 : " + session.getAttribute("id").toString() );
		
		//파라미터의 값을 순차적으로 조회해야 함
		for (int i = 0; i < method.getParameters().length; i++) {
            parameterName = method.getParameters()[i].getName();
            System.out.println("전달된 파라미터의 이름 : " + parameterName);
            
            //accLoginHome메소드를 통해서 넘어왔을 테니깐 mloginHomeVO값을 받아서 id값을 찾는다
            if(parameterName.equals("mloginHomeVO")) { 
            	
                	mloginHomeVO = (loginHomeVO) parameterValues[i];
                	//System.out.println("method 메소드 - parameterValues 배열을 통해서 받아낸 id값 : " + mloginHomeVO.getId());
                	
            }
            
        }
		
		//session.setAttribute("sessionPass", "aop passed");
		
		/*
			if( mloginHomeVO.getId().equals( session.getAttribute("id").toString() ) == false ) {
				
				//sessionPass를 block으로 전환
				session.setAttribute("sessionPass", "block");
				//return "failLogin";
				
			}
		*/

		
	}
	
	
	@Before("within(com.ljy.third.controller..*)")
	public void sessionCreate(JoinPoint jpt) throws Exception {
		
		HttpSession session = ((ServletRequestAttributes)(RequestContextHolder.currentRequestAttributes())).getRequest().getSession(true);
		if(null == session) {
			
			
			
		}
		
	}
	
	@After("execution(* com.ljy.third.controller..*(..))")
	public void sessionPassCheck(JoinPoint jpt) throws Exception {
		
		HttpSession session = ((ServletRequestAttributes)(RequestContextHolder.currentRequestAttributes())).getRequest().getSession(true);
		if( null == session) {
			
			System.out.println("세션 없음");
			
		}else {
			
			/*
			System.out.println("세션 있음");
			System.out.println("sessionPass" + session.getAttribute("sessionPass") );
			*/
			
			//세션 유지 후 그대로 진행
			if("pass" != session.getAttribute("sessionPass")) {
				
				//header에 세션을 새로 만들게 시키고 loginhome로 되돌아가도록 만들기
				System.out.println("세션 초기화 명령 주입");
				session.setAttribute("sessionPass", "aop locked"); 
				
			}
			
		}
		
	}
	
	public void deleteSession(HttpSession session)  { session.invalidate(); }
		
		
	
	
}
