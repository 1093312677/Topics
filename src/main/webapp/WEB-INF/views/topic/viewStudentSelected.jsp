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
	         <a href="<%=request.getContextPath() %>/topic/viewTopic.do?state=1&gradeId=${gradeId}"><span class="glyphicon glyphicon-chevron-left"></span></a>  查看已选学生
	    </div>
    </div>
    <div id="t1" class="t1">
	    <table class="table table-hover table-striped" id="table">
	    	<tr class="info">
	    		<td width="100px">学号</td>
	    		<td>姓名</td>
	    		<td>班级</td>
	    		<td>方向</td>
	    		<td>操作</td>
	    	</tr>
	    	<c:forEach items="${students }" var="items">
	    		<tr>
	    			<td><c:out value="${items.no }"></c:out></td>
	    			<td>
	    				<a href="<%=request.getContextPath() %>/student/viewStudentOne.do?filter=no&no=<c:out value="${items.no }"></c:out>&id=<c:out value="${items.id }"></c:out>"><c:out value="${items.name }"></c:out></a>
	    			</td>
	    			<td><c:out value="${items.clazz.className }"></c:out></td>
	    			<td><c:out value="${items.clazz.direction.directionName }"></c:out></td>
	    			<td><a href="javascript:void(0)" onclick="deleteItem(${items.id })">退选题目</a></td>
	    		</tr>
			</c:forEach>
	    </table>
   </div>


<script>
	//删除
	function deleteItem(id){
		swal({
			  title: "是否退选毕业选题?",
			  text: "不可撤回！",
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonColor: "#DD6B55",
			  confirmButtonText: "　Yes　",
			  showLoaderOnConfirm: true, 
			  closeOnConfirm: false
		},
		function(){
			$.ajax({
				type:"post",
				url:"<%=request.getContextPath()%>/topic/withdrawalTopic.do",
				data:{"studentId":id},
				dataType:"json",
				success:function(data){
					if(data==1){
						swal("退选成功!", "", "success");
						window.setTimeout(reload,700);
					}else{
						swal("退选失败！", "请重试！", "error");
					}
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