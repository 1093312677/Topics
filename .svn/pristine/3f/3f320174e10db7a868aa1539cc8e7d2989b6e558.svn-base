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
	                       <a href="<%=request.getContextPath() %>/topic/viewTopicTeacher.do?gradeId=${gradeId }"  style="margin-right:30px;"><span class="glyphicon glyphicon-chevron-left"></span></a>     查看<span class="glyphicon glyphicon-eye-open">子题目</span> 
	         <a href="<%=request.getContextPath() %>/topic/exportTopic.do">
	        	 <span class="glyphicon glyphicon-export" style="color:green;float:right;margin-right:80px" data-toggle="tooltip" data-placement="bottom" title="导出"></span>
	   		 </a>
	    </div>
    </div> 
    <table class="table table-hover table-striped" >
    	<tr class="info">
    		<td >编号</td>
    		<td >学号</td>
    		<td >姓名</td>
    		<td>题目名称</td>
    		<td>发布时间</td>
    		<td>子题目</td>
    		<td>下载</td>
    		<td>操作</td>
    	</tr>
    	<c:forEach items="${students }" var="items">
	    		<tr>
	    			<td><c:out value="${items.id }"></c:out></td>
	    			<td><c:out value="${items.no }"></c:out></td>
	    			<td><c:out value="${items.name }"></c:out></td>
	    			<td><c:out value="${items.topics.topicsName }"></c:out></td>
	    			
	    			<td><c:out value="${items.topics.time }"></c:out></td>
	    			<td><c:out value="${items.subTopic.subName }"></c:out></td>
	    			<td>
	    				<c:if test="${items.subTopic == null}">
	    					未上传
	    				</c:if>
	    				<c:if test="${items.subTopic !=  null }">
	    					<a href="<%=request.getContextPath() %>/upload/<c:out value="${items.subTopic.taskBookName }"></c:out>"><span class="glyphicon glyphicon-download-alt" style="color:green;float:right" data-toggle="tooltip" data-placement="bottom" title="下载"></span></a>
	    				</c:if>
	    				
	    			</td>
	    			<td>
	    				<a href="javascript:void(0)"  data-toggle="modal" data-target="#myModal"  onclick="getId(${items.id },${items.topics.id })"> 上传子题目</a>
	    			</td>
	    		</tr>
		</c:forEach>
    </table>
    
    <!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">上传子题目</h4>
            </div>
            <div class="modal-body">
            	<form action="<%=request.getContextPath()%>/topic/saveSubTopic.do" method="post" enctype="multipart/form-data">
            		<input type="hidden" name="studentId" id="studentId"/>
            		<input type="hidden" name="topicId" id="topicId"/>
            		
	            	<div class="input-group">
			            <span class="input-group-addon" style="border-radius: 0;">子题目名称<span style="color:red;margin-top:13px;">*</span></span>
			            <input type="text" name="subName" id='subName' class="form-control" placeholder="子题目名称（必填）" required>
			        	<span style="color:#06c290;margin-left:20px" id="numContainer"></span>
			        </div>
			        <br>
			         <div class="input-group">
			            <span class="input-group-addon" style="border-radius: 0;">文件　 上传</span>
			            <input type="file" name="file" class="file" class="form-control" placeholder="文件" required>
			        </div>
			        <br>
			        <input type="submit" class="btn btn-primary" value="提交保存"/>
		        </form>
			</div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
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
	function noaudit(id){
		$.ajax({
			type:"post",
			url:"<%=request.getContextPath()%>/teacher/notAuditTopic.do",
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
	//删除
	function deleteItem(id){
		if(confirm("取认删除？删除该选项将会删除与该选项相关联数据！")){
			$.ajax({
				type:"post",
				url:"<%=request.getContextPath()%>/topic/deleteTopic.do",
				data:{"id":id,
				},
				dataType:"json",
				success:function(data){
					 alert("删除成功！");
					 window.setTimeout(reload,200);
					
				},
				error:function(msg){
					alert("删除失败！");
					console.log(msg)
				}
			})
			
		}
	}
	
	function reload(){
		location.reload()
	}
	
	function getId(studentId, topicId) {
		$("#studentId").val(studentId);
		$("#topicId").val(topicId);
		//alert(studentId+" "+topicId);
	}
</script>	
	
</body>
</html>