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

<!-- alert -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/js/sweetalert/sweetalert.css"/>
<script src="<%=request.getContextPath() %>/js/sweetalert/sweetalert-dev.js"></script>
</head>
<body>
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
					<form id="form" action="" method="post" enctype="multipart/form-data" >
						<br>
						<div class="input-group">
				            <span class="input-group-addon" style="border-radius: 0;">题目　名称<span style="color:red;margin-top:13px;">*</span></span>
				            <input type="text" name="topicsName" id="topicsName" class="form-control" placeholder="题目名称（必填）" required>
				        </div>
				        <br>
				        <br>
				        <input type="hidden" value="${gradeId }" name="gradeId"/>
				         <div class="input-group">
				            <span class="input-group-addon" style="border-radius: 0;">适用于专业<span style="color:red;margin-top:13px;">*</span></span>
				            <div id="direction-content" style="z-index:1">
				            	<select id="direction" name="directionIds" required class="selectpicker show-tick form-control" multiple data-live-search="true">
		                        </select>
				            </div>
				        </div>
				        <%--<br>--%>
				         <%--<div class="input-group">--%>
				            <%--<span class="input-group-addon" style="border-radius: 0;">可选学生数<span style="color:red;margin-top:13px;">*</span></span>--%>
				            <%--<input type="number" name="enableSelect" id='enableSelect' class="form-control" placeholder="可选学生数（必填）" required>--%>
				        	<%--<span style="color:#06c290;margin-left:20px" id="numContainer"></span>--%>
				        <%--</div>--%>
				        <br>
				         <div class="input-group">
				            <span class="input-group-addon" style="border-radius: 0;">文件　 上传</span>
				            <input type="file" name="file" class="file" class="form-control" placeholder="文件">
				        </div>
				        <br>
				        
				        <div class="input-group">
				            <span class="input-group-addon" style="border-radius: 0;">题目  　简介</span></span>
				            <textarea class="form-control"   rows="5" name="introduce"></textarea>
				        </div>
				        <br>
				        <input type="button" value="保存" class="btn btn-success" onclick="check()" style="width:150px"/>
				        
				        
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
	// var inputNum = $("#enableSelect").val();
	// if(inputNum > maxNum ) {
	// 	$("#numContainer").html('超出最大可填人数'+maxNum);
	// 	return false;
	// } else if(inputNum==0) {
	// 	$("#numContainer").html('请输入正确人数！');
	// 	return false;
	// }
	var direction = $("#direction").val();
	var topicsName = $("#topicsName").val();
	var enableSelect = $("#enableSelect").val();
	if(enableSelect == "" || topicsName == "" || enableSelect == "" ) {
		swal("请填写必填项！", "请重试！", "warning");
		return false;
	}
	
	swal({
		  title: "确认提交？",
		  text: "",
		  type: "warning",
		  showCancelButton: true,
		  confirmButtonColor: "#DD6B55",
		  confirmButtonText: "　Yes　",
		  closeOnConfirm: false,
		  showLoaderOnConfirm: true, 
	},
	function(){
		var form = new FormData(document.getElementById("form"));
		$.ajax({
			type:"post",
			url:"<%=request.getContextPath()%>/topic/addTopic.do",
			data:form,
			dataType:"json",
			processData:false,
            contentType:false,
			success:function(data){
				if(data.result==1){
					swal("提交成功!", "", "success");
					window.setTimeout("location.reload()",700);
				}else{
					swal("提交失败！", "请重试！", "error");
				}
			},
			error:function(msg){
				swal("提交失败！", "请重试！", "error");
				window.setTimeout("location.reload()",700);
			}
		})	
	});
	
	return true;
}
</script>
</body>
</html>