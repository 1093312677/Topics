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
</head>
<body>
<!-- 提示框开始 -->
	<div class="alert-container" id="alert">
		<div class="box-container">
			<div class="alert-title">
				<div class="close" id="alert-close">
					<span class="glyphicon glyphicon-remove" style="line-height:50px;"></span>
				</div>
			</div>
			
			<div class="alert-content">
				<span id="success" class="glyphicon glyphicon-ok-circle" style="color:#dff0d8;font-size:60px;margin-left:40%;margin-top:20px"></span>
				<span id="failed" class="glyphicon glyphicon-remove-circle" style="color:#f2dede;font-size:60px;margin-left:40%;margin-top:20px"></span>
				
				<!-- 文字内容开始 -->
				<div class="content" align=center id="content">
				
				</div>
				<!-- 文字内容结束 -->
				
				<!-- 确定区域1开始 -->
				<div class="alert-confirm" id="alert-confirm" >
					<button type="button" class="btn btn-primary btn-sm" id="confirm">
					  <span class="glyphicon glyphicon-ok"></span> 确定
					</button>
					
					<button type="button" class="btn btn-primary btn-sm" style="float:right" id="cancel">
					  <span class="	glyphicon glyphicon-remove"></span> 取消
					</button>
				</div>
				<!-- 确定区域1结束-->
				
				<!-- 确定区域2开始 -->
				<div class="alert-confirm" id="alert-confirm2" >
					<button type="button" class="btn btn-primary btn-sm" id="confirm2">
					  <span class="glyphicon glyphicon-ok"></span> 确定
					</button>
					
					<button type="button" class="btn btn-primary btn-sm" style="float:right" id="cancel2">
					  <span class="	glyphicon glyphicon-remove"></span> 取消
					</button>
				</div>
				<!-- 确定区域2结束-->
			</div>
		</div>
	</div>
<!-- 提示框结束 -->

	<div class="panel panel-default" style="margin:0">
	    <div class="panel-body">
	                            ${message }
	    </div>
    </div>
    <table class="table table-hover table-striped" align=left valign=middle>
    	<tr class="info">
    		<td>题目名称</td>
    		<td width=50px>可选学生</td>
    		<td width=50px>意向学生</td>
    		<td>指导老师</td>
    		<td>题目简介</td>
    		<td width=60px>任务书</td>
    		
    		<td>操作</td>
    		<!-- <td>说明</td> -->
    	</tr>
    	<c:forEach items="${topics }" var="items">
    		<c:if test="${items.enableSelect > items.selectedStudent}">
	    		<c:if test="${items.state == 1 }">
	    			<tr>
		    			<td><c:out value="${items.topicsName }"></c:out></td>
		    			<td><c:out value="${items.enableSelect }"></c:out></td>
		    			<td><c:out value="${items.intentionNumber }"></c:out></td>
		    			<td>
		    				<a href="<%=request.getContextPath() %>/teacher/viewTeacherOne.do?type=no&id=<c:out value="${items.teacher.id }"></c:out>"><c:out value="${items.teacher.name }"></c:out></a>
		    			</td>
		    			
		    			<td><c:out value="${items.introduce }"></c:out></td>
		    			<td>
		    				<c:if test="${items.taskBookName == null}">
		    					未上传
		    				</c:if>
		    				<c:if test="${items.taskBookName !=null }">
		    					<a href="<%=request.getContextPath() %>/upload/<c:out value="${items.taskBookName }"></c:out>"><span class="glyphicon glyphicon-download-alt" style="color:green;float:right" data-toggle="tooltip" data-placement="bottom" title="下载"></span></a>
		    				</c:if>
		    			</td>
		    			
		    			<td>
		    				<div class="dropdown">
							    <button class="btn btn-default dropdown-toggle" type="button" id="menu1" data-toggle="dropdown">选择为
							    <span class="caret"></span></button>
							    <ul class="dropdown-menu" role="menu" aria-labelledby="menu1">
							    	<li role="presentation"><a role="menuitem" tabindex="-1" href="javascript:void(0)" onclick="volunteer(1,<c:out value="${items.id }"></c:out>)">第一志愿</a></li>
							        <li role="presentation"><a role="menuitem" tabindex="-1" href="javascript:void(0)" onclick="volunteer(2,<c:out value="${items.id }"></c:out>)">第二志愿</a></li>
							  <!--  		<li role="presentation"><a role="menuitem" tabindex="-1" href="javascript:void(0)" onclick="volunteer(3,<c:out value="${items.id }"></c:out>)">第三志愿</a></li> -->
							    </ul>
							</div>
								    					
		    			</td>
		    			<!--  <td>第一志愿</td> -->
		    		</tr>
	    		</c:if>
	    	</c:if>
		</c:forEach>
    </table>
<script>
	$(function () { $("[data-toggle='tooltip']").tooltip(); });
	function hide(){
		$("#alert").hide();
		$("#success").hide();
		$("#failed").hide();
		$("#alert-confirm").hide();
		$("#alert-confirm2").hide();
	}
	//hide
	hide();
	var choice1;
	var id1;
	function volunteer(choice,id){
		choice1 = choice;
		id1 = id;
		hide();
		
		$.ajax({
			type:"post",
			url:"<%=request.getContextPath()%>/student/selectIntentionTopic.do",
			data:{"choice":choice,"id":id},
			dataType:"json",
			success:function(data){
				if(data==1){
					$("#content").html("保存成功！")
					$("#alert").fadeIn();
					$("#success").fadeIn();
				}else if(data==2){
					$("#content").html("是否更改当前志愿？<br><span style='font-size:12px;color:red;'>*注意将会覆盖之前的！<span>")
					$("#alert").fadeIn();
					$("#failed").fadeIn();
					$("#alert-confirm").fadeIn();
				}else if(data==3){
					$("#content").html("是否更改为当前选题为此志愿？<br><span style='font-size:12px;color:red;'>*注意将会覆盖之前的！<span>")
					$("#alert").fadeIn();
					$("#failed").fadeIn();
					$("#alert-confirm2").fadeIn();
				}else if(data==4){
					$("#content").html("你已经选择当前志愿！")
					$("#alert").fadeIn();
					$("#failed").fadeIn();
				}else{
					$("#content").html("保存失败！请重试！")
					$("#alert").fadeIn();
					$("#failed").fadeIn();
				}
			},
			error:function(msg){
				console.log(msg)
			}
		})	
		
	}
	//确定键1
	$("#confirm").click(function(){
		hide();
		$.ajax({
			type:"post",
			url:"<%=request.getContextPath()%>/student/updateIntentionTopic.do",
			data:{"choice":choice1,"id":id1,"type":"1"},
			dataType:"json",
			success:function(data){
				if(data==1){
					$("#content").html("更新成功！")
					$("#alert").fadeIn();
					$("#success").fadeIn();
				}else{
					$("#content").html("更新失败！请重试！")
					$("#alert").fadeIn();
					$("#failed").fadeIn();
				}
			},
			error:function(msg){
				console.log(msg)
			}
		})	
	})
	//确定键2
	$("#confirm2").click(function(){
		hide();
		$.ajax({
			type:"post",
			url:"<%=request.getContextPath()%>/student/updateIntentionTopic.do",
			data:{"choice":choice1,"id":id1,"type":"2"},
			dataType:"json",
			success:function(data){
				if(data==1){
					$("#content").html("更新成功！")
					$("#alert").fadeIn();
					$("#success").fadeIn();
				}else{
					$("#content").html("更新失败！请重试！")
					$("#alert").fadeIn();
					$("#failed").fadeIn();
				}
			},
			error:function(msg){
				console.log(msg)
			}
		})	
	})
	//取消键
	$("#cancel").click(function(){
		$("#alert").fadeOut();
	})
	//取消键
	$("#cancel2").click(function(){
		$("#alert").fadeOut();
	})
	//close
	$("#alert-close").click(function(){
		$("#alert").fadeOut();
	})
</script>	
	
</body>
</html>