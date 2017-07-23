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

	<div class="panel panel-default" style="margin:0">
	    <div class="panel-body" >
	                            查看意向题目 
	    </div>
    </div>
    <table class="table table-hover" align=left valign=middle>
    	<tr class="info">
    		<td>题目名称</td>
    		<td>志愿</td>	
    		<td width=50px>可选学生</td>
    		<td width=50px>已选学生</td>
    		<td>指导老师</td>
    		<td>题目简介</td>
    		<td>是否服从教师调配</td>
    		<td>留言内容</td>
    		<td>留言</td>
    		<td width=60px>任务书</td>
    		<!-- <td>说明</td> -->
    	</tr>
    <!-- 第一轮选题开始 -->
    	<tr>
	    	<td colspan="10" align=center height=40px style="background-color:#f6f6f6">第一轮选题</td>
	    </tr>
    	<c:forEach items="${intentionTopics }" var="items">
	    	<c:if test="${items.batch == 1}">
	    		<tr>
	    			<td><c:out value="${items.topic.topicsName }"></c:out></td>
	    			<td>
	    				<c:if test="${items.choice == 1}">
	    					第一志愿
	    				</c:if>
	    				<c:if test="${items.choice == 2}">
	    					第二志愿
	    				</c:if>
	    				<c:if test="${items.choice == 3}">
	    					第三志愿
	    				</c:if>
	    			</td>
	    			<td><c:out value="${items.topic.enableSelect }"></c:out></td>
	    			<td><c:out value="${items.topic.selectedStudent }"></c:out></td>
	    			<td>
	    				<a href="<%=request.getContextPath() %>/teacher/viewTeacherOne.do?type=no&id=<c:out value="${items.topic.teacher.id }"></c:out>"><c:out value="${items.topic.teacher.name }"></c:out></a>
	    			</td>
	    			
	    			<td><c:out value="${items.topic.introduce }"></c:out></td>
	    			<td>
	    				<c:if test="${items.swap == 0 }">
	    					否
	    					<a href="<%=request.getContextPath() %>/swap/changeSwapInTeacher.do?state=1&intenId= <c:out value="${items.id }"></c:out>">修改</a>
	    				</c:if>
	    				<c:if test="${items.swap == 1 }">
	    					是
	    					<a href="<%=request.getContextPath() %>/swap/changeSwapInTeacher.do?state=0&intenId=<c:out value="${items.id }"></c:out>">修改</a>
	    				</c:if>
	    			
	    			</td>
	    			<td>
	    				<c:out value="${items.message }"></c:out>
	    			</td>
	    			<td>
	    				    <!-- 按钮触发模态框 -->
						<button class="btn btn-primary" onclick="getId(<c:out value="${items.id }"></c:out>)" data-toggle="modal" data-target="#myModal">
							留言
						</button>
	    			</td>
	    			
	    			<td>
	    				<c:if test="${items.topic.taskBookName==''}">
	    					未上传
	    				</c:if>
	    				<c:if test="${items.topic.taskBookName!='' }">
	    					<a href="<%=request.getContextPath() %>/upload/<c:out value="${items.topic.taskBookName }"></c:out>"><span class="glyphicon glyphicon-download-alt" style="color:green;float:right" data-toggle="tooltip" data-placement="bottom" title="下载"></span></a>
	    				</c:if>
	    			</td>
	    		</tr>
	    	</c:if>
	    </c:forEach>
	  <!-- 第一轮选题结束-->  
	  
	  
	  <!-- 第二轮选题开始 -->	
	    	<tr>
	    		<td colspan="10" align=center height=40px style="background-color:#f6f6f6">第二轮选题</td>
	    	</tr>
	    	<c:forEach items="${intentionTopics }" var="items">
		    	<c:if test="${items.batch == 2}">
		    		<tr>
		    			<td><c:out value="${items.topic.topicsName }"></c:out></td>
		    			
		    			<td>
		    				<c:if test="${items.choice == 1}">
		    					第一志愿
		    				</c:if>
		    				<c:if test="${items.choice == 2}">
		    					第二志愿
		    				</c:if>
		    				<c:if test="${items.choice == 3}">
	    						第三志愿
	    					</c:if>
		    			</td>
		    			
		    			<td><c:out value="${items.topic.enableSelect }"></c:out></td>
		    			<td><c:out value="${items.topic.selectedStudent }"></c:out></td>
		    			<td>
		    				<a href="<%=request.getContextPath() %>/teacher/viewTeacherOne.do?type=no&id=<c:out value="${items.topic.teacher.id }"></c:out>"><c:out value="${items.topic.teacher.name }"></c:out></a>
		    			</td>
		    			
		    			<td><c:out value="${items.topic.introduce }"></c:out></td>
		    			<td>
		    				<c:if test="${items.swap == 0 }">
		    					否
		    					<a href="<%=request.getContextPath() %>/swap/changeSwapInTeacher.do?state=1&intenId= <c:out value="${items.id }"></c:out>">修改</a>
		    				</c:if>
		    				<c:if test="${items.swap == 1 }">
		    					是
		    					<a href="<%=request.getContextPath() %>/swap/changeSwapInTeacher.do?state=0&intenId=<c:out value="${items.id }"></c:out>">修改</a>
		    				</c:if>
		    			
		    			</td>
		    			<td>
		    				<c:out value="${items.message }"></c:out>
		    			</td>
		    			<td>
		    				    <!-- 按钮触发模态框 -->
							<button class="btn btn-primary" onclick="getId(<c:out value="${items.id }"></c:out>)" data-toggle="modal" data-target="#myModal">
								留言
							</button>
		    			</td>
		    			<td>
		    				<c:if test="${items.topic.taskBookName == null}">
		    					未上传
		    				</c:if>
		    				<c:if test="${items.topic.taskBookName !=null }">
		    					<a href="<%=request.getContextPath() %>/upload/<c:out value="${items.topic.taskBookName }"></c:out>"><span class="glyphicon glyphicon-download-alt" style="color:green;float:right" data-toggle="tooltip" data-placement="bottom" title="下载"></span></a>
		    				</c:if>
		    			</td>
		    		</tr>
		    	</c:if>
		    </c:forEach>
	 <!-- 第二轮选题结束 -->   	
	 
	 <!-- 第三轮选题开始 
	 		<tr>
		    	<td colspan="7" align=center height=40px style="background-color:#f6f6f6">第三轮选题</td>
		    </tr>
	 		<c:forEach items="${intentionTopics }" var="items">
		    	<c:if test="${items.batch == 3}">
		    		<tr>
		    			<td><c:out value="${items.topic.topicsName }"></c:out></td>
		    			<td>
		    				<c:if test="${items.choice == 1}">
		    					第一志愿
		    				</c:if>
		    				<c:if test="${items.choice == 2}">
		    					第二志愿
		    				</c:if>
		    				<c:if test="${items.choice == 3}">
		    					第三志愿
		    				</c:if>
		    			</td>
		    			<td><c:out value="${items.topic.enableSelect }"></c:out></td>
		    			<td><c:out value="${items.topic.selectedStudent }"></c:out></td>
		    			<td>
		    				<a href="<%=request.getContextPath() %>/teacher/viewTeacherOne.do?type=no&id=<c:out value="${items.topic.teacher.id }"></c:out>"><c:out value="${items.topic.teacher.name }"></c:out></a>
		    			</td>
		    			
		    			<td><c:out value="${items.topic.introduce }"></c:out></td>
		    			<td>
		    				<c:if test="${items.topic.taskBookName == null}">
		    					未上传
		    				</c:if>
		    				<c:if test="${items.topic.taskBookName !=null }">
		    					<a href="<%=request.getContextPath() %>/upload/<c:out value="${items.topic.taskBookName }"></c:out>"><span class="glyphicon glyphicon-download-alt" style="color:green;float:right" data-toggle="tooltip" data-placement="bottom" title="下载"></span></a>
		    				</c:if>
		    			</td>
		    		</tr>
		    	</c:if>
		    </c:forEach>-->
	   <!-- 第三轮选题结束 -->
    </table>
    
    

<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					留言
				</h4>
			</div>
			<div class="modal-body">
				<form action="<%=request.getContextPath() %>/swap/leavMessage.do" method="post">
					<input type="hidden" value="" name="id" id="id"/>
					<textarea name="message" id="message" class="form-control" autofocus rows="10">
					
					</textarea>
			</div>
			<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<input type="submit" class="btn btn-primary" id="submit" value="保存">
				</form>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
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
	
	//设置留言获取id
	function getId(id){
		$("#id").val(id);
	}
	
</script>	
	
</body>
</html>