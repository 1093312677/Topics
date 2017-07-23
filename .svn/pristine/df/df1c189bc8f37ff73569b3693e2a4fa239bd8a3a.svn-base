<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>view class</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.min.css">

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/scrollerbar.css"/>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/common.css"/>


<script src="<%=request.getContextPath() %>/js/jquery-3.1.1.min.js"></script>
<script src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script>
</head>
<body>
	<c:if test="${message =='success'}">
		<div class="alert alert-success alert-dismissable">
			<button type="button" class="close" data-dismiss="alert"
					aria-hidden="true">
				&times;
			</button>
			成功！
		</div>
	</c:if>
	<c:if test="${message =='failed'}">
		<div class="alert alert-danger alert-dismissable">
			<button type="button" class="close" data-dismiss="alert" aria-hidden="true">
				&times;
			</button>
			失败！
		</div>	
	</c:if>
	<div class="panel panel-default" style="margin:0">
	    <div class="panel-body">
	                            老师详细信息 
	    </div>
    </div>
    <div class="container">
    	<div class="row">
    		<div class="col-md-3">
    		
    		</div>
    		<div class="col-md-6">
    		<div class="panel panel-default" style="margin-top:25px;">
			    <div class="panel-heading">
			        <h3 class="panel-title"><c:out value="${teacher.name }"></c:out></h3>
			    </div>
			    <table class="table">
			        <tr><td>性别</td><td><c:out value="${teacher.sex }"></c:out></td></tr>
			        <tr><td>职称</td><td><c:out value="${teacher.position }"></c:out></td></tr>
			    	
			    	<c:if test="${privilege == 3 ||privilege == 2 }">
			    		<tr><td>电话</td><td><c:out value="${teacher.telephone }"></c:out></td></tr>
			    	</c:if>
			    	
				    <tr><td>QQ</td><td><c:out value="${teacher.qq }"></c:out></td></tr>
				    <tr><td>学院</td><td><c:out value="${teacher.department.college.collegeName }"></c:out></td></tr>
				    <tr><td>系</td><td><c:out value="${teacher.department.departmentName  }"></c:out></td></tr>
				    <tr><td>备注</td><td><c:out value="${teacher.remark }"></c:out></td></tr>
			    </table>
			</div>
    	</div>
    </div>		
    
<script>
	$(function () { $("[data-toggle='tooltip']").tooltip(); });
</script>	
	
</body>
</html>