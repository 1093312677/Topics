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
	
	<div class="panel panel-default" style="margin:0">
	    <div class="panel-body">
	                    查看课程成绩${message }
           <a href="#" data-toggle="modal" data-target="#import1" id="import"> 
         		<span class="glyphicon glyphicon-import" style="color:green;float:right;margin-right:80px" data-toggle="tooltip" data-placement="bottom" title="导入"></span>
           </a>                 
	       <a href="#" data-toggle="modal" data-target="#myModal" id="add"> <span class="	glyphicon glyphicon-plus-sign" style="color:green;float:right;margin-right:20px" data-toggle="tooltip" data-placement="bottom" title="添加"></span></a>
    			
	    </div>
    </div>
    <table class="table table-hover" >
    	<tr class="info">
    		<td>编号</td>
    		<td>学号</td>
    		<td>姓名</td>
    		<td>课程名称</td>
    		<td>课程性质</td>
    		<td>学分</td>
    		<td>成绩</td>
    		
    	</tr>
    	<c:forEach items="${grades }" var="items">
    		<tr>
    			<td><c:out value="${items.id }"></c:out></td>
    			<td><c:out value="${items.no }"></c:out></td>
    			<td><c:out value="${items.name }"></c:out></td>
    			
    			<td><c:out value="${items.courseName }"></c:out></td>
    			<td><c:out value="${items.courseNature }"></c:out></td>
    			<td><c:out value="${items.credit }"></c:out></td>
    			<td><c:out value="${items.score }"></c:out></td>
    			
    		</tr>
		</c:forEach>
    </table>
    
    
      <!-- 分页开始 -->
    <div class="col-sm-2">
    	<ul class="pagination" style="margin-top:1px">
	    	<!-- 上一页 -->
	    	<c:if test="${pagination.page>1 }">
	    		<li><a href="<%=request.getContextPath() %>/course/viewGradeCourse.do?gradeId=${gradeId }&page=${pagination.page-2 }">上一页</a></li>
	    	</c:if>
			<c:if test="${pagination.page<=1 }">
				<li class="disabled"><a href="javascript:void(0)">上一页</a></li>
	    	</c:if>
			<!-- 下一页 -->
			<c:if test="${pagination.page<pagination.totlePage }">
	    		<li><a href="<%=request.getContextPath() %>/course/viewGradeCourse.do?gradeId=${gradeId }&page=${pagination.page }">下一页</a></li>
	    	</c:if>
			<c:if test="${pagination.page>=pagination.totlePage }">
				<li class="disabled"><a href="javascript:void(0)">下一页</a></li>
	    	</c:if>
		</ul>
    </div>
	<div class="col-sm-2">
	<form action="<%=request.getContextPath() %>/course/viewGradeCourse.do?gradeId=${gradeId }&" method="post">
		<div class="input-group" width="200px">
				<input type="number" class="form-control" name="page" value="1">
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
                <h4 class="modal-title" id="myModalLabel">添加课程</h4>
            </div>
            <div class="modal-body">
           		 <div class="input-group" style="margin-left:20%;margin-right:20%">
		            <span class="input-group-addon" style="border-radius: 0;">学号   </span>
		            <input type="number" name="courseWeek" id="courseWeek" class="form-control" placeholder="行课周数" required>
		        </div>
		        <br>
		        <div class="input-group" style="margin-left:20%;margin-right:20%">
		            <span class="input-group-addon" style="border-radius: 0;">学生名字   </span>
		            <input type="number" name="courseTerm" id="courseTerm" class="form-control" placeholder="行课学期" required>
		        </div>
            	<div class="input-group" style="margin-left:20%;margin-right:20%">
		            <span class="input-group-addon" style="border-radius: 0;">课程名称<span style="color:red;margin-top:13px;">*</span></span>
		            <input type="text" name="courseName" id="courseName" class="form-control" placeholder="课程名称" required>
		        </div>
            	<br>
            	<div class="input-group" style="margin-left:20%;margin-right:20%">
		            <span class="input-group-addon" style="border-radius: 0;">课程性质 <span style="color:red;margin-top:13px;">*</span></span>
		            <input type="text" name="courseClassify" id="courseClassify" class="form-control" placeholder="课程分类" required>
		        </div>
		        <br>
		        <div class="input-group" style="margin-left:20%;margin-right:20%">
		            <span class="input-group-addon" style="border-radius: 0;">课程学分<span style="color:red;margin-top:13px;">*</span></span>
		            <input type="number" name="courseCredit" id="courseCredit" class="form-control" placeholder="课程学分" required>
		        </div>
		        <br>
		         <div class="input-group" style="margin-left:20%;margin-right:20%">
		            <span class="input-group-addon" style="border-radius: 0;">成绩<span style="color:red;margin-top:13px;">*</span></span>
		            <input type="number" name="courseCredit" id="courseCredit" class="form-control" placeholder="课程学分" required>
		        </div>
            	<div class="panel panel-success" style="margin-top:10px;width:60%;margin-left:20%;margin-right:10%;" id="message" align="center">
	 			   <div class="panel-body">
	 			   		保存成功！
	 			   </div>
	 			</div>
	 			
	 			<div class="panel panel-danger" style="margin-top:40px;width:60%;margin-left:20%;margin-right:10%;" id="message2" align="center">
	 			   <div class="panel-body">
	 			   		保存失败！
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

<!-- 批量导入 -->
<div class="modal fade" id="import1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					批量学生课程成绩
				</h4>
			</div>
			<div class="modal-body">
				<form action="<%=request.getContextPath() %>/course/batchImportGrade.do" method="post" enctype="multipart/form-data">
					<div class="input-group">
			            <span class="input-group-addon" style="border-radius: 0;">学生课程成绩文件</span>
			             <input type="file" name="file" class="file" class="form-control" required accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet">
			         </div>
					<br>
					<input type="submit" value="导入" id="submit" class="btn btn-primary" style="width:150px"/>
				</form>
				<br>
				<br>
				<a href="<%=request.getContextPath() %>/upload/CourseGradeFile.xls">下载示例文件</a>
				<br>
				<img id="wait" src="<%=request.getContextPath() %>/images/wait.gif" style="margin-left:40%;" hidden/>
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
	$("#message").hide();
	$("#message2").hide();
	function reload(){
		location.reload()
	}
	$("#addConfirm").click(function(){
		
		var gradeId = ${gradeId };
		
		var courseName = $("#courseName").val();
		var courseClassify = $("#courseClassify").val();
		var courseCredit = $("#courseCredit").val();
		var courseWeek = $("#courseWeek").val();
		var courseTerm = $("#courseTerm").val();
		
		if( courseName != "" && courseClassify != "" && courseCredit != "" ){
			$.ajax({
				type:"post",
				url:"<%=request.getContextPath()%>/course/addCourse.do",
				data:{"courseName":courseName
					,"courseClassify":courseClassify
					,"courseCredit":courseCredit
					,"courseWeek":courseWeek
					,"courseTerm":courseTerm
					,"gradeId":gradeId
				},
				dataType:"json",
				success:function(data){
					if( data == 1 ) {
						$("#message").fadeIn();
					} else {
						$("#message2").fadeIn();
					}
					
					window.setTimeout(reload,1000);
				},
				error:function(msg){
					console.log(msg)
					$("#message2").fadeIn();
				}
			})	
			
		}
		
	})
	$("#submit").click(function(){
		$("#wait").show();
	})
</script>	
	
</body>
</html>