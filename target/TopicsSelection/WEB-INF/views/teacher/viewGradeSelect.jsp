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
	                         　选择 <span class="glyphicon glyphicon-screenshot">年级</span>
	    </div>
    </div>
    <table class="table table-hover table-striped" >
    	<c:if test="${viewType =='gradeSelect' }">
    		<c:forEach items="${grades }" var="items">
	    		<tr class="" align=center>
	    			<td><a href="<%=request.getContextPath() %>/teacher/viewStudentSelectedIntent.do?gradeId=<c:out value="${items.id }"></c:out>"><c:out value="${items.gradeName }"></c:out></a></td>
	    		</tr>
			</c:forEach>
    	</c:if>
	  <!-- 查看未选题 -->	
		<c:if test="${viewType =='gradeNotSelect' }">
    		<c:forEach items="${grades }" var="items">
	    		<tr class="" align=center>
	    			<td><a href="<%=request.getContextPath() %>/teacher/viewNotSelected.do?gradeId=<c:out value="${items.id }"></c:out>"><c:out value="${items.gradeName }"></c:out></a></td>
	    		</tr>
			</c:forEach>
    	</c:if>
	  <!-- 查看毕业最后选题 -->	
		<c:if test="${viewType =='topicEvaluation' }">
    		<c:forEach items="${grades }" var="items">
	    		<tr class="" align=center>
	    			<td><a href="<%=request.getContextPath() %>/teacher/topicEvaluation.do?gradeId=<c:out value="${items.id }"></c:out>"><c:out value="${items.gradeName }"></c:out></a></td>
	    		</tr>
			</c:forEach>
    	</c:if>
      <!-- 查看成绩 -->	
    	<c:if test="${viewType =='viewResults' }">
    		<c:forEach items="${grades }" var="items">
	    		<tr class="" align=center>
	    			<td><a href="<%=request.getContextPath() %>/teacher/viewResults.do?gradeId=<c:out value="${items.id }"></c:out>"><c:out value="${items.gradeName }"></c:out></a></td>
	    		</tr>
			</c:forEach>
    	</c:if>
   <!-- 录入成绩 -->   	
    	<c:if test="${viewType =='entryScore' }">
    		<c:forEach items="${grades }" var="items">
	    		<tr class="" align=center>
	    			<td><a href="<%=request.getContextPath() %>/teacher/entryScore.do?gradeId=<c:out value="${items.id }"></c:out>"><c:out value="${items.gradeName }"></c:out></a></td>
	    		</tr>
			</c:forEach>
    	</c:if>
  <!-- 查看毕业最后选题 -->
    	<c:if test="${viewType =='viewLastSelect' }">
    		<c:forEach items="${grades }" var="items">
	    		<tr class="" align=center>
	    			<td><a href="<%=request.getContextPath() %>/teachStu/viewLastSelect.do?gradeId=<c:out value="${items.id }"></c:out>"><c:out value="${items.gradeName }"></c:out></a></td>
	    		</tr>
			</c:forEach>
    	</c:if>
    	
<!-- 查看分组 -->
    	<c:if test="${viewType =='viewGroup' }">
    		<c:forEach items="${grades }" var="items">
	    		<tr class="" align=center>
	    			<td><a href="<%=request.getContextPath() %>/group/viewGroup.do?gradeId=<c:out value="${items.id }"></c:out>"><c:out value="${items.gradeName }"></c:out></a></td>
	    		</tr>
			</c:forEach>
    	</c:if>
    	
 <!-- 查看分组成员 -->
    	<c:if test="${viewType =='viewGroupMember' }">
    		<c:forEach items="${grades }" var="items">
	    		<tr class="" align=center>
	    			<td><a href="<%=request.getContextPath() %>/group/viewGroupMember.do?gradeId=<c:out value="${items.id }"></c:out>"><c:out value="${items.gradeName }"></c:out></a></td>
	    		</tr>
			</c:forEach>
    	</c:if>   	
<!-- 答辩时间和地点 -->
    	<c:if test="${viewType =='timeAndPlace' }">
    		<c:forEach items="${grades }" var="items">
	    		<tr class="" align=center>
	    			<td><a href="<%=request.getContextPath() %>/group/viewTimeAndPlace.do?gradeId=<c:out value="${items.id }"></c:out>"><c:out value="${items.gradeName }"></c:out></a></td>
	    		</tr>
			</c:forEach>
    	</c:if> 	
<!-- 设置教师出题人数选择 -->
    	<c:if test="${viewType =='setTeacherTopicNum' }">
    		<c:forEach items="${grades }" var="items">
	    		<tr class="" align=center>
	    			<td><a href="<%=request.getContextPath() %>/limit/viewSetTeacherTopicNum.do?type=null&gradeId=<c:out value="${items.id }"></c:out>"><c:out value="${items.gradeName }"></c:out></a></td>
	    		</tr>
			</c:forEach>
    	</c:if> 
 <!-- 查看课程成绩 -->
    	<c:if test="${viewType =='viewGradeCourse' }">
    		<c:forEach items="${grades }" var="items">
	    		<tr class="" align=center>
	    			<td><a href="<%=request.getContextPath() %>/course/viewGradeCourse.do?gradeId=<c:out value="${items.id }"></c:out>"><c:out value="${items.gradeName }"></c:out></a></td>
	    		</tr>
			</c:forEach>
    	</c:if>    	
 <!-- 教师选择需要查看的课程 -->
    	<c:if test="${viewType =='viewCourse' }">
    		<c:forEach items="${grades }" var="items">
	    		<tr class="" align=center>
	    			<td><a href="<%=request.getContextPath() %>/course/viewCourse.do?gradeId=<c:out value="${items.id }"></c:out>"><c:out value="${items.gradeName }"></c:out></a></td>
	    		</tr>
			</c:forEach>
    	</c:if>     	
  <!-- 教师选择需要查看的课程 -->
    	<c:if test="${viewType =='viewCourseChoice' }">
    		<c:forEach items="${grades }" var="items">
	    		<tr class="" align=center>
	    			<td><a href="<%=request.getContextPath() %>/course/viewCourseChoice.do?gradeId=<c:out value="${items.id }"></c:out>"><c:out value="${items.gradeName }"></c:out></a></td>
	    		</tr>
			</c:forEach>
    	</c:if>   	
<!-- 指导老师评阅 -->
    	<c:if test="${viewType =='instructorReview' }">
    		<c:forEach items="${grades }" var="items">
	    		<tr class="" align=center>
	    			<td><a href="<%=request.getContextPath() %>/attach/instructorReview.do?gradeId=<c:out value="${items.id }"></c:out>"><c:out value="${items.gradeName }"></c:out></a></td>
	    		</tr>
			</c:forEach>
    	</c:if>     	
<!-- 中期评阅老师评阅 -->
    	<c:if test="${viewType =='midReview' }">
    		<c:forEach items="${grades }" var="items">
	    		<tr class="" align=center>
	    			<td><a href="<%=request.getContextPath() %>/attach/midReview.do?gradeId=<c:out value="${items.id }"></c:out>"><c:out value="${items.gradeName }"></c:out></a></td>
	    		</tr>
			</c:forEach>
    	</c:if>
<!-- 评阅小组长评阅 -->
    	<c:if test="${viewType =='replyResults' }">
    		<c:forEach items="${grades }" var="items">
	    		<tr class="" align=center>
	    			<td><a href="<%=request.getContextPath() %>/attach/replyResults.do?gradeId=<c:out value="${items.id }"></c:out>"><c:out value="${items.gradeName }"></c:out></a></td>
	    		</tr>
			</c:forEach>
    	</c:if>    	
<!-- 系内调配 -->
    	<c:if test="${viewType =='swapStudentDept' }">
    		<c:forEach items="${grades }" var="items">
	    		<tr class="" align=center>
	    			<td><a href="<%=request.getContextPath() %>/swap/viewSwapStudentDept.do?gradeId=<c:out value="${items.id }"></c:out>"><c:out value="${items.gradeName }"></c:out></a></td>
	    		</tr>
			</c:forEach>
    	</c:if>  
    	
    	   
    	<c:choose>
    		<c:when test="${viewType =='viewGuideStudent' }">
    		  <!-- 查看指导学生 --> 
    			<c:forEach items="${grades }" var="items">
		    		<tr class="" align=center>
		    			<td><a href="<%=request.getContextPath() %>/teachStu/viewGuideStudent.do?gradeId=<c:out value="${items.id }"></c:out>"><c:out value="${items.gradeName }"></c:out></a></td>
		    		</tr>
				</c:forEach>
    		</c:when>
    		<c:when test="${viewType =='attach' }">
    		  <!-- 查看指导学生 --> 
    			<c:forEach items="${grades }" var="items">
		    		<tr class="" align=center>
		    			<td><a href="<%=request.getContextPath() %>/attach/downAttach.do?gradeId=<c:out value="${items.id }"></c:out>"><c:out value="${items.gradeName }"></c:out></a></td>
		    		</tr>
				</c:forEach>
    		</c:when>
    		
    		<c:when test="${viewType =='addTopic' }">
    		  <!-- 添加题目 --> 
    			<c:forEach items="${grades }" var="items">
		    		<tr class="" align=center>
		    			<td><a href="<%=request.getContextPath() %>/topic/goAddTopic.do?gradeId=<c:out value="${items.id }"></c:out>"><c:out value="${items.gradeName }"></c:out></a></td>
		    		</tr>
				</c:forEach>
    		</c:when>
    		<c:when test="${viewType =='autoSelect' }">
    		  <!-- 教师自动选择题目 --> 
    			<c:forEach items="${grades }" var="items">
		    		<tr class="" align=center>
		    			<td><a href="<%=request.getContextPath() %>/teachStu/viewTeacherAutoSelect.do?gradeId=<c:out value="${items.id }"></c:out>"><c:out value="${items.gradeName }"></c:out></a></td>
		    		</tr>
				</c:forEach>
    		</c:when>
    		<c:when test="${viewType =='viewTime' }">
    		  <!-- 教师查看时间 --> 
    			<c:forEach items="${grades }" var="items">
		    		<tr class="" align=center>
		    			<td><a href="<%=request.getContextPath() %>/teacher/viewTime.do?gradeId=<c:out value="${items.id }"></c:out>"><c:out value="${items.gradeName }"></c:out></a></td>
		    		</tr>
				</c:forEach>
    		</c:when>
    		
    	</c:choose>	
    	
    	
    	
    	
    </table>
	
    	
    
    
<script>
	$(function () { $("[data-toggle='tooltip']").tooltip(); });
</script>	
	
</body>
</html>