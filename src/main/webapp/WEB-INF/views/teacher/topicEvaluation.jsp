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
	<!-- 提示框开始 -->
	<div class="alert-container" id="alert">
		<div class="box-container">
			<div class="alert-title">
				<div class="close" id="alert-close">
					<span class="glyphicon glyphicon-remove" style="line-height:50px;"></span>
				</div>
			</div>
			
			<div class="alert-content">
				<span id="success" class="glyphicon glyphicon-ok-circle" style="color:#dff0d8;font-size:60px;margin-left:40%;margin-top:20px"></span>
				<span id="failed" class="glyphicon glyphicon-remove-circle" style="color:#f2dede;font-size:60px;margin-left:40%;margin-top:20px"></span>
				
				<!-- 文字内容开始 -->
				<div class="content" align=center id="content">
				
				</div>
				<!-- 文字内容结束 -->
			</div>
		</div>
	</div>
<!-- 提示框结束 -->
	<div class="panel panel-default" style="margin:0">
	    <div class="panel-body">
	                          <span class="glyphicon glyphicon-pencil">题目评测</span> 
	    </div>
    </div> 
    <table class="table table-hover table-striped" >
    	<tr class="info">
    		<td >学号</td>
    		<td >姓名</td>
    		<td >性别</td>
    		<td>题目名称</td>
    		<td>毕业论文</td>
    		
    	</tr>
    	<c:forEach items="${topics }" var="items">
	    	<c:forEach items="${items.students }" var="it">
	    		<tr>
	    			<td><c:out value="${it.no }"></c:out></td>
	    			<td>
	    				<a href="<%=request.getContextPath() %>/student/viewStudentOne.do?id=<c:out value="${it.id }"></c:out>"><c:out value="${it.name }"></c:out></a>
	    			</td>
	    			<td><c:out value="${it.sex }"></c:out></td>
	    			<td><c:out value="${it.topics.topicsName }"></c:out></td>
	    			
	    			<td>
	    				<c:if test="${it.score.fileName == null}">
	    					未上传
	    				</c:if>
	    				<c:if test="${it.score.fileName !=null }">
	    					<a href="<%=request.getContextPath() %>/upload/<c:out value="${it.score.fileName }"></c:out>"><span class="glyphicon glyphicon-download-alt" style="color:green;float:right" data-toggle="tooltip" data-placement="bottom" title="下载"></span></a>
	    				</c:if>
	    			</td>
	    		</tr>
	    	</c:forEach>
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
	
	function reload(){
		location.reload()
	}
</script>	
	
</body>
</html>