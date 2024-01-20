
$("#ajaxProcess").ready(function() {
	
	
	
	var tCode = '';
	var templateTypeOptionInputUrl = '';
	
	tCode = $("#tCode").val();
	templateTypeOptionInputUrl = $("#templateTypeOptionInputUrl").val() + "?tCode=" + tCode;
	
	
});


// 등록/수정 화면에서 템플릿 유형 옵션 변경시 필드에 맞는 체크박스를 표시시켜주는 함수 
function fn_templateCheckBox(url){
	
	$.ajax({      
        type:"get",  
        url : url,
        //dataType : text 옵션으로 viewresolver가 반응하지 않게 하기
        dataType : 'text',
        contentType : false,
        success:function(args){   
        	//alert(args);
        	$("#templateplace").empty();
        	$("#templateplace").append(args);
        }
    }); 
	
}


//수정 화면에 진입시 템플릿 유형에 따라서 필드에 맞는 체크박스를 표시시켜주는 함수 
function fn_templateTypeOptionInput(url){
		
	$.ajax({      
	    type:"get",  
	    url : url,
	    //dataType : text 옵션으로 viewresolver가 반응하지 않게 하기
	    dataType : 'text',
	    contentType : false,
	    success:function(args){   
	    	//alert(args);
	    	$("#templateTypePlace").empty();
	    	$("#templateTypePlace").append(args);
	    }  
	}); 
		
}



//수정 화면에 진입시 템플릿 유형에 따라서 필드에 맞는 체크박스를 표시시켜주는 함수 
function fn_siteUpdateCheckbox(url){
		
	//sitefield checkbox ajax 넣기
	$.ajax({      
        type:"get",  
        url : url,
        //dataType : text 옵션으로 viewresolver가 반응하지 않게 하기
        dataType : 'text',
        contentType : false,
        success:function(args){   
        	//alert(args);
        	$("#templateplace").empty();
        	$("#templateplace").append(args);
        	
        } 
    }); 
}


function fn_siteUpdateWidthInput(url){
	
	$.ajax({      
        type:"get",  
        url : url,
        //dataType : text 옵션으로 viewresolver가 반응하지 않게 하기
        dataType : 'text',
        contentType : false,
        success:function(args){   
        	//alert(args);
        	$("#lengthInput").empty();
        	$("#lengthInput").append(args);
        	
        }
    }); 
}


function fn_validate(){
	//항목 공백 검색
	//alert(document.frm.brthdy.value);
	
	if($("#title").val() == "")
		{ alert("제목 항목은 필수 항목입니다"); return false; }
	
	if($("#adminName").val() == "")
		{ alert("작성자 항목은 필수 항목입니다"); return false; }
	
	if($("#formCode").val() == "")
		{ alert("소속 항목 설정은 필수 항목입니다"); return false; }
	
	if($("#templateTypeSelect option:selected").val() == "")
		{ alert("유형 설정은 필수 항목입니다"); return false; }
	
	if($("input[name=placeRow]").val() == undefined )
		{ alert("표시항목 속성은 하나이상 등록되어야 합니다."); return false; }
	
	if($("input[name=maxFileUploadNumber]").val() == "" )
		{ alert("첨부파일 허용 옵션은 1이상 등록되어야 합니다."); return false; }
	
	if($("input[name=fileUploadType]").val() == "" )
		{ alert("허용 파일 타입은 하나이상 등록되어야 합니다."); return false; }
	
	return true;
}



function fn_delete(url){
	
	if($("#del_chk").val() == "N"){
		if (!confirm("비활성화하시겠습니까? 사용자 뷰 화면에서 보이지 않게 됩니다.")) { return; }	
	}else if($("#del_chk").val() == "Y"){
		if (!confirm("삭제하시겠습니까? 삭제한 데이터는 복구가 불가능합니다.")) { return; }
	}
	
	//let url = "<c:url value='/site/siteDelete.go'/>";

	let frmData = $("#frm").serializeArray();
 	let frmData_json = JSON.stringify(objectifyForm(frmData));
	
	$.ajax({      
        type:"post",  
        url : url,
        //dataType : text 옵션으로 viewresolver가 반응하지 않게 하기
        data : frmData_json,
        dataType : 'json',
        contentType : 'application/json; charset=utf-8',  
        success:function(args){   
        	//alert("args.returnPage : " + args.returnPage);
        	$("#frm").attr("action",args.returnPage );
        	$("#frm").submit();
        }
    });
	
	
}

function fn_restore(url){
	
	if (!confirm("복구하시겠습니까?")) { return; }
 	
 	let frmData = $("#frm").serializeArray();
 	let frmData_json = JSON.stringify(objectifyForm(frmData));
 	
	$.ajax({      
        type:"post",  
        url : url,
        data : frmData_json,
        dataType : 'json',	//dataType : text 옵션으로 viewresolver가 반응하지 않게 하기
        contentType : 'application/json;charset=utf-8', 
        success:function(args){
        	
        	$("#frm").attr("action",args.returnPage );
        	$("#frm").submit();
        }
    });
	
}

function fn_back(url){
	
	$("#frm").attr("action",url );
	$("#frm").submit();
	
}