<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="me.zxks.entity.Score" %>

    <% ArrayList scores=(ArrayList)session.getAttribute("itemInfo");  %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>主观题评分</title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
	<style type="text/css">
		.eval_main{padding:2%;}
		#item_content{width:100%;}
		.answer{color:red;}
		.Bold{font-weight:bold;}
		.getScore{width:50px;}
		.item-border{border-bottom:1px dashed #9D9D9D;padding-bottom:1%;}
		input[type=button]{border-radius:5px;width:60px;height:40px;outline:none;background:#1188ff;
			border:none;margin-left:45%;color:#fff;font-size:15px;cursor:pointer;}
	</style>
	<script>
		function check_score(){
			if(document.form1.getScore.value==""){
				alert("分数不得为空！");
				return;
			}else{
				document.form1.submit();
			}
		}
	</script>
</head>
<body>
<form method="post" action="CalItemServlet" name="form1">
	<div class="eval_main">
		<%
			for(int i=0;i<scores.size();i++){
				Score score=(Score)scores.get(i);
				%>
			<div class="item-border">
				<div>
					<h3 class="content"><%=i+1 %>.<%=score.getItem_content() %></h3>
					<input type="hidden" name="stuNo" value='<%=score.getStuNo()%>'>
					<input type="hidden" value='<%=score.getItem_id()%>' name="item_id">
				</div>
				<div><span class="Bold">参考答案：</span><p class="answer"><%=score.getItem_full_answer() %></p></div>
				<div><span class="Bold">考生答案：</span><p class="stu_answer"><%=score.getItem_answer() %></p></div>
				<div>
					<span class="Bold">满分：</span><span id=''><%=score.getItem_full_score() %></span>&nbsp;&nbsp;
					<span class="Bold">得分：</span><span><input type="text" class="getScore" name="getScore" id='<%=i+1%>'></span>&nbsp;<span class="answer">*</span>
				</div>
				<script type="text/javascript">
					$('#'+'<%=i+1%>').change(function(){
						if($(this).val()>'<%=score.getItem_full_score() %>'){
							alert("输入的分数不得大于满分！");
							return;
						}
					});
				</script>
			</div>
				<%
			}
		%>	
	</div>
	<input type="button" onclick="check_score()" value="提交">
</form>	
</body>
</html>