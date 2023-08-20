<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div id="contents_align_div_rv4" class="contents_align_div ">

	<div id="contents_align_div_rvcon4" class="contents_wrap_t3 ">
	
		<div class="font_about_me">
			<img class="icon_40" src="${pageContext.request.contextPath}/resources/icon_loginview/free-icon-speech-bubble-181506.png"/>aop 활용
			<button class="btn_rv_top" onclick="javascript:fn_returnIntro();" type="button"></button>
		</div>
		
		</br><span class="space10"></span></br>
		
		<div class="font_about_me_content">
			<img class="icon_20" src="${pageContext.request.contextPath}/resources/icon_loginview/edit_icon_250480.png"/>  aop 기능으로 세션 관리
		</div>
		</br><span class="space20"></span></br>
		<span>
			홈페이지 내부 관리자용 기능 접속에 사용할 세션 관리 기능을 어떻게 구현할까 생각하다가 aop기능으로 코드 중복을 줄이고 재사용성을 높일 수 있는지에 대한 아이디어를 얻게 되었습니다.
			</br></br>			
			@Before, @After과 같은 어노테이션을 이용하여 비교적 쉽게 트리거를 작동시킴으로서 세션을 생성하고, 세션을 검증하는 메소드를 만들 수 있었습니다.
			</br></br>			
			지금 작성한 세션관리 기능은 매우 간단하지만, 기본적인 mvc 기능 외에도 이런 부가적인 기능을 활용해서 비교적 코드재활용 빈도를 높이고 프로젝트 제작 기간을 단축하는 법을 배웠습니다.
			 
		</span>
		</br><span class="space20"></span></br>
		<div class="font_about_me_content">aop를 활용한 로그인 세션 관리 </div>
		</br><span class="space10"></span>
		<img name="div_t3_image" class="border3_black width95p" src="${pageContext.request.contextPath}/resources/siteStep/session-step_d.png"/>

	
	</div>


</div>