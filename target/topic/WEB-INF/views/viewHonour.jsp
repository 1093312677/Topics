<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
 <c:forEach items="${honours }" var="items">
 	<c:out value="${items.id }"></c:out><br/>
 	<c:out value="${items.project }"></c:out><br/>
 	<c:out value="${items.name }"></c:out><br/>
 	<c:forEach items="${items.images }" var="images">
 		<img src="<%=request.getContextPath() %>/account/getFile?id=<c:out value="${images.fileId }"></c:out>" height="300px"/><br/>
 	</c:forEach>
 	<a href="<%=request.getContextPath() %>/honour/deleteHonour?id=<c:out value="${items.id }"></c:out>">delete</a>
 	<br/>
 </c:forEach>
</body>
</html>