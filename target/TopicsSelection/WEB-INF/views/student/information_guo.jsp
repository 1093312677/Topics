<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="fm" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/common.css"/>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap-theme.min.css">

<script src="<%=request.getContextPath() %>/js/jquery-3.1.1.min.js"></script>
<script src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script>

<title>Insert title here</title>
</head>
<!--   private Integer id;
	private String no;//学号
	private String name;
	private String sex;
	private String age;
	private String qq;
	private String telephone;
	private String email;
-->
<body>
<!--  
	<form action="update.do" method="post">
		学号:<input type="text" value="${student.no}" name="no"/> <br>
		姓名:<input type="text" value="${student.name}" name="name"/><br>
		性别:<input type="text" value="${student.sex}" name="sex"/><br>
		QQ:<input type="text" value="${student.qq}" name="qq"/><br>
		电话：<input type="text" value="${student.telephone}" name="telephone"/><br>
		Email：<input type="text" value="${student.email}" name="email"/><br>
		<c:if test="${temp==1}">修改成功。</c:if><br>
		<input type="submit" value="update">
	</form>	
-->	
	
	<!-- zhao -->
<form action="update.do" method="post">
	<!-- 
	<div class="panel panel-primary">
		<div class="panel-heading">
			<center>
			<h3 class="panel-title">个人信息更新</h3>
			</center>
		</div>
	</div>
	 -->
	 <div class="panel panel-default" style="margin:0">
	   	 	<div class="panel-body">
	              	更新<span class="glyphicon glyphicon-leaf">信息</span>
	        </div>
	    </div>
	<c:if test="${temp==1}">修改成功。</c:if><br>
</form>

<center>
<!-- 修改表单 -->
 		<form style = "width:600px;">
		    <div class="input-group input-group-lg">
		        <span class="input-group-addon"><span class="glyphicon glyphicon-list-alt"></span></span>
		        <input type="text" value="${student.no}" name="no"  class="form-control" id="edit"  placeholder="学号">
		    </div>
		   
		      
		    <div class="input-group input-group-lg">
		        <span class="input-group-addon"><span class=" glyphicon glyphicon-user"></span></span>
		        <input type="text" value="${student.name}" name="name" class="form-control" id="edit"   placeholder="姓名">
		    </div>
		    
		    <div class="input-group input-group-lg">
		        <span class="input-group-addon"><span class="glyphicon glyphicon-road"></span></span>
		        <input type="text" value="${student.sex}" name="sex" class="form-control" id="edit"  placeholder="性别">
		    </div>
		    
		    <div class="input-group input-group-lg">
		        <span class="input-group-addon"><span class="glyphicon glyphicon-cloud"></span></span>
		        <input type="text" value="${student.qq}" name="qq" class="form-control" id="edit"  placeholder="QQ">
		    </div>
		    
		    <div class="input-group input-group-lg">
		        <span class="input-group-addon"><span class="glyphicon glyphicon-phone-alt"></span></span>
		        <input type="text" value="${student.telephone}" name="telephone" class="form-control" id="edit"  placeholder="电话">
		    </div>
		    
		    <div class="input-group input-group-lg">
		        <span class="input-group-addon"><span class="glyphicon glyphicon-envelope"></span></span>
		        <input type="text" value="${student.email}" name="email" class="form-control" id="edit"  placeholder="Email">
		    </div>
 		
 		<c:choose>
			<c:when test="${temp==0}">
				<c:out value="密码错误"/><br>
			</c:when>
			<c:when test="${temp==2}">
				<c:out value="密码不一致"/><br>
			</c:when>
		</c:choose>
		<br>
 		<div class="modal-footer">
                <button type="reset" class="btn btn-default" data-dismiss="modal">重置</button>
                <button type="submit" class="btn btn-primary" id="addConfirm">确认更新</button>
        </div>	
</form>
</center>	
</body>
</html>