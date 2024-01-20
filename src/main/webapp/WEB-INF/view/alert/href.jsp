<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	${ lib.getMetaRobots() }
    <title>
    <c:choose>
		<c:when test="${ not empty title }">
			<c:out value="${ title }" />
		</c:when>
		<c:when test="${ not empty message }">
			<c:out value="${ message }" />
		</c:when>
		<c:otherwise>
			잘못된 접근입니다.
		</c:otherwise>
	</c:choose>
    </title>
    <script>
    <c:choose>
		<c:when test="${ empty System_errMessage }">
			alert("잘못된 접근입니다.");
		</c:when>
		<c:otherwise>
    		alert("${ System_errMessage }");
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${ empty location }">
			history.back();
		</c:when>
		<c:otherwise>
    		location.href = "${ location }";
		</c:otherwise>
	</c:choose>
    </script>
</head>
<body>
</body>
</html>