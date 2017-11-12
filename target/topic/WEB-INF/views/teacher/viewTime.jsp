<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>time page</title>
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
		题目提交开始时间：<c:out value="${setting.commitTopicStartTime }"></c:out>
		<br>
		题目提交结束时间：<c:out value="${setting.commitTopicEndTime }"></c:out>
		<br>
		<br>
		<p>第一轮选题</p>
		
		教师录取志愿起始时间：<c:out value="${setting.oneAdmissionStartTime }"></c:out>
		<br>
		第一志愿录取截止时间：<c:out value="${setting.oneFirstChoiceDeadline }"></c:out>
		<br>
		第二志愿录取截止时间：<c:out value="${setting.oneSecondChoiceDeadline }"></c:out>
		<br>
		第三志愿录取截止时间：<c:out value="${setting.oneThirdChoiceDeadline }"></c:out>
		<br>
		<br>
		
		<p>第二轮选题</p>
		
		教师录取志愿起始时间：<c:out value="${setting.twoAdmissionStartTime }"></c:out>
		<br>
		第一志愿录取截止时间：<c:out value="${setting.twoFirstChoiceDeadline }"></c:out>
		<br>
		第二志愿录取截止时间：<c:out value="${setting.twoSecondChoiceDeadline }"></c:out>
		<br>
		第三志愿录取截止时间：<c:out value="${setting.twoThirdChoiceDeadline }"></c:out>
		<br>
		<br>
		<p>指导教师评阅时间</p>
		
		指导教师评阅开始时间：<c:out value="${setting.instructorReviewStartTime }"></c:out>
		<br>
		指导教师评阅结束时间：<c:out value="${setting.instructorReviewEndTime }"></c:out>
		<br>
		<br>
		<p>小组评阅时间</p>
		
		小组评阅开始时间：<c:out value="${setting.midReviewStartTime }"></c:out>
		<br>
		小组评阅结束时间：<c:out value="${setting.midReviewEndTime }"></c:out>
		<br>
		<br>
		<p>答辩结果提交</p>
		
		答辩结果提交开始时间：<c:out value="${setting.replyResultsStartTime }"></c:out>
		<br>
		答辩结果提交结束时间：<c:out value="${setting.replyResultsEndTime }"></c:out>
		<br>
		<br>
		<p>学生选题相关时间</p>
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
		
	</div>
</div>

</body>
</html>