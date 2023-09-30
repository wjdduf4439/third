<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div id="contents_align_div_rv6" class="contents_align_div ">

	<div id="contents_align_div_rvcon6" class="contents_wrap_t3 ">
	
		<div class="font_about_me">
			<img class="icon_40" src="${pageContext.request.contextPath}/resources/icon_loginview/free-icon-speech-bubble-181506.png"/>이미지 내용 첨부 기능 구현
			<button class="btn_rv_top" onclick="javascript:fn_returnIntro();" type="button"></button>
		</div>
		
		</br><span class="space10"></span></br>
		
		<div class="font_about_me_content">
			<img class="icon_20" src="${pageContext.request.contextPath}/resources/icon_loginview/edit_icon_250480.png"/>  이미지 내용 첨부 기능 구현
		</div>
		</br><span class="space20"></span></br>
		<span>
			홈페이지 내부에서 사용자가 여러 가지 형태의 글을 작성하기 위한 내용 첨부 부분에 이미지를 첨부할 수 있는 기능을 구현해 보았습니다.
			</br></br>			
			처음에는 그냥 이미지 태그 텍스트를 집어넣고 이미지를 표시하는 기능인줄 알았지만, 웹 프로젝트의 뷰 태그 간의 자바스크립트 바인딩 접근성 문제로 생각보다 어려운 작업이었습니다.
			</br></br>			
			그래서 기존에 사용하던 네이버의 스마트에디터를 버리고, 구현된 기능이 많은 ckeditor을 사용하기로 했습니다. 
			</br></br>			
			결과적으로 구현을 완료해서 개발자로서의 경험치가 쌓이는 기분이었습니다. 
			
			</br></br></br>
			
			일단 글작성 시 이미지 내용 첨부 기능을 구현하기 위해서 ckEditor5을 사용했습니다.
			</br></br>
			세부적인 내용을 찾아서 구현하기 위해 ckEditor5의 API사이트를 참고해서 이미지 내용 첨부 기능을 사용할 수 있었습니다.
			</br></br>
			ckEditor5의 ckfinder기능이 이미지 첨부 기능에 해당되기 때문에 관련 정보를 많이 찾아보았습니다.
			</br></br>
			ckfinder5의 이미지 첨부 기능은 별도의 자바스크립트 문서로 구성된 클래스를 어댑터로 선언한 다음 사용해야 했기 때문에 새로운 경험이었습니다.
			
		</span>
		</br><span class="space20"></span></br>
		<div class="font_about_me_content">이미지 첨부 기능을 구현하기 위한 알고리즘 구성  </div>
		</br><span class="space10"></span>
		<img name="div_t3_image" class="border3_black" src="${pageContext.request.contextPath}/resources/siteStep/editorImage-step_a.png"/>
		<!-- 처음 프로세스 경우의 수를 언급 후 버튼을 하나씩 만들어서 각 과정을 설명하는 과정 필요 -->
		
		</br><span class="space20"></span></br>
		<div class="font_about_me_content">js로 구현한 이미지 내용첨부 용도의 ckeditor5의 ckfinder 어댑터(프론트)  </div>
		</br><span class="space10"></span>
		<img name="div_t3_image" class="border3_black" src="${pageContext.request.contextPath}/resources/siteStep/editorImage-step_b.png"/>
		
		</br><span class="space20"></span></br>
		<div class="font_about_me_content">글 작성 후 java단에서 db에 등록하기 위한 과정(백엔드)  </div>
		</br><span class="space10"></span>
		<img name="div_t3_image" class="border3_black" src="${pageContext.request.contextPath}/resources/siteStep/editorImage-step_c.png"/>
		

	
	</div>


</div>