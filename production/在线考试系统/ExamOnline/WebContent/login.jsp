<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>在线考试系统用户登陆</title>
	<link rel="stylesheet" type="text/css" href="./css/login_style.css">
	<script type="text/javascript"
	src="<c:url value='/js/jquery.min.js'/>"></script>
	<script type="text/javascript">
	/* window.onload=main;
	function main(){
		document.getElementById("changesrc").onclick=change();
	}
		function change(){
			document.getElementById("checkcode").src="verifyImg";
		} */
	function check(){
		if(document.form1.username.value=="")
			alert("请输入姓名");
		else if(document.form1.userpassword.value=="")
			alert("请输入密码");
		else if(document.form1.check_core.value=="")
			alert("请输入验证码"); 
		else{
			document.form1.submit();
		}
	}	
	function _change() {
		$("#vCode").attr("src",
			"/ExamOnline/VerifyCodeServlet?a=" + new Date().getTime());
	}
	</script>
</head>
<body>
	<div id="head">在线考试系统</div>
	<div id="main">
		<form method="post" name="form1" action="LoginServlet">
			<table>

				<tr>
					<td>用户名：</td>
					<td><input type="text" name="username"></td>
				</tr>
				<tr>
					<td>密码：</td>
					<td><input type="password" name="userpassword"></td>
				</tr>
				<tr>
					<td>角色：</td>
					<td>
						<select name="role">
							<option selected>考生</option>
							<option>题库管理员</option>
							<option>监考员</option>
							<option>管理员</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>验证码：</td>
					<td><input type="text" name="check_core"></td>
				</tr>
				<tr>
					<td></td>
					<td><span><img id="vCode" style="width: 70px;height: 34px;border: 1px solid #000;" src="<c:url value='/VerifyCodeServlet'/>" />
					<a href="javascript: _change()" style="font-size:13px;"> 看不清，换一张</a></span></td>
					
				</tr>
			</table>
						
			<input type="button" value="提交"  class="bottom" onclick="check()">
			<input type="reset" value="重置" class="bottom">
		</form>
	</div>
</body>
</html>