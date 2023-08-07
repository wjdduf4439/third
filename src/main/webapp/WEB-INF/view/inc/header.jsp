<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    <%@page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="expires" content="-1" >
<meta http-equiv="X-UA-Compatible" content="IE=10,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">


<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/icono.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/siteMenu.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/headMenu.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/iconpack.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/display.css" />


<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/smarteditor2-master/workspace/js/service/HuskyEZCreator.js"  charset="utf-8"></script>


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
			
			System.out.println("url1 : " + url1);
			System.out.println("cIpAddress : " + cIpAddress);
		%>

<script type="text/javascript">


	<%
		//log테이블 이외의 페이지에 로그 남기도록 하기
		if(url1.contains("log/") != true){
	%>
	$(document).ready(function(){
			
		var logReq = '<%= url1 %>';
		var cIpAddress = '<%= cIpAddress %>';
		
		var url = "<c:url value='/log/logInsert.go?ip="+cIpAddress+"&logReq="+logReq+"'/>";
		
		//로그 기입 ajax 넣기
		$.ajax({      
	        type:"get",  
	        url : url,
	        async: true,
	        //dataType : text 옵션으로 viewresolver가 반응하지 않게 하기
	        dataType : 'json',
	        processData : false,
	        contentType : false,
	        beforeSend : function(xmlHttpRequest){
	        	   xmlHttpRequest.setRequestHeader("AJAX", "true");
	        	  },    
	        success:function(args){   
	                  
	        },   
	        error:function(e){  
	            //alert("log 기입 실패" + e.responseText);  
	        }  
	    });  
			
	});
	
	<%
		}
	%>
	
</script>	
<style>
	#loginmask {
      position:absolute;
      z-index:9000;
      background-color:#000;
      display:none;
      left:0;
      top:0;
    }
    
    #loginformBox {
      position:absolute;
      z-index:9001;
      display:none;
      left:30%;
      top:30%;
      width:35%;
      height:250px; 
      background:#fff; margin-left: 50px; margin-top:30px; padding:25px 0px 25px 25px; border-style:solid; border-color:black; border-radius:5px; }
    }
</style>

<title>header 포함</title>
		
		<%
			//사용자용 메뉴
				
		%>
	
		
		<div class="inline-flex width100p">
		
			<nav id="topMenu">
					<ul>
						
							
						 	<c:forEach var="form" items="${formList }" varStatus="status1">
		
								<li onmouseover="javascript:fn_show();" ><a class="menuLink" >${form.formName }</a></li>
									
							</c:forEach>
							
							<c:forEach var="form" items="${formList }" varStatus="status1">
								<ul onmouseout="javascript:fn_noshow();" class="sub-menu"style="margin-left: ${status1.index * 100}px;" >
										<c:forEach var="sites" items="${sites }" varStatus="status1" begin="${form.startIndex }" end = "${form.endIndex }" >
											
												<ol onmouseover="javascript:fn_show();" >
												
													<c:if test="${form.formCode == sites.formCode }">	
														<a onmouseover="javascript:fn_show();" href="javascript:fn_ViewSiteLink('${sites.siteCode }');" > ${sites.title }</a>		
													</c:if>
													
												</ol>
											
										</c:forEach>
							
										
								</ul>
							</c:forEach>	
							<li><a class="menuLink" href=<c:url value="/loginHome.go"/>>초기 화면</a></li>
							<%
							
							System.out.println("session id : " + session.getValue("id"));
							
							if( null == session.getValue("id") ){
								%> 
								<li><a class="menuLink" href="javascript:fn_LoginForm();">로그인</a></li>
								<%	 
							} else { %> 
								<li><a class="menuLink" href="javascript:fn_loginOut();">로그아웃</a></li>
								<%	 }
							 %>
							
							
							
							<form id="userSitefrm" name="userSitefrm" method="post">
	
								<input type="hidden" id="siteCode" name="siteCode" value="" />
							
							</form>
						 	
						 	<script>
	
							 	function fn_LoginForm(){
							 		
							 		var maskHeight = $(document).height();
							        var maskWidth = $(window).width(); 
							        
							        console.log(maskWidth);
							        console.log(maskHeight);
							        
							        $('#loginmask').css({'width':maskWidth,'height':maskHeight}); 
							        
							        $('#loginmask').fadeIn(1000);
							        $('#loginmask').fadeTo("slow",0.8);
							        
							        $('#loginmask').show();
							        $('#loginformBox').show();
							 		
							 	}
							 	
							 	//동적 페이지 폼 닫기
							 	$(document).on("click", "#loginmask", function(){
							 	    //alert("동작확인");
							 		console.log("loginmask clicked");
						            $('#loginmask').hide();
						            $('#loginformBox').hide();
							 	});
							 	
							 	//로그아웃 버튼을 눌렀을 때
							 	function fn_loginOut(){  
							        
							 		document.userSitefrm.action = '<c:url value="/accLogout.go"/>';
									document.userSitefrm.submit();		
							 		
							 	}
							 	
							 	//검은 막을 눌렀을 때
						        $('#loginmask').click(function () {
						        	console.log("loginmask clicked");
						            $('#loginmask').hide();
						            //$('.window').hide();
						        });
						 	
							 	//게시판 이동
								function fn_ViewSiteLink(siteCode){
								
									document.userSitefrm.siteCode.value = siteCode;
									document.userSitefrm.action = '<c:url value="/template/templeteViewInfo.go"/>';
									document.userSitefrm.submit();
									
								}
								
								function fn_show(){ $(".sub-menu").css("visibility", "visible"); }
								
								function fn_noshow(){ $(".sub-menu").css("visibility", "hidden"); }
							
							</script>
						 	
					</ul>
			</nav>
			
			
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
		
		
			<%
				
				//관리자용 네비게이션 뷰
				if(	null == session.getValue("id") ){ 
					
				}else{
					
			%>
			
			<nav id="topMenu">
					<ul>
						
							
						 	<c:forEach var="form" items="${formList }" varStatus="status1">
		
								<li onmouseover="javascript:fn_show();" ><a class="menuLink" >${form.formName }</a></li>
									
							</c:forEach>
							
							<c:forEach var="form" items="${formList }" varStatus="status1">
								<ul onmouseout="javascript:fn_noshow();" class="sub-menu"style="margin-left: ${status1.index * 100}px;" >
										<c:forEach var="sites" items="${sites }" varStatus="status1" begin="${form.startIndex }" end = "${form.endIndex }" >
											
												<ol onmouseover="javascript:fn_show();" >
												
													<c:if test="${form.formCode == sites.formCode }">	
														<a onmouseover="javascript:fn_show();" href="javascript:fn_SiteLink('${sites.siteCode }');" > ${sites.title }</a>		
													</c:if>
													
												</ol>
											
										</c:forEach>
								</ul>
							</c:forEach>	
							
							<li><a class="menuLink" href=<c:url value="/site/siteAdmin.go"/>>게시판 관리</a></li>
						 	<li><a class="menuLink" href=<c:url value="/form/formAdmin.go"/>>항목 관리</a></li>
						 	<li><a class="menuLink" href=<c:url value="/log/logAdmin.go"/>>로그 테이블</a></li>
							
							<form id="sitefrm" name="sitefrm" method="post">
	
								<input type="hidden" id="siteCode" name="siteCode" value="" />
							
							</form>
						 	
						 	<script>
	
								function fn_SiteLink(siteCode){
								
									document.sitefrm.siteCode.value = siteCode;
									document.sitefrm.action = '<c:url value="/template/templateInfo.go"/>';
									document.sitefrm.submit();
									
								}
								
								function fn_show(){ $(".sub-menu").css("visibility", "visible"); }
								
								function fn_noshow(){ $(".sub-menu").css("visibility", "hidden"); }
							
							</script>
						 	
					</ul>
			</nav>
			<% } %>
		
		</div>
		
		<!-- 로그인 화면 전용 마스크 -->
		<div id="loginmask"></div>
		<!-- 로그인 화면 전용 폼-->
		<div id="loginformBox"><c:import url="/AdminLoginFormBox.go" /></div>
		
		

</head>