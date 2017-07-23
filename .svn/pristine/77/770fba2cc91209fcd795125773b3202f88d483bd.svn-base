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
	                           调整<span class="	glyphicon glyphicon-cog">分组</span>
    			
	    </div>
    </div>
    <table class="table table-hover table-striped" >
    	<tr class="">
    		<td >编号</td>
    		<td >组长</td>
    		<td>教师姓名</td>
    		<td>移除</td>
    		<td>设置组长</td>
    	</tr>
    	<c:forEach items="${group.teacherGroup }" var="items">
   			<tr>
    			<td><c:out value="${items.id }"></c:out></td>
    			<td>
    				<c:if test="${items.isLeader == 1 }">
    					<span style="color:#06c290">组长</span>
    				</c:if>
    			</td>
    			<td><c:out value="${items.teacher.name }"></c:out></td>
    			<td>
    				<a href="javascript:void(0)" onclick="deleteItem(${items.id })"> 移出该组</a>
    			</td>
    			<td>
    				<c:if test="${items.isLeader == 1 }">
    					<a href="javascript:void(0)" onclick="changeTeacherLeader(${items.id },'remove')">取消组长</a>
    				</c:if>
    				<c:if test="${items.isLeader == 0 }">
    					<a href="javascript:void(0)" onclick="changeTeacherLeader(${items.id },'set')">设置组长</a>
    				</c:if>
    			</td>
    		</tr>
		</c:forEach>
    </table>
<script>
	$(function () { $("[data-toggle='tooltip']").tooltip(); });
	//删除
	function deleteItem(id){
		if(confirm("取认移出？")){
			$.ajax({
				type:"post",
				url:"<%=request.getContextPath()%>/group/removeTeacher.do",
				data:{"teacherGroupId":id
				},
				dataType:"text",
				success:function(data){
					if(data==1){
						alert("移出成功！");
						window.setTimeout(reload,500);
					} else {
						alert("移出失败！")
					}
					 
					
				},
				error:function(msg){
					console.log(msg)
				}
			})
		}
	}
	
	function changeTeacherLeader(id,type){
		if(confirm("取认？")){
			$.ajax({
				type:"post",
				url:"<%=request.getContextPath()%>/group/changeTeacherLeader.do",
				data:{"id":id,
					  "type":type
				},
				dataType:"text",
				success:function(data){
					if(data==1){
						alert("成功！");
						window.setTimeout(reload,500);
					} else {
						alert("失败！")
					}
					 
					
				},
				error:function(msg){
					console.log(msg)
				}
			})
		}
	}
	
	
	function reload(){
		location.reload()
	}
</script>	
	
</body>
</html>