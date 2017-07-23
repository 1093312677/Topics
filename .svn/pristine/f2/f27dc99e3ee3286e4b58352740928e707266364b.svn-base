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
<!-- 选择教师到分组里面 -->
<body>
	<div class="panel panel-default" style="margin:0">
	    <div class="panel-body">
	                            设置<span class="	glyphicon glyphicon-cog">分组</span>
    			
	    </div>
    </div>
    
    <table class="table table-hover table-striped" >
    	<tr class="">
    		<td >选择老师</td>
    		<td >选择组长</td>
    		<td >编号</td>
    		<td>教师名称</td>
    	</tr>
    	<form action="<%=request.getContextPath() %>/group/setTeacherGroup.do" method="post" onsubmit="return check()">
    		<input type="hidden" value="0" id="leaderValue" name="teacherHeaderId" >
	    	<input type="hidden" value="0" id="teacherValue" >		
	    	<c:forEach items="${teachers }" var="items">
	   			<tr>
	   				<td><input type="checkbox" value="${items.id }" class="teacher" onchange="choiceTeacher(${items.id })" name="teacherId"></td>
	   				<td><input type="radio" value="${items.id }"  onchange="choiceLeader(${items.id })" name="teacherHeaderId1"></td>
	   				<td><c:out value="${items.id }"></c:out></td>
	    			<td><c:out value="${items.name }"></c:out></td>
	    		</tr>
			</c:forEach>
			<input type="submit" class="btn btn-primary" value="添加进该组" style="margin:10px;width=150px"/>
		</form>
    </table>
<script>
	$(function () { $("[data-toggle='tooltip']").tooltip(); });
	
	function choiceLeader(id){
		$("#leaderValue").val(id)
	}
	
	
	function choiceTeacher(id){
		$("#teacherValue").val(id)
		
	}
	var items = $(".teacher");
	function check(){
		var count = 0;
		for(var i=0;i<items.length;i++){
			if(items[i].checked){
				count++;
			}
		}
		if(count == 0){
			alert("请选择教师！")
			return false;
		}
		return true;
		
	}
</script>	
	
</body>
</html>