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
			
				$( "#contents_align_div1" ).show();
				$( "#contents_align_div1_mv" ).hide();
			} else if(windowWidth < 1170 && windowWidth > 550 ) {
			//창 가로 크기가 1170 미만일 경우 
			//div_t3_image 네임 width95p 설정
				
				$("img[name=div_t3_image]").removeClass('width65p'); $("img[name=div_t3_image]").addClass('width95p');
			
				$( "#contents_align_div1" ).hide();
				$( "#contents_align_div1_mv" ).show();
			} else if(windowWidth <= 550) {				
				//창 가로 크기가 400 미만일 경우 
				//contents_align_div1_mv + cont height 200으로 설정
				
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
		 
		
		for(i=1; i<5; i++){ fn_hideHeight(i); }
		
		$( "#contents_align_div_rv"+buttonid ).show(); $( "#contents_align_div_rvcon"+buttonid ).show();
		
		if(buttonid == '1'){ fn_setHeight(buttonid, '1650'); }
		else if(buttonid == '2'){ fn_setHeight(buttonid, '800'); }
		else if(buttonid == '3'){ fn_setHeight(buttonid, '2850'); }
		else if(buttonid == '2'){ fn_setHeight(buttonid, '1200'); }
		
		
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
			전반적인 홈페이지 개발을 통해 더욱 깊이 있는 기술과 솔루션을 탐구하며, 새로운 도전에 적극적으로 임하고자 합니다.
			</br></br>
			이 aws 서버에서 배포한 프로젝트로 지원하고자 하는 회사에 저의 기술과 솔루션에 대한 관심을 표현하고자 합니다.
			</br></br>
			잘부탁드립니다.
	</div>
</div>	

<div id="contents_align_div1_mv" class="contents_align_div_mv">

	<div id="contents_align_div1_mv_cont" class="contents_wrap_mv">
			
			안녕하십니까. 백엔드 웹 개발자 지망생 이정열입니다.
			</br></br>
			과거에는 홈페이지 유지보수 일을 함으로서 웹사이트 안정성과 기능 개선을 담당했으며, 이번에는 백엔드 개발 분야로 나아가고자 합니다. 
			</br></br>
			유지보수를 통해 다양한 문제를 해결하고 협업을 경험한 덕분에 효율적인 문제 해결과 팀워크의 중요성을 깨닫게 되었습니다. 
			</br></br>
			전반적인 홈페이지 개발을 통해 더욱 깊이 있는 기술과 솔루션을 탐구하며, 새로운 도전에 적극적으로 임하고자 합니다.
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
				
				<!--
				<button id="rv4" type="button" class="btn_rv1_div" onclick="javascript:fn_setRV('4','1');">
					<img src="${pageContext.request.contextPath}/resources/icon_loginview/9845968.png" class="btn_rv1"/></br>db의 구조와 다이어그램
				</button>
				 -->
			
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
			</br></br>
			게시판을 한번에 묶어서 관리하는 항목과, 각 게시판을 만드는 기능을 구현해 보았습니다.
			</br></br>
			서버의 성능이 좋지 못해서 사용자가 익명이나 회원가입을 통하여 게시물을 만드는 기능을 넣지 않았습니다. 
			
			</br></br></br>
			
			웹 사이트를 어떻게 조작하고 콘텐츠를 관리할지에 대한 초보적인 기능을 구현했습니다.
			</br></br>
			사용자가 편리하게 콘텐츠를 추가하고 수정할 수 있는 UI를 구성하는 것의 중요성을 배웠습니다.
			
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
			</br></br>
			특정한 ip가 특정한 뷰를 조회하였는지를 파악하여 기능이 정상적으로 이루어져 있는지 확인할 수 있게 만들었습니다. 
			</br></br>
			간단하게나마 사용자 활동에 대한 데이터를 수집하고 시각화하는 능력을 키울수 있었습니다.
			</br></br>
			만약 개발자로 입사해서 더 넓은 시야를 가지고 필요에 따른 요구사항에 대한 정리가 되면 더 세부적으로 기록할 것이 많을 것이고,
			</br></br>
			그렇게 되면 더 다양한 형태의 기록을 인지하고 받아들여서 기록할 수 있는 역량을 키워냈으면 좋겠습니다. 
			
		
		</span>
		</br><span class="space10"></span></br>
		<img name="div_t3_image" class="border3_black width95p" src="${pageContext.request.contextPath}/resources/siteStep/logAdmin-step.png"/>

	
	</div>


</div>

<span id="contents_align_span_rv3" ></span><!-- contents_align_div_rv를 호출하는 동안 해당 display의 좌표가 00으로 잡히는 버그가 있어서 span들을 여기저기 뿌린 다음 거기로 좌표를 잡기 -->
<div id="contents_align_div_rv3" class="contents_align_div ">

	<div id="contents_align_div_rvcon3" class="contents_wrap_t3 ">
	
		<div class="font_about_me">
			<img class="icon_40" src="${pageContext.request.contextPath}/resources/icon_loginview/free-icon-speech-bubble-181506.png"/>기능 소개
			<button class="btn_rv_top" onclick="javascript:fn_returnIntro();" type="button"></button>
		</div>
		
		</br><span class="space10"></span></br>
		
		<div class="font_about_me_content">
			<img class="icon_20" src="${pageContext.request.contextPath}/resources/icon_loginview/edit_icon_250480.png"/>  AWS 활용에 대한 경험
		</div>
		</br><span class="space20"></span></br>
		<span>
			AWS를 활용하여 웹 기반의 프로젝트를 구축한 경험을 가지고 좀 더 넓은 범위의 프로그래밍 지식을 쌓고 싶었고, 이 경험은 제 생각보다 더 큰 도전과 성장의 기회였습니다.
			</br></br>
			 이 프로젝트를 통해 새로운 분야에 도전하고 배움을 얻는 과정이 매우 의미있었다고 생각해서 걱정보다 몸이 먼저 움직인 덕분에 이 프로젝트를 여기까지 진행시켰을 수 있다고 생각합니다.
			</br> </br>
			 이 경험을 통해 저는 아직 배울 점이 많고 부족한 점이 많지만, 새로운 분야에 도전하고 성장하는 의지와 노력을 가지고 있다는 것을 느낄 수 있었습니다. 
			 
			</br></br></br>
			
			이 프로젝트를 제작하면서 목표로 삼았던 것은 웹을 통한 기본적인 컨텐츠 상호 작용을 제공하고 이를 관리하는 관리자 기능을 만드는 것이었습니다.
			</br></br>
			하지만 기본적인 상호작용조차 개발에 있어서 처음인 제가 함부로 할만한 일이 아니라는걸 깨닫는 데에는 1주일도 안걸렸습니다. 정말 힘들더라구요 
			 
			</br></br></br>
			
			Amazon EC2 인스턴스를 활용하여 웹 서버를 구축하였고, VPC, 보안그룹, 보안 그룹, 웹 애플리케이션 계층과 데이터베이스 계층을 분리하기 위해 rds 페이지를 활용하여 프로젝트를 만들었습니다.
			</br></br>
			이 서버에 Tomcat 서버를 설치하여 로컬에서 springboot의 프로젝트를 만들어 GIT로 전송할 다리를 만든 다음, 동적인 콘텐츠 생성과 관리를 수행하는 기능을 구현할 수 있었습니다.
			</br></br>
			제가 이렇게 만든 홈페이지는 저에게 실제로 개발자가 된 경험을 주었고, 더 나은 실력 발전을 위한 첫 걸음이라고 생각합니다. 회사에 입하하여 경험을 더 키울 수 있으면 그만큼 더 배울수 있을것이라고 확신합니다.
		</span>
		</br><span class="space20"></span></br>
		<div class="font_about_me_content">AWS의 EC2 인스턴스 화면 </div>
		</br><span class="space10"></span>
		<img name="div_t3_image" class="border3_black width95p" src="${pageContext.request.contextPath}/resources/siteStep/aws-step_b.png"/>
		
		</br><span class="space20"></span></br>
		<div class="font_about_me_content">AWS의 VPC/라우팅 테이블 화면 </div>
		</br><span class="space10"></span>
		<img name="div_t3_image" class="border3_black width95p" src="${pageContext.request.contextPath}/resources/siteStep/vpc_route-step_a.png"/>
		
		</br><span class="space20"></span></br>
		<div class="font_about_me_content">AWS의 보안 그룹 화면 </div>
		</br><span class="space10"></span>
		<img name="div_t3_image" class="border3_black width95p" src="${pageContext.request.contextPath}/resources/siteStep/security-step_b.png"/>
		
		<%-- </br><span class="space20"></span></br>
		<div class="font_about_me_content">Tomcat의 포트 연결 및 webapp 설정 화면 </div>
		</br><span class="space10"></span>
		<img name="div_t3_image" class="border3_black width95p" src="${pageContext.request.contextPath}/resources/siteStep/tomcat-step_d.png"/> --%>

	
	</div>


</div>
	
</body>