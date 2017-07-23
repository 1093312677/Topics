<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>main page</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.min.css">

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/scrollerbar.css"/>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/common.css"/>


<script src="<%=request.getContextPath() %>/js/jquery-3.1.1.min.js"></script>
<script src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script>
</head>
<body>
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title" align="center">
			时间安排
		</h3>
	</div>
	<div class="panel-body" align="center">
		<c:if test="${privilege == 4 }">
			第一轮选课开始时间：<c:out value="${setting.oneSelectStartTime }"></c:out>
			<br>
			第一轮选课结束时间：<c:out value="${setting.oneSelectEndTimeOne }"></c:out>
			<br>
			<br>
			第二轮选课开始时间：<c:out value="${setting.twoSelectStartTime }"></c:out>
			<br>
			第二轮选课结束时间：<c:out value="${setting.twoSelectEndTimeOne }"></c:out>
			<br>
		</c:if>
	</div>
</div>

</body>
</html>