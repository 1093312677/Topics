<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>view college</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.min.css">

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/scrollerbar.css"/>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/common.css"/>


<script src="<%=request.getContextPath() %>/js/jquery-3.1.1.min.js"></script>
<script src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
	    <div class="row">
	    	<div class="col-sm-12">
	    	<form action="<%=request.getContextPath() %>/college/updateCollege.do" method="post" >
	    		<c:forEach items="${college }" var="items">
		    		
		    			<input type="hidden" name="id" value="<c:out value="${items.id }"></c:out>"/>
		    			<br>
		    			<input type="text" class="form-control" name="collegeName" value="<c:out value="${items.collegeName }"></c:out>" style="width:50%"/>
		    			<br>
		    			<input type="submit" value="更新" class="btn btn-default"/>
		    		
		    	</c:forEach>
		    	</form>
	    	</div>
	    </div>
    </div>
</body>
</html>