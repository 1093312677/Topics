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
	                            查看<span class="	glyphicon glyphicon-eye-open">分组</span> 
	          <a href="#" data-toggle="modal" data-target="#myModal" id="add"> <span class="glyphicon glyphicon-plus-sign" style="color:green;float:right;margin-right:40px" data-toggle="tooltip" data-placement="bottom" title="添加"></span></a>
    		  <a href="<%=request.getContextPath() %>/group/exportTeacherGroup.do"> <span style="color:green;float:right;margin-right:40px" data-toggle="tooltip" data-placement="bottom" title="导出教师分组">导出教师分组</span></a>
    		  <a href="<%=request.getContextPath() %>/group/exportStudentGroup.do" > <span style="color:green;float:right;margin-right:40px" data-toggle="tooltip" data-placement="bottom" title="导出学生分组">导出学生分组</span></a>
	    </div>
    </div>
    <table class="table table-hover table-striped" >
    	<tr class="">
    		<td >编号</td>
    		<td>分组名称</td>
    		<td>答辩组编号</td>
    		<td>答辩组名称</td>
    		<td>取消答辩组</td>
    		<td>设置答辩组</td>
    		<td>分配教师</td>
    		<td>调整教师</td>
    		<td>删除分组</td>
    		<td>修改分组</td>
    	</tr>
    	<c:forEach items="${groups }" var="items">
    		<tr>
    			<td><c:out value="${items.id }"></c:out></td>
    			<td><c:out value="${items.groupName }"></c:out></td>
    			<td><c:out value="${items.ansGroup.id }"></c:out></td>
    			<td><c:out value="${items.ansGroup.groupName }"></c:out></td>
    			<td><a href="<%=request.getContextPath()%>/group/cancelGroup.do?id=${items.id }">取消答辩组</a></td>
    			<td><a href="<%=request.getContextPath()%>/group/setViewGroup.do?id=${items.id }">设置答辩组</a></td>
    			<td><a href="<%=request.getContextPath()%>/group/setViewTeacherGroup.do?id=${items.id }">分配教师</a></td>
    			<td><a href="<%=request.getContextPath()%>/group/changeTeacherGroup.do?id=${items.id }">调整教师</a></td>
    			
    			<td>
    				<a href="javascript:void(0)" onclick="deleteItem(${items.id })" > <span class="glyphicon  glyphicon-trash" style="color:" data-toggle="tooltip" data-placement="bottom" title="删除"></span></a>
    			</td>
    			<td>	
    				<a href="<%=request.getContextPath()%>/group/viewOneGroup.do?id=${items.id }" > <span class="glyphicon glyphicon-edit" style="color:#06c290;padding-left:20px" data-toggle="tooltip" data-placement="bottom" title="更新"></span></a>
    			</td>
    		</tr>
		</c:forEach>
    </table>
    
    
    
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">添加分组</h4>
            </div>
            <div class="modal-body">
            	<input type="text" id="groupName" placeholder="请输入组名" class="form-control"/>
            	<br>
            	<div class="grade-content">
            		<select id='selectGrade' onchange='getSpecialty()' class='form-control'>
            			<option value="null">--请选择年级--</option>
            			<c:forEach items="${grades }" var="grades">
            				<option value="<c:out value="${grades.id }"></c:out>"><c:out value="${grades.gradeName }"></c:out></option>
            			</c:forEach>
            		</select>
            	</div>
            	<div class="panel panel-success" style="margin-top:30px;width:50%;margin-left:25%" id="message">
	 			   <div class="panel-body" align="center">
	 			   		保存成功！
	 			   </div>
	 			</div>
	 			<div class="panel panel-danger" style="margin-top:30px;width:50%;margin-left:25%" id="message2">
	 			   <div class="panel-body" align="center">
	 			   		请填写内容或选择正确选项！
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
<script>
	$(function () { $("[data-toggle='tooltip']").tooltip(); });
	$("#add").click(function(){
		//初始时提示消息隐藏
		$("#message").hide();
		$("#message2").hide();
		getGrades();//获取信息
		
	})
	//获取年级信息
	function getGrades(){
		var departmentId = ${departmentId}
		$.ajax({
			type:"post",
			url:"<%=request.getContextPath()%>/grade/findGradeBy.do",
			data:{"type":"json","departmentId":departmentId},
			dataType:"json",
			success:function(data){
				var length = data.grades.length;
				var content = "<select name='id' class='form-control' id='selectGrade' onchange='getSpecialty()'>";
				content += "<option value='null'>--请选择年级--</option>";
				for(var i=0;i<length;i++){
					content += "<option value='"+data.grades[i].id+"'>"+data.grades[i].gradeName+"</option>";
				}
				content += "</select>";
				$(".grade-content").html(content);
				
			},
			error:function(msg){
				console.log(msg)
			}
		})	
	}
	//选取学院后的事件处理
	function showInput(){
		//选取学院后，错误消息隐藏
		$("#message2").hide();
		$(".clazz-content").html("<input type='text' class='form-control' id='clazz' hidden='hidden' placeholder='班级' required/>");
		//alert(document.getElementById("selectCollege").value)
	}
	
	$("#addConfirm").click(function(){
		var groupName=$("#groupName").val();
		var gradeId = $("#selectGrade").val();
		if(groupName!=""&&gradeId!="null"){
			$.ajax({
				type:"post",
				url:"<%=request.getContextPath()%>/group/addGroup.do",
				data:{"gradeId":gradeId,"groupName":groupName},
				dataType:"text",
				success:function(data){
					if(data==1){
						$("#message").fadeIn();
					}
					 window.setTimeout(reload,2000);
					
				},
				error:function(msg){
					console.log(msg)
				}
			})
		}else{
			$("#message2").fadeIn();
		}
		
		
		
	})
	function reload(){
		location.reload()
	}
	
	window.setTimeout(hidden,1000); 
	function hidden() 
	{ 
		$(".alert").hide();
	} 
	
	//删除
	function deleteItem(id){
		if(confirm("取认删除？删除该选项将会删除与该选项相关联数据！")){
			location.href="<%=request.getContextPath()%>/group/deleteGroup.do?id="+id;
		}
	}
</script>	
	
</body>
</html>