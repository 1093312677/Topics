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
	                            查看未选题学生 
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
    		<td>系</td>
    		<td>学院</td>
    	</tr>
    	<c:forEach items="${students }" var="items">
    		<c:if test="${items.topics == null }">
    			<tr>
	    			<td><c:out value="${items.no }"></c:out></td>
	    			<td><c:out value="${items.name }"></c:out></td>
	    			<td><c:out value="${items.clazz.className }"></c:out></td>
	    			<td><c:out value="${items.clazz.direction.directionName }"></c:out></td>
	    			<td><c:out value="${items.clazz.direction.spceialty.specialtyName }"></c:out></td>
	    			<td><c:out value="${items.clazz.direction.spceialty.grade.gradeName }"></c:out></td>
	    			<td><c:out value="${items.clazz.direction.spceialty.grade.department.departmentName }"></c:out></td>
	    			<td><c:out value="${items.clazz.direction.spceialty.grade.department.college.collegeName }"></c:out></td>
	    		</tr>
    		</c:if>
		</c:forEach>
    </table>
 
<script>
	$(function () { $("[data-toggle='tooltip']").tooltip(); });
	
</script>	
	
</body>
</html>