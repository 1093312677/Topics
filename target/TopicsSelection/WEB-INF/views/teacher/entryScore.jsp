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
	                          <span class="glyphicon glyphicon-pencil">录入成绩</span> 
	    </div>
    </div> 
    <table class="table table-hover table-striped" >
    	<tr class="info">
    		<td >学号</td>
    		<td >姓名</td>
    		<td >性别</td>
    		<td>题目名称</td>
    		<td width="150px">成绩</td>
    	</tr>
    	<form action="<%=request.getContextPath() %>/teacher/batchEntryScore.do" method="post" enctype="multipart/form-data">
    		<input type="hidden" name="gradeId" value="${gradeId }"/>
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
		    				<c:if test="${it.score.id == null}">
		    					未上传论文
		    				</c:if>
		    				<c:if test="${it.score.id !=null }">
		    					<input type="hidden" class="form-control" name="id" value="<c:out value='${it.score.id }'></c:out>"/>
		    					<input type="number" class="form-control" name="score" value="<c:out value='${it.score.score }'></c:out>"/>
		    				</c:if>
		    			</td>
		    		</tr>
		    	</c:forEach>
			</c:forEach>
			<input type="submit" value="保存" class="btn btn-success" style="float:right;width:150px;margin:15px"/>
    	</form>
    	
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