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
	<div class="panel panel-default" style="margin:0">
	    <div class="panel-body">
	                            查看导入失败教师信息
	                            <a href="<%=request.getContextPath() %>/import/exportErrorTeacher.do">导出错误信息</a>
	    </div>
    </div>
    <table class="table table-hover table-striped" >
    	<tr class="info">
    		<td width="100px">工号</td>
    		<td>姓名</td>
    		<td width="50px">性别</td>
    		<td>职称</td>
    		<td>系</td>
    		<td>QQ</td>
    		<td>电话</td>
    		<td>邮箱</td>
    		<td>错误信息</td>
    	</tr>
    	<c:forEach items="${teachers }" var="items">
    		<tr>
    			<td><c:out value="${items.no }"></c:out></td>
    			<td><c:out value="${items.name }"></c:out></td>
    			<td><c:out value="${items.sex }"></c:out></td>
    			<td><c:out value="${items.position }"></c:out></td>
    			<td><c:out value="${items.department.departmentName }"></c:out></td>
    			
    			<td><c:out value="${items.qq }"></c:out></td>
    			<td><c:out value="${items.telephone }"></c:out></td>
    			<td><c:out value="${items.email }"></c:out></td>
    			
    			<td><c:out value="${items.remark }"></c:out></td>
    		</tr>
		</c:forEach>
    </table>
</body>
</html>