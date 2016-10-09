$(document).ready(function(){
	
	//消息列表
	$(".choice ul li:nth-child(3)").click(function(){
		$("#scanMessage table tr:not(:first)").remove();
		$.post('ScanMessageServlet',{method:'showMessage'},function(data){
			var obj = eval('(' +data+ ')');
			$.each(obj,function(index,p){
				$("#scanMessage table").append("<tr><td><a href='#' onclick='toMessage_content(this)' class='msgTitle'>"+
						obj[index].message_title+"</a><div>"+obj[index].message_time+"</div></td></tr>");
			});
		});
	});
	
	$("#cancel").click(function(){
		$("#new_pwd").val("");
		$("#sure").val("");
	});
});

//修改密码
function ChangeKey(stuNo){
	//alert("ok");
	var new_pwd=$('#new_pwd').val();
	var sure=$("#sure").val();
	if(new_pwd==""&&sure==""){
		alert("请输入密码！");
		return;
	}
		
	if(new_pwd!=sure){
		alert("两次输入的密码不一致！");
	}
	else{
		$.post('ChangeKeyServlet',{method:'Change',newPassword:new_pwd},function(data){
			alert(data);
			$("#new_pwd").val("");
			$("#sure").val("");
		});
	}
}

//显示消息内容
var objWin;
function toMessage_content(obj){
	//获取被点击标签的值
	var str=obj.text;
	if(objWin==null||objWin.closed){
		objWin=window.open("message_content.jsp?"+encodeURI(str),"消息查看");//记得加上编码方式
	}else{
		objWin.focus();
	}
}

//显示试卷信息
function showPaper(){
	$("#paper tr:not(:first)").empty();
	$.post('PaperInfoServlet',{method:'showPaper',subject:$('#pa_subject').val()},function(data){
		var obj=eval('('+data+')');	
		$.each(obj,function(index,p){
			var state=null;//考试状态变量
			$.ajax({
			       url: "PaperInfoServlet",
			       async: false,//改为同步方式
			       type: "POST",
			       data: { method:'testState',subject:$('#pa_subject').val(),time:obj[index].qTime},
			       success: function (tf) {
			           state = tf;
			       }
			});
			var no=index+1;//设置试卷的序号
			var date=new Date(obj[index].qTime);//获取考试时间obj[index].qTime.replace(/-/,"/")
			var now=new Date();
			var test_date=date.getFullYear()+"/"+(date.getMonth()+1)+"/"+date.getDate();
			var now_date=now.getFullYear()+"/"+(now.getMonth()+1)+"/"+now.getDate();
			var str=null;
			
			if(state=='已考'){
				//当考试状态为“已考”，则设置链接无效
				str="<a href='#' onclick='show_alert1()'>"+obj[index].paper_name+"</a>";
			}else{
				if(test_date==now_date){//date.getTime()==now.getTime()
					//当考试时间等于当前时间则为<a>标签加上链接,要给参数加上两次编码encodeURI
					str="<a href='ShowPaperServlet?paper_name="+encodeURI(encodeURI(obj[index].paper_name))+
						"&test_time="+obj[index].qTest_time+"&subjectName="+encodeURI(encodeURI($('#pa_subject').val()))+"' target='_blank'>"+
						obj[index].paper_name+"</a>";
				}
				else if(date.getTime()>now.getTime()){					
					str="<a href='#' onclick='show_alert2()'>"+obj[index].paper_name+"</a>";
				}
				else{
					str="<a href='#' onclick='show_alert3()'>"+obj[index].paper_name+"</a>";
				}
				
			}
			var time=date.getFullYear()+"年"+(date.getMonth()+1)+"月"+date.getDate()+"日";//将获取到的日期转换格式
			
			$("#paper").append("<tr><td>"+no+"</td><td>"+str+"</td><td>"+
					obj[index].qTest_time+"分钟</td><td>"+time+"</td><td>"+
					obj[index].tName+"</td><td>"+state+"</td></tr>");
		});
	});
}

function show_alert1(){
	alert("你已考过该试卷！");
}
function show_alert2(){
	alert("考试时间还没到！");
}

function show_alert3(){
	alert("考试时间已过！");
}

//显示成绩
function showScore(){
	$("#test_result tr:not(:first)").empty();
	$.post('PaperInfoServlet',{method:'showScore',subject:$("#result_subject").val()},function(data){
		var obj=eval('('+data+')');
		$.each(obj,function(index,p){
			var str="<tr><td>"+(index+1)+"</td><td>"+obj[index].paper_name+"</td><td>"+obj[index].avg.toFixed(2)+
				"</td><td>"+obj[index].myScore+"</td><td>"+obj[index].rank+"</td></tr>";
			$("#test_result").append(str);
		});
	});
}
