<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	
	<script>
	
		function fn_access(){
			
			document.loginHomeVO.action = "accLoginHome.go";
			document.loginHomeVO.submit();
			
		}
	
	
	</script>

	<div class="loginFormBox">
		<form class=""  id="loginHomeVO" name="loginHomeVO" method="post" enctype="multipart/form-data"  runat="server" action="" >
			<div class="contents_login">
				<div class="space10"></div>
					포트폴리오 : 이정열
					로그인
					</br>
				<div>
					<table id="btable" border="1" cellpadding="1" cellspacing="1" class="admin_table tableline_login">
						<tbody>
							<tr>
							
								<th>id<th>
								<td> <input type="text" id="id" name="id" value="" class="width90p" > </td>
							
							</tr>
							<tr>
							
								<th>pw<th>
								<td> <input type="password" id="pw" name="pw" value="" class="width90p" > </td>
							
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