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
	                            论文
	    </div>
    </div>
    <div class="container">
    	<div class="row">
    		<div class="col-md-3">
    		
    		</div>
    		<div class="col-md-6">
    		<div class="panel panel-default" style="margin-top:25px;">
			    <div class="panel-heading">
			        <h3 class="panel-title">  论文</h3>
			    </div>
			    <table class="table">
			    	<c:if test="${isNow == false }">
			    		 <tr><td colspan="2" align="center">现在不是提交时间！</td></tr>
			    	</c:if>
			   <!-- 判断是否提交文档 -->
			    	<c:if test="${fileName != null }">
		    		 	<tr>
		    		 		<td><a href="<%=request.getContextPath() %>/upload/${fileName }">下载</a></td>
		    		 	</tr>
		    		 </c:if>
		    		 <c:if test="${fileName == null }">
		    		 	<tr>
		    		 		<td colspan="2" align="center">尚未提交文档！</td>
		    		 	</tr>
		    		 </c:if>
			    <!-- /判断是否提交文档 -->
			    	<c:if test="${isNow == true }">
			    		 <tr>
			    		 	<td>
			    		 		<form action="<%=request.getContextPath() %>/attach/addSubmitThesis.do" method="post" enctype="multipart/form-data">
									 <div class="input-group">
							            <span class="input-group-addon" style="border-radius: 0;"> 论文</span>
							           	<input type="file" name="file" required class="form-control file" placeholder="文件">
							          </div>
							          <br>
							         <input type="submit" value="提　　交" class="btn btn-primary"/>
						        </form>
							</td>
						</tr>	
			    		
			    	</c:if>
			   	    
			    </table>
			</div>
    	</div>
     </div>		
   </div>
<script>
	$(function () { $("[data-toggle='tooltip']").tooltip(); });
</script>	
	
</body>
</html>