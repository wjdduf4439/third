<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
			
			기본적인 CRUD게시판을 생성하고 관리하는데 필요한 요소들을 만들어보고 보니, 생각 이상으로 관리하고 주시해야할 요소가 많음을 느꼈습니다.
			</br></br>
			정적인 게시판과 다르게 동적인 게시판은, 게시판의 생성 시에 DB에 적용할 필드 명까지 관리를 해야한다는 추가적인 작업이 더 필요하였고
			</br></br>
			대학교에서 정적인 게시판을 만드는 것으로 멈춰있는 저에게는 정말 난감한 요소였지만 위대한 구글의 힘으로 MYBATIS기능의 검색을 하며 알아낸 결과 구현할 수 있었습니다. 
			 
			</br></br></br>
			
			1차적으로 MYBATIS에서 CREATE SQL 구문을 배워서 적용하여 테이블을 동적으로 생성하는 법을 배웠고
			</br></br>
			2차적으로 게시판 유형별로 적용시킬 FIELD명을 별도의 테이블에 보관해서 "게시판의 유형" - "게시판의 필드명" - "게시판의 필드 정보"를 보관하여 관리하는 구조의 관계를 db 내부에 만들었습니다. 
			</br></br>
			앞으로 회사에 입사하여 견문을 넓힐 기회가 생긴다면, 회사생활을 하면서 받아들이고 적용시켜볼만한 기능들 제가 생각해서 배운 구조를 톻해 여러 형태의 기능들을 만들어볼 수 있도록 발전해 나가고 싶습니다.
			
		</span>
			
		</br><span class="space20"></span></br>
		<div class="font_about_me_content">항목 생성 과정 </div>
		</br><span class="space10"></span></br>
		<img name="div_t3_image" class="border3_black width95p" src="${pageContext.request.contextPath}/resources/siteStep/form-step_b.png"/>
		
		</br><span class="space20"></span></br>
		<div class="font_about_me_content">게시판 생성 과정</div>
		</br><span class="space10"></span></br>
		<img name="div_t3_image" class="border3_black width95p" src="${pageContext.request.contextPath}/resources/siteStep/site-step_a.png"/>
		
		</br><span class="space20"></span></br>
		<div class="font_about_me_content">외부키 설정 및 게시판 생성 시 화면에 적용</div>
		</br><span class="space10"></span></br>
		<img name="div_t3_image" class="border3_black width95p" src="${pageContext.request.contextPath}/resources/siteStep/foreign-key-step_end.png"/>
	
	</div>


</div>