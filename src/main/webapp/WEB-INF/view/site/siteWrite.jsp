<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:import url="/AdminHeader.go" />
<script>

$( document ).ready(function() {		
		
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

var firstlen = 0;

function fn_checked(m){
	
		var placeRowVal = '';
		var placeWidthVal = '';
		
		var placeRowArr = [];
		var placeWidthArr = [];
		
		$("input:checkbox[name=placeRow]:checked").each(function(i){
			
			if(this.value != m){
				//체크하지 않은 부분의 width정보를 모아서 별도로 배열에 보관
				console.log(this.value + " - " + m);
				
				placeRowVal += this.value;
				placeRowVal += ',';
				
				//console.log($('#placeWidthOption'+i+'').val());
				placeWidthVal += $('#placeWidthOption'+this.value+'').val();
				placeWidthVal += ',';	
				
			}else{
				//체크한 부분은 공백값이 당연하기 때문에 배열 내에 빈 공간을 생성
				placeRowVal += ',';
				placeWidthVal += ',';
				
			}
			
		});
		placeRowVal = placeRowVal.substring(0,placeRowVal.length-1);
		placeWidthVal = placeWidthVal.substring(0,placeWidthVal.length-1);
		
		var placeRowArr = placeRowVal.split(",");
		var placeWidthArr = placeWidthVal.split(",");
		
		console.log("placeRowVal : " + placeRowVal);
		console.log("placeWidthVal : " + placeWidthVal);

	$("#lengthInput").empty();

	var template = '';
	
	$("input:checkbox[name=placeRow]:checked").each(function(i){ 
		
		template += '<input  type="number" id="placeWidthOption'+this.value+'" name="placeWidthOption'+this.value+'" class ="width150" value="" placeholder="속성'+(i+1)+'" /> </br>';
		
	})
	
	$("#lengthInput").append(template);
	
	//insert 시 체크박스를 다시 만들고 값을 다시 배열하는 작업
	
	$("input:checkbox[name=placeRow]:checked").each(function(i){
		
		if(this.value == placeRowArr[i]){
			
			console.log(this.value + " : " + placeRowArr[i]);
			$('#placeWidthOption'+this.value+'').val(placeWidthArr[i]);
			
		}
		
	});
	
	//추가한 체크박스의 값을 서로 매칭시켜 체크박스 추가를 시켜도 width 값이 유지되도록 함, update시 result값을 고정값으로 받고 template에 적용함
	//row와 width 값을 매칭시키는 이중배열 생성
	//새로운 row 를 체크했을 시 해당 배열에는 값을 미배정
	//if index i+2 == placerowwidth[i]의 값	
		
}

function fn_fileUploadTypeChecked(m){
	
	let fileUploadTypeVal = '';
	let fileUploadTypeArr = [];
	
	$("input:checkbox[name=fileUploadType_chkbox]:checked").each(function(i){ fileUploadTypeVal +=  this.value; fileUploadTypeVal +=  ',' });
	fileUploadTypeVal = fileUploadTypeVal.substring(0,fileUploadTypeVal.length-1);
	$('#fileUploadType').val(fileUploadTypeVal);
	
}

function fn_template(){
	
	
	var template = '';
	
	var tCode = $("#templateTypeSelect").val();
	
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
	
	return true;
}

function fn_insert(){
	
	
	
	var placeWidthVal = '';
	
	$("input:checkbox[name=placeRow]:checked").each(function() {

		placeWidthVal += $('#placeWidthOption'+this.value+'').val();
		placeWidthVal += ',';

	});
	
	/*for(var i = 0; i < $("input:checkbox[name=placeRow]:checked").length; i++){
		
		placeWidthVal += $('#placeWidthOption'+i+'').val();
		placeWidthVal += ',';
		
	}*/
	
	placeWidthVal = placeWidthVal.substring(0,placeWidthVal.length-1);
	$('#placeWidth').val(placeWidthVal);
	
	
	/*
	return;
	*/
	
	if(fn_validate() == false) { return;}
	
	if (!confirm("등록하시겠습니까?")) { return; }
	
	$("#templateType").val($("#templateTypeSelect option:selected").val());
	
	if($("#templateTypeSelect option:selected").val() == "0"){ //select-option 태그를 가져오는 val함수

		
		<c:if test="${not empty resultList }"> 
			document.frm.action = '<c:url value="/site/siteUpdate.go"/>';
		</c:if>
		<c:if test="${empty resultList }">
			document.frm.action = '<c:url value="/site/siteInsert.go"/>';
		</c:if>
	
	}else if($("#templateTypeSelect option:selected").val() == "1"){

		
		<c:if test="${not empty resultList }"> 
			document.frm.action = '<c:url value="/site/siteUpdate1.go"/>';
		</c:if>
		<c:if test="${empty resultList }">
			document.frm.action = '<c:url value="/site/siteInsert1.go"/>';
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
			            <td colspan="100">
				            <select  id="templateTypeSelect" name="templateTypeSelect" class="selectText width150" onchange="javascript:fn_template();" <c:out value="${ not empty resultList ? 'disabled' : '' }" /> >
				            	<!-- 차후 자바스크립트 수정 필요! -->
				            	<option value="9">::: 선택 :::</option>
				            	<option id="templateTypeOption" value="0" <c:out value="${resultList.templateType == '0' ? 'selected' : ''}"/> <c:out value="${ not empty resultList ? 'disabled' : '' }" /> >일반</option>
				            	<option id="templateTypeOption" value="1" <c:out value="${resultList.templateType == '1' ? 'selected' : ''}"/> <c:out value="${ not empty resultList ? 'disabled' : '' }" /> >템플릿</option>
				            </select>
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