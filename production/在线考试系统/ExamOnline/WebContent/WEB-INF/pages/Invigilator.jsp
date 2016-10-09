<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="me.zxks.entity.Teacher" %>
    <% Teacher teacher=(Teacher)session.getAttribute("tInfo"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>监考员</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/invigilator.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/student_style.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/Invigilator.js"></script>
	<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/js_data/admin_data.js"></script> --%>
</head>
<body>
	<div class="main">
		<div class="head">
			<div class="title">在线考试系统</div>
			<div class="welInfo">
				<div class="wel">欢迎&nbsp;</div>
				<div class="wel" id="student_name">${sessionScope.tInfo.tName }</div>
				<div class="wel">&nbsp;监考员&nbsp;&nbsp;</div>
				<div id="date" class="wel"></div>
				
				<a href="login.jsp" class="logout">退出</a>
			</div>
		</div>
		<div class="nav_left">			
			<div class="studentImg"><img src="<%=request.getContextPath() %>/img/teacher.png"></div>
			<div style="float:left;margin-top:20%;">监考员中心</div>
			<div class="choice">
				 <ul>
				 	<li onclick="showStu(this)">考生状态</li>
				 	<li onclick="voidPaper(this)">试卷作废</li>
				 </ul>
			</div>
		</div>
		<div class="right">
			<div class="location">
				<div style="float:left;">当前位置>></div>
				<div style="float:left;font-weight:bold;" id="location"></div>
			</div>
			<!--考生状态-->
			<div class="detail" id="stuState">
				<div class="online_count">当前在线人数：<span id="online_count"></span></div>
				<div>
					<span>学院：<select class="institute1" onchange="getClass(this,'#class')"><option>请选择</option></select>&nbsp;</span>
					<span>班级：<select class="class1" id="class" onchange="showOnlineStu()"><option></option></select></span>
				</div>
				<div>
					<table class="online_tb"  id="online_tb1">
						<thead>
							<tr>
								<td>学号</td>
								<td>姓名</td>
								<td>性别</td>
								<td>登录时间</td>
							</tr>
						</thead>
						<tr>
							<td>001</td>
							<td>John</td>
							<td>男</td>
							<td>2016-5-6 10:50:20</td>
						</tr>
					</table>
				</div>
			</div>
			
			<!-- 试卷作废 -->
			<div class="detail" id="void_paper">
				<div>
					<span>学院：<select class="institute1" id="institute1" onchange="getClass(this,'#class1')"><option>请选择</option></select>&nbsp;</span>
					<span>班级：<select class="class1" id="class1" onchange="showStudentPaper()"><option></option></select></span>&nbsp;&nbsp;
					<span>试卷：<select class="paper1" id="paper1" onchange="showStudentPaper()"><option>请选择</option></select></span>
				</div>
				<div>
					<table class="online_tb"  id="online_tb2">
						<thead>
							<tr>
								<td>学号</td>
								<td>姓名</td>
								<td>性别</td>
								<td>试卷名称</td>
								<td>考试时间</td>
								<td>成绩</td>
								<td>操作</td>
							</tr>
						</thead>
						
					</table>
				</div>
				<div class="paging">
				<span><button onclick="last()" id="last">上一页</button></span>
				<span id="page_no"><button class='pages' onclick="showQuestion(this)">1</button></span>
				<span id="now_all"></span>
				<span><button id="next" onclick="next()" >下一页</button></span>
			</div>
			</div>
		</div>
	</div>
</body>
</html>