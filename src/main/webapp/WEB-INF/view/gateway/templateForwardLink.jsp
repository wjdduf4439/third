<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<% 

	//controller에서 적용한 fieldWidth을 가져와서 전송할때 같이 보낼 수 있어야 함


%>
<c:if test="${empty templateInfoVO.templateType}"> 
	<c:import url="/template/templateZeroWrite.go" />
</c:if>
<c:if test="${templateInfoVO.templateType == '0'}"> 
	<c:import url="/template/templateZeroWrite.go" />
</c:if>
<c:if test="${templateInfoVO.templateType == '1'}"> 
	<c:import url="/template/templateOneList.go" />
</c:if>
