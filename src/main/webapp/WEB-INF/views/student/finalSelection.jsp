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
	                            最终选题
	    </div>
    </div>
    <div class="container">
    	<div class="row">
    		<div class="col-md-3">
    		
    		</div>
    		<div class="col-md-6">
    		<div class="panel panel-default" style="margin-top:25px;">
			    <div class="panel-heading">
			        <h3 class="panel-title"> 最终选题</h3>
			    </div>
			    <table class="table">
			   	    <tr><td>学号</td><td><c:out value="${student.no }"></c:out></td></tr>
			 	    <tr><td>姓名</td><td><c:out value="${student.name }"></c:out></td></tr>
			        <tr><td>题目名称</td><td><c:out value="${student.topics.topicsName }"></c:out></td></tr>
			        <tr>
			        	<td>指导教师</td>
				        <td>
				        	<a href="<%=request.getContextPath() %>/teacher/viewTeacherOne.do?id=${student.topics.teacher.id}&type=null&view=yes"><c:out value="${student.topics.teacher.name }"></c:out>
				        	</a>
				        </td>
				    </tr>
			        <tr><td width="90px">题目简介</td><td><c:out value="${student.topics.introduce }"></c:out></td></tr>
			    	<tr>
			    		<td>题目附件</td>
			    		<td>
			    			<c:if test="${student.topics.taskBookName == ''|| student.topics.taskBookName == null}">
			    				未上传附件！
			    			</c:if>
			    			<c:if test="${student.topics!= null}">
				    			<c:if test="${student.topics.taskBookName != ''}">
				    				<a href="<%=request.getContextPath() %>/document/download.do?randName=${student.topics.taskBookName }&documentName=${student.topics.topicsName }_${student.topics.teacher.name }_任务书_${student.topics.taskBookName }">下载</a>
				    			</c:if>
				    		</c:if>
			    		</td>
			    	</tr>
			       <tr><td>子题目</td><td><c:out value="${student.subTopic.subName }"></c:out></td></tr>
			    	<tr>
			    		<td>子题目附件</td>
			    		<td>
			    			<c:if test="${student.subTopic.taskBookName == ''|| student.subTopic.taskBookName == null}">
			    				未上传附件！
			    			</c:if>
			    			<c:if test="${student.subTopic!= null}">
				    			<c:if test="${student.subTopic.taskBookName != ''}">
				    				<a href="<%=request.getContextPath() %>/document/download.do?randName=${student.subTopic.taskBookName }&documentName=${student.subTopic.subName }_${student.topics.teacher.name }_子题目任务书_${student.subTopic.taskBookName }">下载</a>
				    			</c:if>
				    		</c:if>
			    		</td>
			    	</tr>
			    </table>
			</div>
    	</div>
    </div>		
    
<script>
	$(function () { $("[data-toggle='tooltip']").tooltip(); });
</script>	
	
</body>
</html>