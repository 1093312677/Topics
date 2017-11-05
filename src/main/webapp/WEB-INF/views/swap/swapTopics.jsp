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
	<!-- 提示框开始
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
				
				<!-- 文字内容开始
				<div class="content" align=center id="content">
				
				</div>
				<!-- 文字内容结束
			</div>
		</div>
	</div>-->
<!-- 提示框结束 -->
	<div class="panel panel-default" style="margin:0">
	    <div class="panel-body">
	                          教师调剂
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
    		<td>发布时间</td>
    		<td>题目简介</td>
    		
    		<td>操作</td>
    	</tr>
    	<c:forEach items="${topics }" var="items">
    		<c:if test="${items.state == 1 }">
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
	    			
	    			<td><c:out value="${items.time }"></c:out></td>
	    			<td><c:out value="${items.introduce }"></c:out></td>
	    			<td>
	    				<c:if test="${items.enableSelect > items.selectedStudent }">
    						<a href="javascript:void(0)" onclick="swap(${items.id })" > 调剂至此题目</a>
	    				</c:if>
	    			</td>
	    		</tr>
	    	</c:if>
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
	
	function swap(id) {
		
		var url = "<%=request.getContextPath()%>/swap/swapTeacher.do?type=teacher";
		swal({
			  title: "确认调剂 ？",
			  text: "",
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonColor: "#DD6B55",
			  confirmButtonText: "　Yes　",
			  closeOnConfirm: false,
			  showLoaderOnConfirm: true, 
		},
		function(){
			$.ajax({
				type:"post",
				url:url,
				data:{"topicId":id},
				dataType:"json",
				success:function(data){
					if(data.result==1){
						swal("调剂 成功!", "", "success");
						window.setTimeout("jump()",700);
					}else{
						swal("调剂 失败！", "请重试！", "error");
					}
				},
				error:function(msg){
					swal("调剂 失败！", "请重试！", "error");
					window.setTimeout("location.reload()",700);
				}
			})	
		});
	}
	
	function reload(){
		location.reload()
	}
	function jump() {
        var gradeId = ${gradeId};
        var jumpUrl = "<%=request.getContextPath()%>/teacher/viewStudentSelectedIntent.do?gradeId="+gradeId;
        window.location.href=jumpUrl;
    }
</script>	
	
</body>
</html>