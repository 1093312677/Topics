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
	                          教师调剂
	    </div>
    </div> 
    <table class="table table-hover table-striped" >
    	<tr class="info">
    		<td >编号</td>
    		<td width=250px>题目名称</td>
    		<td>适用方向</td>
    		<td width=50px>可选学生</td>
    		<td width=50px>已选学生</td>
    		<td>指导老师</td>
    		<td>发布时间</td>
    		
    		<td>操作</td>
    	</tr>
    	<c:forEach items="${topics }" var="items">
    		<c:if test="${items.state == 1 }">
	    		<tr>
	    			<td><c:out value="${items.id }"></c:out></td>
	    			<td>
	    				<span  data-toggle="tooltip" data-placement="bottom" title="简介：${items.introduce }">
	    					<a href="javascript:void(0)">${items.topicsName }</a>
	   					</span>
	    			</td>
	    			<td>
		    			<c:forEach items="${items.directions }" var="directions">
		    				<c:out value="${directions.directionName }"></c:out><br>
		    			</c:forEach>
	    			</td>
	    			
	    			<td><c:out value="${items.enableSelect }"></c:out></td>
	    			<td><c:out value="${items.selectedStudent }"></c:out></td>
	    			<td>
	    				<a href="<%=request.getContextPath() %>/teacher/viewTeacherOne.do?type=no&id=<c:out value="${items.teacher.id }"></c:out>"><c:out value="${items.teacher.name }"></c:out></a>
	    			</td>
	    			
	    			<td><c:out value="${items.time }"></c:out></td>
	    			<td>
	    				<c:if test="${items.enableSelect > items.selectedStudent }">
    						<a href="javascript:void(0)" onclick="change(${items.id })" > 调剂至此题目</a>
	    				</c:if>
	    				<c:if test="${items.enableSelect <= items.selectedStudent }">
	    				超过可选人数
	    				</c:if>
	    			</td>
	    		</tr>
	    	</c:if>
		</c:forEach>
    </table>
    
<script>
	$(function () { $("[data-toggle='tooltip']").tooltip(); });
	
	function change(id){
		
		swal({
			  title: "是否更改当前志愿？",
			  text: "注意将会覆盖之前的！",
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
				url:"<%=request.getContextPath()%>/swap/swapTeacher.do",
				data:{"type":"depart","topicId":id},
				dataType:"json",
				success:function(data){
					if(data.result==1){
						swal("调剂成功!", "", "success");
						window.setTimeout(reload,200);
					}else{
						swal("调剂失败！", "请重试！", "error");
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