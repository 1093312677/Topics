<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>view class</title>
<!-- alert -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/js/sweetalert/sweetalert.css"/>
<script src="<%=request.getContextPath() %>/js/sweetalert/sweetalert-dev.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.min.css">

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/scrollerbar.css"/>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/common.css"/>


<script src="<%=request.getContextPath() %>/js/jquery-3.1.1.min.js"></script>
<script src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script>


</head>
<body>

	<div class="panel panel-default" style="margin:0">
	    <div class="panel-body">
	                            ${message }
	            <select id="find" style="height:35px;outline:none">
	       		<option value="teachername" selected="selected">教师</option>
	       		<option value="topicName">毕业设计名称</option>
		       </select>
		       <input type="text" id="pk" value="" placeholder="请输入教师名称/题目关键字" onkeypress="entry(event)" style="height:35px;outline:none">
		       <input class="button1" value="查询" type="button" onclick="findTopics()" style="color:white;height:35px;outline:none;border:solid 1px white;width:80px;background-color:#286090">
	    </div>
    </div>
    <div class="t1">
    <table class="table table-hover table-striped" align=left valign=middle>
    	<tr class="info">
    		<td >题目名称</td>
    		<td >可选学生</td>
    		<td>意向学生</td>
    		<td>已选学生</td>
    		<td>指导老师</td>
    		<td >任务书</td>
    		
    		<td>操作</td>
    		<!-- <td>说明</td> -->
    	</tr>
    	<c:forEach items="${topics }" var="items">
    		
   			<tr>
   				
    			<td>
    				<span  data-toggle="tooltip" data-placement="bottom" title="简介：${items.introduce }">
    					<a href="javascript:void(0)">${items.topicsName }</a>
   					</span>
    			</td>
    			<td><c:out value="${items.enableSelect }"></c:out></td>
    			<td><c:out value="${items.intentionNumber }"></c:out></td>
    			<td><c:out value="${items.selectedStudent }"></c:out></td>
    			<td>
    				<a href="<%=request.getContextPath() %>/teacher/viewTeacherOne.do?type=no&id=<c:out value="${items.teacher.id }"></c:out>"><c:out value="${items.teacher.name }"></c:out></a>
    			</td>
    			
    			<td>
    				<c:choose>
    					<c:when test="${items.taskBookName != '' }">
    						<a href="<%=request.getContextPath() %>/upload/<c:out value="${items.taskBookName }"></c:out>">
    							<span class="glyphicon glyphicon-download-alt" style="color:green;float:right" data-toggle="tooltip" data-placement="bottom" title="下载"></span>
    						</a>
    					</c:when>
    					<c:otherwise>
    						未上传
    					</c:otherwise>
    				</c:choose>
    			</td>
    			
    			<td>
    				<c:choose>
    					<c:when test="${selected == 'no'}">
    						<div class="dropdown">
							    <button class="btn btn-default dropdown-toggle" type="button" id="menu1" data-toggle="dropdown">选择为
							    <span class="caret"></span></button>
							    <ul class="dropdown-menu" role="menu" aria-labelledby="menu1">
							    	<li role="presentation"><a role="menuitem" tabindex="-1" href="javascript:void(0)" onclick="volunteer(1,<c:out value="${items.id }"></c:out>)">第一志愿</a></li>
							        <li role="presentation"><a role="menuitem" tabindex="-1" href="javascript:void(0)" onclick="volunteer(2,<c:out value="${items.id }"></c:out>)">第二志愿</a></li>
							        <li role="presentation"><a role="menuitem" tabindex="-1" href="javascript:void(0)" onclick="volunteer(3,<c:out value="${items.id }"></c:out>)">第三志愿</a></li> 
							    </ul>
							</div>
    					</c:when>
    					<c:otherwise>
    						题目已选
    					</c:otherwise>
    				</c:choose>
    				
						    					
    			</td>
    			
    			<!--  <td>第一志愿</td> -->
    		</tr>
    		
		</c:forEach>
    </table>
    
    <!-- 分页开始 -->
	    <div class="col-sm-2">
	    	<ul class="pagination" style="margin-top:1px">
		    	<!-- 上一页 -->
		    	<c:if test="${pagination.page>1 }">
		    		<li><a href="<%=request.getContextPath() %>/student/viewTopicsStudent.do?gradeId=${gradeId }&page=${pagination.page-2 }">上一页</a></li>
		    	</c:if>
				<c:if test="${pagination.page<=1 }">
					<li class="disabled"><a href="javascript:void(0)">上一页</a></li>
		    	</c:if>
				<!-- 下一页 -->
				<c:if test="${pagination.page<pagination.totlePage }">
		    		<li><a href="<%=request.getContextPath() %>/student/viewTopicsStudent.do?gradeId=${gradeId }&page=${pagination.page }">下一页</a></li>
		    	</c:if>
				<c:if test="${pagination.page>=pagination.totlePage }">
					<li class="disabled"><a href="javascript:void(0)">下一页</a></li>
		    	</c:if>
			</ul>
	    </div>
		<div class="col-sm-2">
		<form action="<%=request.getContextPath() %>/student/viewTopicsStudent.do?gradeId=${gradeId }" method="post" onsubmit="return checkPage()">
			<div class="input-group" width="100px">
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
	   <!-- /分页--> 
	   
    </div>
<script>
function entry(event){
	if(event.keyCode==13){
		findTopics();
	}
}
function findTopics(){
	var findType=$("#find").val();
	var primaryKey=$("#pk").val();
	$.ajax({
		type:"post",
		url:"<%=request.getContextPath()%>/topic/findtopics.do",
		data:{"findType":findType,"primaryKey":primaryKey},
		dataType:"json",
		success:function(data){
			var length = data.topics.length;
			if(length==0) alert(length);
			else{
				$(".t1").html('');
				var content1=" <table class='table table-hover table-striped' align=left valign=middle><tr class='info'><td>题目名称</td><td >可选学生</td><td>意向学生</td><td>指导老师</td><td width=200px>题目简介</td><td >任务书</td><td>操作</td></tr>";
				for(var i=0;i<length;i++){
					if(data.topics[i].enableSelect>data.topics[i].selectedStudent){
						if(data.topics[i].state==1){
							content1+="<tr>"
							content1+="<td>"+data.topics[i].topicsName+"</td>";
							content1+="<td>"+data.topics[i].enableSelect+"</td>";
							content1+="<td>"+data.topics[i].intentionNumber+"</td>";
							content1+="<td>";
							content1+="<a href='<%=request.getContextPath() %>/teacher/viewTeacherOne.do?type=no&id="+data.topics[i].teacher.id+"'>"+data.topics[i].teacher.name+"</a>";
							content1+="</td>";
							content1+="<td>"+data.topics[i].introduce+"</td>";
							content1+="<td>";
							if(data.topics[i].taskBookName == ''){
								content1+="未上传";
							}
							else{
								content1+="<a href='<%=request.getContextPath() %>/upload/value='"+data.topics[i].taskBookName+"'>"+"<span class='glyphicon glyphicon-download-alt' style='color:green;float:right' data-toggle='tooltip' data-placement='bottom' title='下载'></span></a>";
							}
							content1+="</td>";
							content1+="<td>";
							if(data.selected=='no'){		
								content1+="<div class='dropdown'> <button class='btn btn-default dropdown-toggle' type='button' id='menu1' data-toggle='dropdown'>选择为<span class='caret'></span></button> <ul class='dropdown-menu' role='menu' aria-labelledby='menu1'>";
								content1+="<li role='presentation'><a role='menuitem' tabindex='-1' href='javascript:void(0)' onclick='volunteer(1,"+data.topics[i].id+")'>第一志愿</a></li>";
								content1+="<li role='presentation'><a role='menuitem' tabindex='-1' href='javascript:void(0)' onclick='volunteer(2,"+data.topics[i].id+")'>第二志愿</a></li>";
								content1+="<li role='presentation'><a role='menuitem' tabindex='-1' href='javascript:void(0)' onclick='volunteer(3,"+data.topics[i].id+")'>第三志愿</a></li>";
								content1+="</ul></div>";						
							}
							else{
								content1+="题目已选";
							}
							content1+="</td>";
							content1+="</tr>"
						}
					}
				}
				content1+="</table>";
				$(".t1").html(content1);
			}				
		},
		error:function(msg){
			console.log(msg)
		}
	})	
}

function findBy(){
	var content1='';
	$('.input').html('');
	if($("#find").val()=="name"){
		content1+="<input type='text' id='pk'>";
		$(".input").html(content1);
	}
	else if(($("#find").val())=="directionId"){
		$.ajax({
			type:"post",
			url:"<%=request.getContextPath()%>/direction/findDirection.do",
			data:{"departmentId":1},
			dataType:"json",
			success:function(data){
				var length = data.directions.length;
				content1+= "<select id='pk'>";
				for(var  i=0;i<length;i++){
					content1+="<option value='"+data.directions[i].id +"' selected='selected'>"+data.directions[i].directionName +"</option>";
				}
				content1+="</select> ";
				$(".input").html(content1);
			},
			error:function(msg){
				console.log(msg)
			}
		})	
		/*content1+= "<select id='findBy'>";
		content1+="<option value='name' selected='selected'>关键字</option>";
		content1+="<option value='directionId'>方向</option>";
		content1+="</select> ";
		$(".input").html(content1);*/
	}
}



	$(function () { $("[data-toggle='tooltip']").tooltip(); });
	var choice1;
	var id1;
	function volunteer(choice,id){
		choice1 = choice;
		id1 = id;
		
		$.ajax({
			type:"post",
			url:"<%=request.getContextPath()%>/student/selectIntentionTopic.do",
			data:{"choice":choice,"id":id},
			dataType:"json",
			success:function(data){
				if(data==1){
					swal("保存成功!", "请继续操作!", "success")
					//$("#content").html("保存成功")
					//$("#alert").fadeIn();
					//$("#success").fadeIn();
					//window.setTimeout(reload,200);
				}else if(data==2){
					
					swal({
						  title: "是否更改当前志愿？",
						  text: "注意将会覆盖之前的！",
						  type: "warning",
						  showCancelButton: true,
						  confirmButtonColor: "#DD6B55",
						  confirmButtonText: "　Yes　",
						  closeOnConfirm: false
					},
					function(){
						$.ajax({
							type:"post",
							url:"<%=request.getContextPath()%>/student/updateIntentionTopic.do",
							data:{"choice":choice1,"id":id1,"type":"1"},
							dataType:"json",
							success:function(data){
								if(data==1){
									swal("更新成功!", "", "success");
									//window.setTimeout(reload,200);
								}else{
									swal("更新失败！", "请重试！", "error");
								}
							},
							error:function(msg){
								console.log(msg)
							}
						})	
					});
					
					//$("#content").html("是否更改当前志愿？<br><span style='font-size:12px;color:red;'>*注意将会覆盖之前的！<span>")
					//$("#alert").fadeIn();
					//$("#failed").fadeIn();
					//$("#alert-confirm").fadeIn();
				}else if(data==3){
					swal({
						  title: "是否更改当前志愿？",
						  text: "注意将会覆盖之前的！",
						  type: "warning",
						  showCancelButton: true,
						  confirmButtonColor: "#DD6B55",
						  confirmButtonText: "　Yes　",
						  closeOnConfirm: false
					},
					function(){
						$.ajax({
							type:"post",
							url:"<%=request.getContextPath()%>/student/updateIntentionTopic.do",
							data:{"choice":choice1,"id":id1,"type":"2"},
							dataType:"json",
							success:function(data){
								if(data==1){
									swal("更新成功!", "", "success");
									//window.setTimeout(reload,200);
								}else{
									swal("更新失败！", "请重试！", "error");
								}
							},
							error:function(msg){
								console.log(msg)
							}
						})	
					});
					///$("#content").html("是否更改为当前选题为此志愿？<br><span style='font-size:12px;color:red;'>*注意将会覆盖之前的！<span>")
					//$("#alert").fadeIn();
					//$("#failed").fadeIn();
					//$("#alert-confirm2").fadeIn();
				}else if(data==4){
					swal("你已经选择当前志愿!", "", "warning");
					//$("#content").html("你已经选择当前志愿")
					//$("#alert").fadeIn();
					//$("#failed").fadeIn();
				}else{
					swal("保存失败！", "请重试！", "error");
					
					///$("#content").html("保存失败！请重试！")
					//$("#alert").fadeIn();
					//$("#failed").fadeIn();
				}
			},
			error:function(msg){
				console.log(msg)
			}
		})	
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	function reload(){
		location.reload()
	}
	//确定键1
	$("#confirm").click(function(){
		hide();
		$.ajax({
			type:"post",
			url:"<%=request.getContextPath()%>/student/updateIntentionTopic.do",
			data:{"choice":choice1,"id":id1,"type":"1"},
			dataType:"json",
			success:function(data){
				if(data==1){
					$("#content").html("更新成功")
					$("#alert").fadeIn();
					$("#success").fadeIn();
					window.setTimeout(reload,200);
				}else{
					$("#content").html("更新失败！请重试！")
					$("#alert").fadeIn();
					$("#failed").fadeIn();
				}
			},
			error:function(msg){
				console.log(msg)
			}
		})	
	})
	//确定键2
	$("#confirm2").click(function(){
		hide();
		$.ajax({
			type:"post",
			url:"<%=request.getContextPath()%>/student/updateIntentionTopic.do",
			data:{"choice":choice1,"id":id1,"type":"2"},
			dataType:"json",
			success:function(data){
				if(data==1){
					$("#content").html("更新成功")
					$("#alert").fadeIn();
					$("#success").fadeIn();
					window.setTimeout(reload,200);
				}else{
					$("#content").html("更新失败！请重试！")
					$("#alert").fadeIn();
					$("#failed").fadeIn();
				}
			},
			error:function(msg){
				console.log(msg)
			}
		})	
	})
	//取消键
	$("#cancel").click(function(){
		$("#alert").fadeOut();
	})
	//取消键
	$("#cancel2").click(function(){
		$("#alert").fadeOut();
	})
	//close
	$("#alert-close").click(function(){
		$("#alert").fadeOut();
	})
</script>	
	
</body>
</html>