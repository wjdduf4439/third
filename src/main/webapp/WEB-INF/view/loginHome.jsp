<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:import url="/AdminHeader.go" />

<script type="text/javascript">

	
	function fn_access(){
		
		document.loginHomeVO.action = "accLoginHome.go";
		document.loginHomeVO.submit();
		
	}


</script>
<body>

<div class="contents_align_div">
	<span class="contents_wrap_t1 fl">
		<form class=""  id="loginHomeVO" name="loginHomeVO" method="post" enctype="multipart/form-data"  runat="server" action="" >
			<div class="contents_login">
				<div class="space10"></div>
					포트폴리오 : 이정열
					로그인
					</br>
				<div>
					<table id="btable" border="1" cellpadding="1" cellspacing="1" class="admin_table tableline width450">
						<tbody>
							<tr>
							
								<th>id<th>
								<td> <input type="text" id="id" name="id" value="" > </td>
							
							</tr>
							<tr>
							
								<th>pw<th>
								<td> <input type="password" id="pw" name="pw" value="" > </td>
							
							</tr>
						</tbody>
					</table>
				</div>
				<div class="btngroup mt0">
					<button class="btn02 fl" onclick="javascript:fn_access();" type="button">access login</button>
				</div>
				
			</div>
			
			
		</form>
	</span>
	<span class="contents_wrap_t2">
			
			안녕하십니까. 백엔드 웹 개발자를 지향하는 이정열입니다.
			</br>
			로그인 하지 않고 상단에 보이는 항목과 게시판은 사용자 시점에서 사용하는 게시판으로 구현했습니다.
			</br>
			면접을 할 기회가 생기면 관리자용 게시판 기능을 보여드리겠습니다. 잘부탁드립니다.
	</span>
</div>	

<div class="contents_wrap_t3">

	<div class="font_about_me">
		<img class="icon_40" src="${pageContext.request.contextPath}/resources/icon_loginview/free-icon-speech-bubble-181506.png"/>
		ABOUT ME
	</div>
	<span class="space10"></span>
	</br><span class="space10"></span></br>
	
		<img class="icon_20" src="${pageContext.request.contextPath}/resources/icon_loginview/free-icon-internet-181526.png"/>
		<span>주소 : </span> <span> 대구 중구</span>
		
	</br><span class="space10"></span></br>
	
		<img class="icon_20" src="${pageContext.request.contextPath}/resources/icon_loginview/free-icon-smartphone-181516.png"/>
		<span>tel : </span> <span> 010 - 2933 - 4813</span>
		
	</br><span class="space10"></span></br>
	
		<img class="icon_20" src="${pageContext.request.contextPath}/resources/icon_loginview/free-icon-email-181535.png"/>
		<span>email : </span><span>wjdduf4439@gmail.com</span>
		
	</br><span class="space10"></span></br>
	
		<img class="icon_20" src="${pageContext.request.contextPath}/resources/icon_loginview/free-icon-link-181531.png"/>
		<span>skill : </span><span>spring, php, java, c++</span>

</div>
	
</body>