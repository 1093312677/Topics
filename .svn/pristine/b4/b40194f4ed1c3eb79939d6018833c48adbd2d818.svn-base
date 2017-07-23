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

<!-- file -->
<script src="<%=request.getContextPath() %>/js/fileinput.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/js/fileinput_locale_zh.js" type="text/javascript"></script>
<link href="<%=request.getContextPath() %>/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="panel panel-default" style="margin:0">
	    <div class="panel-body">
	                           评阅学生
	    </div>
    </div>
    <div>
	    <table class="table table-hover table-striped" id="table">
	    	<tr class="info">
	    		<td width="100px">学号</td>
	    		<td>姓名</td>
	    		<td>开题报告下载</td>
	    		<td>评阅分数</td>
	    		<td>班级</td>
	    		<td>方向</td>
	    		<td>操作</td>
	    		<td>下载</td>
	    	</tr>
	    	<c:forEach items="${students }" var="items">
	    		<tr>
	    			<td><c:out value="${items.no }"></c:out></td>
	    			<td>
	    				<a href="<%=request.getContextPath() %>/student/viewStudentOne.do?filter=yes&no=<c:out value="${items.no }"></c:out>&id=<c:out value="${items.id }"></c:out>"><c:out value="${items.name }"></c:out></a>
	    			</td>
	    			
	    			<td>
	    				<c:if test="${items.form.openingReport != null }">
	    					<a href="<%=request.getContextPath() %>/upload/${items.form.openingReport}">下载</a>
	    				</c:if>
	    			</td>
	    			
	    			<td><c:out value="${items.score.mediumScore }"></c:out></td>
	    			<td><c:out value="${items.clazz.className }"></c:out></td>
	    			<td><c:out value="${items.clazz.direction.directionName }"></c:out></td>
	    			<td>
	    				<c:if test="${isNow == true }">
	    					<a href="#" data-toggle="modal" data-target="#import1" id="import">
		    					<button class="btn btn-primary" onclick="getId(<c:out value="${items.id }"></c:out>)">评阅</button>
		    				</a>
	    				</c:if>
	    				<c:if test="${isNow == false }">
	    					不是评阅时间！
	    				</c:if>
	    			</td>
	    			
	    			<td>
	    				<c:if test="${items.form.interimEvalForm != null }">
	    					<a href="<%=request.getContextPath() %>/upload/${items.form.interimEvalForm}">下载</a>
	    				</c:if>
	    			</td>
	    		</tr>
			</c:forEach>
	    </table>
	  </div>
<!-- 模态框（Modal） -->
<div class="modal fade" id="import1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					上传评阅结果
				</h4>
			</div>
			<div class="modal-body">
				<form action="<%=request.getContextPath() %>/attach/submitInstructorReview.do" method="post" enctype="multipart/form-data">
					<input type="hidden" name="studentId" id="id" required  class="form-control" >
					<div class="input-group">
			            <span class="input-group-addon" style="border-radius: 0;">学生成绩</span>
			            <input type="number" name="mediumScore" required  class="form-control" >
			        </div>
			        <br>
					<div class="input-group">
			            <span class="input-group-addon" style="border-radius: 0;">评阅表　</span>
			            <input type="file" name="file" required class="file" class="form-control" accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet">
			        </div>
					<br>
					<input type="submit" value="保存" class="btn btn-primary" style="width:150px"/>
				</form>
				
				<br>
				<br>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>







<script>
function getId(id) {
	$("#id").val(id)
}
</script>
</body>
</html>