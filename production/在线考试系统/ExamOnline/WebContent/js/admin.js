$(document).ready(function(){
	//设置当前位置
	$("div.content li").click(function(){
		var str=$(this).parent().parent().attr("id");//获取元素的id
		var str1=$("#"+str+"_title").text();
		var location1=str1.substr(0,str1.indexOf('-'))
		$("#location1").html(location1);
		$("#location2").html(">>"+$(this).text());
	}); 
	$("#Info_query1").click(function(){
		$("#stuInfo_query").show();
		$("#stuInfo_insert").hide();
		$("#tInfo_manage").hide();
		$("#data_maintain").hide();
		$("#data_restore").hide();
	});
	$("#Info_insert").click(function(){
		$("#stuInfo_query").hide();
		$("#stuInfo_insert").show();
		$("#tInfo_manage").hide();
		$("#data_backup").hide();
		$("#data_restore").hide();
	});
	
	$("#list2_title").click(function(){
		$("#stuInfo_query").hide();
		$("#stuInfo_insert").hide();
		$("#tInfo_manage").show();
		$("#data_backup").hide();
		$("#data_restore").hide();
		$("#location1").html($(this).text());
		$("#location2").html("");
	});
	
	$("#list2_title").click(function(){
		$("#stuInfo_query").hide();
		$("#stuInfo_insert").hide();
		$("#tInfo_manage").show();
		$("#data_backup").hide();
		$("#data_restore").hide();
	});
	$("#backup").click(function(){
		$("#stuInfo_query").hide();
		$("#stuInfo_insert").hide();
		$("#tInfo_manage").hide();
		$("#data_backup").show();
		$("#data_restore").hide();
	});
	$("#restore").click(function(){
		$("#stuInfo_query").hide();
		$("#stuInfo_insert").hide();
		$("#tInfo_manage").hide();
		$("#data_backup").hide();
		$("#data_restore").show();
	});
	$(".tblist2 select").addClass("hide-select");
	$(".tblist2 select").attr("disabled",true);
	$(".tblist2 .hide-text").attr("disabled",true);

	//弹出添加教师信息的窗口
	$("#add_teacher").click(function(){
		$(".background").fadeIn(100);
		$(".popover").slideDown(200);
	});

	//关闭窗口
	$(".close").click(function(){
		$(".background").fadeOut(100);
		$(".popover").slideUp(200);
	});
	

	
	//取消
	$("#teacher-cancel").click(function(){
		/*$("#po-tNo").val("");
		$("#po-tName").val("");*/
		$(".background").fadeOut(100);
		$(".popover").slideUp(200);
	});
});

//二级菜单
function showmenu(id,sign){
	var list=$("#"+id);
	var sign=$("#"+sign);
	if(list.css("display")=='none'){
		list.css("display","block");
		sign.html("-");
		var str=$("#"+id+"_title").text();
		var str1=str.substr(0,str.indexOf('-'));
		$("#location1").html(str1);
		$("#location2").html("");
	}else {
		list.css("display","none");
		sign.html("+");
	}
}


//将控件设为可编辑状态
function edit_stu(obj){
	$(obj).parent().parent().find("input").removeClass("hide-text");
	$(obj).parent().parent().find("input").attr("disabled",false);
	$(obj).parent().parent().find("select").removeClass("hide-select");
	$(obj).parent().parent().find("select").attr("disabled",false);
	$(obj).next().next().removeClass("invalid");
	$(obj).next().next().addClass("valid");
}

//保存信息
function save_change(obj,role){
	//alert("d");
	$(obj).parent().parent().find("input").addClass("hide-text");
	$(obj).parent().parent().find("input").attr("disabled",true);
	$(obj).parent().parent().find("select").addClass("hide-select");
	$(obj).parent().parent().find("select").attr("disabled",true);
	$(obj).removeClass("valid");
	$(obj).addClass("invalid");
	if(role=="student"){
		var stuNoValue=$.trim($(obj).parent().prev().prev().prev().prev().prev().text());
		var stuNameValue=$(obj).parent().parent().find("input[class='hide-text']").val();
		var stuGenderValue=$(obj).parent().parent().eq(0).children().eq(2).children().val();
		var className=$(obj).parent().parent().eq(0).children().eq(4).children().val();
		$.post("DoAdminServlet",{method:"saveStudent",stuNo:stuNoValue,
			stuGender:stuGenderValue,stuName:stuNameValue,className:className},
			function(result){
			if(!parseInt(result)){alert("信息保存失败！");}
		});
	}else{
		
	}
}

//删除学生信息
function delete_stu(obj){
	var stuNoValue=$(obj).parent().prev().prev().prev().prev().prev().text();
	if(confirm("确定要删除该学生信息吗？")){
		$(obj).parent().parent().remove();
		$.post("DoAdminServlet",{method:"deleteStudent",stuNo:stuNoValue},
				function(result){
			if(!parseInt(result)){alert("信息删除失败！");}
		});
	}
}

//删除教师信息
function delete_t(obj){
	if(confirm("确定要删除该教师信息吗？")){
		$(obj).parent().parent().remove();
	}
}
//提交插入的信息
function insert(){
	if($("#insert_stuNo").val()==""){
		alert("请输入学号！");
	}
	else if($("#insert_stuName").val()==""){
		alert("请输入姓名！");
	}
	else if($(".insertInfo .institute1").val()==""){
		alert("请选择学院！");
	}
	$.post("DoAdminServlet",{method:"insertStudent",stuNo:$("#insert_stuNo").val(),
		stuName:$("#insert_stuName").val(),stuGender:$("#insert_gender").val(),
		className:$(".insertInfo .class1").val()},function(str){
			alert(str);
			$("#insert_stuNo").val('');
			$("#insert_stuName").val('');
			$(".insertInfo .institute1").val('');
			$(".insertInfo .class1").val('');
		});
	
}
//取消信息插入  清空
function cancel(){
	$("#insert_stuNo").val("");
	$("#insert_stuName").val("");
	$(".insertInfo .institute1").val("");
	$(".insertInfo .institute1 .class1").val("");
}