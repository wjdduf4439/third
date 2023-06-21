<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>login-Home</title>

<script type="text/javascript">

	
	function fn_access(){
		
		document.listform.action = "accLoginHome.go";
		document.listform.submit();
		
	}


</script>

</head>
<body>

	<form  id="listform" name="listform">
		this is login home
		</br>
		</br>
		</br>
		<a onclick="javascript:fn_access()">access login</a>
	</form>
	
</body>
</html>