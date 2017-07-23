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
	<c:if test="${privilege == 4 ||privilege == 3}">
		<div class="panel-heading">
			<h3 class="panel-title" align="center">
				模板文档
			</h3>
		</div>
		<div class="panel-body" align="center">
			<c:if test="${privilege == 4 }">
				<a href="<%=request.getContextPath() %>/document/viewDocument.do?type=student">下载模板文档</a>
			</c:if>
			<c:if test="${privilege == 3 }">
				<a href="<%=request.getContextPath() %>/document/viewDocument.do?type=teacher">下载模板文档</a>
			</c:if>
		</div>
	</c:if>	
</div>

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
			<br>
			开题报告提交开始时间：<c:out value="${setting.openReportStartTime }"></c:out>
			<br>
			开题报告提交结束时间：<c:out value="${setting.openReportEndTime }"></c:out>
			<br>
			<br>
			中期报告提交开始时间：<c:out value="${setting.midReportStartTime }"></c:out>
			<br>
			中期报告提交结束时间：<c:out value="${setting.midReportEndTime }"></c:out>
			<br>
			<br>
			毕业论文提交开始时间：<c:out value="${setting.commitAttachStartTime }"></c:out>
			<br>
			毕业论文提交结束时间：<c:out value="${setting.commitAttachEndTime }"></c:out>
			<br>
			
		</c:if>
		<c:if test="${privilege == 3 }">
			<a href="<%=request.getContextPath() %>/teacher/viewGradeSelectedIntent.do?viewType=viewTime">查看相关时间节点</a>
		</c:if>
		<c:if test="${privilege == 2 }">
			欢迎！
		</c:if>
	</div>
</div>

</body>
</html>