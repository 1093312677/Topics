<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>submit thesis</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.min.css">

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/scrollerbar.css"/>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/common.css"/>


<script src="<%=request.getContextPath() %>/js/jquery-3.1.1.min.js"></script>
<script src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script>
<!-- file -->
<script src="<%=request.getContextPath() %>/js/fileinput.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/js/fileinput_locale_zh.js" type="text/javascript"></script>
<link href="<%=request.getContextPath() %>/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />

<!-- select -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap-select.min.css">
<script src="<%=request.getContextPath() %>/js/bootstrap-select.min.js"></script>

<!-- alert -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/js/sweetalert/sweetalert.css"/>
<script src="<%=request.getContextPath() %>/js/sweetalert/sweetalert-dev.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-3">
			</div>
			<div class="col-md-6">
				<div class="panel panel-default" align='center'>
					<div class="panel-heading">
						<h3 class="panel-title">
							提示
						</h3>
					</div>
					<div class="panel-body">
						服从系内调配：
						<c:if test="${infor[0].swapInDepa == 0}">
							是
							<a href="javascript:void(0)" onclick="change(1)">修改</a>
						</c:if>
						<c:if test="${infor[0].swapInDepa == 1}">
							否
							<a href="javascript:void(0)" onclick="change(0)">修改</a>
						
						</c:if>
					</div>
				</div>
				
			</div>
			<div class="col-md-3">
			</div>
		</div>
	</div>
	<script>
	function change(state) {
		var url = "<%=request.getContextPath() %>/swap/changeSwapInDepart.do";
		swal({
			  title: "确认修改？",
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
				data:{"state":state},
				dataType:"json",
				success:function(data){
					if(data.result==1){
						swal("修改成功!", "", "success");
						window.setTimeout("location.reload()",700);
					}else{
						swal("修改失败！", "请重试！", "error");
					}
				},
				error:function(msg){
					swal("修改失败！", "请重试！", "error");
					window.setTimeout("location.reload()",700);
				}
			})	
		});
	}
	</script>
</body>
</html>