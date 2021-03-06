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
	         <span class = "glyphicon glyphicon-zoom-in"></span>查看老师
	         	&nbsp;&nbsp;&nbsp;&nbsp;
	             <select id="queryBy" name="queryBy" style="height:35px;outline:none">
            		<option value="no">按工号查询</option>
            		<option value="name">按姓名查询</option>
              	</select>
  
              	<input type="text" id="primary" style="height:35px;outline:none" >
              	<input type="button" value="查询"   onclick="query()" style="color:white;height:35px;outline:none;border:solid 1px white;width:80px;background-color:#286090">
	          <a href="#" data-toggle="modal" data-target="#import1" id="import"> 
	          
	          	<span class="glyphicon glyphicon-import" style="color:green;float:right;margin-right:80px" data-toggle="tooltip" data-placement="bottom" title="导入"></span>
	          </a>
    		
	          <a href="#" data-toggle="modal" data-target="#myModal" id="add"> <span class="glyphicon glyphicon-plus-sign" style="color:green;float:right;margin-right:40px" data-toggle="tooltip" data-placement="bottom" title="添加"></span></a>
    				
	    </div>
    </div>
    <div class="t1">
	    <table class="table table-hover table-striped" >
	    	<tr class="info">
	    		<td width="100px">工号</td>
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
	    				<a href="javascript:void(0)" 	onclick="editInfo(${items.id})"> <span class="glyphicon glyphicon-edit" style="color:green;padding-left:20px" data-toggle="tooltip" data-placement="bottom" title="更新"></span></a>
	    			</td>
	    			<td width="70px">
    				 <button type="button" class="btn btn-primary" id="resetPW" onclick="resetPW(${items.user.id})">重置密码</button>
    				</td>
	    			
	    		</tr>
			</c:forEach>
	    </table>
	    
	    <!-- 分页开始 -->
	    <div class="col-sm-2">
	    	<ul class="pagination" style="margin-top:1px">
		    	<!-- 上一页 -->
		    	<c:if test="${pagination.page>1 }">
		    		<li><a href="<%=request.getContextPath() %>/teacher/viewTeacher.do?page=${pagination.page-2 }">上一页</a></li>
		    	</c:if>
				<c:if test="${pagination.page<=1 }">
					<li class="disabled"><a href="javascript:void(0)">上一页</a></li>
		    	</c:if>
				<!-- 下一页 -->
				<c:if test="${pagination.page<pagination.totlePage }">
		    		<li><a href="<%=request.getContextPath() %>/teacher/viewTeacher.do?page=${pagination.page }">下一页</a></li>
		    	</c:if>
				<c:if test="${pagination.page>=pagination.totlePage }">
					<li class="disabled"><a href="javascript:void(0)">下一页</a></li>
		    	</c:if>
			</ul>
	    </div>
		<div class="col-sm-2">
		<form action="<%=request.getContextPath() %>/teacher/viewTeacher.do" method="post" onsubmit="return checkPage()">
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
	   </div> 
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
            	<span id="name_tip"></span>
            	<br>
            	<input type="text" name="no" placeholder="老师工号（*必填）" id="no" class="form-control"/>
            	<span id="no_tip"></span>
            	<br>
            	<input type="text" name="position" placeholder="老师职称（*必填）" id="position" class="form-control"/>
            	<span id="position_tip"></span>
            	<br>
            	<select id="sex" name="sex" class="form-control">
            		<option value="男">男</option>
            		<option value="女">女</option>
            	</select>
            	<br>
            	<input type="text" name="telephone" placeholder="老师电话（选填）" id="telephone" class="form-control"/>
            	<span id="tel_tip"></span>
            	<br>
            	<input type="text" name="qq" placeholder="老师QQ（选填）" id="qq" class="form-control"/>
            	<span id="qq_tip"></span>
            	<br>
            	<input type="text" name="email" placeholder="老师邮箱（选填）" id="email" class="form-control"/>
            	<span id="email_tip"></span>
            	<br>
            	<input type="text" name="remark" placeholder="备注（选填）" id="remark" class="form-control"/>
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
					批量导入教师
				</h4>
			</div>
			<div class="modal-body">
				<form action="<%=request.getContextPath() %>/import/importTeacher.do" method="post" enctype="multipart/form-data">
					<div class="input-group">
			            <span class="input-group-addon" style="border-radius: 0;">教师名单文件</span>
			             <input type="file" name="file" class="file" class="form-control" accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet">
			         </div>
					<br>
					<input type="submit" id="submit" value="导入" class="btn btn-primary" style="width:150px"/>
				</form>
				<br>
				<br>
				<a href="<%=request.getContextPath() %>/upload/importTeacher.xlsx">下载示例文件</a>
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
				<label>姓名：</label><input type="text" name="name1" placeholder="教师姓名（*必填）" id="name1" disabled="true" class="form-control"/>
            	<br>
            	<label>工号：</label><input type="text" name="no1" placeholder="教师学号（*必填）" id="no1" disabled="true" class="form-control"/>
            	<br>
            	<label>性别：</label><select id="sex1" name="sex1" class="form-control">
            		<option value="男" id="man" >男</option>
            		<option value="女" id="woman" selected="selected">女</option>
            	</select>
            	<br>
            	<label>职称：</label><input type="text" name="position1" placeholder="教师职称（*必填）" id="position1" disabled="true" class="form-control"/>
            	<br>
            	<label>电话号码：</label><input type="text" name="telephone1" placeholder="教师电话（选填）" id="telephone1" class="form-control"/>
            	<br>
            	<label>QQ：</label><input type="text" name="qq1" placeholder="教师QQ（选填）" id="qq1" class="form-control"/>
            	<br>
            	<label>邮箱：</label><input type="text" name="email1" placeholder="教师邮箱（选填）" id="email1" class="form-control"/>
            	<br>
            	<label>备注：</label><input type="text" name="remark1" placeholder="教师备注（选填）" id="remark1" class="form-control"/>
            	<br>
            	<label>系：</label><input type="text" name="department1" placeholder="教师备注（选填）" id="department1" disabled="true" class="form-control"/>
            	<br>
            	<div class="specialty-content1">
            	
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
	function resetPW(userId){
		if(window.confirm("确定重置密码？")){
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
	function query(){
		if($('#primary').val()==''){
			alert("请输入查询教师姓名或工号");
		}
		else{
			var findBy=$('#queryBy').val();
			var primaryKey=$('#primary').val();
			$.ajax({
				type:"post",
				url:"<%=request.getContextPath()%>/teacher/findTeacherInfo.do",
				data:{"type":"json","findBy":findBy,"primaryKey":primaryKey},
				dataType:"json",
				success:function(data){
					var length = data.teachers.length;
					if(length>0){
						$('.t1').html('');
						var content=" <table class='table table-hover table-striped' id='table'><tr class='info'><td width='100px'>工号</td><td>姓名</td><td width='50px'>性别</td><td>职称</td><td>学院</td><td>系</td><td>备注</td><td>操作</td><td>重置密码</td></tr>";
						for(var i=0;i<length;i++){
							content+="<tr>";
							content+="<td>"+data.teachers[i].no+"</td>";
							content+="<td>"+data.teachers[i].name+"</td>";
							content+="<td>"+data.teachers[i].sex+"</td>";
							content+="<td>"+data.teachers[i].position+"</td>";
							content+="<td>"+data.teachers[i].department.departmentName +"</td>";
							content+="<td>"+data.teachers[i].department.college.collegeName+"</td>";
							content+="<td>"+data.teachers[i].remark+"</td>";
							content+="<td width='70px'>";
							content+="<a href='javascript:void(0)' onclick='deleteItem("+data.teachers[i].id+")'>"+"<span class='glyphicon  glyphicon-trash' style='color:red' data-toggle='tooltip' data-placement='bottom' title='删除'></span></a>";
							content+="<a href='javascript:void(0)' onclick='editInfo("+data.teachers[i].id+")'>"+"<span class='glyphicon glyphicon-edit' style='color:green;padding-left:20px' data-toggle='tooltip' data-placement='bottom' title='更新'></span></a>";
							content+="</td>";
							content+="<td width='70px'>";
							content+="<button type='button' class='btn btn-primary' id='resetPW' onclick='resetPW("+data.teachers[i].user.id+")'>重置密码</button>";
							content+="</td>";
							content+="</tr>";
						}
						$('.t1').html(content);
					}
					else{
						alert("无此教师");
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
		$.ajax({
			type:"post",
			url:"<%=request.getContextPath()%>/teacher/findTeacher1By.do",
			data:{"type":"json","teacherNo":item},
			dataType:"json",
			success:function(data){
				if(data.teacher.sex=='男'){
					document.getElementById('man').selected=true;
					document.getElementById('woman').selected=false;
				}
				if(data.teacher.sex=='女'){
					document.getElementById('woman').selected=true;
					document.getElementById('man').selected=false;
				}
				$('#email1').val(data.teacher.email);
				$('#position1').val(data.teacher.position);
				$('#teaid').val(data.teacher.id);
				$('#qq1').val(data.teacher.qq);
				$('#telephone1').val(data.teacher.telephone);
				$('#remark1').val(data.teacher.remark);
				$('#name1').val(data.teacher.name);
				$('#no1').val(data.teacher.no);
				$('#department1').val(data.teacher.department.departmentName);
			},
			error:function(msg){
				console.log(msg)
			}
		})
		$('#update').modal('show');
	}
	function updateInfo(){
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
		if(name!="null"&&no!="null"&&sex!="null"&&position!="null"){
			$.ajax({
				type:"post",
				url:"<%=request.getContextPath()%>/teacher/updateInfo.do",
				data:{
					  "id":id,
					  "name":name,
					  "no":no,	 
					  "sex":sex,
					  "position":position,
					  "qq":qq,	 
					  "telephone":telephone,
					  "email":email,
					  "remark":remark
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
		getColleges();//获取学院信息
		
	})
	
	$("#addConfirm").click(function(){
		//获取学生信息
		var name = $("#name").val();
		var no = $("#no").val();
		var sex = $("#sex").val();
		var qq = $("#qq").val();
		var telephone = $("#telephone").val();
		var remark = $("#remark").val();
		var position = $("#position").val();
		var email = $("#email").val();
		
		$.ajax({
			type:"post",
			url:"<%=request.getContextPath()%>/teacher/addTeacher.do",
			data:{"name":name,
				  "no":no,	 
				  "sex":sex,	 
				  "qq":qq,	 
				  "telephone":telephone,
				  "position":position,
				  "email":email,
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
		
		
	})
	
	
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
	
	function reload(){
		location.reload()
	}
	$("#submit").click(function(){
		$("#wait").show();
	})
</script>	
<script type="text/javascript"> //验证手机号码
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