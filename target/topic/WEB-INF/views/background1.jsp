<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>毕业设计管理系统</title>
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
	<link rel="shortcut icon" href="<%=request.getContextPath() %>/images/image.ico"/>
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap-theme.min.css">

    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/background.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/scrollerbar.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/responsive.css"/>
    
    
<style>
.lis{
	padding-left:10px;
}
</style>
</head>

<body>
	 <nav class="navbar navbar-default" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" id="show2" data-target="#example1-navbar-collapse">
			<span class="sr-only">切换导航</span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
		</button>
                <a class="navbar-brand" href="#">毕业设计管理系统</a>
            </div>
            <!--
            <div class="collapse navbar-collapse" id="example-navbar-collapse">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#">iOS</a></li>
                    <li><a href="#">SVN</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
					Java <b class="caret"></b>
				</a>
                        <ul class="dropdown-menu">
                            <li><a href="#">jmeter</a></li>
                            <li><a href="#">EJB</a></li>
                            <li><a href="#">Jasper Report</a></li>
                            <li class="divider"></li>
                            <li><a href="#">分离的链接</a></li>
                            <li class="divider"></li>
                            <li><a href="#">另一个分离的链接</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
-->
        </div>
    </nav>

    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-2" id="left" style="overflow:auto">
                <ul id="main-nav" class="nav nav-tabs nav-stacked" style="">
                    <li class="active">
                        <a href="javascript:void(0)" target="frame">
                            <i class="glyphicon glyphicon-th-large"></i> 欢迎！${name }
                        </a>
                    </li>
                    
                    <li id="header">
                        <a href="<%=request.getContextPath() %>/account/mainpage.do" target="frame">
                            <i class="glyphicon glyphicon-home"></i> 主　　页
                        </a>
                    </li>
                    
                    
                    <!-- 管理员或者系主任能够查看 -->
                    <c:if test="${privilege == 1 ||privilege == 2 }">
	                    <li>
	                        <a href="#systemSetting" class="nav-header collapsed" data-toggle="collapse">
	                            <i class="glyphicon glyphicon-th"></i> 信息管理
                            	<span class="pull-right glyphicon glyphicon-chevron-right right"></span>
                            	<span class="pull-right glyphicon glyphicon-chevron-down down"></span>
	                        </a>
	                        <ul id="systemSetting" class="nav nav-list collapse secondmenu" style="height: 0px;">
	                           <c:if test="${privilege == 1 }">
		                            <li class="lis"><a href="<%=request.getContextPath() %>/college/viewCollege.do" target="frame"><i class="glyphicon1 glyphicon-user1"></i> 　学院信息</a></li>
		                            <li class="lis"><a href="<%=request.getContextPath() %>/department/viewDepartment.do" target="frame"><i class="glyphicon1 glyphicon-asterisk1"></i>　系　信息</a></li>
	                            </c:if>
	                            <c:if test="${privilege == 2 }">
		                            <li class="lis"><a href="<%=request.getContextPath() %>/grade/viewGrade.do" target="frame"><i class="glyphicon1 glyphicon-th-list1"></i> 　年级信息</a></li>
		                            <li class="lis"><a href="<%=request.getContextPath() %>/specialty/viewSpecialty.do" target="frame"><i class="glyphicon1 glyphicon-edit1"></i>　专业信息</a></li>
		                            <li class="lis"><a href="<%=request.getContextPath() %>/direction/viewDirection.do" target="frame"><i class="glyphicon1 glyphicon-eye-open1"></i> 　方向信息</a></li>
		                      	    <li class="lis"><a href="<%=request.getContextPath() %>/clazz/viewClazz.do" target="frame"><i class="glyphicon1 glyphicon-eye-open1"></i> 　班级信息</a></li>
	                       		</c:if>
	                        </ul>
	                    </li>
					</c:if>
  <!-- 管理员或者系主任能够查看 的角色信息 -->
  					<c:if test="${privilege == 1 ||privilege == 2 }">
	                    <li>
	                        <a href="#systemSetting1" class="nav-header collapsed" data-toggle="collapse">
	                            <i class="glyphicon glyphicon-user"></i> 角色管理
	                            <span class="pull-right glyphicon glyphicon-chevron-right right"></span>
                            	<span class="pull-right glyphicon glyphicon-chevron-down down"></span>
	                        </a>
	                        <ul id="systemSetting1" class="nav nav-list collapse secondmenu" style="height: 0px;">
	                            <c:if test="${privilege == 2}">
		                            <li><a href="<%=request.getContextPath() %>/student/viewGrade.do" target="frame">　学生　管理</a></li>
		                            <li><a href="<%=request.getContextPath() %>/teacher/viewTeacher.do" target="frame">　教师　管理</a></li>
	                            </c:if>
	                            <c:if test="${privilege == 1}">
		                            <li><a href="<%=request.getContextPath() %>/teacher/viewDean.do" target="frame">　系主任管理</a></li>
		                            <li><a href="<%=request.getContextPath() %>/admin/viewAdministrator.do" target="frame">　管理员管理</a></li>
		                        </c:if>
	                        </ul>
	                    </li>
	                </c:if>
	                
  <!-- 系主任，教师或者学生 能够查看 题目 -->	
  					<c:if test="${privilege == 2 ||privilege == 3 ||privilege == 4}">               
						<li>
	                        <a href="#topics" class="nav-header collapsed" data-toggle="collapse">
	                            <i class="glyphicon glyphicon-briefcase"></i> 题目管理
	                            <span class="pull-right glyphicon glyphicon-chevron-right right"></span>
                            	<span class="pull-right glyphicon glyphicon-chevron-down down"></span>
	                        </a>
	                          <!-- 系主任权限 -->	
	                        <c:if test="${privilege == 2}"> 
		                        <ul id="topics" class="nav nav-list collapse secondmenu" style="height: 0px;">
		                            <li><a href="<%=request.getContextPath() %>/topic/viewGradeTopic.do?state=1" target="frame">　查看题目</a></li>
		                       		<li><a href="<%=request.getContextPath() %>/topic/viewGradeTopic.do?state=2" target="frame">　审核题目</a></li>
		                       		<li><a href="<%=request.getContextPath() %>/teacher/viewGradeSelectedIntent.do?viewType=setTeacherTopicNum" target="frame">　设置教师出题数</a></li>
		                       
		                        </ul>
		                    </c:if>
		                    
		                    <c:if test="${privilege == 3}"> 
		                    	 <ul id="topics" class="nav nav-list collapse secondmenu" style="height: 0px;">
			                         <li><a href="<%=request.getContextPath() %>/teacher/viewGradeSelectedIntent.do?viewType=addTopic" target="frame">　添加题目</a></li>
		                    	 	<li><a href="<%=request.getContextPath() %>/topic/viewGradeTopic.do?state=6" target="frame">　查看题目</a></li>
		                    	 	<li><a href="<%=request.getContextPath() %>/topic/viewGradeTopic.do?state=4" target="frame">　查看待审核题目</a></li>
		                    	 	<li><a href="<%=request.getContextPath() %>/topic/viewGradeTopic.do?state=5" target="frame">　查看未通过题目</a></li>
		                    	 </ul>
		                    </c:if>
		                    
		                    <c:if test="${privilege == 4}"> 
		                    	<ul id="topics" class="nav nav-list collapse secondmenu" style="height: 0px;">
		                    		<li><a href="<%=request.getContextPath() %>/student/viewTopicsStudent.do?state=1" target="frame">　查看题目</a></li>
		                   			<li><a href="<%=request.getContextPath() %>/student/viewIntentionTopic.do" target="frame">　意向题目</a></li>
		                   			<li><a href="<%=request.getContextPath() %>/student/finalSelection.do" target="frame">　最终题目</a></li>
		                   			
		                   		</ul>
		                    </c:if>
	                    </li>
					</c:if>
					
					<c:if test="${privilege == 3 || privilege == 2 }">
						<li>
	                        <a href="#student" class="nav-header collapsed" data-toggle="collapse">
	                            <i class="glyphicon glyphicon-leaf"></i> 学生管理
	                            <span class="pull-right glyphicon glyphicon-chevron-right right"></span>
                            	<span class="pull-right glyphicon glyphicon-chevron-down down"></span>
	                        </a>
	                        <ul id="student" class="nav nav-list collapse secondmenu" style="height: 0px;">
	                            <c:if test="${privilege == 3 }">
		                            <li><a href="<%=request.getContextPath() %>/teacher/viewGradeSelectedIntent.do?viewType=autoSelect" target="frame">　设置自动选择学生</a></li>
		                            <li><a href="<%=request.getContextPath() %>/teacher/viewGradeSelectedIntent.do?viewType=gradeSelect" target="frame">　选择学生</a></li>
		                           	<li><a href="<%=request.getContextPath() %>/teacher/viewGradeSelectedIntent.do?viewType=viewGuideStudent" target="frame">　指导学生</a></li>
	                            </c:if>
	                            <c:if test="${privilege == 2 }">
	                        		<li><a href="<%=request.getContextPath() %>/teacher/viewGradeSelectedIntent.do?viewType=gradeNotSelect" target="frame">　未选题学生</a></li>
	                        		<li><a href="<%=request.getContextPath() %>/teacher/viewGradeSelectedIntent.do?viewType=swapStudentDept" target="frame">　调剂学生</a></li>
	                        		<li><a href="<%=request.getContextPath() %>/teacher/viewGradeSelectedIntent.do?viewType=viewLastSelect" target="frame">　最终选题</a></li>
	                        	</c:if>
	                        </ul>
	                    </li>
					</c:if>
<!-- 学生提交相关文档 -->					
					<c:if test="${privilege == 4 }">
						<li>
	                        <a href="#submit" class="nav-header collapsed" data-toggle="collapse">
	                            <i class="glyphicon glyphicon-tasks"></i> 文档提交
	                            <span class="pull-right glyphicon glyphicon-chevron-right right"></span>
                            	<span class="pull-right glyphicon glyphicon-chevron-down down"></span>
	                        </a>
	                        <ul id="submit" class="nav nav-list collapse secondmenu" style="height: 0px;">
	                        	<li><a href="<%=request.getContextPath() %>/attach/viewOpenReport.do" target="frame">　开题报告</a></li>
		                       	<li><a href="<%=request.getContextPath() %>/attach/viewMidReport.do" target="frame">　中期报告</a></li>
		                   		
		                       	<li><a href="<%=request.getContextPath() %>/attach/viewSubmitThesis.do" target="frame">　论文提交</a></li>
		                   		
	                        </ul>
	                    </li>
					</c:if>
<!-- /学生提交相关文档  -->	

<!-- 评阅管理 -->					
					<c:if test="${privilege == 3}">
						<li>
	                        <a href="#review" class="nav-header collapsed" data-toggle="collapse">
	                            <i class="glyphicon glyphicon-pencil"></i> 评阅管理
	                            <span class="pull-right glyphicon glyphicon-chevron-right right"></span>
                            	<span class="pull-right glyphicon glyphicon-chevron-down down"></span>
	                        </a>
	                        <ul id="review" class="nav nav-list collapse secondmenu" style="height: 0px;">
	                        	<li><a href="<%=request.getContextPath() %>/teacher/viewGradeSelectedIntent.do?viewType=instructorReview" target="frame">　指导老师评阅</a></li>
		                       	<li><a href="<%=request.getContextPath() %>/teacher/viewGradeSelectedIntent.do?viewType=midReview" target="frame">　小组成员评阅</a></li>
		                   		
		                       	<li><a href="<%=request.getContextPath() %>/teacher/viewGradeSelectedIntent.do?viewType=replyResults" target="frame">　组长评阅</a></li>
		                   		
	                        </ul>
	                    </li>
					</c:if>
<!-- /评阅管理  -->	


<!-- 成绩管理 -->					
					<c:if test="${privilege == 2 || privilege == 4 }">
						<li>
	                        <a href="#grade" class="nav-header collapsed" data-toggle="collapse">
	                            <i class="glyphicon glyphicon-certificate"></i> 成绩管理
	                            <span class="pull-right glyphicon glyphicon-chevron-right right"></span>
                            	<span class="pull-right glyphicon glyphicon-chevron-down down"></span>
	                        </a>
	                        <ul id="grade" class="nav nav-list collapse secondmenu" style="height: 0px;">
	                        	<c:if test="${privilege == 2 }">
		                            <li><a href="<%=request.getContextPath() %>/teacher/viewGradeSelectedIntent.do?viewType=viewResults" target="frame">　查看成绩</a></li>
		                        </c:if>
		                         <c:if test="${privilege == 4 }">
		                       		<li><a href="<%=request.getContextPath() %>/student/viewScoreStudent.do" target="frame">　查看成绩</a></li>
		                       	</c:if>
	                        </ul>
	                    </li>
					</c:if>
<!-- /成绩管理 -->	

<!-- 打包下载学生所有附件 -->					
					<c:if test="${privilege == 2 }">
						<li>
	                        <a href="#attach" class="nav-header collapsed" data-toggle="collapse">
	                            <i class="glyphicon glyphicon-file"></i> 附件管理
	                            <span class="pull-right glyphicon glyphicon-chevron-right right"></span>
                            	<span class="pull-right glyphicon glyphicon-chevron-down down"></span>
	                        </a>
	                        <ul id="attach" class="nav nav-list collapse secondmenu" style="height: 0px;">
	                        	<c:if test="${privilege == 2 }">
	                        		<li><a href="<%=request.getContextPath() %>/teacher/viewGradeSelectedIntent.do?viewType=attach" target="frame">　打包下载学生相关附件</a></li>
	                        	</c:if>
	                        </ul>
	                    </li>
					</c:if>
<!-- /打包下载学生所有附件 -->

<!-- 调配管理 -->					
					<c:if test="${privilege == 4 }">
	      			    <li>
	                        <a href="#swapInDepa" class="nav-header collapsed" data-toggle="collapse">
	                            <i class="	glyphicon glyphicon-book"></i> 调配管理
	                            <span class="pull-right glyphicon glyphicon-chevron-right right"></span>
                            	<span class="pull-right glyphicon glyphicon-chevron-down down"></span>
	                        </a>
	                        <ul id="swapInDepa" class="nav nav-list collapse secondmenu" style="height: 0px;">
	                        	<li><a href="<%=request.getContextPath() %>/swap/viewDepartSwap.do" target="frame">　系内调配</a></li>
	                        </ul>
	                    </li>   
	                 </c:if>   
<!-- /调配管理 -->	
	
<!-- 课程管理 -->					
					<c:if test="${privilege == 2 ||privilege == 3}">
	      			    <li>
	                        <a href="#course" class="nav-header collapsed" data-toggle="collapse">
	                            <i class="	glyphicon glyphicon-book"></i> 课程管理
	                            <span class="pull-right glyphicon glyphicon-chevron-right right"></span>
                            	<span class="pull-right glyphicon glyphicon-chevron-down down"></span>
	                        </a>
	                        <ul id="course" class="nav nav-list collapse secondmenu" style="height: 0px;">
	                        	<c:if test="${privilege == 2}">
		                        	<li><a href="<%=request.getContextPath() %>/teacher/viewGradeSelectedIntent.do?viewType=viewGradeCourse" target="frame">　课程成绩</a></li>
	                        	</c:if>
	                        	<c:if test="${privilege == 3}">
		                        	<li><a href="<%=request.getContextPath() %>/teacher/viewGradeSelectedIntent.do?viewType=viewCourse" target="frame">　设置需要查看的课程</a></li>
			                        <li><a href="<%=request.getContextPath() %>/teacher/viewGradeSelectedIntent.do?viewType=viewCourseChoice" target="frame">　查看已选择课程</a></li>
		                        </c:if>	
	                        </ul>
	                    </li>   
	                 </c:if>   
<!-- /课程管理 -->						
					
<!-- 分组管理 -->					
					<c:if test="${privilege == 2 ||privilege == 3}">
	      			    <li>
	                        <a href="#group" class="nav-header collapsed" data-toggle="collapse">
	                            <i class="		glyphicon glyphicon-align-justify"></i> 分组管理
	                            <span class="pull-right glyphicon glyphicon-chevron-right right"></span>
                            	<span class="pull-right glyphicon glyphicon-chevron-down down"></span>
	                        </a>
	                        <ul id="group" class="nav nav-list collapse secondmenu" style="height: 0px;">
	                        	<c:if test="${privilege == 2 }">
	                        		<li><a href="<%=request.getContextPath() %>/teacher/viewGradeSelectedIntent.do?viewType=viewGroup" target="frame">　查看分组</a></li>
	                        	</c:if>
	                        	<c:if test="${privilege == 3 }">
		                        	<li><a href="<%=request.getContextPath() %>/teacher/viewGradeSelectedIntent.do?viewType=viewGroupMember" target="frame">　成员管理</a></li>
		                       		<li><a href="<%=request.getContextPath() %>/teacher/viewGradeSelectedIntent.do?viewType=timeAndPlace" target="frame">　答辩时间地点</a></li>
	                       		</c:if>
	                        </ul>
	                    </li>   
	                 </c:if>   
<!-- /分组管理 -->	
<!-- 分组管理 学生 -->					
					<c:if test="${privilege == 4}">
	      			    <li>
	                        <a href="#group" class="nav-header collapsed" data-toggle="collapse">
	                            <i class="		glyphicon glyphicon-align-justify"></i> 分组管理
	                            <span class="pull-right glyphicon glyphicon-chevron-right right"></span>
                            	<span class="pull-right glyphicon glyphicon-chevron-down down"></span>
	                        </a>
	                        <ul id="group" class="nav nav-list collapse secondmenu" style="height: 0px;">
	                        	<li><a href="<%=request.getContextPath() %>/group/studentGroup.do" target="frame">　查看分组</a></li>
	                        </ul>
	                    </li>   
	                 </c:if>   
<!-- /分组管理 学生 -->	
<!-- 相关文档 -->			
					<c:if test="${privilege == 2 ||privilege == 3 || privilege == 4}">		
						<li>
	                        <a href="#document" class="nav-header collapsed" data-toggle="collapse">
	                            <i class="glyphicon glyphicon-paperclip"></i> 模板文档
	                            <span class="pull-right glyphicon glyphicon-chevron-right right"></span>
	                           	<span class="pull-right glyphicon glyphicon-chevron-down down"></span>
	                        </a>
	                        <ul id="document" class="nav nav-list collapse secondmenu" style="height: 0px;">
	                        	<c:if test="${privilege == 2 ||privilege == 3}">
	                        		<li><a href="<%=request.getContextPath() %>/document/viewDocument.do?type=teacher" target="frame">　下载文档</a></li>
	                        	</c:if>
	                        	<c:if test="${privilege == 4}">
	                        		<li><a href="<%=request.getContextPath() %>/document/viewDocument.do?type=student" target="frame">　下载文档</a></li>
	                        	</c:if>
	                        	<c:if test="${privilege == 2}">
	                        		<li><a href="<%=request.getContextPath() %>/document/goDocument.do" target="frame">　上传文档</a></li>
	                        	</c:if>
	                        </ul>
	                    </li>
	                  </c:if>
<!-- /相关文档  -->	
<!-- 

                    <li id="header">
                        <a href="./plans.html">
                            <i class="glyphicon glyphicon-credit-card"></i> 物料管理
                        </a>
                    </li>

                    <li id="header">
                        <a href="./grid.html">
                            <i class="glyphicon glyphicon-globe"></i> 分发配置
                            <span class="label label-warning pull-right">5</span>
                        </a>
                    </li>

                    <li id="header">
                        <a href="./charts.html">
                            <i class="glyphicon glyphicon-calendar"></i> 图表统计
                        </a>
                    </li>
                    <li id="header">
                        <a href="#">
                            <i class="glyphicon glyphicon-fire"></i> 关于系统
                        </a>
                    </li>
        -->          
        			<c:if test="${privilege == 2 }">
	      			    <li>
	                        <a href="#setting-select" class="nav-header collapsed" data-toggle="collapse">
	                            <i class="glyphicon glyphicon-list-alt"></i> 选课设置
	                            <span class="pull-right glyphicon glyphicon-chevron-right right"></span>
                            	<span class="pull-right glyphicon glyphicon-chevron-down down"></span>
	                        </a>
	                        <ul id="setting-select" class="nav nav-list collapse secondmenu" style="height: 0px;">
	                        	<li><a href="<%=request.getContextPath() %>/setting/viewGradeSetting.do" target="frame">　时间设置</a></li>
	                        </ul>
	                    </li>   
	                 </c:if>   
	                    
	                    
                    <li>
                        <a href="#setting" class="nav-header collapsed" data-toggle="collapse">
                            <i class="glyphicon glyphicon-cog"></i> 设　　置
                            <span class="pull-right glyphicon glyphicon-chevron-right right"></span>
                           	<span class="pull-right glyphicon glyphicon-chevron-down down"></span>
                        </a>
                        <ul id="setting" class="nav nav-list collapse secondmenu" style="height: 0px;">
                        	<c:if test="${privilege != 1 }">
                        		<li><a href="<%=request.getContextPath() %>/update/viewInfo.do" target="frame">　个人信息</a></li>
                            </c:if>
                            <li><a href="<%=request.getContextPath() %>/update/alertpw.do" >　修改密码</a></li>
                            <c:if test="${privilege == 5 }">
	                            <li><a href="<%=request.getContextPath() %>/teacher/viewTeacher.do" target="frame">　数据备份</a></li>
	                       		<li><a href="<%=request.getContextPath() %>/teacher/viewTeacher.do" target="frame">　清空数据</a></li>
	                       	</c:if>
                        </ul>
                    </li>
                    <li id="header">
                        <a href="<%=request.getContextPath() %>/account/loginOut.do">
                            <i class="	glyphicon glyphicon-off"></i> 退出系统
                        </a>
                    </li>

                </ul>
            </div>
            <div class="col-sm-10" id="right">

                <iframe src="<%=request.getContextPath() %>/account/mainpage.do" name="frame" id="winframe" width="100%"  frameborder="0">

                </iframe>
            </div>
        </div>
    </div>

    </ul>



</body>

<script src="<%=request.getContextPath() %>/js/jquery-3.1.1.min.js"></script>
<script src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script>
<!--<script src="dist/js/bootstrap-submenu.min.js"></script>-->

<script type="text/javascript">
	
	var height = window.screen.height - 170;
	$("#winframe").css("height", height);
	$("#left").css("height", height);
	$(".down").hide();
	//$(".right").hide();
    $(".collapsed").click(function() {
        $(this).find(".right").fadeToggle(0);
        $(this).find(".down").fadeToggle(0);
        //$("#left").slideDown("slow");
    })
    
    $("#show2").click(function(){
    	$("#left").fadeToggle(0);
    });
</script>

</html>