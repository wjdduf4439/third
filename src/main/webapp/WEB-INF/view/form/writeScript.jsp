<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script>

$(document).ready(function() {
	
	var template = "";
	
	<c:forEach var="site" items="${ siteList }" varStatus="status1">
	
		template += '${ site.title }';
		template += ",";

	</c:forEach>
	
	$("#siteplace").append(template.substr(0,template.length-1));
	
});

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