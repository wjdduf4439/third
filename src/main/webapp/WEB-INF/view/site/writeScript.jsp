<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script>

$( document ).ready(function() {		
	
	//게시판 최초등록 시 보여주기 switch만들기
	<%--  
		
		현재 게시판을 등록하면, 삭제 버튼을 누를시 바로 삭제되는데, 이걸 완화하기 위한 보여주기 취소 버튼 만들기
		목록 화면에서 삭제 표시 만들기
		<c:if test="${resultList.del_chk eq 'Y'}"> <button class="btn05 width30" type="button">삭제</button> </c:if>

	--%>
	
	var tCode = "<c:out value= '${not empty resultList.templateType ? resultList.templateType : \"NONE\"}' />";
	var templateTypeOptionInputUrl = "<c:url value='/site/templateTypeOptionInput.go?tCode=" + tCode + "'/>";
	
	fn_templateTypeOptionInput(templateTypeOptionInputUrl);
	
	<c:if test="${not empty resultList }">
	
		var placeRowStr = "${resultList.placeRow}";
		var placeRowArray = placeRowStr.split(",");
		
		var placeWidthStr = "${resultList.placeWidth}";
		var placeWidthArray = placeWidthStr.split(",");
		
		var tCode = '${resultList.templateType}';
		
		var siteUpdateCheckboxUrl = "<c:url value='/site/siteUpdateInput.go?tCode=" + tCode + "&placeRowStr=" + placeRowStr + "'/>";
		
		fn_siteUpdateCheckbox(siteUpdateCheckboxUrl);
	
		
		var siteUpdateWidthInputUrl = "<c:url value='/site/siteUpdateWidthInput.go?placeRowStr=" + placeRowStr + "&placeWidthStr=" + placeWidthStr + "'/>";
		fn_siteUpdateWidthInput(siteUpdateWidthInputUrl);
		
		//fileUploadType 부분 체크박스 값 불러오기
		let fileUploadTypeVal = '${resultList.fileUploadType}';
		let fileUploadTypeArr = [];
		fileUploadTypeArr = fileUploadTypeVal.split(",");
		
		for( let i = 0; i < fileUploadTypeArr.length; i++ ){
			
			$("#fileUploadType_" + fileUploadTypeArr[i]).prop("checked", true);
		}
	
	</c:if>
	
});

$(document).on("change","#templateTypeSelect",function(){

    //템플릿 옵션 변경 이벤트 바인딩
    
	fn_templateCheckBox("<c:url value='/site/siteFieldInput.go?tCode=" + $("#templateTypeSelect").val() + "'/>");

});

function fn_insert(){
	/* 
	fn_checked(-1) 을 호출시켜서 placeRowVal input과 placeWidthVal input 값을 재지정시킨다
	fn_fileUploadTypeChecked(-1) 을 호출시켜서 fileUploadType_chkbox input 값을 재지정시킨다
	 */
	fn_checked(-1);
	fn_fileUploadTypeChecked(-1);
	
	if(fn_validate() == false) { return;}
	
	if (!confirm("등록하시겠습니까?")) { return; }
	
	$("#templateType").val($("#templateTypeSelect option:selected").val());
	
	//alert('$("#templateTypeSelect option:selected").val() : ' + $("#templateTypeSelect option:selected").val());
	
	let url = "";
	if($("#templateTypeSelect option:selected").val() == "T0SF"){ //select-option 태그를 가져오는 val함수

		
		<c:if test="${not empty resultList }"> 
			url = "<c:url value='/site/siteUpdate.go'/>";
		</c:if>
		<c:if test="${empty resultList }">
			url = "<c:url value='/site/siteInsert.go'/>";
		</c:if>
	
	}
	
	let frmData = $("#frm").serializeArray();
 	let frmData_json = JSON.stringify(objectifyForm(frmData));
 	
 	$.ajax({      
        type:"post",  
        url : url,
        async: true,
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




</script>