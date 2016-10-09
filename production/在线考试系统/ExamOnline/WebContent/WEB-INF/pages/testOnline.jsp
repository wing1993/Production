<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="me.zxks.entity.QType" %>
    <%@ page import="me.zxks.entity.Single_choose" %>
    <%@ page import="me.zxks.entity.Completion" %>
    <%@ page import="me.zxks.entity.Subjective_item" %>
    <%@ page import="me.zxks.entity.Tfng" %>
    <% QType qType=(QType)session.getAttribute("qType"); %>
    <% ArrayList single_chooses=(ArrayList)session.getAttribute("single_chooses"); %>
    <% ArrayList completions=(ArrayList)session.getAttribute("completions"); %>
    <% ArrayList subjective_items=(ArrayList)session.getAttribute("subjective_items"); %>
    <% ArrayList tfngs=(ArrayList)session.getAttribute("tfngs"); %>
    <% ArrayList qTypes=(ArrayList)session.getAttribute("qTypes"); %>
    <% int add_score=0; %>
    <% String subjectId=(String)session.getAttribute("subjectId"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>在线考试系统</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/testOnline.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/student_style.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script>

	<script type="text/javascript">
		$(document).ready(function(){
			var iNow=null;
			var iNew=null;
			var t=0;
			var str='';
			var timer=null;
			iNew=(new Date()).getTime()+${sessionScope.qType.qTest_time}*60*1000;//到设定的时间 query*60*1000
	
				timer=setInterval(function(){
					iNow=new Date();
					t=Math.floor((iNew-iNow)/1000);//1秒=1000毫秒，js以毫秒为单位
					if(t>=0){//当t小于0时停止倒计时
						str=Math.floor(t%86400/3600)+"时"+Math.floor(t%86400%3600/60)+"分"+t%60+"秒";
						$(".timer").html(str);
					}else {
						clearInterval(timer);
						alert("考试时间结束了");
						window.close();
					}
					
				}, 1000);
		});
	
		//设置页面为不可刷新
		function keydown(){
			if(event.keyCode==116){
				event.keyCode=0;
				event.returnValue=false;
				alert("当前不允许使用F5刷新键");
			}
			/* if(event.keyCode==123){
				event.keyCode=0;//禁止F12
				event.returnValue=false;
			} */
		}
		function stop(){return false;}
		document.oncontextmenu=stop;
		
	</script>
</head>
<body onkeydown="keydown()">
	<div class="right">
		<div style="padding-bottom:20px;font-size:20px;">答题情况</div>	
		<div class="right-in"></div>
	</div>
	<div class="left_time">
		<div class="left_title">剩余时间：</div>
		<div class="timer"></div>
	</div>
	<form method="post" action="CalScoreServlet" name="form1">
	<div class="main">
		<div>
			<div class="title">${sessionScope.qType.paper_name}</div>
			<input type="hidden" name="subjectId" value='${sessionScope.subjectId}'>
			<% 
				int sum=0;
				for(int i=0;i<qTypes.size();i++){
					QType qType2=(QType)qTypes.get(i);//将ArrayList中的每个对象转化为QType
					sum=sum+qType2.getqScore()*qType2.getqNum();
				}
			%>
			<div>
				<div class="full_score">满分：<%=sum %>分</div>		
				<%
					String type;
					String[] tyStrings=new String[5];
					for(int i=0;i<qTypes.size();i++){
						QType qType2=(QType)qTypes.get(i);
						if(qType2.getqType().equals("单项选择"))
							type="单项选择";			
						else if(qType2.getqType().equals("填空题"))
							type="填空题";
						else if(qType2.getqType().equals("判断题"))
							type="判断题";
						else
							type="主观题";
				%>		<div class="full_score"><%=type %>：<%=qType2.getqNum()*qType2.getqScore() %>分</div>
				<%		tyStrings[i]=type;
					}
				%>
			</div>
			
		</div>
		
		<div class="content">
		<%
			String[] str={"一","二","三","四","五","六","七"};
			
			for(int i=0;i<qTypes.size();i++){
				QType qType3=(QType)qTypes.get(i);
		%>
			<div>
				<div class="question-title"><%--输出题目编号和类型 --%>
					<span class="num"><%=str[i] %>、</span><span><%=tyStrings[i] %></span>
					<span>(本题共<%=qType3.getqNum() %>小题，每小题<%=qType3.getqScore() %>分)</span> 
				</div>
				<script type="text/javascript">
					var no='<%=str[i]%>';
					$(".right-in").append("<div class='type-no' id="+no+">"+no+"、</div>");
				</script>
			
		<%		if(tyStrings[i]=="单项选择"){
					for(int j=0;j<single_chooses.size();j++){
						Single_choose sChoose=(Single_choose)single_chooses.get(j);
		%>	
					<div  id='<%=j+1 %>'>
						<p><%=j+1 %>、<%=sChoose.getChoice_content() %><%--输出题目内容 --%>
							<% String choose_ans=str[i]+j; %>
							<%String sub_sc=str[i]+(j+1)+"sc"; %>
							<%String sub_sc_bmg=str[i]+(j+1)+"sc_bmg"; %>
							<input type="hidden" name="choose_score" id='<%=choose_ans %>' value="0">
							
						</p> 
						<%String a=j+1+"a";String b=j+1+"b";String c=j+1+"c";String d=j+1+"d"; %>
						<input type="radio" name='<%=j+1%>' id='<%=a%>' value='A'><%=sChoose.getChoiceA() %><br>
						<input type="radio" name='<%=j+1%>' id='<%=b%>' value='B'><%=sChoose.getChoiceB() %><br>
						<input type="radio" name='<%=j+1%>' id='<%=c%>' value='C'><%=sChoose.getChoiceC() %><br>
						<input type="radio" name='<%=j+1%>' id='<%=d%>' value='D'><%=sChoose.getChoiceD() %><br>
					</div>
					<script>
						$(".right-in").append("<div class='sub_no' id="+'<%=sub_sc%>'+">"+'<%=j+1%>.'+"</div>");
						$('#'+'<%=sub_sc%>').append("<div class='gap'></div><div class='ans-bmg' id="+'<%=sub_sc_bmg%>'+"></div>");
						$('#'+'<%=a%>').click(function(){
							if($(this).val()=='<%=sChoose.getChoice_answer()%>'){
								$('#'+'<%=choose_ans%>').val('<%=qType3.getqScore()%>');
							}
							else{
								$('#'+'<%=choose_ans%>').val(0);
							}
							$('#'+'<%=sub_sc_bmg%>').removeClass("ans-bmg");
							$('#'+'<%=sub_sc_bmg%>').addClass("ans-bmg-after");
							
						});
						$('#'+'<%=b%>').click(function(){
							if($(this).val()=='<%=sChoose.getChoice_answer()%>'){
								$('#'+'<%=choose_ans%>').val('<%=qType3.getqScore()%>');
							}
							else{
								$('#'+'<%=choose_ans%>').val(0);
							}
							$('#'+'<%=sub_sc_bmg%>').removeClass("ans-bmg");
							$('#'+'<%=sub_sc_bmg%>').addClass("ans-bmg-after");
							
						});
						$('#'+'<%=c%>').click(function(){
							if($(this).val()=='<%=sChoose.getChoice_answer()%>'){
								$('#'+'<%=choose_ans%>').val('<%=qType3.getqScore()%>');
							}
							else{
								$('#'+'<%=choose_ans%>').val(0);
							}
							$('#'+'<%=sub_sc_bmg%>').removeClass("ans-bmg");
							$('#'+'<%=sub_sc_bmg%>').addClass("ans-bmg-after");
							
						});
						$('#'+'<%=d%>').click(function(){
							if($(this).val()=='<%=sChoose.getChoice_answer()%>'){
								$('#'+'<%=choose_ans%>').val('<%=qType3.getqScore()%>');
							}
							else{
								$('#'+'<%=choose_ans%>').val(0);
							}
							$('#'+'<%=sub_sc_bmg%>').removeClass("ans-bmg");
							$('#'+'<%=sub_sc_bmg%>').addClass("ans-bmg-after");
							
						});
					</script>
		<%			
					
					}
				}
				else if(tyStrings[i]=="填空题"){
					for(int j=0;j<completions.size();j++){
						Completion completion=(Completion)completions.get(j);
		%>
					<div>
						<p><%=j+1 %>、<%=completion.getCompletion_content() %></p>
						<%String ans_id=str[i]+(j+1); %>
						<%String sub_no=str[i]+(j+1)+"c"; %>
						<%String sub_bmg=str[i]+(j+1)+"bmg"; %>
						<p>答案：<input type="text" style="" id='<%=ans_id%>'>
								<input type="hidden" name="comple_score" value="0">   <%-- 将答案隐藏--%>
						</p>
					</div>
					<script>
						$(".right-in").append("<div class='sub_no' id="+'<%=sub_no%>'+">"+'<%=j+1%>.'+"</div>");
						$('#'+'<%=sub_no%>').append("<div class='gap'></div><div class='ans-bmg' id="+'<%=sub_bmg%>'+"></div>");
						$('#'+'<%=ans_id%>').change(function(){
							if($(this).val()!=''){
								$('#'+'<%=sub_bmg%>').removeClass("ans-bmg");
								$('#'+'<%=sub_bmg%>').addClass("ans-bmg-after");
							}else{
								$('#'+'<%=sub_bmg%>').removeClass("ans-bmg-after");
								$('#'+'<%=sub_bmg%>').addClass("ans-bmg");
							}
							if($(this).val()=='<%=completion.getCompletion_answer()%>'){
								$(this).next().val(<%=qType3.getqScore()%>);								
							}								
							else{
								$(this).next().val(0);
							}
						});						
					</script>
					<p></p>
		<%			}
				}
				else if(tyStrings[i]=="判断题"){
					for(int j=0;j<tfngs.size();j++){
						Tfng tfng=(Tfng)tfngs.get(j);
		%>				
						<div>
							<p>
								<span><%=j+1 %>、</span>
								<%String ans_id=str[i]+(j+1); %>
								<%String sub_tf=str[i]+(j+1)+"tf"; %>
								<%String sub_tf_bmg=str[i]+(j+1)+"bmg"; %>
								<span>
									<select id='<%=ans_id %>'><option></option><option value="1">T</option><option value="0">F</option></select>
								</span>
								<span><%=tfng.getTfng_content() %></span>
								<span><input type="hidden" name="tfng_score" value="0" id="ok"></span>
							</p>
							
						</div>
						<script>
							$(".right-in").append("<div class='sub_no' id="+'<%=sub_tf%>'+">"+'<%=j+1%>.'+"</div>");
							$('#'+'<%=sub_tf%>').append("<div class='gap'></div><div class='ans-bmg' id="+'<%=sub_tf_bmg%>'+"></div>");
							$("#"+'<%=ans_id%>').change(function(){
								if($(this).val()=='<%=tfng.getTfng_answer()%>'){
									$(this).parent().next().next().find("input").val(<%=qType3.getqScore()%>);
								}else{
									$(this).parent().next().next().find("input").val(0);
								}
								if($(this).val()!=''){
									$('#'+'<%=sub_tf_bmg%>').removeClass("ans-bmg");
									$('#'+'<%=sub_tf_bmg%>').addClass("ans-bmg-after");
								}else{
									$('#'+'<%=sub_tf_bmg%>').removeClass("ans-bmg-after");
									$('#'+'<%=sub_tf_bmg%>').addClass("ans-bmg");
								}
							});
						</script>
		<%			}
				}
				else{
					for(int j=0;j<subjective_items.size();j++){
						Subjective_item sItem=(Subjective_item)subjective_items.get(j);
		%>				
						<div>
							<p><%=j+1 %>、<span><input type="hidden" value='<%=sItem.getItem_content() %>' name="item_content"><%=sItem.getItem_content() %></span></p>
							<%String ans_id=str[i]+(j+1); %>
							<%String sub_item=str[i]+(j+1)+"item"; %>
							<%String sub_item_bmg=str[i]+(j+1)+"bmg"; %>
							<input type="hidden" name="item_score" value='<%=qType3.getqScore()%>'>
							<textarea class="subject-tarea" rows='6' id='<%=ans_id%>' name='item_answer'></textarea>
						</div>
						<script>
							var sub_no='<%=str[i]%>'+'<%=j+1%>';
							$(".right-in").append("<span id="+sub_no+"></span>");
							$(".right-in").append("<div class='sub_no' id="+'<%=sub_item%>'+">"+'<%=j+1%>.'+"</div>");
							$('#'+'<%=sub_item%>').append("<div class='gap'></div><div class='ans-bmg' id="+'<%=sub_item_bmg%>'+"></div>");
							$("#"+'<%=ans_id%>').change(function(){
								if($(this).val()!=''){
									$('#'+'<%=sub_item_bmg%>').removeClass("ans-bmg");
									$('#'+'<%=sub_item_bmg%>').addClass("ans-bmg-after");
								}else{
									$('#'+'<%=sub_item_bmg%>').removeClass("ans-bmg-after");
									$('#'+'<%=sub_item_bmg%>').addClass("ans-bmg");
								}
							});
						</script>
		<%			}
				}
		%>
			</div><br>							
		<%}%>
		</div>	
	</div>
	<div class="foot">
			<input type="button" value="提交" onclick="complete()">
		</div>
	</form>
	<script type="text/javascript">
		function complete(){
			if(confirm("确定要提交吗？")){
				document.form1.submit();
			}
		}
	</script>
</body>
</html>