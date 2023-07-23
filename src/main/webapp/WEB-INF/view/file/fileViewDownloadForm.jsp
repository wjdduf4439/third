<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<tr>
	<th>
		첨부파일
	</th>
	<td colspan="3">
		<ul class="attach">
			<c:forEach var="fileList" items="${fileList}" varStatus="status">
				<li id="${fileList.code}">
					<a href="javascript:fn_downFile('<c:out value="${fileList.code}"/>')"><c:out value="${fileList.fname}"/></a>
				</li>
			</c:forEach>
		</ul>
	</td>
</tr>
	
<script>

function fn_downFile(code){
	//window.open("<c:url value='/cmm/fms/FileDown.do?atchFileId="+atchFileId+"&fileSn="+fileSn+"'/>");
	window.open("<c:url value='/filedown.go?code="+code+"'/>");
}

</script>