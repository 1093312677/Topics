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
	                            分配<span class="	glyphicon glyphicon-cog">学生</span>
    			
	    </div>
    </div>
   
   	<table class="table table-hover table-striped" >
    	<tr class="">
    		<td >选择</td>
    		<td >编号</td>
    		<td>学号</td>
    		<td>学生姓名</td>
    	</tr>
    	<form action="<%=request.getContextPath() %>/group/setStuTeachGroup.do" method="post" onsubmit="return check()">
	    	<c:forEach items="${students }" var="items">
	   			<tr>
	   				<td><input type="checkbox" name="studentId" class="student" value='<c:out value="${items.id }"></c:out>'/></td>
	    			<td><c:out value="${items.id }"></c:out></td>
	    			<td><c:out value="${items.no }"></c:out></td>
	    			<td><c:out value="${items.name }"></c:out></td>
	    		</tr>
			</c:forEach>
			<input type="submit" class="btn btn-primary" value="分配" style="margin:10px;width=150px"/>
   		</form>
    </table>
    
<script>
	$(function () { $("[data-toggle='tooltip']").tooltip(); });
	var items = $(".student");
	function check(){
		var count = 0;
		for(var i=0;i<items.length;i++){
			if(items[i].checked){
				count++;
			}
		}
		if(count == 0){
			alert("请选择学生！")
			return false;
		}
		return true;
		
	}
</script>	
	
</body>
</html>