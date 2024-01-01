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

 <!-- 제이쿼리 ui css -->
 <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"> 

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/icono.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/siteMenu.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/headMenu.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/iconpack.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/display.css" />


<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/smartedityor2-master/workspace/js/service/HuskyEZCreator.js"  charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/third_commonUtil.js"></script>

 
<!-- 제이쿼리 ui js -->
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/inc/inc.js"></script>
<!-- 헤더 세션 체크 --> 
<jsp:include page="headerSessionCheck.jsp"/>

<script type="text/javascript">
	
	    
	<c:if test="${not empty System_errMessage}">

		window.addEventListener('load', function() { alert('<c:out value="${System_errMessage}"/>'); });
		
	</c:if>	
	
	
</script>	

<title>header 포함</title>
		
		<%
			//사용자용 메뉴 		
		%>
	
		
		<div id="navContent" class="width100p inline-flex">
		
			<jsp:include page="UserHeaderMenu.jsp"/>
			<%
				//관리자용 네비게이션 뷰
				if(	null != session.getValue("id") && !"".equals(session.getValue("id")) ){	
			%>
				<jsp:include page="managerHeaderMenu.jsp"/>
			<% } %>
		
		</div>
		
		
		<!-- 로그인 화면 전용 폼-->
		<c:import url="/AdminLoginFormBox.go" />
		
		

</head>