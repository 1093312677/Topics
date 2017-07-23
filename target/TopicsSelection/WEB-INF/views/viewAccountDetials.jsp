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
	<form action="<%=request.getContextPath() %>/account/updateAccount" method="post" enctype="multipart/form-data">
		<input type="text" name="username" value="<c:out value="${member.username }"></c:out>"/><br/>
		<input type="text" name="photoUrl" value="<c:out value="${member.photoUrl }"></c:out>"/><br/>
		<input type="text" name="name" value="<c:out value="${member.name }"></c:out>"/><br/>
		<input type="text" name="id" value="<c:out value="${member.id }"></c:out>"/><br/>
		<input type="text" name="password" value="<c:out value="${member.password }"></c:out>"/><br/>
		<input type="text" name="occupation" value="<c:out value="${member.occupation }"></c:out>"/><br/>
		<input type="text" name="profession" value="<c:out value="${member.profession }"></c:out>"/><br/>
		
		<input type="text" name="college" value="<c:out value="${member.college }"></c:out>"/><br/>
		<input type="text" name="phoneNumber" value="<c:out value="${member.phoneNumber }"></c:out>"/><br/>
		<input type="text" name="classNumber" value="<c:out value="${member.classNumber }"></c:out>"/><br/>
		<input type="text" name="isManager" value="<c:out value="${member.isManager }"></c:out>"/><br/>
		
		<input type="submit" value="update"/>
	</form>
</body>
</html>