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
	                            查看<span class="	glyphicon glyphicon-eye-open">班级</span> <a href="#" data-toggle="modal" data-target="#myModal" id="add"> <span class="	glyphicon glyphicon-plus-sign" style="color:green;float:right" data-toggle="tooltip" data-placement="bottom" title="添加"></span></a>
    			
	    </div>
    </div>
    <table class="table table-hover table-striped" >
    	<tr class="info">
    		<td width="70px">编号</td>
    		<td>班级</td>
    		<td>方向</td>
    		<td>专业</td>
    		<td>年级</td>
    		<td>系</td>
    		
    		<td>学院</td>
    		<td>操作</td>
    	</tr>
    	<c:forEach items="${clazzs }" var="items">
    		<tr>
    			<td><c:out value="${items.id }"></c:out></td>
    			<td><c:out value="${items.className }"></c:out></td>
    			<td><c:out value="${items.direction.directionName }"></c:out></td>
    			<td><c:out value="${items.direction.spceialty.specialtyName }"></c:out></td>
    			<td><c:out value="${items.direction.spceialty.grade.gradeName }"></c:out></td>
    			
    			<td><c:out value="${items.direction.spceialty.grade.department.departmentName }"></c:out></td>
    			<td><c:out value="${items.direction.spceialty.grade.department.college.collegeName }"></c:out></td>
    			<td width="70px">
    				<a href="javascript:void(0)" onclick="deleteItem(${items.id })"> <span class="glyphicon  glyphicon-trash" style="color:red" data-toggle="tooltip" data-placement="bottom" title="删除"></span></a>
    				<a href="javascript:void(0)" 	onclick="editInfo(${items.id})"><span class="glyphicon glyphicon-edit" style="color:green;padding-left:20px" data-toggle="tooltip" data-placement="bottom" title="更新"></span></a>
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
                <h4 class="modal-title" id="myModalLabel">添加专业</h4>
            </div>
            <div class="modal-body">
            	<div class="grade-content">
            		<select id='selectGrade' onchange='getSpecialty()' class='form-control'>
            			<option value="null">--请选择年级--</option>
            			<c:forEach items="${grades }" var="grades">
            				<option value="<c:out value="${grades.id }"></c:out>"><c:out value="${grades.gradeName }"></c:out></option>
            			</c:forEach>
            		</select>
            	</div>
            	<br>
            	<div class="specialty-content">
            	
            	</div>
            	<br>
            	<div class="direction-content">
            	
            	</div>
            	<br>
            	<div class="clazz-content">
            	
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
<!-- 模态框（Modal）[学生修改_郭亮] [开始]-->
<div class="modal fade" id="update" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">
					修改信息
				</h4>
			</div>
			<div class="modal-body">
				<input type="text" name="qq1" placeholder="学生QQ（选填）" id="clazzid" class="form-control"/>
				<label>班级：</label><input type="text" name="clazzname1" placeholder="班级（*必填）" id="clazzname1" class="form-control"/>
            	<br>
            	
            	<div class="panel panel-success" style="margin-top:30px;width:50%;margin-left:25%" id="message01">
	 			   <div class="panel-body">
	 			   		保存成功！
	 			   </div>
	 			</div>
	 			<div class="panel panel-danger" style="margin-top:30px;width:50%;margin-left:25%" id="message02">
	 			   <div class="panel-body">
	 			   		请输入班级！
	 			   </div>
	 			</div>
            	
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				 <button type="button" class="btn btn-primary" id="updateInfo" onclick="updateInfo()">提交保存</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
<!-- 模态框（Modal）[学生修改_郭亮] [结束]-->

<script>
	function editInfo(clazzId) { 
		$("#clazzid").hide();
		$("#message01").hide();
		$("#message02").hide();
		$("#clazzid").val(clazzId);
		$.ajax({
			type:"post",
			url:"<%=request.getContextPath()%>/clazz/findclazz1By.do",
			data:{"type":"json","clazzId":clazzId},
			dataType:"json",
			success:function(data){
				$("#clazzname1").val(data.clazz.className);
			},
			error:function(msg){
				console.log(msg)
			}
		})	
		$('#update').modal('show');
	}
	function updateInfo(){
		var clazzid=$("#clazzid").val();
		var	classname=$("#clazzname1").val();
		if(classname!=''){
			$.ajax({
				type:"post",
				url:"<%=request.getContextPath()%>/clazz/updateInfo.do",
				data:{ "id":clazzid,
					  "className":classname			  
				},
				dataType:"text",
				success:function(data){
					if(data==1){
						$("#message02").hide();
						$("#message01").fadeIn();
					}
					 window.setTimeout(reload,2000);
				},
				error:function(msg){
					console.log(msg)
				}
			})	
		}
		else{
			$("#message01").hide();
			$("#message02").fadeIn();
		}
		
	}



	$(function () { $("[data-toggle='tooltip']").tooltip(); });
	$("#add").click(function(){
		//初始时提示消息隐藏
		$("#message").hide();
		$("#message2").hide();
		//getColleges();//获取学院信息
		
	})
	
		//获取学院信息
	function getColleges(){
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
		var collegeId = $("#selectCollege").val();
		$.ajax({
			type:"post",
			url:"<%=request.getContextPath()%>/department/findDepartmentBy.do",
			data:{"type":"json","collegeId":collegeId},
			dataType:"json",
			success:function(data){
				console.log(data);
				var length = data.departments.length;
				var content = "<select name='id' class='form-control' id='selectDepartment' onchange='getGrades()'>";
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
	
	//获取年级信息
	function getGrades(){
		var departmentId = $("#selectDepartment").val();
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
	//获取专业信息
	function getSpecialty(){
		var gradeId = $("#selectGrade").val();
		$.ajax({
			type:"post",
			url:"<%=request.getContextPath()%>/specialty/findSpecialtyBy.do",
			data:{"type":"json","gradeId":gradeId},
			dataType:"json",
			success:function(data){
				var length = data.specialtys.length;
				var content = "<select name='id' class='form-control' id='selectSpecialty' onchange='getDirection()'>";
				content += "<option value='null'>--请选择专业--</option>";
				for(var i=0;i<length;i++){
					content += "<option value='"+data.specialtys[i].id+"'>"+data.specialtys[i].specialtyName+"</option>";
				}
				content += "</select>";
				$(".specialty-content").html(content);
				
			},
			error:function(msg){
				console.log(msg)
			}
		})	
	}
	//获取方向信息
	function getDirection(){
		var specialtyId = $("#selectSpecialty").val();
		$.ajax({
			type:"post",
			url:"<%=request.getContextPath()%>/direction/findDirectionBy.do",
			data:{"type":"json","specialtyId":specialtyId},
			dataType:"json",
			success:function(data){
				var length = data.directions.length;
				var content = "<select name='id' class='form-control' id='selectDirection' onchange='showInput()'>";
				content += "<option value='null'>--请选择方向--</option>";
				for(var i=0;i<length;i++){
					content += "<option value='"+data.directions[i].id+"'>"+data.directions[i].directionName+"</option>";
				}
				content += "</select>";
				$(".direction-content").html(content);
				
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
		var className=$("#clazz").val();
		var gradeId = $("#selectGrade").val();
		var specialtyId = $("#selectSpecialty").val();
		var directionId = $("#selectDirection").val();
		if(className!=""&&gradeId!="null"&&specialtyId!="null"&&directionId!="null"){
			$.ajax({
				type:"post",
				url:"<%=request.getContextPath()%>/clazz/addClazz.do",
				data:{"directionId":directionId,"className":className},
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
			location.href="<%=request.getContextPath()%>/clazz/deleteClazz.do?id="+id;
		}
	}
</script>	
	
</body>
</html>