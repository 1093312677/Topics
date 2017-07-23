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
	                            指导学生
	    </div>
    </div> 
    <table class="table table-hover table-striped" >
    	<tr class="info">
	    		<td width="100px">学号</td>
	    		<td>姓名</td>
	    		<td>班级</td>
	    		<td>方向</td>
	    		<td>专业</td>
	    		
	    		<td>年级</td>
	    		<td>题目名称</td>
	    		<td>子题目</td>
	    		<td>分数</td>
	    		<td>等级</td>
	    	</tr>
	    	<c:forEach items="${students }" var="items">
	    		<tr>
	    			<td><c:out value="${items.no }"></c:out></td>
	    			<td>
	    				<a href="<%=request.getContextPath() %>/student/viewStudentOne.do?filter=yes&no=<c:out value="${items.no }"></c:out>&id=<c:out value="${items.id }"></c:out>"><c:out value="${items.name }"></c:out></a>
	    			</td>
	    			<td><c:out value="${items.clazz.className }"></c:out></td>
	    			<td><c:out value="${items.clazz.direction.directionName }"></c:out></td>
	    			<td><c:out value="${items.clazz.direction.spceialty.specialtyName }"></c:out></td>
	    			<td><c:out value="${items.clazz.direction.spceialty.grade.gradeName }"></c:out></td>
	    			<td>${items.topics.topicsName }</td>
	    			<td>${items.subTopic.subName }</td>
	    			<td>${items.score.mediumScore+items.score.headScore+items.score.replyResult }</td>
	    			<td>${items.score.level }</td>
	    		</tr>
			</c:forEach>
    </table>
<script>
	$(function () { $("[data-toggle='tooltip']").tooltip(); });
</script>	
	
</body>
</html>