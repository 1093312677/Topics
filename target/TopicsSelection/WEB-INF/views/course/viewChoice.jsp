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
<!-- file -->
<script src="<%=request.getContextPath() %>/js/fileinput.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/js/fileinput_locale_zh.js" type="text/javascript"></script>
<link href="<%=request.getContextPath() %>/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
</head>
<body>
	
	<div class="panel panel-default" style="margin:0">
	    <div class="panel-body">
	                    查看已选择课程
	    </div>
    </div>
    <table class="table table-hover" >
    	<tr class="info">
    		<td>课程名称</td>
    		<td>操作</td>
    	</tr>
    	<c:forEach items="${checkViewGrade }" var="items">
    		<tr>
    			<td><c:out value="${items.courseName }"></c:out></td>
    			<td><a href="javascript:void(0)" onclick="deleteItem(${items.id })">删除</a></td>
    		</tr>
		</c:forEach>
    </table>
<script>
	$(function () { $("[data-toggle='tooltip']").tooltip(); });
	
	//删除
	function deleteItem(id){
		if(confirm("取认删除？")){
			$.ajax({
				type:"post",
				url:"<%=request.getContextPath() %>/topic/deleteTopicNotThrought.do",
				data:{"id":id,
				},
				dataType:"json",
				success:function(data){
					if(data==1){
						alert("删除成功！")
					}
					 window.setTimeout(reload,200);
					
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
	
</script>	
	
</body>
</html>