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
	         查看学院 
	      	 <a href="#" data-toggle="modal" data-target="#myModal" id="add"> <span class="	glyphicon glyphicon-plus-sign" style="color:green;float:right" data-toggle="tooltip" data-placement="bottom" title="添加"></span></a>
    			
	    </div>
    </div>
    <table class="table table-hover" >
    	<tr class="info">
    		<td width="70px">编号</td>
    		<td>学院</td>
    		<td>操作</td>
    	</tr>
    	<c:forEach items="${colleges }" var="items">
    		<tr>
    			<td><c:out value="${items.id }"></c:out></td>
    			<td><c:out value="${items.collegeName }"></c:out></td>
    			<td width="70px">
    				<a href="javascript:void(0)" onclick="deleteItem(${items.id })" > <span class="glyphicon  glyphicon-trash" style="color:red" data-toggle="tooltip" data-placement="bottom" title="删除"></span></a>
    				<a href="<%=request.getContextPath()%>/college/findCollege.do?id=${items.id }" > <span class="glyphicon glyphicon-edit" style="color:green;padding-left:20px" data-toggle="tooltip" data-placement="bottom" title="更新"></span></a>
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
                <h4 class="modal-title" id="myModalLabel">添加学院</h4>
            </div>
            <div class="modal-body">
            	<input type="text" id="collegeName" name="collegeName" class="form-control" placeholder="学院名称"/>
            	<div class="panel panel-success" style="margin-top:30px;width:50%;margin-left:25%" id="message">
	 			   <div class="panel-body">
	 			   		保存成功！
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
		$("#message").hide();//隐藏提示信息
		
	})
	
	
	$("#addConfirm").click(function(){
		var collegeName=$("#collegeName").val();
		$.ajax({
			type:"post",
			url:"<%=request.getContextPath()%>/college/addCollege.do",
			data:{"collegeName":collegeName},
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
			location.href="<%=request.getContextPath()%>/college/deleteCollege.do?id="+id;
		}
	}
</script>	

</body>
</html>