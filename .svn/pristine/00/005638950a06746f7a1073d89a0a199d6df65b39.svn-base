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
    		<td>重置密码</td>
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
    				<a href="javascript:void(0)" onclick="editInfo(${items.id})">  <span class="glyphicon glyphicon-edit" style="color:green;padding-left:20px" data-toggle="tooltip" data-placement="bottom" title="更新"></span></a>
    			</td>
    			<td width="70px">
    				 <button type="button" class="btn btn-primary" id="resetPW" onclick="resetPW(${items.user.id},'${items.name}')">重置密码</button>
    			</td>
    			
    		</tr>
		</c:forEach>
    </table>
    
     <!-- 分页开始 -->
	    <div class="col-sm-2">
	    	<ul class="pagination" style="margin-top:1px">
		    	<!-- 上一页 -->
		    	<c:if test="${pagination.page>1 }">
		    		<li><a href="<%=request.getContextPath() %>/teacher/viewDean.do?page=${pagination.page-2 }">上一页</a></li>
		    	</c:if>
				<c:if test="${pagination.page<=1 }">
					<li class="disabled"><a href="javascript:void(0)">上一页</a></li>
		    	</c:if>
				<!-- 下一页 -->
				<c:if test="${pagination.page<pagination.totlePage }">
		    		<li><a href="<%=request.getContextPath() %>/teacher/viewDean.do?page=${pagination.page }">下一页</a></li>
		    	</c:if>
				<c:if test="${pagination.page>=pagination.totlePage }">
					<li class="disabled"><a href="javascript:void(0)">下一页</a></li>
		    	</c:if>
			</ul>
	    </div>
		<div class="col-sm-2">
		<form action="<%=request.getContextPath() %>/teacher/viewDean.do" method="post" onsubmit="return checkPage()">
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
                <h4 class="modal-title" id="myModalLabel">添加老师</h4>
            </div>
            <div class="modal-body">
            	<input type="text" name="name" placeholder="老师姓名（*必填）" id="name" class="form-control"/>
            	<br>
            	<input type="text" name="no" placeholder="老师工号（*必填）" id="no" class="form-control" onchange="inspection()"/>
            	<br>
            	<div class="panel panel-success" style="margin-top:30px;width:50%;margin-left:25%" id="message3">
	 			   <div class="panel-body">
	 			   		该工号已存在！
	 			   </div>
	 			</div>
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
<!-- 模态框（Modal）[学生修改_郭亮] -->
<div class="modal fade" id="update" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">
					修改信息
				</h4>
			</div>
			<div class="modal-body">
				<input type="text" name="qq1" placeholder="学生QQ（选填）" id="teaid" class="form-control"/>
				<label>姓名：</label><input type="text" name="name1" placeholder="教师姓名（*必填）" id="name1" class="form-control"/>
            	<br>
            	<label>工号：</label><input type="text" name="no1" placeholder="教师学号（*必填）" id="no1" class="form-control" onchange="inspection1()"/>
            	<br>
            	<div class="panel panel-success" style="margin-top:30px;width:50%;margin-left:25%" id="message03">
	 			   <div class="panel-body">
	 			   		该工号已存在！
	 			   </div>
	 			</div>
            	<label>性别：</label><select id="sex1" name="sex1" class="form-control">
            		<option value="男" id="man" selected>男</option>
            		<option value="女" id="woman" selected="selected">女</option>
            	</select>
            	<br>
            	<label>职称：</label><input type="text" name="position1" placeholder="教师职称（*必填）" id="position1" class="form-control"/>
            	<br>
            	<label>电话号码：</label><input type="text" name="telephone1" placeholder="教师电话（选填）" id="telephone1" class="form-control"/>
            	<br>
            	<label>QQ：</label><input type="text" name="qq1" placeholder="教师QQ（选填）" id="qq1" class="form-control"/>
            	<br>
            	<label>邮箱：</label><input type="text" name="email1" placeholder="教师邮箱（选填）" id="email1" class="form-control"/>
            	<br>
            	<label>备注：</label><input type="text" name="remark1" placeholder="教师备注（选填）" id="remark1" class="form-control"/>
            	<br>
            	<div class="college-content1">
            	
            	</div>
            	<div class="department-content1">
            	
            	</div>
            	<div class="panel panel-success" style="margin-top:30px;width:50%;margin-left:25%" id="message01">
	 			   <div class="panel-body">
	 			   		保存成功！
	 			   </div>
	 			</div>
	 			<div class="panel panel-danger" style="margin-top:30px;width:50%;margin-left:25%" id="message02">
	 			   <div class="panel-body">
	 			   		请正确填写信息！
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
	function resetPW(userId,name){
		if(window.confirm("姓名："+name+"     确定重置密码？")){
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
	function editInfo(item) { 
		$("#teaid").hide();
		$("#message01").hide();
		$("#message02").hide();
		$("#message03").hide();
		var collegeId;
		var departmentId;
		$.ajax({
			type:"post",
			url:"<%=request.getContextPath()%>/teacher/findTeacherInfo.do",
			data:{"type":"json","findBy":"id","primaryKey":item},
			dataType:"json",
			success:function(data){
				var length = data.teachers.length;
					if(data.teachers[0].sex=='男'){
						document.getElementById('man').selected=true;
					}
					$('#email1').val(data.teachers[0].email);
					$('#position1').val(data.teachers[0].position);
					$('#teaid').val(data.teachers[0].id);
					$('#qq1').val(data.teachers[0].qq);
					$('#telephone1').val(data.teachers[0].telephone);
					$('#remark1').val(data.teachers[0].remark);
					$('#name1').val(data.teachers[0].name);
					$('#no1').val(data.teachers[0].no);
					collegeId=data.teachers[0].department.college.id;
					departmentId=data.teachers[0].department.id;
					$.ajax({
						type:"post",
						url:"<%=request.getContextPath()%>/college/viewCollege.do",
						data:{"type":"json"},
						dataType:"json",
						success:function(data){
							var length = data.colleges.length;
							var content = "<label>学院：</label><select name='id' class='form-control' id='selectCollege1' onchange='getDepartments()'>";
							for(var i=0;i<length;i++){
								if(data.colleges[i].id==collegeId){
									content += "<option value='"+data.colleges[i].id+"'selected='selected'>"+data.colleges[i].collegeName+"</option>";
								}
								else content += "<option value='"+data.colleges[i].id+"'>"+data.colleges[i].collegeName+"</option>";
							}
							content += "</select>";
							$(".college-content1").html(content);
							$.ajax({
								type:"post",
								url:"<%=request.getContextPath()%>/department/findDepartmentBy.do",
								data:{"type":"json","collegeId":collegeId},
								dataType:"json",
								success:function(data){
									console.log(data);
									var length = data.departments.length;
									var content = "<label>系：</label><select name='id2' class='form-control' id='selectDepartment1' onchange=''>";
									for(var i=0;i<length;i++){
										if(departmentId==data.departments[i].id){
											content += "<option value='"+data.departments[i].id+"'selected='selected'>"+data.departments[i].departmentName+"</option>";
										}
										else content += "<option value='"+data.departments[i].id+"'>"+data.departments[i].departmentName+"</option>";
									}
									content += "</select>";
									$(".department-content1").html(content);
									
								},
								error:function(msg){
									console.log(msg)
								}
							})	
							
						},
						error:function(msg){
							console.log(msg);
						}
					})	
	
			},
			error:function(msg){
				console.log(msg);
			}
		})
		$('#update').modal('show');
	}
	function getDepartments(){
		var collegeId = $("#selectCollege1").val();
		$.ajax({
			type:"post",
			url:"<%=request.getContextPath()%>/department/findDepartmentBy.do",
			data:{"type":"json","collegeId":collegeId},
			dataType:"json",
			success:function(data){
				$(".department-content1").html('');
				var length = data.departments.length;
				var content ="<label>系：</label><select name='id2' class='form-control' id='selectDepartment1' onchange=''>";
				content += "<option value='null'>--请选择系--</option>";
				for(var i=0;i<length;i++){
					content += "<option value='"+data.departments[i].id+"'>"+data.departments[i].departmentName+"</option>";
				}
				content += "</select>";
				$(".department-content1").html(content);
				
			},
			error:function(msg){
				console.log(msg)
			}
		})	
	}
	function inspection1(){
		var teacherno=$("#no1").val();
		$.ajax({
			type:"post",
			url:"<%=request.getContextPath()%>/teacher/inspection.do",
			data:{"teacherno":teacherno},
			dataType:"text",
			success:function(data){	
				if(data==1){
					$("#updateInfo").attr("disabled", true);
					$("#no1").focus().select();
					$("#message03").fadeIn();
				}
				else{
					$("#message03").hide();
					$("#updateInfo").attr("disabled", false);
				}
			},
			error:function(msg){
				console.log(msg)
			}
		})		
		//$(document).ready(function(){  $("input[name=collegeName]").focus();});
	}
	function updateInfo(){
		var collegeID=$("#selectCollege1").val();
		var dempartmentID=$("#selectDepartment1").val();
		//获取学生信息
		var id=$("#teaid").val();
		var name = $("#name1").val();
		var no = $("#no1").val();
		var sex = $("#sex1").val();
		var position=$('#position1').val();
		var qq = $("#qq1").val();
		var telephone = $("#telephone1").val();
		var email = $("#email1").val();
		var remark=$('#remark1').val();
		if(name!=""&&no!=""&&sex!=""&&position!=""&&collegeID!=""&&dempartmentID!=""){
			$.ajax({
				type:"post",
				url:"<%=request.getContextPath()%>/teacher/updateAllInfo.do",
				data:{
					  "id":id,
					  "name":name,
					  "no":no,	 
					  "sex":sex,
					  "position":position,
					  "qq":qq,	 
					  "telephone":telephone,
					  "email":email,
					  "remark":remark,
					  "dempartmentID":dempartmentID
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
		$("#message3").hide();
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
	function inspection(){
		var teacherno=$("#no").val();
		$.ajax({
			type:"post",
			url:"<%=request.getContextPath()%>/teacher/inspection.do",
			data:{"teacherno":teacherno},
			dataType:"text",
			success:function(data){	
				if(data==1){
					$("#addConfirm").attr("disabled", true);
					$("#no").focus().select();
					$("#message3").fadeIn();
				}
				else{
					$("#message3").hide();
					$("#addConfirm").attr("disabled", false);
				}
			},
			error:function(msg){
				console.log(msg)
			}
		})		
		//$(document).ready(function(){  $("input[name=collegeName]").focus();});
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
			$.ajax({
				type:"post",
				url:"<%=request.getContextPath()%>/teachStu/deleteTeacher.do",
				data:{"teacherId":id,
				},
				dataType:"json",
				success:function(data){
					 alert("删除成功！");
					 window.setTimeout(reload,200);
					
				},
				error:function(msg){
					alert("删除失败！");
					console.log(msg)
				}
			})
			
		}
	}
</script>	
	
</body>
</html>