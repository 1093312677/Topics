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

<!-- select -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap-select.min.css">
<script src="<%=request.getContextPath() %>/js/bootstrap-select.min.js"></script>
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
	
	<div class="container">
		<div class="row">
			<div class="col-md-3">
			</div>
			<div class="col-md-6">
				<c:if test="${isNow == false }">
					<div class="panel panel-default" align='center' style="margin-top:20px">
							<div class="panel-heading">
								<h3 class="panel-title">
									提示
								</h3>
							</div>
							<div class="panel-body">
								不是上传题目时间！
							</div>
						</div>
					
				</c:if>
				<c:if test="${isNow == true }">
					<!--  enctype="multipart/form-data" -->
					<form action="<%=request.getContextPath()%>/topic/addTopic.do" method="post" enctype="multipart/form-data" onsubmit="return check()">
						<br>
						<div class="input-group">
				            <span class="input-group-addon" style="border-radius: 0;">题目　名称<span style="color:red;margin-top:13px;">*</span></span>
				            <input type="text" name="topicsName" class="form-control" placeholder="题目名称（必填）" required>
				        </div>
				        <br>
				        <br>
				        <input type="hidden" value="${gradeId }" name="gradeId"/>
				         <div class="input-group">
				            <span class="input-group-addon" style="border-radius: 0;">适用于方向<span style="color:red;margin-top:13px;">*</span></span>
				            <div id="direction-content" style="z-index:1">
				            	<select id="direction" name="directionIds" required class="selectpicker show-tick form-control" multiple data-live-search="true">
		                        </select>
				            </div>
				        </div>
				        <br>
				         <div class="input-group">
				            <span class="input-group-addon" style="border-radius: 0;">可选学生数<span style="color:red;margin-top:13px;">*</span></span>
				            <input type="number" name="enableSelect" id='enableSelect' class="form-control" placeholder="可选学生数（必填）" required>
				        	<span style="color:#06c290;margin-left:20px" id="numContainer"></span>
				        </div>
				        <br>
				         <div class="input-group">
				            <span class="input-group-addon" style="border-radius: 0;">文件　 上传</span>
				            <input type="file" name="file" class="file" class="form-control" placeholder="文件">
				        </div>
				        <br>
				        
				        <div class="input-group">
				            <span class="input-group-addon" style="border-radius: 0;">题目  　简介</span></span>
				            <textarea class="form-control"   rows="5" name="introduce">
				            
				            </textarea>
				        </div>
				        <br>
				        <input type="submit" value="保存" class="btn btn-success" style="width:150px"/>
				        
				        
					</form>
				</div>
			</c:if>
			<div class="col-md-3">
			</div>
		</div>
	</div>
	
<script>

function change(){
	getDirection();
	var gradeId = ${gradeId };
	//var gradeId = $("#grade").val();
	getNum(gradeId);
	
}
window.onload = change;
//获取方向信息
function getDirection(){
	var gradeId = ${gradeId };
	$.ajax({
		type:"post",
		url:"<%=request.getContextPath()%>/topic/findDirectionBy.do",
		data:{"type":"json","gradeId":gradeId},
		dataType:"json",
		success:function(data){
			console.log(data);
			var length = data.directions.length;
			//var content = '<select id="department" name="directionIds" class="selectpicker show-tick form-control" multiple data-live-search="true">';
			var content = "<select class='form-control' id='department'  name='directionIds' onchange='' multiple='multiple' required>";
			for(var i=0;i<length;i++){
				content += "<option value='"+data.directions[i].id+"'>"+data.directions[i].directionName+"</option>";
			}
			content += "</select>";
			$("#direction-content").html(content);
			
		},
		error:function(msg){
			console.log(msg)
		}
	})	
}

//获取年级信息
function getGrades(){
	var collegeId = $("#collegeId").val();
	$.ajax({
		type:"post",
		url:"<%=request.getContextPath()%>/grade/findGradeBy.do",
		data:{"type":"json","collegeId":collegeId},
		dataType:"json",
		success:function(data){
			var length = data.grades.length;
			//var content = "";
			var content = '<select id="grade" class="form-control" onchange="getDirection()"><option>--选择年级--</option>';
            
			for(var i=0;i<length;i++){
				content += "<option value='"+data.grades[i].id+"'>"+data.grades[i].gradeName+"</option>";
			}
			content += '</select>';
			$("#grade-content").html(content);
			//$("#grade").append(content);
			
		},
		error:function(msg){
			console.log(msg)
		}
	})	
}
//获取系信息
function getDepartment(){
	var gradeId = $("#grade").val();
	$.ajax({
		type:"post",
		url:"<%=request.getContextPath()%>/department/findDepartmentBy.do",
		data:{"type":"json","gradeId":gradeId},
		dataType:"json",
		success:function(data){
			console.log(data);
			var length = data.departments.length;
			var content = "<select name='id' class='form-control' id='department' onchange='getDirection()'>";
			content += "<option value='null'>--请选择系--</option>";
			for(var i=0;i<length;i++){
				content += "<option value='"+data.departments[i].id+"'>"+data.departments[i].departmentName+"</option>";
			}
			content += "</select>";
			$("#department-content").html(content);
			
		},
		error:function(msg){
			console.log(msg)
		}
	})	
}
var inputNum;
var maxNum;
function getNum(gradeId){
	$.ajax({
		type:"post",
		url:"<%=request.getContextPath()%>/limit/getTeacherNum.do",
		data:{"gradeId":gradeId},
		dataType:"json",
		success:function(data){
			maxNum = data;
			$("#numContainer").html('最大可填人数'+data);			
		},
		error:function(msg){
			console.log(msg)
		}
	})	
}


function check() {
	var inputNum = $("#enableSelect").val();
	if(inputNum > maxNum ) {
		$("#numContainer").html('超出最大可填人数'+maxNum);
		return false;
	} else if(inputNum==0) {
		$("#numContainer").html('请输入正确人数！');
		return false;
	}
	//if($("#grade").val()=="null") {
	//	alert("请选择年级！");
	//	return false;
	//}
		
	return true;
}
</script>
</body>
</html>