

	//브라우저 해상도 조절시 ui 크기 조절
	$( window ).resize(function() { fn_setNavWidth(); });	
	
	$(document).ready(function(){ fn_setNavWidth();  });
	
	//네비게이션 div 높이 조정
	function fn_setNavWidth(){
		
		var windowWidth = $( window ).width();
		var t1_width = $( "#contents_align_div_t1" ).width();
		
		var navContent_height = $("#navContent").height();
		
		
		if (windowWidth >= 1170) {
			//창 가로 크기가 1170 보다 클 경우
			//div_t3_image 네임 width45p 설정
			
				$("#navContent").css('height', '50px');
				
				
			} else if(windowWidth < 1170 && windowWidth > 550 ) {
			//창 가로 크기가 1170 미만일 경우 
			//div_t3_image 네임 width95p 설정
			
				$("#navContent").css('height', '100px');
				
				
			} else if(windowWidth <= 550) {				
				//창 가로 크기가 400 미만일 경우 
				//contents_align_div1_mv + cont height 200으로 설정
				
				$("#navContent").css('height', '150px');
				
			}
		
	}
	
	//동적 페이지 폼 닫기
	$(document).on("click", "#loginmask", function(){
	    //alert("동작확인");
		//console.log("loginmask clicked");
	    $('#loginmask').hide();
	    $('#loginformBox').hide();
	});
	
	//로그인박스 표시 애니메이션 및 기능
 	function fn_LoginForm(){
 		
 		var maskHeight = $(document).height();
        var maskWidth = $(window).width(); 
        /*
        console.log(maskWidth);
        console.log(maskHeight);
        */
        $('#loginmask').css({'width':maskWidth,'height':maskHeight}); 
        
        $('#loginmask').fadeIn(1000);
        $('#loginmask').fadeTo("slow",0.8);
        
        $('#loginmask').show();
        $('#loginformBox').show();
 		
 	}
							 	
 	//로그인 박스 주변의 검은 막을 눌렀을 때
    $('#loginmask').click(function () { $('#loginmask').hide(); });
	
	
	//상단 메뉴 폼을 보여주는 기능							
	function fn_show(){ $(".sub-menu").css("visibility", "visible"); }
	
	function fn_noshow(){ $(".sub-menu").css("visibility", "hidden"); }
	
	
	//관리자용 메뉴 들어가는 기능
	function fn_SiteLink(siteCode, url){
	
		
		let siteCode_jsontest = { 'siteCode_json':siteCode };
		$('#siteCode_json').val(JSON.stringify(siteCode_jsontest));
		
		document.sitefrm.siteCode.value = siteCode;
		document.sitefrm.action = url;
		document.sitefrm.submit();
		
	}
	
	//로그아웃 버튼을 눌렀을 때
	function fn_loginOut(url){  
	    
		document.userSitefrm.action = url;
		document.userSitefrm.submit();		
		
	}			 	
						 	
	//게시판 이동
	function fn_ViewSiteLink(siteCode, url){
	
		document.userSitefrm.siteCode.value = siteCode;
		document.userSitefrm.action = url;
		document.userSitefrm.submit();
		
	}	