<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

	
	<script>
	
		$(document).ready(function(){ fn_setLoginFormWidth();  });
			
		$( window ).resize(function() {
			fn_setLoginFormWidth();
		});	
	
		function fn_access(){
			
			document.loginHomeVO.action = '<c:url value="/accLoginHome.go"/>';
			document.loginHomeVO.submit();
			
		}
	
		
		function fn_setLoginFormWidth(){
			
			var windowWidth = $( window ).width();
		   
			$( "#loginmask").css('width', '100%');
			
				if (windowWidth >= 1170) {
				//창 가로 크기가 1170 보다 클 경우
					
					$( "#loginformBox").css('left', '30%');
					$( "#loginformBox").css('margin-left', '30px');
					$( "#loginformBox").css('width', '35%');
					$( "#loginformBox").css('padding', '25px 0px 25px 25px');
					
				} else if(windowWidth < 1170 && windowWidth > 550 ) {
				//창 가로 크기가 1170 미만일 경우 
					$( "#loginformBox").css('left', '25%');
					$( "#loginformBox").css('margin-;eft', '30px');
					$( "#loginformBox").css('width', '45%');
					$( "#loginformBox").css('padding', '25px 0px 25px 25px');

				} else if(windowWidth <= 550) {				
					//창 가로 크기가 400 미만일 경우 
					
					$( "#loginformBox").css('left', '0%');
					$( "#loginformBox").css('margin-left', '10px');
					$( "#loginformBox").css('width', (windowWidth - 25) + 'px');
					$( "#loginformBox").css('padding', '10px 0px 10px 10px');
					
				}
			   
		}
	
	</script>
	<!-- 로그인 화면 전용 마스크 -->
	<div id="loginmask"></div>

	<div id="loginformBox" >
		<div class="loginFormBox">
			<form class=""  id="loginHomeVO" name="loginHomeVO" method="post" enctype="multipart/form-data" action="" >
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
									<td> <input type="text" id="id" name="id" value="" class="width90p" > </td>
								
								</tr>
								<tr>
								
									<th>pw<th>
									<td> <input type="password" id="pw" name="pw" value="" class="width90p" > </td>
								
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