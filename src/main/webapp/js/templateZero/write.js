

function fn_validate(){
	
	if($("#title").val() == "")
		{ alert("제목 항목은 필수 항목입니다."); return false; }
	if($("#frstRegistNm").val() == "")
		{ alert("작성자 이름 항목은 필수 항목입니다."); return false; }
	if($("#writerID").val() == "")
		{ alert("작성자 id 항목은 필수 항목입니다."); return false; }
	
	//var context = oEditors.getById["contexteditor"].getIR();
	//$("#context").val(context);
	//oEditors.getById["contexteditor"].exec("UPDATE_CONTENTS_FIELD", []);
	
	//만약 editor의 내용을 변경할 일이 있으면 setData 메소드 사용
	$("#context").val(editor.getData());
	
	return true;
}

function fn_delete(url){
	
	if($("#del_chk").val() == "N"){
		if (!confirm("비활성화하시겠습니까? 사용자 뷰 화면에서 보이지 않게 됩니다.")) { return; }	
	}else if($("#del_chk").val() == "Y"){
		if (!confirm("삭제하시겠습니까? 삭제한 데이터는 복구가 불가능합니다.")) { return; }
	}
	
	fn_pageReset();
	
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
	
	fn_pageReset();
	
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