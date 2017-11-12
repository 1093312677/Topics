<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="shortcut icon" href="<%=request.getContextPath() %>/images/image.ico"/>
<title>错误</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.min.css">

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/scrollerbar.css"/>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/common.css"/>
<link href="<%=request.getContextPath() %>/css/animate.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/css/style.css?v=4.1.0" rel="stylesheet">

<script src="<%=request.getContextPath() %>/js/jquery-3.1.1.min.js"></script>
<script src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script>

</head>
<body class="gray-bg">


    <div class="middle-box text-center animated fadeInDown">
        <h1>500</h1>
        <h3 class="font-bold">服务器内部错误</h3>

        <div class="error-desc">
            <br/>
            <span class="label label-warning" style="font-size:15px">您可以重试或者重新登录试试！ </span>
            <br>
            <br>
            <br>
            <a href="javascript:void(0)" onclick="goback()" class="btn btn-primary m-t">返回</a>  
       </div>
    </div>
<script type="text/javascript">
	function goback() {
		history.go(-1);
	}
</script>
</body>

</html>
