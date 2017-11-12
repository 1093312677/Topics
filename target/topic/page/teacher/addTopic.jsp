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
	
	<div class="container">
		<div class="row">
			<div class="col-md-3">
			</div>
			<div class="col-md-6">
				<form action="" method="post" enctype="multipart/form-data">
					<br>
					<div class="input-group">
			            <span class="input-group-addon" style="border-radius: 0;">题目　名称<span style="color:red;margin-top:13px;">*</span></span>
			            <input type="text" name="topicsName" class="form-control" placeholder="题目名称（必填）" required>
			        </div>
			        <br>
			        
			        <div class="input-group">
			            <span class="input-group-addon" style="border-radius: 0;">可选学生数<span style="color:red;margin-top:13px;">*</span></span>
			            <input type="number" name="enableSelect" class="form-control" placeholder="可选学生数（必填）" required>
			        </div>
			        <br>
			        
			         <div class="input-group">
			            <span class="input-group-addon" style="border-radius: 0;">适用于方向<span style="color:red;margin-top:13px;">*</span></span>
			            <select id="usertype" name="usertype" class="selectpicker show-tick form-control" multiple data-live-search="false">
                                        <option value="0">苹果</option>
                                        <option value="1">菠萝</option>
                                        <option value="2">香蕉</option>
                                        <option value="3">火龙果</option>
                                        <option value="4">梨子</option>
                                        <option value="5">草莓</option>
                                        <option value="6">哈密瓜</option>
                                        <option value="7">椰子</option>
                                        <option value="8">猕猴桃</option>
                                        <option value="9">桃子</option>
                                </select>
			        </div>
			        <br>
			        
			         <div class="input-group">
			            <span class="input-group-addon" style="border-radius: 0;">文件　 上传</span>
			            <input type="file" name="file" class="file" class="form-control" placeholder="文件">
			        </div>
			        <br>
			        
			        <div class="input-group">
			            <span class="input-group-addon" style="border-radius: 0;">题目  　简介</span></span>
			            <textarea class="form-control"   rows="5" name="introduce">
			            
			            </textarea>
			        </div>
			        <br>
			        <input type="submit" value="保存" class="btn btn-success" style="width:150px"/>
			        
			        
				</form>
			</div>
			<div class="col-md-3">
			</div>
		</div>
	</div>
</body>
</html>