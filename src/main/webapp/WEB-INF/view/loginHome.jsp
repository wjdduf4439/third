<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:import url="/AdminHeader.go" />

<script type="text/javascript">

	
	$(document).ready(function(){ fn_setWidth(); });
	
	
	
	$( window ).resize(function() {
			fn_setWidth();
	});
	
	function fn_setWidth(){
		
		var windowWidth = $( window ).width();
		var t1_width = $( "#contents_align_div_t1" ).width();
		   
		   if(windowWidth < 1170) {
			//창 가로 크기가 1170 미만일 경우
			   $( ".contents_wrap_t1" ).css( "width", "92.5%" );
			
			   $( "#contents_align_div_t2" ).css( "width", t1_width + "px");
			   
			   $( ".contents_wrap_t2" ).hide();
			   $( ".contents_align_div_mv" ).show();
			} else {
			//창 가로 크기가 1170 보다 클 경우
				$( ".contents_wrap_t1" ).css( "width", "45%");
				$( ".contents_wrap_t2" ).css( "margin-left", +"px" )
			
				$( "#contents_align_div_t2" ).css( "width", t1_width + "px");
			
				$( ".contents_wrap_t2" ).show();
				$( ".contents_align_div_mv" ).hide();
			}
		   
	}
	
	function fn_access(){
		
		document.loginHomeVO.action = "accLoginHome.go";
		document.loginHomeVO.submit();
		
	}


</script>
<body>

<div id="contents_align_div_t1" class="contents_align_div he inline-flex">
	<div class="contents_wrap_t1">
		<form class=""  id="loginHomeVO" name="loginHomeVO" method="post" enctype="multipart/form-data"  runat="server" action="" >
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
	<div class="contents_wrap_t2">
			
			안녕하십니까. 백엔드 웹 개발자를 지향하는 이정열입니다.
			</br>
			로그인 하지 않고 상단에 보이는 항목과 게시판은 사용자 시점에서 사용하는 게시판으로 구현했습니다.
			</br>
			면접을 할 기회가 생기면 관리자용 게시판 기능을 보여드리겠습니다. 잘부탁드립니다.
	</div>
</div>	
<div class="contents_align_div_mv">

	<div class="contents_wrap_mv">
			
			안녕하십니까. 백엔드 웹 개발자를 지향하는 이정열입니다.
			</br>
			로그인 하지 않고 상단에 보이는 항목과 게시판은 사용자 시점에서 사용하는 게시판으로 구현했습니다.
			</br>
			면접을 할 기회가 생기면 관리자용 게시판 기능을 보여드리겠습니다. 잘부탁드립니다.
	</div>

</div>
<div style="clear:both;"></div>

<div id="contents_align_div_t2"  class="contents_align_div height200">
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
</div>
	
</body>