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
	
	<div class="panel panel-default" style="margin:0">
	    <div class="panel-body">
	                            修改分组信息 
	    </div>
    </div>
    <div class="container">
    	<div class="row">
    		<div class="col-md-3">
    		
    		</div>
    		<div class="col-md-6">
    		<div class="panel panel-default" style="margin-top:25px;">
			    <div class="panel-heading">
			        <h3 class="panel-title">修改组
				       
			        </h3>
			    </div>
				<br>
				<form action="<%=request.getContextPath() %>/group/updateGroup.do" method="post">
				 	<c:forEach items="${groups }" var="items">
				 		<input type="text" name="groupName" class="form-control" value="${items.groupName }" placeholder="新组名"/>
						<input type="hidden" name="id" class="form-control" value="${items.id }" placeholder="新组名"/>
					</c:forEach>
					
					<br> 	
					<input type="submit" value="修改" class="btn btn-primary" />
				</form>
				
			</div>
    	</div>
    </div>		
 </div> 
<script>
	$(function () { $("[data-toggle='tooltip']").tooltip(); });
</script>	
	
</body>
</html>