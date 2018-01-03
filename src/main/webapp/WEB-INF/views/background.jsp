<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>毕业设计管理系统</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="shortcut icon" href="<%=request.getContextPath() %>/images/image.ico"/>
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap-theme.min.css">

    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/scrollerbar.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/responsive.css"/>
    
    <link href="<%=request.getContextPath() %>/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/css/animate.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/css/style.css?v=4.1.0" rel="stylesheet">
    

</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
    <div id="wrapper">
        <!--左侧导航开始-->
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="nav-close"><i class="fa fa-times-circle"></i>
            </div>
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="nav-header">
                        <div class="dropdown profile-element">
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                                    <span class="block m-t-xs" style="font-size:20px;">
                                        <i class="fa fa-area-chart"></i>
                                        <strong class="font-bold">${name }</strong><br>
                                        ${no }
                                    </span>
                                </span>
                            </a>
                        </div>
                        <div class="logo-element">${name }
                        </div>
                    </li>
                    <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
                        <span class="ng-scope">主页</span>
                    </li>
                    <li>
                        <a class="J_menuItem" href="<%=request.getContextPath() %>/account/mainpage.do">
                            <i class="fa fa-home"></i>
                            <span class="nav-label">主页</span>
                        </a>
                    </li>
                    
                    
                    
                    <c:choose>
                    	<c:when test="${privilege == 1 }">
                    		<li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
		                        <span class="ng-scope">信息</span>
		                    </li>
                    		<li>
		                        <a href="#">
		                            <i class="glyphicon glyphicon-th"></i>
		                            <span class="nav-label">信息管理</span>
		                            <span class="fa arrow"></span>
		                        </a>
		                        <ul class="nav nav-second-level">
		                            <li>
		                                <a class="J_menuItem" href="<%=request.getContextPath() %>/college/viewCollege.do">学院信息</a>
		                            </li>
		                            <li>
		                                <a class="J_menuItem" href="<%=request.getContextPath() %>/department/viewDepartment.do">系　信息</a>
		                            </li>
		                        </ul>
		                    </li>
		                    
		                    
		                    <li>
		                        <a href="#">
		                            <i class="glyphicon glyphicon-user"></i>
		                            <span class="nav-label">角色管理</span>
		                            <span class="fa arrow"></span>
		                        </a>
		                        <ul class="nav nav-second-level">
		                            <li>
		                                <a class="J_menuItem" href="<%=request.getContextPath() %>/teacher/viewDean.do">系主任管理</a>
		                            </li>
		                            <li>
		                                <a class="J_menuItem" href="<%=request.getContextPath() %>/admin/viewAdministrator.do">管理员管理</a>
		                            </li>
		                        </ul>
		                    </li>
		                    
		                    
		                    <li>
		                        <a href="#">
		                            <i class="glyphicon glyphicon-cog"></i>
		                            <span class="nav-label">设　　置</span>
		                            <span class="fa arrow"></span>
		                        </a>
		                        <ul class="nav nav-second-level">
		                            <li>
		                                <a class="J_menuItem1" href="<%=request.getContextPath() %>/update/alertpw.do">修改密码</a>
		                            </li>
		                        </ul>
		                    </li>
		                    
		                    <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
		                        <span class="ng-scope">退出</span>
		                    </li>
		                    <li>
		                        <a class="J_menuItem1" href="<%=request.getContextPath() %>/account/loginOut.do">
		                            <i class="glyphicon glyphicon-off"></i>
		                            <span class="nav-label">退出系统</span>
		                        </a>
		                    </li>
                    	</c:when>
                    	<c:when test="${privilege == 2 }">
                    		<li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
		                        <span class="ng-scope">信息</span>
		                    </li>
                    		<li>
		                        <a href="#">
		                            <i class="glyphicon glyphicon-th"></i>
		                            <span class="nav-label">信息管理</span>
		                            <span class="fa arrow"></span>
		                        </a>
		                        <ul class="nav nav-second-level">
	                       		
		                            <li>
		                                <a class="J_menuItem" href="<%=request.getContextPath() %>/grade/viewGrade.do">年级信息</a>
		                            </li>
		                            <li>
		                                <a class="J_menuItem" href="<%=request.getContextPath() %>/specialty/viewSpecialty.do">专业信息</a>
		                            </li>
		                            <%--<li>--%>
		                                <%--<a class="J_menuItem" href="<%=request.getContextPath() %>/direction/viewDirection.do">方向信息</a>--%>
		                            <%--</li>--%>
		                            <li>
		                                <a class="J_menuItem" href="<%=request.getContextPath() %>/clazz/viewClazz.do">班级信息</a>
		                            </li>
		                        </ul>
		                    </li>
		                    
		                    
		                    <li>
		                        <a href="#">
		                            <i class="glyphicon glyphicon-user"></i>
		                            <span class="nav-label">角色管理</span>
		                            <span class="fa arrow"></span>
		                        </a>
		                        <ul class="nav nav-second-level">
		                            <li>
		                                <a class="J_menuItem" href="<%=request.getContextPath() %>/student/viewGrade.do">学生管理</a>
		                            </li>
		                            <li>
		                                <a class="J_menuItem" href="<%=request.getContextPath() %>/teacher/viewTeacher.do">教师管理</a>
		                            </li>
		                        </ul>
		                    </li>
		                    
		                    <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
		                        <span class="ng-scope">题目</span>
		                    </li>
		                    <li>
		                        <a href="#">
		                            <i class="glyphicon glyphicon-briefcase"></i>
		                            <span class="nav-label">题目管理</span>
		                            <span class="fa arrow"></span>
		                        </a>
		                        <ul class="nav nav-second-level">
		                        
		                            <li>
		                                <a class="J_menuItem" href="<%=request.getContextPath() %>/topic/viewGradeTopic.do?state=1">查看题目</a>
		                            </li>
		                            <li>
		                                <a class="J_menuItem" href="<%=request.getContextPath() %>/topic/viewGradeTopic.do?state=2">审核题目</a>
		                            </li>
		                            <%--<li>--%>
		                                <%--<a class="J_menuItem" href="<%=request.getContextPath() %>/teacher/viewGradeSelectedIntent.do?viewType=setTeacherTopicNum">设置教师出题数</a>--%>
		                            <%--</li>--%>
		                        </ul>
		                    </li>
		                    
		                    
		                    <li>
		                        <a href="#">
		                            <i class="glyphicon glyphicon-leaf"></i>
		                            <span class="nav-label">学生管理</span>
		                            <span class="fa arrow"></span>
		                        </a>
		                        <ul class="nav nav-second-level">
		                            <li>
		                                <a class="J_menuItem" href="<%=request.getContextPath() %>/teacher/viewGradeSelectedIntent.do?viewType=gradeNotSelect">未选题学生</a>
		                            </li>
		                            <li>
		                                <a class="J_menuItem" href="<%=request.getContextPath() %>/teacher/viewGradeSelectedIntent.do?viewType=swapStudentDept">调剂学生</a>
		                            </li>
		                            <li>
		                                <a class="J_menuItem" href="<%=request.getContextPath() %>/teacher/viewGradeSelectedIntent.do?viewType=viewLastSelect">最终选题</a>
		                            </li>
		                        </ul>
		                    </li>
		                    
		                    <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
		                        <span class="ng-scope">成绩</span>
		                    </li>
		                    <li>
		                        <a href="#">
		                            <i class="glyphicon glyphicon-certificate"></i>
		                            <span class="nav-label">成绩管理</span>
		                            <span class="fa arrow"></span>
		                        </a>
		                        <ul class="nav nav-second-level">
		                            <li>
		                                <a class="J_menuItem" href="<%=request.getContextPath() %>/teacher/viewGradeSelectedIntent.do?viewType=viewResults">查看成绩</a>
		                            </li>
		                            
		                             <li>
		                                <a class="J_menuItem" href="<%=request.getContextPath() %>/teacher/viewGradeSelectedIntent.do?viewType=viewFormResults">文档情况</a>
		                            </li>
		                        </ul>
		                    </li>
		                    
		                    
		                    <li>
		                        <a href="#">
		                            <i class="glyphicon glyphicon-file"></i>
		                            <span class="nav-label">附件管理</span>
		                            <span class="fa arrow"></span>
		                        </a>
		                        <ul class="nav nav-second-level">
		                        
		                            <li>
		                                <a class="J_menuItem" href="<%=request.getContextPath() %>/teacher/viewGradeSelectedIntent.do?viewType=attach">打包下载学生相关附件</a>
		                            </li>
		                        </ul>
		                    </li>
		                    
		                     <li>
		                        <a href="#">
		                            <i class="glyphicon glyphicon-book"></i>
		                            <span class="nav-label">课程管理</span>
		                            <span class="fa arrow"></span>
		                        </a>
		                        <ul class="nav nav-second-level">
		                        
		                            <li>
		                                <a class="J_menuItem" href="<%=request.getContextPath() %>/teacher/viewGradeSelectedIntent.do?viewType=viewGradeCourse">课程成绩</a>
		                            </li>
		                        </ul>
		                    </li>
		                    
		                    <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
		                        <span class="ng-scope">分组</span>
		                    </li>
		                    <li>
		                        <a href="#">
		                            <i class="glyphicon glyphicon-align-justify"></i>
		                            <span class="nav-label">分组管理</span>
		                            <span class="fa arrow"></span>
		                        </a>
		                        <ul class="nav nav-second-level">
		                        
		                            <li>
		                                <a class="J_menuItem" href="<%=request.getContextPath() %>/teacher/viewGradeSelectedIntent.do?viewType=viewGroup">查看分组</a>
		                            </li>
		                        </ul>
		                    </li>
		                    
		                    <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
		                        <span class="ng-scope">文档</span>
		                    </li>
		                    <li>
		                        <a href="#">
		                            <i class="glyphicon glyphicon-paperclip"></i>
		                            <span class="nav-label">模板文档</span>
		                            <span class="fa arrow"></span>
		                        </a>
		                        <ul class="nav nav-second-level">
		                        
		                            <li>
		                                <a class="J_menuItem" href="<%=request.getContextPath() %>/document/goDocument.do">上传文档</a>
		                            </li>
		                            
		                            <li>
		                                <a class="J_menuItem" href="<%=request.getContextPath() %>/document/viewDocument.do?type=teacher">下载文档</a>
		                            </li>
		                        </ul>
		                    </li>
		                    
		                    
		                    <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
		                        <span class="ng-scope">设置</span>
		                    </li>
		                    
		                    <li>
		                        <a href="#">
		                            <i class="glyphicon glyphicon-list-alt"></i>
		                            <span class="nav-label">时间设置</span>
		                            <span class="fa arrow"></span>
		                        </a>
		                        <ul class="nav nav-second-level">
		                            <li>
		                                <a class="J_menuItem" href="<%=request.getContextPath() %>/setting/viewGradeSetting.do">时间设置</a>
		                            </li>
		                        </ul>
		                    </li>
		                    
		                    <li>
		                        <a href="#">
		                            <i class="glyphicon glyphicon-cog"></i>
		                            <span class="nav-label">设　　置</span>
		                            <span class="fa arrow"></span>
		                        </a>
		                        <ul class="nav nav-second-level">
		                        	<li>
		                                <a class="J_menuItem" href="<%=request.getContextPath() %>/update/viewInfo.do">个人信息</a>
		                            </li>
		                            <li>
		                                <a class="J_menuItem1" href="<%=request.getContextPath() %>/update/alertpw.do">修改密码</a>
		                            </li>
		                        </ul>
		                    </li>
		                    
		                    <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
		                        <span class="ng-scope">退出</span>
		                    </li>
		                    <li>
		                        <a class="J_menuItem1" href="<%=request.getContextPath() %>/account/loginOut.do">
		                            <i class="glyphicon glyphicon-off"></i>
		                            <span class="nav-label">退出系统</span>
		                        </a>
		                    </li>
		                    
                    	</c:when>
                    	<c:when test="${privilege == 3 }">
		                    
		                    <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
		                        <span class="ng-scope">题目</span>
		                    </li>
		                    <li>
		                        <a href="#">
		                            <i class="glyphicon glyphicon-briefcase"></i>
		                            <span class="nav-label">题目管理</span>
		                            <span class="fa arrow"></span>
		                        </a>
		                        <ul class="nav nav-second-level">
		                            <li>
		                                <a class="J_menuItem" href="<%=request.getContextPath() %>/teacher/viewGradeSelectedIntent.do?viewType=addTopic">添加题目</a>
		                            </li>
		                            <li>
		                                <a class="J_menuItem" href="<%=request.getContextPath() %>/topic/viewGradeTopic.do?state=6">查看题目</a>
		                            </li>
		                            <li>
		                                <a class="J_menuItem" href="<%=request.getContextPath() %>/topic/viewGradeTopic.do?state=4">查看待审核题目</a>
		                            </li>
		                            <li>
		                                <a class="J_menuItem" href="<%=request.getContextPath() %>/topic/viewGradeTopic.do?state=5">查看未通过题目</a>
		                            </li>
		                        </ul>
		                    </li>
		                    
		                    
		                    <li>
		                        <a href="#">
		                            <i class="glyphicon glyphicon-leaf"></i>
		                            <span class="nav-label">学生管理</span>
		                            <span class="fa arrow"></span>
		                        </a>
		                        <ul class="nav nav-second-level">
		                            <li>
		                                <a class="J_menuItem" href="<%=request.getContextPath() %>/teacher/viewGradeSelectedIntent.do?viewType=autoSelect">设置自动选择学生</a>
		                            </li>
		                            <li>
		                                <a class="J_menuItem" href="<%=request.getContextPath() %>/teacher/viewGradeSelectedIntent.do?viewType=gradeSelect">选择学生</a>
		                            </li>
		                            <li>
		                                <a class="J_menuItem" href="<%=request.getContextPath() %>/teacher/viewGradeSelectedIntent.do?viewType=viewGuideStudent">指导学生</a>
		                            </li>
		                        </ul>
		                    </li>
		                    
		                    <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
		                        <span class="ng-scope">评阅</span>
		                    </li>
		                    <li>
		                        <a href="#">
		                            <i class="glyphicon glyphicon-pencil"></i>
		                            <span class="nav-label">评阅管理</span>
		                            <span class="fa arrow"></span>
		                        </a>
		                        <ul class="nav nav-second-level">
		                   		
		                            <li>
		                                <a class="J_menuItem" href="<%=request.getContextPath() %>/teacher/viewGradeSelectedIntent.do?viewType=instructorReview">指导老师评阅</a>
		                            </li>
		                            <li>
		                                <a class="J_menuItem" href="<%=request.getContextPath() %>/teacher/viewGradeSelectedIntent.do?viewType=midReview">小组成员评阅</a>
		                            </li>
		                            <li>
		                                <a class="J_menuItem" href="<%=request.getContextPath() %>/teacher/viewGradeSelectedIntent.do?viewType=replyResults">组长评阅</a>
		                            </li>
		                        </ul>
		                    </li>
		                    
		                    
		                    
		                     <li>
		                        <a href="#">
		                            <i class="glyphicon glyphicon-book"></i>
		                            <span class="nav-label">课程管理</span>
		                            <span class="fa arrow"></span>
		                        </a>
		                        <ul class="nav nav-second-level">
		                            <li>
		                                <a class="J_menuItem" href="<%=request.getContextPath() %>/teacher/viewGradeSelectedIntent.do?viewType=viewCourse">设置需要查看的课程</a>
		                            </li>
		                            <li>
		                                <a class="J_menuItem" href="<%=request.getContextPath() %>/teacher/viewGradeSelectedIntent.do?viewType=viewCourseChoice">查看已选择课程</a>
		                            </li>
		                        </ul>
		                    </li>
		                    
		                    <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
		                        <span class="ng-scope">分组</span>
		                    </li>
		                    <li>
		                        <a href="#">
		                            <i class="glyphicon glyphicon-align-justify"></i>
		                            <span class="nav-label">分组管理</span>
		                            <span class="fa arrow"></span>
		                        </a>
		                        <ul class="nav nav-second-level">
		                            <li>
		                                <a class="J_menuItem" href="<%=request.getContextPath() %>/teacher/viewGradeSelectedIntent.do?viewType=viewGroupMember">成员管理</a>
		                            </li>
		                            <li>
		                                <a class="J_menuItem" href="<%=request.getContextPath() %>/teacher/viewGradeSelectedIntent.do?viewType=timeAndPlace">答辩时间地点</a>
		                            </li>
		                        </ul>
		                    </li>
		                    
		                    <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
		                        <span class="ng-scope">文档</span>
		                    </li>
		                    <li>
		                        <a href="#">
		                            <i class="glyphicon glyphicon-paperclip"></i>
		                            <span class="nav-label">模板文档</span>
		                            <span class="fa arrow"></span>
		                        </a>
		                        <ul class="nav nav-second-level">
		                            
		                            <li>
		                                <a class="J_menuItem" href="<%=request.getContextPath() %>/document/viewDocument.do?type=teacher">下载文档</a>
		                            </li>
		                        </ul>
		                    </li>
		                    
		                    
		                    <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
		                        <span class="ng-scope">设置</span>
		                    </li>
		                    
		                    
		                    <li>
		                        <a href="#">
		                            <i class="glyphicon glyphicon-cog"></i>
		                            <span class="nav-label">设　　置</span>
		                            <span class="fa arrow"></span>
		                        </a>
		                        <ul class="nav nav-second-level">
		                        	<li>
		                                <a class="J_menuItem" href="<%=request.getContextPath() %>/update/viewInfo.do">个人信息</a>
		                            </li>
		                            <li>
		                                <a class="J_menuItem1" href="<%=request.getContextPath() %>/update/alertpw.do">修改密码</a>
		                            </li>
		                        </ul>
		                    </li>
		                    
		                    <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
		                        <span class="ng-scope">退出</span>
		                    </li>
		                    <li>
		                        <a class="J_menuItem1" href="<%=request.getContextPath() %>/account/loginOut.do">
		                            <i class="glyphicon glyphicon-off"></i>
		                            <span class="nav-label">退出系统</span>
		                        </a>
		                    </li>
                    	</c:when>
                    	<c:when test="${privilege == 4 }">
                    		<li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
		                        <span class="ng-scope">题目</span>
		                    </li>
		                    <li>
		                        <a href="#">
		                            <i class="glyphicon glyphicon-briefcase"></i>
		                            <span class="nav-label">题目管理</span>
		                            <span class="fa arrow"></span>
		                        </a>
		                        <ul class="nav nav-second-level">
		                            <li>
		                                <a class="J_menuItem" href="<%=request.getContextPath() %>/student/viewTopicsStudent.do?state=1">查看题目</a>
		                            </li>
		                            <li>
		                                <a class="J_menuItem" href="<%=request.getContextPath() %>/student/viewIntentionTopic.do">意向题目</a>
		                            </li>
		                            <li>
		                                <a class="J_menuItem" href="<%=request.getContextPath() %>/student/finalSelection.do">最终题目</a>
		                            </li>
		                        </ul>
		                    </li>
		                    
		                    
		                    
		                    <li>
		                        <a href="#">
		                            <i class="glyphicon glyphicon-tasks"></i>
		                            <span class="nav-label">文档提交</span>
		                            <span class="fa arrow"></span>
		                        </a>
		                        <ul class="nav nav-second-level">
		                            <li>
		                                <a class="J_menuItem" href="<%=request.getContextPath() %>/attach/viewOpenReport.do">开题报告</a>
		                            </li>
		                            <li>
		                                <a class="J_menuItem" href="<%=request.getContextPath() %>/attach/viewMidReport.do">中期报告</a>
		                            </li>
		                            <li>
		                                <a class="J_menuItem" href="<%=request.getContextPath() %>/attach/viewSubmitThesis.do">论文提交</a>
		                            </li>
		                        </ul>
		                    </li>
		                    
		                    
		                    
		                     <li>
		                        <a href="#">
		                            <i class="glyphicon glyphicon-certificate"></i>
		                            <span class="nav-label">成绩管理</span>
		                            <span class="fa arrow"></span>
		                        </a>
		                        <ul class="nav nav-second-level">
		                            <li>
		                                <a class="J_menuItem" href="<%=request.getContextPath() %>/student/viewScoreStudent.do">查看成绩</a>
		                            </li>
		                        </ul>
		                    </li>
		                    
		                    <li>
		                        <a href="#">
		                            <i class="glyphicon glyphicon-book"></i>
		                            <span class="nav-label">调配管理</span>
		                            <span class="fa arrow"></span>
		                        </a>
		                        <ul class="nav nav-second-level">
		                            <li>
		                                <a class="J_menuItem" href="<%=request.getContextPath() %>/swap/viewDepartSwap.do">系内调配</a>
		                            </li>
		                        </ul>
		                    </li>
		                    
		                    
		                    
		                    <li>
		                        <a href="#">
		                            <i class="glyphicon glyphicon-align-justify"></i>
		                            <span class="nav-label">分组管理</span>
		                            <span class="fa arrow"></span>
		                        </a>
		                        <ul class="nav nav-second-level">
		                            <li>
		                                <a class="J_menuItem" href="<%=request.getContextPath() %>/group/studentGroup.do">查看分组</a>
		                            </li>
		                        </ul>
		                    </li>
		                    
		                    <li>
		                        <a href="#">
		                            <i class="glyphicon glyphicon-paperclip"></i>
		                            <span class="nav-label">模板文档</span>
		                            <span class="fa arrow"></span>
		                        </a>
		                        <ul class="nav nav-second-level">
		                            
		                            <li>
		                                <a class="J_menuItem" href="<%=request.getContextPath() %>/document/viewDocument.do?type=student">下载文档</a>
		                            </li>
		                        </ul>
		                    </li>
		                    
		                    
		                    
		                    <li>
		                        <a href="#">
		                            <i class="glyphicon glyphicon-cog"></i>
		                            <span class="nav-label">设　　置</span>
		                            <span class="fa arrow"></span>
		                        </a>
		                        <ul class="nav nav-second-level">
		                        	<li>
		                                <a class="J_menuItem" href="<%=request.getContextPath() %>/update/viewInfo.do">个人信息</a>
		                            </li>
		                            <li>
		                                <a class="J_menuItem1" href="<%=request.getContextPath() %>/update/alertpw.do">修改密码</a>
		                            </li>
		                        </ul>
		                    </li>
		                    
		                    <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
		                        <span class="ng-scope">退出</span>
		                    </li>
		                    <li>
		                        <a class="J_menuItem1" href="<%=request.getContextPath() %>/account/loginOut.do">
		                            <i class="glyphicon glyphicon-off"></i>
		                            <span class="nav-label">退出系统</span>
		                        </a>
		                    </li>
                    	</c:when>
                    	<c:otherwise>
                    	
                    	</c:otherwise>

                    </c:choose>
                   
                    

                </ul>
            </div>
        </nav>

        <!--左侧导航结束-->
        <!--右侧部分开始-->
        <div id="page-wrapper" class="gray-bg dashbard-1">
            <div class="row border-bottom">
                <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                    <div class="navbar-header"><a class="navbar-minimalize minimalize-styl-2 btn btn-info " href="#"><i class="fa fa-bars"></i> </a>
                        <%--<form role="search" class="navbar-form-custom" method="post" action="search_results.html">--%>
                            <%--<div class="form-group">--%>
                                <%--<!-- <input type="text" placeholder="请输入您需要查找的内容 …" class="form-control" name="top-search" id="top-search"> -->--%>
                            <%--</div>--%>
                        <%--</form>--%>
						<button type="button" class="btn btn-default btn-sm" style="margin:10px 0 0 30px;" id="back">
							<span class="glyphicon glyphicon-arrow-left"></span> 返回
						</button>
                    </div>

                    
                </nav>
            </div>
            <div class="row J_mainContent" id="content-main">
                <iframe id="J_iframe" name="frame" width="100%" height="100%" src="<%=request.getContextPath() %>/account/mainpage.do" frameborder="0" data-id="index_v1.html" seamless></iframe>
            </div>
        </div>
        <!--右侧部分结束-->
    </div>
	<script src="<%=request.getContextPath() %>/js/jquery-3.1.1.min.js"></script>
	<script src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script>
    <!-- 全局js -->
    <script src="<%=request.getContextPath() %>/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="<%=request.getContextPath() %>/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="<%=request.getContextPath() %>/js/plugins/layer/layer.min.js"></script>

    <!-- 自定义js -->
    <script src="<%=request.getContextPath() %>/js/hAdmin.js?v=4.1.0"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/index.js"></script>

    <!-- 第三方插件 -->
    <script src="<%=request.getContextPath() %>js/plugins/pace/pace.min.js"></script>
	
	<script>
        $("#back").click(function(){
            window.history.go(-1);
            window.setTimeout(function(){
                frame.window.location.reload();
            },100)

        })

	</script>
<div style="text-align:center;">

</div>
</body>

</html>