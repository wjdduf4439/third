<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:import url="/AdminHeader.go" />

<script type="text/javascript" src="${pageContext.request.contextPath}/js/site/checkBoxFunction.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/site/write.js"></script>  
<jsp:include page="writeScript.jsp"/>


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
				<input type="hidden" id="del_chk" name="del_chk" value="${resultList.del_chk }"/>
				
				<input type="hidden" id="placeRow" name="placeRow" value="${resultList.placeRow}" />
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
		        			<input type="checkbox" id="fileUploadType_jpg" name="fileUploadType_chkbox" value="jpg" placeholder="허용파일형" onclick="javascript:fn_fileUploadTypeChecked('jpg');" >
		        			<label for="fileUploadType_jpg">jpg</label>
		        			<input type="checkbox" id="fileUploadType_png" name="fileUploadType_chkbox" value="png" placeholder="허용파일형" onclick="javascript:fn_fileUploadTypeChecked('png');" >
		        			<label for="fileUploadType_png">png</label>
							<input type="checkbox" id="fileUploadType_pdf" name="fileUploadType_chkbox" value="pdf" placeholder="허용파일형" onclick="javascript:fn_fileUploadTypeChecked('pdf');" >
							<label for="fileUploadType_pdf">pdf</label>
							<input type="checkbox" id="fileUploadType_zip" name="fileUploadType_chkbox" value="zip" placeholder="허용파일형" onclick="javascript:fn_fileUploadTypeChecked('zip');" >
							<label for="fileUploadType_zip">zip</label>
							<input type="checkbox" id="fileUploadType_webp" name="fileUploadType_chkbox" value="webp" placeholder="허용파일형" onclick="javascript:fn_fileUploadTypeChecked('webp');" >
							<label for="fileUploadType_webp">webp</label>
							<input type="checkbox" id="fileUploadType_java" name="fileUploadType_chkbox" value="java" placeholder="허용파일형" onclick="javascript:fn_fileUploadTypeChecked('java');" >
							<label for="fileUploadType_java">java</label>
							<input type="checkbox" id="fileUploadType_xml" name="fileUploadType_chkbox" value="xml" placeholder="허용파일형" onclick="javascript:fn_fileUploadTypeChecked('xml');" >
							<label for="fileUploadType_xml">xml</label>
							<input type="checkbox" id="fileUploadType_jsp" name="fileUploadType_chkbox" value="jsp" placeholder="허용파일형" onclick="javascript:fn_fileUploadTypeChecked('jsp');" >
							<label for="fileUploadType_jsp">jsp</label>
							<input type="checkbox" id="fileUploadType_class" name="fileUploadType_chkbox" value="class" placeholder="허용파일형" onclick="javascript:fn_fileUploadTypeChecked('class');" >
							<label for="fileUploadType_class">class</label>
		        		</td>
		        	</tr>
				</table>
	
			</form>
			
			<div class="btngroup mt0">
				<button class="btn03 fr" onclick="javascript:fn_back('<c:url value='/site/siteAdmin.go'/>');" type="button">돌아가기</button>
				<c:out value= '' />
				<c:if test="${empty resultList }"> <button class="btn02 fr" onclick="javascript:fn_insert();" type="button">등록</button> </c:if>
				<c:if test="${not empty resultList}"> <button class="btn02 fr" onclick="javascript:fn_insert();" type="button">수정</button> </c:if>
				<c:if test="${not empty resultList}">
					<button class="btn05 fr" onclick="javascript:fn_delete('<c:url value='/site/siteDelete.go'/>');" type="button">
						<c:if test="${resultList.del_chk eq 'Y'}"> 삭제 </c:if>
						<c:if test="${resultList.del_chk eq 'N'}"> 비활성화 </c:if>
					</button>
				</c:if>
				<c:if test="${not empty resultList and resultList.del_chk eq 'Y'}"> <button class="btn05 fr" onclick="javascript:fn_restore('<c:url value='/site/siteRestore.go'/>');" type="button">복구</button> </c:if>
			</div>
		</div>

</div>

</body>
