<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>管理员</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/admin_style.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/testManager.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
	<script charset="utf-8" type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/admin.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js_data/admin_data.js"></script>
    <script type="text/javascript">
	</script>
</head>
<body>
	<div class="main">
		<div class="head">
			<div class="title">在线考试系统</div>
			<div class="welInfo">
				<div class="wel">欢迎系统管理员</div>
				<div class="wel" id="date"></div>
				<a href="login.jsp" class="logout">退出</a>
			</div>
		</div>
		<div class="nav_left">	
			<div style="margin-left:4%;margin-right:4%;height:auto;margin-top:5%">
				<div class="list_title" onclick="showmenu('list1','show1')" id="list1_title">考生信息管理
					<div class="show" id="show1">+</div>
				</div>
				<div class="content" id="list1">
					<ul>
						<li id="Info_query1">信息查询修改</li>
						<li id="Info_insert">信息插入</li>
					</ul>
				</div>
				<div class="list_title" onclick="showmenu('list2','show2')" id="list2_title">教师信息管理
					<!-- <div class="show" id="show2">+</div> -->
				</div>
				
				<div class="list_title" onclick="showmenu('list4','show4')" id="list4_title">数据维护
					<div class="show" id="show4">+</div>
				</div>
				<div class="content" id="list4">
					<ul>
						<li id="backup">数据库备份</li>
						<li id="restore">数据库还原</li>
					</ul>
				</div>
			</div>	
			
		</div>
		
		<div class="right">
			<!--位置-->
			<div class="location">
				<div style="float:left;">当前位置>></div>
				<div class="sub_location" id="location1"></div>
				<div class="sub_location" id="location2"></div>
			</div>
			<!--学生信息查询修改-->
			<div class="detail" id="stuInfo_query">
				<table class="tblist1">
					<tr>
						<td></td>
						<td>学院：</td>
						<td>
							<select class="institute1" id="institute" onchange="getClass(this,'#class')">
							<option></option>
							</select>
						<td>班级：</td>
						<td>
							<select class="class1" id="class" onchange="change_class()">
							</select>
						</td>
					</tr>
					<tr>
						<td></td>
						<td>学号：</td>
						<td><input id="stuNo" type="text"></td>
						<td>姓名：</td>
						<td><input id="stuName" type="text"></td>
						<td></td>
						<td><input id="search_student" type="button" value="查找" onclick="findStudent()"></td>
					</tr>
				</table>
				<table class="tblist2" id="stuInfo">
					<thead>
						<tr>
							<td>学号</td>
							<td>姓名</td>
							<td>性别</td>
							<td>学院</td>
							<td>班级</td>
							<td>操作</td>
						</tr>
					</thead>
					<!-- <tr>
						<td>001</td>
						<td><input type="text" value="叶俊浩" class="hide-text"></td>
						<td>
							 <select class='gender'>
							 	<option class="hide" value="1">男</option>
							 	<option class="hide" value="0">女</option>
							 </select>
						</td>
						<td>
							<select class="institute2" >
								<option>计算机学院</option>
							</select>
						</td>
						<td>
							<select class="class2" onclick="change(this)">
								<option>13科技2班</option>
							</select>
						</td>
						<td>
							<button class="valid" onclick="edit_stu(this)">修改</button>
							<button class="valid" onclick="delete_stu(this)">删除</button>
							<button class="invalid" onclick="save_change(this,'student')">保存</button>
						</td>
					</tr>
					<tr>
						<td>002</td>
						<td><input type="text" value="吴玉玲" class="hide-text"></td>
						<td>
							 <select>
							 	<option class="hide" value="0">女</option>
							 	<option class="hide" value="1">男</option>
							 </select>
						</td>
						<td>
							<select class="institute2" onchange="change(this)">
								<option>计算机学院</option>
							</select>
						</td>
						<td>
							<select class="class2">
								<option>13科技2班</option>
							</select>
						</td>
						<td>
							<button class="valid" onclick="edit_stu(this)">修改</button>
							<button class="valid" onclick="delete_stu(this)">删除</button>
							<button class="invalid" onclick="save_change(this,'student')">保存</button>
						</td>
					</tr> -->
				</table>
				<div class="paging">
					<span><button onclick="last()">上一页</button> </span>
					<span  id="page_no_stu"><button class="page_no_stu1" onclick="">1</button><!-- <button class="page_no_stu1" onclick="">2</button> --></span>
					<span class="page_no_stu1">第<span id="current_page">1</span>/<span id="total_count">1</span>页 </span>
					<span id="next"><button onclick="next()" >下一页</button></span>
				</div>
							
			</div>
			<!-- 学生信息插入 -->
			<div class="detail" id="stuInfo_insert">
				<table class="insertInfo">
					<tr>
						<td>学号：</td>
						<td><input type="text" id="insert_stuNo">&nbsp;<span>*</span></td>
					</tr>
					<tr>
						<td>姓名:</td>
						<td><input type="text" id="insert_stuName">&nbsp;<span>*</span></td>
					</tr>
					<tr>
						<td>性别:</td>
						<td>
							<select id="insert_gender">
								<option value="1">男</option>
								<option value="0">女</option>
							</select>
							<span>*</span>
						</td>
					</tr>
					<tr>
						<td>学院：</td>
						<td><select class="institute1" id="instituteI" onchange="getClass(this,'#class1')"><option></option></select>&nbsp;<span>*</span></td>
					</tr>
					<tr>
						<td>班级：</td>
						<td><select class="class1" id="class1"><option></option></select>&nbsp;<span>*</span></td>
					</tr>
				</table>
				<div class="insert_btn">
					<input type="button"  onclick="insert()" value="提交">
					<input type="button" onclick="cancel()" value="取消">
				</div>
				<div style="margin-top:3%;width:100%;border-bottom:2px dotted #d0d0d0;"></div>	
				<div class="import">
					<span>从文件中导入：</span>
					<span>
						<input type="text" size="20" name="upfile" id="upfile" >  

						<button onclick="path.click()" class="valid">浏览</button>  
						<input type="file" id="path" style="display:none" onchange="upfile.value=this.value">
					</span><br>
					<input type="button" value="导入" id="" onclick="ImportExcel()" style="margin-top:2%;margin-left:12%;">
				</div>
			</div>
			<!--教师信息管理-->
			<div class="detail" id="tInfo_manage">
				<div style="border-bottom:1px solid #d0d0d0;padding-bottom:2%;padding-left:8%;">
					<span>学院：</span><span><select class="institute1" id="instituteT" onchange="showTeacher()"><option></option></select>&nbsp;</span>
					<span>教师编号：</span><span><input type="text" id="tNo"></span>
					<span>姓名：</span><span><input type="text" id="tName"></span>
					<input type="button" value="查找" id="search_teacher" onclick="findTeacher()" >
				</div>
				<div class="add-teacher">
					<span style="font-size:20px;font-weight:bold;">+</span><span id="add_teacher">新增教师信息</span>
				</div>
				<table class="tblist2" id="teaInfo">
					<thead>
						<tr>
							<td>编号</td>
							<td>姓名</td>
							<td>性别</td>
							<td>角色</td>
							<td>学院</td>
							<td>操作</td>
						</tr>
					</thead>					
					<tr>
						<td>001</td>
						<td><input type="text" value="叶俊浩" class="hide-text"></td>
						<td>
							<select>
							 	<option class="hide" value="0">女</option>
							 	<option class="hide" value="1">男</option>
							 </select>
						</td>
						<td>
							<select>
								<option class="hide" value="题库管理员">题库管理员</option>
								<option class="hide" value="监考员">监考员</option>
							</select>
						</td>
						<td>
							<select class="institute2">
								<option>计算机学院</option>
							</select>
						</td>
						<td>
							<button class="valid" onclick="edit_stu(this)">修改</button>
							<button class="valid" onclick="delete_t(this)">删除</button>
							<button class="invalid" onclick="save_change(this,'teacher')">保存</button>
						</td>
					</tr>
				</table>
				<div class="paging">
					<span><button onclick="lastT()">上一页</button> </span>
					<span  id="page_no_tea"><button class="page_no_stu1" onclick="">1</button><button class="page_no_stu1" onclick="">2</button></span>
					<span class="page_no_stu1">第<span id="current_pageT">1</span>/<span id="total_countT">1</span>页 </span>
					<span id="nextT"><button onclick="nextT()" >下一页</button></span>
				</div>
				<!--弹出编辑框-->
				
			</div>
			<!--数据备份-->
			<div class="detail" id="data_backup"></div>
			<!-- 数据还原 -->
			<div class="detail" id="data_restore"></div>
		</div>
		
		<!-- 弹出增加教师信息的窗口 -->
		<div class="popover">
			<div class="popover-head">
				<div title="关闭" class="close">×</div>
				<div>新增教师信息</div>
				
			</div>
			<div class="popover-teacher" style="padding:5%;">
				<table>
					<tr>
						<td>编号：</td>
						<td><input type="text" id="po-tNo"></td>
						<td><label class="tip">*</label></td>
					</tr>
					<tr >
						<td>姓名：</td>
						<td><input type="text" id="po-tName"></td>
						<td><label class="tip">*</label></td>
					</tr>
					<tr >
						<td>性别：</td>
						<td>
							<select id="insert_tgender">
								<option value="1">男</option>
								<option value="0">女</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>角色：</td>
						<td>
							<select class="tRole">
								<option value="题库管理员">题库管理员</option>
								<option value="监考员">监考员</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>学院：</td>
						<td><select class="institute1" id="insert_tinstitute"><option></option></select></td>
					</tr>
				</table>
				<div style="margin-left:34%;margin-top:3%;">
					<input type="button" value="保存" id="teacher-btn" onclick="insert_tea()">&nbsp;&nbsp;
					<input type="button" value="取消" id="teacher-cancel">
				</div>
			</div>
		</div>
		<div class="background"></div>
	</div>
</body>
</html>