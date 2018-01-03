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

<!-- alert -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/js/sweetalert/sweetalert.css"/>
<script src="<%=request.getContextPath() %>/js/sweetalert/sweetalert-dev.js"></script>
</head>
<body>
	<div class="panel panel-default" style="margin:0">
	    <div class="panel-body">
	                            查看<span class="glyphicon glyphicon-eye-open">题目</span> 
	    </div>
    </div> 
    <table class="table table-hover table-striped" >
    	<tr class="info">
    		<td >编号</td>
    		<td>题目名称</td>
    		<td>适用方向</td>
    		<td >可选学生</td>
    		<td >已选学生</td>
    		<td>指导老师</td>
    		<td>题目状态</td>
    		<td>发布时间</td>
    		<td>子题目</td>
    		<td>任务书</td>
    		<td>上传任务书</td>
    	</tr>
    	<c:forEach items="${topics }" var="items">
        	<c:if test="${items.state == 1}">
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
	    			<td><a href="<%=request.getContextPath() %>/topic/addSubTopic.do?id=${items.id}">添加子题目</a></td>
	    			<td>
	    				<c:if test="${items.taskBookName == ''}">
	    					未上传
	    				</c:if>
	    				<c:if test="${items.taskBookName !='' }">
	    					<a href="<%=request.getContextPath() %>/document/download.do?randName=${items.taskBookName }&documentName=${items.topicsName }_${items.teacher.name }_任务书_${items.taskBookName }" onclick="download(${items.taskBookName },${items.topicsName }, ${items.teacher.name }, ${items.taskBookName })"><span class="glyphicon glyphicon-download-alt" style="color:green;float:right" data-toggle="tooltip" data-placement="bottom" title="下载"></span></a>
							<%--<a href="#" onclick="down('${items.taskBookName }','${items.topicsName }', '${items.teacher.name }', '${items.taskBookName }')"><span class="glyphicon glyphicon-download-alt" style="color:green;float:right" data-toggle="tooltip" data-placement="bottom" title="下载"></span></a>--%>

						</c:if>
	    				
	    			</td>
	    			<td>
	    				<button type="button" class="btn btn-default btn-sm" style="color:green">
						    <span class="glyphicon glyphicon-arrow-up"></span>
							<a href="javascript:void(0)" style="color:green" id="upload" onclick="upload(${items.id })" data-toggle="modal" data-target="#myModal"> 上传或更新</a>
						</button>
	    			</td>
	    			<td>
	    				<c:if test="${state == 2 }">
	    					<button type="button" class="btn btn-default btn-sm" style="color:green">
							    <span class="glyphicon glyphicon-ok"></span>
								<a href="javascript:void(0)" style="color:green" id="audit" onclick="audit(<c:out value="${items.id }"></c:out>)"> 通过审核</a>
							</button>
							<button type="button" class="btn btn-default btn-sm" style="color:red">
							    <span class="glyphicon glyphicon-remove"></span>
								<a href="javascript:void(0)" style="color:red" id="notAudit" onclick="noaudit(<c:out value="${items.id }"></c:out>)"> 未通过审核</a>
							</button>
	    				</c:if>
	    				
	    			</td>
	    		</tr>
	    	 </c:if>
		</c:forEach>
    </table>
    <!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					上传或更新任务书
				</h4>
			</div>
			<div class="modal-body">
				<form id="form" action="" method="post" enctype="multipart/form-data">
					<input type="hidden" id="id" name="id"/>
					 <div class="input-group">
			            <span class="input-group-addon" style="border-radius: 0;">文件　 上传</span>
			            <input type="file" name="file" class="file" id="file" class="form-control" placeholder="文件" required>
			        </div>
			        <br>
			        
			        <br>
			        <input type="button" value="保存" id="save" class="btn btn-success" style="width:150px"/>
			        
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭
				</button>
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
	
	function upload(id){
		$("#id").val(id)
	}
	function reload(){
		location.reload()
	}

	function down(a,b,c,d) {
	    window.location.href="<%=request.getContextPath() %>/document/download.do?randName="+a+"&documentName="+encodeURI(a+b+c);
	}
	
	$("#save").click(function(){
		var file = $("#file").val();
		if(file == "") {
			swal("请选择文件！", "请重试！", "warning");
			return false;
		}
		swal({
			  title: "确认提交？",
			  text: "",
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonColor: "#DD6B55",
			  confirmButtonText: "　Yes　",
			  closeOnConfirm: false,
			  showLoaderOnConfirm: true, 
		},
		function(){
			var form = new FormData(document.getElementById("form"));
			$.ajax({
				type:"post",
				url:"<%=request.getContextPath()%>/topic/addUpdateAttach.do",
				data:form,
				dataType:"json",
				processData:false,
	            contentType:false,
				success:function(data){
					if(data.result==1){
						swal("提交成功!", "", "success");
						window.setTimeout("location.reload()",700);
					}else{
						swal("提交失败！", "请重试！", "error");
					}
				},
				error:function(msg){
					swal("提交失败！", "请重试！", "error");
					window.setTimeout("location.reload()",700);
				}
			})	
		});
	})
</script>	
	
</body>
</html>