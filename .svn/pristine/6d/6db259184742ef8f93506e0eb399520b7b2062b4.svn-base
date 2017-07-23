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
	                           系内调剂学生
	           <select id="queryBy" name="queryBy">
            		<option value="no">学号</option>
            		<option value="name">姓名</option>
              </select><input type="text" id="primary"><input type="button" value="查询"  onclick="query()">
            	
	          <a href="<%=request.getContextPath() %>/student/exportStudent.do">
	         	<span class="glyphicon glyphicon-export" style="color:green;float:right;margin-right:80px" data-toggle="tooltip" data-placement="bottom" title="导出"></span>                  
	          </a>
	          <a href="#" data-toggle="modal" data-target="#import1" id="import"> 
	          	<span class="glyphicon glyphicon-import" style="color:green;float:right;margin-right:40px" data-toggle="tooltip" data-placement="bottom" title="导入"></span>
	          </a>
    		
	          <a href="#" data-toggle="modal" data-target="#myModal" id="add"> <span class="glyphicon glyphicon-plus-sign" style="color:green;float:right;margin-right:40px" data-toggle="tooltip" data-placement="bottom" title="添加"></span></a>
    			
	    </div>
    </div>
    <div id="t1" class="t1">
	    <table class="table table-hover table-striped" id="table">
	    	<tr class="info">
	    		<td width="100px">学号</td>
	    		<td>姓名</td>
	    		<td>班级</td>
	    		<td>方向</td>
	    		<td>专业</td>
	    		
	    		<td>年级</td>
	    		<td>系</td>
	    		<td>是否允许系内调剂</td>
	    		<td>操作</td>
	    	</tr>
	    	<c:forEach items="${sutdents }" var="items">
	    		<c:if test="${items.topics == null }">
		    		<tr>
		    			<td><c:out value="${items.no }"></c:out></td>
		    			<td>
		    				<a href="<%=request.getContextPath() %>/student/viewStudentOne.do?filter=no&no=<c:out value="${items.no }"></c:out>&id=<c:out value="${items.id }"></c:out>"><c:out value="${items.name }"></c:out></a>
		    			</td>
		    			<td><c:out value="${items.clazz.className }"></c:out></td>
		    			<td><c:out value="${items.clazz.direction.directionName }"></c:out></td>
		    			<td><c:out value="${items.clazz.direction.spceialty.specialtyName }"></c:out></td>
		    			<td><c:out value="${items.clazz.direction.spceialty.grade.gradeName }"></c:out></td>
		    			<td><c:out value="${items.clazz.direction.spceialty.grade.department.departmentName }"></c:out></td>
		    			<td>
		    				<!-- 系内调剂默认为0，1为不调剂  -->
		    				<c:if test="${items.swapInDepa == 0 }">
		    					是
		    				</c:if>
		    				<c:if test="${items.swapInDepa == 1 }">
		    					否
		    				</c:if>
		    			</td>
		    			
		    			<td>
		    				<c:if test="${items.swapInDepa == 0 }">
		    					<a href="<%=request.getContextPath() %>/swap/viewSwapTopicDept.do?studentId=${items.id }&directionId=${items.clazz.direction.id}">调剂</a>
		    				</c:if>
		    			</td>
		    			
		    			
		    		</tr>
		    	</c:if>
			</c:forEach>
	    </table>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">添加学生</h4>
            </div>
            <div class="modal-body">
            	<input type="text" name="name" placeholder="学生姓名（*必填）" id="name" class="form-control"/>
            	<br>
            	<input type="text" name="no" placeholder="学生学号（*必填）" id="no" class="form-control"/>
            	<br>
            	<select id="sex" name="sex" class="form-control">
            		<option value="男">男</option>
            		<option value="女">女</option>
            	</select>
            	<br>
            	<input type="text" name="telephone" placeholder="学生电话（选填）" id="telephone" class="form-control"/>
            	<span id="tel_tip"></span>
            	<br>
            	<input type="text" name="qq" placeholder="学生QQ（选填）" id="qq" class="form-control"/>
            	<br>
            	<input type="text" name="email" placeholder="学生邮箱（选填）" id="email" class="form-control"/>
            	<br>
            	<div class="grade-content">
            	
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

<!-- 模态框（Modal） -->
<div class="modal fade" id="import1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					批量导入学生
				</h4>
			</div>
			<div class="modal-body">
				<form action="<%=request.getContextPath() %>/student/batchImportStudent.do" method="post" enctype="multipart/form-data">
					<div class="input-group">
			            <span class="input-group-addon" style="border-radius: 0;">学生名单文件</span>
			            <input type="file" name="file" required class="file" class="form-control" accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet">
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
				<input type="text" name="qq1" placeholder="学生QQ（选填）" id="stuid" class="form-control"/>
				<label>姓名：</label><input type="text" name="name1" placeholder="学生姓名（*必填）" id="name1" class="form-control"/>
            	<br>
            	<label>学号：</label><input type="text" name="no1" placeholder="学生学号（*必填）" id="no1" class="form-control"/>
            	<br>
            	<label>性别：</label><select id="sex1" name="sex1" class="form-control">
            		<option value="男" id="man" selected>男</option>
            		<option value="女" id="woman" selected="selected">女</option>
            	</select>
            	<br>
            	<label>电话号码：</label><input type="text" name="telephone1" placeholder="学生电话（选填）" id="telephone1" class="form-control"/>
            	<br>
            	<label>QQ：</label><input type="text" name="qq1" placeholder="学生QQ（选填）" id="qq1" class="form-control"/>
            	<br>
            	<label>邮箱：</label><input type="text" name="email1" placeholder="学生邮箱（选填）" id="email1" class="form-control"/>
            	<br>
            	<div class="grade-content1">
            
            	</div>
            	<br>
            	<div class="specialty-content1">
            	
            	</div>
            	<br>
            	<div class="direction-content1">
            	
            	</div>
            	<br>
            	<div class="clazz-content1">
            	
            	</div>
            	<div class="panel panel-success" style="margin-top:30px;width:50%;margin-left:25%" id="message01">
	 			   <div class="panel-body">
	 			   		保存成功！
	 			   </div>
	 			</div>
	 			<div class="panel panel-danger" style="margin-top:30px;width:50%;margin-left:25%" id="message02">
	 			   <div class="panel-body">
	 			   		请选择正确选项！
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
	function query(){
		if($('#primary').val()==''){
			alert("请输入查询学生姓名或学号");
		}
		else{
			var queryBy=$('#queryBy').val();
			var primary=$('#primary').val();
			$.ajax({
				type:"post",
				url:"<%=request.getContextPath()%>/student/findStudentInfo.do",
				data:{"type":"json","queryBy":queryBy,"primary":primary},
				dataType:"json",
				success:function(data){
					var length = data.students.length;
					if(length>0){
						$('.t1').html('');
						var content=" <table class='table table-hover table-striped' id='table'><tr class='info'><td width='100px'>学号</td><td>姓名</td><td>班级</td><td>方向</td><td>专业</td><td>年级</td><td>系</td><td>学院</td><td>操作</td></tr>";
						for(var i=0;i<length;i++){
							content+="<tr>";
							content+="<td>"+data.students[i].no+"</td>";
							content+="<td>"+data.students[i].name+"</td>";
							content+="<td>"+data.students[i].clazz.className+"</td>";
							content+="<td>"+data.students[i].clazz.direction.directionName+"</td>";
							content+="<td>"+data.students[i].clazz.direction.spceialty.specialtyName +"</td>";
							content+="<td>"+data.students[i].clazz.direction.spceialty.grade.gradeName+"</td>";
							content+="<td>"+data.students[i].clazz.direction.spceialty.grade.department.departmentName+"</td>";
							content+="<td>"+data.students[i].clazz.direction.spceialty.grade.department.college.collegeName+"</td>";
							content+="<td width='70px'>";
							content+="<a href='javascript:void(0)' onclick='deleteItem("+data.students[i].id+")'>"+"<span class='glyphicon  glyphicon-trash' style='color:red' data-toggle='tooltip' data-placement='bottom' title='删除'></span></a>";
							content+="<a href='javascript:void(0)' onclick='editInfo("+data.students[i].id+")'>"+"<span class='glyphicon glyphicon-edit' style='color:green;padding-left:20px' data-toggle='tooltip' data-placement='bottom' title='更新'></span></a>";
							content+="</td>";
							content+="</tr>";
						}
						$('.t1').html(content);
					}
					else{
						alert("无此学生");
					}
				},
				error:function(msg){
					console.log(msg)
				}
			})
		}
	}
	//学生修改js开始
	function updateInfo(){
		var classId=$("#selectClazz1").val();
		var gradeId = $("#selectGrade1").val();
		var collegeId = $("#selectCollege1").val();
		var departmentId = $("#selectDepartment1").val();
		var specialtyId = $("#selectSpecialty1").val();
		var directionId = $("#selectDirection1").val();
		//获取学生信息
		var id=$("#stuid").val();
		var name = $("#name1").val();
		var no = $("#no1").val();
		var sex = $("#sex1").val();
		var qq = $("#qq1").val();
		var telephone = $("#telephone1").val();
		var email = $("#email1").val();
		
		if(classId!="null"&&gradeId!="null"&&collegeId!="null"&&departmentId!="null"&&specialtyId!="null"&&directionId!="null"){
			$.ajax({
				type:"post",
				url:"<%=request.getContextPath()%>/student/updateInfo.do",
				data:{"classId":classId,
					  "id":id,
					  "name":name,
					  "no":no,	 
					  "sex":sex,	 
					  "qq":qq,	 
					  "telephone":telephone,
					  "email":email
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



    function editInfo(item) { 
    	$("#stuid").hide();
    	$("#message01").hide();
		$("#message02").hide();
    	var gradeID;
    	var spceialtyID;
    	var directionID;
    	var clazzID;
    	$.ajax({
			type:"post",
			url:"<%=request.getContextPath()%>/student/findStudent1By.do",
			data:{"type":"json","studentID":item},
			dataType:"json",
			success:function(data){
				if(data.student.sex=='男'){
					document.getElementById('man').selected= true;
				}
				gradeID=data.student.clazz.direction.spceialty.grade.id;
				spceialtyID=data.student.clazz.direction.spceialty.id;
				directionID=data.student.clazz.direction.id;
				clazzID=data.student.clazz.id;
				$('#email1').val(data.student.email);
				$('#stuid').val(data.student.id);
				$('#qq1').val(data.student.qq);
				$('#telephone1').val(data.student.telephone);
				$('#name1').val(data.student.name);
				$('#no1').val(data.student.no);
				$.ajax({
					type:"post",
					url:"<%=request.getContextPath()%>/specialty/findSpecialtyBy.do",
					data:{"type":"json","gradeId":gradeID},
					dataType:"json",
					success:function(data){
						var length = data.specialtys.length;
						var content = "<label>专业：</label><select name='id' class='form-control' id='selectSpecialty1' onchange='getDirection1()'>";
						for(var i=0;i<length;i++){
							if(spceialtyID==data.specialtys[i].id){
								content += "<option value='"+data.specialtys[i].id+"'selected='selected'>"+data.specialtys[i].specialtyName+"</option>";
							}
							else content += "<option value='"+data.specialtys[i].id+"'>"+data.specialtys[i].specialtyName+"</option>";
						}
						content += "</select>";
						$(".specialty-content1").html(content);
						
						$.ajax({
							type:"post",
							url:"<%=request.getContextPath()%>/direction/findDirectionBy.do",
							data:{"type":"json","specialtyId":spceialtyID},
							dataType:"json",
							success:function(data){
								var length = data.directions.length;
								var content = "<label>方向：</label><select name='id2' class='form-control' id='selectDirection1' onchange='getClazz1()'>";
								for(var i=0;i<length;i++){
									if(directionID==data.directions[i].id){
										content += "<option value='"+data.directions[i].id+"'selected='selected'>"+data.directions[i].directionName+"</option>";
									}
									else content += "<option value='"+data.directions[i].id+"'>"+data.directions[i].directionName+"</option>";
								}
								content += "</select>";
								$(".direction-content1").html(content);
								
								$.ajax({
									type:"post",
									url:"<%=request.getContextPath()%>/clazz/findClazzBy.do",
									data:{"type":"json","directionId":directionID},
									dataType:"json",
									success:function(data){
										var length = data.clazzs.length;
										var content = "<label>班级：</label><select name='id4' class='form-control' id='selectClazz1' onchange=''>";
										for(var i=0;i<length;i++){
											if(clazzID==data.clazzs[i].id){
												content += "<option value='"+data.clazzs[i].id+"'selected='selected'>"+data.clazzs[i].className+"</option>";
											}
											else content += "<option value='"+data.clazzs[i].id+"'>"+data.clazzs[i].className+"</option>";
										}
										content += "</select>";
										$(".clazz-content1").html(content);
										
									},
									error:function(msg){
										console.log(msg)
									}
								})	
								
							},
							error:function(msg){
								console.log(msg)
							}
						})	
						
					},
					error:function(msg){
						console.log(msg)
					}
				})	
				
			},
			error:function(msg){
				console.log(msg)
			}
		})	
    	var departmentId = ${departmentId};
		$.ajax({
			type:"post",
			url:"<%=request.getContextPath()%>/grade/findGradeBy.do",
			data:{"type":"json","departmentId":departmentId},
			dataType:"json",
			success:function(data){
				var length = data.grades.length;
				var content = "<label>年级：</label><select name='id1' class='form-control' id='selectGrade1' onchange='getSpecialty1()'>";
				for(var i=0;i<length;i++){
					if(gradeID==data.grades[i].id){
						content += "<option value='"+data.grades[i].id+"'selected='selected'>"+data.grades[i].gradeName+"</option>";
					}
					else content += "<option value='"+data.grades[i].id+"'>"+data.grades[i].gradeName+"</option>";
				}
				content += "</select>";
				$(".grade-content1").html(content);
				
			},
			error:function(msg){
				console.log(msg)
			}
		})
   		$('#update').modal('show');     		
    }  
	
    function getSpecialty1(){
    	var gradeId = $("#selectGrade1").val();
    	$(".specialty-content1").html('');
    	$.ajax({
			type:"post",
			url:"<%=request.getContextPath()%>/specialty/findSpecialtyBy.do",
			data:{"type":"json","gradeId":gradeId},
			dataType:"json",
			success:function(data){
				var length = data.specialtys.length;
				var content = "<label>专业：</label><select name='id' class='form-control' id='selectSpecialty1' onchange='getDirection1()'>";
				content += "<option value='null'>--请选择专业--</option>";
				for(var i=0;i<length;i++){
					content += "<option value='"+data.specialtys[i].id+"'>"+data.specialtys[i].specialtyName+"</option>";
				}
				content += "</select>";
				$(".specialty-content1").html(content);
				
			},
			error:function(msg){
				console.log(msg)
			}
		})	
    }
    function getDirection1(){
    	var specialtyId = $("#selectSpecialty1").val();
    	$(".direction-content1").html('');
		$.ajax({
			type:"post",
			url:"<%=request.getContextPath()%>/direction/findDirectionBy.do",
			data:{"type":"json","specialtyId":specialtyId},
			dataType:"json",
			success:function(data){
				var length = data.directions.length;
				var content = "<label>方向：</label><select name='id2' class='form-control' id='selectDirection1' onchange='getClazz1()'>";
				content += "<option value='null'>--请选择方向--</option>";
				for(var i=0;i<length;i++){
					content += "<option value='"+data.directions[i].id+"'>"+data.directions[i].directionName+"</option>";
				}
				content += "</select>";
				$(".direction-content1").html(content);
				
			},
			error:function(msg){
				console.log(msg)
			}
		})
    }
	function getClazz1(){
		var directionId = $("#selectDirection1").val();
		$(".clazz-content1").html('');
		$.ajax({
			type:"post",
			url:"<%=request.getContextPath()%>/clazz/findClazzBy.do",
			data:{"type":"json","directionId":directionId},
			dataType:"json",
			success:function(data){
				var length = data.clazzs.length;
				var content = "<label>班级：</label><select name='id4' class='form-control' id='selectClazz1' onchange=''>";
				content += "<option value='null'>--请选择班级--</option>";
				for(var i=0;i<length;i++){
					content += "<option value='"+data.clazzs[i].id+"'>"+data.clazzs[i].className+"</option>";
				}
				content += "</select>";
				$(".clazz-content1").html(content);
				
			},
			error:function(msg){
				console.log(msg)
			}
		})	
	}
	//学生修改js结束
    
    
    
    
    
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
	
	
	$("#add").click(function(){
		//初始时提示消息隐藏
		$("#message").hide();
		$("#message2").hide();
		getGrades();//获取学院信息
	})
	

	//获取年级信息
	function getGrades(){
		$("#message2").hide();
		var departmentId = ${departmentId};
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
		$("#message2").hide();
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
		$("#message2").hide();
		var specialtyId = $("#selectSpecialty").val();
		$.ajax({
			type:"post",
			url:"<%=request.getContextPath()%>/direction/findDirectionBy.do",
			data:{"type":"json","specialtyId":specialtyId},
			dataType:"json",
			success:function(data){
				var length = data.directions.length;
				var content = "<select name='id' class='form-control' id='selectDirection' onchange='getClazz()'>";
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
	//获取班级信息
	function getClazz(){
		$("#message2").hide();
		var directionId = $("#selectDirection").val();
		$.ajax({
			type:"post",
			url:"<%=request.getContextPath()%>/clazz/findClazzBy.do",
			data:{"type":"json","directionId":directionId},
			dataType:"json",
			success:function(data){
				var length = data.clazzs.length;
				var content = "<select name='id' class='form-control' id='selectClazz' onchange=''>";
				content += "<option value='null'>--请选择班级--</option>";
				for(var i=0;i<length;i++){
					content += "<option value='"+data.clazzs[i].id+"'>"+data.clazzs[i].className+"</option>";
				}
				content += "</select>";
				$(".clazz-content").html(content);
				
			},
			error:function(msg){
				console.log(msg)
			}
		})	
	}
	
	$("#addConfirm").click(function(){
		var classId=$("#selectClazz").val();
		var gradeId = $("#selectGrade").val();
		var collegeId = $("#selectCollege").val();
		var departmentId = $("#selectDepartment").val();
		var specialtyId = $("#selectSpecialty").val();
		var directionId = $("#selectDirection").val();
		//获取学生信息
		var name = $("#name").val();
		var no = $("#no").val();
		var sex = $("#sex").val();
		var qq = $("#qq").val();
		var telephone = $("#telephone").val();
		var email = $("#email").val();
		
		if(classId!="null"&&gradeId!="null"&&collegeId!="null"&&departmentId!="null"&&specialtyId!="null"&&directionId!="null"){
			$.ajax({
				type:"post",
				url:"<%=request.getContextPath()%>/student/addStudent.do",
				data:{"classId":classId,
					  "name":name,
					  "no":no,	 
					  "sex":sex,	 
					  "qq":qq,	 
					  "telephone":telephone,
					  "email":email
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
	</script>	
	<script type="text/javascript"> //验证手机号码
		//当tel失去焦点的时候，验证是否是正确的手机号码，如果不是，在tel_tip中提示错误
		window.onload=function(){
			var oTel = document.getElementById("telephone");
			oTel.onblur = function(){
				var isTel = isTelephone(oTel.value);
				var oSpan = document.getElementById("tel_tip");
				if(!isTel){
					oSpan.style.color = "red";
					oSpan.innerHTML = "错误的手机号码，请重新输入";
				}else{
					oSpan.style.color = "green";
					oSpan.innerHTML = "正确的手机号码";
				}
			}
			
		}
		function isTelephone(tel){
			var p = /^0?(13[0-9]|15[012356789]|17[013678]|18[0-9]|14[57])[0-9]{8}$/;
			return p.test(tel);
		}
</script>
</body>
</html>