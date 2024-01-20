<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>



<%
	String sessionpass = "none";
	
	//url 추출 후 login화면이면 상단 패널을 지우기
	String url1 = request.getRequestURL().toString();
	
	String cIpAddress = request.getHeader("X-Forwarded-For");
	
	if (cIpAddress == null || cIpAddress.length() == 0 || "unknown".equalsIgnoreCase(cIpAddress)) {                    
		cIpAddress = request.getHeader("Proxy-Client-IP");
	}
	 
	if (cIpAddress == null || cIpAddress.length() == 0 || "unknown".equalsIgnoreCase(cIpAddress)) {
		cIpAddress = request.getHeader("WL-Proxy-Client-IP");
	}
	 
	if (cIpAddress == null || cIpAddress.length() == 0 || "unknown".equalsIgnoreCase(cIpAddress)) {
		cIpAddress = request.getHeader("HTTP_CLIENT_IP");
	}
	 
	if (cIpAddress == null || cIpAddress.length() == 0 || "unknown".equalsIgnoreCase(cIpAddress)) {
		cIpAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
	}
	 
	if (cIpAddress == null || cIpAddress.length() == 0 || "unknown".equalsIgnoreCase(cIpAddress)) {
		cIpAddress = request.getRemoteAddr();
	}
	
%>


<%
	//log테이블 이외의 페이지에 로그 남기도록 하기
	if(url1.contains("log/") != true){
%>
	<script type="text/javascript">
	$(document).ready(function(){
			
		var logReq = '<%= url1 %>';
		var cIpAddress = '<%= cIpAddress %>';
		
		//var url = "<c:url value='/log/logInsert.go?ip="+cIpAddress+"&logReq="+logReq+"'/>";
		var url = "<c:url value='/log/logInsert.go'/>";

		// [요청 json 데이터 선언]
		var jsonData = { // Body에 첨부할 json 데이터
				"ip" : cIpAddress,
				"logReq" : logReq
			};
		
		
		//로그 기입 ajax 넣기
		$.ajax({      
	        type:"post",  
	        url : url,
	        //dataType : text 옵션으로 viewresolver가 반응하지 않게 하기
	        dataType : 'text', // 받을 data type
	        data : JSON.stringify(jsonData),
	        processData : false,
	        contentType : false,    
	        success:function(args){   
	        	//alert(args);
	        }
	    });  
			
	});
	
	</script>
	
<%
	}
%>
 
			
			
<%
	//관리자 로그인 세션 체크 - 허용 안될시 초기화면으로 넘어감
	
	if(		
			
			url1.contains("view/loginHome") == true ||
			url1.contains("view/failLogin") == true ||
			url1.contains("view/userView") == true ){ 
		
	}else{
	
		//세션 작업 = none시 로그인 페이지로 이동
		if( null == session ){ sessionpass = "locked"; }
		else { sessionpass = (String) session.getAttribute("sessionPass"); }
		
		System.out.println("sessionpass in header : " + sessionpass);
			if("pass".equals(sessionpass) == false){
				
				System.out.println("세션 삭제 후 초기화면으로 이동");
				%> <script>location.replace("/third/loginHome.go");</script> <%
			}
	}
%> 
