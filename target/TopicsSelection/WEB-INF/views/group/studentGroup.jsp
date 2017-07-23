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
	                            查看分组
	    </div>
    </div>
    <div class="container">
    	<div class="row">
	    		<div class="col-md-3">
	    		
	    		</div>
	    		<div class="col-md-6">
	    		<div class="panel panel-default" style="margin-top:25px;">
				    <div class="panel-heading">
				        <h3 class="panel-title">  查看分组</h3>
				    </div>
				    <table class="table">
				        <tr><td>答辩分组</td><td><c:out value="${group.groupName }"></c:out></td></tr>
			    		<tr>
			    			<td>答辩教师</td>
			    			<td>
			    				<c:forEach items="${group.teacherGroup }" var="items">
			    					<c:out value="${items.teacher.name }"></c:out><br>
			    				</c:forEach>
			    			</td>
			    		</tr>
			    		 <tr><td>答辩时间</td><td><c:out value="${group.timeAndPlace.time }"></c:out></td></tr>
			    		 <tr><td>答辩地点</td><td><c:out value="${group.timeAndPlace.place }"></c:out></td></tr>
			    	
				    </table>
				</div>
	    	</div>
	    </div>		
    </div>
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