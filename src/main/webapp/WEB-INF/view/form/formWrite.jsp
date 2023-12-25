<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:import url="/AdminHeader.go" />
<script>

$(document).ready(function() {
	
	var template = "";
	
	<c:forEach var="site" items="${ siteList }" varStatus="status1">
	
		template += '${ site.title }';
		template += ",";

	</c:forEach>
	
	$("#siteplace").append(template.substr(0,template.length-1));
	
});

function fn_validate(){
	//항목 공백 검색
	//alert(document.frm.brthdy.value);
	
	if($("#formName").val() == "")
		{ alert("제목 항목은 필수 항목입니다"); return false; }
	
	if($("#frstRegistNm").val() == "")
		{ alert("작성자 항목은 필수 항목입니다"); return false; }
	
	return true;
}

function fn_insert(){
	
	if(fn_validate() == false) { return;}
	
	if (!confirm("등록하시겠습니까?")) { return; }
	
	let url = "";
	<c:if test="${not empty resultList }"> 
		url = '<c:url value="/form/formUpdate.go"/>';
	</c:if>
	<c:if test="${empty resultList }">
		url = '<c:url value="/form/formInsert.go"/>';
	</c:if>
	
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
        beforeSend : function(xmlHttpRequest){
     	   xmlHttpRequest.setRequestHeader("AJAX", "true");
        	  },    
        success:function(args){   
        	//alert("args.returnPage : " + args.returnPage);
        	$("#frm").attr("action",args.returnPage );
        	$("#frm").submit();
        },   
        error:function(e){  
            alert("siteajax 실패" + e.responseText);  
        }  
    });
	
}

function fn_delete(){
	

	if($("#del_chk").val() == "N"){
		if (!confirm("비활성화하시겠습니까? 사용자 뷰 화면에서 보이지 않게 됩니다.")) { return; }	
	}else if($("#del_chk").val() == "Y"){
		if (!confirm("삭제하시겠습니까? 삭제한 데이터는 복구가 불가능합니다.")) { return; }
	}
	
	let url = "<c:url value='/form/formDelete.go'/>";

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
        beforeSend : function(xmlHttpRequest){
     	   xmlHttpRequest.setRequestHeader("AJAX", "true");
        	  },    
        success:function(args){   
        	//alert("args.returnPage : " + args.returnPage);
        	$("#frm").attr("action",args.returnPage );
        	$("#frm").submit();
        },   
        error:function(e){  
            alert("siteajax 실패" + e.responseText);  
        }  
    });
	
}

function fn_restore(){
	
	if (!confirm("복구하시겠습니까?")) { return; }
	
	let url = "<c:url value='/form/formRestore.go'/>";
 	
 	let frmData = $("#frm").serializeArray();
 	let frmData_json = JSON.stringify(objectifyForm(frmData));
 	
	$.ajax({      
        type:"post",  
        url : url,
        async: true,
        data : frmData_json,
        dataType : 'json',	//dataType : text 옵션으로 viewresolver가 반응하지 않게 하기
        contentType : 'application/json;charset=utf-8',
        beforeSend : function(xmlHttpRequest){
        	xmlHttpRequest.setRequestHeader("AJAX", "true");
        	  },    
        success:function(args){
        	
        	$("#frm").attr("action",args.returnPage );
        	$("#frm").submit();
        },   
        error:function(e){  
            alert("siteajax 실패" + e.responseText);  
        }  
    });
	
}

function fn_back(){
	
	document.frm.action = '<c:url value="/form/formAdmin.go"/>';
	document.frm.submit();
	
}


</script>

<div class="contents_wrap">
		
		<div class="contents" >
			<div class="space20"></div>
			<h3 class="fl"><i class="icono-list"></i>   항목 관리</h3>
			<div class="essential fr">
		      <i class="icono-asterisk width10"></i>필수입력항목
		    </div>
			<form id="frm" name="frm" method="post" enctype="multipart/form-data" >
				<input type="hidden" name="pageIndex" value="${searchVO.pageIndex}" />
				<input type="hidden" name="pageUnit" value="${searchVO.pageUnit}" />
				<input type="hidden" name="searchCnd" value="${searchVO.searchCnd}" />
				<input type="hidden" name="searchWrd" value="${searchVO.searchWrd}" />
				<input type="hidden" name="recordCountPerPage" value="${searchVO.recordCountPerPage}" />
		
				<input type="hidden" id="del_chk" name="del_chk" value="${resultList.del_chk }"/>
		
				<input type="hidden" id="formCode" name="formCode" value="${resultList.formCode }"/>
				<input type="hidden" id="context" name="context" value=""/>
				<table class="tablewrite nohover">
					<colgroup>
						<col class="width150">
						<col class="width400">
						<col class="width150">
						<col class="width400">
					</colgroup>
					
					<tr>
						<th colspan="1"> <i class="fa fa-asterisk" aria-hidden="true"></i>제목</th>
						<td colspan="3">
							<input type="text" name="formName" id="formName" class="width500" value="${resultList.formName }"  />
						</td>
					</tr>
					<tr>
						<th> <i class="fa fa-asterisk" aria-hidden="true" ></i>작성자</th>
						<td class="width200" colspan="3">
							<input type="text" name="frstRegistNm" id="frstRegistNm" value="관리자" readonly="readonly"  />
						</td>
					</tr>
					<tr>
						<th> <i class="fa fa-asterisk" aria-hidden="true" ></i>소속 게시판</th>
						<td class="width200" colspan="3" id="siteplace">
							
							
						</td>
					</tr>
				</table>
	
			</form>
			
			<div class="btngroup mt0">
				<button class="btn03 fr" onclick="javascript:fn_back();" type="button">돌아가기</button>
				<c:if test="${empty resultList }"> <button class="btn02 fr" onclick="javascript:fn_insert();" type="button">등록</button> </c:if>
				<c:if test="${not empty resultList}"> <button class="btn02 fr" onclick="javascript:fn_insert();" type="button">수정</button> </c:if>
				<c:if test="${not empty resultList}">
					<button class="btn05 fr" onclick="javascript:fn_delete();" type="button">
						<c:if test="${resultList.del_chk eq 'Y'}"> 삭제 </c:if>
						<c:if test="${resultList.del_chk eq 'N'}"> 비활성화 </c:if>
					</button>
				</c:if>
				<c:if test="${not empty resultList and resultList.del_chk eq 'Y'}"> <button class="btn05 fr" onclick="javascript:fn_restore();" type="button">복구</button> </c:if>
			</div>
		</div>

</div>

</body>