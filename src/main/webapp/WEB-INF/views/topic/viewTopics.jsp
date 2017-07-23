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
	                            查看<span class="glyphicon glyphicon-eye-open">题目</span> 
	          <c:if test="${state == 1 }">
		         <a href="<%=request.getContextPath() %>/topic/exportTopic.do">
		        	 <span class="glyphicon glyphicon-export" style="color:green;float:right;margin-right:80px" data-toggle="tooltip" data-placement="bottom" title="导出题目情况"></span>
		   		 </a>
		   		 <a href="<%=request.getContextPath() %>/topic/exportSubTopic.do">
		        	 <span style="float:right;margin-right:20px;">导出子题目</span>
		   		 </a>
		   	  </c:if>
	    </div>
    </div> 
    <table class="table table-hover table-striped" >
    	<tr class="info">
    		<td >编号</td>
    		<td width=200px>题目名称</td>
    		<td>适用方向</td>
    		<td width=50px>可选学生</td>
    		<td width=50px>已选学生</td>
    		<td>指导老师</td>
    		<td width=90px>题目状态</td>
    		<td>发布时间</td>
    		<td>任务书</td>
    		<c:if test="${state == 1 }">
    			<td>查看学生</td>
    		</c:if>
    		
    		<td>操作</td>
    	</tr>
    	<c:forEach items="${topics }" var="items">
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
	    			<td>
	    				<c:if test="${items.taskBookName == ''}">
	    					未上传
	    				</c:if>
	    				<c:if test="${items.taskBookName !='' }">
	    					<a href="<%=request.getContextPath() %>/upload/<c:out value="${items.taskBookName }"></c:out>"><span class="glyphicon glyphicon-download-alt" style="color:green;float:right" data-toggle="tooltip" data-placement="bottom" title="下载"></span></a>
	    				</c:if>
	    				
	    			</td>
	    			<c:if test="${state == 1 }">
		    			<td><a href="<%=request.getContextPath() %>/topic/viewStudentSelected.do?topicId=${items.id}">查看学生</a></td>
		    		</c:if>
	    				
	    			<td>
	    				<c:if test="${state == 2 }">
	    					<button type="button" class="btn btn-default btn-sm" style="color:green">
							    <span class="glyphicon glyphicon-ok"></span>
								<a href="javascript:void(0)" style="color:green" id="audit" onclick="audit(<c:out value="${items.id }"></c:out>)"> 通过审核</a>
							</button>
							<button type="button" class="btn btn-default btn-sm" style="color:red">
							    <span class="glyphicon glyphicon-remove"></span>
								<a href="javascript:void(0)" style="color:red" id="audit" onclick="noaudit(<c:out value="${items.id }"></c:out>)"> 未通过审核</a>
							</button>
	    				</c:if>
	    				
	    				<c:if test="${state == 1 }">
	    					<a href="javascript:void(0)" onclick="deleteItem(${items.id })"> <span class="glyphicon  glyphicon-trash" style="color:red" data-toggle="tooltip" data-placement="bottom" title="删除"></span></a>
    						<a href="<%=request.getContextPath()%>/topic/update.do?id=${items.id }" > <span class="glyphicon glyphicon-edit" style="color:green;padding-left:20px" data-toggle="tooltip" data-placement="bottom" title="更新"></span></a>
	    				</c:if>
	    			</td>
	    		</tr>
		</c:forEach>
    </table>
    
      <!-- 分页开始 -->
    <div class="col-sm-2">
    	<ul class="pagination" style="margin-top:1px">
	    	<!-- 上一页 -->
	    	<c:if test="${pagination.page>1 }">
	    		<li><a href="<%=request.getContextPath() %>/topic/viewTopic.do?state=1&page=${pagination.page-2 }">上一页</a></li>
	    	</c:if>
			<c:if test="${pagination.page<=1 }">
				<li class="disabled"><a href="javascript:void(0)">上一页</a></li>
	    	</c:if>
			<!-- 下一页 -->
			<c:if test="${pagination.page<pagination.totlePage }">
	    		<li><a href="<%=request.getContextPath() %>/topic/viewTopic.do?state=1&page=${pagination.page }">下一页</a></li>
	    	</c:if>
			<c:if test="${pagination.page>=pagination.totlePage }">
				<li class="disabled"><a href="javascript:void(0)">下一页</a></li>
	    	</c:if>
		</ul>
    </div>
	<div class="col-sm-2">
	<form action="<%=request.getContextPath() %>/topic/viewTopic.do?state=1" method="post">
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
					swal("成功!", "", "success");
				} else {
					swal("失败!", "", "error");
				}
				
				window.setTimeout(reload,700);
			},
			error:function(msg){
				console.log(msg)
			}
		})	
	}
	function noaudit(id){
		$.ajax({
			type:"post",
			url:"<%=request.getContextPath()%>/teacher/notAuditTopic.do",
			data:{"topicId":id},
			dataType:"json",
			success:function(data){
				if( data == 1 ) {
					swal("成功!", "", "success");
				} else {
					swal("失败!", "", "error");
				}
				
				window.setTimeout(reload,700);
			},
			error:function(msg){
				console.log(msg)
			}
		})	
	}
	//删除
	function deleteItem(id){
		swal({
			  title: "取认删除？",
			  text: "删除该选项将会删除与该选项相关联数据！",
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonColor: "#DD6B55",
			  confirmButtonText: "　Yes　",
			  closeOnConfirm: false
		},
		function(){
			$.ajax({
				type:"post",
				url:"<%=request.getContextPath()%>/topic/deleteTopic.do",
				data:{"id":id,
				},
				dataType:"json",
				success:function(data){
					 swal("删除成功!", "", "success");
					 window.setTimeout(reload,700);
					
				},
				error:function(msg){
					swal("删除失败！", "请重试！", "error");
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