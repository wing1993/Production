$(function(){
	$("#stuState").hide();
	$("#void_paper").hide();
});

function showStu(obj){
	$("#stuState").show();
	$("#void_paper").hide();
	$("#location").text($(obj).text());
	onlinestu();
}

function voidPaper(obj){
	$("#stuState").hide();
	$("#void_paper").show();
	$("#location").text($(obj).text());
	
}

$('document').ready(function(){ 
	ajaxGetInstitute();//填充学院的下拉框
	ajaxGetPapername();//填充试卷的下拉框
});
function getClass(obj,str){
	ajaxGetClass(obj,str);
}
function ajaxGetClass(obj,str){
	//根据学院获取该学院下的所有班级
    var instituteValue = $(obj).val();
    $.post('OptionServlet',{method:'ajaxClassOption',instituteName:instituteValue},
 		   function(list){
		   var array = eval('(' +list+ ')');
		   $(str).html("");
		   for(var i=0;i<array.length;i++){
            var opt = '<option value='+array[i]+'>'+array[i]+'</option>';
            $(str).append(opt);
            }

			showOnlineStu();
    }); 
}
function ajaxGetInstitute(){
    //获取学院下拉框内容
   $.ajax({
	    cache: false,
		async: false,
		type: "POST",
		data: {method: "ajaxInstituteOption"},
		url: "OptionServlet",
		 success: function (list) {
             var obj = eval('(' +list+ ')');
             for(var i=0;i<obj.length;i++){
                var opt = '<option value='+obj[i]+'>'+obj[i]+'</option>';
                $(".institute1").append(opt);
             }
         },
         error: function (XMLHttpRequest, textStatus, errorThrown) {
             alert("ajax学院信息获取失败-"+errorThrown);
         }
	});
}
function ajaxGetPapername(){
   //获取试卷下拉框内容
   $.ajax({
	    cache: false,
		async: false,
		type: "POST",
		data: {method: "ajaxPapernameOption"},
		url: "OptionServlet",
		 success: function (list) {
             var obj = eval('(' +list+ ')');
             for(var i=0;i<obj.length;i++){
                var opt = '<option value='+obj[i]+'>'+obj[i]+'</option>';
                $("#paper1").append(opt);
             }
         }
   });
}


//显示在线人数以及在线学生信息
$('document').ready(function(){ 
	onlinestu();
});
function onlinestu(){
	$.post("DoInvigilatorServlet",{method:"getOnlineTotal"},function(n){
		$("#online_count").text(parseInt(n));
	});
	$("#online_tb1 tr:not(:first)").empty();
	$.post("DoInvigilatorServlet",{method:"ShowStudentState",className:$("#class").val()},
			function(array){
		var obj=eval('('+array+')');
		var gender=null;
		for(var i=0;obj.length;i++){
			if(obj[i].stuGender==1){ gender="男";}
			else gender="女";
			var opt="<tr><td>"+obj[i].stuNo+"</td><td>"+obj[i].stuName+"</td><td>"+gender+
			     "</td><td>"+obj[i].loginTime+"</td></tr>";
			$("#online_tb1").append(opt);
		}
	});}
//筛选在线学生信息
function showOnlineStu(){
	$("#online_tb1 tr:not(:first)").empty();
	$.post("DoInvigilatorServlet",{method:"ShowStudentState",className:$("#class").val()},
		function(array){ var obj=eval('('+array+')');
		for(var i=0;obj.length;i++){
			if(obj[i].stuGender==1){var gender="男";}
			else var gender="女";
			var opt="<tr><td>"+obj[i].stuNo+"</td><td>"+obj[i].stuName+"</td><td>"+gender+
			     "</td><td>"+obj[i].loginTime+"</td></tr>";
			$("#online_tb1").append(opt);
		}
	});
}

/**
 * 信息分页显示
 */
pagepc=parseInt(1);  //设置当前页(初始化)   全局的页数
pages_total=parseInt(1);
//将数据输出
function InputPaper(array){
	var obj = eval('(' +array+ ')');
	$("#online_tb2 tr:not(:first)").empty();
	for(var i=0;i<obj.length;i++){
		if(obj[i].test_time==undefined) {obj[i].test_time="未考";}
		if(obj[i].test_time=="未考") {obj[i].score=0;}
		if(obj[i].stuGender==1) { stuGender="男";}
		else{stuGender="女";}
		var opt="<tr><td>"+obj[i].stuNo+"</td><td>"+obj[i].stuName+"</td><td>"+stuGender+
		"</td><td>"+$(".paper1").val()+"</td><td>"+obj[i].test_time+"</td><td>"+
		obj[i].score+"</td><td><input type='button' onclick='cancelScore(this)' " +"value='试卷作废'></td></tr>";
		$("#online_tb2").append(opt);
	}
}
//先查询出总的页数再默认显示第一页的内容
function showStudentPaper(){
	if($(".paper1").val()=="请选择"){return;}
	$.post("DisplayByPagesServlet",{method:"getPagesTotal_Student",
		className:$("#class1").val(),instituteName:$("#institute1").val()},
		function(num){
			pages_total=parseInt(num);
			$.post("DisplayByPagesServlet",{method:"showStudentPaper",paper_name:$(".paper1").val(),
				className:$("#class1").val(),page:1},function(array){
			InputPaper(array);
		});
		varpage(pages_total);
	});
}
//按下一页 实现搜索内容跳转
function next(){
	if(pagepc<pages_total){    //如果当前页数最大了，不能下一页
		$.post("DisplayByPagesServlet",{method:"showStudentPaper",paper_name:$(".paper1").val(),
			className:$("#class1").val(),page:parseInt(pagepc)+parseInt(1)},function(array){
		InputPaper(array);
		});
		pagepc=parseInt(pagepc)+parseInt(1);   //按下一页，相当的页数增加
		varpage(pages_total);
	}else{
		alert("这已经是最后一页！");
	}
}	
//按上一页 实现搜索内容跳转
function last(){
	if(pagepc>1){    //如果当前页数最大了，不能下一页
		$.post("DisplayByPagesServlet",{method:"showStudentPaper",paper_name:$(".paper1").val(),
			className:$("#class1").val(),page:parseInt(pagepc)-parseInt(1)},function(array){
				InputPaper(array);
		});
		pagepc=parseInt(pagepc)-parseInt(1);   //按下一页，相当的页数增加
		varpage(pages_total);
	}else{
		alert("这是第一页！");
	}
}	
//按数字键  实现页数跳转
function showQuestion(obj){
	pagepc=$(obj).text();     //赋值页数
	$.post("DisplayByPagesServlet",{method:"showStudentPaper",paper_name:$(".paper1").val(),
		className:$("#class1").val(),page:$(obj).text()},function(array){
			InputPaper(array);
	});
		varpage(pages_total);    //调用页数列表的改变函数
}
//------- 页数显示的变化------------
function varpage(pages_total){
	$("#page_no button").remove();
	$("#now_all span").empty();
	$("#now_all").append("<span class='pages'>第<span>"+pagepc+"</span>/<span class='total_count'>"+pages_total+"</span>页 </span>");
	for(var p=pagepc-2;p<=pages_total;p++){
		if(p>=1 && p<=parseInt($(".total_count").text())){
			$("#page_no").append("<button class='pages'  onclick='showQuestion(this)'>"+p+"</button>");
		}
	}
}
//试卷作废  取消成绩
function cancelScore(obj){
	var stuNoValue=$(obj).parent().prev().prev().prev().prev().prev().prev().text();
	$.post("DoInvigilatorServlet",{method:"cancelStuScore",paper_name:$(".paper1").val(),
		stuNo:stuNoValue},function(result){
			alert(result);
			$(obj).parent().prev().text("0");
	});
}


