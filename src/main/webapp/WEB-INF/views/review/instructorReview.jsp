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
	                           评阅学生
	    </div>
    </div>
    <div>
	    <table class="table table-hover table-striped" id="table">
	    	<tr class="info">
	    		<td width="100px">学号</td>
	    		<td>姓名</td>
	    		<td>开题报告</td>
	    		<td>中期报告</td>
	    		<td>毕业论文</td>
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
	    					<a href="<%=request.getContextPath() %>/document/download.do?randName=${items.form.openingReport}&documentName=${items.no }_${items.name }_开题报告_${items.form.openingReport}">下载</a>
	    				</c:if>
	    			</td>
	    			<td>
	    				<c:if test="${items.form.interimReport != null }">
	    					<a href="<%=request.getContextPath() %>/document/download.do?randName=${items.form.interimReport}&documentName=${items.no }_${items.name }_中期报告_${items.form.interimReport}">下载</a>
	    				</c:if>
	    			</td>
	    			
	    			<td>
	    				<c:if test="${items.form.fileName != null }">
	    					<a href="<%=request.getContextPath() %>/document/download.do?randName=${items.form.fileName}&documentName=${items.no }_${items.name }_毕业论文_${items.form.fileName}">下载</a>
	    				</c:if>
	    			</td>
	    			
	    			<td><c:out value="${items.score.mediumScore }"></c:out></td>
	    			<td><c:out value="${items.clazz.className }"></c:out></td>
	    			<td><c:out value="${items.clazz.direction.directionName }"></c:out></td>
	    			<td>
	    				<c:if test="${isNow == true }">
	    					<a href="#" data-toggle="modal" data-target="#import1" id="import">
	    						<c:choose>
	    							<c:when test="${items.form.interimEvalForm == null }">
	    								<button class="btn btn-primary" onclick="getId(<c:out value="${items.id }"></c:out>)">评阅</button>
	    							</c:when>
	    							<c:otherwise>
	    								<button class="btn btn-primary" onclick="getId(<c:out value="${items.id }"></c:out>)">修改</button>
	    							</c:otherwise>
	    						</c:choose>		    					
		    				</a>
	    				</c:if>
	    				<c:if test="${isNow == false }">
	    					不是评阅时间！
	    				</c:if>
	    			</td>
	    			
	    			<td>
	    				<c:if test="${items.form.interimEvalForm != null }">
	    					<a href="<%=request.getContextPath() %>/document/download.do?randName=${items.form.interimEvalForm}&documentName=${items.no }_${items.name }_指导教师评阅表_${items.form.interimEvalForm}">下载</a>
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
				<form id="form" action="<%=request.getContextPath() %>/attach/submitInstructorReview.do" method="post" enctype="multipart/form-data">
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
					<input type="button" value="保存" id="save" class="btn btn-primary" style="width:150px"/>
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

$("#save").click(function(){
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
			url:"<%=request.getContextPath() %>/attach/submitInstructorReview.do",
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