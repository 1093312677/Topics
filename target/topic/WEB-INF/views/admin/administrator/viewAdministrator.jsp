<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>view Administrator</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.min.css">

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/scrollerbar.css"/>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/common.css"/>


<script src="<%=request.getContextPath() %>/js/jquery-3.1.1.min.js"></script>
<script src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="panel panel-default" style="margin:0">
	    <div class="panel-body">
	                            查看<span class="	glyphicon glyphicon-eye-open">管理员</span> <a href="#" data-toggle="modal" data-target="#myModal" id="add"> <span class="	glyphicon glyphicon-plus-sign" style="color:green;float:right" data-toggle="tooltip" data-placement="bottom" title="添加"></span></a>   			
	    </div>
    </div>
    <div class="t1">
    	<table class="table table-hover table-striped">
    		<tr>
    			<td>账号</td>
				<td>重置密码</td>
    		</tr>
    		<c:forEach var="user"  items="${users}">
    			<tr>
    				<td><c:out value="${user.username}"/></td>
    				<td width="70px">
						<button type="button" class="btn btn-primary" id="resetPW" onclick="resetPW(${user.id},'${user.username}')">重置密码</button>
    				</td>
    			</tr>
    		</c:forEach>
    	</table>
    </div>
    <!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">添加专业</h4>
            </div>
            <div class="modal-body">
            	<input type="text" name="username" placeholder="账号（*必填）" id="username" class="form-control" onchange="inspection()"/><br>
            	<input type="password" name="pw1" placeholder="密码（*必填）" id="pw1" class="form-control"/><br>
            	<input type="password" name="pw2" placeholder="确定密码（*必填）" id="pw2" class="form-control"/><br>
            	<div class="panel panel-success" style="margin-top:30px;width:50%;margin-left:25%" id="message3">
	 			   <div class="panel-body" align="center">
	 			   		该账户已存在！
	 			   </div>
	 			</div>
            	<div class="panel panel-success" style="margin-top:30px;width:50%;margin-left:25%" id="message">
	 			   <div class="panel-body" align="center">
	 			   		保存成功！
	 			   </div>
	 			</div>
	 			<div class="panel panel-danger" style="margin-top:30px;width:50%;margin-left:25%" id="message2">
	 			   <div class="panel-body" align="center">
	 			   		密码不一致
	 			   </div>
	 			</div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="addUser">提交保存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</div>
<script type="text/javascript">
	function inspection(){
		var userName=$("#username").val();
		$.ajax({
			type:"post",
			url:"<%=request.getContextPath()%>/update/inspection.do",
			data:{"userName":userName},
			dataType:"text",
			success:function(data){	
				if(data==1){
					$("#addUser").attr("disabled", true);
					$("#username").focus().select();
					$("#message3").fadeIn();
				}
				else{
					$("#message3").hide();
					$("#addUser").attr("disabled", false);
				}
			},
			error:function(msg){
				console.log(msg)
			}
		})		
		//$(document).ready(function(){  $("input[name=collegeName]").focus();});
	}
	function resetPW(userId,name){
		if(window.confirm("账号："+name+"     确定重置密码？")){
			$.ajax({
				type:"post",
				url:"<%=request.getContextPath()%>/update/resetPw.do",
				data:{"type":"json","userId":userId},
				dataType:"json",
				success:function(data){
					if(data==1){
						alert("重置成功，默认密码：123456");
					}
				},
				error:function(msg){
					console.log(msg)
				}
			})	
		}
	}
	$("#add").click(function(){
		$("#message").hide();
		$("#message2").hide();
		$("#message3").hide();
	})
	$("#adUser").click(function(){
		if($("#username").val()!='' &&($("#pw1").val()==$("#pw2").val())){
			$.ajax({
				type:"post",
				url:"<%=request.getContextPath()%>/admin/addAdmin.do",
				data:{"username":$("#username").val(),"password":$("#pw1").val()},
				dataType:"text",
				success:function(data){
					if(data==1){
						$("#message").fadeIn();
						$("#message2").hide();
					}
					window.setTimeout(reload,2000);
				},
				error:function(msg){
					console.log(msg)
				}
			})
		}
		else{
			$("#message").hide();
			$("#message2").fadeIn();
		}
	})
	function reload(){
		location.reload()
	}
</script>
</body>
</html>