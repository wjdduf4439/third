<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:import url="/AdminHeader.go" />

<!--
ckeditor 기능검색 위치 : 
https://ckeditor.com/docs/index.html 
-->
<style>
	/* .ck.ck-editor { max-width: 500px; } */
	.ck.ck-editor__editable_inline {

	    height: 750px;
	    
	}

</style>

<!-- ckeditor과 그 이미지 첨부 기능을 선언 -->
<script src="${pageContext.request.contextPath}/resources/js/ckeditor5-build-classic-39.0.1/build/ckeditor.js"></script>
<%-- <script src="${pageContext.request.contextPath}/resources/js/ckeditor5-build-classic-39.0.1/build/cdn.ckeditor.com_ckeditor5_39.0.2_classic_ckeditor.js"></script> --%>
<script src="${pageContext.request.contextPath}/resources/js/ckeditor5-build-classic-39.0.1/ckeditorUploadAdapter.js"></script>
<script>

/* var flag = 0;

var oEditors = []; // 개발되어 있는 소스에 맞추느라, 전역변수로 사용하였지만, 지역변수로 사용해도 전혀 무관 함.
$(document).ready(function() {
	// Editor Setting 
	nhn.husky.EZCreator.createInIFrame({ 
		oAppRef : oEditors, // 전역변수 명과 동일해야 함. 
		elPlaceHolder : "contexteditor", // 에디터가 그려질 textarea ID 값과 동일 해야 함. 
		sSkinURI : "${pageContext.request.contextPath}/resources/js/smarteditor2-master/workspace/SmartEditor2Skin.html", // Editor HTML 
		fCreator : "createSEditor2", // SE2BasicCreator.js 메소드명이니 변경 금지 X 
		htParams : { 
			// 툴바 사용 여부 (true:사용/ false:사용하지 않음) 
			bUseToolbar : true, 
			// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음) 
			bUseVerticalResizer : true, 
			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음) 
			bUseModeChanger : true, 
		} 
	});
});
 */
 //에디터 선언을 위한 let변수
 
 //ckeditor에 플러그인으로 선언할 함수 생성
 function editorUploadAdapter(editor) {
	//에디터의 초기선언이 끝나면 이미지 첨부 어댑터(직접만들어야함)을 플러그인으로 선언
	//sitecode까지 함께 보내서 해당 어댑터를 초기선언
	editor.plugins.get("FileRepository").createUploadAdapter = (loader) => {
	    return new ckeditorUploadAdapter(loader, '${searchVO.siteCode }' );
	  };
 }

	 
 let editor;
/*  var pastContent = '';
	<c:if test="${not empty resultList}">
		pastContent = '${resultList.context }';
	</c:if> */
	
var nowContent = '';
 
 $(document).ready(function() {
	 
	 
	 ClassicEditor.create( document.querySelector('#contexteditor'),{
		 		//이미지 업로드 기능 
		 		extraPlugins: [editorUploadAdapter],
		        ckfinder: {
					uploadUrl : '/ckeditorCon/upload.go'
				}
		 
			}).then( newEditor => {
			 	//에디터의 변수 지정
			 	window.editor = newEditor;
		        editor = newEditor;
		        /* https://stackoverflow.com/questions/60762205/ckeditor-5-change-height-event 여기 참고*/
		        editor.model.document.on( 'change:data', () => {     
	                
	                
		        	/*
		        		load_editorImage = 
		        		editorImage
		        	*/
	                
		        	//마지막에는 변경한 내용부분을 계속 비교할 수 있도록 past를 갱신
		        	/* pastContent = nowContent; */
	                
	            } );    
	        
	    	})
			 .catch( error => {
			    console.error( error );
			});
	
		
	
 });
 
/* $('.ck.ck-content.ck-editor__editable.ck-rounded-corners.ck-editor__editable_inline.ck-blurred').on('change', function(){ alert("내용 변경"); }); */
 
 
/*  ClassicEditor.model.change( writer => {
	    // Move selection to the end of the document.
	    writer.setSelection(
	        writer.createPositionAt( editor.model.document.getRoot(), 'end' )
	    );

 } ); */

function fn_validate(){
	
	if($("#title").val() == "")
		{ alert("제목 항목은 필수 항목입니다."); return false; }
	if($("#frstRegistNm").val() == "")
		{ alert("작성자 이름 항목은 필수 항목입니다."); return false; }
	if($("#writerID").val() == "")
		{ alert("작성자 id 항목은 필수 항목입니다."); return false; }
	
	//var context = oEditors.getById["contexteditor"].getIR();
	//$("#context").val(context);
	//oEditors.getById["contexteditor"].exec("UPDATE_CONTENTS_FIELD", []);
	
	//만약 editor의 내용을 변경할 일이 있으면 setData 메소드 사용
	$("#context").val(editor.getData());
	
	return true;
}

function fn_insert(){
	
	if(fn_validate() == false) { return;}
	
	if (!confirm("등록하시겠습니까?")) { return; }
	
	fn_pageReset();
	
	if($("input:checkbox[name=notice_chk_val]").is(":checked") == true){ $("#notice_chk").val("Y"); }else{ $("#notice_chk").val("N"); }
	
	<c:if test="${empty resultList}">
		document.frm.action = '<c:url value="/template/templateZeroInsert.go"/>';
	</c:if>
	<c:if test="${not empty resultList}">
		document.frm.action = '<c:url value="/template/templateZeroUpdate.go"/>';
	</c:if>
	
	
	var contentText = $("#context").val();
	var targetText = '<img src="/ckeditor_upload/';
	var acc = 1;
	
	var ecfileText = '';
	var ecfileTotalText = '';
	
	//1. 글작성 작업
		//처음 글작성 시에는 글 내용의 img 태그만 가져와서 targetText를 가려내어서 적용함
		//잘라낸 텍스트 상관없이 contentText는 그대로 놔두고 다음 img 태그를 찾도록 유도
		//img 태그의 위치를 찾고 ecfile 코드를 분리하면 나머지를 통하여 다시 img태그를 찾아야함
	
	//2. 글수정 작업
		//글 내용의 targetText를 가려내어서 ecfileTotalText를 찾아내는것은 기존의 insert작업과 동일함
		//$('#editorImage')에 바로 등록하지 않고 기존에 등록한 $('#editorImage').의 값과 비교하여 save_editorImage, drop_editorImage를 가려내야함
		//save_editorImage : now_editorImageArr에서 past_editorImageArr에 해당되는 값을 모두 제외시켜야함
		//drop_editorImage : past_editorImageArr에서 now_editorImageArr에 해당되는 값을 모두 제외시켜야함
	
	while( acc = 1 ){
		
		var imgsrcText =  contentText.indexOf(targetText);
		if(imgsrcText === -1) { break; } //console.log("추출한 imgsrcText : " + imgsrcText);
		
		ecfileText = contentText.substr(imgsrcText + targetText.length, 20); //console.log("추출한 ecfileText : " + ecfileText);
		
		
		console.log("ECFILE_ 검출결과 " + ecfileText.includes( 'ECFILE_' ));
		if( true == ecfileText.includes( 'ECFILE_' ) ){ ecfileTotalText = ecfileTotalText.concat(ecfileText,','); }
		
		contentText = contentText.substr(imgsrcText + targetText.length); //추출 끝나면 문자열을 잘라내고 다시 targetText를 탐색
		
	}
	
	ecfileTotalText = ecfileTotalText.substr(0, ecfileTotalText.length - 1);//while문이 끝나면 ecfileTotal의 맨 끝 ,를 제거하기
		

	<c:if test="${empty resultList}">
		$('#editorImage').val(ecfileTotalText);
	</c:if>
	<c:if test="${not empty resultList}">

		let past_editorImageArr = $('#editorImage').val().split(',');
		let now_editorImageArr = ecfileTotalText.split(',');
		
		let save_ecfileTotalText = '';
		let save_ecfileTotalToken = 1;
		
		let drop_ecfileTotalText = '';
		let drop_ecfileTotalToken = 1;

		//save_editorImage 작업
		for(var i = 0; i < now_editorImageArr.length; i++ ){
			
			for(var j = 0; j < past_editorImageArr.length; j++ ){
				
				console.log('now_editorImageArr -:- past_editorImageArr : ' + now_editorImageArr[i] + ' -:- ' + past_editorImageArr[j]);
				//이중for문에서 now_editorImageArr와 past_editorImageArr일치하는 사유가 나오면 save_ecfileTotalToken을 0으로 두게 하고,
				//이중for문에서 now_editorImageArr와 past_editorImageArr일치하지 않는 사유가 나오면 save_ecfileTotalToken을 1으로 두게 하기,
				if(now_editorImageArr[i].includes(past_editorImageArr[j]) == true){ save_ecfileTotalToken = 0; break; }
			}
			
			if(save_ecfileTotalToken == 1) { save_ecfileTotalText = save_ecfileTotalText.concat(now_editorImageArr[i],',');}
			else if (save_ecfileTotalToken == 0){ save_ecfileTotalToken = 1; }
			
		}
		save_ecfileTotalText = save_ecfileTotalText.substr(0, save_ecfileTotalText.length - 1);//for문이 끝나면 save_ecfileTotalText의 맨 끝 ,를 제거하기
		$('#save_editorImage').val(save_ecfileTotalText); console.log('최종 save_ecfileTotalText : ' + save_ecfileTotalText);
		
		//drop_editorImage 작업
		for(var i = 0; i < past_editorImageArr.length; i++ ){
			
			for(var j = 0; j < now_editorImageArr.length; j++ ){
				
				console.log('past_editorImageArr -:- now_editorImageArr : ' + past_editorImageArr[i] + ' -:- ' + now_editorImageArr[j]);
				//이중for문에서 now_editorImageArr와 past_editorImageArr일치하는 사유가 나오면 save_ecfileTotalToken을 0으로 두게 하고,
				//이중for문에서 now_editorImageArr와 past_editorImageArr일치하지 않는 사유가 나오면 save_ecfileTotalToken을 1으로 두게 하기,
				if(past_editorImageArr[i].includes(now_editorImageArr[j]) == true){ drop_ecfileTotalToken = 0; break; }
			}
			
			if(drop_ecfileTotalToken == 1) { drop_ecfileTotalText = drop_ecfileTotalText.concat(past_editorImageArr[i],',');}
			else if (drop_ecfileTotalToken == 0){ drop_ecfileTotalToken = 1; }
			
		}
		drop_ecfileTotalText = drop_ecfileTotalText.substr(0, drop_ecfileTotalText.length - 1);//for문이 끝나면 save_ecfileTotalText의 맨 끝 ,를 제거하기
		$('#drop_editorImage').val(drop_ecfileTotalText); console.log('최종 drop_ecfileTotalText : ' + drop_ecfileTotalText);
		
		
	
	</c:if> 
	
	//console.log('최종 ecfileTotal : ' + $('#editorImage').val());
	//console.log('imgsrcText : ' + imgsrcText);
	
	
	document.frm.submit();
	
}

function fn_delete(){
	
	if($("#del_chk").val() == "N"){
		if (!confirm("비활성화하시겠습니까? 사용자 뷰 화면에서 보이지 않게 됩니다.")) { return; }	
	}else if($("#del_chk").val() == "Y"){
		if (!confirm("삭제하시겠습니까? 삭제한 데이터는 복구가 불가능합니다.")) { return; }
	}
	
	fn_pageReset();
	
	document.frm.action = '<c:url value="/template/templateZeroDelete.go"/>';
	document.frm.submit();
	
}

function fn_restore(){
	
	if (!confirm("복구하시겠습니까?")) { return; }
	
	fn_pageReset();
	
	document.frm.action = '<c:url value="/template/templateZeroRestore.go"/>';
	document.frm.submit();
	
}

function fn_back(){ history.back(); }

function fn_pageReset(){ $("#pageIndex").val(${searchVO.pageIndex/searchVO.recordCountPerPage});} 

</script>

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
				
				<c:import url="/file/fileAdmin.go" charEncoding="utf-8">        
					<c:param name="atchFileId" value="${resultList.atchFileId}" />
				</c:import>
			
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