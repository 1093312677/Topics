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
	                            查看老师 <a href="#" data-toggle="modal" data-target="#myModal" id="add"> <span class="	glyphicon glyphicon-plus-sign" style="color:green;float:right" data-toggle="tooltip" data-placement="bottom" title="添加"></span></a>
    			
	    </div>
    </div>
    <table class="table table-hover" >
    	<tr class="info">
    		<td width="100px">编号</td>
    		<td>姓名</td>
    		<td width="50px">性别</td>
    		<td>职称</td>
    		<td>学院</td>
    		<td>系</td>
    		<td>备注</td>
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
    			<td><c:out value="${items.remark }"></c:out></td>
    			<td width="70px">
    				<a href="javascript:void(0)" onclick="deleteItem(${items.id })"> <span class="glyphicon  glyphicon-trash" style="color:red" data-toggle="tooltip" data-placement="bottom" title="删除"></span></a>
    				<a href="<%=request.getContextPath()%>/clazz/findClazz.do?id=${items.id }" > <span class="glyphicon glyphicon-edit" style="color:green;padding-left:20px" data-toggle="tooltip" data-placement="bottom" title="更新"></span></a>
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
                <h4 class="modal-title" id="myModalLabel">添加老师</h4>
            </div>
            <div class="modal-body">
            	<input type="text" name="name" placeholder="老师姓名（*必填）" id="name" class="form-control"/>
            	<br>
            	<input type="text" name="no" placeholder="老师工号（*必填）" id="no" class="form-control"/>
            	<br>
            	<input type="text" name="position" placeholder="老师职称（*必填）" id="position" class="form-control"/>
            	<br>
            	<select id="sex" name="sex" class="form-control">
            		<option value="男">男</option>
            		<option value="女">女</option>
            	</select>
            	<br>
            	<input type="text" name="telephone" placeholder="老师电话（选填）" id="telephone" class="form-control"/>
            	<br>
            	<input type="text" name="qq" placeholder="老师QQ（选填）" id="qq" class="form-control"/>
            	<br>
            	<input type="text" name="remark" placeholder="备注（选填）" id="remark" class="form-control"/>
            	<br>
            	<div class="college">
            	
            	</div>
            	<br>
            	<div class="department-content">
            	
            	</div>
            	<div class="panel panel-success" style="margin-top:30px;width:50%;margin-left:25%" id="message">
	 			   <div class="panel-body">
	 			   		保存成功！
	 			   </div>
	 			</div>
	 			<div class="panel panel-danger" style="margin-top:30px;width:50%;margin-left:25%" id="message2">
	 			   <div class="panel-body">
	 			   		请选择正确选项！
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
		getColleges();//获取学院信息
		
	})
	
	//获取学院信息
	function getColleges(){
		$("#message2").hide();
		$.ajax({
			type:"post",
			url:"<%=request.getContextPath()%>/college/viewCollege.do",
			data:{"type":"json"},
			dataType:"json",
			success:function(data){
				var length = data.colleges.length;
				var content = "<select name='id' class='form-control' id='selectCollege' onchange='getDepartment()'>";
				content += "<option value='null'>--请选择学院--</option>";
				for(var i=0;i<length;i++){
					content += "<option value='"+data.colleges[i].id+"'>"+data.colleges[i].collegeName+"</option>";
				}
				content += "</select>";
				$(".college").html(content);
				
			},
			error:function(msg){
				console.log(msg)
			}
		})	
	}
	//获取系信息
	function getDepartment(){
		$("#message2").hide();
		var collegeId = $("#selectCollege").val();
		$.ajax({
			type:"post",
			url:"<%=request.getContextPath()%>/department/findDepartmentBy.do",
			data:{"type":"json","collegeId":collegeId},
			dataType:"json",
			success:function(data){
				console.log(data);
				var length = data.departments.length;
				var content = "<select name='id' class='form-control' id='selectDepartment' onchange=''>";
				content += "<option value='null'>--请选择系--</option>";
				for(var i=0;i<length;i++){
					content += "<option value='"+data.departments[i].id+"'>"+data.departments[i].departmentName+"</option>";
				}
				content += "</select>";
				$(".department-content").html(content);
				
			},
			error:function(msg){
				console.log(msg)
			}
		})	
	}
	
	
	$("#addConfirm").click(function(){
		var collegeId = $("#selectCollege").val();
		var departmentId = $("#selectDepartment").val();
		//获取学生信息
		var name = $("#name").val();
		var no = $("#no").val();
		var sex = $("#sex").val();
		var qq = $("#qq").val();
		var telephone = $("#telephone").val();
		var remark = $("#remark").val();
		var position = $("#position").val();
		
		if(collegeId!="null"&&departmentId!="null"){
			$.ajax({
				type:"post",
				url:"<%=request.getContextPath()%>/teacher/addDean.do",
				data:{"departmentId":departmentId,
					  "name":name,
					  "no":no,	 
					  "sex":sex,	 
					  "qq":qq,	 
					  "telephone":telephone,
					  "position":position,
					  "remark":remark
				},
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
	
	window.setTimeout(hidden,3000); 
	function hidden() 
	{ 
		$(".alert").hide();
	} 
	
	//删除
	function deleteItem(id){
		if(confirm("取认删除？删除该选项将会删除与该选项相关联数据！")){
			location.href="<%=request.getContextPath()%>/clazz/deleteClazz.do?id="+id;
		}
	}
</script>	
	
</body>
</html>