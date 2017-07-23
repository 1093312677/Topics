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
	                            查看<span class="	glyphicon glyphicon-cog">学生</span>
    			
	    </div>
    </div>
   
   	<table class="table table-hover table-striped" >
    	<tr class="">
    		<td >编号</td>
    		<td>学号</td>
    		<td>学生姓名</td>
    		<td>操作</td>
    	</tr>
    	<c:forEach items="${stuTeachGroups }" var="items">
   			<tr>
    			<td><c:out value="${items.id }"></c:out></td>
    			<td><c:out value="${items.student.no }"></c:out></td>
    			<td><c:out value="${items.student.name }"></c:out></td>
    			<td><a href="javascript:void(0)" onclick="remove(${items.id})">移除该学生</a></td>
    		</tr>
		</c:forEach>
    </table>
    
<script>
	$(function () { $("[data-toggle='tooltip']").tooltip(); });
	function remove(id){
		$.ajax({
			type:"post",
			url:"<%=request.getContextPath()%>/group/changeStudentGroup.do",
			dataType:"json",
			data:{"id":id},
			success:function(data){
				if(data == 1){
					alert("移除成功！");
					window.setTimeout(reload,500);
				} else {
					alert("移除失败！")
				}
			},
			error:function(msg){
				
			}
		})
	}
	
	function reload(){
		location.reload()
	}
</script>	
	
</body>
</html>