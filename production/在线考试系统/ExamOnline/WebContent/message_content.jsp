<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>消息查看</title>
	<style type="text/css">
		.bmg{margin-top:6%;margin-left:20%;margin-right:20%;height:500px;box-shadow: 2px 4px 10px #004B97;
			border-radius:10px;border:1px solid #ADADAD;}
		.main{margin-top:10%;margin-left:10%;margin-right:10%;}
		.main input{margin-left:45%;margin-top:20%;border-radius:5px;width:60px;height:30px;
				background:#2894FF;color:#fff;cursor:pointer;}
		.top{text-align:center;border-bottom:2px solid #ADADAD;}
		.top-1{font-size:25px;font-family:SimHei;font-weight:bold; color:#FF0000;height:10%;margin-bottom:3%;}
		.top-2{margin-bottom:2%;font-size:15px;color:#6c6c6c;}
		.msg-content{text-align:center;margin-top:5%;height:100px;}
		.releaser{float:right;margin-top:10%;}
	</style>
	<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<script>
		$(document).ready(function(){
			var query=decodeURI(location.search.substring(1));//获取student.jsp传递过来的数据,要用decodeURI进行解码
			//alert(query);
			$.post('ScanMessageServlet',{method:'showDetail',title:query},function(data){
				var obj=eval('('+data+')');
				$.each(obj,function(index,p){
					$(".top-1").html(obj[index].message_title);
					$(".top-2").html("发布时间："+obj[index].message_time);
					$(".msg-content").html(obj[index].message_content);
					$(".releaser").html("发布人："+obj[index].message_authorName);
				});
			});
			$("input").click(function(){
				window.close();
			});
		});
	</script>
</head>
<body>
	<div class="bmg">
		<div class="main">
			<div class="top">
				<div class="top-1">消息标题</div>
				<div class="top-2">发布时间</div>
			</div>
			<div class="msg-content">消息内容</div>
			<div class="releaser">发布人：001</div>
			<input type="button" value="关闭">
		</div>
	</div>
</body>
</html>