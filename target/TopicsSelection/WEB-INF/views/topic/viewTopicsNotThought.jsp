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
	                            查看待审核题目 
	    </div>
    </div> 
    <table class="table table-hover table-striped" >
    	<tr class="info">
    		<td >编号</td>
    		<td>题目名称</td>
    		<td>适用方向</td>
    		<td >可选学生</td>
    		<td>指导老师</td>
    		<td>题目状态</td>
    		<td>发布时间</td>
    		<td>题目简介</td>
    		<td>任务书</td>
    		
    		<td>操作</td>
    	</tr>
    	<c:forEach items="${topics }" var="items">
	    		<tr>
	    			<td><c:out value="${items.id }"></c:out></td>
	    			<td><c:out value="${items.topicsName }"></c:out></td>
	    			
	    			<td>
		    			<c:forEach items="${items.directions }" var="directions">
		    				<c:out value="${directions.directionName }"></c:out><br>
		    			</c:forEach>
	    			</td>
	    			
	    			<td><c:out value="${items.enableSelect }"></c:out></td>
	    			<td>
	    				<a href="<%=request.getContextPath() %>/teacher/viewTeacherOne.do?type=no&id=<c:out value="${items.teacher.id }"></c:out>"><c:out value="${items.teacher.name }"></c:out></a>
	    			</td>
	    			<td>
	    				<c:if test="${items.state == 1}">
	    					<span style="color:green"><span class="glyphicon glyphicon-ok-sign" >通过审核</span></span>
	    				</c:if>
	    				<!-- -->
	    				<c:if test="${items.state == 2}">
	    					<span style="color:#604884"><span class="glyphicon glyphicon-info-sign" >在审核中</span></span>
	    				</c:if>
	    				<c:if test="${items.state == 3}">
	    					<span style="color:red"><span class="glyphicon glyphicon-remove-sign" >未通审核</span></span>
	    				</c:if>
	    				 
	    			</td>
	    			
	    			
	    			<td><c:out value="${items.time }"></c:out></td>
	    			<td><c:out value="${items.introduce }"></c:out></td>
	    			<td>
	    				<c:if test="${items.taskBookName == ''}">
	    					未上传
	    				</c:if>
	    				<c:if test="${items.taskBookName !='' }">
	    					<a href="<%=request.getContextPath() %>/upload/<c:out value="${items.taskBookName }"></c:out>"><span class="glyphicon glyphicon-download-alt" style="color:green;float:right" data-toggle="tooltip" data-placement="bottom" title="下载"></span></a>
	    				</c:if>
	    				
	    			</td>
	    			
	    			<td>
	    				<c:if test="${state == 2 }">
	    					<button type="button" class="btn btn-default btn-sm" style="color:green">
							    <span class="glyphicon glyphicon-ok"></span>
								<a href="javascript:void(0)" style="color:green" id="audit" onclick="audit(<c:out value="${items.id }"></c:out>)"> 通过审核</a>
							</button>
	    				</c:if>
	    				
	    				<c:if test="${state == 1 }">
	    					<a href="javascript:void(0)" onclick="deleteItem(${items.id })"> <span class="glyphicon  glyphicon-trash" style="color:red" data-toggle="tooltip" data-placement="bottom" title="删除"></span></a>
	    				</c:if>
	    			</td>
	    		</tr>
		</c:forEach>
    </table>
    
    
<script>
	$(function () { $("[data-toggle='tooltip']").tooltip(); });
	$("#alert").hide();
	$("#failed").hide();
	$("#success").hide();
	$("#alert-close").click(function(){
		$("#alert").fadeOut();
	})
	
	function audit(id){
		$.ajax({
			type:"post",
			url:"<%=request.getContextPath()%>/teacher/auditTopic.do",
			data:{"topicId":id},
			dataType:"json",
			success:function(data){
				if( data == 1 ) {
					$("#alert").fadeIn();
					$("#success").fadeIn();
				} else {
					$("#alert").fadeIn();
					$("#failed").fadeIn();
				}
				
				window.setTimeout(reload,1000);
			},
			error:function(msg){
				console.log(msg)
			}
		})	
	}
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
	
</body>
</html>