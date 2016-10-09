$(document).ready(function(){
	$("#subject_score").change(function(){
		$(".paper_name").empty();
		$(".paper_name").append("<option>请选择</option>");
		$.post('PaperInfoServlet',{method:'showPaper',subject:$("#subject_score").val()},function(data){
			var obj=eval('('+data+')');
			$.each(obj,function(index,p){
				$(".paper_name").append("<option value="+obj[index].paper_name+">"+obj[index].paper_name+"</option>");
			});
		});
	});

	$(".paper_name").change(function(){
		$(".class1").empty();
		$(".class1").append("<option>请选择</option>");
		$.post('TestResultServlet',{method:'find_class',paper_name:$(".paper_name").val()},function(data){
			var obj=eval('('+data+')');
			$.each(obj,function(index,p){
				$(".class1").append("<option value="+obj[index]+">"+obj[index]+"</option>");
			});
		});
	});
	
	$(".class1").change(function(){
		$("#eval tr:not(:first)").remove();
		$.post('TestResultServlet',{method:'get_item_content',grade:$(this).val().substring(0,2),
			className:$(this).val().substr(2),paper_name:$(".paper_name").val()},function(data){
				var obj=eval('('+data+')');
				$.each(obj,function(index,p){
					$("#eval").append("<tr><td>"+obj[index].stuNo+"</td><td>"+obj[index].stuName
							+"</td><td>"+obj[index].test_time+"</td><td><a href='ItemGradingServlet?stuNo="+encodeURI(encodeURI(obj[index].stuNo))
							+"&paper_name="+encodeURI(encodeURI(obj[index].paper_name))+"' target='_blank'>进入评卷</a></td></tr>");
				});
			});
	});
	/**
	 * 查找有考试记录的班级
	 */
	$("#subject_testResult").change(function(){
		
		$("#testResultClass").empty();
		$("#testResultClass").append("<option>请选择</option>");
		$.post('TestResultServlet',{method:'searchClass',subjectName:$(this).val()},function(data){
			var obj=eval('('+data+')');
			$.each(obj,function(index,p){
				$("#testResultClass").append("<option value="+obj[index]+">"+obj[index]+"</option>");
			});			
		});
		
	});
	
	/**
	 * 查找试卷
	 */
	
	$("#testResultClass").change(function(){
		$("#analyse tr:not(:first)").empty();
		
		$.post('TestResultServlet',{method:'searchPaper',grade:$(this).val().substring(0,2),
			className:$(this).val().substr(2,10)},function(data){
			var obj=eval('('+data+')');
			$.each(obj,function(index,p){
				$("#analyse").append("<tr><td>"+(index+1)+"</td><td><a href='#' onclick='show_chart()' title='点击查看考试情况'>"
					+obj[index].paper_name+"</a></td><td>"+obj[index].test_num+"</td><td>"+obj[index].avg.toFixed(2)+"</td><td>"+obj[index].test_time+"</td></tr>");
				var under_six=obj[index].under_six;
				var six=obj[index].six;
				var seven=obj[index].seven;
				var eight=obj[index].eight;		
				var nine=obj[index].nine;
				var ten=obj[index].ten;

				$("#usix-height").html("");
				$("#six-height").html("");
				$("#seven-height").html("");
				$("#eight-height").html("");
				$("#nine-height").html("");
				$("#ten-height").html("");
				
				$("#max").html(obj[index].max);
				$("#min").html(obj[index].min);
				$("#avg").html(obj[index].avg.toFixed(2));
				
				$("#under-six").height(under_six*5);
				$("#six").height(six*5);
				$("#seven").height(seven*5);
				$("#eight").height(eight*5);
				$("#nine").height(nine*5);
				$("#ten").height(ten*5);
				
				var y=$(".bar-box").height()+80;
				$("#usix-height").parent().height(y-$("#under-six").height());
				$("#six-height").parent().height(y-$("#six").height());
				$("#seven-height").parent().height(y-$("#seven").height());
				$("#eight-height").parent().height(y-$("#eight").height());
				$("#nine-height").parent().height(y-$("#nine").height());
				$("#ten-height").parent().height(y-$("#ten").height());
				
				if(under_six!=0)
					$("#usix-height").html(under_six+"人");
				if(six!=0)
					$("#six-height").html(six+"人");
				if(seven!=0)
					$("#seven-height").html(seven+"人");
				if(eight!=0)
					$("#eight-height").html(eight+"人");
				if(nine!=0)
					$("#nine-height").html(nine+"人");
				if(ten!=0)
					$("#ten-height").html(ten+"人");
			});
			if(obj==null){
				$("#analyse").append("<tr></tr>");
			}
			
		});
		
	});
});

//打开弹窗
function show_chart(){
	$(".background").fadeIn(100);
	$(".po-bar-chart").slideDown(200);
}

function close_chart(){
	$(".background").fadeOut(100);
	$(".po-bar-chart").slideUp(200);
}