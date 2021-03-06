<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>view college</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.min.css">

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/scrollerbar.css"/>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/common.css"/>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/jquery.searchableSelect.css"/>

<script src="<%=request.getContextPath() %>/js/jquery-3.1.1.min.js"></script>
<script src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath() %>/js/jquery.searchableSelect.js"></script>
<script src="<%=request.getContextPath() %>/js/person/common.js"></script>

<style>
	/*查找框样式*/
.search{
	width:70%;
	float:left;
	margin-top:0;
}
.searchable-select-holder{
	border-radius:0;
}
.btn{
	border-radius:0;
}
</style>
</head>
<body>

	<c:if test="${message =='failed'}">
		<div class="alert alert-danger alert-dismissable">
			<button type="button" class="close" data-dismiss="alert" aria-hidden="true">
				&times;
			</button>
			失败！
		</div>	
	</c:if>
	<c:if test="${message =='success'}">
	<div class="alert alert-success alert-dismissable">
		<button type="button" class="close" data-dismiss="alert"
				aria-hidden="true">
			&times;
		</button>
		成功！
	</div>
	</c:if>
	
	<div class="panel panel-default" style="margin:0">
	    <div class="panel-body">
	       	  查看<span class="	glyphicon glyphicon-eye-open">年级 </span>                   
	       <!--  <div class="col-md-1">
	    		    
	        </div> 
	          
	        <form action="<%=request.getContextPath() %>/grade/findGradeBy.do" method="post">      
	      		<input type="hidden" name="type" value="view"/>
	      	<div class="col-md-3">
		      	 <div id="collegeHeadContent">
		      	 
		      	 </div>
	      	</div>
		      	<div class="col-md-4">
		      		<input type="submit" value="查询" class="btn btn-default"/>
		        </div> 
	   		</form>  
	   	-->               
	        <a href="#" data-toggle="modal" data-target="#myModal" id="add"> <span class="	glyphicon glyphicon-plus-sign" style="color:green;float:right" data-toggle="tooltip" data-placement="bottom" title="添加"></span></a>
    			
	    </div>
    </div>
    <table class="table table-hover table-striped" >
    	<tr class="info">
    		<td width="70px">编号</td>
    		<td>年级</td>
    		<td>系</td>
    		<td>操作</td>
    	</tr>
    	<c:forEach items="${grades }" var="items">
    		<tr>
    			<td><c:out value="${items.id }"></c:out></td>
    			<td><c:out value="${items.gradeName }"></c:out></td>
    			<td><c:out value="${items.department.departmentName }"></c:out></td>
    			
    			<td width="70px">
    				<!----> <a href="javascript:void(0)" onclick="deleteItem(${items.id })"> <span class="glyphicon  glyphicon-trash" style="color:red" data-toggle="tooltip" data-placement="bottom" title="删除"></span></a> 
    				<a href="javascript:void(0)" 	onclick="editInfo(${items.id})"> <span class="glyphicon glyphicon-edit" style="color:green;padding-left:20px" data-toggle="tooltip" data-placement="bottom" title="更新"></span></a>
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
                <h4 class="modal-title" id="myModalLabel">添加年级</h4>
            </div>
            <div class="modal-body">
            	<div class="grade-content">
            		<input type='text' class='form-control' id='grade' hidden='hidden' placeholder='年级' required onchange="inspection()"/>
            	</div>
            	<div class="panel panel-success" style="margin-top:30px;width:50%;margin-left:25%" id="message3">
	 			   <div class="panel-body" align="center">
	 			   		该年级已存在！
	 			   </div>
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
				<input id="gradeid" />
				<label>系：</label><input type="text" name="departmentname1" placeholder="系名称（*必填）" id="departmentname1" class="form-control"/>
				<label>年级：</label><input type="text" name="gradename1" placeholder="年级名称（*必填）" id="gradename1" class="form-control" onchange="inspection1()"/>
            	<br>
            	<div class="panel panel-success" style="margin-top:30px;width:50%;margin-left:25%" id="message03">
	 			   <div class="panel-body">
	 			   		该年级已存在！
	 			   </div>
	 			</div>
            	<div class="panel panel-success" style="margin-top:30px;width:50%;margin-left:25%" id="message01">
	 			   <div class="panel-body">
	 			   		保存成功！
	 			   </div>
	 			</div>
	 			<div class="panel panel-danger" style="margin-top:30px;width:50%;margin-left:25%" id="message02">
	 			   <div class="panel-body">
	 			   		请输入年级！
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
	function inspection(){
		var departmentId=1;
		var gradeName=$("#grade").val();
		$.ajax({
			type:"post",
			url:"<%=request.getContextPath()%>/grade/inspection.do",
			data:{"gradeName":gradeName,"departmentId":departmentId},
			dataType:"text",
			success:function(data){	
				if(data==1){
					$("#addConfirm").attr("disabled", true);
					$("#grade").focus().select();
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
	
	function editInfo(gradeId) { 
		$("#gradeid").hide();
		$("#message01").hide();
		$("#message02").hide();
		$("#message03").hide();
		$("#gradeid").val(gradeId);
		$.ajax({
			type:"post",
			url:"<%=request.getContextPath()%>/grade/findgrade1By.do",
			data:{"type":"json","gradeId":gradeId},
			dataType:"json",
			success:function(data){
				$("#gradename1").val(data.grades[0].gradeName);
				$("#departmentname1").val(data.grades[0].department.departmentName);
			},
			error:function(msg){
				console.log(msg)
			}
		})	
		$('#update').modal('show');
	}
	function inspection1(){
		var departmentId=1;
		var gradeName=$("#gradename1").val();
		$.ajax({
			type:"post",
			url:"<%=request.getContextPath()%>/grade/inspection.do",
			data:{"gradeName":gradeName,"departmentId":departmentId},
			dataType:"text",
			success:function(data){	
				if(data==1){
					$("#updateInfo").attr("disabled", true);
					$("#gradename1").focus().select();
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
		var gradeid=$("#gradeid").val();
		var	gradename=$("#gradename1").val();
		if(gradename!=''){
			$.ajax({
				type:"post",
				url:"<%=request.getContextPath()%>/grade/updateInfo.do",
				data:{ "id":gradeid,
					  "gradeName":gradename			  
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
		//getColleges();//获取学院信息
		
	})
	var data;//获取学院数据的全局变量
	//获取学院信息
	function getColleges(){
		$.ajax({
			type:"post",
			url:"<%=request.getContextPath()%>/college/viewCollege.do",
			data:{"type":"json"},
			dataType:"json",
			success:function(data){
				var length = data.colleges.length;
				var content = "<select name='id' class='form-control' id='selectCollege1' onchange='getDepartment()'>";
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
		var collegeId = $("#selectCollege1").val();
		$.ajax({
			type:"post",
			url:"<%=request.getContextPath()%>/department/findDepartmentBy.do",
			data:{"type":"json","collegeId":collegeId},
			dataType:"json",
			success:function(data){
				console.log(data);
				var length = data.departments.length;
				var content = "<select name='id' class='form-control' id='selectDepartment' onchange='showInput()'>";
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
	//选取学院后的事件处理
	function showInput(){
		//选取学院后，错误消息隐藏
		$("#message2").hide();
		$(".grade-content").html("<input type='text' class='form-control' id='grade' hidden='hidden' placeholder='年级' required/>");
		//alert(document.getElementById("selectCollege").value)
	}
	
	$("#addConfirm").click(function(){
		$("#message").hide();
		$("#message2").hide();
		var gradeName=$("#grade").val();
		//var departmentId=$("#selectDepartment").val();
		if(gradeName!=""){
			$.ajax({
				type:"post",
				url:"<%=request.getContextPath()%>/grade/addGrade.do",
				data:{"gradeName":gradeName},
				dataType:"text",
				success:function(data){
					if(data==1){
						$("#message").fadeIn();
					}
					 window.setTimeout(reload,1000);
					
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
	
	//删除
	function deleteItem(id){
		if(confirm("取认删除？删除该选项将会删除与该选项相关联数据！")){
			location.href="<%=request.getContextPath()%>/grade/deleteGrade.do?id="+id;
		}
	}
	
	window.setTimeout(hidden,1000); 
	function hidden() 
	{ 
		$(".alert").hide();
	} 
	//弹出框
	$(function () { 
		$("[data-toggle='popover']").popover();
	});
	//进入时 显示要查找的学院
	// window.onload = function(){ 
//     $.ajax({
//       type:"post",
//       url:"<%=request.getContextPath()%>/college/viewCollege.do",
//       data:{"type":"json"},
//       dataType:"json",
//       success:function(data){
//         var length = data.colleges.length;
//         var content = "<select name='collegeId' class='form-control' id='selectCollege'>";
//         content += "<option value='null'>--请选择学院--</option>";
//         for(var i=0;i<length;i++){
//           content += "<option value='"+data.colleges[i].id+"'>"+data.colleges[i].collegeName+"</option>";
//         }
//         content += "</select>";
//         $("#collegeHeadContent").html(content);
        
//       },
//       error:function(msg){
//         console.log(msg)
//       }
//     });
//	}
	
</script>	
	
</body>
</html>