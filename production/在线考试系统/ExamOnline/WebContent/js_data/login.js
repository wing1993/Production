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

function VCodeTest(){
	$.post("LoginServlet",{method:"VCodeTest",
			vCode:$("input[name='check_core']").val()},
			function(str){
				alert(str);
			});
}






