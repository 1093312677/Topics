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
	<div class="panel panel-default" style="margin:0">
	    <div class="panel-body">
	                            设置老师题目可选人数上限
	    </div>
    </div>
    <table class="table table-hover table-striped" >
    	<tr class="info">
    		<td width="100px">编号</td>
    		<td>姓名</td>
    		<td width="50px">性别</td>
    		<td>职称</td>
    		<td>学院</td>
    		<td>系</td>
    		<td>限制人数</td>
    		<td>操作</td>
    	</tr>
    	<c:forEach items="${teachers }" var="items">
    		<tr>
    			<td><c:out value="${items.no }"></c:out></td>
    			<td><c:out value="${items.name }"></c:out></td>
    			<td><c:out value="${items.sex }"></c:out></td>
    			<td><c:out value="${items.position }"></c:out></td>
    			<td><c:out value="${items.department.college.collegeName }"></c:out></td>
    			<td><c:out value="${items.department.departmentName }"></c:out></td>
    			
    			
    			<td>
    				<c:forEach items="${items.limitNumbers }" var="limit">
    					<c:out value="${limit.number }"></c:out>
    				</c:forEach>
    			</td>
    			<td >
    				<c:choose>
    					 <c:when test="${empty items.limitNumbers}">   
						    <button type="button" id="set" class="btn btn-primary" onclick="get(${items.id })" data-toggle="modal" data-target="#myModal">设置人数</button>
						  </c:when> 
						 <c:otherwise>  
							<c:forEach items="${items.limitNumbers }" var="limit">
    							<button type="button" id="change" class="btn btn-primary" onclick="getLimitId(${limit.id })" data-toggle="modal" data-target="#myModal">修改人数</button>
    						</c:forEach>	
						 </c:otherwise> 
    				</c:choose>
    			</td>
    		</tr>
		</c:forEach>
    </table>
    
    <!-- 分页开始 -->
    <div class="col-sm-2">
    	<ul class="pagination" style="margin-top:1px">
	    	<!-- 上一页 -->
	    	<c:if test="${pagination.page>1 }">
	    		<li><a href="<%=request.getContextPath() %>/limit/viewSetTeacherTopicNum.do?page=${pagination.page-2 }">上一页</a></li>
	    	</c:if>
			<c:if test="${pagination.page<=1 }">
				<li class="disabled"><a href="javascript:void(0)">上一页</a></li>
	    	</c:if>
			<!-- 下一页 -->
			<c:if test="${pagination.page<pagination.totlePage }">
	    		<li><a href="<%=request.getContextPath() %>/limit/viewSetTeacherTopicNum.do?page=${pagination.page }">下一页</a></li>
	    	</c:if>
			<c:if test="${pagination.page>=pagination.totlePage }">
				<li class="disabled"><a href="javascript:void(0)">下一页</a></li>
	    	</c:if>
		</ul>
    </div>
	<div class="col-sm-2">
	<form action="<%=request.getContextPath() %>/limit/viewSetTeacherTopicNum.do" method="post" onsubmit="return checkPage()">
		<div class="input-group" width="200px">
				<input type="number" class="form-control" name="page" value="1" id="page">
				<input type="hidden" class="form-control" name="pageType" value="1">
				<span class="input-group-btn">
				<button class="btn btn-default" type="submit">
					跳转
				</button>
			</span>
		</div><!-- /input-group -->
	</form>
	</div>
	<div class="col-sm-8" style="margin-top:10px">
		<p class="text-left">共 ${pagination.totleSize }条记录，每页显示${pagination.eachPage }条，当前( ${pagination.page }/${pagination.totlePage } )页</p>
    </div>
   <!-- /分页开始 -->   
    
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">设置老师可选人数</h4>
            </div>
            <div class="modal-body">
            	<input type="number" name="name" placeholder="人数" id="number" class="form-control"/>
            	<br>
            	<input type="hidden" id="type" class="form-control"/>
            	<br>
            	<div class="panel panel-success" style="margin-top:30px;width:50%;margin-left:25%" id="message">
	 			   <div class="panel-body">
	 			   		保存成功！
	 			   </div>
	 			</div>
	 			<div class="panel panel-danger" style="margin-top:30px;width:50%;margin-left:25%" id="message2">
	 			   <div class="panel-body">
	 			   		请正确填写！
	 			   </div>
	 			</div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="addConfirm">提交保存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<!-- 模态框（Modal） -->
<div class="modal fade" id="import1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					批量导入教师
				</h4>
			</div>
			<div class="modal-body">
				<form action="" method="post" enctype="multipart/form-data">
					<div class="input-group">
			            <span class="input-group-addon" style="border-radius: 0;">教师名单文件</span>
			             <input type="file" name="file" class="file" class="form-control" accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet">
			         </div>
					<br>
					<input type="submit" value="导入" class="btn btn-primary" style="width:150px"/>
				</form>
				<br>
				<br>
				<a href="<%=request.getContextPath() %>/upload/">下载示例文件</a>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
<script>
	$(function () { $("[data-toggle='tooltip']").tooltip(); });
	
	//检查页数
	function checkPage() {
		var page = $("#page").val();
		if(page <= 0) {
			$("#page").val("1");
		} else if(page > ${pagination.totlePage }) {
			$("#page").val(${pagination.totlePage });
		}
		
		return true;
	}
	
	$("#set").click(function(){
		//初始时提示消息隐藏
		$("#message").hide();
		$("#message2").hide();
		$("#type").val("set");
	})
	
	$("#change").click(function(){
		//初始时提示消息隐藏
		$("#message").hide();
		$("#message2").hide();
		
		$("#type").val("change");
	})
	
	var teacherId;
	var limitId;
	function get(id) {
		//初始时提示消息隐藏
		$("#message").hide();
		$("#message2").hide();
		teacherId = id;
	}
	function getLimitId(id) {
		//初始时提示消息隐藏
		$("#message").hide();
		$("#message2").hide();
		limitId = id;
	}
	
	$("#addConfirm").click(function(){
		var type = $("#type").val();
		//获取学生信息
		var number = $("#number").val();
		if(type == 'set') {
			limitId = 0;
		} else if(type= "change") {
			teacherId = 0;
		}
		
		$.ajax({
			type:"post",
			url:"<%=request.getContextPath()%>/limit/setNumber.do",
			data:{
				"number":number,
				"type":type,
				"teacherId":teacherId,
				"limitId":limitId,
				},
			dataType:"text",
			success:function(data){
				if(data==1){
					$("#message").fadeIn();
				} else {
					$("#message2").fadeIn();
				}
				 window.setTimeout(reload,500);
				
			},
			error:function(msg){
				console.log(msg)
			}
		})
	})
	
	function reload(){
		location.reload();
	}
	
	window.setTimeout(hidden,3000); 
	function hidden(){ 
		$(".alert").hide();
	} 
	
</script>	
	
</body>
</html>