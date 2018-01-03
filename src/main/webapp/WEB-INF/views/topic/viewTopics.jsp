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

<!-- alert -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/js/sweetalert/sweetalert.css"/>
<script src="<%=request.getContextPath() %>/js/sweetalert/sweetalert-dev.js"></script>

<style>
	.checkbox-class {
		width:17px;
		height:17px;
		outline:none;
	}
</style>
</head>
<body>
	<div class="panel panel-default" style="margin:0">
	    <div class="panel-body">
	                            查看<span class="glyphicon glyphicon-eye-open">题目</span> 
	           
	          <%--<c:if test="${state == 2 }">--%>
	          		<%--<button class="btn btn-info" style="outline:none;" id="batch">批量通过</button>--%>
	          <%--</c:if>--%>
	          <c:if test="${state == 1 }">
		         <a href="<%=request.getContextPath() %>/topic/exportTopic.do">
		        	 <span class="glyphicon glyphicon-export" style="color:green;float:right;margin-right:80px" data-toggle="tooltip" data-placement="bottom" title="导出题目情况"></span>
		   		 </a>
		   		 <a href="<%=request.getContextPath() %>/topic/exportSubTopic.do">
		        	 <span style="float:right;margin-right:20px;">导出子题目</span>
		   		 </a>
		   	  </c:if>
	    </div>
    </div> 
    <table class="table table-hover table-striped" >
    	<tr class="info">
    		
    		<%--<c:if test="${state == 2 }">--%>
    			<%--<td>--%>
    				<%--<input type="checkbox" class="checkbox-class" id="all-checkbox"/>--%>
    			<%--</td> --%>
    		<%--</c:if>--%>
    		<td >编号</td>
    		<td width=200px>题目名称</td>
    		<td>适用方向</td>
    		<td width=50px>可选学生</td>
    		<td width=50px>已选学生</td>
    		<td>指导老师</td>
    		<td width=90px>题目状态</td>
    		<td>发布时间</td>
    		<td>任务书</td>
    		<c:if test="${state == 1 }">
    			<td>查看学生</td>
    		</c:if>
    		
    		<td>操作</td>
    	</tr>
    	<form action="" id="form1">
    	<c:forEach items="${topics }" var="items">
	    		<tr class="choiceBox">
	    			<%--<c:if test="${state == 2 }">--%>
		    			<%--<td>--%>
		    				<%--<input type="checkbox" name="topicId" class="checkbox-class needcheck" value="${items.id }"/>--%>
		    			<%--</td> --%>
		    		<%--</c:if>--%>
	    			<td><c:out value="${items.id }"></c:out></td>
	    			<td>
	    				<span  data-toggle="tooltip" data-placement="bottom" title="简介：${items.introduce }">
	    					<a href="javascript:void(0)">${items.topicsName }</a>
	   					</span>
	    			</td>
	    			<td>
		    			<c:forEach items="${items.directions }" var="directions">
		    				<c:out value="${directions.directionName }"></c:out><br>
		    			</c:forEach>
	    			</td>
	    			
	    			<td><c:out value="${items.enableSelect }"></c:out></td>
	    			<td><c:out value="${items.selectedStudent }"></c:out></td>
	    			<td>
	    				<a href="<%=request.getContextPath() %>/teacher/viewTeacherOne.do?type=no&id=<c:out value="${items.teacher.id }"></c:out>"><c:out value="${items.teacher.name }"></c:out></a>
	    			</td>
	    			<td>
	    				<c:if test="${items.state == 1}">
	    					<span style="color:green"><span class="glyphicon glyphicon-ok-sign" >通过审核</span></span>
	    				</c:if>
	    				<!-- -->
	    				<c:if test="${items.state == 2}">
	    					<span style="color:#604884"><span class="glyphicon glyphicon-info-sign" >在审核中</span></span>
	    				</c:if>
	    				<c:if test="${items.state == 3}">
	    					<span style="color:red"><span class="glyphicon glyphicon-remove-sign" >未通审核</span></span>
	    				</c:if>
	    				 
	    			</td>
	    			
	    			
	    			<td><c:out value="${items.time }"></c:out></td>
	    			<td>
	    				<c:if test="${items.taskBookName == '' || items.taskBookName == null}">
	    					未上传
	    				</c:if>
	    				<c:if test="${items.taskBookName !='' && items.taskBookName != null }">
	    					<a href="<%=request.getContextPath() %>/document/download.do?randName=${items.taskBookName }&documentName=${items.topicsName }_${items.teacher.name }_任务书_${items.taskBookName }"><span class="glyphicon glyphicon-download-alt" style="color:green;float:right" data-toggle="tooltip" data-placement="bottom" title="下载"></span></a>
	    				</c:if>
	    				
	    			</td>
	    			<c:if test="${state == 1 }">
		    			<td><a href="<%=request.getContextPath() %>/topic/viewStudentSelected.do?topicId=${items.id}">查看学生</a></td>
		    		</c:if>
	    				
	    			<td>
	    				<c:if test="${state == 2 }">
	    					<button type="button" class="btn btn-success btn-sm" style="color:white;outline:none" onclick="audit(<c:out value="${items.id }"></c:out>)">
							    <span class="glyphicon glyphicon-ok"></span>
								<a href="javascript:void(0)" style="color:white" id="audit" > 通过审核</a>
							</button>
							<button type="button" class="btn btn-danger btn-sm" style="outline:none" onclick="noaudit(<c:out value="${items.id }">
							    <span class="glyphicon glyphicon-remove"></span>
								<a href="javascript:void(0)" style="color:white" id="audit" ></c:out>)"> 未通过审核</a>
							</button>
	    				</c:if>
	    				
	    				<c:if test="${state == 1 }">
	    					<a href="javascript:void(0)" onclick="deleteItem(${items.id })"> <span class="glyphicon  glyphicon-trash" style="color:red" data-toggle="tooltip" data-placement="bottom" title="删除"></span></a>
    						<a href="<%=request.getContextPath()%>/topic/update.do?id=${items.id }" > <span class="glyphicon glyphicon-edit" style="color:green;padding-left:20px" data-toggle="tooltip" data-placement="bottom" title="更新"></span></a>
	    				</c:if>
	    			</td>
	    		</tr>
		</c:forEach>
		</form>
    </table>
    
      <!-- 分页开始 -->
    <div class="col-sm-2">
    	<ul class="pagination" style="margin-top:1px">
	    	<!-- 上一页 -->
	    	<c:if test="${pagination.page>1 }">
	    		<li><a href="<%=request.getContextPath() %>/topic/viewTopic.do?gradeId=${gradeId}&state=${state}&page=${pagination.page-2 }">上一页</a></li>
	    	</c:if>
			<c:if test="${pagination.page<=1 }">
				<li class="disabled"><a href="javascript:void(0)">上一页</a></li>
	    	</c:if>
			<!-- 下一页 -->
			<c:if test="${pagination.page<pagination.totlePage }">
	    		<li><a href="<%=request.getContextPath() %>/topic/viewTopic.do?gradeId=${gradeId}&state=${state}&page=${pagination.page }">下一页</a></li>
	    	</c:if>
			<c:if test="${pagination.page>=pagination.totlePage }">
				<li class="disabled"><a href="javascript:void(0)">下一页</a></li>
	    	</c:if>
		</ul>
    </div>
	<div class="col-sm-2">
	<form action="<%=request.getContextPath() %>/topic/viewTopic.do?gradeId=${gradeId}&state=${state}" method="post">
		<div class="input-group" width="200px">
				<input type="hidden" class="form-control" name="pageType" value="1">
				<input type="number" class="form-control" name="page" value="1">
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
    
    
<script>

	$(".choiceBox").click(function(){
		$(this).children("input").attr("checked","true");
	})
//	$("#all-checkbox").click(function(){
//		if(document.getElementById("all-checkbox").checked){
//		    $(".checkbox-class").attr("checked","true");
//		} else {
//			$(".checkbox-class").removeAttr("checked"); 
//		}
//	})
    $("#all-checkbox").click(function(){
        if(document.getElementById("all-checkbox").checked){
            $(".checkbox-class").prop("checked", true); 
        } else {
            $(".checkbox-class").prop("checked", false); 
        }
    })
    
    //点击一行选中
//    $("#tb tr").click(function(){
//        var check = $(this).find("input");
//        if(check.is(':checked')) {
//            check.prop("checked", false);
//        } else {
//            check.prop("checked", true);
//        }
//    })

	$(function () { $("[data-toggle='tooltip']").tooltip(); });
	$("#alert").hide();
	$("#failed").hide();
	$("#success").hide();
	$("#alert-close").click(function(){
		$("#alert").fadeOut();
	})
	
	function audit(id){
        // 直接通过需要系主任输入人数的------版本
        swal({
                title: "确认通过",
                text: "请选择人数 <select class='form-control' id='num'><option>1</option><option>2</option><option>3</option><option>4</option><option>5</option><option>6</option><option>7</option><option>8</option><option>9</option><option>10</option></select>",
                type: "info",
                html: true,
                showCancelButton: true,
                closeOnConfirm: false,
                showLoaderOnConfirm: true,
            },
            function(){
                $.ajax({
                    type:"post",
                    url:"<%=request.getContextPath()%>/teacher/auditTopic.do",
                    data:{"topicId":id,"num":$("#num").val()},
                    dataType:"json",
                    success:function(data){
                        if( data == 1 ) {
                            swal("成功!", "", "success");
                        } else {
                            swal("失败!", "", "error");
                        }

                        window.setTimeout(reload,700);
                    },
                    error:function(msg){
                        console.log(msg)
                    }
                })

            });

        <%--swal({--%>
            <%--title: "确认通过！",--%>
            <%--text: "请设置该题目可选学生人数",--%>
            <%--type: "info",--%>
            <%--showCancelButton: true,--%>
            <%--closeOnConfirm: false,--%>
            <%--animation: "slide-from-top",--%>
            <%--inputPlaceholder: "请输入学生人数"--%>
        <%--}, function(inputValue) {--%>
            <%--if (inputValue === false) {--%>
                <%--return false;--%>
            <%--}--%>
            <%--if (inputValue === "") {--%>
				<%--swal.showInputError("内容不能为空！");--%>
				<%--return false;--%>
            <%--}--%>
            <%--$.ajax({--%>
                <%--type:"post",--%>
                <%--url:"<%=request.getContextPath()%>/teacher/auditTopic.do",--%>
                <%--data:{"topicId":id,"num":inputValue},--%>
                <%--dataType:"json",--%>
                <%--success:function(data){--%>
                    <%--if( data == 1 ) {--%>
                        <%--swal("成功!", "", "success");--%>
                    <%--} else {--%>
                        <%--swal("失败!", "", "error");--%>
                    <%--}--%>

                    <%--window.setTimeout(reload,700);--%>
                <%--},--%>
                <%--error:function(msg){--%>
                    <%--console.log(msg)--%>
                <%--}--%>
            <%--})--%>
        <%--})--%>

	    // 直接通过不需要系主任输入人数的------版本
		<%--swal({--%>
			  <%--title: "确认通过？",--%>
			  <%--text: "",--%>
			  <%--type: "warning",--%>
			  <%--showCancelButton: true,--%>
			  <%--confirmButtonColor: "#DD6B55",--%>
			  <%--confirmButtonText: "　Yes　",--%>
			  <%--closeOnConfirm: false--%>
		<%--},--%>
		<%--function(){--%>
			<%--$.ajax({--%>
				<%--type:"post",--%>
				<%--url:"<%=request.getContextPath()%>/teacher/auditTopic.do",--%>
				<%--data:{"topicId":id},--%>
				<%--dataType:"json",--%>
				<%--success:function(data){--%>
					<%--if( data == 1 ) {--%>
						<%--swal("成功!", "", "success");--%>
					<%--} else {--%>
						<%--swal("失败!", "", "error");--%>
					<%--}--%>
					<%----%>
					<%--window.setTimeout(reload,700);--%>
				<%--},--%>
				<%--error:function(msg){--%>
					<%--console.log(msg)--%>
				<%--}--%>
			<%--})	--%>
		<%--});--%>
		
	}
	function noaudit(id){
		swal({
	        title: "请输入原因！",
	        text: "不通过原因",
	        type: "input",
	        showCancelButton: true,
	        closeOnConfirm: false,
	        animation: "slide-from-top",
	        inputPlaceholder: "请输入原因..."
	    }, function(inputValue) {
	        if (inputValue === false) {
	            return false;
	        }
	        //if (inputValue === "") {
	          //  swal.showInputError("内容不能为空！");
	            //return false;
	        //}
			$.ajax({
				type:"post",
				url:"<%=request.getContextPath()%>/teacher/notAuditTopic.do",
				data:{"topicId":id,"reason":inputValue},
				dataType:"json",
				success:function(data){
					if( data == 1 ) {
						swal("成功!", "", "success");
					} else {
						swal("失败!", "", "error");
					}
					
					window.setTimeout(reload,700);
				},
				error:function(msg){
					console.log(msg)
				}
			})	
	    })
	}
	//删除
	function deleteItem(id){
		swal({
			  title: "取认删除？",
			  text: "删除该选项将会删除与该选项相关联数据！",
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonColor: "#DD6B55",
			  confirmButtonText: "　Yes　",
			  closeOnConfirm: false
		},
		function(){
			$.ajax({
				type:"post",
				url:"<%=request.getContextPath()%>/topic/deleteTopic.do",
				data:{"id":id,
				},
				dataType:"json",
				success:function(data){
					 swal("删除成功!", "", "success");
					 window.setTimeout(reload,700);
					
				},
				error:function(msg){
					swal("删除失败！", "请重试！", "error");
					console.log(msg)
				}
			})
		});
		
	}
	
	//批量通过
	$("#batch").click(function(){
		
		var obj = $(".needcheck"); 
		var s = 0; 
		for(var i=0; i<obj.length; i++){ 
			if(obj[i].checked) 
				s += 1; //如果选中，将value添加到变量s中 
		} 
		if(s <= 0) {
			swal("请选择!", "至少选择一项", "info");
			return false;
		}
		var form = new FormData(document.getElementById("form1"));
		swal({
			  title: "确定批量通过？",
			  text: "！",
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonColor: "#DD6B55",
			  confirmButtonText: "　Yes　",
			  closeOnConfirm: false
		},
		function(){
			$.ajax({
				type:"post",
				url:"<%=request.getContextPath()%>/teacher/batchAuditTopic.do",
				data:form,
				processData:false,
	            contentType:false,
				dataType:"json",
				success:function(data){
					 swal("成功!", "", "success");
					 window.setTimeout(reload,700);
					
				},
				error:function(msg){
					swal("失败！", "请重试！", "error");
					console.log(msg)
				}
			})
		});
	})
	
	function reload(){
		location.reload()
	}
</script>	
	
</body>
</html>