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
			
			   //t1_width = $( "#contents_align_div_t1" ).width();
			   //$( ".contents_wrap_mv" ).css( "width", "92.5%");			
			   //$( "#contents_align_div_t2" ).css( "width", t1_width + "px");
			   
			   $( "#contents_align_div_t1" ).hide();
			   $( ".contents_align_div_mv" ).show();
			} else {
			//창 가로 크기가 1170 보다 클 경우
			
				//t1_width = $( "#contents_align_div_t1" ).width();
				//$( ".contents_wrap_t2" ).css( "width", "92.5%" );
				//$( "#contents_align_div_t2" ).css( "width", t1_width + "px");
			
				$( "#contents_align_div_t1" ).show();
				$( ".contents_align_div_mv" ).hide();
			}
		   
	}
	



</script>
<body>


<div id="contents_align_div_t1" class="contents_align_div height200">
	<div class="contents_wrap_t2 height100">
			
			안녕하십니까. 백엔드 웹 개발자를 지향하는 이정열입니다.
			</br>
			로그인 하지 않고 상단에 보이는 항목과 게시판은 사용자 시점에서 사용하는 게시판으로 구현했습니다.
			</br>
			면접을 할 기회가 생기면 관리자용 게시판 기능을 시연해드리겠습니다. 잘부탁드립니다.
	</div>
</div>	
<div class="contents_align_div_mv height150">

	<div class="contents_wrap_mv height150">
			
			안녕하십니까. 백엔드 웹 개발자를 지향하는 이정열입니다.
			</br>
			로그인 하지 않고 상단에 보이는 항목과 게시판은 사용자 시점에서 사용하는 게시판으로 구현했습니다.
			</br>
			면접을 할 기회가 생기면 관리자용 게시판 기능을 보여드리겠습니다. 잘부탁드립니다.
	</div>

</div>
<div style="clear:both;"></div>

<div id="contents_align_div_t2"  class="contents_align_div height350">
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

<div id="contents_align_div_t3"  class="contents_align_div height1500">

	<div class="contents_wrap_t3 height1750">
	
		<div class="font_about_me">
			<img class="icon_40" src="${pageContext.request.contextPath}/resources/icon_loginview/free-icon-speech-bubble-181506.png"/>
			기능 소개
		</div>
		
		</br><span class="space10"></span></br>
		
		<div class="font_about_me_content">
			<img class="icon_20" src="${pageContext.request.contextPath}/resources/icon_loginview/edit_icon_250480.png"/>  항목과 게시판의 생성
		</div>
		</br><span class="space20"></span></br>
		<span>
			홈페이지의 사이트를 관리하는 관리자가 개발 관련 지식이 없어도 홈페이지 관리 업무를 할 수 있도록 
			</br>
			게시판을 한번에 묶어서 관리하는 항목과, 각 게시판을 만드는 기능을
			</br>
			aws 서버와 tomcat을 이용해서 이 페이지를 만들어 보았습니다.
			</br>
			서버의 성능이 좋지 못해서 사용자가 익명이나 회원가입을 통하여 게시물을 만드는 기능을 넣지 않은 점 양해부탁드립니다. 
		</span>
			
		</br><span class="space20"></span></br>
	
		<div class="font_about_me_content">항목 생성 과정 </div>
		</br><span class="space10"></span></br>
		<img class="border3_black" src="${pageContext.request.contextPath}/resources/siteStep/form-step_b.png"/>
		
		</br><span class="space20"></span></br>
	
		<div class="font_about_me_content">게시판 생성 과정</div>
		</br><span class="space10"></span></br>
		<img class="border3_black" src="${pageContext.request.contextPath}/resources/siteStep/site-step_a.png"/>
	
	</div>


</div>
	
</body>