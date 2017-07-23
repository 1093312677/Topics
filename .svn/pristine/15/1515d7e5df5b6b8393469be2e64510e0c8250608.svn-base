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
	<div class="panel panel-default" style="margin:0">
	    <div class="panel-body">
	                            查看分组
	    </div>
    </div>
    <div class="container">
    	<div class="row">
	    		<div class="col-md-3">
	    		
	    		</div>
	    		<div class="col-md-6">
	    		<div class="panel panel-default" style="margin-top:25px;">
				    <div class="panel-heading">
				        <h3 class="panel-title">  查看答辩时间地点</h3>
				    </div>
				    <c:if test="${groupTimeAndPlace.leader == 0 }">
				    	<table class="table">
				    		<tr><td colspan="2" align="center">由组长上传时间地点！请查看时间地点</td></tr>
				    		<tr><td>答辩时间</td><td><c:out value="${groupTimeAndPlace.group.timeAndPlace.time }"></c:out></td></tr>
				    		<tr><td>答辩地点</td><td><c:out value="${groupTimeAndPlace.group.timeAndPlace.place }"></c:out></td></tr>
				    	</table>
				    </c:if>
				    <c:if test="${groupTimeAndPlace.leader == 1 }">
				    	<c:if test="${groupTimeAndPlace.group.timeAndPlace.id ==null}">
				    		<input type="hidden" name="check1" id="check1" value='0' class="form-control"/>
				    	</c:if>
				    	<c:if test="${groupTimeAndPlace.group.timeAndPlace.id !=null}">
				    		<input type="hidden" name="check1" id="check1" value='1' class="form-control"/>
				    	</c:if>
				    	<table class="table">
				    		
					    		<input type="hidden" name="groupId" id="groupId" value='<c:out value="${groupTimeAndPlace.group.id }"></c:out>' class="form-control"/>
					    		 <input type="hidden" name="timeId" id="timeId" value='<c:out value="${groupTimeAndPlace.group.timeAndPlace.id }"></c:out>' class="form-control"/>
					    		
					    		 <tr><td>答辩时间</td><td><input type="datetime-local" id="time" name="time" value='<c:out value="${groupTimeAndPlace.group.timeAndPlace.time }"></c:out>' class="form-control"/></td></tr>
					    		 <tr><td>答辩地点</td><td><input type="text" name="place" id="place" value='<c:out value="${groupTimeAndPlace.group.timeAndPlace.place }"></c:out>' class="form-control"/></td></tr>
				    			 <tr><td colspan="2"><input type="button" id="submit" value="保存或更新" class="btn btn-primary"/></td>
					    </table>
				    </c:if>
				    
				</div>
	    	</div>
	    </div>		
    </div>
<script>
	$(function () { $("[data-toggle='tooltip']").tooltip(); });
	$("#submit").click(function(){
		var time = $("#time").val();
		var place = $("#place").val();
		var timeId = $("#timeId").val();
		var groupId = $("#groupId").val();
		var check1 = $("#check1").val();
		if(timeId == ""){
			timeId = 0;
		}
		if(time != "" && place !="") {
			$.ajax({
				type:"post",
				url:"<%=request.getContextPath()%>/group/addTimeAndPlace.do",
				dataType:"json",
				data:{"timeId":timeId,
					  "time":time,
					  "place":place,
					  "groupId":groupId,
					  "check1":check1
					 },
				success:function(data){
					if(data == 1){
						alert("修改成功！");
						window.setTimeout(reload,500);
					} else {
						alert("修改失败！")
					}
				},
				error:function(msg){
					window.setTimeout(reload,500);
				}
			})
		} else {
			alert("请输入");
		}
		
	})
	
	function reload(){
		location.reload()
	}
</script>	
	
</body>
</html>