<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/loginForm.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/inc/loginFormBox.js"></script>
	
	<!-- 로그인 화면 전용 마스크 -->
	<div id="loginmask"></div>

	<div id="loginformBox" >
		<div class="loginFormBox">
			<form class=""  id="loginHomeVO" name="loginHomeVO" method="post" enctype="multipart/form-data" action="<c:url value="/accLoginHome.go"/>" >
				<div class="contents_login">
					<div class="space10"></div>
						포트폴리오 : 이정열
						로그인
						</br>
					<div>
						<table id="btable" border="1" cellpadding="1" cellspacing="1" class="admin_table tableline_login">
							<tbody>
								<tr>
								
									<th>id<th>
									<td> <input type="text" id="id" name="id" value="" class="max-width90p" > </td>
								
								</tr>
								<tr>
								
									<th>pw<th>
									<td> <input type="password" id="pw" name="pw" value="" class="max-width90p" > </td>
								
								</tr>
							</tbody>
						</table>
					</div>
					<div class="btngroup mt0">
						<button class="btn02 fl" onclick="javascript:fn_access();" type="button">access login</button>
					</div>
					
				</div>
				
				
			</form>
		</div>
	</div>