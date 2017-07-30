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

<!-- alert -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/js/sweetalert/sweetalert.css"/>
<script src="<%=request.getContextPath() %>/js/sweetalert/sweetalert-dev.js"></script>
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
	    			<td>
	    				<span  data-toggle="tooltip" data-placement="bottom" title="简介：${items.topic.introduce }">
	    					<a href="javascript:void(0)">${items.topic.topicsName }</a>
	   					</span>
	    			</td>
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
	    			
	    			<td>
	    				<c:if test="${items.swap == 0 }">
	    					否
	    					<a href="javascript:void(0)" onclick="change(1,${items.id })">修改</a>
	    				</c:if>
	    				<c:if test="${items.swap == 1 }">
	    					是
	    					<a href="javascript:void(0)" onclick="change(1,${items.id })">修改</a>
	    				</c:if>
	    			
	    			</td>
	    			<td>
	    				<span  data-toggle="tooltip" data-placement="bottom" title="留言：${items.message }">
	    					<a href="javascript:void(0)">留言</a>
	   					</span>
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
		    			<td>
		    				<span  data-toggle="tooltip" data-placement="bottom" title="简介：${items.topic.introduce }">
		    					<a href="javascript:void(0)">${items.topic.topicsName }</a>
		   					</span>
		    			</td>
		    			
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
		    			
		    			<td>
		    				<c:if test="${items.swap == 0 }">
		    					否
		    					<a href="javascript:void(0)" onclick="change(1,${items.id })">修改</a>
		    				</c:if>
		    				<c:if test="${items.swap == 1 }">
		    					是
		    					<a href="javascript:void(0)" onclick="change(0,${items.id })">修改</a>
		    				</c:if>
		    			
		    			</td>
		    			<td>
		    				<span  data-toggle="tooltip" data-placement="bottom" title="留言：${items.message }">
		    					<a href="javascript:void(0)">留言</a>
		   					</span>
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
				<textarea name="message" id="message" class="form-control" autofocus rows="10">
				
				</textarea>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭
				</button>
				<input type="button" class="btn btn-primary" id="submit"  onclick="saveMessage()" value="保存">
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
<script>
	$(function () { $("[data-toggle='tooltip']").tooltip(); });
	var url = "<%=request.getContextPath() %>/swap/changeSwapInTeacher.do";
	
	//修改是否服从调配
	function change(state, intenId) {
		swal({
			  title: "是否更改？",
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonColor: "#DD6B55",
			  confirmButtonText: "　Yes　",
			  closeOnConfirm: false
		},
		function(){
			$.ajax({
				type:"post",
				url:url,
				data:{"state":state,"intenId":intenId},
				dataType:"json",
				success:function(data){
					if(data==1){
						swal("更改成功!", "", "success");
						window.setTimeout(reload,800);
					}else{
						swal("更改失败！", "请重试！", "error");
					}
				},
				error:function(msg){
					console.log(msg)
				}
			})	
		});
	}
	
	
	
	//设置留言获取id
	var messageId;
	function getId(id){
		messageId = id;
	}
	
	var messageUrl = "<%=request.getContextPath() %>/swap/leavMessage.do";
	//设置留言
	function saveMessage(){
		
		var message = $("#message").val();
		$.ajax({
			type:"post",
			url:messageUrl,
			data:{"id":messageId,"message":message},
			dataType:"json",
			success:function(data){
				if(data==1){
					swal("留言成功!", "", "success");
					window.setTimeout(reload,800);
				}else{
					swal("留言失败！", "请重试！", "error");
				}
			},
			error:function(msg){
				console.log(msg)
			}
		})	
	}
	function reload(){
		location.reload()
	}
</script>	
	
</body>
</html>