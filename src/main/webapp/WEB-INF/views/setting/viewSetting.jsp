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
	
	<div class="container">
		<div class="row">
			<div class="col-md-3">
			</div>
			<div class="col-md-6">
				<!--  enctype="multipart/form-data" -->
				<form action="<%=request.getContextPath()%>/setting/saveOrUpdateSetting.do" method="post" onsubmit="return check()">
					<br>
					<input type="hidden" name="gradeId" value="${gradeId }"/>
					<input type="hidden" name="id" value="${settings.id }"/>
					<span style="font-size:10px;color:gray;margin-left:-40px;">题目提交</span>
					<div class="input-group">
			            <span class="input-group-addon" style="border-radius: 0;">题目提交开始时间　　<span style="color:red;margin-top:13px;">*</span></span>
			            <input type="datetime-local" name="commitTopicStartTime" id="commitTopicStartTime" class="form-control" required value='${settings.commitTopicStartTime }'>
			        </div>
			        <br>
			        <div class="input-group">
			            <span class="input-group-addon" style="border-radius: 0;">题目提交结束时间　　<span style="color:red;margin-top:13px;">*</span></span>
			            <input type="datetime-local" name="commitTopicEndTime" id="commitTopicEndTime" class="form-control"  required value='${settings.commitTopicEndTime }'>
			        </div>
			        <br>
			        
<!-- 第一轮选题设置开始 -->        
			        <span style="font-size:10px;color:gray;margin-left:-40px;">第一轮选题</span>
			        <div class="input-group">
			            <span class="input-group-addon" style="border-radius: 0;">第一轮选题　开始时间<span style="color:red;margin-top:13px;">*</span></span>
			            <input type="datetime-local" name="oneSelectStartTime" id="oneSelectStartTime" class="form-control"  required value='${settings.oneSelectStartTime }'>
			        </div>
			        <br>
			        <div class="input-group">
			            <span class="input-group-addon" style="border-radius: 0;">第一轮选题　结束时间<span style="color:red;margin-top:13px;">*</span></span>
			            <input type="datetime-local" name="oneSelectEndTimeOne" id="oneSelectEndTimeOne" class="form-control"  required value='${settings.oneSelectEndTimeOne }'>
			        </div>
			        <br>
			        <div class="input-group">
			            <span class="input-group-addon" style="border-radius: 0;">教师录取志愿起始时间<span style="color:red;margin-top:13px;">*</span></span>
			            <input type="datetime-local" name="oneAdmissionStartTime" id="oneAdmissionStartTime" class="form-control"  required value='${settings.oneAdmissionStartTime }'>
			        </div>
			        <br>
			        
			         <div class="input-group">
			            <span class="input-group-addon" style="border-radius: 0;">第一志愿录取截止时间<span style="color:red;margin-top:13px;">*</span></span>
			            <input type="datetime-local" name="oneFirstChoiceDeadline" id="oneFirstChoiceDeadline" class="form-control"  required value='${settings.oneFirstChoiceDeadline }'>
			        </div>
			        <br>
			         <div class="input-group">
			            <span class="input-group-addon" style="border-radius: 0;">第二志愿录取截止时间<span style="color:red;margin-top:13px;">*</span></span>
			            <input type="datetime-local" name="oneSecondChoiceDeadline" id="oneSecondChoiceDeadline" class="form-control"  required value='${settings.oneSecondChoiceDeadline }'>
			        </div>
			        <br>
			         <div class="input-group">
			            <span class="input-group-addon" style="border-radius: 0;">第三志愿录取截止时间<span style="color:red;margin-top:13px;">*</span></span>
			            <input type="datetime-local" name="oneThirdChoiceDeadline" id="oneThirdChoiceDeadline" class="form-control"  required value='${settings.oneThirdChoiceDeadline }'>
			        </div>
			        <br>
<!-- 第一轮选题设置结束 -->  

<!-- 第二轮选题设置开始 -->        
			        <span style="font-size:10px;color:gray;margin-left:-40px;">第二轮选题</span>
			        <div class="input-group">
			            <span class="input-group-addon" style="border-radius: 0;">第二轮选题　开始时间<span style="color:red;margin-top:13px;">*</span></span>
			            <input type="datetime-local" name="twoSelectStartTime" id="twoSelectStartTime" class="form-control"  required value='${settings.twoSelectStartTime }'>
			        </div>
			        <br>
			        <div class="input-group">
			            <span class="input-group-addon" style="border-radius: 0;">第二轮选题　结束时间<span style="color:red;margin-top:13px;">*</span></span>
			            <input type="datetime-local" name="twoSelectEndTimeOne" id="twoSelectEndTimeOne" class="form-control"  required value='${settings.twoSelectEndTimeOne }'>
			        </div>
			        <br>
			        <div class="input-group">
			            <span class="input-group-addon" style="border-radius: 0;">教师录取志愿起始时间<span style="color:red;margin-top:13px;">*</span></span>
			            <input type="datetime-local" name="twoAdmissionStartTime" id="twoAdmissionStartTime" class="form-control"  required value='${settings.twoAdmissionStartTime }'>
			        </div>
			        <br>
			        
			         <div class="input-group">
			            <span class="input-group-addon" style="border-radius: 0;">第一志愿录取截止时间<span style="color:red;margin-top:13px;">*</span></span>
			            <input type="datetime-local" name="twoFirstChoiceDeadline" id="twoFirstChoiceDeadline" class="form-control"  required value='${settings.twoFirstChoiceDeadline }'>
			        </div>
			        <br>
			         <div class="input-group">
			            <span class="input-group-addon" style="border-radius: 0;">第二志愿录取截止时间<span style="color:red;margin-top:13px;">*</span></span>
			            <input type="datetime-local" name="twoSecondChoiceDeadline" id="twoSecondChoiceDeadline" class="form-control"  required value='${settings.twoSecondChoiceDeadline }'>
			        </div>
			        <br>
			         <div class="input-group">
			            <span class="input-group-addon" style="border-radius: 0;">第三志愿录取截止时间<span style="color:red;margin-top:13px;">*</span></span>
			            <input type="datetime-local" name="twoThirdChoiceDeadline" id="twoThirdChoiceDeadline" class="form-control"  required value='${settings.twoThirdChoiceDeadline }'>
			        </div>
			        <br>
<!-- 第二轮选题设置结束 -->  


<!-- 开题报告设置开始 --> 	
					<span style="font-size:10px;color:gray;margin-left:-40px;">开题报告</span>		         
			        <div class="input-group">
			            <span class="input-group-addon" style="border-radius: 0;">开题报告提交开始时间<span style="color:red;margin-top:13px;">*</span></span>
			            <input type="datetime-local" name="openReportStartTime" id="openReportStartTime" class="form-control"  required value='${settings.openReportStartTime }'>
			        </div>
			        <br>
			        <div class="input-group">
			            <span class="input-group-addon" style="border-radius: 0;">开题报告提交结束时间<span style="color:red;margin-top:13px;">*</span></span>
			            <input type="datetime-local" name="openReportEndTime" id="openReportEndTime" class="form-control"  required value='${settings.openReportEndTime }'>
			        </div>
			        <br>
<!-- /开题报告设置 -->	 


<!-- 中期检查设置开始 -->        
			        <span style="font-size:10px;color:gray;margin-left:-40px;">中期报告</span>
			         <div class="input-group">
			            <span class="input-group-addon" style="border-radius: 0;">中期报告提交开始时间<span style="color:red;margin-top:13px;">*</span></span>
			            <input type="datetime-local" name="midReportStartTime" id="midReportStartTime" class="form-control"  required value='${settings.midReportStartTime }'>
			        </div>
			        <br>
			        <div class="input-group">
			            <span class="input-group-addon" style="border-radius: 0;">中期报告提交结束时间<span style="color:red;margin-top:13px;">*</span></span>
			            <input type="datetime-local" name="midReportEndTime" id="midReportEndTime" class="form-control"  required value='${settings.midReportEndTime }'>
			        </div>
			        <br>
<!-- /中期检查设置 --> 
<!-- 指导教师评阅 -->  
					<span style="font-size:10px;color:gray;margin-left:-40px;">指导教师评阅</span>
					<div class="input-group">
			            <span class="input-group-addon" style="border-radius: 0;">指导教师评阅开始时间<span style="color:red;margin-top:13px;">*</span></span>
			            <input type="datetime-local" name="instructorReviewStartTime" id="instructorReviewStartTime" class="form-control"  required value='${settings.instructorReviewStartTime }'>
			        </div>
			        <br>
			        
			         <div class="input-group">
			            <span class="input-group-addon" style="border-radius: 0;">指导教师评阅结束时间<span style="color:red;margin-top:13px;">*</span></span>
			            <input type="datetime-local" name="instructorReviewEndTime" id="instructorReviewEndTime" class="form-control"  required value='${settings.instructorReviewEndTime }'>
			        </div>
			        <br>
<!-- /指导教师评阅-->
<!-- 毕业论文提交 -->
					<span style="font-size:10px;color:gray;margin-left:-40px;">毕业论文提交</span>
			        <div class="input-group">
			            <span class="input-group-addon" style="border-radius: 0;">毕业论文提交开始时间<span style="color:red;margin-top:13px;">*</span></span>
			            <input type="datetime-local" name="commitAttachStartTime" id="commitAttachStartTime" class="form-control"  required value='${settings.commitAttachStartTime }'>
			        </div>
			        <br>
			        <div class="input-group">
			            <span class="input-group-addon" style="border-radius: 0;">毕业论文提交结束时间<span style="color:red;margin-top:13px;">*</span></span>
			            <input type="datetime-local" name="commitAttachEndTime" id="commitAttachEndTime" class="form-control"  required value='${settings.commitAttachEndTime }'>
			        </div>
			        <br>
<!-- /毕业论文提交 -->			        
		
<!-- 小组评阅 -->  
					<span style="font-size:10px;color:gray;margin-left:-40px;">小组评阅</span>
			        <div class="input-group">
			            <span class="input-group-addon" style="border-radius: 0;">教师评阅开始时间　　<span style="color:red;margin-top:13px;">*</span></span>
			            <input type="datetime-local" name="midReviewStartTime" id="midReviewStartTime" class="form-control"  required value='${settings.midReviewStartTime }'>
			        </div>
			        <br>
			        
			         <div class="input-group">
			            <span class="input-group-addon" style="border-radius: 0;">教师评阅结束时间　　<span style="color:red;margin-top:13px;">*</span></span>
			            <input type="datetime-local" name="midReviewEndTime" id="midReviewEndTime" class="form-control"  required value='${settings.midReviewEndTime }'>
			        </div>
			        <br>
<!-- /小组评阅-->

<!-- 答辩置结束 -->
					<span style="font-size:10px;color:gray;margin-left:-40px;">答辩</span>
					<div class="input-group">
						<span class="input-group-addon" style="border-radius: 0;">答辩开始时间　　　　<span style="color:red;margin-top:13px;">*</span></span>
						<input type="datetime-local" name="defenceStartTime" id="defenceStartTime" class="form-control"  required value='${settings.defenceStartTime }'>
					</div>
					<br>

					<div class="input-group">
						<span class="input-group-addon" style="border-radius: 0;">答辩结束时间　　　　<span style="color:red;margin-top:13px;">*</span></span>
						<input type="datetime-local" name="defenceEndTime" id="defenceEndTime" class="form-control"  required value='${settings.defenceEndTime }'>
					</div>
					<br>
					<!-- 答辩设置结束 -->

<!-- 答辩结果提交 -->  
					<span style="font-size:10px;color:gray;margin-left:-40px;">答辩结果提交</span>
			        <div class="input-group">
			            <span class="input-group-addon" style="border-radius: 0;">答辩结果提交开始时间<span style="color:red;margin-top:13px;">*</span></span>
			            <input type="datetime-local" name="replyResultsStartTime" id="replyResultsStartTime" class="form-control"  required value='${settings.replyResultsStartTime }'>
			        </div>
			        <br>
			        
			         <div class="input-group">
			            <span class="input-group-addon" style="border-radius: 0;">答辩结果提交结束时间<span style="color:red;margin-top:13px;">*</span></span>
			            <input type="datetime-local" name="replyResultsEndTime" id="replyResultsEndTime" class="form-control"  required value='${settings.replyResultsEndTime }'>
			        </div>
			        <br>
<!-- /答辩结果提交-->	

        

			        <input type="submit" value="保存或更新" class="btn btn-success" style="width:150px"/>
		        
				</form>
			</div>
			<div class="col-md-3">
			</div>
		</div>
	</div>
	
<script>
window.setTimeout(hide,2000);
function hide(){
	$(".alert").hide();
}
//提交题目时间判断
function commitTopic(){
	if($("#commitTopicStartTime").val() >= $("#commitTopicEndTime").val()){
		alert("题目提交开始时间需要比题目提交结束时间早");
		document.getElementById("commitTopicEndTime").focus();
		return false;
	} else if($("#commitTopicEndTime").val() >= $("#oneSelectStartTime").val()) {
		alert("题目提交结束时间需要比第一轮选题开始时间早");
		document.getElementById("oneSelectStartTime").focus();
		return false;
//第一轮选题
	} else if($("#oneSelectStartTime").val() >= $("#oneSelectEndTimeOne").val()) {
		alert("第一轮选题开始时间需要比第一轮选题结束时间早");
		document.getElementById("oneSelectEndTimeOne").focus();
		return false;
	} else if($("#oneAdmissionStartTime").val() >= $("#oneFirstChoiceDeadline").val()) {
		alert("第一轮选题 教师录取志愿起始时间需要比第一志愿录取截止时间早");
		document.getElementById("oneFirstChoiceDeadline").focus();
		return false;
	} else if($("#oneFirstChoiceDeadline").val() >= $("#oneSecondChoiceDeadline").val()) {
		alert("第一轮选题 第一志愿录取截止时间需要比第二志愿录取截止时间早");
		document.getElementById("oneSecondChoiceDeadline").focus();
		return false;
	} else if($("#oneSecondChoiceDeadline").val() >= $("#oneThirdChoiceDeadline").val()) {
		alert("第一轮选题 第二志愿录取截止时间需要比第三志愿录取截止时间早");
		document.getElementById("oneThirdChoiceDeadline").focus();
		return false;
//第二轮选题
	} else if($("#oneThirdChoiceDeadline").val() >= $("#twoSelectStartTime").val()) {
		alert("第一轮录取结束时间需要比第二轮选题时间早");
		document.getElementById("twoSelectStartTime").focus();
		return false;
	} else if($("#twoSelectStartTime").val() >= $("#twoSelectEndTimeOne").val()) {
		alert("第二轮选题 开始时间需要比第二轮选题结束时间早");
		document.getElementById("twoSelectEndTimeOne").focus();
		return false;
	} else if($("#twoSelectEndTimeOne").val() >= $("#twoAdmissionStartTime").val()) {
		alert("第二轮选题 结束时间需要比教师录取志愿起始时间早");
		document.getElementById("twoAdmissionStartTime").focus();
		return false;
	} else if($("#twoAdmissionStartTime").val() >= $("#twoFirstChoiceDeadline").val()) {
		alert("第二轮选题 教师录取志愿起始时间 需要比 第一志愿录取截止时间 早");
		document.getElementById("twoFirstChoiceDeadline").focus();
		return false;
	} else if($("#twoFirstChoiceDeadline").val() >= $("#twoSecondChoiceDeadline").val()) {
		alert("第二轮选题 第一志愿录取截止时间 需要比 第二志愿录取截止时间 早");
		document.getElementById("twoSecondChoiceDeadline").focus();
		return false;
	} else if($("#twoSecondChoiceDeadline").val() >= $("#twoThirdChoiceDeadline").val()) {
		alert("第二轮选题 第二志愿录取截止时间 需要比 第三志愿录取截止时间 早");
		document.getElementById("twoThirdChoiceDeadline").focus();
		return false;
	}
		 
	return true;
}
function check(){
	//return commitTopic();
	return true;
}
//$(".form-control").val("2017-05-13T00:00");
var date = new Date();
$(function (){
	var month = date.getMonth() + 1;
    if(month < 10) {
        month = "0" + month;
    }
    var day = date.getDate();
    if(day < 10) {
        day = "0" + day;
    }
    var d = date.getFullYear()+"-"+month+"-"+day+"T23:59";
    if($(".form-control").val() == '') {
    	$(".form-control").val(d);
    }
}) 
</script>
</body>
</html>