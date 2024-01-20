function fn_validate(){
	//항목 공백 검색
	//alert(document.frm.brthdy.value);
	
	if($("#formName").val() == "")
		{ alert("제목 항목은 필수 항목입니다"); return false; }
	
	if($("#frstRegistNm").val() == "")
		{ alert("작성자 항목은 필수 항목입니다"); return false; }
	
	return true;
}

function fn_delete(url){
	

	if($("#del_chk").val() == "N"){
		if (!confirm("비활성화하시겠습니까? 사용자 뷰 화면에서 보이지 않게 됩니다.")) { return; }	
	}else if($("#del_chk").val() == "Y"){
		if (!confirm("삭제하시겠습니까? 삭제한 데이터는 복구가 불가능합니다.")) { return; }
	}

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
	
	document.frm.action = url;
	document.frm.submit();
	
}