<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


	<nav id="topMenu" class="width50p">
			<ul>
				 	<c:forEach var="form" items="${formList }" varStatus="status1">

						<li onmouseover="javascript:fn_show();" ><a class="menuLink" >${form.formName } </a></li>
							
					</c:forEach>
					
					<c:forEach var="form" items="${formList }" varStatus="status1">
						<ul onmouseout="javascript:fn_noshow();" class="sub-menu"style="margin-left: ${status1.index * 100}px;" >
								<c:forEach var="sites" items="${sites }" varStatus="status1" begin="${form.startIndex }" end = "${form.endIndex }" >
									
										<ol onmouseover="javascript:fn_show();" >
										
											<c:if test="${form.formCode == sites.formCode }">	
												<a onmouseover="javascript:fn_show();" href="javascript:fn_ViewSiteLink('${sites.siteCode }', '<c:url value="/templateView/templateZeroViewList.go"/>');" > ${sites.title }</a>		
											</c:if>
											
										</ol>
									
								</c:forEach>
					
								
						</ul>
					</c:forEach>	
					<li><a class="menuLink" href=<c:url value="/loginHome.go"/>>초기 화면</a></li>
					<%
					
					System.out.println("session id : " + session.getValue("id"));
					
					if( null == session.getValue("id") ){
						%>  <li><a class="menuLink" href="javascript:fn_LoginForm();">로그인</a></li> <%	 
					} else { 
						%>  <li><a class="menuLink" href="javascript:fn_loginOut('<c:url value="/accLogout.go"/>');">로그아웃</a></li> <%	 
					}
					
					%>
					
					
					
					<form id="userSitefrm" name="userSitefrm" method="post">

						<input type="hidden" id="siteCode" name="siteCode" value="" />
					
					</form>
				 	
			</ul>
	</nav>