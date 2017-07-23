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
	                            查看未选题学生 
	         <a href="<%=request.getContextPath() %>/teachStu/exportNotSelectedStudent.do">
	        	 <span class="glyphicon glyphicon-export" style="color:green;float:right;margin-right:80px" data-toggle="tooltip" data-placement="bottom" title="导出"></span>
	   		 </a>
	   		 <a href="<%=request.getContextPath() %>/teachStu/automaticSelection.do" style="float:right;margin-right:80px">
		   		 <button class="btn btn-primary">
		   		 	一键选题
		   		 </button>
		     </a>              
	    </div>
    </div>
    <table class="table table-hover table-striped" >
    	<tr class="info">
    		<td width="100px">学号</td>
    		<td>姓名</td>
    		<td>班级</td>
    		<td>方向</td>
    		<td>专业</td>
    		
    		<td>年级</td>
    		<td>是否服从系内调配</td>
    	</tr>
    	<c:forEach items="${students }" var="items">
    		<c:if test="${items.topics == null }">
    			<tr>
	    			<td><c:out value="${items.no }"></c:out></td>
	    			<td><c:out value="${items.name }"></c:out></td>
	    			<td><c:out value="${items.clazz.className }"></c:out></td>
	    			<td><c:out value="${items.clazz.direction.directionName }"></c:out></td>
	    			<td><c:out value="${items.clazz.direction.spceialty.specialtyName }"></c:out></td>
	    			<td><c:out value="${items.clazz.direction.spceialty.grade.gradeName }"></c:out></td>
	    			<td>
	    				<c:if test="${items.swapInDepa == 0 }">
		    				是
		    			</c:if>
		    			<c:if test="${items.swapInDepa == 1 }">
		    				否
		    			</c:if>
	    			</td>
	    		</tr>
    		</c:if>
		</c:forEach>
    </table>
 	<!-- 分页开始 -->
	    <div class="col-sm-2">
	    	<ul class="pagination" style="margin-top:1px">
		    	<!-- 上一页 -->
		    	<c:if test="${pagination.page>1 }">
		    		<li><a href="<%=request.getContextPath() %>/teacher/viewNotSelected.do?gradeId=${gradeId }&page=${pagination.page-2 }">上一页</a></li>
		    	</c:if>
				<c:if test="${pagination.page<=1 }">
					<li class="disabled"><a href="javascript:void(0)">上一页</a></li>
		    	</c:if>
				<!-- 下一页 -->
				<c:if test="${pagination.page<pagination.totlePage }">
		    		<li><a href="<%=request.getContextPath() %>/teacher/viewNotSelected.do?gradeId=${gradeId }&page=${pagination.page }">下一页</a></li>
		    	</c:if>
				<c:if test="${pagination.page>=pagination.totlePage }">
					<li class="disabled"><a href="javascript:void(0)">下一页</a></li>
		    	</c:if>
			</ul>
	    </div>
		<div class="col-sm-2">
		<form action="<%=request.getContextPath() %>/teacher/viewNotSelected.do?gradeId=${gradeId }" method="post" onsubmit="return checkPage()">
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
<script>
	$(function () { $("[data-toggle='tooltip']").tooltip(); });
	
</script>	
	
</body>
</html>