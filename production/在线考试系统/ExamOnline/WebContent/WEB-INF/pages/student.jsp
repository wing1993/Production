<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="me.zxks.entity.Student" %>
    <%@ page import="me.zxks.entity.QType" %>
    <%@ page import="me.zxks.entity.Class_institute" %>
    <% QType qType=(QType)session.getAttribute("qType"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>学生</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/student_style.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js_data/student_data.js"></script>
	<script type="text/javascript">
		function show(id){

			switch (id) {
				case 1:
					$("#examOnline").show();
					$("#score_query").hide();
					$("#scanMessage").hide();
					$("#changeKey").hide();
					$("#location").html("在线考试");
					break;
				case 2:
					$("#examOnline").hide();
					$("#score_query").show();
					$("#scanMessage").hide();
					$("#changeKey").hide();
					$("#location").html("成绩查询");
					break;
				case 3:
					$("#examOnline").hide();
					$("#score_query").hide();
					$("#scanMessage").show();
					$("#changeKey").hide();
					$("#location").html("消息查看");
					break;
				case 4:
					$("#examOnline").hide();
					$("#score_query").hide();
					$("#scanMessage").hide();
					$("#changeKey").show();
					$("#location").html("密码修改");
					break;
				default:
					// statements_def
					break;
			}
		}
	</script>
</head>
<body>
	<div class="main">
		<div class="head">
			<div class="title">在线考试系统</div>
			<div class="welInfo">
				<div class="wel">欢迎&nbsp;</div>
				<div class="wel" id="student_name">${sessionScope.stuInfo.stuName}</div>
				<div class="wel">&nbsp;同学&nbsp;&nbsp;</div>
				<div class="wel" id="institute">${sessionScope.classInfo.instituteName}&nbsp;&nbsp;</div>
				<div class="wel">${sessionScope.classInfo.grade}${sessionScope.classInfo.className}</div>
				<div id="date" class="wel"></div>
				<!-- <div style="float:left;"><img src="../img/logout.ico"></div> -->
				<a href="LoginoutServlet" class="logout">退出</a>
			</div>
		</div>
		<div class="nav_left">			
			<div class="studentImg"><img src="<%=request.getContextPath()%>/img/student.png"></div>
			<div style="float:left;margin-top:20%;">学生中心</div>
			<div class="choice">
				 <ul>
				 	<li onclick="show(1)">在线考试</li>
				 	<li onclick="show(2)">成绩查询</li>
				 	<li onclick="show(3)">消息查看</li>
				 	<li onclick="show(4)">密码修改</li>
				 </ul>
			</div>
		</div>
		<div class="right">
			<div class="location">
				<div style="float:left;">当前位置>></div>
				<div style="float:left;font-weight:bold;" id="location"></div>
			</div>
			<!--在线考试-->
			<div class="detail" id="examOnline">
				<div class="choose_sub">
					<div style="float:left;">请选择科目:</div>
					<div class="subject">
						<select name="subject" class="subject1" id="pa_subject" onchange="showPaper()">
							<option>请选择</option>
						</select>
					</div>
				</div>

				<table id="paper">
					<thead>
						<tr>	
							<td>序号</td>
							<td>试卷题目</td>
							<td>考试时长</td>
							<td>考试时间</td>
							<td>出卷人</td>
							<td>考试状态</td>
						</tr>
					</thead>
					<!-- <tr>
						<td>1</td>
						当enter_test()函数在js文件中时不能用href="javascript:enter_test();" class="paper_name" onclick="enter_test(this)"
						<td><a href="ShowPaperServlet" target="_blank" >2016年高等数学期中考试</a></td>
						<td>60分钟</td>
						<td>2016年5月10日</td>
						<td>？？</td>
						<td>未考</td>
					</tr> -->
				</table>
			</div>

			<!--成绩查询-->
			<div class="detail" id="score_query">
				
				<span style="margin-left:2%;">科目：</span>
				<select class="subject1" onchange="showScore()" id="result_subject"><option>请选择</option></select>&nbsp;&nbsp;
				<table id="test_result">
					<thead>
						<tr>
							<td>序号</td>
							<td>试卷名称</td>
							<td>班级平均分</td>
							<td>我的成绩</td>
							<td>班级排名</td>
						</tr>
					</thead>
				</table>
			</div>

			<!--消息查看-->
			<div class="detail" id="scanMessage">
				<table>
					<thead>
						<tr>
							<td>消息列表</td>
						</tr>
					</thead>
				</table>
			</div>

			<!--密码修改-->
			<div class="detail" id="changeKey">
				<table style="margin-left:25%;margin-top:10%;">
					<tr>
						<td>新密码：</td>
						<td><input type="password" id="new_pwd"><label class="tip">&nbsp;*</label></td>
					</tr>
					<tr>
						<td>密码确认：</td>
						<td><input type="password" id="sure"><label class="tip">&nbsp;*</label></td>
					</tr>
				</table>
				<input type="button" value="确定" style="margin-left:35%;margin-top:1%;" onclick="ChangeKey(${sessionScope.stuInfo.stuNo})"> 
				<input type="button" value="取消" id="cancel">
				
			</div>
		</div>
	</div>
</body>
</html>