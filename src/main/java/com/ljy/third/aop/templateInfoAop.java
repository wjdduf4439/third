package com.ljy.third.aop;

//@Component
//@Aspect
/*
public class templateInfoAop {
	
	// 시작 전에 실행될 AOP 메소드
	//within: 해당 패키지 또는 클래스 내의 모든 메서드
	//forward 구문으로 매핑 접근을 시도하면 aop는 적용되지 않는다.
	//@Before("within(com.ljy.third.controller.TemplateInfoController)")
	//@Before("execution(* com.ljy.third.controller.TemplateZeroController.*(..))")
	@Before("execution(* com.ljy.third.controller.TemplateInfoController.*(..))")
	public void beforetemplateInfoMethod(JoinPoint jpt) throws IOException, Throwable  {
		
		System.out.println("TemplateZeroController컨트롤러 시작전~!");
		//1. view에 json형식으로 aop에 request 보내기
		
		//servlet에서 request 가져오기
		final String methodName = jpt.getSignature().getName();
	    ServletRequestAttributes attribute = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes());
	    HttpServletRequest request = attribute.getRequest();
		
		ServletInputStream inputStream = request.getInputStream();
		String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
		System.out.println("messageBody={} : " + messageBody);
		
		System.out.println("request siteCode : " + request.getAttribute("siteCode"));
		System.out.println("request siteCode_json : " + request.getAttribute("siteCode_json"));
		
		
	}
	

	
}
*/
