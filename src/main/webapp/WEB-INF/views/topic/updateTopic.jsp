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
<!-- file -->
<script src="<%=request.getContextPath() %>/js/fileinput.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/js/fileinput_locale_zh.js" type="text/javascript"></script>
<link href="<%=request.getContextPath() %>/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />

<!-- select -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap-select.min.css">
<script src="<%=request.getContextPath() %>/js/bootstrap-select.min.js"></script>
</head>
<body>	
	<div class="container">
		<div class="row">
			<div class="col-md-3">
			</div>
			<div class="col-md-6">
				<!--  enctype="multipart/form-data" -->
				<form action="<%=request.getContextPath()%>/topic/updateInfo.do" method="post" enctype="multipart/form-data" onsubmit="return check()">
					<h5>题目修改</h5>
					<input type="text" name="id" required value="${topic.id}" id="topicsId">
					<div class="input-group">
			            <span class="input-group-addon" style="border-radius: 0;">题目　名称<span style="color:red;margin-top:13px;">*</span></span>
			            <input type="text" name="topicsName" class="form-control" placeholder="题目名称（必填）" required value="${topic.topicsName}">
			        </div>
			        <br>
			        <div class="input-group">
			            <span class="input-group-addon" style="border-radius: 0;">指导　教师<span style="color:red;margin-top:13px;">*</span></span>
			            <input type="text" name="teacherName" class="form-control" placeholder="题目名称（必填）" required value="${topic.teacher.name}">
			        </div>
			        <br>				   
			         <div class="input-group">
			            <span class="input-group-addon" style="border-radius: 0;">适用于方向<span style="color:red;margin-top:13px;">*</span></span>
			            <div id="direction-content" >
			            	<select class='form-control' id='directionIds'  name='directionIds' onchange='' multiple='multiple' required>
	                        	<c:forEach var="direction1" items="${directions1}">	                   
	                        		<option value="${direction1.id}" selected="selected"> ${direction1.directionName}</option>	                        				
	                        	</c:forEach> 
	                         	<c:forEach var="direction" items="${directions}">	                   
	                        		<option value="${direction.id}" > ${direction.directionName}</option>	                        				
	                        	</c:forEach>        
	                        </select>
			            </div>
			        </div>
			        <br>
			         <div class="input-group">
			            <span class="input-group-addon" style="border-radius: 0;">可选学生数<span style="color:red;margin-top:13px;">*</span></span>
			            <input type="number" name="enableSelect" id='enableSelect' class="form-control" placeholder="可选学生数（必填）" required value="${topic.enableSelect}">
			        	<span style="color:#06c290;margin-left:20px" id="numContainer"></span>
			        </div>
			        <br>
			        
			        <div class="input-group">
			            <span class="input-group-addon" style="border-radius: 0;">题目  　简介</span>
			            <textarea class="form-control"   rows="5" name="introduce">${topic.introduce}</textarea>
			        </div>
			        <br>
			        <input type="submit" value="保存" class="btn btn-success" style="width:150px"/>
			        
			        
				</form>
			</div>
			<div class="col-md-3">
			</div>
		</div>
	</div>
	
<script>		
	function check() {
		inputNum = $("#enableSelect").val();
		if(inputNum > maxNum && inputNum==0) {
			$("#numContainer").html('超出最大可填人数'+maxNum);
			return false;
		}
		if($("#grade").val()=="null") {
			alert("请选择年级！");
			return false;
		}
			
		return true;
	}
	window.onload = function(){ 
		$("#topicsId").hide();
	}
</script>
</body>
</html>