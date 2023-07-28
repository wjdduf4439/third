<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/bootstrap_dist/css/bootstrap.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/bootstrap_component/css/dashboard.css"/>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap_dist/js/bootstrap.js"></script>
</head>
<body>
    <nav class="navbar navbar-inverse navbar-fixed-top">
	    <div class="container-fluid">
	        <div class="navbar-header">
	            <a class="navbar-brand" href="#">Project name</a>
	        </div>
	        <div id="navbar" class="navbar-collapse collapse">
	            <ul class="nav navbar-nav navbar-right">
	                <li><a href="#">Dashboard</a></li>
	            </ul>
	            <form class="navbar-form navbar-right">
	                <input type="text" class="form-control" placeholder="Search...">
	            </form>
	        </div>
	    </div>
	</nav>
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-3 col-md-2 sidebar">
			    <ul class="nav nav-sidebar">
			        <li class="active"><a href="javascript:void(0);">Active</a></li>
			        <li><a href="#">Sample1</a></li>
			        <li><a href="#">Sample2</a></li>
			        <li><a href="#">Sample3</a></li>
			    </ul>
			</div>
            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

			    <h2 class="sub-header">Section title</h2>
			    <div class="table-responsive">
			        <table class="table table-striped">
			            <thead>
			            <tr>
			                <th>#</th>
			                <th>Header</th>
			                <th>Header</th>
			                <th>Header</th>
			                <th>Header</th>
			            </tr>
			            </thead>
			            <tbody>
			            <tr>
			                <td>1,001</td>
			                <td>Lorem</td>
			                <td>ipsum</td>
			                <td>dolor</td>
			                <td>sit</td>
			            </tr>
			            <tr>
			                <td>1,014</td>
			                <td>per</td>
			                <td>inceptos</td>
			                <td>himenaeos</td>
			                <td>Curabitur</td>
			            </tr>
			            <tr>
			                <td>1,015</td>
			                <td>sodales</td>
			                <td>ligula</td>
			                <td>in</td>
			                <td>libero</td>
			            </tr>
			            </tbody>
			        </table>
			    </div>
			</div>
        </div>
    </div>
</body>
</html>