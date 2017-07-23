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
	                            查看<span class="glyphicon glyphicon-eye-open">题目</span> <a href="#" data-toggle="modal" data-target="#myModal" id="add"> <span class="	glyphicon glyphicon-plus-sign" style="color:green;float:right" data-toggle="tooltip" data-placement="bottom" title="添加"></span></a>
    			
	    </div>
    </div> 
    <table class="table table-hover table-striped" >
    	<tr class="info">
    		<td >编号</td>
    		<td>题目名称</td>
    		<td>适用方向</td>
    		<td width=50px>可选学生</td>
    		<td width=50px>已选学生</td>
    		<td>指导老师</td>
    		<td>题目状态</td>
    		<td>发布时间</td>
    		<td>题目简介</td>
    		<td>任务书</td>
    		
    		<td>操作</td>
    	</tr>
    	<c:forEach items="${topics }" var="items">
        	<c:if test="${items.state == state}">
	    		<tr>
	    			<td><c:out value="${items.id }"></c:out></td>
	    			<td><c:out value="${items.topicsName }"></c:out></td>
	    			
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
    						<a href="<%=request.getContextPath()%>/clazz/findClazz.do?id=${items.id }" > <span class="glyphicon glyphicon-edit" style="color:green;padding-left:20px" data-toggle="tooltip" data-placement="bottom" title="更新"></span></a>
	    				</c:if>
	    			</td>
	    		</tr>
	    	 </c:if>
		</c:forEach>
    </table>
    
      <!-- 分页开始 -->
    <div class="col-sm-2">
    	<ul class="pagination" style="margin-top:1px">
	    	<!-- 上一页 -->
	    	<c:if test="${pagination.page>1 }">
	    		<li><a href="<%=request.getContextPath() %>/department/viewDepartment.do?page=${pagination.page-2 }">上一页</a></li>
	    	</c:if>
			<c:if test="${pagination.page<=1 }">
				<li class="disabled"><a href="javascript:void(0)">上一页</a></li>
	    	</c:if>
			<!-- 下一页 -->
			<c:if test="${pagination.page<pagination.totlePage }">
	    		<li><a href="<%=request.getContextPath() %>/department/viewDepartment.do?page=${pagination.page }">下一页</a></li>
	    	</c:if>
			<c:if test="${pagination.page>=pagination.totlePage }">
				<li class="disabled"><a href="javascript:void(0)">下一页</a></li>
	    	</c:if>
		</ul>
    </div>
	<div class="col-sm-2">
	<form action="<%=request.getContextPath() %>/department/viewDepartment.do" method="post">
		<div class="input-group" width="200px">
				<input type="number" class="form-control" name="page" value="1">
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
   <!-- /分页开始 --> 
    
    
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