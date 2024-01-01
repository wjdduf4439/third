<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>



<nav id="topMenu" class="width50p">
		<ul>
			
				
			 	<c:forEach var="form" items="${ formList }" varStatus="status1">

					<li onmouseover="javascript:fn_show();" ><a class="menuLink" >${form.formName }</a></li>
						
				</c:forEach>
				
				<c:forEach var="form" items="${ formList }" varStatus="status1">
					<ul onmouseout="javascript:fn_noshow();" class="sub-menu"style="margin-left: ${status1.index * 100}px;" >
							<c:forEach var="sites" items="${ sites }" varStatus="status1" begin="${form.startIndex }" end = "${form.endIndex }" >
								
									<ol onmouseover="javascript:fn_show();" >
									
										<c:if test="${form.formCode == sites.formCode }">	
											<a onmouseover="javascript:fn_show();" href="javascript:fn_SiteLink('${sites.siteCode }', '<c:url value="/template/templateZeroList.go"/>');" > ${sites.title }</a>		
										</c:if>
										
									</ol>
								
							</c:forEach>
					</ul>
				</c:forEach>	
				
				<li><a class="menuLink" href=<c:url value="/site/siteAdmin.go"/>>게시판 관리</a></li>
			 	<li><a class="menuLink" href=<c:url value="/form/formAdmin.go"/>>항목 관리</a></li>
			 	<li><a class="menuLink" href=<c:url value="/log/logAdmin.go"/>>로그 테이블</a></li>
			 	<li><a class="menuLink" href=<c:url value="/siteMenu/menuAdmin.go"/>>메뉴 관리</a></li>
				
				<form id="sitefrm" name="sitefrm" method="post">

					<input type="hidden" id="siteCode" name="siteCode" value="" />
					<input type="hidden" id="siteCode_json" name="siteCode_json" value="" />
				
				</form>
			 	
		</ul>
</nav>