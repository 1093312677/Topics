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
	                            选择年级
	    </div>
    </div>
    <table class="table table-hover table-striped" >
    	<c:forEach items="${grades }" var="items">
    		<tr class="" align=center>
    			<td><a href="<%=request.getContextPath() %>/setting/viewSetting.do?gradeId=<c:out value="${items.id }"></c:out>"><c:out value="${items.gradeName }"></c:out></a></td>
    		</tr>
		</c:forEach>
		
		
    </table>
    
    
    
<script>
	$(function () { $("[data-toggle='tooltip']").tooltip(); });
	$("#add").click(function(){
		//初始时提示消息隐藏
		$("#message").hide();
		$("#message2").hide();
		getColleges();//获取学院信息
		
	})
	
	
	window.setTimeout(hidden,3000); 
	function hidden() 
	{ 
		$(".alert").hide();
	} 
	
</script>	
	
</body>
</html>