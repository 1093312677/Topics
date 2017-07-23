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
 <c:forEach items="${files }" var="files">
 	<c:out value="${files.photoUrl }"></c:out><br/>
 	<c:out value="${files.name }"></c:out><br/>
 	<img src="getFile?id=<c:out value="${files.name }"></c:out>" height="300px"/><br/>
 	<a href="deleteFile?id=<c:out value="${files.name }"></c:out>">delete</a>
 	<br/>
 </c:forEach>
</body>
</html>