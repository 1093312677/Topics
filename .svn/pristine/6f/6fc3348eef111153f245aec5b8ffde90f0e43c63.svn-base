<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="fm" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<!--  private long id;
	private String no;//工号
	private String name;
	private String sex;
	private String qq;
	private String position;//职称
	private String telephone;
	private String email;
-->
<body>
	<form action="update.do" method="post">
		工号:<input type="text" value="${teacher.no}" name="no"/> <br>
		姓名:<input type="text" value="${teacher.name}" name="name"/><br>
		职称::<input type="text" value="${teacher.position}" name="position"/> <br>
		性别:<input type="text" value="${teacher.sex}" name="sex"/><br>
		QQ:<input type="text" value="${teacher.qq}" name="qq"/><br>
		电话：<input type="text" value="${teacher.telephone}" name="telephone"/><br>
		Email：<input type="text" value="${teacher.email}" name="email"/><br>
		备注：<input type="text" value="${teacher.remark}" name="remark"/><br>
		专业：<input type="text" value="${teacher.department.departmentName}" /><br>
		<c:if test="${temp==1}">修改成功。</c:if><br>
		<input type="submit" value="update">
	</form>		
</body>
</html>