<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:import url="/AdminHeader.go" />

<script type="text/javascript">

	
	function fn_access(){
		
		document.loginHomeVO.action = "accLoginHome.go";
		document.loginHomeVO.submit();
		
	}


</script>
<body>

	<div class="contents_wrap">
		<form  id="loginHomeVO" name="loginHomeVO" method="post" enctype="multipart/form-data"  runat="server" action="" >
			<div class="contents">
				<div class="space10"></div>
					포트폴리오 : 이정열
					로그인
					</br>
				<div>
					<table id="btable" border="1" cellpadding="1" cellspacing="1" class="admin_table tableline width450">
						<tbody>
							<tr>
							
								<th>id<th>
								<td> <input type="text" id="id" name="id" value="" > </td>
							
							</tr>
							<tr>
							
								<th>pw<th>
								<td> <input type="password" id="pw" name="pw" value="" > </td>
							
							</tr>
						</tbody>
					</table>
				</div>
				
				
				<div class="btngroup mt0">
					<button class="btn02 fl" onclick="javascript:fn_access();" type="button">access login</button>
				</div>
			</div>
		</form>
	</div>
	
</body>