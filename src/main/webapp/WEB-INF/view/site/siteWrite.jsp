<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:import url="/AdminHeader.go" />
<script>

$( document ).ready(function() {		
		
	//유형 부분 select input박스 ajax 만들기 
	/*  
		
		
	*/
	
	var tCode = '';
	
	<c:if test="${not empty resultList }">
		tCode = '${resultList.templateType}';
		var url = "<c:url value='/site/templateTypeOptionInput.go?tCode=" + tCode + "'/>";
	</c:if>
	<c:if test="${empty resultList }">
		tCode = 'NONE';
		var url = "<c:url value='/site/templateTypeOptionInput.go?tCode=" + tCode + "'/>";
	</c:if>
	
	$.ajax({      
        type:"get",  
        url : url,
        async: true,
        //dataType : text 옵션으로 viewresolver가 반응하지 않게 하기
        dataType : 'text',
        processData : false,
        contentType : false,
        beforeSend : function(xmlHttpRequest){
        	   xmlHttpRequest.setRequestHeader("AJAX", "true");
        	  },    
        success:function(args){   
        	//alert(args);
        	$("#templateTypePlace").empty();
        	$("#templateTypePlace").append(args);
        },   
        error:function(e){  
            alert("siteajax 실패" + e.responseText);  
        }  
    }); 
	
	<c:if test="${not empty resultList }">
	
		var placeRowStr = "${resultList.placeRow}";
		var placeRowArray = placeRowStr.split(",");
		
		var placeWidthStr = "${resultList.placeWidth}";
		var placeWidthArray = placeWidthStr.split(",");
		
		var tCode = '${resultList.templateType}';
		
		var url = "<c:url value='/site/siteUpdateInput.go?tCode=" + tCode + "&placeRowStr=" + placeRowStr + "'/>";
		
		//sitefield checkbox ajax 넣기
		$.ajax({      
	        type:"get",  
	        url : url,
	        async: true,
	        //dataType : text 옵션으로 viewresolver가 반응하지 않게 하기
	        dataType : 'text',
	        processData : false,
	        contentType : false,
	        beforeSend : function(xmlHttpRequest){
	        	   xmlHttpRequest.setRequestHeader("AJAX", "true");
	        	  },    
	        success:function(args){   
	        	//alert(args);
	        	$("#templateplace").empty();
	        	$("#templateplace").append(args);
	        	
	        	/* var template = '';
	    		for(var i = Number(placeRowArray[0]); i < Number(placeRowArray[0]) + placeRowArray.length; i++){
	    			
	    			template += '<input  type="number" id="placeWidthOption'+i+'" name="placeWidthOption'+i+'" class ="width150" value="" placeholder="속성'+i+'" /></br>';
	    			
	    		}
	        	
	        	$("#lengthInput").append(template);
	        	$("input:checkbox[name=placeRow]:checked").each(function(i){ 
	    			
	    			$("#placeWidthOption"+this.value+"").after(":"+$(this).attr('placeholder'));
	    			$("#placeWidthOption"+this.value+"").val(placeWidthArray[i]);
	    			
	    		});  */
	        	
	        },   
	        error:function(e){  
	            alert("sitefieldajax 실패" + e.responseText);  
	        }  
	    }); 
	
		
		var url = "<c:url value='/site/siteUpdateWidthInput.go?placeRowStr=" + placeRowStr + "&placeWidthStr=" + placeWidthStr + "'/>";
		
		//sitefield checkbox ajax 넣기
		$.ajax({      
	        type:"get",  
	        url : url,
	        async: true,
	        //dataType : text 옵션으로 viewresolver가 반응하지 않게 하기
	        dataType : 'text',
	        processData : false,
	        contentType : false,
	        beforeSend : function(xmlHttpRequest){
	        	   xmlHttpRequest.setRequestHeader("AJAX", "true");
	        	  },    
	        success:function(args){   
	        	//alert(args);
	        	$("#lengthInput").empty();
	        	$("#lengthInput").append(args);
	        	
	        },   
	        error:function(e){  
	            alert("sitewidthajax 실패" + e.responseText);  
	        }  
	    }); 
		
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
    console.log("변경전");
	fn_template();
	//$("#templateTypeSelect").val()의 값이 ''판정이라 제대로된 태그를 불러오지 않음
	console.log("변경후");

});


function fn_template(){
	
	
	var template = '';
	
	var tCode = $("#templateTypeSelect").val();
	console.log("siteFieldInput tCode : " + tCode);
	
	var url = "<c:url value='/site/siteFieldInput.go?tCode="+tCode+"'/>";
	
	//로그 기입 ajax 넣기
	$.ajax({      
        type:"get",  
        url : url,
        async: true,
        //dataType : text 옵션으로 viewresolver가 반응하지 않게 하기
        dataType : 'text',
        processData : false,
        contentType : false,
        beforeSend : function(xmlHttpRequest){
        	   xmlHttpRequest.setRequestHeader("AJAX", "true");
        	  },    
        success:function(args){   
        	//alert(args);
        	$("#templateplace").empty();
        	$("#templateplace").append(args);
        },   
        error:function(e){  
            alert("siteajax 실패" + e.responseText);  
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

function fn_insert(){
	
	let placeWidthVal = '';
	let fileUploadTypeVal = '';
	
	//각 게시판의 표시 field 길이 설정
	$("input:checkbox[name=placeRow]:checked").each(function() {

		placeWidthVal += $('#placeWidthOption'+this.value+'').val();
		placeWidthVal += ',';

	});
	
	placeWidthVal = placeWidthVal.substring(0,placeWidthVal.length-1);
	$('#placeWidth').val(placeWidthVal);
	
	//허용 파일 형식 데이터 설정
	$("input:checkbox[name=fileUploadType_chkbox]:checked").each(function() {

		fileUploadTypeVal += $('#fileUploadType_'+this.value+'').val();
		fileUploadTypeVal += ',';

	});
	fileUploadTypeVal = fileUploadTypeVal.substring(0,fileUploadTypeVal.length-1);
	$('#fileUploadType').val(fileUploadTypeVal);
	
	/*
	return;
	*/
	
	if(fn_validate() == false) { return;}
	
	if (!confirm("등록하시겠습니까?")) { return; }
	
	$("#templateType").val($("#templateTypeSelect option:selected").val());
	
	//alert('$("#templateTypeSelect option:selected").val() : ' + $("#templateTypeSelect option:selected").val());
	
	if($("#templateTypeSelect option:selected").val() == "T0SF"){ //select-option 태그를 가져오는 val함수

		
		<c:if test="${not empty resultList }"> 
			document.frm.action = '<c:url value="/site/siteUpdate.go"/>';
		</c:if>
		<c:if test="${empty resultList }">
			document.frm.action = '<c:url value="/site/siteInsert.go"/>';
		</c:if>
	
	}else{
		
		alert("update실패" + $("#templateTypeSelect option:selected").val());
		
	}
	
	document.frm.submit();
	
}

function fn_delete(){
	
	if (!confirm("삭제하시겠습니까? 삭제한 데이터는 복구가 불가능합니다.")) { return; }
	
	
	document.frm.action = '<c:url value="/site/siteDelete.go"/>';
	document.frm.submit();
	
}

function fn_back(){
	
	document.frm.action = '<c:url value="/site/siteAdmin.go"/>';
	document.frm.submit();
	
}


</script>


<script type="text/javascript" src="${pageContext.request.contextPath}/js/site/checkBoxFunction.js"></script> 


<div class="contents_wrap">
		
		<div class="contents" >
			<div class="space20"></div>
			<h3 class="fl"><i class="icono-list"></i>   게시판 관리</h3>
			<div class="essential fr">
		      <i class="icono-asterisk"></i>필수입력항목
		    </div>
			<form id="frm" name="frm" method="post" enctype="multipart/form-data" >
				<input type="hidden" name="pageIndex" value="${searchVO.pageIndex}" />
				<input type="hidden" name="pageUnit" value="${searchVO.pageUnit}" />
				<input type="hidden" name="searchCnd" value="${searchVO.searchCnd}" />
				<input type="hidden" name="searchWrd" value="${searchVO.searchWrd}" />
				
				<input type="hidden" name="recordCountPerPage" value="${searchVO.recordCountPerPage}" />
		
				<input type="hidden" id="siteCode" name="siteCode" value="${resultList.siteCode }"/>
				<input type="hidden" id="templateType" name="templateType" value="" />
				
				<!-- <input type="hidden" id="placeRow" name="placeRow" value="${resultList.placeRow}" /> -->
				<input type="hidden" id="placeWidth" name="placeWidth" value="${resultList.placeWidth}" />
				<input type="hidden" id="fileUploadType" name="fileUploadType" value="${resultList.fileUploadType}" />
				
				<input type="hidden" id="context" name="context" value=""/>
				<table class="tablewrite nohover">
					<colgroup>
						<col class="width150">
						<col class="width400">
						<col class="width150">
						<col class="width400">
					</colgroup>
					
					<tr>
						<th colspan="1"> <i class="icono-asterisk"></i>   제목</th>
						<td >
							<input type="text" name="title" id="title" class="width500" value="${resultList.title }"  />
						</td>
						<!--
						 <th colspan="1">공지여부확인</th>
						<td >
							<input type='checkbox' name='noticeSwitch' value='1' <c:out value="${resultList.noticeSwitch == '1' ? 'checked' : ''}"/> <c:out value="${resultList.templateType == '1' ? 'disabled' : ''}" /> ></td>
						</td> 
						-->
					</tr>
					<tr>
						<th> <i class="icono-asterisk"></i>   작성자</th>
						<td class="width200" colspan="3">
							<input type="text" name="adminName" id="adminName" class="width200" value="${resultList.adminName }" />
						</td>
					</tr>
					<tr>
						<th> <i class="icono-asterisk"></i>   소속 항목</th>
						<td class="width200" colspan="3">
							<select class="width150 selectText mb05" id="formCode" name="formCode">
								
								<option value=""  selected>없음(표시안함)</option>
								<c:forEach var="form" items="${formList }" varStatus="status1">
									
									<option value="${ form.formCode }" <c:if test='${resultList.formCode eq form.formCode}'> selected </c:if> >${ form.formName }</option>
								
								</c:forEach>
								
							</select>
						</td>
					</tr>
					<tr>
		        		<th><i class="icono-asterisk"></i>   유형</th>
			            <td id="templateTypePlace" colspan="100">
				            
				            	<!-- 템플릿 표시 성공했으나, onchangeEvent가 제대로 동작하지 않음 -->
				            	<!-- 동적 input 태그 이벤트 바인딩 -->
				            	<!-- https://brunch.co.kr/@ourlove/98 -->
				            	
				            
			            </td>
		        	</tr>
					<tr>
		        		<th><i class="icono-asterisk"></i>   표시항목</th>
			            <td id="templateplace" colspan="3">
							<!-- sitefield 정보 기입 구간 -->
			            </td>
		        	</tr>
		        	<tr>
		        		<th><i class="fa fa-asterisk" aria-hidden="true"></i>표시길이</th>
		        		<td id ="lengthInput" >
		        			<!-- sitewidthinput 정보 기입 구간 -->		        		
		        		</td>
		        	</tr>
		        	<tr>
		        		<th><i class="fa fa-asterisk" aria-hidden="true"></i>첨부파일 허용 갯수 </th>
		        		<td id ="allowFileTypeInput" >	
							여기서 갯수를 저장한 다음, insert, update 기능 시 해당 첨부파일의 갯수를 따지고 허용/반려하는식의 로직 짜기
							</br>
							<input type="text" name="maxFileUploadNumber" id="maxFileUploadNumber" class="width200" value="${resultList.maxFileUploadNumber }" />
		        		</td>
		        	</tr>
		        	<tr>
		        		<th><i class="fa fa-asterisk" aria-hidden="true"></i>허용 파일 타입(추가구성)</th>
		        		<td id ="allowFileTypeInput" >
		        			여기서 체크박스로 여러 타입의 파일 형식을 체크하고 저장하여 db에 저장한 다음, insert, update 기능 시 해당 첨부파일의 파일 형식을 따져 허용/반려하는식의 로직 짜기
		        			</br>        		
		        			<input type="checkbox" id="fileUploadType_jpg" name="fileUploadType_chkbox" value="jpg" placeholder="허용파일형" onclick="javascript:fn_fileUploadTypeChecked('jpg');" >jpg
		        			<input type="checkbox" id="fileUploadType_png" name="fileUploadType_chkbox" value="png" placeholder="허용파일형" onclick="javascript:fn_fileUploadTypeChecked('png');" >png
		        			<input type="checkbox" id="fileUploadType_pdf" name="fileUploadType_chkbox" value="pdf" placeholder="허용파일형" onclick="javascript:fn_fileUploadTypeChecked('pdf');" >pdf
		        			<input type="checkbox" id="fileUploadType_zip" name="fileUploadType_chkbox" value="zip" placeholder="허용파일형" onclick="javascript:fn_fileUploadTypeChecked('zip');" >zip
		        			<input type="checkbox" id="fileUploadType_webp" name="fileUploadType_chkbox" value="webp" placeholder="허용파일형" onclick="javascript:fn_fileUploadTypeChecked('webp');" >webp
		        		</td>
		        	</tr>
				</table>
	
			</form>
			
			<div class="btngroup mt0">
				<button class="btn03 fr" onclick="javascript:fn_back();" type="button">돌아가기</button>
				<c:if test="${empty resultList }"> <button class="btn02 fr" onclick="javascript:fn_insert();" type="button">등록</button> </c:if>
				<c:if test="${not empty resultList}"> <button class="btn02 fr" onclick="javascript:fn_insert();" type="button">수정</button> </c:if>
				<c:if test="${not empty resultList}"> <button class="btn05 fr" onclick="javascript:fn_delete();" type="button">삭제</button> </c:if>
			</div>
		</div>

</div>

</body>