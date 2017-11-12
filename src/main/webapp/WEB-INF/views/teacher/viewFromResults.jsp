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
          <span class="glyphicon glyphicon-pencil">查看文档提交情况</span> 
	    </div>
    </div> 
    <table class="table table-hover table-striped">
    	<tr class="info">
    		<td >学号</td>
    		<td >姓名</td>
    		<td >性别</td>
    		<td >班级</td>
    		<td >方向</td>
    		<td width=250px>题目名称</td>
    		<td width=60px>开题报告</td>
    		<td width=60px>中期报告</td>
    		<td width=60px>毕业论文</td>
    		<td width=60px>指导老师评阅</td>
    		<td width=60px>小组评阅</td>
    		<td width=60px>组长评阅</td>
    		
    	</tr>
	    	<c:forEach items="${students}" var="it">
	    		<tr>
	    			<td><c:out value="${it.no }"></c:out></td>
	    			<td>
	    				<a href="<%=request.getContextPath() %>/student/viewStudentOne.do?filter=no&no=${it.no }&id=${it.id }">${it.name }</a>
	    			</td>
	    			<td><c:out value="${it.sex }"></c:out></td>
	    			<td><c:out value="${it.clazz.className }"></c:out></td>
	    			<td><c:out value="${it.clazz.direction.directionName }"></c:out></td>
	    			
	    			
	    			<td>
	    				<c:choose>
	    					<c:when test="${it.topics.topicsName == null }">
	    						<span style="color:blue">未选题</span>
	    					</c:when>
	    					<c:otherwise>
	    						<c:out value="${it.topics.topicsName }"></c:out>
	    					</c:otherwise>
	    				</c:choose>
	    			</td>
	    			
	    			<td>
	    				<c:choose>
	    					<c:when test="${it.form.openingReport == null || it.form.openingReport == ''}">
	    						未提交
	    					</c:when>
	    					<c:otherwise>
	    						<a href="<%=request.getContextPath() %>/document/download.do?randName=${it.form.openingReport }&documentName=${it.no }_${it.name }_开题报告_${it.form.openingReport }">
									<span class="glyphicon glyphicon-download-alt" style="color:green;float:right" data-toggle="tooltip" data-placement="bottom" title="下载"></span>
								</a>
	    					</c:otherwise>
	    				</c:choose>
	    			</td>
	    			
	    			<td>
	    				<c:choose>
	    					<c:when test="${it.form.interimReport == null || it.form.interimReport == ''}">
	    						未提交
	    					</c:when>
	    					<c:otherwise>
	    						<a href="<%=request.getContextPath() %>/document/download.do?randName=${it.form.interimReport }&documentName=${it.no }_${it.name }_中期报告_${it.form.interimReport }">
									<span class="glyphicon glyphicon-download-alt" style="color:green;float:right" data-toggle="tooltip" data-placement="bottom" title="下载"></span>
								</a>
	    					</c:otherwise>
	    				</c:choose>
	    			</td>
	    			
	    			<td>
	    				<c:choose>
	    					<c:when test="${it.form.fileName == null || it.form.fileName == ''}">
	    						未提交
	    					</c:when>
	    					<c:otherwise>
	    						<a href="<%=request.getContextPath() %>/document/download.do?randName=${it.form.fileName }&documentName=${it.no }_${it.name }_毕业论文_${it.form.fileName }">
									<span class="glyphicon glyphicon-download-alt" style="color:green;float:right" data-toggle="tooltip" data-placement="bottom" title="下载"></span>
								</a>
	    					</c:otherwise>
	    				</c:choose>
	    			</td>
	    			
	    			<td>
	    				<c:choose>
	    					<c:when test="${it.form.interimEvalForm == null || it.form.interimEvalForm == ''}">
	    						未提交
	    					</c:when>
	    					<c:otherwise>
	    						<a href="<%=request.getContextPath() %>/document/download.do?randName=${it.form.interimEvalForm }&documentName=${it.no }_${it.name }_指导老师评阅表_${it.form.interimEvalForm }">
									<span class="glyphicon glyphicon-download-alt" style="color:green;float:right" data-toggle="tooltip" data-placement="bottom" title="下载"></span>
								</a>
	    					</c:otherwise>
	    				</c:choose>
	    			</td>
	    			
	    			<td>
	    				<c:choose>
	    					<c:when test="${it.form.reviewEvalForm == null || it.form.reviewEvalForm == ''}">
	    						未提交
	    					</c:when>
	    					<c:otherwise>
	    						<a href="<%=request.getContextPath() %>/document/download.do?randName=${it.form.reviewEvalForm }&documentName=${it.no }_${it.name }_小组评阅表_${it.form.reviewEvalForm }">
									<span class="glyphicon glyphicon-download-alt" style="color:green;float:right" data-toggle="tooltip" data-placement="bottom" title="下载"></span>
								</a>
	    					</c:otherwise>
	    				</c:choose>
	    			</td>
	    			
	    			<td>
	    				<c:choose>
	    					<c:when test="${it.form.replyRecord == null || it.form.replyRecord == ''}">
	    						未提交
	    					</c:when>
	    					<c:otherwise>
	    						<a href="<%=request.getContextPath() %>/document/download.do?randName=${it.form.replyRecord }&documentName=${it.no }_${it.name }_答辩评阅表_${it.form.replyRecord }">
									<span class="glyphicon glyphicon-download-alt" style="color:green;float:right" data-toggle="tooltip" data-placement="bottom" title="下载"></span>
								</a>
	    					</c:otherwise>
	    				</c:choose>
	    			</td>
	    			
	    		</tr>
	    	</c:forEach>
    </table>
    	  <!-- 分页开始 -->
	    <div class="col-sm-2">
	    	<ul class="pagination" style="margin-top:1px">
		    	<!-- 上一页 -->
		    	<c:if test="${pagination.page>1 }">
		    		<li><a href="<%=request.getContextPath() %>/teacher/viewFormResults.do?gradeId=${gradeId }&page=${pagination.page-2 }">上一页</a></li>
		    	</c:if>
				<c:if test="${pagination.page<=1 }">
					<li class="disabled"><a href="javascript:void(0)">上一页</a></li>
		    	</c:if>
				<!-- 下一页 -->
				<c:if test="${pagination.page<pagination.totlePage }">
		    		<li><a href="<%=request.getContextPath() %>/teacher/viewFormResults.do?gradeId=${gradeId }&page=${pagination.page }">下一页</a></li>
		    	</c:if>
				<c:if test="${pagination.page>=pagination.totlePage }">
					<li class="disabled"><a href="javascript:void(0)">下一页</a></li>
		    	</c:if>
			</ul>
	    </div>
		<div class="col-sm-2">
		<form action="<%=request.getContextPath() %>/teacher/viewFormResults.do?gradeId=${gradeId }" method="post" onsubmit="return checkPage()">
			<div class="input-group" width="100px">
					<input type="number" class="form-control" name="page" value="1" id="page">
					<input type="hidden" class="form-control" name="pageType" value="1">
					<span class="input-group-btn">
					<button class="btn btn-default" type="submit">
						跳转
					</button>
				</span>
			</div><!-- /input-group -->
		</form>
		</div>
		<div class="col-sm-8" style="margin-top:10px">
			<p class="text-left">共 ${pagination.totleSize }条记录，每页显示${pagination.eachPage }条，当前( ${pagination.page }/${pagination.totlePage } )页</p>
	    </div>
	   <!-- /分页--> 
    
<script>
	$(function () { $("[data-toggle='tooltip']").tooltip(); });
</script>	
	
</body>
</html>