<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:import url="/AdminHeader.go" />

<script type="text/javascript">

	
	$(document).ready(function(){ fn_setWidth(); fn_setRV('3','0'); });
	
	$( window ).resize(function() {
			fn_setWidth();
	});
	
	function fn_setWidth(){
		
		var windowWidth = $( window ).width();
		var t1_width = $( "#contents_align_div_t1" ).width();
		
		var contents_align_div1_height = $( "#contents_align_div1_cont" ).height();
		$( "#contents_align_div1" ).css('height', (contents_align_div1_height + 75) + 'px');
		
		var contents_align_div1_mv_cont_height = $( "#contents_align_div1_mv_cont" ).height();
		$( "#contents_align_div1_mv" ).css('height', (contents_align_div1_mv_cont_height + 75) + 'px');
		
		var contents_align_div_ry_cont = $( "#contents_align_div_ry_cont" ).height();
		$( "#contents_align_div_ry" ).css('height', (contents_align_div_ry_cont + 75) + 'px');
		
			if (windowWidth >= 1170) {
			//창 가로 크기가 1170 보다 클 경우
			//div_t3_image 네임 width45p 설정
				
				$("img[name=div_t3_image]").removeClass('width95p'); $("img[name=div_t3_image]").addClass('width65p');
				$( "#contents_align_div_t2" ).css('height', '300px'); $( "#contents_align_div_cont_t2" ).css('height', '250px');
				
			} else if(windowWidth < 1170 && windowWidth > 550 ) {
			//창 가로 크기가 1170 미만일 경우 
			//div_t3_image 네임 width95p 설정
				
				$("img[name=div_t3_image]").removeClass('width65p'); $("img[name=div_t3_image]").addClass('width95p');
				$( "#contents_align_div_t2" ).css('height', '300px'); $( "#contents_align_div_cont_t2" ).css('height', '250px');

			} else if(windowWidth <= 550) {				
				//창 가로 크기가 400 미만일 경우 
				//contents_align_div1_mv + cont height 200으로 설정
				
				$("img[name=div_t3_image]").removeClass('width65p'); $("img[name=div_t3_image]").addClass('width95p');
				$( "#contents_align_div_t2" ).css('height', '450px'); $( "#contents_align_div_cont_t2" ).css('height', '400px');

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
		 
		
		for(i=1; i<5; i++){ fn_hideHeight(i); }
		
		$( "#contents_align_div_rv"+buttonid ).show(); $( "#contents_align_div_rvcon"+buttonid ).show();
		
		if(buttonid == '1'){  fn_setHeight(buttonid, '2750'); }
		else if(buttonid == '2'){ fn_setHeight(buttonid, '800'); }
		else if(buttonid == '3'){ fn_setHeight(buttonid, '2850'); }
		else if(buttonid == '4'){ fn_setHeight(buttonid, '1000'); }
		
		
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

<div id="contents_align_div1" class="contents_align_div">
	<div id="contents_align_div1_cont" class="contents_wrap_t2">
			
			안녕하십니까. 백엔드 웹 개발자 지망생 이정열입니다.
			</br></br>
			과거에는 홈페이지 유지보수 일을 함으로서 웹사이트 안정성과 기능 개선을 담당했으며, 이번에는 백엔드 개발 분야로 나아가고자 합니다. 
			</br></br>
			유지보수를 통해 다양한 문제를 해결하고 협업을 경험한 덕분에 효율적인 문제 해결과 팀워크의 중요성을 깨닫게 되었습니다. 
			</br></br>
			전반적인 홈페이지 개발을 통해 더욱 깊이 있는 기술과 솔루션을 탐구하며, 새로운 도전에 적극적으로 참여해 보고 싶습니다.
			</br></br>
			이 aws 서버에서 배포한 프로젝트로 지원하고자 하는 회사에 저의 기술과 솔루션에 대한 관심을 표현하고자 합니다.
			</br></br>
			잘부탁드립니다.
	</div>
</div>	


<div id="contents_align_div_ry" class="contents_align_div">
	<div id="contents_align_div_ry_cont" class="contents_wrap_t2">
			
			<div class="font_about_me_content">구현 기능과 학습한 점 소개(클릭하면 해당 소개문으로 이동합니다)</div>
			</br><span class="space10"></span></br>
			<div style="display:inline-block;">
				
				
				<button id="rv3" type="button" class="btn_rv1_div" onclick="javascript:fn_setRV('3','1');">
					<img src="${pageContext.request.contextPath}/resources/icon_loginview/9845754.png" class="btn_rv1"/></br>AWS 활용에 대한 경험
				</button>
			
				<button id="rv1" type="button" class="btn_rv1_div" onclick="javascript:fn_setRV('1','1');">
					<img src="${pageContext.request.contextPath}/resources/icon_loginview/9846051.png" class="btn_rv1"/></br>항목과 게시판의 생성
				</button>
				
				<button id="rv2" type="button" class="btn_rv1_div" onclick="javascript:fn_setRV('2','1');">
					<img src="${pageContext.request.contextPath}/resources/icon_loginview/9845968.png" class="btn_rv1"/></br>로그 테이블
				</button>
				
				<button id="rv4" type="button" class="btn_rv1_div" onclick="javascript:fn_setRV('4','1');">
					<img src="${pageContext.request.contextPath}/resources/icon_loginview/9846025.png" class="btn_rv1"/></br>aop 활용
				</button>
			
			</div>
			
	</div>
</div>

<div id="contents_align_div_t2"  class="contents_align_div">
	<div id="contents_align_div_cont_t2" class="contents_wrap_t3">
	
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
<jsp:include page="/WEB-INF/view/loginHomeIntroduction/contents_align_div_rv1.jsp"/>

<span id="contents_align_span_rv2" ></span><!-- contents_align_div_rv를 호출하는 동안 해당 display의 좌표가 00으로 잡히는 버그가 있어서 span들을 여기저기 뿌린 다음 거기로 좌표를 잡기 -->
<jsp:include page="/WEB-INF/view/loginHomeIntroduction/contents_align_div_rv2.jsp"/>

<span id="contents_align_span_rv3" ></span><!-- contents_align_div_rv를 호출하는 동안 해당 display의 좌표가 00으로 잡히는 버그가 있어서 span들을 여기저기 뿌린 다음 거기로 좌표를 잡기 -->
<jsp:include page="/WEB-INF/view/loginHomeIntroduction/contents_align_div_rv3.jsp"/>

<span id="contents_align_span_rv4" ></span><!-- contents_align_div_rv를 호출하는 동안 해당 display의 좌표가 00으로 잡히는 버그가 있어서 span들을 여기저기 뿌린 다음 거기로 좌표를 잡기 -->
<jsp:include page="/WEB-INF/view/loginHomeIntroduction/contents_align_div_rv4.jsp"/>
	
</body>