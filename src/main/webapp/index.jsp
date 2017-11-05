<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="fullscreen-bg">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
	<link rel="shortcut icon" href="<%=request.getContextPath() %>/images/image.ico"/>
	<title>毕业设计选题系统登录</title>
	
	
	<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/bootstrap.min.css">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<!-- VENDOR CSS -->
	<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/vendor/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/vendor/linearicons/style.css">
	<!-- MAIN CSS -->
	<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/main.css">
	<!-- FOR DEMO PURPOSES ONLY. You should remove this in your project -->
	<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/demo.css">
	<!-- GOOGLE FONTS -->
	<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700" rel="stylesheet">
	<!-- ICONS -->
	<link rel="apple-touch-icon" sizes="76x76" href="<%=request.getContextPath() %>/assets/img/apple-icon.png">
	
</head>
<body>
	<!-- WRAPPER -->
	<div id="wrapper">
		<div class="vertical-align-wrap">
			<div class="vertical-align-middle">
				<div class="auth-box ">
					<div class="left">
						<div class="content">
							<div class="header">
								<div class="logo text-center">
								
								</div>
								<p class="lead"><h3>毕业设计管理系统</h3></p>
							</div>
							<form class="form-auth-small" action="<%=request.getContextPath() %>/account/login.do" method="post"">
								<div class="form-group">
									<label for="signin-email1" class="control-label sr-only">Email</label>
									<input type="type" class="form-control" id="signin-email1" value="" name="username" placeholder="帐号">
								</div>
								<div class="form-group">
									<label for="signin-password" class="control-label sr-only">Password</label>
									<input type="password" class="form-control" id="signin-password" value="" name="password" placeholder="密码">
								</div>
								<!-- <div class="form-group clearfix">
									<label class="fancy-checkbox element-left">
										<input type="checkbox">
										<span>Remember me</span>
									</label>
								</div> 
								-->
								
								<div class="form-group clearfix">
									<label class="fancy-checkbox element-left">
										<span style="color:#f40;font-size:11px">
											<c:if test="${loginMessage == 'error'}">
												用户名或者密码错误！
											</c:if>
											
										</span>
									</label>
								</div> 
								
								<button type="submit" class="btn btn-primary btn-lg btn-block">登录</button>
								<!-- <div class="bottom">
									<span class="helper-text"><i class="fa fa-lock"></i> <a href="#">Forgot password?</a></span>
								</div> -->
							</form>
						</div>
					</div>
					<div class="right">
						<div class="overlay"></div>
						<div class="content text">
							<!-- <h1 class="heading">Free Bootstrap dashboard template</h1>
							<p>by The Develovers</p> -->
						</div>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
	</div>
	<!-- END WRAPPER -->
	<script>
		winWidth  = document.documentElement.clientWidth;
		winHeight = document.documentElement.clientHeight;
		$("#bg").css({"height":parseInt(winHeight)+30});
		
		window.setTimeout(hide,2000);
		function hide(){
			$(".alert").hide();
		}	
	</script>
		<!-- zhao -->

</body>
</html>