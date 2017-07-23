<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="fm" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/common.css"/>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap-theme.min.css">

<script src="<%=request.getContextPath() %>/js/jquery-3.1.1.min.js"></script>
<script src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script>

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

<!-- zhao -->
<body>
	<form action="update.do" method="post" style = "margin-left:200px;margin-top:50px;width:500px;height:700px;">
		 <div class="input-group input-group-lg">
		        <span class="input-group-addon"><span class="glyphicon glyphicon-list-alt"></span></span>
		        <input type="text" value="${teacher.no}" name="no" class="form-control" id="edit"  disabled="true" placeholder="工号">
		 </div>
		 
		 <div class="input-group input-group-lg">
		        <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
		        <input type="text" value="${teacher.name}" name="name" class="form-control" id="edit" disabled="true" placeholder="姓名">
		 </div>
		 
		  <div class="input-group input-group-lg">
		        <span class="input-group-addon"><span class="glyphicon glyphicon-align-right"></span></span>
		        <input type="text" value="${teacher.position}" name="position" class="form-control" id="edit" disabled="true" placeholder="职称">
		 </div>
		 
		  <div class="input-group input-group-lg">
		        <span class="input-group-addon"><span class="glyphicon glyphicon-road"></span></span>
		        <input type="text" value="${teacher.sex}" name="sex" class="form-control" id="edit" disabled="true" placeholder="性别">
		 </div>
		 
		  <div class="input-group input-group-lg">
		        <span class="input-group-addon"><span class="glyphicon glyphicon-cloud"></span></span>
		        <input type="text" value="${teacher.qq}" name="qq" class="form-control" id="edit" placeholder="QQ">
		 </div>
		 
		  <div class="input-group input-group-lg">
		        <span class="input-group-addon"><span class="glyphicon glyphicon-phone-alt"></span></span>
		        <input type="text" value="${teacher.telephone}" name="telephone" class="form-control" id="edit" placeholder="电话">
		 </div>
		 
		  <div class="input-group input-group-lg">
		        <span class="input-group-addon"><span class="glyphicon glyphicon-envelope"></span></span>
		        <input type="text" value="${teacher.email}" name="email" class="form-control" id="edit" placeholder="Email">
		 </div>
		 
		  <div class="input-group input-group-lg">
		        <span class="input-group-addon"><span class="glyphicon glyphicon-pencil"></span></span>
		        <input type="text" value="${teacher.remark}" name="remark" class="form-control" id="edit" placeholder="备注">
		 </div>
		 
		  <div class="input-group input-group-lg">
		        <span class="input-group-addon"><span class="glyphicon glyphicon-glass"></span></span>
		        <input type="text" value="${teacher.department.departmentName}" class="form-control" id="edit" disabled="true" placeholder="专业">
		 </div>
		 
		 <div class="modal-footer">
                <button type="reset" class="btn btn-default" data-dismiss="modal">重置</button>
                <button type="submit" class="btn btn-primary" id="addConfirm">更新</button>
        </div>	
		<!--  
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
		-->
	</form>	
	<!-- /zhao -->
</body>
</html>