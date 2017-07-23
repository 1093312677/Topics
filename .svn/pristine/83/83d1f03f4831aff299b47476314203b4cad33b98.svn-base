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
	                          <span class="glyphicon glyphicon-pencil">查看成绩</span> 
	    </div>
    </div> 
    <table class="table table-hover table-striped" >
    	<tr class="info">
    		<td >学号</td>
    		<td >姓名</td>
    		<td >性别</td>
    		<td>题目名称</td>
    		<td>毕业论文成绩</td>
    		
    	</tr>
    	<c:forEach items="${topics }" var="items">
	    	<c:forEach items="${items.students }" var="it">
	    		<tr>
	    			<td><c:out value="${it.no }"></c:out></td>
	    			<td>
	    				<a href="<%=request.getContextPath() %>/student/viewStudentOne.do?id=<c:out value="${it.id }"></c:out>"><c:out value="${it.name }"></c:out></a>
	    			</td>
	    			<td><c:out value="${it.sex }"></c:out></td>
	    			<td><c:out value="${it.topics.topicsName }"></c:out></td>
	    			<td><c:out value="${it.score.score }"></c:out></td>
	    		</tr>
	    	</c:forEach>
		</c:forEach>
    </table>
    
    
    
<script>
	$(function () { $("[data-toggle='tooltip']").tooltip(); });
</script>	
	
</body>
</html>