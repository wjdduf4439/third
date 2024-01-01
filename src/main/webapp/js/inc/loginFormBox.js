

	$(document).ready(function(){ fn_setLoginFormWidth();  });
		
	$( window ).resize(function() {
		fn_setLoginFormWidth();
	});	

	function fn_access(){ document.loginHomeVO.submit(); }
		
	function fn_setLoginFormWidth(){
		
		var windowWidth = $( window ).width();
	   
		$( "#loginmask").css('width', '100%');
		
			if (windowWidth >= 1170) {
			//창 가로 크기가 1170 보다 클 경우
				
				$( "#loginformBox").css('left', '30%');
				$( "#loginformBox").css('margin-left', '30px');
				$( "#loginformBox").css('width', '35%');
				$( "#loginformBox").css('padding', '25px 0px 25px 25px');
				
			} else if(windowWidth < 1170 && windowWidth > 550 ) {
			//창 가로 크기가 1170 미만일 경우 
				$( "#loginformBox").css('left', '25%');
				$( "#loginformBox").css('margin-;eft', '30px');
				$( "#loginformBox").css('width', '45%');
				$( "#loginformBox").css('padding', '25px 0px 25px 25px');

			} else if(windowWidth <= 550) {				
				//창 가로 크기가 400 미만일 경우 
				
				$( "#loginformBox").css('left', '0%');
				$( "#loginformBox").css('margin-left', '10px');
				$( "#loginformBox").css('width', (windowWidth - 25) + 'px');
				$( "#loginformBox").css('padding', '10px 0px 10px 10px');
				
			}
		   
	}