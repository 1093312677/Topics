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
	<c:if test="${message =='success'}">
		<div class="alert alert-success alert-dismissable">
			<button type="button" class="close" data-dismiss="alert"
					aria-hidden="true">
				&times;
			</button>
			成功！
		</div>
	</c:if>
	<c:if test="${message =='failed'}">
		<div class="alert alert-danger alert-dismissable">
			<button type="button" class="close" data-dismiss="alert" aria-hidden="true">
				&times;
			</button>
			失败！
		</div>	
	</c:if>
	<div class="panel panel-default" style="margin:0">
	    <div class="panel-body">
	                            论文
	    </div>
    </div>
    <div class="container">
    	<div class="row">
    		<div class="col-md-3">
    		
    		</div>
    		<div class="col-md-6">
    		<div class="panel panel-default" style="margin-top:25px;">
			    <div class="panel-heading">
			        <h3 class="panel-title">  论文</h3>
			    </div>
			    <table class="table">
			    	<c:if test="${isNow == false || isSelect == false }">
			    		 <tr><td colspan="2" align="center">现在不是提交时间！或者未选择题目！</td></tr>
			    	</c:if>
			   <!-- 判断是否提交文档 -->
			    	<c:if test="${fileName != null }">
		    		 	<tr>
		    		 		<td><a href="<%=request.getContextPath() %>/upload/${fileName }">下载</a></td>
		    		 	</tr>
		    		 </c:if>
		    		 <c:if test="${fileName == null }">
		    		 	<tr>
		    		 		<td colspan="2" align="center">尚未提交文档！</td>
		    		 	</tr>
		    		 </c:if>
			    <!-- /判断是否提交文档 -->
			    	<c:if test="${isNow == true && isSelect == true}">
			    		 <tr>
			    		 	<td>
			    		 		<form id="form" action="" method="post" enctype="multipart/form-data">
									 <div class="input-group">
							            <span class="input-group-addon" style="border-radius: 0;"> 论文</span>
							           	<input type="file" name="file" id="file" required class="form-control file" placeholder="文件">
							          </div>
							          <br>
							         <input type="button" value="提　　交" id="save" class="btn btn-primary"/>
						        </form>
							</td>
						</tr>	
			    		
			    	</c:if>
			   	    
			    </table>
			</div>
    	</div>
     </div>		
   </div>
<script>
	$(function () { $("[data-toggle='tooltip']").tooltip(); });
	$("#save").click(function(){
		var file = $("#file").val();
		if(file == "" ) {
			swal("文件不能为空！", "请重试！", "warning");
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
				url:"<%=request.getContextPath() %>/attach/addSubmitThesis.do",
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