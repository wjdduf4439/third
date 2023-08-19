<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	
<%@ page import="com.ljy.third.vo.TemplateZeroViewVO" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:import url="/AdminHeader.go" />

<script>

var oEditors = []; // 개발되어 있는 소스에 맞추느라, 전역변수로 사용하였지만, 지역변수로 사용해도 전혀 무관 함.
var flag = 0;
$(document).ready(function() {
	
	<%
		//TemplateZeroViewVO ajaxTemplateZeroViewVO = (TemplateZeroViewVO) request.getAttribute("resultList");
		TemplateZeroViewVO ajaxTemplateZeroViewVO = (TemplateZeroViewVO) request.getAttribute("searchVO");
	
		String ajaxSiteCode = ajaxTemplateZeroViewVO.getSiteCode();
		String ajaxCode = ajaxTemplateZeroViewVO.getCode();
	%>
	
	//조회수 +1 ajax
	var ajaxSiteCode = '<%=ajaxSiteCode%>';
	var ajaxCode = '<%=ajaxCode%>';
	
	var url = "<c:url value='/ViewNumPlus.go?siteCode="+ajaxSiteCode+"&code="+ajaxCode+"'/>";
	
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
        },   
        error:function(e){  
            //alert("조회수 추가 실패" + e.responseText);  
        }  
    }); 
	
});


function fn_validate(){
	
	if($("#title").val() == "")
		{ alert("제목 항목은 필수 항목입니다."); return false; }
	if($("#frstRegistNm").val() == "")
		{ alert("작성자 이름 항목은 필수 항목입니다."); return false; }
	if($("#writerID").val() == "")
		{ alert("작성자 id 항목은 필수 항목입니다."); return false; }
	
	var context = oEditors.getById["contexteditor"].getIR();
	$("#context").val(context);
	oEditors.getById["contexteditor"].exec("UPDATE_CONTENTS_FIELD", []);
	
	return true;
}

function fn_back(){ history.back(); }

function fn_pageReset(){ $("#pageIndex").val(${searchVO.pageIndex/searchVO.recordCountPerPage});} 

</script>

<div class="contents_wrap">
		<div class="contents">
		<h3 class="fl">${searchVO.siteTitle }</h3>
		<form id="frm" name="frm" method="post" enctype="multipart/form-data">
			<input type="hidden" id="pageIndex" name="pageIndex" value="${searchVO.pageIndex}" />
			<input type="hidden" name="pageUnit" value="${searchVO.pageUnit}" />
			<input type="hidden" name="searchCnd" value="${searchVO.searchCnd}" />
			<input type="hidden" name="searchWrd" value="${searchVO.searchWrd}" />
			<input type="hidden" name="recordCountPerPage" value="${searchVO.recordCountPerPage}" />
			
			<input type="hidden" id="siteCode" name="siteCode" value="${searchVO.siteCode }"/>
			<input type="hidden" id="code" name="code" value="${searchVO.code }"/>
			<input type="hidden" id="siteTitle" name="siteTitle" value="${searchVO.siteTitle }"/>
			<input type="hidden" id="atchFileId" name="atchFileId" value="${resultList.atchFileId }"/>
			<input type="hidden" id="context" name="context" value=""/>
			<input type="hidden" id="noticeSwitch" name="noticeSwitch" value="${resultList.noticeSwitch }"/>
			
			<table class="tablewrite tableline">
				<colgroup>
					<col style="width: 20%;" >
					<col style="width: 30%;" >
					<col style="width: 20%;" >
					<col style="width: 30%;" >
				</colgroup>
				
				<tr>
					<th>제목</th>
					<td colspan="1">${resultList.title }</td>
					<th>조회수</th>
					<td colspan="1">${resultList.viewNum + 1 }</td>
				</tr>
				<tr>
					<th>작성자 이름</th>
					<td colspan="3"> ${resultList.frstRegistNm } </td>
				</tr>
				<tr>
					<th>작성자 id</th>
					<td colspan="3">${resultList.writerID }</td>
				</tr>
				<tr>
					<th>내용</th>
					<td colspan="3">${resultList.context }</td>
				</tr>
				
				<c:import url="/file/fileViewAdmin.go" charEncoding="utf-8">        
					<c:param name="atchFileId" value="${resultList.atchFileId}" />
				</c:import>
			
			</table>

		</form>
			<div class="btngroup mt0">
				<button class="btn03 fr" onclick="javascript:fn_back();" type="button">돌아가기</button>
			</div>
		</div>

</div>
</body>