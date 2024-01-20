<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:import url="/AdminHeader.go" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/templateZero/write.js"></script> 
<jsp:include page="templateZeroWriteScript.jsp"/> 

<!--
ckeditor 기능검색 위치 : 
https://ckeditor.com/docs/index.html 
-->
<style>
	/* .ck.ck-editor { max-width: 500px; } */
	.ck.ck-editor__editable_inline { height: 750px; }

</style>

<!-- ckeditor과 그 이미지 첨부 기능을 선언 -->
<script src="${pageContext.request.contextPath}/resources/js/ckeditor5-build-classic-39.0.1/build/ckeditor.js"></script>
<%-- <script src="${pageContext.request.contextPath}/resources/js/ckeditor5-build-classic-39.0.1/build/cdn.ckeditor.com_ckeditor5_39.0.2_classic_ckeditor.js"></script> --%>
<script src="${pageContext.request.contextPath}/resources/js/ckeditor5-build-classic-39.0.1/ckeditorUploadAdapter.js"></script>

<div class="contents_wrap">
		<div class="contents">
		<h3 class="fl">${searchVO.siteTitle }</h3>
		<div class="essential fr">
	      <i class="icono-asterisk"></i>필수입력항목
	    </div>
		<form id="frm" name="frm" method="post" enctype="multipart/form-data">
			<input type="hidden" id="pageIndex" name="pageIndex" value="${searchVO.pageIndex}" />
			<input type="hidden" name="pageUnit" value="${searchVO.pageUnit}" />
			<input type="hidden" name="searchCnd" value="${searchVO.searchCnd}" />
			<input type="hidden" name="searchWrd" value="${searchVO.searchWrd}" />
			<input type="hidden" name="recordCountPerPage" value="${searchVO.recordCountPerPage}" />
			
			<input type="hidden" id="siteCode" name="siteCode" value="${searchVO.siteCode }"/>
			<input type="hidden" id="primaryCode" name="primaryCode" value="${ primaryCode }"/>
			<input type="hidden" id="code" name="code" value="${searchVO.code }"/>
			<input type="hidden" id="siteTitle" name="siteTitle" value="${searchVO.siteTitle }"/>
			<input type="hidden" id="atchFileId" name="atchFileId" value="${resultList.atchFileId }"/>
			<input type="hidden" id="context" name="context" value=""/>
			<input type="hidden" id="notice_chk" name="notice_chk" value="${resultList.notice_chk }"/>
			<input type="hidden" id="del_chk" name="del_chk" value="${resultList.del_chk }"/>
			
			<input type="hidden" id="load_editorImage" name="load_editorImage" value="${editorImageCode.code }"/>
			<input type="hidden" id="editorImage" name="editorImage" value="${resultList.editorImage }"/>
			<input type="hidden" id="save_editorImage" name="save_editorImage" value=""/>
			<input type="hidden" id="drop_editorImage" name="drop_editorImage" value=""/>
			
			<table class="tablewrite tableline">
				<colgroup>
					<col style="width: 20%;" >
					<col style="width: 30%;" >
					<col style="width: 20%;" >
					<col style="width: 30%;" >
				</colgroup>
				
				<tr>
					<th> <i class="icono-asterisk"></i>   제목</th>
					<td><input type="text" name="title" id="title" class="maxwidth200" value="${resultList.title }"/></td>
					<th>공지여부확인</th>
					<td >
						<input type='checkbox' id='notice_chk_val' name='notice_chk_val' value='Y' <c:out value="${resultList.notice_chk eq 'Y' ? 'checked' : ''}"/> >
					</td>
				</tr>
				<tr>
					<th> <i class="icono-asterisk"></i>   작성자 이름</th>
					<td colspan="1">
						<input type="text" class="maxwidth200" name="frstRegistNm" id="frstRegistNm" value="관리자" readonly="readonly"  />
						<!--  <input type="text" name="frstRegistNm" id="frstRegistNm" value="${resultList.frstRegistNm }"value="관리자" readonly="readonly"  /> --> 
					</td>
					<th>조회수</th>
					<td colspan="1">${resultList.viewNum }</td>
				</tr>
				</tr>
				<tr>
					<th> <i class="icono-asterisk"></i>   작성자 id</th>
					<td colspan="3"><input type="text" name="writerID" id="writerID" class="maxwidth200" value="${sessionScope.id}" readonly="readonly"  /></td>
				</tr>
				<tr>
					<th>내용</th>
					<!-- <td colspan="3"><input type="text" name="contexteditor" id="contexteditor" class="width800" value="" /></td> -->
					<td colspan="3"><textarea  name="contexteditor" id="contexteditor" class="maxwidth800"> ${resultList.context } </textarea></td>
					
				</tr>
				<%-- 
				<c:import url="/file/fileAdmin.go" charEncoding="utf-8">        
					<c:param name="atchFileId" value="${resultList.atchFileId}" />
				</c:import>
				 --%>
			
			</table>

		</form>
		
		<div class="btngroup mt0">
			<button class="btn03 fr" onclick="javascript:fn_back('<c:url value='/template/templateZeroList.go'/>');" type="button">돌아가기</button>
			<c:if test="${empty resultList }"> <button class="btn02 fr" onclick="javascript:fn_insert();" type="button">등록</button> </c:if>
			<c:if test="${not empty resultList}"> <button class="btn02 fr" onclick="javascript:fn_insert();" type="button">수정</button> </c:if>
			<c:if test="${not empty resultList}">
				<button class="btn05 fr" onclick="javascript:fn_delete('<c:url value='/template/templateZeroDelete.go'/>');" type="button">
					<c:if test="${resultList.del_chk eq 'Y'}"> 삭제 </c:if>
					<c:if test="${resultList.del_chk eq 'N'}"> 비활성화 </c:if>
				</button>
			</c:if>
			<c:if test="${not empty resultList and resultList.del_chk eq 'Y'}"> <button class="btn05 fr" onclick="javascript:fn_restore('<c:url value='/template/templateZeroRestore.go'/>');" type="button">복구</button> </c:if>
		</div>
		</div>

</div>

</body>