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
	<c:if test="${message =='success'}">
		<div class="alert alert-success alert-dismissable">
			<button type="button" class="close" data-dismiss="alert"
					aria-hidden="true">
				&times;
			</button>
			成功！
		</div>
	</c:if>
	<c:if test="${message =='failed'}">
		<div class="alert alert-danger alert-dismissable">
			<button type="button" class="close" data-dismiss="alert" aria-hidden="true">
				&times;
			</button>
			失败！
		</div>	
	</c:if>
	<div class="panel panel-default" style="margin:0">
	    <div class="panel-body">
	                            第${bc[0] }批次第${bc[1] }志愿
	    </div>
    </div>
    <table class="table table-hover" >
    	<tr class="info">
    		<td >编号</td>
    		<td>题目名称</td>
    		<td >可选学生</td>
    		<td >已选学生</td>
    		
    		<td>发布时间</td>
    		<td>题目简介</td>
    		<td >任务书</td>
    		<td>选择学生</td>
    	</tr>
    	<c:forEach items="${topics }" var="items">
	    		<tr>
	    			<td><c:out value="${items.id }"></c:out></td>
	    			<td><c:out value="${items.topicsName }"></c:out></td>
	    			
	    			
	    			<td><c:out value="${items.enableSelect }"></c:out></td>
	    			<td><c:out value="${items.selectedStudent }"></c:out></td>
	    			
	    			<td><c:out value="${items.time }"></c:out></td>
	    			<td><c:out value="${items.introduce }"></c:out></td>
	    			<td>
	    				<c:if test="${items.taskBookName == null}">
	    					未上传
	    				</c:if>
	    				<c:if test="${items.taskBookName !=null }">
	    					<a href="<%=request.getContextPath() %>/upload/<c:out value="${items.taskBookName }"></c:out>"><span class="glyphicon glyphicon-download-alt" style="color:green;float:right" data-toggle="tooltip" data-placement="bottom" title="下载"></span></a>
	    				</c:if>
	    				
	    			</td>
	    			
	    			<td>
	    				<c:forEach items="${items.intentionTopics }" var="intentionTopics">
	    					<c:if test="${intentionTopics.student.id > 0 }">
	    						<c:choose>
		    						<c:when test="${intentionTopics.student.topics.id > 0 }">
		    							<a href="<%=request.getContextPath() %>/student/viewStudentOne.do?filter=yes&no=<c:out value="${intentionTopics.student.no }"></c:out>&id=<c:out value="${intentionTopics.student.id }"></c:out>"><c:out value="${intentionTopics.student.name }"></c:out>(已选)</a>
		    							<br>
		    						</c:when>
		    						<c:otherwise>
		    							<div class="dropdown">
										    <button class="btn btn-default dropdown-toggle" type="button" id="menu1" data-toggle="dropdown">
												<c:out value="${intentionTopics.student.name }"></c:out>
												<c:if test="${intentionTopics.swap == 1}">
												(可调剂)
												</c:if>
												<c:if test="${intentionTopics.swap == 0}">
												(不可调剂)
												</c:if>
										    	<span class="caret"></span>
										    </button>
										    <ul class="dropdown-menu" role="menu" aria-labelledby="menu1">
										    	<li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath() %>/teacher/comfirmStudent.do?gradeId=${gradeId }&studentId=<c:out value="${intentionTopics.student.id }"></c:out>&topicId=<c:out value="${items.id }"></c:out>">确认选择</a></li>
										        <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath() %>/student/viewStudentOne.do?filter=yes&no=<c:out value="${intentionTopics.student.no }"></c:out>&id=<c:out value="${intentionTopics.student.id }"></c:out>">查看信息</a></li>
										   		<li role="presentation"><a role="menuitem" tabindex="-1"  id="${intentionTopics.message }" class="mes"  data-toggle="modal" data-target="#myModal" href="javascript:void(0)" >查看留言</a></li>
										   		<c:if test="${intentionTopics.swap == 1}">
										   			<li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath() %>/swap/viewSwapTeacher.do?studentId=${intentionTopics.student.id}&directionId=${intentionTopics.student.clazz.direction.id }">调剂</a></li>
										    	</c:if>
										    </ul>
										</div>
		    						</c:otherwise>
		    					</c:choose>
	    					</c:if>
	    				</c:forEach>
	    			</td>
	    		</tr>
		</c:forEach>
    </table>
<!-- 查看留言 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					留言
				</h4>
			</div>
			<div class="modal-body">
					<span id="messageContainer"></span>
			</div>
			<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>    
<script>
	$(".mes").click(function(){
		$("#messageContainer").html($(this).attr("id"));
	})
	function showMessage1(message) {
		$("#messageContainer").html($(this).attr("id"));
	}
	$(function () { $("[data-toggle='tooltip']").tooltip(); });
	
</script>	
	
</body>
</html>