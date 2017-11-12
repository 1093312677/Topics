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
	                            查看<span class="	glyphicon glyphicon-eye-open">文档</span> 
	    </div>
    </div>
    <table class="table table-hover table-striped" >
    	<c:forEach items="${documents }" var="items">
    		<tr>
    			<td><a href="<%=request.getContextPath()%>/document/download.do?randName=${items.randName}&documentName=${items.documentName }">${items.documentName }</a></td>
    			<c:if test="${privilege == 2 }">
    				<td><a href="javascript:void(0)" onclick="deleteDocument(${items.id })">删除</a></td>
    			</c:if>
    			
    		</tr>
		</c:forEach>
    </table>
    
    
    <script>
    	function deleteDocument(id) {
    		swal({
	  			  title: "取认删除？",
	  			  text: "不可撤回！",
	  			  type: "warning",
	  			  showCancelButton: true,
	  			  confirmButtonColor: "#DD6B55",
	  			  confirmButtonText: "　Yes　",
	  			  closeOnConfirm: false
	  		},
	  		function(){
	  			$.ajax({
	  				type:"post",
	  				url:"<%=request.getContextPath()%>/document/deleteDocument.do",
	  				data:{"id":id,
	  				},
	  				dataType:"json",
	  				success:function(data){
	  					if(data == 1){
	  						 swal("删除成功!", "", "success");
		  					 window.setTimeout(reload,700);
	  					} else {
	  						swal("删除失败！", "请重试！", "error");
	  					}
	  					
	  					
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