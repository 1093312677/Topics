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
	                            设置<span class="	glyphicon glyphicon-cog">分组</span>
    			
	    </div>
    </div>
    <table class="table table-hover table-striped" >
    	<tr class="">
    		<td >编号</td>
    		<td>分组名称</td>
    		<td>设置为答辩组</td>
    	</tr>
    	<c:forEach items="${groups }" var="items">
    		<c:if test="${ items.answerGroup != 1}">
    			<tr>
	    			<td><c:out value="${items.id }"></c:out></td>
	    			<td><c:out value="${items.groupName }"></c:out></td>
	    			<td><a href="<%=request.getContextPath()%>/group/setGroup.do?id=${items.id }">设置为答辩组</a></td>
	    		</tr>
    		</c:if>
		</c:forEach>
    </table>
<script>
	$(function () { $("[data-toggle='tooltip']").tooltip(); });
</script>	
	
</body>
</html>