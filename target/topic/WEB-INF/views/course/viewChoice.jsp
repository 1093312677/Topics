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
<!-- alert -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/js/sweetalert/sweetalert.css"/>
<script src="<%=request.getContextPath() %>/js/sweetalert/sweetalert-dev.js"></script>
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
		
		swal({
			  title: "取认删除？",
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonColor: "#DD6B55",
			  confirmButtonText: "　Yes　",
			  closeOnConfirm: false
		},
		function(){
			$.ajax({
				type:"post",
				url:"<%=request.getContextPath() %>/course/deleteCourseChoice.do",
				data:{"id":id,
				},
				dataType:"json",
				success:function(data){
					if(data==1){
						swal("删除成功!", "请继续操作!", "success");
					}
					 window.setTimeout(reload,800);
					
				},
				error:function(msg){
					console.log(msg)
				}
			})
		});
	}
	
	function reload(){
		location.reload()
	}
</script>
	
	
</body>
</html>