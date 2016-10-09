<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="me.zxks.entity.Teacher" %>
    <% Teacher teacher=(Teacher)session.getAttribute("tInfo"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>题库管理员</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/testManager.css">
	<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/testManager.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js_data/testManager_data.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js_data/score_data.js"></script>
	<script type="text/javascript">
	$('document').ready(function(){ShowMessage();});
<%-- 	//显示已发布的消息
	function ShowMessage(){
		$(".showmessage").html("");
		$.post('PaperManagerServlet',{method:'showMessage'},
		   	function(arry){
	         	var obj = eval('('+arry+')');
	         	for(var i=0;obj.length;i++){
	         		var n=i+1;
	         		var opt = "<tr class='showmessage'><td>"+n+"</td><td>"+obj[i].message_title+"</td><td>"+obj[i].message_time+
	         			"</td><td><a href='#' onclick='edit(this)'><img title='编辑' src='"+'<%=request.getContextPath()%>'+"/img/edit1.png'>修改</a>"+
	         			"<a href='#' onclick='deletemsg(this)'><img src='"+'<%=request.getContextPath()%>'+"/img/delete.png'>删除"+
	         			"</a></td></tr>";
	         		$('.released').append(opt);
	         	}
		});
	}
 --%>	//显示已发布的消息
	function ShowMessage(){
		$(".showmessage").html("");
		$.post('PaperManagerServlet',{method:'showMessage'},
		   	function(arry){
	         	var obj = eval('('+arry+')');
	         	for(var i=0;obj.length;i++){
	         		var n=i+1;
	         		var opt = "<tr class='showmessage'><td>"+n+"</td><td>"+obj[i].message_title+"</td><td>"+obj[i].message_time+
	         			"</td><td><img title='编辑' onclick='edit(this)' src='"+'<%=request.getContextPath()%>'+"/img/edit1.png'>&nbsp;"+
	         			"<img title='删除' onclick='deletemsg(this)' src='"+'<%=request.getContextPath()%>'+"/img/delete.png'>"+
	         			"</td></tr>";
	         		$('.released').append(opt);
	         	}
		});
	}
	
	</script>
</head>
<body>
	<div class="main">
		<div class="head">
			<div class="title">在线考试系统</div>
			<div class="welInfo">
				<div class="wel">欢迎题库管理员&nbsp;</div>
				<div class="wel">${sessionScope.tInfo.tName }</div>
				<div class="wel">&nbsp;您好！</div>
				<div class="wel" id="date"></div>
				<a href="login.jsp" class="logout">退出</a>
			</div>
		</div>
		<div class="nav_left">	
			<div style="margin-left:4%;margin-right:4%;height:auto;margin-top:5%">
				<div class="list_title" onclick="showmenu('list1','show1')" id="list1_title">试题管理
					<div class="show" id="show1">+</div>
				</div>
				<div class="content" id="list1">
					<ul>
						<li id="manualAdd">我要出题</li>
						<li id="mytest">我的试题</li>
						<li id="importTest">导入试题</li>
					</ul>
				</div>
				<div class="list_title" onclick="showmenu('list2','show2')" id="list2_title">试卷管理
					<div class="show" id="show2">+</div>
				</div>
				<div class="content" id="list2">
					<ul>
						<li id="setPaper">我要出卷</li>
						<li id="editPaper">试卷修改</li>
					</ul>
				</div>
				
				<div class="list_title" onclick="showmenu('list3','show3')" id="list3_title">试卷评分
					<div class="show" id="show3">+</div>
				</div>
				<div class="content" id="list3">
					<ul>
						<li id="sub_eval">主观题评分</li>
						<li id="statistic">考试情况统计</li>
					</ul>
				</div>
				<div class="list_title" id="list4_title">发布消息</div>
			</div>	
			
		</div>
		
		<div class="right">
			<!--位置-->
			<div class="location">
				<div style="float:left;">当前位置>></div>
				<div class="sub_location" id="location1"></div>
				<div class="sub_location" id="location2"></div>
			</div>
			<!--出题-->
			<div class="detail" id="buildTest">
				<table class="editQuestion">
					<tr>
						<td>请选择科目:</td>
						<td>
							<select id="subject1"class="subject1"></select>
							<option></option>
						</td>
					</tr>
					<tr>
						<td>请选择章节:</td>
						<td>
							<select id="chapter1">
								<option>1</option>
								<option>2</option>
								<option>3</option>
								<option>4</option>
								<option>5</option>
								<option>6</option>
								<option>7</option>
								<option>8</option>
								<option>9</option>
								<option>10</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>题目类型:</td>
						<td id="type">
							<input  type="radio" value="单项选择" name="type" checked="true">单项选择
							<input  type="radio" value="填空题" name="type">填空题
							<input  type="radio" value="判断题" name="type">判断题
							<input  type="radio" value="主观题" name="type">主观题
						</td>
					</tr>
					<tr>
						<td>题目内容:</td>
						<td>
							<textarea id="question1" style="overflow-y:scroll;resize:none;width:50%;" rows="6";></textarea>
						</td>
					</tr>
					<tr class="choice">
						<td>A:</td>
						<td><textarea id="choice1A"class=""></textarea></td>
					</tr>
					<tr class="choice">
						<td>B:</td>
						<td><textarea id="choice1B" class=""></textarea></td>
					</tr>
					<tr class="choice">
						<td>C:</td>
						<td><textarea id="choice1C" class=""></textarea></td>
					</tr>
					<tr class="choice">
						<td>D:</td>
						<td><textarea id="choice1D" class=""></textarea></td>
					</tr>
					<tr class="answer">
						<td>答案：</td>
						<td>
							<select id="answer1">
								<option>A</option>
								<option>B</option>
								<option>C</option>
								<option>D</option>
							</select>
						</td>
					</tr>
				</table>
				<input type="button" value="提交" onclick="click_addQuestion()" style="margin-left:30%;margin-top:1%;">
			</div>
			<!--我的试题-->
			<div class="detail" id="manage_mytest">
				<div>
					<table>
						<tr>
							<td>科目：</td>
							<td>
								<select id="subject2" class="subject1"></select>
							</td>
							<td></td>
							<td></td>
							<td>题型：</td>
							<td>
								<select id="qType">
									<option>请选择</option>
									<option>单项选择</option>
									<option>填空题</option>
									<option>判断题</option>
									<option>主观题</option>
								</select>
							</td>
						</tr>
					</table>
				</div>
				<div style="overflow:auto;height:82%;">
					<!--选择题内容-->
					<table class="showMytest" id="single_choice">
						<thead>
							<tr>
								<td>题目内容</td>
								<td>选项</td>
								<td>答案</td>
								<td>操作</td>
							</tr>
						</thead>	
						
					</table>
					<!--填空题内容-->
					<table class="showMytest" id="completion">
						<thead>
							<tr>
								<td>题目内容</td>
								<td>答案</td>
								<td>操作</td>
							</tr>
						</thead>
					</table>
					<!--判断题内容-->
					<table class="showMytest" id="tfng">
						<thead>
							<tr>
								<td>题目内容</td>
								<td>答案</td>
								<td>操作</td>
							</tr>
						</thead>
					</table>
					<!--主观题内容-->
					<table class="showMytest" id="subjective">
						<thead>
							<tr>
								<td>题目内容</td>
								<td>参考答案</td>
								<td>操作</td>
							</tr>
						</thead>
					</table>				
			</div>
			<!-- <div id="question_content"></div> -->
			<div class="paging">
				<span><button onclick="last()" id="last">上一页</button></span>
				<span id="page_no"><button class='pages' onclick="showQuestion(this)">1</button>
				<button class='pages' onclick="showQuestion(this)">2</button></span>
				<span id="now_all"></span>
				<span><button id="next" onclick="next()" >下一页</button></span>
			</div>	
			</div>
			<!--导入试题-->
			<div class="detail" id="importFile">
				<div>
					<span>科目：<select class="subject1"></select></span>&nbsp;&nbsp;
					<span>题型：<select id="Import_qType">
								<option>请选择</option>
								<option>单项选择</option>
								<option>填空题</option>
								<option>判断题</option>
								<option>主观题</option>
							</select>					
					</span>
				</div>

				<div class="import">
					<span>从文件中导入：</span>
					<span>
						<input type="text" name="upfile" id="upfile" style="width:30% !important;">  

						<button onclick="path.click()" class="valid">浏览</button>  
						<input type="file" id="path" style="display:none" onchange="upfile.value=this.value">
					</span><br>
					<input type="button" value="导入" id="" onclick="ImportExcel()" style="margin-top:2%;">
				</div>
			</div>
			<!--我要出卷-->
			<div class="detail" id="buildPaper">
				<form method="post" action="">
					<table class="editQuestion">
						<tr>
							<td>试卷名称：</td>
							<td><input type="text" id="paper_title"></td>
						</tr>
						<tr>
							<td>考试科目：</td>
							<td>
								<select id="subject4" name="subject1" class="subject1"></select>
							</td>
						</tr>
						<tr>
							<td>章节：</td>
							<td>
								<select name="chapter" id="chapter2">
								    <option>1</option>
								    <option>2</option>
								    <option>3</option>
								    <option>4</option>
							    	<option>5</option>
							     	<option>6</option>
							    	<option>7</option>
						    		<option>8</option>
							    	<option>9</option>
						    		<option>10</option>
							    	</select>
							</td>
						</tr>
						<tr>
							<td>考试时间：</td>
							<td><input id="qTime" type="date" ></td>
						</tr>
						<tr>
							<td>试卷题型：</td>
							<td class="type-num-weight">
								<div>
									<input type="checkbox" name="question_type" value="单项选择">单项选择
									数量：<input type="text" id="question_num1" name="question_num">
									<label class="tip">*</label>&nbsp;
									分值：<input type="text" id="weighting1" name="weighting">
									<label class="tip">*</label>&nbsp;
								</div>
								<div>
									<input type="checkbox" name="question_type" value="填空题">填空题
									&nbsp;&nbsp;数量：<input type="text" id="question_num2" name="question_num">
									<label class="tip">*</label>&nbsp;
									分值：<input type="text" id="weighting2" name="weighting">
									<label class="tip">*</label>&nbsp;
								</div>
								<div>
									<input type="checkbox" name="question_type" value="判断题">判断题
									&nbsp;&nbsp;数量：<input type="text" id="question_num3" name="question_num">
									<label class="tip">*</label>&nbsp;
									分值：<input type="text" id="weighting3" name="weighting">
									<label class="tip">*</label>&nbsp;
								</div>
								<div>
									<input type="checkbox" name="question_type" value="主观题">主观题
									&nbsp;&nbsp;数量：<input type="text" id="question_num4" name="question_num">
									<label class="tip">*</label>&nbsp;
									分值：<input type="text" id="weighting4" name="weighting">
									<label class="tip">*</label>&nbsp;
								</div>
								
							</td>
						</tr>
						<tr>
							<td>考试时长：</td>
							<td><input type="text" id="test_time" name="test_time"></td>
						</tr>
					</table>
					<input style="margin-left:40%;margin-top:2%;" type="button"value="提交" onclick="click_addPaper()" >
				</form>
				
			</div>
			<!--试卷修改-->
			<div class="detail" id="edit_paper">
				<table  id="edit_paper_tb">
					<thead>
						<tr>
							<td>试卷名称</td>
							<td>考试时间</td>
							<td>考试时长</td>
							<td>操作</td>
						</tr>
					</thead>
				    <tr class="showpaper1"></tr> 
					<!-- <tr>
						<td>2016年高等数学期中考试</td>
						<td>2016-05-10</td>
						<td>60分钟</td>
						<td>
							<a href="#" onclick="edit_paper(this)">修改</a>&nbsp;<a href="#" onclick="delete_paper(this)">删除</a>
						</td>
					</tr> -->
				</table>
			</div>
			<!--主观题评分-->
			<div class="detail" id="grading">
				<div>
					<span>科目：</span><select class="subject1" id="subject_score"><option>请选择</option></select>&nbsp;&nbsp;
					<span>试卷名称：</span><select class="paper_name"><option>请选择</option></select>&nbsp;&nbsp;
					<span>班级：</span><select class="class1" ><option>请选择</option></select>
				</div>
				<div>
					<table id="eval" class="tblist">
						<thead>
							<tr>
								<td>学号</td>
								<td>姓名</td>
								<td>考试时间</td>
								<td>操作</td>
							</tr>
						</thead>
					</table>
				</div>
			</div>
			<!--考试情况统计-->
			<div class="detail" id="sum_up">
				<div>
					<span>科目：</span><select class="subject1" id="subject_testResult"><option>请选择</option></select>&nbsp;&nbsp;
					<span>班级：</span><select class="class" id="testResultClass"><option></option></select>
				</div>
				<div>
					<table id="analyse" class="tblist">
						<thead>
							<tr>
								<td>序号</td>
								<td>试卷名称</td>
								<td>考试人数</td>
								<td>平均分</td>
								<td>考试时间</td>
							</tr>
						</thead>
<!-- 						<tr>
							<td>1</td>
							<td><a href="#" onclick="showGraphic()">2016年高等数学期中考试</a></td>
							<td>20</td>
							<td>2016年5月10日</td>
						</tr> -->
					</table>
				</div>
				
			</div>
			<!--发布消息-->
			<div class="detail" id="releaseNews">
				<div>
					<table class="unrelease">
						<tr>
							<td>消息标题:</td>
							<td><input type="text" id="message_title" style="width:97%;"></td>
							<td><label class="tip">*</label></td>
						</tr>
						<tr height="100">
							<td>消息内容：</td>
							<td>
								<textarea id="message_content"></textarea> 
							</td>
							<td><label class="tip">*</label></td>
						</tr>
					</table>
					<div style="margin-left:20%;margin-top:1%;">
						<input type="button" value="发布" id="release" onclick='click_postMessage()'>
						<input type="reset" value="重置" onclick='click_resetMessage()' >
					</div>
				</div>
				<!--已发布的消息-->
				<div style="margin-top:3%;">
					<div>已发布的消息</div>
					<div>
						<table class="released">
							<thead>
								<tr>
									<td>序号</td>
									<td>标题</td>
									<td>发布时间</td>
									<td>操作</td>
								</tr>
							</thead>
							<tr class="showmessage"></tr>
							<!-- <tr  id="showmessage"></tr> -->
								<%-- <td>1</td>
								<td>2016年高等数学期中考试</td>
								<td>2016年4月5日</td>
								<td>
									<a href="#" class="edit"><img src="<%=request.getContextPath()%>/img/edit1.png">修改</a>
									<a href="#" class="delete"><img src="<%=request.getContextPath()%>/img/delete.png">删除</a>
								</td>
							</tr>
							<tr>
								<td>2</td>
								<td>2017年高等数学期中考试</td>
								<td>2017年4月5日</td>
								<td>
									<a href="#" class="edit"><img src="<%=request.getContextPath()%>/img/edit1.png">修改</a>
									<a href="#" class="delete"><img src="<%=request.getContextPath()%>/img/delete.png">删除</a>
								</td>
							</tr> --%>
						</table>
					</div>
				</div>
			
			
			
		</div>
	</div>
	<!--弹出编辑框-->
	<div class="popover">
		<div class="popover-head">
			<div title="关闭" class="close">×</div>
			<div>修改消息</div>
			
		</div>
		<div class="popover-content" style="padding:10%;">
			<table>
				<tr>
					<td>消息标题：</td>
					<td><input type="text" id="po-message-title" style="width:97%;"></td>
					<td><label class="tip">*</label></td>
				</tr>
				<tr height='100'>
					<td>消息内容：</td>
					<td><textarea id="po-message-content"></textarea></td>
					<td><label class="tip">*</label></td>
				</tr>
			</table>
			<input type="button" value="保存" id="popover-btn" onclick="SaveMessage1()">
		</div>
	</div>
	<div class="background"></div>
	<!--弹出修改试卷的窗口-->
	<div class="po-edit-paper">
		<div class="popover-head">
			<div title="关闭" onclick="close_paper()" class="close-paper">×</div>
			<div>修改试卷</div>		
		</div>
		<div>
			<table>
				<tr>
					<td>试卷名称：</td>
					<td id="po-paper-name"></td>
				</tr>
				<tr>
					<td>考试时间：</td>
					<td><input type="date" id="po-test-date"></td>
				</tr>
				<tr>
					<td>考试时长：</td>
					<td><input type="text" id="po-test-time" onkeypress="keypress()">分钟</td>
				</tr>
			</table>
			<input type="button" value="保存" onclick="save_change()">
		</div>
	</div>
	<!-- 弹出考试情况窗口 -->
	<div class="po-bar-chart">
		<div class="popover-head">
			<div title="关闭" onclick="close_chart()" class="close-chart">×</div>
			<div>考试情况统计</div>		
		</div>
		<div>
			<div class="top">
				<span>最高分：<span id="max"></span>&nbsp;<span>最低分：</span><span id="min"></span>&nbsp;</span>
				<span>平均分：<span id="avg"></span></span>
			</div>
			<div class="coordinate">
				<div class="ro-y"></div>
				<div class="co-left">
					<div class="co-y"></div>
					<div class="co-y"></div>
					<div class="co-y"></div>
					<div class="co-y"></div>
					<div class="co-y"></div>
					<div class="co-y"></div>
				</div>
				<div class="co-border">	
					<div class="co-head">单位：人</div>
					<div class="bar-box">
						<div class="co-num"><div class="co-num-y" id="usix-height"></div></div>
						<div class="bar" id="under-six"></div>					
					</div>
					<div class="bar-box">
						<div class="co-num"><div class="co-num-y" id="six-height">2</div></div>
						<div class="bar" id="six"></div>
					</div>
					<div class="bar-box">
						<div class="co-num"><div class="co-num-y" id="seven-height"></div></div>
						<div class="bar" id="seven"></div>
					</div>	
					<div class="bar-box">
						<div class="co-num"><div class="co-num-y" id="eight-height"></div></div>
						<div class="bar" id="eight"></div>
					</div>	
					<div class="bar-box">
						<div class="co-num"><div class="co-num-y" id="nine-height"></div></div>
						<div class="bar" id="nine"></div>
					</div>	
					<div class="bar-box">
						<div class="co-num"><div class="co-num-y" id="ten-height"></div></div>
						<div class="bar" id="ten"></div>
					</div>	
					
				</div>
				<div class="co-foot">
					<div class="range">60以下</div>
					<div class="range">60-69</div>
					<div class="range">70-79</div>
					<div class="range">80-89</div>
					<div class="range">90-99</div>
					<div class="range">100以上</div>
					<div class="unit"></div>
				</div>
				<div class="ro-x"></div>
			</div>		
		</div>
		<input type="button" value="关闭" onclick="close_chart()" title="关闭">
	</div>
</body>
</html>