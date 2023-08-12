<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:import url="/AdminHeader.go" />

<script type="text/javascript">

	
	$(document).ready(function(){ fn_setWidth(); fn_setRV('1','0'); });
	
	
	
	$( window ).resize(function() {
			fn_setWidth();
	});
	
	function fn_setWidth(){
		
		var windowWidth = $( window ).width();
		var t1_width = $( "#contents_align_div_t1" ).width();
		
			if (windowWidth >= 1170) {
			//창 가로 크기가 1170 보다 클 경우
			//div_t3_image 네임 width45p 설정
				
				$("img[name=div_t3_image]").removeClass('width95p'); $("img[name=div_t3_image]").addClass('width65p');
			
				$( "#contents_align_div1" ).show();
				$( "#contents_align_div1_mv" ).hide();
			} else if(windowWidth < 1170 && windowWidth > 500 ) {
			//창 가로 크기가 1170 미만일 경우 
			//div_t3_image 네임 width95p 설정
			//contents_align_div1_mv + cont height 150으로 설정
			
				$( "#contents_align_div1_mv" ).removeClass('height250'); $( "#contents_align_div1_mv" ).addClass('height150');
				$( "#contents_align_div1_mv_cont" ).removeClass('height200'); $( "#contents_align_div1_mv_cont" ).addClass('height100');
				
				$( "#contents_align_div_ry" ).removeClass('height350'); $( "#contents_align_div_ry" ).addClass('height200');
				$( "#contents_align_div_ry_cont" ).removeClass('height300'); $( "#contents_align_div_ry_cont" ).addClass('height150');
				
				$("img[name=div_t3_image]").removeClass('width65p'); $("img[name=div_t3_image]").addClass('width95p');
			
				$( "#contents_align_div1" ).hide();
				$( "#contents_align_div1_mv" ).show();
			} else if(windowWidth <= 500) {
			//창 가로 크기가 400 미만일 경우 
			//contents_align_div1_mv + cont height 200으로 설정
			
				$( "#contents_align_div1_mv" ).removeClass('height150'); $( "#contents_align_div1_mv" ).addClass('height250');
				$( "#contents_align_div1_mv_cont" ).removeClass('height100'); $( "#contents_align_div1_mv_cont" ).addClass('height200');
				
				$( "#contents_align_div_ry" ).removeClass('height200'); $( "#contents_align_div_ry" ).addClass('height350');
				$( "#contents_align_div_ry_cont" ).removeClass('height150'); $( "#contents_align_div_ry_cont" ).addClass('height300');
				
				$("img[name=div_t3_image]").removeClass('width65p'); $("img[name=div_t3_image]").addClass('width95p');
				
				$( "#contents_align_div1" ).hide();
				$( "#contents_align_div1_mv" ).show();
			}
		   
	}
	
	function fn_returnIntro(){
		
		var offset = $("#contents_align_div_ry").offset();
	     $('html, body').animate({scrollTop : offset.top}, 400);
		
	}
	
	function fn_gotoRV(buttonid){
		
		
		
		
	}
	
	
	function fn_setRV(buttonid,mode){
		
		//buttonid 값에 따라 레이아웃 보여주기, 1차적으로 모든 뷰를 끄고 buttionid값에 해당하는 기능 뷰를 보여주기
		//mode가 0이면 스크롤 이동 없음, 1이면 기능 소개 부분으로 스크롤 이동
		 
		
		for(i=1; i<3; i++){ fn_hideHeight(i); }
		
		$( "#contents_align_div_rv"+buttonid ).show(); $( "#contents_align_div_rvcon"+buttonid ).show();
		
		if(buttonid == '1'){
			fn_setHeight(buttonid, '1650');
		}else if(buttonid == '2'){
			fn_setHeight(buttonid, '1000');
		}
		
		
		if(mode == '1'){ var offset = $( "#contents_align_span_rv"+buttonid ).offset(); $('html, body').animate({scrollTop : offset.top}, 400); }
		//contents_align_div_rv를 호출하는 동안 해당 display의 좌표가 00으로 잡히는 버그가 있어서 span들을 여기저기 뿌린 다음 거기로 좌표를 잡기 
		 
		
	}
	
	
	function fn_hideHeight(buttonid){
		
		$( "#contents_align_div_rv" + buttonid ).css('display', 'none'); $( "#contents_align_div_rvcon" + buttonid  ).css('display', 'none');
		$( "#contents_align_div_rv" + buttonid ).css('height', '0px'); $( "#contents_align_div_rvcon" + buttonid  ).css('height', '0px');
		
	}
	
	function fn_setHeight(buttonid, setH){
		
		$( "#contents_align_div_rv"+buttonid ).css('height', setH+'px');
		$( "#contents_align_div_rvcon"+buttonid ).css('height', setH+'px');
	}


</script>
<body>

<div id="contents_align_div1" class="contents_align_div height200">
	<div id="contents_align_div1_cont" class="contents_wrap_t2 height150">
			
			안녕하십니까. 백엔드 웹 개발자 지망생 이정열입니다.
			</br>
			로그인 하지 않고 상단에 보이는 항목과 게시판은 사용자 시점에서 사용하는 게시판으로 구현했습니다.
			</br>
			면접을 할 기회가 생기면 관리자용 게시판 기능을 시연해드리겠습니다. 잘부탁드립니다.
	</div>
</div>	

<div id="contents_align_div1_mv" class="contents_align_div_mv height150">

	<div id="contents_align_div1_mv_cont" class="contents_wrap_mv height100">
			
			안녕하십니까. 백엔드 웹 개발자 지망생 이정열입니다.
			</br>
			로그인 하지 않고 상단에 보이는 항목과 게시판은 사용자 시점에서 사용하는 게시판으로 구현했습니다.
			</br>
			면접을 할 기회가 생기면 관리자용 게시판 기능을 시연해드리겠습니다. 잘부탁드립니다.
	</div>

</div>


<div id="contents_align_div_ry" class="contents_align_div height200">
	<div id="contents_align_div_ry_cont" class="contents_wrap_t2 height150">
			
			<div class="font_about_me_content">구현 기능 소개(클릭하면 해당 소개문으로 이동합니다)</div>
			</br><span class="space10"></span></br>
			<div style="display:inline-block;">
			
				<button id="rv1" type="button" class="btn_rv1_div" onclick="javascript:fn_setRV('1','1');">
					<img src="${pageContext.request.contextPath}/resources/icon_loginview/9846051.png" class="btn_rv1"/></br>항목과 게시판의 생성
				</button>
				
				<button id="rv2" type="button" class="btn_rv1_div" onclick="javascript:fn_setRV('2','1');">
					<img src="${pageContext.request.contextPath}/resources/icon_loginview/9845968.png" class="btn_rv1"/></br>로그 테이블
				</button>
			
			</div>
			
	</div>
</div>

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

<span id="contents_align_span_rv1" ></span><!-- contents_align_div_rv를 호출하는 동안 해당 display의 좌표가 00으로 잡히는 버그가 있어서 span들을 여기저기 뿌린 다음 거기로 좌표를 잡기 -->
<div id="contents_align_div_rv1"  class="contents_align_div ">

	<div id="contents_align_div_rvcon1" class="contents_wrap_t3 ">
	
		<div class="font_about_me">
			<img class="icon_40" src="${pageContext.request.contextPath}/resources/icon_loginview/free-icon-speech-bubble-181506.png"/>기능 소개
			<button class="btn_rv_top" onclick="javascript:fn_returnIntro();" type="button"></button>
		</div>
		
		</br><span class="space10"></span></br>
		
		<div class="font_about_me_content">
			<img class="icon_20" src="${pageContext.request.contextPath}/resources/icon_loginview/edit_icon_250480.png"/>  항목과 게시판의 생성
		</div>
		</br><span class="space20"></span></br>
		<span>
			홈페이지의 사이트를 관리하는 관리자가 개발 관련 지식이 없어도 홈페이지 관리 업무를 할 수 있도록 
			</br>
			게시판을 한번에 묶어서 관리하는 항목과, 각 게시판을 만드는 기능을 구현해 보았습니다.
			</br>
			aws 서버와 tomcat을 이용해서 이 페이지를 만들어 보았습니다.
			</br>
			서버의 성능이 좋지 못해서 사용자가 익명이나 회원가입을 통하여 게시물을 만드는 기능을 넣지 않은 점 양해부탁드립니다. 
		</span>
			
		</br><span class="space20"></span></br>
	
		<div class="font_about_me_content">항목 생성 과정 </div>
		</br><span class="space10"></span></br>
		<img name="div_t3_image" class="border3_black width95p" src="${pageContext.request.contextPath}/resources/siteStep/form-step_b.png"/>
		
		</br><span class="space20"></span></br>
	
		<div class="font_about_me_content">게시판 생성 과정</div>
		</br><span class="space10"></span></br>
		<img name="div_t3_image" class="border3_black width95p" src="${pageContext.request.contextPath}/resources/siteStep/site-step_a.png"/>
	
	</div>


</div>

<span id="contents_align_span_rv2" ></span><!-- contents_align_div_rv를 호출하는 동안 해당 display의 좌표가 00으로 잡히는 버그가 있어서 span들을 여기저기 뿌린 다음 거기로 좌표를 잡기 -->
<div id="contents_align_div_rv2" class="contents_align_div ">

	<div id="contents_align_div_rvcon2" class="contents_wrap_t3 ">
	
		<div class="font_about_me">
			<img class="icon_40" src="${pageContext.request.contextPath}/resources/icon_loginview/free-icon-speech-bubble-181506.png"/>기능 소개
			<button class="btn_rv_top" onclick="javascript:fn_returnIntro();" type="button"></button>
		</div>
		
		</br><span class="space10"></span></br>
		
		<div class="font_about_me_content">
			<img class="icon_20" src="${pageContext.request.contextPath}/resources/icon_loginview/edit_icon_250480.png"/>  로그 테이블
		</div>
		</br><span class="space20"></span></br>
		<span>
			홈페이지의 사이트를 관리하는 관리자가 홈페이지 관리 도중 문제를 확인할 때 보는 웹 로그를 구현해 보았습니다.
			</br>
			특정한 ip가 특정한 뷰를 조회하였는지를 파악하여 기능이 정상적으로 이루어져 있는지 확인할 수 있게 만들었습니다. 
		</span>
		</br><span class="space10"></span></br>
		<img name="div_t3_image" class="border3_black width95p" src="${pageContext.request.contextPath}/resources/siteStep/logAdmin-step.png"/>

	
	</div>


</div>
	
</body>